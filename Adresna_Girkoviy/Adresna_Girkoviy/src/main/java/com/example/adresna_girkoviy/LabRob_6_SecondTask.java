package com.example.adresna_girkoviy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class LabRob_6_SecondTask {
    private Stage newStage;

    private LabRob6 mainController;

    @FXML
    private TextField textField;

    @FXML
    private RadioMenuItem randomTextRadio;

    @FXML
    private RadioMenuItem clearTextRadio;

    @FXML
    private ToggleGroup toggleContextText;

    @FXML
    void toggleContextText(ActionEvent event) {
        if (toggleContextText.getSelectedToggle().equals(randomTextRadio))
            setRandomText();
        else if (toggleContextText.getSelectedToggle().equals(clearTextRadio))
            clearText();
    }

    @FXML
    void exitButton(ActionEvent event) {
        closeStage();
    }

    public void setNewStage(Stage newStage) {
        this.newStage = newStage;
    }

    public void setMainController(LabRob6 mainController) {
        this.mainController = mainController;
    }

    private void setRandomText() {
        textField.setText("RandomText");
    }

    private void clearText() {
        textField.setText(null);
    }

    private void closeStage() {
        newStage.close();
    }
}
