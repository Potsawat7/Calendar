package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Appointment;
import models.Events;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;


public class Controller {


    ArrayList<Appointment> arrayList;
    Events events;
//    LocalDate time;
//    String title,location,hour,minute;
//    int day,month,year,reptNum;
    public Controller(){
        arrayList = new ArrayList();
        events = new Events();

    }

    ObservableList<String> hrList = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
    ObservableList<String> minList = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12",
            "13","14","15","16","17","18","19","20","21","22","23","24","25",
            "26","27","28","29","30","31","32","33","34","35","36","37","38",
            "39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
    ObservableList<String> zoneList = FXCollections.observableArrayList("AM","PM");

    @FXML
    private ChoiceBox hrDrop;
    @FXML
    private ChoiceBox minDrop;
    @FXML
    private ChoiceBox zoneTime;
    @FXML
    private Button add;
    @FXML
    private Button clr;
    @FXML
    private DatePicker calendar;
    @FXML
    private TextArea textfield;
    @FXML
    private TextField locat;
    @FXML
    private TextField titlefield;
    @FXML
    private Button edit;
    @FXML
    private TextField editIDField,reptDayField;
    @FXML
    private RadioButton radioDay,radioMon,radioWeek;


    @FXML
    public void addApp(ActionEvent e){
        LocalDate time  = calendar.getValue();
        String title = titlefield.getText();
        int day = time.getDayOfMonth();
        int month = time.getMonthValue();
        int year = time.getYear();
        String hour = hrDrop.getSelectionModel().getSelectedItem().toString();
        String minute = minDrop.getSelectionModel().getSelectedItem().toString();
        String location = locat.getText();
        int reptNum = Integer.parseInt(reptDayField.getText());

        String str = getDate(day,month,year,hour,minute);
        creatEvent(title,str,location);

        if(!reptDayField.equals("")){
            if(radioDay.isSelected()){
                for (int i=1;i<=reptNum;i++){
//                    System.out.println(time);
//                    System.out.println(time.plusDays(reptNum));
                    LocalDate plusDay =  time.plusDays(reptNum);
                    day = plusDay.getDayOfMonth();
                    month = plusDay.getMonthValue();
                    year = plusDay.getYear();

                    str = getDate(day,month,year,hour,minute);
                    creatEvent(title,str,location);
                }
            }else if(radioMon.isSelected()){
                for (int i=1;i<=reptNum;i++){

                    LocalDate plusMon =  time.plusMonths(reptNum);
                    day = plusMon.getDayOfMonth();
                    month = plusMon.getMonthValue();
                    year = plusMon.getYear();
                    str = getDate(day,month,year,hour,minute);
                    creatEvent(title,str,location);
                }
            }else if(radioWeek.isSelected()){
                for (int i=1;i<=reptNum;i++){
                    LocalDate plusWeek = time.plusWeeks(reptNum);
                    day = plusWeek.getDayOfMonth();
                    month = plusWeek.getMonthValue();
                    year = plusWeek.getYear();
                    str = getDate(day,month,year,hour,minute);
                    creatEvent(title,str,location);
                }
            }

        }
        showAppoint();
    }
    @FXML
    public void clearAction(ActionEvent event){
        clear();
    }
    @FXML
    public void editButton(ActionEvent event) {
        showEditScene();

    }
    private String getDate(int day,int month, int year,String hour ,String minute)   {

        String datTime="";
        String Date = day+"/"+month+"/"+year+" ";
        String time = hour+" : "+minute
                +" "+zoneTime.getSelectionModel().getSelectedItem().toString();
        datTime = Date+time;
        return datTime;
    }

    private void showEditScene() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/editPanel.fxml"));
        if(editIDField.getText() != ""){
        try {
            stage.initOwner(edit.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Appointment list");

            EditController editController = loader.getController();
            editController.id = Integer.parseInt(editIDField.getText());
            editController.setEditByID();

            stage.showAndWait();
            showAppoint();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


    private void clear() {
        titlefield.clear();
        locat.clear();
        textfield.clear();
        events.event.clear();
        events.show="";
        try {
            // setup
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:appointment.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                String query = "DELETE from Appointments";
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);

                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void initialize(){
        add.setOnAction(this::addApp);
        clr.setOnAction(this::clearAction);
        edit.setOnAction(this::editButton);
        hrDrop.setValue("1");
        hrDrop.setItems(hrList);
        minDrop.setValue("00");
        minDrop.setItems(minList);
        zoneTime.setValue("AM");
        zoneTime.setItems(zoneList);
    }

    private void creatEvent(String title,String date,String location) {
        try {
            // setup

            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:appointment.db";
            Connection conn = DriverManager.getConnection(dbURL);

//            System.out.println(getDate());
            Appointment appointmentObj = new Appointment(title,date, location);

            events.addApp(appointmentObj);


            if (conn != null) {

//          set appoint into database
                System.out.println(date);

                String query = "insert into Appointments(title,date,location) values (\'"+title+"\',\'"+date+"\',\'"+location+"\')";
//                PreparedStatement statement = conn.prepareStatement(query);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);

                statement.close();

            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }



    public void showAppoint(){
        try {
            // setup
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:appointment.db";
            Connection conn = DriverManager.getConnection(dbURL);
            String show ="";
            int i = 0;
            if (conn != null) {
                String query = "select * from Appointments";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){

                    String ti = resultSet.getString(2);
                    String dat = resultSet.getString(3);
                    String loc = resultSet.getString(4);
                    String idToEdit = resultSet.getString(1);
                    String s = "";

                    s = (i+1)+".  " + "Title: " + ti + "\n"
                            +"   " + "Date: " + dat + "\n"
                            +"   " + "Location: "  + loc +"\n"
                            +"   "+ "ID Event to edit: "+ idToEdit+"\n";
                    show+=s;
                    i = i + 1;
                    textfield.setText(show);
                    Appointment obj = new Appointment(ti,dat,loc);

                    arrayList.add(obj);

                }
            events.event = arrayList;
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



}
