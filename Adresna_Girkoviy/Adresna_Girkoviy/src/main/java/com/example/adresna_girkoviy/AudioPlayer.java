package com.example.adresna_girkoviy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AudioPlayer implements Initializable {
    @FXML
    private Slider volume;

    @FXML
    private ProgressBar playbackProgress;

    @FXML
    private Label currentAudioLabel;

    private File directory;
    private File[] audioFiles;
    private Media mediaFile;
    private MediaPlayer audioPlayer;
    private ArrayList<File> audioTracks;
    private int trackIndex;
    private Stage applicationStage;
    private double currentPlaybackProgress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        directory = new File("C:\\Users\\Admin\\IdeaProjects\\Adresna_Girkoviy\\music");
        audioFiles = directory.listFiles();

        audioTracks = new ArrayList<>();

        if (audioFiles != null) {
            for (File file : audioFiles) {
                audioTracks.add(file);
                System.out.println(file);
            }
        }

        mediaFile = new Media(audioTracks.get(trackIndex).toURI().toString());
        audioPlayer = new MediaPlayer(mediaFile);

        volume.valueProperty().addListener((observable, oldValue, newValue) -> {
            audioPlayer.setVolume(volume.getValue() * 0.01);
        });

        updatePlaybackProgress();
    }

    private void updatePlaybackProgress() {
        audioPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                double progress = newValue.toSeconds() / mediaFile.getDuration().toSeconds();
                playbackProgress.setProgress(progress + currentPlaybackProgress);
            }
        });
    }

    @FXML
    void play(ActionEvent event) {
        audioPlayer.play();
        currentAudioLabel.setText(audioTracks.get(trackIndex).getName());
    }

    @FXML
    void pause(ActionEvent event) {
        audioPlayer.pause();
        currentAudioLabel.setText(audioTracks.get(trackIndex).getName());
    }

    @FXML
    void next(ActionEvent event) {
        if (trackIndex < audioTracks.size() - 1) {
            trackIndex++;
        } else {
            trackIndex = 0;
        }
        audioPlayer.stop();

        mediaFile = new Media(audioTracks.get(trackIndex).toURI().toString());
        audioPlayer = new MediaPlayer(mediaFile);

        currentAudioLabel.setText(audioTracks.get(trackIndex).getName());
        currentPlaybackProgress = 0.0;
        updatePlaybackProgress();

        audioPlayer.play();
    }

    @FXML
    void previous(ActionEvent event) {
        if (trackIndex > 0) {
            trackIndex--;
        } else {
            trackIndex = audioTracks.size() - 1;
        }
        audioPlayer.stop();

        mediaFile = new Media(audioTracks.get(trackIndex).toURI().toString());
        audioPlayer = new MediaPlayer(mediaFile);

        currentAudioLabel.setText(audioTracks.get(trackIndex).getName());
        currentPlaybackProgress = 0.0;
        updatePlaybackProgress();

        audioPlayer.play();
    }

    public void setApplicationStage(Stage applicationStage) {
        this.applicationStage = applicationStage;
    }
}
