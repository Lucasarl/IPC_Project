/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import ipc_fxmlcore.IPC_FXMLCore;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.*;
import models2.*;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InitialPageController implements Initializable {

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
    
    private LocalDate date;
    @FXML
    private TableColumn<Slot, String> column1;
    @FXML
    private DatePicker dpBookingDay;
    private ImageView cogwheel;
    private ImageView profilePicture;
    private Label userName;
    
    Service service = new ProcessService();
    @FXML
    private Label taskLabel;
    @FXML
    private Button logIn;
    @FXML
    private Button signUp;
    
    
        
    private void inicializaModelo() throws ClubDAOException, IOException {
        
        court1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        court1.getSelectionModel().setCellSelectionEnabled(true);
        court1.addEventFilter(ScrollEvent.ANY, Event::consume);

       // or if you only want to disable horizontal scrolling
       court1.addEventFilter(ScrollEvent.ANY, event -> {
         if (event.getDeltaX() != 0) {
              event.consume();
         }
     });
        
        Club c=Club.getInstance();
       
        // Club c=Club.getInstance();
        //c.setInitialData(); //REINICIA LOS DATOS DEL CLUB
       
        //nickName="Ntonio";
        //passwordMember="erewrqdc";
        //String urlImage = "src/images/men.PNG"; 
        //Image avatar=new Image(new FileInputStream(urlImage));
        // Si usamos, Image avatar=null, muestra default.png;
        //Member m=c.registerMember("Pedro","Antonio Palillo","643213454","Ntonio","erewrqdc",null, -1,null);
        //Booking b=c.registerBooking(LocalDateTime.now(), date, LocalTime.NOON, false, c.getCourts().get(0), m);
       
       //System.out.println(b.getMember().getName());
       List<Booking> misdatosCourt1 = c.getCourtBookings(c.getCourts().get(0).getName(),date);
       List<Booking> misdatosCourt2 = c.getCourtBookings(c.getCourts().get(1).getName(),date);
       List<Booking> misdatosCourt3 = c.getCourtBookings(c.getCourts().get(2).getName(),date);
       List<Booking> misdatosCourt4 = c.getCourtBookings(c.getCourts().get(3).getName(),date);
       List<Booking> misdatosCourt5 = c.getCourtBookings(c.getCourts().get(4).getName(),date);
       List<Booking> misdatosCourt6 = c.getCourtBookings(c.getCourts().get(5).getName(),date);
       
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
       
      /* for(int i=0; i<14;i++) {
           System.out.println(misdatos.get(i).toString());
       }*/
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

column1.setCellFactory(column -> {
    return new TableCell<Slot, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            String s=this.getStyle();
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                  
                   setTextFill(Color.BLACK);
                   // setStyle("");
                   /*this.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));*/
                   setStyle(s);
                   setText(item);
                   setAlignment(Pos.CENTER);
                   this.selectedProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                        setTextFill(Color.BLACK);
                        setStyle(s);
                        
                    } else{
                        setTextFill(Color.BLACK);
                        setStyle(s);
                        
                    }
                    });
                }
            
        }
    };

});

/*court1.setRowFactory(tv -> {
TableRow<Slot> row = new TableRow<>();
row.selectedProperty().addListener((obs, oldVal, newVal) -> {
if (newVal) {
row.setStyle("-fx-background-color: #22bad9;");
} else {
row.setStyle("");
}
});
return row;});*/

// Custom rendering of the table cell.
column2.setCellFactory(column -> {
    return new TableCell<Slot, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
           
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                // Style all dates in March with a different color.
                if (item.equals("Free")) {
                    setStyle("-fx-text-fill: #707070");
                    setText("Free");
                    setAlignment(Pos.CENTER);
                    String s=this.getStyle();
                    this.hoverProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                         setStyle("-fx-background-color:  #ececec; -fx-text-fill: #707070");
                         
                    } else{
                        
                        setStyle(s);
                    }
                    });
                    
                    
                } else {
                    setTextFill(Color.BLACK);
                   // setStyle("");
                   setStyle("-fx-background-color:  #d6d6d6");
                   setText(item);
                   setAlignment(Pos.CENTER);
                   
             
                        }
                       
            }
        }
    };
            });
    




    column3.setCellFactory(column -> {
    return new TableCell<Slot, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                // Style all dates in March with a different color.
                if (item.equals("Free")) {
                    setStyle("-fx-text-fill: #707070");
                    setText("Free");
                    setAlignment(Pos.CENTER);
                    String s=this.getStyle();
                    this.hoverProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                         setStyle("-fx-background-color:  #ececec; -fx-text-fill: #707070");
                         
                    } else{
                        
                        setStyle(s);
                    }
                    });
                    
                     
                } else {
                    setTextFill(Color.BLACK);
                    setStyle("-fx-background-color:  #d6d6d6");
                   // setStyle("");
                   setText(item);
                   setAlignment(Pos.CENTER);
                   
                }  
                }
            }
        
     };
    });




