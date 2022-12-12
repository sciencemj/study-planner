package com.sciencemj.wordbook.controller;

import eu.hansolo.tilesfx.addons.YearChart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.time.*;

public class PlannerController implements Initializable {
    /*@FXML
    private AnchorPane text_button;*/
    @FXML
    private VBox today_box;
    @FXML
    private VBox yesterday_box;
    @FXML
    private VBox tomorrow_box;
    @FXML
    private AnchorPane today_buttons;
    @FXML
    private AnchorPane yesterday_buttons;
    @FXML
    private AnchorPane tomorrow_buttons;

    private static LocalDate today;
    private static LocalDate yesterday;
    private static LocalDate tomorrow;
    private static HashMap<LocalDate, VBox> dates = new HashMap<>();
    private static HashMap<VBox, ArrayList<TextField>> plans = new HashMap<>();
    private HashMap<TextField, CheckBox> planscheck = new HashMap<>();


    @FXML
    public void addButtonToday(){
        addButton(today_box, "", false);
    }
    @FXML
    public void addButtonYesterday(){
        addButton(yesterday_box, "", false);
    }
    @FXML
    public void addButtonTomorrow(){
        addButton(tomorrow_box, "", false);
    }

    public void addButton(VBox vbox, String msg, boolean check){
        TextField txt = new TextField(msg);
        txt.setPrefSize(235, 26);
        txt.setOnAction(event -> writePlan());

        CheckBox box = new CheckBox();
        box.setSelected(check);
        box.setLayoutX(240);
        box.setLayoutY(4);
        box.setOnAction(event -> writePlan());


        AnchorPane pane = new AnchorPane(txt, box);
        if(vbox != null) {
            vbox.getChildren().add(pane);
            if (plans.containsKey(vbox)) {
                plans.get(vbox).add(txt);
                planscheck.put(txt, box);
            }else {
                plans.put(vbox, new ArrayList<>());
                plans.get(vbox).add(txt);
                planscheck.put(txt, box);
            }
            AnchorPane buttons = (AnchorPane) vbox.getChildren().get(vbox.getChildren().size() - 2);
            vbox.getChildren().remove(buttons);
            vbox.getChildren().add(buttons);

            System.out.println("[ALERT] pane added");
        }else {
            System.out.println("[ALERT] vbox is null");
        }
    }



    @FXML
    public void removeButtonToday(){
        int size = today_box.getChildren().size();
        today_box.getChildren().remove(size - 2);
    }
    @FXML
    public void removeButtonYesterday(){
        int size = yesterday_box.getChildren().size();
        yesterday_box.getChildren().remove(size - 2);
    }
    @FXML
    public void removeButtonTomorrow(){
        int size = tomorrow_box.getChildren().size();
        tomorrow_box.getChildren().remove(size - 2);
    }

    public void writePlan(){
        try {
            FileWriter fw = new FileWriter("Plan.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            //yesterday plan ------------------------
            bw.write(yesterday.toString());
            bw.newLine();
            if (plans.containsKey(yesterday_box)) {
                for (TextField x : plans.get(yesterday_box)) {
                    bw.write(x.getText());
                    bw.newLine();
                    bw.write(String.valueOf(planscheck.get(x).isSelected()));
                    bw.newLine();
                }
            }
            bw.write(yesterday.toString() + "//");
            bw.newLine();
            // today plan ---------------------------
            bw.write(today.toString());
            bw.newLine();
            if (plans.containsKey(today_box)) {
                for (TextField x : plans.get(today_box)) {
                    bw.write(x.getText());
                    bw.newLine();
                    bw.write(String.valueOf(planscheck.get(x).isSelected()));
                    bw.newLine();
                }
            }
            bw.write(today.toString() + "//");
            bw.newLine();
            // tomorrow plan ------------------------
            bw.write(tomorrow.toString());
            bw.newLine();
            if (plans.containsKey(tomorrow_box)) {
                for (TextField x : plans.get(tomorrow_box)) {
                    bw.write(x.getText());
                    bw.newLine();
                    bw.write(String.valueOf(planscheck.get(x).isSelected()));
                    bw.newLine();
                }
            }
            bw.write(tomorrow.toString() + "//");
            // --------------------------------------
            bw.flush();
            System.out.println("[ALERT] plan saved");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readPlan(){
        File file = new File("Plan.txt");
        if (file.exists()) {
            for (LocalDate date : dates.keySet()) {
                try {
                    FileReader fr = new FileReader("Plan.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String read = br.readLine();
                    long length = 100;
                    while (!Objects.equals(read, date.toString())) {
                        read = br.readLine();
                        length = length - 1;
                        if(length < 0){
                            break;
                        }
                    }
                    if(read != null) {
                        if (read.equals(date.toString())) {
                            while (!read.equals(date.toString() + "//")) {
                                read = br.readLine();
                                if (read.equals(date.toString() + "//"))
                                    break;
                                boolean check = Boolean.parseBoolean(br.readLine());
                                addButton(dates.get(date), read, check);
                            }
                        }
                    }
                    System.out.println("[ALERT] read plan");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else {
            writePlan();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        today = LocalDate.now();
        yesterday = LocalDate.now().minusDays(1);
        tomorrow = LocalDate.now().plusDays(1);
        dates.put(today, today_box);
        dates.put(yesterday, yesterday_box);
        dates.put(tomorrow, tomorrow_box);
        System.out.println("[ALERT] today is " + today.toString());
        //today_plans.add((TextField) text_button.getChildren().get(0));
        readPlan();
    }
}
