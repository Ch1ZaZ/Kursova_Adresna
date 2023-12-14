package com.example.adresna_girkoviy;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private CollectionAddressBook addressBook = new CollectionAddressBook();

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button searchButton;

    @FXML
    private Label countLabel;

    @FXML
    private Button exitButton;

    @FXML
    private TableView<Person> addressBookTable;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, String> phoneColumn;

    @FXML
    private Button otherLabsButton;

    private Stage newStage;

    private Stage editDialogStage;

    private Parent root;

    private EditControler editController = new EditControler();

    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("pip"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        addressBook.getPersonList().addListener((ListChangeListener<Person>) c -> updateCountLabel());

        addressBook.fillTestData();
        addressBookTable.setItems(addressBook.getPersonList());
        addButton.setOnAction(actionEvent -> onAddButtonClick());
    }

    private void updateCountLabel() {
        countLabel.setText("Кількість записів: " + addressBook.getPersonList().size());
    }

    @FXML
    public void onEditButtonClick() {
        Person selectedPerson = addressBookTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit.fxml"));
                Parent root = loader.load();
                EditControler editController = loader.getController();
                editController.setPerson(selectedPerson);

                Stage editDialogStage = new Stage();
                editDialogStage.setTitle("Редагування запису");
                editDialogStage.initModality(Modality.WINDOW_MODAL);
                editDialogStage.initOwner(editButton.getScene().getWindow());
                Scene scene = new Scene(root);
                editDialogStage.setScene(scene);

                editController.setDialogStage(editDialogStage);

                editDialogStage.showAndWait();

                if (editController.isOkClicked()) {
                    addressBook.update(selectedPerson);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onAddButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit.fxml"));
            Parent root = loader.load();
            EditControler editControler = loader.getController();




            editControler.setAddressBookImpl(addressBook);

            Stage newStage = new Stage();
            newStage.setTitle("Додавання запису");
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(addButton.getScene().getWindow());
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            editControler.setDialogStage(newStage);

            newStage.showAndWait();

            if (editControler.isOkClicked()) {
                Person newPerson = editControler.getPerson();
                if (newPerson != null) {
                    addressBook.add(newPerson);
                    // Оновлюю таблицю
                    updateTable();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    public void exitApplication() {
        Platform.exit();
    }

    @FXML
    public void onDeleteButtonClick() {
        Person selectedPerson = addressBookTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Видалення");
            alert.setContentText("Ви впевнені, що хочете видалити запис?");
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
            if (result == ButtonType.OK) {
                addressBook.delete(selectedPerson);
            }
        }
    }

    @FXML
    public void onSearchButtonClick() {
        String searchTerm = searchTextField.getText().toLowerCase();
        ObservableList<Person> searchResults = addressBook.search(searchTerm);
        addressBookTable.setItems(searchResults);
    }

    public void updateTable() {
        Platform.runLater(() -> {
            addressBookTable.setItems(addressBook.getPersonList());
        });
    }

    @FXML
    public void nextLab() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Lab_rob6.fxml"));
            Parent root = loader.load();
            LabRob6 labRob6 = loader.getController();

            Stage otherDialogStage = new Stage();
            otherDialogStage.setTitle("Практична робота №6");
            otherDialogStage.initModality(Modality.WINDOW_MODAL);
            otherDialogStage.initOwner(otherLabsButton.getScene().getWindow());
            otherDialogStage.setMinHeight(480);
            otherDialogStage.setMinWidth(855);
            otherDialogStage.setMaxHeight(480);
            otherDialogStage.setMaxWidth(855);
            Scene scene = new Scene(root);
            otherDialogStage.setScene(scene);

            labRob6.setDialogStage(otherDialogStage);

            otherDialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}