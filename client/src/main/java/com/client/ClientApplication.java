package com.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            // Client client = new Client();
        } catch(Exception e) {
            e.printStackTrace();
        }

        Parent root = FXMLLoader.load(ClientApplication.class.getResource("/welcome.fxml"));
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root, 700, 400));
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}