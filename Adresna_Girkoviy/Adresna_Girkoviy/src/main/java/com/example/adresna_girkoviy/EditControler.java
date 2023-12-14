package com.example.adresna_girkoviy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditControler {

    CollectionAddressBook addressBookImpl = new CollectionAddressBook();
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOk;

    @FXML
    private TextField txtPhoneNum;

    @FXML
    private TextField txtPip;

    private Person person = new Person("", "");

    private boolean okClicked = false;

    private Stage dialogStage;

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {

        person.setPip(txtPip.getText());
        person.setPhone(txtPhoneNum.getText());
        okClicked = true;
        dialogStage.close();

    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;
        txtPip.setText(person.getPip());
        txtPhoneNum.setText(person.getPhone());
    }

    public void setAddressBookImpl(CollectionAddressBook addressBookImpl) {
        this.addressBookImpl = addressBookImpl;
    }

    public Person getPerson() {
        return person;
    }

    // Additional methods can be added here

}

