/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import ipc_fxmlcore.IPC_FXMLCore;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import models2.Slot;
import models2.myBooking;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MyBookingsController implements Initializable {

    @FXML
    private Label unpaidLabel;
    @FXML
    private Button addACard;
    @FXML
    private TableView<myBooking> tableView;
    @FXML
    private TableColumn<myBooking, String> column1;
    @FXML
    private TableColumn<myBooking, String> column2;
    @FXML
    private TableColumn<myBooking, String> column3;
    @FXML
    private TableColumn<myBooking, String> column4;
    @FXML
    private Button cancel;
    @FXML
    private Button next;
    @FXML
    private Button previous;
    
    private String nickName;
    private String passwordMember;
    private int counter=0;
    private ObservableList <myBooking> data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       User u =User.getInstance();
       nickName=u.getNickname();
       passwordMember=u.getPassword();
        try {
            inicializarModelo();
        } catch (ClubDAOException ex) {
            Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void addCard(ActionEvent event) {
        
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void next(ActionEvent event) {
    }

    @FXML
    private void previous(ActionEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/mainView.fxml"));
        Parent root=myLoader.load();
        //MainViewController ps=myLoader.getController();
        IPC_FXMLCore.setRoot(root);  
    }
    
    private void inicializarModelo() throws ClubDAOException, IOException {
        
        Club c=Club.getInstance();
        List<Booking> b=c.getUserBookings(nickName);
        for(int i=0; i<b.size(); i++ ) {
           if(b.get(i).getMadeForDay().isBefore(LocalDate.now())) {
               counter++;
           } else if(b.get(i).getMadeForDay().isEqual(LocalDate.now()) && b.get(i).getFromTime().isBefore(LocalTime.now())) {
               counter++;
           } else {break;}
        }
        System.out.println(counter);
         List<Booking> misdatosCourt1 = c.getUserBookings(nickName);
         List<String> bookings1=date(misdatosCourt1);
         List<String> bookings2=court(misdatosCourt1);
         List<String> bookings3=time(misdatosCourt1);
         List<String> bookings4=status(misdatosCourt1);
         ArrayList<myBooking> misdatos = new ArrayList<>();
       for(int i=0; i<bookings1.size();i++) {
           misdatos.add(new myBooking( bookings1.get(i), bookings2.get(i),bookings3.get(i), bookings4.get(i)));
       }
       data = FXCollections.observableList(misdatos);
        column1.setCellValueFactory(new PropertyValueFactory<myBooking, String>("date"));
        column2.setCellValueFactory(new PropertyValueFactory<myBooking, String>("court"));
        column3.setCellValueFactory(new PropertyValueFactory<myBooking, String>("time"));
        column4.setCellValueFactory(new PropertyValueFactory<myBooking, String>("status"));
        tableView.setItems(data);
    }
    
    private List<String> date(List<Booking> b) {
        List<String> res=new ArrayList<>();
        int x=counter+10;
        for(int i=counter; i<x && i<b.size(); i++ ) {
            res.add(b.get(i).getMadeForDay().toString());
        }
        
        return res;
    }
    
    private List<String> court(List<Booking> b) {
        List<String> res=new ArrayList<>();
        int x=counter+10;
        for(int i=counter; i<x && i<b.size(); i++ ) {
            res.add(b.get(i).getCourt().getName());
        }
       
        return res;
    }
    
    private List<String> time(List<Booking> b) {
        List<String> res=new ArrayList<>();
        int x=counter+10;
        for(int i=counter; i<x && i<b.size(); i++ ) {
            res.add(b.get(i).getFromTime().toString());
        }
        return res;
    }
    
    private List<String> status(List<Booking> b) {
        List<String> res=new ArrayList<>();
        int x=counter+10;
        for(int i=counter; i<x && i<b.size(); i++ ) {
            String s;
            if(b.get(i).getPaid()) {
                s="Paid";
            } else {
                s="Unpaid";
            }
            res.add(s);
        }
        return res;
    }
}