column4.setCellFactory(column -> {
    return new TableCell<Slot, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
           
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                // Style all dates in March with a different color.
                if (item.equals("Free")) {
                    setStyle("-fx-text-fill: #707070");
                    setText("Free");
                    setAlignment(Pos.CENTER);
                    String s=this.getStyle();
                    this.hoverProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                         setStyle("-fx-background-color:  #ececec; -fx-text-fill: #707070");
                         
                    } else{
                        
                        setStyle(s);
                    }
                    });
                } else {
                    setTextFill(Color.BLACK);
                    setStyle("-fx-background-color:  #d6d6d6");
                   // setStyle("");
                   setText(item);
                   setAlignment(Pos.CENTER);
                }
            }
        }
    };

});

column5.setCellFactory(column -> {
    return new TableCell<Slot, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                // Style all dates in March with a different color.
                if (item.equals("Free")) {
                    setStyle("-fx-text-fill: #707070");
                    setText("Free");
                    setAlignment(Pos.CENTER);
                    String s=this.getStyle();
                    this.hoverProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                         setStyle("-fx-background-color:  #ececec; -fx-text-fill: #707070");
                         
                    } else{
                        
                        setStyle(s);
                    }
                    });
                } else {
                    setTextFill(Color.BLACK);
                    setStyle("-fx-background-color:  #d6d6d6");
                   // setStyle("");
                   setText(item);
                   setAlignment(Pos.CENTER);
                }
            }
        }
    };

});

column6.setCellFactory(column -> {
    return new TableCell<Slot, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                // Style all dates in March with a different color.
                // Style all dates in March with a different color.
                if (item.equals("Free")) {
                    setStyle("-fx-text-fill: #707070");
                    setText("Free");
                    setAlignment(Pos.CENTER);
                    String s=this.getStyle();
                    this.hoverProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                         setStyle("-fx-background-color:  #ececec; -fx-text-fill: #707070");
                         
                    } else{
                        
                        setStyle(s);
                    }
                    });
                } else {
                    setTextFill(Color.BLACK);
                    setStyle("-fx-background-color:  #d6d6d6");
                   // setStyle("");
                   setText(item);
                   setAlignment(Pos.CENTER);
                   
                }
            }
        }
    };

});

