package com.olga.server;

import com.olga.command.Exit;
import com.olga.command.Save;
import com.olga.command.manager.CommandManager;
import com.olga.i18n.Messenger;
import com.olga.i18n.MessengerFactory;
import com.olga.io.XMLParser;
import com.olga.message.Message;
import com.olga.print.implementation.FormatterImpl;
import com.olga.print.implementation.PrinterImpl;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Server {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    private ServerSocketChannel serverSocket;
    private Selector selector;
    private final ServerHelper serverHelper;

    public Server(ServerHelper serverHelper) {
        this.serverHelper = serverHelper;
    }

    public void init() throws IOException {
        // Получаем Selector
        selector = Selector.open();

        // Получаем ServerSocketChannel, задаем ему адрес, а также регистрируем полученный Selector
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 5454));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        serverHelper.init();


        // Запускаем новый поток, который слушает пользовательский ввод
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            String serverCommand;
            while (!(serverCommand = sc.nextLine()).equals("exit")) {
                if ("save".equals(serverCommand)) {
                    System.out.println(new Save(serverHelper.getMDataSet()).execute(null));
                } else {
                    System.out.println("Неизвестная команда: " + serverCommand);
                }
            }
            new Exit(serverHelper.getMDataSet()).execute(null);
        }).start();

    }

    public void start() throws IOException {

        init();

        System.out.println(messenger.getMessage("greeting_sever"));

        while (serverSocket.isOpen()) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                }
                if (key.isReadable()) {
                    try {
                        getRequest(key);
                    } catch (Exception e) {
                        key.cancel();
                        System.err.println(e.getMessage());
                        key.channel().close();
                    }
                }
                iter.remove();
            }
        }
    }

    private void getRequest(SelectionKey key) throws Exception {
        // Получаем SocketChannel, через который мы будем обмениваться данными (через ByteBuffer) с конкретным клиентом
        SocketChannel client = (SocketChannel) key.channel();

        // Инициализируем ByteBuffer
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024 * 1024);
        requestBuffer.clear();

        int read = client.read(requestBuffer);

        // Если полученное значение равно -1, то клиент по каким-то
        // причинам потерял связь (Просто отключился или возникла ошибка)
        if(read == -1) {
            throw new Exception("Соединение с одним из клиентов прервано: " +
                    ((SocketChannel) key.channel()).socket().getRemoteSocketAddress());
        }

        byte[] bytes = new byte[read];
        requestBuffer.position(0);
        requestBuffer.get(bytes);

        Message message = SerializationUtils.deserialize(bytes);
        message = serverHelper.getCommandManager().executeCommand(message);

        sendResponse(client, message);
    }

    private void sendResponse(SocketChannel client, Message message) throws IOException {
        // Сериализуем Message и заворачиваем его в ByteBuffer
        // Отправляем результат клиенту
        ByteBuffer responseBuffer = ByteBuffer.wrap(SerializationUtils.serialize(message));
        while (responseBuffer.hasRemaining()) {
            client.write(responseBuffer);
        }
        responseBuffer.clear();
    }

    private void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        // Принимаем клиента
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        // Регистрируем Selector для этого клиента
        client.register(selector, SelectionKey.OP_READ);
        System.out.println("New connection at: " + client.socket().getRemoteSocketAddress());
    }

    public static void main(String[] args) throws Exception {
        new Server(
                new ServerExecutorBuilder()
                        .setDataSet(XMLParser.read(System.getenv("XML_FILE")))
                        .setCommandManager(new CommandManager())
                        .setFormatter(new FormatterImpl())
                        .setPrinter(new PrinterImpl())
                        .build()
        ).start();
    }

}