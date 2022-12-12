package com.sciencemj.wordbook.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TestController implements Initializable {
    @FXML
    private GridPane table;

    @FXML
    private ArrayList<TextField> tfs = new ArrayList<TextField>();

    @FXML
    public void saveTable(){
        try{
            FileWriter fw = new FileWriter("TimeTable.txt");
            BufferedWriter bf = new BufferedWriter(fw);
            for(TextField x : tfs){
                bf.write(x.getText());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void readTable() {
        try {
            FileReader fr = new FileReader("TimeTable.txt");
            BufferedReader br = new BufferedReader(fr);
            for(TextField x : tfs){
                x.setText(br.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 0; i < 35;i++){
            tfs.add(new TextField("TEXT"));
        }
        readTable();
        for(int i = 0; i < 35;i++){
            if(i%7 != 0) {
                table.add(tfs.get(i), i / 7, i % 7);
            } else {
                table.add(tfs.get(i), i / 7, 0);
            }
            GridPane.setHalignment(table.getChildren().get(i), HPos.CENTER);
        }
    }
}
