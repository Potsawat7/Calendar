package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Appointment;
import models.Events;

import java.time.LocalDate;



public class Controller {


    Events events;
    public Controller(){
        events = new Events();
    }

    ObservableList<String> hrList = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
    ObservableList<String> minList = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12",
            "13","14","15","16","17","18","19","20","21","22","23","24","25",
            "26","27","28","29","30","31","32","33","34","35","36","37","38",
            "39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
    ObservableList<String> zoneList = FXCollections.observableArrayList("AM","PM");

    @FXML
    public ChoiceBox hrDrop;
    @FXML
    public ChoiceBox minDrop;
    @FXML
    public ChoiceBox zoneTime;
    @FXML
    private Button add;
    @FXML
    private Button clr;
    @FXML
    public DatePicker calendar;
    @FXML
    private TextArea textfield;
    @FXML
    public TextField locat;
    @FXML
    public TextField titlefield;



    @FXML
    public void addApp(ActionEvent e){
        creatEvent();
        textfield.setText(events.showEvent());
    }
    @FXML
    public void clearAction(ActionEvent event){
        clear();
    }

    private void clear() {
        titlefield.clear();
        locat.clear();
        textfield.clear();
        events.event.clear();
        events.show="";
    }

    @FXML
    private void initialize(){
        add.setOnAction(this::addApp);
        clr.setOnAction(this::clearAction);
        hrDrop.setValue("1");
        hrDrop.setItems(hrList);
        minDrop.setValue("00");
        minDrop.setItems(minList);
        zoneTime.setValue("AM");
        zoneTime.setItems(zoneList);
    }

    private void creatEvent() {

        Appointment appointmentObj = new Appointment(titlefield.getText(),calendar.getValue().getDayOfMonth(),calendar.getValue().getMonth(),calendar.getValue().getYear(),
                hrDrop.getSelectionModel().getSelectedItem().toString(),minDrop.getSelectionModel().getSelectedItem().toString(),zoneTime.getSelectionModel().getSelectedItem().toString(),
                locat.getText());
      //  System.out.println(appointmentObj);
        events.addApp(appointmentObj);
    }





}
