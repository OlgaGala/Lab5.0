package com.client.ui.controller;

import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import com.client.Client;
import com.client.ClientApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.ResourceBundle;


@Getter @Setter
public class AuthController {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    private static String nickname;
    private static String password;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    public static Client client;

    public AuthController() {
        client = new Client();
    }

    @FXML
    void login(ActionEvent event) {
        // TODO
    }

    @FXML
    void register(ActionEvent event) {
        // TODO
    }

    @FXML
    void showHelp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(messenger.getMessage("help"));
        alert.setContentText(messenger.getMessage("helpText"));
        alert.showAndWait();
    }

    @FXML
    void russian(ActionEvent event) {

        // Hide current window
        hideWindow();

        // Change language
        messenger.setBundle(
                ResourceBundle.getBundle("com.api.i18n.bundle.Language", new Locale("ru", "RU"))
        );

        // Load new windows with a new selected language
        ClientApplication.loadSceneWithLanguage(new Stage());
    }

    @FXML
    void ukrainian(ActionEvent event) {
        // Hide current window
        hideWindow();

        // Change language
        MessengerFactory.changeLanguage(
                ResourceBundle.getBundle("com.api.i18n.bundle.Language", new Locale("uk", "UA"))
        );

        // Load new windows with a new selected language
        ClientApplication.loadSceneWithLanguage(new Stage());
    }

    @FXML
    void macedonian(ActionEvent event) {
        // Hide current window
        hideWindow();

        // Change language
        MessengerFactory.changeLanguage(
                ResourceBundle.getBundle("com.api.i18n.bundle.Language", new Locale("mk", "MK"))
        );

        // Load new windows with a new selected language
        ClientApplication.loadSceneWithLanguage(new Stage());
    }

    @FXML
    void spanish(ActionEvent event) {
        // Hide current window
        hideWindow();

        // Change language
        MessengerFactory.changeLanguage(
                ResourceBundle.getBundle("com.api.i18n.bundle.Language", new Locale("es", "SV"))
        );

        // Load new windows with a new selected language
        ClientApplication.loadSceneWithLanguage(new Stage());
    }

    void hideWindow() {
        Window stageP = nameField.getScene().getWindow();
        stageP.hide();
    }

}