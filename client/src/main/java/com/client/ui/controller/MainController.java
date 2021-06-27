package com.client.ui.controller;

import com.api.command.manager.CommandManager;
import com.api.entity.Dragon;
import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import com.client.ClientApplication;
import com.client.util.CustomTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.*;

public class MainController extends GenericController implements Initializable {

    private static final Messenger messenger = MessengerFactory.getMessenger();

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
    private TableView table;

    @FXML
    private AnchorPane pane;

    private Dragon selectedDragon;

    private Timer timer;

    public MainController() {
        super("main.fxml");
    }

    private void init() {
        nickname.setAlignment(Pos.CENTER_RIGHT);
        nickname.setText(ClientApplication.getClient().getUser().getName());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();

        this.timer = new Timer();
        this.timer.schedule(new CustomTableView(table), 0, 3000);

        this.commandsList.getItems().addAll(CommandManager.getCommandNames());

        this.table.setDisable(true);
        this.table.setOpacity(0);
        this.pane.setCursor(Cursor.MOVE);
    }


    @FXML
    void getDragon(MouseEvent event) {
//        gc2.clearRect(0, 0, 351, 380);
//
//        CustomTableView.storageOld.forEach(h -> {
//
//            if((Math.abs( -h.getY() + ((int) event.getX())) < 35*h.getSize()) && Math.abs( -h.getX() + ((int) event.getY())) < 35*h.getSize()){
//
//                gc2.beginPath();
//                gc2.save();
//                gc2.setFill(Color.BLACK);
//                gc2.setStroke(Color.BLACK);
//                gc2.scale(0.4 * h.getSize(),0.4 * h.getSize());
//                gc2.strokeRect(h.getX() + 3, h.getY(), 200, 190);
//                gc2.restore();
//                gc2.closePath();
//
//                showHumanOnPanel(h);
//
//            }
//        });
    }

    private void clearRemoteControl() {
//        humanName.clear();
//        humanAge.clear();
//        skillName.clear();
//        skillInfo.clear();
//        commandsList.setValue(null);
//        slider.setValue(slider.getMin());
//        labelSize.setText("0.00");
//        xCoordinate.clear();
//        yCoordinate.clear();
//        CustomTableView.cleanCanvas();
//        selected = null;
//        currentDate = new Date();
//        df = DateFormat.getDateInstance(DateFormat.SHORT, currentResource.getLocale());
//        date.setText(df.format(currentDate));
//        gc2.clearRect(0, 0, 351, 380);
    }

    @FXML
    void clear(ActionEvent event) {
//        clearRemoteControl();
//        table.getItems().clear();
//        CustomTableView.storage.stream().forEach(x -> table.getItems().add(x));
//        table.refresh();
    }

    @FXML
    void showTable(ActionEvent event) {
//        table.setDisable(false);
//        table.setOpacity(1);
    }

    @FXML
    void getDragonFromTable(MouseEvent event) {
//        if (!table.isDisabled()){
//            Human h = (Human) table.getSelectionModel().getSelectedItem();
//            if (h != null) {
//                showHumanOnPanel(h);
//            }
//        }
    }

    @FXML
    void hideCanvas(ActionEvent event) {
//        table.setDisable(true);
//        table.setOpacity(0);
    }

