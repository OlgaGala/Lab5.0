//package com.client.controller;
//
//
//import com.api.entity.Color;
//import com.api.entity.Dragon;
//import com.api.entity.DragonCharacter;
//import com.api.entity.DragonType;
//import com.api.i18n.Messenger;
//import com.api.i18n.MessengerFactory;
//import javafx.collections.FXCollections;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.text.Text;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//public class AddController {
//
//    private static final Messenger messenger = MessengerFactory.getMessenger();
//
//    @FXML
//    private Text addText;
//
//    @FXML
//    private TextField nameField;
//
//    @FXML
//    private TextField xField;
//
//    @FXML
//    private TextField yField;
//
//    @FXML
//    private TextField ageField;
//
//    @FXML
//    private ChoiceBox<String> colorBox;
//
//    @FXML
//    private ChoiceBox<String> typeBox;
//
//    @FXML
//    private ChoiceBox<String> characterBox;
//
//    @FXML
//    private TextField depthField;
//
//    @FXML
//    private TextField numberOfTreasuresField;
//
//    @FXML
//    private Button addButton;
//
//    @FXML
//    void initialize() {
//        nameField.setPromptText(messenger.getMessage("Enter a name"));
//        xField.setPromptText(messenger.getMessage("Enter a name"));
//        yField.setPromptText(messenger.getMessage("Enter a name"));
//        ageField.setPromptText(messenger.getMessage("Enter a name"));
//
//        colorBox.setItems(FXCollections.observableArrayList(Arrays.toString(Color.values())));
//        colorBox.setValue("RED");
//
//        typeBox.setItems(FXCollections.observableArrayList(Arrays.toString(DragonType.values())));
//        typeBox.setValue("WATER");
//
//        characterBox.setItems(FXCollections.observableArrayList(Arrays.toString(DragonCharacter.values())));
//        characterBox.setValue("CHAOTIC");
//
//
//        addText.setText(messenger.getMessage("add"));
//
//
//        ReceiveController.addController = this;
//        addButton.setOnAction(actionEvent -> {
//            String name = nameField.getText();
//            String x = xField.getText();
//            String y = yField.getText();
//
//
//            try {
//                rece
//            } catch (IOException e) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle(messanger.getString("Oops!"));
//                alert.setHeaderText(resourceBundle.getString("Please make sure that you entered everything correctly")+"!");
//                alert.showAndWait();
//            }
//        });
//    }
//
//    public Dragon createDragon() {
//        return new Dragon(); // TODO
//
//    }
//}
