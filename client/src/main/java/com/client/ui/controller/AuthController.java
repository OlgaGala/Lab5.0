package com.client.ui.controller;

import com.api.entity.User;
import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import com.client.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * Контроллер, которы хранит логику аутентификации клиента через UI формы
 */
@Getter @Setter
public class AuthController extends GenericController {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    @FXML
    private PasswordField passwordField;

    public AuthController() {
        super("auth.fxml");
    }

    @FXML
    public void login(ActionEvent event) {
        try {
            Application.getClient().signIn(new User(getNameField().getText(), passwordField.getText()));
            showMainScene();
        } catch (Exception e) {
            e.printStackTrace();
            showWarning("loginFailure");
        }
    }

    @FXML
    public void register(ActionEvent event) {
        try {
            Application.getClient().signUp(new User(getNameField().getText(), passwordField.getText()));
            showMainScene();
        } catch (Exception e) {
            showWarning("registrationFailure");
        }
    }

    private void showMainScene() {
        hideWindow();
        Application.loadSceneWithLanguage(new Stage(), messenger.getBundle().getLocale(), "main.fxml");
    }

}