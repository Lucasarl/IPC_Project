/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;
//#29a61ed1
import ipc_fxmlcore.IPC_FXMLCore;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    
    @FXML
    private TextField name;
    @FXML
    private TextField familyName;
    @FXML
    private TextField password;
    private TextField cardNumber;
    private TextField svc;

    public Member m;
    @FXML
    private Label errorPassword;
    @FXML
    private Button update;
    @FXML
    private Label nameRequired;
    @FXML
    private Label surnameRequired;
    
    //LOADS PROFILE DETAILS
    public void initMember(String nickName, String password) throws ClubDAOException, IOException {
        Club c=Club.getInstance();
        m=c.getMemberByCredentials(nickName, password);
        name.textProperty().setValue(m.getName());
        familyName.textProperty().setValue(m.getSurname());
        this.password.textProperty().setValue(m.getPassword());
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
        validPassword = new SimpleBooleanProperty();
        validName= new SimpleBooleanProperty();
        validSurname=new SimpleBooleanProperty();
        
        validPassword.setValue(Boolean.TRUE);
        validName.setValue(Boolean.TRUE);
        validSurname.setValue(Boolean.TRUE);
        
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


    }    
    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword());
        ps.changeImage(m.getImage());
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void updateInfo(ActionEvent event) throws IOException {
        if((!errorPassword.isVisible() && !nameRequired.isVisible() && !surnameRequired.isVisible())){
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
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.loginInfo(m.getNickName(), m.getPassword());
        //System.out.println(m.getPassword());
        //SI CAMBIAS LA CONTRASEÑA PETA PORQUE ESTOY USANDO UN USUARIO EJEMPLO "A LA FUERZA"
        ps.changeInfo(name.textProperty().getValue(),familyName.textProperty().getValue(),password.textProperty().getValue());
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
    
}

