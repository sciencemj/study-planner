package com.sciencemj.wordbook.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label welcomeText;

    @FXML
    private TextField txtfield;
    @FXML
    private Box testBox;
    @FXML
    private GridPane TimeTable;

    @FXML
    private ArrayList<Label> labels = new ArrayList<>();
    @FXML
    protected void onHelloButtonClick() {

    }

    public void switchScene(ActionEvent e) throws IOException {
        URL fmlPath = new File("src/main/resources/com.sciencemj.wordbook/test-view.fxml").toURL();
        Parent root = FXMLLoader.load(fmlPath);
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchPlanner(ActionEvent e) throws IOException {
        URL fmlPath = new File("src/main/resources/com.sciencemj.wordbook/planner-view.fxml").toURL();
        Parent root = FXMLLoader.load(fmlPath);
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 0;i < 35;i++){
            labels.add(new Label("TEXT" + i));
        }
        readTimeTable();
        for(int i = 0;i < labels.size();i++) {
            if(i%7 != 0) {
                TimeTable.add(labels.get(i), i / 7, i % 7);
            } else {
                TimeTable.add(labels.get(i), i / 7, 0);
            }
            GridPane.setHalignment(TimeTable.getChildren().get(i), HPos.CENTER);
        }
        TimeTable.setGridLinesVisible(true);
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int today = dayOfWeek.getValue();
    }

    public void writeTimeTable(){
        try{
            FileWriter fw = new FileWriter("TimeTable.txt");
            BufferedWriter bf = new BufferedWriter(fw);
            for(Label x : labels){
                bf.write(x.getText());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void readTimeTable(){
        try {
            FileReader fr = new FileReader("TimeTable.txt");
            BufferedReader br = new BufferedReader(fr);
            for(Label x : labels){
                x.setText(br.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}