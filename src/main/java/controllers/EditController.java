package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import models.Appointment;
import models.Events;


import java.sql.*;

public class EditController {

    @FXML
    private TextField editTitleField,editLocField;
    @FXML
    private Button updateBtn;
    @FXML
    private Label oldEventLabel;
    @FXML
    private DatePicker editDateField ;
    @FXML
    private ChoiceBox editHrDrop,editMnDrop,editMeridDrop;

    String newDate="";

    String show ="";
    public int id;

    ObservableList<String> hrList = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
    ObservableList<String> minList = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12",
            "13","14","15","16","17","18","19","20","21","22","23","24","25",
            "26","27","28","29","30","31","32","33","34","35","36","37","38",
            "39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
    ObservableList<String> zoneList = FXCollections.observableArrayList("AM","PM");



    @FXML
    private void initialize(){
        editHrDrop.setValue("1");
        editHrDrop.setItems(hrList);
        editMnDrop.setValue("00");
        editMnDrop.setItems(minList);
        editMeridDrop.setValue("AM");
        editMeridDrop.setItems(zoneList);

    }



    public void setEditByID() {

        try {
            // setup
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:appointment.db";
            Connection conn = DriverManager.getConnection(dbURL);


            if (conn != null) {

                String query = "select * from Appointments where "+"ID="+id ;
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {

                    String ti = resultSet.getString(2);
                    String dat = resultSet.getString(3);
                    String loc = resultSet.getString(4);
                    System.out.println(ti);
                    String s = "";

                    s = "Title: " + ti + "\n" + "Date: " + dat + "\n" + "Location: " + loc + "\n";
                    show += s;

                    oldEventLabel.setText(show);

                }

                conn.close();

            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private String getDate()   {
        String Date = editDateField.getValue().getDayOfMonth()+" "+editDateField.getValue().getMonth()+" "+editDateField.getValue().getYear()+" ";
        String time = editHrDrop.getSelectionModel().getSelectedItem().toString()+" : "+editMnDrop.getSelectionModel().getSelectedItem().toString()
                +" "+editMeridDrop.getSelectionModel().getSelectedItem().toString();
        newDate = '"'+Date+time+'"';
        return newDate;
    }

    @FXML
    public void updateAction() {

        System.out.println(this.id);
        updateNewEvent();

        updateBtn.getScene().getWindow().hide();
    }

    private void updateNewEvent(){


        try {
            // setup
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:appointment.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                String title = '"'+editTitleField.getText()+'"';
                String loc = '"'+editLocField.getText()+'"';
                String query = "update Appointments set title=" +title+ ","+"date="+ getDate()+","+"location="+loc+" " +"where "+"ID="+id;
//
                System.out.println(query);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);

                statement.close();

            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
