package com.client;

import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Главный класс, наследующийся от javafx Application.
 * Здесь происходит начальная инициализация клиента, и загрузка окон со сценами.
 */
public class Application extends javafx.application.Application {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    @Getter(AccessLevel.PUBLIC)
    private static Client client;

    @Override
    public void init() {
        client = new Client();
    }

    @Override
    public void start(Stage stage) {
        loadSceneWithLanguage(stage, messenger.getBundle().getLocale(), "auth.fxml");
    }

    @SneakyThrows
    public static void loadSceneWithLanguage(Stage stage, Locale locale, String viewName) {

        // Change language
        MessengerFactory.changeLanguage(
                ResourceBundle.getBundle("com.api.i18n.bundle.Language", locale)
        );

        FXMLLoader loader = new FXMLLoader();
        loader.setResources(messenger.getBundle());
        loader.setLocation(Application.class.getClassLoader().getResource(viewName));

        Parent root = loader.load();
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    public static void main(String[] args) {
        javafx.application.Application.launch(args);
    }
}