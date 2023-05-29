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
public class ChangeProfileInfoNocardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    
    private BooleanProperty validPassword;
    private BooleanProperty validName;
    private BooleanProperty validSurname;
    private BooleanProperty validTelephone;
    private Tooltip tooltip;
    
    private boolean hidePassword;
    @FXML
    private TextField name;
    @FXML
    private TextField familyName;
    @FXML
    private TextField password;
    private TextField cardNumber;
    private TextField svc;
    private BooleanProperty showPassword;
    public Member m;
    @FXML
    private Label errorPassword;
    @FXML
    private Button update;
    @FXML
    private Label nameRequired;
    @FXML
    private Label surnameRequired;
    @FXML
    private ImageView eyePassword;
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
        
        // ENTER SHORTCUTS
        
        
        name.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 System.out.println((!errorPassword.isVisible() && !nameRequired.isVisible() && !surnameRequired.isVisible() &&!telephoneRequired.isVisible()));
                 checkName();
                 update.fire();
                 
}                System.out.println((!errorPassword.isVisible() && !nameRequired.isVisible() && !surnameRequired.isVisible() &&!telephoneRequired.isVisible()));
         }
  
 );
          familyName.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 checkSurname();
                 update.fire();
                
}
         }
  
 );
           password.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 checkPassword();
                 update.fire();
                 
}
         }
  
 );
            telephone.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                     checkTelephone();
                     update.fire();
                
}
         }
  
 );
        
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
        
        hidePassword=true;
        validPassword = new SimpleBooleanProperty();
        validName= new SimpleBooleanProperty();
        validSurname=new SimpleBooleanProperty();
        validTelephone=new  SimpleBooleanProperty();
        validPassword.setValue(Boolean.TRUE);
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
        
        tooltip = new Tooltip();
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setAutoHide(false);
        tooltip.setMinWidth(50);
    }    
    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(m.getImage());
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void updateInfo(ActionEvent event) throws IOException, ClubDAOException {
         //if((validPassword.getValue().equals(Boolean.TRUE) && validName.getValue().equals(Boolean.TRUE) && validSurname.getValue().equals(Boolean.TRUE) && validTelephone.getValue().equals(Boolean.TRUE))){
         updateInfo();
    }
    
    private void updateInfo() throws IOException, ClubDAOException {
        if((!errorPassword.isVisible() && !nameRequired.isVisible() && !surnameRequired.isVisible()) &&!telephoneRequired.isVisible()){
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
        alert.setContentText("Your profile has been updated");
        alert.showAndWait();
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        User u=User.getInstance();
        Club c=Club.getInstance();
        Member m=c.getMemberByCredentials(u.getNickname(), u.getPassword());
        m.setPassword(password.textProperty().getValue());
        u.setPassword(password.textProperty().getValue());
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        //ps.loginInfo(m.getNickName(), m.getPassword());
        //System.out.println(m.getPassword());
        //SI CAMBIAS LA CONTRASEÑA PETA PORQUE ESTOY USANDO UN USUARIO EJEMPLO "A LA FUERZA"
        ps.changeInfo(name.textProperty().getValue(),familyName.textProperty().getValue(),password.textProperty().getValue(),telephone.textProperty().getValue());
        //ps.changeImage(m.getImage());
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
   
     
    private void checkName(){
        if(name.textProperty().getValue().length()==0) {
            manageError(nameRequired, name, validName);
        }
        
        else {
            manageCorrect(nameRequired, name, validName);
        }
    }
    
    private void checkTelephone(){
        if(telephone.textProperty().getValue().length()!=9) {
            manageError(telephoneRequired, telephone, validTelephone);
        }
        
        else {
            manageCorrect(telephoneRequired, telephone, validTelephone);
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

    @FXML
    private void passwordVisibility(MouseEvent event) throws FileNotFoundException {
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
    
    private void showPassword() {
        Point2D p = password.localToScene(password.getBoundsInLocal().getMaxX(), password.getBoundsInLocal().getMaxY());
            tooltip.setText(password.getText());
            tooltip.show(password,
                p.getX() + password.getScene().getX() + password.getScene().getWindow().getX(),
                p.getY() + password.getScene().getY() + password.getScene().getWindow().getY());
    }
    
    private void hidePassword() {
        tooltip.setText("");
        tooltip.hide();
    }
    
    
}

