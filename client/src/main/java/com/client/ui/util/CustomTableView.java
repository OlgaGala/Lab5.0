package com.client.ui.util;

import com.api.entity.Dragon;
import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import com.api.message.MessageReq;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;
import java.util.TimerTask;

import static com.client.Application.getClient;

@Getter @Setter
public class CustomTableView extends TimerTask {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    private Stack<Dragon> storage;

    private final  TableView<Dragon> tableView;

    private final GraphicsContext gc;

    public CustomTableView(TableView<Dragon> tableView, GraphicsContext gc) {
        this.tableView = tableView;
        this.gc = gc;
        this.storage = new Stack<>();

        // Create and fill the table
        createTable();
    }

    @Override
    public void run() {

        // Connect to server and get all available entities
        storage = (Stack<Dragon>) getClient().sendRequest(new MessageReq(getClient().getUser(),"show")).getResult();
    }


    public void createTable() {

        TableColumn<Dragon, Dragon> idColumn = new TableColumn<>(messenger.getMessage("id"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));


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
                yColumn, ageColumn, colorColumn,
                typeColumn, characterColumn, depthColumn,
                numberOfTreasureColumn, creationDateColumn
        );
    }

    public void drawDragon(Dragon dragon) {

        double xC = dragon.getCoordinates().getX();
        int yC = dragon.getCoordinates().getY();

        gc.beginPath();
        gc.save();
        gc.strokeArc(20 + xC, 85 + yC, 20, 40, 70, 280, ArcType.OPEN);
        gc.strokeArc(170 + xC, 85 + yC, 20, 42, 80, 360, ArcType.OPEN);
        gc.fillOval(30 + xC,35 + yC,150,150);
        gc.fillOval(20 + xC, 85 + yC,20, 40 );
        gc.fillOval(170 + xC, 85 + yC,20, 42 );
        gc.strokeOval(30 + xC,35 + yC,150,150);
        gc.setFill(Color.BLACK);
        gc.fillOval(60 + xC,80 + yC,15,25);
        gc.fillOval(130 + xC,80 + yC,15,25);
        gc.setFill(Color.SADDLEBROWN);
        gc.strokeOval(5 + xC, 25 + yC, 195, 62);
        gc.fillOval(5 + xC, 25 + yC, 195, 62);
        gc.setFill(Color.BLACK);
        gc.strokeArc(73 + xC, 35 + yC, 60, 30, 180, 180, ArcType.OPEN);
        gc.strokeArc(73 + xC, yC, 60, 100, 0, 180, ArcType.OPEN);
        gc.setFill(Color.SADDLEBROWN);
        gc.fillArc(73 + xC, yC, 60, 100, 0, 180, ArcType.OPEN);
        gc.setFill(Color.BLACK);
        double[] x = {104 + xC,94 + xC,104 + xC,104 + xC};
        double[] y = {105 + (double) yC,124 + (double) yC,124 + (double) yC,105 + (double) yC};
        gc.fillPolygon(x , y, 4);           // Nose
        gc.arc(105 + xC,139 + yC,37,22,0,-180);

        gc.setFill(Color.web("#e35d6a"));
        gc.fill();
        gc.setFill(Color.WHITE);
        gc.fillRect(68.5 + xC,138 + yC,73,8);
        gc.strokeArc(67.5 + xC,116 + yC,75,45,0,-180,ArcType.ROUND);
        gc.restore();
        gc.closePath();

    }

    public void refreshTable() {
        // Clear canvas
        gc.clearRect(0, 0, 351, 380);

        // Fill canvas with new dragons
        storage.forEach(this::drawDragon);

        // Fill the table with new dragon entities
        storage.forEach(x -> tableView.getItems().add(x));

        tableView.refresh();
    }

}
