package com.client.ui.controller;

import com.api.command.manager.CommandManager;
import com.api.entity.Color;
import com.api.entity.Dragon;
import com.api.entity.DragonCharacter;
import com.api.entity.DragonType;
import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import com.client.Application;
import com.client.ui.util.CustomTableView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.*;

/**
 * Главная страница по работе с коллекцией. Содержит таблицу объектов Dragon, а также панель управления коллекцией
 */
public class MainController extends GenericController implements Initializable {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    /**
     * FXML компоненты, каждый компонент вводится юзером и передается сюда,
     * где из них достается введенная информация
     */

    @FXML
    private Label nickname;

    @FXML
    private ComboBox<String> commandsList;

    @FXML
    private TextField name;

    @FXML
    private TextField age;

    @FXML
    private TextField xCoordinate;

    @FXML
    private TextField yCoordinate;

    @FXML
    private ChoiceBox<String> color;

    @FXML
    private ChoiceBox<String> type;

    @FXML
    private ChoiceBox<String> character;

    @FXML
    private TextField depth;

    @FXML
    private TextField numberOfTreasure;

    @FXML
    private TableView<Dragon> table;

    @FXML
    private AnchorPane pane;

    @FXML
    private Canvas canvasForSelection;

    @FXML
    private Canvas canvas;

    /**
     * Текущий дракон, нужен для заполнения панели управления
     */
    private Dragon selectedDragon;

    /**
     * Объект для работы с TableView
     */
    private CustomTableView customTableView;

    private Timer timer;

    public MainController() {
        super("main.fxml");
    }

    private void init() {
        nickname.setAlignment(Pos.CENTER_RIGHT);
        nickname.setText(Application.getClient().getUser().getName());

        color.setItems(FXCollections.observableArrayList("RED","BLACK","BLUE","ORANGE"));
        color.setValue(Color.RED.name());

        type.setItems(FXCollections.observableArrayList("WATER","AIR","FIRE"));
        color.setValue(DragonType.WATER.name());

        character.setItems(FXCollections.observableArrayList("CUNNING","CHAOTIC","FICKLE"));
        color.setValue(DragonCharacter.CHAOTIC.name());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();

        this.timer = new Timer();

        this.customTableView = new CustomTableView(table, canvas.getGraphicsContext2D());
        this.timer.schedule(customTableView, 0, 3000);

        this.commandsList.getItems().addAll(CommandManager.getCommandNames());

        this.table.setDisable(true);
        this.table.setOpacity(0);
        this.pane.setCursor(Cursor.MOVE);
    }

    @FXML
    public void clear(ActionEvent event) {
        clearPanel();
        table.getItems().clear();
        customTableView.getStorage().forEach(x -> table.getItems().add(x));
        table.refresh();
    }

    @FXML
    public void showTable(ActionEvent event) {
        table.setDisable(false);
        table.setOpacity(1);
    }

    @FXML
    public void hideCanvas(ActionEvent event) {
        table.setDisable(true);
        table.setOpacity(0);
    }

    @FXML
    public void getDragonFromTable(MouseEvent event) {
        if (!table.isDisabled()){
            Dragon h = table.getSelectionModel().getSelectedItem();
            if (h != null) {
                showDragonOnPanel(h);
            }
        }
    }

    @FXML
    public void submit(ActionEvent event) {
        try {
            // TODO get chosen command from command list choice box
        } catch (Exception e) {
            showWarning(messenger.getMessage("submit_warning"));
        }
    }

    @FXML
    public void logOut(ActionEvent event) {
        Application.getClient().stop("User has stopped the application");
    }

    /**
     * Заполняет поля ввода значениями полей выбранного объекта из таблицы
     * @param selected - выбранный объект
     */
    private void showDragonOnPanel(Dragon selected) {
        selectedDragon = selected;

        name.setText(selected.getName());
        xCoordinate.setText(String.valueOf(selected.getCoordinates().getX()));
        yCoordinate.setText(String.valueOf(selected.getCoordinates().getY()));


        // TODO continue
    }

    /**
     * Очищает поля ввода от ранее введенных значений
     */
    private void clearPanel() {
        name.clear();
        age.clear();
        color.setValue(Color.RED.name());
        type.setValue(DragonType.WATER.name());
        character.setValue(DragonCharacter.CHAOTIC.name());
        xCoordinate.clear();
        yCoordinate.clear();

        customTableView.refreshTable();

        selectedDragon = null;
    }
}
