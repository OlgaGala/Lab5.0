package com.client.ui.controller;

import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import com.client.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

/**
 * Базовый контроллер, хранящий методы, которые используются сразу в нескольких котроллерах
 */
@Getter @Setter
public class GenericController {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    @FXML
    private TextField nameField;

    /**
     * Имя текущей view
     */
    private final String viewName;

    public GenericController(String viewName) {
        this.viewName = viewName;
    }

    @FXML
    public void showHelp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(messenger.getMessage("help"));
        alert.setContentText(messenger.getMessage("helpText"));
        alert.showAndWait();
    }

    @FXML
    public void russian(ActionEvent event) {
        hideWindow();
        Application.loadSceneWithLanguage(new Stage(), new Locale("ru", "RU"), viewName);
    }

    @FXML
    public void ukrainian(ActionEvent event) {
        hideWindow();
        Application.loadSceneWithLanguage(new Stage(), new Locale("uk", "UA"), viewName);
    }

    @FXML
    public void macedonian(ActionEvent event) {
        hideWindow();
        Application.loadSceneWithLanguage(new Stage(), new Locale("mk","MK"), viewName);
    }

    @FXML
    public void spanish(ActionEvent event) {
        hideWindow();
        Application.loadSceneWithLanguage(new Stage(), new Locale("es","SV"), viewName);
    }

    /**
     * Вызывает диалоговое окно с предупреждением
     * @param waningBody - текст с предупреждением или ошибкой
     */
    protected void showWarning(String waningBody) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(messenger.getMessage("warning"));
        alert.setHeaderText(messenger.getMessage("warning"));
        alert.setContentText(messenger.getMessage(waningBody));
        alert.showAndWait();
    }

    /**
     * Скрывает текущее окно
     */
    protected void hideWindow() {
        Window stageP = nameField.getScene().getWindow();
        stageP.hide();
    }
}
