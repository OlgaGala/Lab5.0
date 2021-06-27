package com.client.util;

import com.api.entity.Dragon;
import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Stack;
import java.util.TimerTask;

public class CustomTableView extends TimerTask {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    private final Stack<Dragon> storageOld;
    private final Stack<Dragon> storage;

    private final  TableView<Dragon> tableView;

    public CustomTableView(TableView<Dragon> tableView){
        this.tableView = tableView;

        this.storageOld = new Stack<>();
        this.storage = new Stack<>();

        this.createTable();
    }

    @Override
    public void run() {

        // TODO connect to the server and get all available data

//        storage.stream().forEach(x -> tableView.getItems().add(x));
//        storage.stream().forEach(x -> drawHuman(x, x.getX(), x.getY(), x.getSize()));
//        tableView.refresh();
//        storageOld = storage;
    }


    public void createTable() {

        TableColumn<Dragon, Dragon> idColumn = new TableColumn<>(messenger.getMessage("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<Dragon, Dragon> nameColumn = new TableColumn<>(messenger.getMessage("name"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Dragon, Dragon> xColumn = new TableColumn<>("X");
        xColumn.setCellValueFactory(new PropertyValueFactory<>("X"));

        TableColumn<Dragon, Dragon> yColumn = new TableColumn<>("Y");
        yColumn.setCellValueFactory(new PropertyValueFactory<>("Y"));

        TableColumn<Dragon, Dragon> ageColumn = new TableColumn<>(messenger.getMessage("age"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Dragon, Dragon> colorColumn = new TableColumn<>(messenger.getMessage("color"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Dragon, Dragon> typeColumn = new TableColumn<>(messenger.getMessage("type"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Dragon, Dragon> characterColumn = new TableColumn<>(messenger.getMessage("character"));
        characterColumn.setCellValueFactory(new PropertyValueFactory<>("character"));

        TableColumn<Dragon, Dragon> depthColumn = new TableColumn<>(messenger.getMessage("depth"));
        depthColumn.setCellValueFactory(new PropertyValueFactory<>("depth"));

        TableColumn<Dragon, Dragon> numberOfTreasureColumn = new TableColumn<>(messenger.getMessage("numberOfTreasure"));
        numberOfTreasureColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfTreasure"));

        TableColumn<Dragon, Dragon> creationDateColumn = new TableColumn<>(messenger.getMessage("creationDate"));
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));


        tableView.getColumns().addAll(
                idColumn, nameColumn, xColumn,
                yColumn, ageColumn, characterColumn,
                typeColumn, characterColumn, depthColumn,
                numberOfTreasureColumn, creationDateColumn
        );
    }

}
