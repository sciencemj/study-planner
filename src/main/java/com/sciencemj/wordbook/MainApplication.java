package com.sciencemj.wordbook;

import com.sciencemj.wordbook.controller.PlannerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL fmlPath = new File("src/main/resources/com.sciencemj.wordbook/main-view.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(fmlPath);
        Scene scene = new Scene(fxmlLoader.load(), 900, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}