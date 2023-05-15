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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.*;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ChangeProfileInfoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private BooleanProperty validPassword;
    private BooleanProperty validCreditCard;
    private BooleanProperty validSvc;  
    private BooleanProperty validName;
    private BooleanProperty validSurname;
    private BooleanProperty validTelephone;
    
    private Tooltip tooltipP;
    private Tooltip tooltipS;
    private BooleanProperty showPassword;
    private BooleanProperty showSvc;
    
    @FXML
    private TextField name;
    @FXML
    private TextField familyName;
    @FXML
    private TextField password;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField svc;

    public Member m;
    @FXML
    private Label errorPassword;
    @FXML
    private Label errorCardNumber;
    @FXML
    private Label errorSvc;
    @FXML
    private Button update;
    @FXML
    private Label nameRequired;
    @FXML
    private Label surnameRequired;
    @FXML
    private ImageView eyePassword;
    @FXML
    private ImageView eyeSvc;
    @FXML
    private TextField telephone;
    @FXML
    private Label telephoneRequired;
    
    //LOADS PROFILE DETAILS
    public void initMember(String nickName, String password) throws ClubDAOException, IOException {
        Club c=Club.getInstance();
        m=c.getMemberByCredentials(nickName, password);
        name.textProperty().setValue(m.getName());
        familyName.textProperty().setValue(m.getSurname());
        this.password.textProperty().setValue(m.getPassword());
        cardNumber.textProperty().setValue(m.getCreditCard());
        String svcString=Integer.toString(m.getSvc());
        svc.textProperty().setValue(svcString);
        telephone.textProperty().setValue(m.getTelephone());
    }
    
    private void manageError(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.FALSE);
        showErrorMessage(errorLabel,textField);
        textField.requestFocus();
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
        
        // sumamos los atajos de update con enter
         name.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 try {
                     updateInfo();
                 } catch (IOException ex) {
                     Logger.getLogger(ChangeProfileInfoController.class.getName()).log(Level.SEVERE, null, ex);
                 }
}
         }
  
 );
          familyName.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 try {
                     updateInfo();
                 } catch (IOException ex) {
                     Logger.getLogger(ChangeProfileInfoController.class.getName()).log(Level.SEVERE, null, ex);
                 }
}
         }
  
 );
           password.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 try {
                     updateInfo();
                 } catch (IOException ex) {
                     Logger.getLogger(ChangeProfileInfoController.class.getName()).log(Level.SEVERE, null, ex);
                 }
}
         }
  
 );
            telephone.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 try {
                     updateInfo();
                 } catch (IOException ex) {
                     Logger.getLogger(ChangeProfileInfoController.class.getName()).log(Level.SEVERE, null, ex);
                 }
}
         }
  
 );
             cardNumber.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 try {
                     updateInfo();
                 } catch (IOException ex) {
                     Logger.getLogger(ChangeProfileInfoController.class.getName()).log(Level.SEVERE, null, ex);
                 }
}
         }
  
 );
         svc.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 try {
                     updateInfo();
                 } catch (IOException ex) {
                     Logger.getLogger(ChangeProfileInfoController.class.getName()).log(Level.SEVERE, null, ex);
                 }
}
         }
  
 );     
        
        validPassword = new SimpleBooleanProperty();
        validCreditCard = new SimpleBooleanProperty();   
        validSvc = new SimpleBooleanProperty();
        validName= new SimpleBooleanProperty();
        validSurname=new SimpleBooleanProperty();
        validTelephone=new SimpleBooleanProperty();
        validPassword.setValue(Boolean.TRUE);
        validCreditCard.setValue(Boolean.TRUE);
        validSvc.setValue(Boolean.TRUE);
        validName.setValue(Boolean.TRUE);
        validSurname.setValue(Boolean.TRUE);
        validTelephone.setValue(Boolean.TRUE);
        /*BooleanBinding validFields = Bindings.and(validPassword, validCreditCard)
                 .and(validSvc);
         update.disableProperty().bind(Bindings.not(validFields));*/
        
         
        password.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkPassword();
        }});
        
        cardNumber.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkCreditCard();
        }});
        
        svc.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkSvc();
        }});
        
        name.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkName();
        }});
        
        familyName.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkSurname();
        }});
        
        telephone.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkTelephone();
        }});

        showPassword = new SimpleBooleanProperty();
        showPassword.setValue(Boolean.FALSE);
        showPassword.addListener((observable, oldValue, newValue) -> {
            if(newValue){
                showPassword();
            }else{
                hidePassword();
            }
        });
        
        
        
        password.setOnKeyTyped(e-> {
                if(showPassword.get()) {
                showPassword();
                }
                });
        
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
        
        tooltipP = new Tooltip();
        tooltipP.setShowDelay(Duration.ZERO);
        tooltipP.setAutoHide(false);
        tooltipP.setMinWidth(50);
        
        tooltipS = new Tooltip();
        tooltipS.setShowDelay(Duration.ZERO);
        tooltipS.setAutoHide(false);
        tooltipS.setMinWidth(50);
    }    
    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(m.getImage());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void updateInfo(ActionEvent event) throws IOException {
        updateInfo();
    }
    
    private void updateInfo() throws IOException {
        if((!errorPassword.isVisible() && !errorCardNumber.isVisible() && !errorSvc.isVisible() && !nameRequired.isVisible() && !surnameRequired.isVisible()) &&!telephoneRequired.isVisible()){
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
        alert.setContentText("Your profile has been updated");
        alert.showAndWait();
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        //NECESARIO SOLO DE MOMENTO
        //SI CAMBIAS LA CONTRASEÑA PETA PORQUE ESTOY USANDO UN USUARIO EJEMPLO "A LA FUERZA"
        ps.changeInfo(name.textProperty().getValue(),familyName.textProperty().getValue(),password.textProperty().getValue(),telephone.textProperty().getValue(),cardNumber.textProperty().getValue(),svc.textProperty().getValue());
        ps.changeImage(m.getImage());
        IPC_FXMLCore.setRoot(root);}
    }
    
    private void checkPassword(){
        if(password.textProperty().getValue().length()<6) {
            manageError(errorPassword, password, validPassword);
        }
        
        else {
            manageCorrect(errorPassword, password, validPassword);
        }
    }
    
    private void checkCreditCard ()  {
        if(cardNumber.textProperty().getValue().length()!=16) {
            manageError(errorCardNumber, cardNumber, validCreditCard);
        }
        
        else {
            manageCorrect(errorCardNumber, cardNumber, validCreditCard);
        }
    }
    
     private void checkSvc(){
        if(svc.textProperty().getValue().length()!=3) {
            manageError(errorSvc,svc,validSvc);}
           
        else {
            manageCorrect(errorSvc,svc,validSvc);
        }
    }
     
    private void checkName(){
        if(name.textProperty().getValue().length()==0) {
            manageError(nameRequired, name, validName);
        }
        
        else {
            manageCorrect(nameRequired, name, validName);
        }
    }
    
    private void checkSurname ()  {
        if(familyName.textProperty().getValue().length()==0) {
            manageError(surnameRequired, familyName, validSurname);
        }
        
        else {
            manageCorrect(surnameRequired, familyName, validSurname);
        }
    }
    
    private void checkTelephone(){
        if(telephone.textProperty().getValue().length()==0) {
            manageError(telephoneRequired, telephone, validTelephone);
        }
        
        else {
            manageCorrect(telephoneRequired, telephone, validTelephone);
        }
    }
    
    private void showPassword() {
        Point2D p = password.localToScene(password.getBoundsInLocal().getMaxX(), password.getBoundsInLocal().getMaxY());
            tooltipP.setText(password.getText());
            tooltipP.show(password,
                p.getX() + password.getScene().getX() + password.getScene().getWindow().getX(),
                p.getY() + password.getScene().getY() + password.getScene().getWindow().getY());
    }
    
    private void hidePassword() {
        tooltipP.setText("");
        tooltipP.hide();
    }
    
    private void showSvc() {
        Point2D p = svc.localToScene(svc.getBoundsInLocal().getMaxX(), svc.getBoundsInLocal().getMaxY());
            tooltipS.setText(svc.getText());
            tooltipS.show(svc,
                p.getX() + svc.getScene().getX() + svc.getScene().getWindow().getX(),
                p.getY() + svc.getScene().getY() + svc.getScene().getWindow().getY());
    }
    
    private void hideSvc() {
        tooltipS.setText("");
        tooltipS.hide();
    }

    @FXML
    private void visibilityPassword(MouseEvent event) throws FileNotFoundException {
        if(showPassword.getValue().equals(Boolean.FALSE)) {
           showPassword.setValue(Boolean.TRUE);
           String showEyeURL = "src/images/eye2.png"; 
            Image showEye=new Image(new FileInputStream(showEyeURL));
            eyePassword.imageProperty().setValue(showEye);
            return;
        }
        else {
            String showEyeURL = "src/images/eye1.png"; 
            Image showEye=new Image(new FileInputStream(showEyeURL));
            eyePassword.imageProperty().setValue(showEye);
           showPassword.setValue(Boolean.FALSE);
           
        }
        
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
    
}
