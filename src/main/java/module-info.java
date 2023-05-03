module com.example.becomemillionairegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.becomemillionairegame to javafx.fxml;
    exports com.example.becomemillionairegame;
    exports com.example.becomemillionairegame.questions_data;
    opens com.example.becomemillionairegame.questions_data to javafx.fxml;
}