    @FXML
    void submit(ActionEvent event) {
//
//        boolean command = false;
//        boolean numberRight = true;
//
//        try {
//            String gson = "";
//
//            String commandInEnglish2 = "";
//            String key2;
//            Enumeration<String> keys2 = currentResource.getKeys();
//            while (keys2.hasMoreElements()){
//                key2 = keys2.nextElement();
//                String commandTemp = (commandsList.getValue() != null) ? commandsList.getValue() : "";
//                if (commandTemp.equals(currentResource.getString(key2))){
//                    commandInEnglish2 = key2;
//                }
//            }
//
//
//            if (commandInEnglish2.equals("filter")){
//
//                if (!table.isDisabled()){
//                    table.getItems().clear();
//                    String filterName = (humanName.getText() != null) ? humanName.getText() : "";
//                    String filterAge = (humanAge.getText() != null) ? humanAge.getText() : "";
//                    String filterXLocation = (xCoordinate.getText() != null) ? xCoordinate.getText() : "";
//                    String filterYLocation = (yCoordinate.getText() != null) ? yCoordinate.getText() : "";
//                    String filterSize = (size == 0) ? "" : String.valueOf(size);
//                    String filterSkillName = (skillName.getText() != null) ? skillName.getText() : "";
//                    String filterSkillInfo = (skillInfo.getText() != null) ? skillInfo.getText() : "";
//                    CustomTableView.storageOld.stream().filter(y ->
//                               y.getName().contains(filterName)
//                            && String.valueOf(y.getAge()).contains(filterAge)
//                            && String.valueOf(y.getX()).contains(filterXLocation)
//                            && String.valueOf(y.getY()).contains(filterYLocation)
//                            && String.valueOf(y.getSize()).contains(filterSize)
//                            && y.getSkills().get(0).getName().contains(filterSkillName)
//                            && y.getSkills().get(0).getAction().contains(filterSkillInfo))
//                            .forEach( x -> table.getItems().add(x));
//                    table.refresh();
//                    throw new Exception();
//
//                } else {
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle(Login.currentResource.getString("command"));
//                    alert.setHeaderText(Login.currentResource.getString("warning"));
//                    alert.setContentText(currentResource.getString("showTable"));
//                    alert.showAndWait();
//                    throw new Exception();
//
//                }
//            }
//
//            if (humanName.getText() == null || humanAge.getText() == null || humanName.getText().isEmpty() || humanAge.getText().isEmpty() || commandsList.getValue() == null || size == 0){
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle(Login.currentResource.getString("command"));
//                alert.setHeaderText(Login.currentResource.getString("warning"));
//                alert.setContentText(currentResource.getString("sheerVoid"));
//                alert.showAndWait();
//            } else {
//                try {
//                    if (xCoordinate.getText() != null && !xCoordinate.getText().isEmpty()){
//                        x = Integer.parseInt(xCoordinate.getText());
//                    }
//                    if (yCoordinate.getText() != null && !yCoordinate.getText().isEmpty()){
//                        y = Integer.parseInt(yCoordinate.getText());
//                    }
//
//                    Integer.parseInt(humanAge.getText());
//                } catch (NumberFormatException e){
//                    numberRight = false;
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle(Login.currentResource.getString("command"));
//                    alert.setHeaderText(Login.currentResource.getString("warning"));
//                    alert.setContentText(currentResource.getString("notANumber"));
//                    alert.showAndWait();
//                }
//
//                if ((skillName.getText() == null || skillName.getText().isEmpty()) && skillInfo.getText()!= null && !skillInfo.getText().isEmpty()) {
//                    command = false;
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setHeaderText(Login.currentResource.getString("warning"));
//                    alert.setTitle(Login.currentResource.getString("command"));
//                    alert.setContentText(currentResource.getString("skillNameMissing"));
//                    alert.showAndWait();
//                } else if ((skillName.getText() == null || skillName.getText().isEmpty()) && (skillInfo.getText() == null || skillInfo.getText().isEmpty())){
//                    gson = "{\"name\":\"" + humanName.getText() +"\", \"age\":" + humanAge.getText() + ", \"size\":" + size + ", \"x\": " + x + ", \"y\": " + y + "%s"+ "}";
//                    command = true;
//                } else if (!(skillName.getText() == null || skillName.getText().isEmpty()) && (skillInfo.getText() == null || skillInfo.getText().isEmpty())){
//                    gson = "{\"name\":\"" + humanName.getText() +"\", \"age\":" + humanAge.getText() + ", \"size\":" + size + ", \"x\": " + x + ", \"y\": "
//                            + y + ", \"skill\": {" + "\"name\": \"" + skillName.getText() + "\"}" + "%s" +"}";
//                    command = true;
//                } else {
//                    gson = "{\"name\":\"" + humanName.getText() + "\", \"age\":" + humanAge.getText() + ", \"size\":" + size + ", \"x\": " + x + ", \"y\": "
//                            + y + ", \"skill\": {" + "\"name\": \"" + skillName.getText() + "\","
//                            + "\"info\": \"" + skillInfo.getText() + "\"}"  + "%s"+ "}";
//                    command = true;
//                }
//
//                if (command && numberRight) {
//
//                    lastCommand.setText(commandsList.getValue());
//                    lastHumanName.setText(humanName.getText());
//
//                    String commandInEnglish = "";
//                    String key;
//                    Enumeration<String> keys = currentResource.getKeys();
//                    while (keys.hasMoreElements()){
//                        key = keys.nextElement();
//                        String commandTemp = commandsList.getValue();
//                        if (commandTemp.equals(currentResource.getString(key))){
//                            commandInEnglish = key;
//                        }
//                    }
//
//                    if (commandInEnglish.equals("update") || commandInEnglish.equals("remove")){
//
//                        if (selected != null){
//                            String prepareId = ", \"id\": \"" + selected.getId() + "\"";
//                            gson = String.format(gson, prepareId);
//                        } else {
//                            Alert alert = new Alert(Alert.AlertType.WARNING);
//                            alert.setTitle(Login.currentResource.getString("command"));
//                            alert.setHeaderText(Login.currentResource.getString("message"));
//                            alert.setContentText(currentResource.getString("notSelectHuman"));
//                            alert.showAndWait();
//                            throw new Exception();
//                        }
//                    } else {
//                        gson = String.format(gson, "");
//                    }
//
//                    SkeletonLogin.client.udpSocket.send(SkeletonLogin.client.createRequest(commandInEnglish, gson, SkeletonLogin.getNickname() + " " + server.DataBaseConnection.encryptString(SkeletonLogin.getPassword())));
//                    byte[] resp = new byte[8192];
//                    DatagramPacket responsePacket = new DatagramPacket(resp, resp.length);
//                    SkeletonLogin.client.udpSocket.receive(responsePacket);
//
//                    try (ByteArrayInputStream bais = new ByteArrayInputStream(resp);
//                         ObjectInputStream ois = new ObjectInputStream(bais)) {
//                        Response response = (Response) ois.readObject();
//                        String output = new String(SkeletonLogin.client.decodeResponse(commandsList.getValue(), response));
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setHeaderText(Login.currentResource.getString("warning"));
//                        alert.setTitle(Login.currentResource.getString("command"));
//                        alert.setContentText(Login.getLocaleMessageFromServer(output));
//                        alert.showAndWait();
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                }
//            }
//
//        }catch (IOException e){
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle(Login.currentResource.getString("serverConnection"));
//            alert.setContentText(Login.currentResource.getString("disconnected"));
//            alert.setHeaderText(Login.currentResource.getString("warning"));
//            alert.showAndWait();
//        } catch (Exception e){}

    }

    @FXML
    public void logOut(ActionEvent event) {
        ClientApplication.getClient().stop("User has stopped the application");
    }

    private void showHumanOnPanel(Dragon selected) {
//        this.selected = selected;
//        humanName.setText(selected.getName());
//        humanAge.setText(String.valueOf(selected.getAge()));
//        xCoordinate.setText(String.valueOf(selected.getX()));
//        yCoordinate.setText(String.valueOf(selected.getY()));
//        slider.setValue(selected.getSize());
//        if (selected.getSkills().iterator().hasNext()) {
//            Skill skill = selected.getSkills().iterator().next();
//            if (!skill.getName().toLowerCase().equals("null")) {
//                skillName.setText(skill.getName());
//                if (!skill.getAction().toLowerCase().equals("null")) {
//                    skillInfo.setText(skill.getAction());
//                } else {skillInfo.clear();}
//            } else {skillName.clear();}
//        } else {skillInfo.clear();skillName.clear();}
//
//        currentDate = new Date(selected.getDateTime().toEpochSecond() * 1000);
//        df = DateFormat.getDateInstance(DateFormat.SHORT, currentResource.getLocale());
//        date.setText(df.format(currentDate));
    }


}
