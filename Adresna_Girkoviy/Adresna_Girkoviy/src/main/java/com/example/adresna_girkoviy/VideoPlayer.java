package com.example.adresna_girkoviy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VideoPlayer implements Initializable {
    @FXML
    private Button pauseButton;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button playButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button audioPlayerButton;

    private Stage newStage;
    private File videoFile;
    private Media videoMedia;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        videoFile = new File("mountains_-_59291 (1080p).mp4");

        try {
            videoMedia = new Media(videoFile.toURI().toURL().toString());
            mediaPlayer = new MediaPlayer(videoMedia);
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    @FXML
    void playButton_method(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    void pauseButton_method(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void resetButton_method(ActionEvent event) {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
            mediaPlayer.play();
        }
    }

    @FXML
    public void audioPlayer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AudioPlayer.fxml"));
            Parent root = loader.load();
            AudioPlayer audioPlayer = loader.getController();

            Stage otherDialogStage = new Stage();
            otherDialogStage.setTitle("Практична робота №7");
            otherDialogStage.initModality(Modality.WINDOW_MODAL);
            otherDialogStage.initOwner(audioPlayerButton.getScene().getWindow());
            Scene scene = new Scene(root);
            otherDialogStage.setScene(scene);

            audioPlayer.setApplicationStage(otherDialogStage);

            otherDialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    public void setNewStage(Stage newStage) {
        this.newStage = newStage;
        // Add event handler to stop the media player and release resources when the stage is closed
        this.newStage.setOnCloseRequest(windowEvent -> {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        });
    }
}
