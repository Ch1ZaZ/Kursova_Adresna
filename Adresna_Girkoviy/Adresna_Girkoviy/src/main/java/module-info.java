module com.example.adresna_girkoviy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires MaterialFX;

    opens com.example.adresna_girkoviy to javafx.fxml;
    exports com.example.adresna_girkoviy;
}