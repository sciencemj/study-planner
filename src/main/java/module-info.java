module com.sciencemj.wordbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.sciencemj.wordbook to javafx.fxml;
    exports com.sciencemj.wordbook;
    exports com.sciencemj.wordbook.controller;
    opens com.sciencemj.wordbook.controller to javafx.fxml;
}