package com.client;

import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class ClientApplication extends Application {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    @Override
    public void start(Stage stage) throws Exception {
        loadSceneWithLanguage(stage);
    }

    @SneakyThrows
    public static void loadSceneWithLanguage(Stage stage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(messenger.getBundle());
        loader.setLocation(ClientApplication.class.getClassLoader().getResource("auth.fxml"));

        Parent root = loader.load();
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}