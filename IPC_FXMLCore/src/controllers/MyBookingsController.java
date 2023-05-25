/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import ipc_fxmlcore.IPC_FXMLCore;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Member;
import models2.Slot;
import models2.myBooking;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MyBookingsController implements Initializable {
    
    private int elements;
    
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
    private int initC;
    private ObservableList <myBooking> data;
    @FXML
    private HBox hBox;
    @FXML
    private HBox hBox2;
    @FXML
    private Label page;
    @FXML
    private Label elementsL;
    
    private int past;
    
   //ChangeListener <Number> listenerTable=this::listenerTable; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{
        Club c=Club.getInstance();
        previous.setDisable(true);
        List<Booking> bo=c.getUserBookings(nickName);
       
       tableView.getSelectionModel().setSelectionMode(
               SelectionMode.MULTIPLE
               
);
       
      
       
       tableView.setPlaceholder(new Label("You have no bookings."));
       column1.setReorderable(false);
       column2.setReorderable(false);
       column3.setReorderable(false);
       column4.setReorderable(false);
       cancel.setDisable(true);
       tableView.getSelectionModel().selectedIndexProperty().addListener((o, oldVal,newVal)-> {
           if(newVal.intValue()==-1) {
               cancel.setDisable(true);
           } else {
               cancel.setDisable(false);
           }
       });
       
       
       
       
       User u =User.getInstance();
       nickName=u.getNickname();
       passwordMember=u.getPassword();
        
            c = Club.getInstance();
             List<Booking> b=c.getUserBookings(nickName);
             for(int i=0; i<b.size(); i++ ) {
           if(b.get(i).getMadeForDay().isBefore(LocalDate.now())) {
               counter++;
           }
           else if(b.get(i).getFromTime().equals(LocalTime.of(22,0))) {
               if(b.get(i).getMadeForDay().isEqual(LocalDate.now()) && LocalDateTime.of(LocalDate.now(), b.get(i).getFromTime()).isBefore( LocalDateTime.of(LocalDate.now(), LocalTime.now().plusMinutes(45)))  && LocalTime.now().isBefore(LocalTime.of(0, 0)) && LocalTime.now().isAfter(LocalTime.of(9, 0))) {
                    
                   counter++;
               }
           }
           
           else if(b.get(i).getMadeForDay().isEqual(LocalDate.now()) && LocalTime.now().isBefore(LocalTime.of(0, 0)) && LocalTime.now().isAfter(LocalTime.of(9, 0))) {
                   
               
              if(LocalDateTime.of(LocalDate.now(), b.get(i).getFromTime()).isBefore( LocalDateTime.of(LocalDate.now(), LocalTime.now().plusMinutes(60)))) {
                //System.out.println("h");
               counter++;
           }} else {break;}
             
             }
             
             initC=counter;
              elementsL.textProperty().addListener((o,oldVal,newVal)-> {
          int e=Integer.parseInt(newVal);
        
           if(counter-10<initC) {
              previous.setDisable(true);
          } else {previous.setDisable(false);}
           
           if(e-10<=0) {
              next.setDisable(true);
              /*if(data.isEmpty()) {
                  if(counter>initC) {
                      counter-=10;
                      try {
                          inicializarModelo();
                      } catch (ClubDAOException ex) {
                          Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      next.setDisable(false);
                  */
              
          } else {
               next.setDisable(false);
           }
       });
            c = Club.getInstance();
            Member m=c.getMemberByCredentials(nickName, passwordMember);
            
        } catch (ClubDAOException ex) {
            Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try {
            inicializarModelo();
        } catch (ClubDAOException ex) {
            Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*elementsL.textProperty().addListener((o,oldVal,newVal)-> {
         if(elements>0 && data.isEmpty()) {
             counter-=10;
             try {
                 inicializarModelo();
             } catch (ClubDAOException ex) {
                 Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        });*/
        /*
        selectAll.selectedProperty().addListener((o,oldVal,newVal)->{
           if(newVal) 
           {
               System.out.println(data.size());
              for(int i=0; i<data.size(); i++) {
                  System.out.println(i);
                  tableView.getSelectionModel().select(i);
              }
              tableView.getSelectionModel().selectedIndexProperty().addListener(listenerTable);
           }
           else {
               tableView.getSelectionModel().selectedIndexProperty().removeListener(listenerTable);
               tableView.getSelectionModel().select(-1);
               tableView.setDisable(false);
           }
        });
        
        */
       
    } 
    /*
    private void listenerTable (ObservableValue<? extends Number> valProp, Number oldProp, Number newProp) {
            if(selectAll.isSelected()) {
                for(int i=0; i<data.size(); i++) {
                  System.out.println(i);
                  tableView.getSelectionModel().select(i);
              
            }
        }
    }
    */

    @FXML
    private void addCard(ActionEvent event) throws IOException, ClubDAOException {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/addCard.fxml"));
        Parent root=myLoader.load();
        AddCardController a=myLoader.getController();
        a.initMember(nickName,passwordMember);
        a.setFromProfile(false);
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void cancel(ActionEvent event) throws ClubDAOException, IOException {
        List <myBooking> l=tableView.getSelectionModel().getSelectedItems();
        Club c=Club.getInstance();
        int d=0; int v=0;
        List <Booking> b=c.getUserBookings(nickName);
        for(int i=0;i<l.size();i++) {
            myBooking m=l.get(i);
            for(int j=0;j<b.size();j++) {
                Booking nb=b.get(j);
                if(m.getCourt().equals(nb.getCourt().getName())
                   && m.getDate().equals(nb.getMadeForDay().toString()) &&
                           m.getTime().substring(0, 5).equals(nb.getFromTime().toString())){
                    if(LocalDateTime.now().isBefore(LocalDateTime.of(nb.getMadeForDay(), nb.getFromTime()).minusHours(24))) {
                    c.removeBooking(nb); v++;
                } else {
                    d+=1;
                }}
                        
            }
        }
        
        int index=0;
        if(v==1) {
        index=tableView.getSelectionModel().getSelectedIndex(); }
        //System.out.println(d);
        int e=elements;
        inicializarModelo();
        
       // System.out.println(data.isEmpty() && e>0);
        if (data.isEmpty() && e>0){
            try{
            counter-=10;
            inicializarModelo();
            } catch (IndexOutOfBoundsException u) {
                
            }
        }
        
        if(v==1) {
            tableView.getSelectionModel().select(index);
        }
        
        if(d>0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                // ó AlertType.WARNING ó AlertType.ERROR ó AlertType.CONFIRMATIONalert.setTitle("Diálogo de información");
                 alert.setHeaderText(null);
                 ButtonType buttonTypeOne = new ButtonType("OK");
                 alert.getButtonTypes().setAll(buttonTypeOne);
                 DialogPane dialogPane = alert.getDialogPane();
                 dialogPane.getStylesheets().add(
               getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
                 alert.getDialogPane().getStyleClass().add("myAlert");
                 // ó null si no queremos cabecera
                 alert.setContentText("("+d+")"+" of the selected bookings haven`t been removed, because bookings can only be canceled more than 24 hours in advance.");
                 alert.showAndWait();
        }
    }

    @FXML
    private void next(ActionEvent event) throws ClubDAOException, IOException {
        counter+=10;
        inicializarModelo();
    }

    @FXML
    private void previous(ActionEvent event) throws ClubDAOException, IOException {
        counter-=10;
        inicializarModelo();
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
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        //System.out.println(counter);
         List<Booking> misdatosCourt1 = c.getUserBookings(nickName);
         past=0;
         for (int i=0;i<misdatosCourt1.size();i++){
             if(misdatosCourt1.get(i).getMadeForDay().isBefore(LocalDate.now()) || (misdatosCourt1.get(i).getMadeForDay().isEqual(LocalDate.now()) && misdatosCourt1.get(i).getFromTime().isBefore(LocalTime.now().plusMinutes(60))&& LocalTime.now().isBefore(LocalTime.of(0, 0)) && LocalTime.now().isAfter(LocalTime.of(9, 0))) || (misdatosCourt1.get(i).getFromTime().equals(LocalTime.of(22,0))&& misdatosCourt1.get(i).getMadeForDay().isEqual(LocalDate.now()) && LocalDateTime.of(LocalDate.now(), misdatosCourt1.get(i).getFromTime()).isBefore( LocalDateTime.of(LocalDate.now(), LocalTime.now().plusMinutes(45)))  && LocalTime.now().isBefore(LocalTime.of(0, 0)) && LocalTime.now().isAfter(LocalTime.of(9, 0)))) {
                past+=1; 
             }
             
         }
         //System.out.println(elementsL.getText());
         List<String> bookings1=date(misdatosCourt1);
         boolean f=false;
         for(int i=past; i<misdatosCourt1.size();i++){
             if(misdatosCourt1.get(i).getPaid()==false) {
                 f=true;
             }
         }
         //System.out.println(f);
         if(f) {
             unpaidLabel.setVisible(true);
                addACard.setVisible(true);
                addACard.setDisable(false);
                hBox.setPrefHeight(100);
                hBox2.setPrefHeight(100);
         } else if(bookings1.isEmpty()) {
            unpaidLabel.setVisible(false);
                addACard.setVisible(false);
                addACard.setDisable(true);
                hBox.setPrefHeight(30);
                hBox2.setPrefHeight(30);
         } else {
             unpaidLabel.setVisible(false);
                addACard.setVisible(false);
                addACard.setDisable(true);
                hBox.setPrefHeight(30);
                hBox2.setPrefHeight(30);
         }
         
         if(m.getCreditCard()!=null && !f) {
              unpaidLabel.setVisible(false);
                addACard.setVisible(false);
                addACard.setDisable(true);
                hBox.setPrefHeight(30);
                hBox2.setPrefHeight(30);
         }
         
         
         elements=misdatosCourt1.size()-counter;
         elementsL.setText(Integer.toString(elements));
         List<String> bookings2=court(misdatosCourt1);
         List<String> bookings3=time(misdatosCourt1);
         List<String> bookings4=status(misdatosCourt1);
         ArrayList<myBooking> misdatos = new ArrayList<>();
         
         int numPages;
         System.out.println(counter);
         if((misdatosCourt1.size()-past)%10==0){
             numPages=(misdatosCourt1.size()-past)/10;
         } else {
              numPages=(misdatosCourt1.size()-past)/10+1;
         }
         try{
        page.textProperty().setValue("Page "+Integer.toString(((counter-past)%misdatosCourt1.size()+10)/10)+" of "+Integer.toString(numPages));
        page.setVisible(true);
         } catch (java.lang.ArithmeticException e) {
             page.setVisible(false);
         }
       for(int i=0; i<bookings1.size();i++) {
           misdatos.add(new myBooking( bookings1.get(i), bookings2.get(i),bookings3.get(i), bookings4.get(i)));
       }
       data = FXCollections.observableList(misdatos);
        column1.setCellValueFactory(new PropertyValueFactory<myBooking, String>("date"));
        column2.setCellValueFactory(new PropertyValueFactory<myBooking, String>("court"));
        column3.setCellValueFactory(new PropertyValueFactory<myBooking, String>("time"));
        column4.setCellValueFactory(new PropertyValueFactory<myBooking, String>("status"));
        tableView.setItems(data);
        
        
        column1.setCellFactory(column -> {
    return new TableCell<myBooking, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                // Style all dates in March with a different color.
                   String s=getStyle();
                    /*this.hoverProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                         setStyle("-fx-background-color:  #8cffc6");
                    } else{
                        setStyle(s);
                    }
                    });*/
                    setText(item);
                    setAlignment(Pos.CENTER);
            }}
    };

});

column2.setCellFactory(column -> {
    return new TableCell<myBooking, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                    /*String s=getStyle();
                    this.hoverProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                         setStyle("-fx-background-color:  #8cffc6");
                    } else{
                        setStyle(s);
                    }
                    });*/
                // Style all dates in March with a different color.
                    setText(item);
                    setAlignment(Pos.CENTER);
            }}
    };

});


