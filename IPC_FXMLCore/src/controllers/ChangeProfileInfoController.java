/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        validCreditCard = new SimpleBooleanProperty();   
        validSvc = new SimpleBooleanProperty();
        
        validPassword.setValue(Boolean.TRUE);
        validCreditCard.setValue(Boolean.TRUE);
        validSvc.setValue(Boolean.TRUE);
        
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
    }    
    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.changeImage(m.getImage());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void updateInfo(ActionEvent event) throws IOException {
        if((!errorPassword.isVisible() && !errorCardNumber.isVisible() && !errorSvc.isVisible())){
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.changeImage(m.getImage());
        //SI CAMBIAS LA CONTRASEÑA PETA PORQUE ESTOY USANDO UN USUARIO EJEMPLO "A LA FUERZA"
        ps.changeInfo(name.textProperty().getValue(),familyName.textProperty().getValue(),password.textProperty().getValue(),cardNumber.textProperty().getValue(),svc.textProperty().getValue());
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
    
}
