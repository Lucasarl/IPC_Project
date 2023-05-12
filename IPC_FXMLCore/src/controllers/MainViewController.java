/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import model.*;
import models2.*;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MainViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ObservableList<Slot> datos =null; // Colecci�n vinculada a la vista.
    @FXML
    private TableView<Slot> court1;
    @FXML
    private TableColumn<Slot, String> column2;
    @FXML
    private TableColumn<Slot, String> column3;
    @FXML
    private TableColumn<Slot, String> column4;
    @FXML
    private TableColumn<Slot, String> column5;
    @FXML
    private TableColumn<Slot, String> column6;
    @FXML
    private TableColumn<Slot, String> column7;
    String nickName;//PARAMETROS PASADOS DEL LOGIN
    String passwordMember;
    @FXML
    private TableColumn<Slot, String> column1;
        
    private void inicializaModelo() throws ClubDAOException, IOException {
        
        Club c=Club.getInstance();
        LocalDate a=LocalDate.ofYearDay(2023, 12);
       
        
        
       
        c.setInitialData(); //REINICIA LOS DATOS DEL CLUB
        nickName="Ntonio";
        passwordMember="erewrqdc";
        String urlImage = "src/images/men.PNG"; 
        Image avatar=new Image(new FileInputStream(urlImage));
        // Si usamos, Image avatar=null, muestra default.png;
        Member m=c.registerMember("Pedro","Antonio Palillo","643213454","Ntonio","erewrqdc",null, -1,null);
        Booking b=c.registerBooking(LocalDateTime.now(), a, LocalTime.NOON, false, c.getCourts().get(0), m);
       
       //System.out.println(b.getMember().getName());
       List<Booking> misdatosCourt1 = c.getCourtBookings(c.getCourts().get(0).getName(),a);
       List<Booking> misdatosCourt2 = c.getCourtBookings(c.getCourts().get(1).getName(),a);
       List<Booking> misdatosCourt3 = c.getCourtBookings(c.getCourts().get(2).getName(),a);
       List<Booking> misdatosCourt4 = c.getCourtBookings(c.getCourts().get(3).getName(),a);
       List<Booking> misdatosCourt5 = c.getCourtBookings(c.getCourts().get(4).getName(),a);
       List<Booking> misdatosCourt6 = c.getCourtBookings(c.getCourts().get(5).getName(),a);
       
       List<String> hours= times();
       List<String> bookings1=slots(misdatosCourt1);
       List<String> bookings2=slots(misdatosCourt2);
       List<String> bookings3=slots(misdatosCourt3);
       List<String> bookings4=slots(misdatosCourt4);
       List<String> bookings5=slots(misdatosCourt5);
       List<String> bookings6=slots(misdatosCourt6);
         
       ArrayList<Slot> misdatos = new ArrayList<>();
       for(int i=0; i<14;i++) {
           misdatos.add(new Slot(hours.get(i), bookings1.get(i), bookings2.get(i),bookings3.get(i), bookings4.get(i), bookings5.get(i), bookings6.get(i)));
       }
       
       for(int i=0; i<14;i++) {
           System.out.println(misdatos.get(i).toString());
       }
       //System.out.println(misdatos.get(0).getMember().getNickName());
         //System.out.println(misdatos.get(0).getMember().getNickName());
        datos = FXCollections.observableList(misdatos);
        column1.setCellValueFactory(new PropertyValueFactory<Slot, String>("hour"));
        column2.setCellValueFactory(new PropertyValueFactory<Slot, String>("court1"));
        column3.setCellValueFactory(new PropertyValueFactory<Slot, String>("court2"));
        column4.setCellValueFactory(new PropertyValueFactory<Slot, String>("court3"));
        column5.setCellValueFactory(new PropertyValueFactory<Slot, String>("court4"));
        column6.setCellValueFactory(new PropertyValueFactory<Slot, String>("court5"));
        column7.setCellValueFactory(new PropertyValueFactory<Slot, String>("court6"));
        court1.setItems(datos);
        
        /*ObservableList<TablePosition> selectedCells = court1.getSelectionModel().getSelectedCells() ;
        selectedCells.addListener((ListChangeListener.Change<? extends TablePosition> change) -> {
        if (selectedCells.size() > 0) {
        TablePosition selectedCell = selectedCells.get(0);
        TableColumn column = selectedCell.getTableColumn();
        int rowIndex = selectedCell.getRow();
        Object data = column.getCellObservableValue(rowIndex).getValue();
    }
});*/
        
         //System.out.println(courtBookedAtTime(misdatos,LocalTime.NOON));
         //System.out.println(courtBookedAtTime(misdatos,LocalTime.MIDNIGHT));
         System.out.println();
         
         
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         System.out.println();
         System.out.println(bookings1.size());
         System.out.println();
         for(int i=0; i<bookings1.size(); i++){
             System.out.println(bookings1.get(i));
         }
         System.out.println();
         for(int i=0; i<bookings1.size(); i++){
             System.out.println(bookings2.get(i));
         }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            inicializaModelo();
        } catch (ClubDAOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public Booking courtBookedAtTime(List<Booking> bookingsCourtDay, LocalTime h) {
        for(int i=0; i<bookingsCourtDay.size() ; i++) {
            if(bookingsCourtDay.get(i).getFromTime().equals(h)) return bookingsCourtDay.get(i);
        }
        return null;
    }
    
    public List<String> slots(List<Booking> bookingsCourtDay) {
        List<String> res = new ArrayList<>();
        LocalTime l; int i=0;
        for(i=9;i<23;i++)
        {
            l=LocalTime.of(i,0);
            Booking b=courtBookedAtTime(bookingsCourtDay,l);
            if(b==null){
              res.add("free");
            } else {
              res.add(b.getMember().getNickName());
            }
        }
        return res;
        
        
 
    } 
    
    public List<String> times() {
        List<String> res = new ArrayList<>();
        for(int i=9;i<23;i++)
        {
            res.add(i+"h");
        }
        return res;
    }
}