column3.setCellFactory(column -> {
    return new TableCell<myBooking, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            /*String s=getStyle();
                    this.hoverProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                         setStyle("-fx-background-color:  #8cffc6");
                    } else{
                        setStyle(s);
                    }
                    });*/
            super.updateItem(item, empty);
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                // Style all dates in March with a different color.
                    setText(item);
                    setAlignment(Pos.CENTER);
            }}
    };

});
     
column4.setCellFactory(column -> {
    return new TableCell<myBooking, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            /*String s=getStyle();
                    this.hoverProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                        
                         setStyle("-fx-background-color:  #8cffc6");
                    } else{
                        
                        setStyle(s);
                    }
                    });*/
            super.updateItem(item, empty);
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                // Style all dates in March with a different color.
                    setText(item);
                    setAlignment(Pos.CENTER);
                try {
                    Club c=Club.getInstance();
                    Member m=c.getMemberByCredentials(nickName, passwordMember);
                    if(item.equals("Unpaid")) {
                        setTextFill(Color.RED);
                    } else {
                        setTextFill(Color.DARKGREEN);
                    }
                } catch (ClubDAOException ex) {
                    Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MyBookingsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }}
    };

});
    
    if(data.isEmpty()) {
             page.setVisible(false);
         } else {
             page.setVisible(true);
         }
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
            if(b.get(i).getFromTime().equals(LocalTime.of(22,0))) {
                 res.add(b.get(i).getFromTime().toString()+ " - "+ b.get(i).getFromTime().plusMinutes(45).toString());
            }
            else {
                          res.add(b.get(i).getFromTime().toString()+ " - "+ b.get(i).getFromTime().plusMinutes(60).toString());  
            }

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

    @FXML
    private void info(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                // ó AlertType.WARNING ó AlertType.ERROR ó AlertType.CONFIRMATIONalert.setTitle("Diálogo de información");
                 alert.setHeaderText(null);
                 ButtonType buttonTypeOne = new ButtonType("OK");
                 alert.getButtonTypes().setAll(buttonTypeOne);
                 DialogPane dialogPane = alert.getDialogPane();
                 dialogPane.getStylesheets().add(
               getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
                 alert.getDialogPane().getStyleClass().add("myAlert");
                 // ó null si no queremos cabecera
                 alert.setContentText("Use Ctrl+Left Click if you want to select multiple bookings to cancel.");
                 alert.showAndWait();
    }
}
