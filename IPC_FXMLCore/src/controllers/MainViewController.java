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

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MainViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ObservableList<Booking> datos =null; // Colecci�n vinculada a la vista.
    @FXML
    private TableView<Booking> court1;
    @FXML
    private TableColumn<Booking, String> column1;
    @FXML
    private TableColumn<Booking, String> column2;
    @FXML
    private TableColumn<Booking, String> column3;
    @FXML
    private TableColumn<Booking, String> column4;
    @FXML
    private TableColumn<Booking, String> column5;
    @FXML
    private TableColumn<Booking, String> column6;
    @FXML
    private TableColumn<Booking, String> column7;
    String nickName;//PARAMETROS PASADOS DEL LOGIN
    String passwordMember;
        
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
       
       System.out.println(b.getMember().getName());
       List<Booking> misdatos = c.getForDayBookings(a);
       System.out.println(misdatos.get(0).getMember().getNickName());
         //System.out.println(misdatos.get(0).getMember().getNickName());
        datos = FXCollections.observableList(misdatos);
        column1.setCellValueFactory(bookingRow->new SimpleStringProperty(bookingRow.getValue().getFromTime().toString()));
        column2.setCellValueFactory(bookingRow-> new SimpleStringProperty(bookingRow.getValue().getMember().getNickName()));
        court1.setItems(datos);
        
        ObservableList<TablePosition> selectedCells = court1.getSelectionModel().getSelectedCells() ;
        selectedCells.addListener((ListChangeListener.Change<? extends TablePosition> change) -> {
        if (selectedCells.size() > 0) {
        TablePosition selectedCell = selectedCells.get(0);
        TableColumn column = selectedCell.getTableColumn();
        int rowIndex = selectedCell.getRow();
        Object data = column.getCellObservableValue(rowIndex).getValue();
    }
});
        
         System.out.println(courtBookedAtTime(misdatos,LocalTime.NOON));
         System.out.println(courtBookedAtTime(misdatos,LocalTime.MIDNIGHT));
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
    
    public boolean courtBookedAtTime(List<Booking> bookingsCourtDay, LocalTime h) {
        for(int i=0; i<bookingsCourtDay.size() ; i++) {
            if(bookingsCourtDay.get(i).getFromTime().equals(h)) return true;
        }
        return false;
    }
    
   
    
}
