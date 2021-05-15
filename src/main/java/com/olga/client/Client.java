package com.olga.client;

import com.olga.dragon.Dragon;
import com.olga.message.Message;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    private SocketChannel server;

    public Client() {
        try {
            open();
        } catch (Exception e) {
            stop("Server isn't available");
        }
    }

    public void open() throws Exception {
        try {
            server = SocketChannel.open(new InetSocketAddress("localhost", 5454));
            server.configureBlocking(true);
        } catch (IOException e) {
            throw new Exception("Сервер недоступен");
        }
    }

    public Message sendRequest(Message message) {
        // Сериализуем Message и обертываем его в ByteBuffer
        ByteBuffer requestBuffer = ByteBuffer.wrap(SerializationUtils.serialize(message));
        try {
            // Посылаем запрос серверу
            server.write(requestBuffer);
            requestBuffer.clear();
            // Получаем ответ
            return getResponse();
        } catch (Exception e) {
            stop(e.getMessage());
        }
        return null;
    }

    public Message getResponse() throws Exception {
        // Инициализируем ByteBuffer
        ByteBuffer responseBuffer = ByteBuffer.allocate(1024 * 1024);
        responseBuffer.clear();

        // Считываем информацию в ByteBuffer и получаем количество считанных байтов
        int read = server.read(responseBuffer);

        // Если результат -1, бросаем исключение
        if(read == -1) { throw new Exception("Связь прервалась"); }

        // Считываем ByteBuffer в массив байтов
        byte[] bytes = new byte[read];
        responseBuffer.position(0);
        responseBuffer.get(bytes);

        // Десериализуем Message, возвращаем результат
        return SerializationUtils.deserialize(bytes);
    }

    public Message prepareRequest(String command, Dragon dragon) {
        return new Message(command, dragon);
    }


    public void stop(String message) {
        System.out.println(message);
        System.exit(0);
    }

}