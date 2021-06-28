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

@Getter @Setter
public class GenericController {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    @FXML
    private TextField nameField;

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

    protected void hideWindow() {
        Window stageP = nameField.getScene().getWindow();
        stageP.hide();
    }
}