column7.setCellFactory(column -> {
    return new TableCell<Slot, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            
            if (item == null || empty) {
              //  setText(null);
                setStyle("");
            } else {
                // Style all dates in March with a different color.
                if (item.equals("Free")) {
                    setStyle("-fx-text-fill: #707070");
                    setText("Free");
                    setAlignment(Pos.CENTER);
                    String s=this.getStyle();
                    this.hoverProperty().addListener((o,oldVal,newVal)->{
                    if(newVal) {
                         setStyle("-fx-background-color:  #ececec; -fx-text-fill: #707070");
                         
                    } else{
                        
                        setStyle(s);
                    }
                    });
                } else {
                    setTextFill(Color.BLACK);
                    setStyle("-fx-background-color:  #d6d6d6");
                   // setStyle("");
                   setText(item);
                   setAlignment(Pos.CENTER);
                   
                }
            }
        }
    };

});

         //System.out.println(courtBookedAtTime(misdatos,LocalTime.NOON));
         //System.out.println(courtBookedAtTime(misdatos,LocalTime.MIDNIGHT));
         //System.out.println();
         
         
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //List<String> bookings1=slots(misdatos);
         //System.out.println();
         //System.out.println(bookings1.size());
         //System.out.println();
         /*for(int i=0; i<bookings1.size(); i++){
             System.out.println(bookings1.get(i));
         }
         System.out.println();
         for(int i=0; i<bookings1.size(); i++){
             System.out.println(bookings2.get(i));
         }*/
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            column1.setReorderable(false);
            column2.setReorderable(false);
            column3.setReorderable(false);
            column4.setReorderable(false);
            column5.setReorderable(false);
            column6.setReorderable(false);
            column7.setReorderable(false);
            Club c = Club.getInstance();
            //c.setInitialData();
            //Member m=c.registerMember("Pedro","Antonio Palillo","643213454","Ftonio","erewrqdc",null,321,null);
            //setP();
            dpBookingDay.valueProperty().addListener((o,oldVal,newVal)->{
               if(newVal.isAfter(LocalDate.of(2032, Month.JANUARY, 1))) {
                   dpBookingDay.valueProperty().setValue(oldVal);
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
                 alert.setContentText("Please select an earlier date.");
                 alert.showAndWait();
               } 
            });
            
            service.setOnSucceeded(e -> {
            taskLabel.setVisible(false);
            //reset service
            service.reset();
        });
            
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
           dpBookingDay.setDayCellFactory((DatePicker picker) -> {
           return new DateCell() {
           @Override
           public void updateItem(LocalDate date, boolean empty) {
           super.updateItem(date, empty);
           LocalDate today = LocalDate.now();
           setDisable(empty || date.compareTo(today) < 0 || date.isAfter(LocalDate.of(2032, Month.JANUARY, 1)));
            }
           };
          });
           Tooltip t=new Tooltip();
           t.setText("select a date");
           dpBookingDay.setTooltip(t);
            //Booking b=c.registerBooking(LocalDateTime.now(), date, LocalTime.NOON, false, c.getCourts().get(0), m);
            date=LocalDate.now();
            inicializaModelo();
            dpBookingDay.setValue(date);
            dpBookingDay.valueProperty().addListener((o,oldVal,newVal)-> {
                date=newVal;
                 try {
                     inicializaModelo();
                 } catch (ClubDAOException ex) {
                     Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException ex) {
                     Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            });
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
              res.add("Free");
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
    
    @FXML
     public void clickedColumn(MouseEvent event) throws IndexOutOfBoundsException, ClubDAOException, IOException {
        try {
        TablePosition tablePosition=court1.getSelectionModel().getSelectedCells().get(0);
        int column=tablePosition.getColumn();
        int row=tablePosition.getRow();
        if(column>0){
        Slot item=court1.getItems().get(row);
        TableColumn tableColumn=tablePosition.getTableColumn();
        String data= (String) tableColumn.getCellObservableValue(item).getValue();
        if(data.equals("Free")) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                // ó AlertType.WARNING ó AlertType.ERROR ó AlertType.CONFIRMATIONalert.setTitle("Diálogo de información");
                 alert.setHeaderText(null);
                 ButtonType buttonTypeOne = new ButtonType("OK",ButtonBar.ButtonData.CANCEL_CLOSE);
                 ButtonType buttonTypeTwo = new ButtonType("Log In");
                 ButtonType buttonTypeThree = new ButtonType("Sign Up");
                 alert.getButtonTypes().setAll(buttonTypeTwo, buttonTypeThree, buttonTypeOne);
                 DialogPane dialogPane = alert.getDialogPane();
                 dialogPane.getStylesheets().add(
               getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
                 alert.getDialogPane().getStyleClass().add("myAlert");
                 // ó null si no queremos cabecera
                 alert.setContentText("Please log in to book a court or sign up if you don't have an account.");
                 alert.getDialogPane().getChildren().forEach(node -> {
    if (node instanceof ButtonBar) {
        ButtonBar buttonBar = (ButtonBar) node;
        buttonBar.getButtons().forEach(possibleButtons -> {
            if (possibleButtons instanceof Button) {
                Button b = (Button) possibleButtons;
                if (b.getText().equals("Log In") || b.getText().equals("Sign Up") ) {
                    b.getStyleClass().add("login");
                }
            }
        });
    }
});
                 Optional<ButtonType> res=alert.showAndWait();
                 if(res.isPresent()) {
                     if(res.get().equals(buttonTypeTwo)) {
                         FXMLLoader loader= new FXMLLoader(getClass().getResource("/views/login.fxml"));
                         Parent root= loader.load();
                         IPC_FXMLCore.setRoot(root);
                     } 
                     else if(res.get().equals(buttonTypeTwo)) {
                         
                     }
                 }
        
        inicializaModelo();} 
    }} catch (IndexOutOfBoundsException e) {
    }}

    public void setImage(Image avatar) {
        profilePicture.imageProperty().setValue(avatar);
    }
    
    


    @FXML
    private void exitApplication(ActionEvent event) {
        cogwheel.getScene().getWindow().hide();
    }

    @FXML
    private void logIn(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/views/login.fxml"));
        Parent root= loader.load();
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void signUp(ActionEvent event) {
    }
    
    private void setP() throws ClubDAOException, IOException {
         Club c=Club.getInstance();
        String nickName="Ftonio";
        String passwordMember="erewrqdc";
        Member m=c.getMemberByCredentials(nickName, passwordMember);
        List<Booking> b=c.getUserBookings(nickName);
        
        //limites de registro de pista
        for(int i=0; i<b.size();i++){
           Booking n=b.get(i);
             //System.out.println(n.getFromTime().toString());
              System.out.println(n.getPaid()+" "+n.getFromTime().toString()); 
               
                     
           }
        }
}



 /*
.table-view{
 -fx-selection-bar: #8cffc6; 
 -fx-text-fill: red;

}

*/
