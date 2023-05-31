/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;
//#29a61ed1
import ipc_fxmlcore.IPC_FXMLCore;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AddCardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private BooleanProperty validCard;
    private BooleanProperty validSvc;
    private Tooltip tooltip;
     private BooleanProperty showSvc;
    
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField svc;

    public Member m;
    private Label errorPassword;
    @FXML
    private Button update;
    private Label nameRequired;
    private Label surnameRequired;
    @FXML
    private Label errorSvc;
    @FXML
    private Label errorCardNumber;
    @FXML
    private ImageView eyeSvc;
    
    private boolean fromProfile;
    private boolean signUp;
  
    public void initMember(String nickName, String password) throws ClubDAOException, IOException {
        Club c=Club.getInstance();
        m=c.getMemberByCredentials(nickName, password);
    }
    
    public void setSignup (boolean b) {
        signUp=b;
    }
    public void setFromProfile(boolean b) {
        fromProfile=b;
    }
    
    private void manageError(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.FALSE);
        showErrorMessage(errorLabel,textField);
        textField.requestFocus();
    }
    
    private void manageErrorSvc(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.FALSE);
        showErrorMessage(errorLabel,textField);
        //textField.requestFocus();
    }
    
    private void manageCorrect(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.TRUE);
        hideErrorMessage(errorLabel,textField);
    }
    
    private void showErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(true);
        textField.styleProperty().setValue("-fx-background-color: #FCE5E0");    
    }
    
    private void hideErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(false);
        textField.styleProperty().setValue("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
        //ENTER SHORTCUTS
        
         cardNumber.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 checkCreditCard();
                 update.fire();
}
         }
  
 );
         svc.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 checkSvc();
                 update.fire();
}
         }
  
 );   
        
        showSvc= new SimpleBooleanProperty();
        showSvc.setValue(Boolean.FALSE);
        showSvc.addListener((observable, oldValue, newValue) -> {
            if(newValue){
                showSvc();
            }else{
                hideSvc();
            }
        });
        
        
        
        svc.setOnKeyTyped(e-> {
                if(showSvc.get()) {
                showSvc();
                }
                });
        
        validCard = new SimpleBooleanProperty();
        validSvc= new SimpleBooleanProperty();
        
        
        validCard.setValue(Boolean.FALSE);
        validSvc.setValue(Boolean.FALSE);
        
        
        /*BooleanBinding validFields = Bindings.and(validPassword, validCreditCard)
                 .and(validSvc);
         update.disableProperty().bind(Bindings.not(validFields));*/
        
         
        cardNumber.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkCreditCard();
        }});
        
        
        svc.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkSvc();
        }});

       tooltip = new Tooltip();
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setAutoHide(false);
        tooltip.setMinWidth(50);
    }    
    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        if(!signUp) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.changeImage(m.getImage());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        
        if(fromProfile) {
        IPC_FXMLCore.setRoot(root);} else {
         myLoader=new FXMLLoader(getClass().getResource("/views/myBookings.fxml"));
         root=myLoader.load();
        //MyBookingsController b=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        IPC_FXMLCore.setRoot(root);
        }} else {
         Alert alert = new Alert(AlertType.CONFIRMATION);
         Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
         stage.getIcons().add(new Image(this.getClass().getResource("/images/Logo.png").toString()));
        // ó AlertType.WARNING ó AlertType.ERROR ó AlertType.CONFIRMATIONalert.setTitle("Diálogo de información");
        alert.setGraphic(new ImageView(this.getClass().getResource("/images/confirmation.png").toString()));
        alert.setHeaderText(null);
        ButtonType buttonTypeOne = new ButtonType("OK");
        alert.getButtonTypes().setAll(buttonTypeOne);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
      getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("myAlert");
        // ó null si no queremos cabecera
        alert.setContentText("Your account has been created. Please log in.");
        alert.showAndWait();
         FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/login.fxml"));
        Parent root=myLoader.load();
        //LoginController s=myLoader.getController();
        
        IPC_FXMLCore.setRoot(root);
        }
        
    }

    @FXML
    private void updateInfo(ActionEvent event) throws IOException, ClubDAOException {
        
         if(containsLetter(svc.textProperty().getValue())) {
            manageError(errorSvc,svc,validSvc);}
         
        else if(svc.textProperty().getValue().length()!=3) {
            manageError(errorSvc,svc,validSvc);}
        else {
            manageCorrect(errorSvc,svc,validSvc);
        }
        
        if(validCard.getValue().equals(Boolean.TRUE)&&validSvc.getValue().equals(Boolean.TRUE)) {
        Alert alert = new Alert(AlertType.WARNING);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/images/Logo.png").toString()));
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
      getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("myAlert");
        alert.setTitle("Add a card");
        alert.setHeaderText("Are you sure you want to add a credit card? You will pay for all of your current bookings automatically.");
        alert.setContentText(null);
        ButtonType buttonTypeAdd = new ButtonType("Add Card");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeAdd, buttonTypeCancel);
        alert.getDialogPane().getChildren().forEach(node -> {
    if (node instanceof ButtonBar) {
        ButtonBar buttonBar = (ButtonBar) node;
        buttonBar.getButtons().forEach(possibleButtons -> {
            if (possibleButtons instanceof Button) {
                Button b = (Button) possibleButtons;
                if (b.getText().equals("Cancel")) {
                    b.getStyleClass().add("cancel");
                }
            }
        });
    }
});
       Optional<ButtonType> result = alert.showAndWait();
       if (result.isPresent()) {
       if (result.get() == buttonTypeAdd){
        updateInfo();}
    }}}
    
    private void updateInfo() throws IOException, ClubDAOException {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
         Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
         stage.getIcons().add(new Image(this.getClass().getResource("/images/Logo.png").toString()));
        // ó AlertType.WARNING ó AlertType.ERROR ó AlertType.CONFIRMATIONalert.setTitle("Diálogo de información");
        alert.setGraphic(new ImageView(this.getClass().getResource("/images/confirmation.png").toString()));
        alert.setHeaderText(null);
        ButtonType buttonTypeOne = new ButtonType("OK");
        alert.getButtonTypes().setAll(buttonTypeOne);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
      getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("myAlert");
        // ó null si no queremos cabecera
        alert.setContentText("The credit card has been added.");
        alert.showAndWait();
        if(!signUp) {
        Club c=Club.getInstance();
        List<Booking> b=c.getUserBookings(m.getNickName());
        for(int i=0;i<b.size();i++){
            b.get(i).setPaid(true);
        }
        }
        Club c=Club.getInstance();
        
        User u=User.getInstance();
        Member m=c.getMemberByCredentials(u.getNickname(), u.getPassword());
        m.setCreditCard(cardNumber.textProperty().getValue());
        
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        //ps.loginInfo(m.getNickName(), m.getPassword());
        
        //ps.changeImage(m.getImage());
        //ps.setInvisible();
        //SI CAMBIAS LA CONTRASEÑA PETA PORQUE ESTOY USANDO UN USUARIO EJEMPLO "A LA FUERZA"
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),cardNumber.textProperty().getValue(),svc.textProperty().getValue());
        if(fromProfile && !signUp) {
        IPC_FXMLCore.setRoot(root);} else if(!signUp) {
        myLoader=new FXMLLoader(getClass().getResource("/views/myBookings.fxml"));
        root=myLoader.load();
        MyBookingsController bo=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        //ps.loginInfo(m.getNickName(), m.getPassword());
        
        //ps.changeImage(m.getImage());
        //ps.setInvisible();
        //SI CAMBIAS LA CONTRASEÑA PETA PORQUE ESTOY USANDO UN USUARIO EJEMPLO "A LA FUERZA"
        //ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),cardNumber.textProperty().getValue(),svc.textProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        
        } else {
                alert = new Alert(AlertType.CONFIRMATION);
         stage = (Stage) alert.getDialogPane().getScene().getWindow();
         stage.getIcons().add(new Image(this.getClass().getResource("/images/Logo.png").toString()));
        // ó AlertType.WARNING ó AlertType.ERROR ó AlertType.CONFIRMATIONalert.setTitle("Diálogo de información");
        alert.setGraphic(new ImageView(this.getClass().getResource("/images/confirmation.png").toString()));
        alert.setHeaderText(null);
        buttonTypeOne = new ButtonType("OK");
        alert.getButtonTypes().setAll(buttonTypeOne);
         dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
      getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("myAlert");
        // ó null si no queremos cabecera
        alert.setContentText("Your account has been created. Please log in.");
        alert.showAndWait();
                myLoader=new FXMLLoader(getClass().getResource("/views/login.fxml"));
                root=myLoader.load();
                LoginController l =myLoader.getController();
                IPC_FXMLCore.setRoot(root);
                }
        }
    
    
    private void checkCreditCard ()  {
        if(cardNumber.textProperty().getValue().length()!=16) {
            manageError(errorCardNumber, cardNumber, validCard);
        }
        
        else {
            manageCorrect(errorCardNumber, cardNumber, validCard);
        }
        
        
    }
    
     private void checkSvc(){
       if(svc.textProperty().getValue().length()!=3) {
            errorSvc.setText("CSC must be 3 characters long.");
            manageError(errorSvc,svc,validSvc);}
        else if(containsLetter(svc.textProperty().getValue())) {
             errorSvc.setText("CSC must not contain letters.");
            manageError(errorSvc,svc,validSvc);
        }
        else {
            manageCorrect(errorSvc,svc,validSvc);
        }
    }
     
     private void showSvc() {
        Point2D p = svc.localToScene(svc.getBoundsInLocal().getMaxX(), svc.getBoundsInLocal().getMaxY());
            tooltip.setText(svc.getText());
            tooltip.show(svc,
                p.getX() + svc.getScene().getX() + svc.getScene().getWindow().getX(),
                p.getY() + svc.getScene().getY() + svc.getScene().getWindow().getY());
    }
    
    private void hideSvc() {
        tooltip.setText("");
        tooltip.hide();
    }

    @FXML
    private void visibilitySvc(MouseEvent event) throws FileNotFoundException {
        if(showSvc.getValue().equals(Boolean.FALSE)) {
           showSvc.setValue(Boolean.TRUE);
           String showEyeURL = "src/images/eye2.png"; 
            Image showEye=new Image(new FileInputStream(showEyeURL));
            eyeSvc.imageProperty().setValue(showEye);
            return;
        }
        else {
            String showEyeURL = "src/images/eye1.png"; 
            Image showEye=new Image(new FileInputStream(showEyeURL));
            eyeSvc.imageProperty().setValue(showEye);
           showSvc.setValue(Boolean.FALSE);
           
        }
    }
    private boolean containsLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
