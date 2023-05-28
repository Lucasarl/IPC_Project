/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import ipc_fxmlcore.IPC_FXMLCore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import model.Club;
import model.ClubDAOException;

/**
 * FXML Controller class
 *
 * @author win
 */
public class SignUpController implements Initializable {

    @FXML
    private HBox title;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private Label nickNameError;
    @FXML
    private TextField nickNameTF;
    @FXML
    private Label phoneError;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private Label passwordError;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Label passwordConfirmationError;
    @FXML
    private PasswordField passwordConfirmationTF;
    @FXML
    private Label creditCardError;
    @FXML
    private TextField creditCardTF;
    @FXML
    private Label cvcError;
    @FXML
    private TextField cscTF;
    @FXML
    private Button cancelButton;
    @FXML
    private Button signUpButton;

    
    private BooleanProperty validPassword;
    
    private BooleanProperty validCreditCard;
    
    private BooleanProperty validSvc;  
    
    private BooleanProperty validName;
    
    private BooleanProperty validSurname;
    
    private BooleanProperty validTelephone;
    
    private BooleanProperty validNickname;
    
    private BooleanProperty validPasswordConfirmation;
    @FXML
    private Label firstNameError;
    @FXML
    private Label lastNameError;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        validPassword = new SimpleBooleanProperty(); validPassword.setValue(Boolean.FALSE);
        
        validCreditCard = new SimpleBooleanProperty(); validCreditCard.setValue(Boolean.TRUE);
        
        validSvc = new SimpleBooleanProperty();validSvc.setValue(Boolean.TRUE);
        
        validName = new SimpleBooleanProperty(); validName.setValue(Boolean.FALSE);
        
        validSurname = new SimpleBooleanProperty(); validSurname.setValue(Boolean.FALSE);
        
        validTelephone = new SimpleBooleanProperty(); validTelephone.setValue(Boolean.FALSE);
        
        validNickname = new SimpleBooleanProperty(); validNickname.setValue(Boolean.FALSE);
        
        validPasswordConfirmation = new SimpleBooleanProperty(); validPasswordConfirmation.setValue(Boolean.FALSE);
        
        signUpButton.setDisable(true);
        
        firstNameTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && firstNameTF.textProperty().getValue().equals("")){
                validName.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                firstNameError.setText("You must fill this part");
            }
            else{
                validName.setValue(Boolean.TRUE);
                firstNameError.setText("*");
                checkFieldsAndEnableButton();
            }
        });
        lastNameTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && lastNameTF.textProperty().getValue().equals("")){
                validSurname.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                lastNameError.setText("You must fill this part");
            }
            else{
                validSurname.setValue(Boolean.TRUE);
                lastNameError.setText("*");
                checkFieldsAndEnableButton();
            }
        });
        
        nickNameTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            try{
                if(!newValue && nickNameTF.textProperty().getValue().equals("")){
                    validNickname.setValue(Boolean.FALSE);
                    signUpButton.setDisable(true);
                    nickNameError.setText("You must fill this part");
                }
                else if(!newValue &&
                        !validNickName(nickNameTF.textProperty().getValue())){
                    validNickname.setValue(Boolean.FALSE);                    
                    signUpButton.setDisable(true);
                    nickNameError.setText("Nickname used");
                }
                else{
                    validNickname.setValue(Boolean.TRUE);
                    nickNameError.setText("*");
                    checkFieldsAndEnableButton();
                }
            }catch(Exception e){System.out.println(e.getMessage());}
            
        });
        
        phoneNumberTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && phoneNumberTF.textProperty().getValue().equals("")){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("You must fill this part");
            }
            else if(!newValue && containsLetter(phoneNumberTF.textProperty().getValue())){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("Only numbers are allowed");
            }
            else if(!newValue && phoneNumberTF.textProperty().getValue().length()!=9){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("Your number can have 9 digits");
            }
            else{
                validTelephone.setValue(Boolean.TRUE);
                phoneError.setText("*");
                checkFieldsAndEnableButton();
            }
        });
        
        passwordTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && passwordTF.textProperty().getValue().equals("")){
                validPassword.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordError.setText("You must fill this part");
            }
            
            else if(!newValue && passwordTF.textProperty().getValue().length()<6){
                validPassword.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordError.setText("You need at least 6 characters");
            }
            else{
                validPassword.setValue(Boolean.TRUE);
                passwordError.setText("*");
                checkFieldsAndEnableButton();
            }
        });
        
        passwordConfirmationTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && passwordConfirmationTF.textProperty().getValue().equals("")){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordConfirmationError.setText("You must fill this part");
            }
            else if(!newValue && !passwordConfirmationTF.textProperty().getValue().equals(passwordTF.textProperty().getValue())){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordConfirmationError.setText("Passwords must be equal");
            }
            else{
                validPasswordConfirmation.setValue(Boolean.TRUE);
                passwordConfirmationError.setText("*");
                checkFieldsAndEnableButton();
            }
        });
    }
    
    private boolean validNickName(String nickname)throws ClubDAOException, IOException{
        Club club = Club.getInstance();
        if(!club.existsLogin(nickname)) {
            return true;
        } 
        return false;
    }
    
    private int specialLength(String s){
        int res = 0;
        for(int i = 0; i<s.length(); i++){
            if(!Character.isWhitespace(s.charAt(i))){
                res++;
            }
        }
        return res;
    }
    private boolean containsLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    
    private void checkFieldsAndEnableButton() {
        if(validPassword.getValue() &&
           validName.getValue() &&
           validSurname.getValue() &&
           validTelephone.getValue() &&
           validNickname.getValue() &&
           validPasswordConfirmation.getValue()){
        
           signUpButton.setDisable(false); 
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/views/initialPage.fxml"));
        Parent root= loader.load();
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void handleSignUp(ActionEvent event) throws ClubDAOException, IOException{
        
        Club club = Club.getInstance();
        if(cscTF.textProperty().getValue().equals("")){
            cscTF.textProperty().setValue("000");
        
        }
        club.registerMember(
                firstNameTF.textProperty().getValue(),
                lastNameTF.textProperty().getValue(),
                phoneNumberTF.textProperty().getValue(),
                nickNameTF.textProperty().getValue(),
                passwordTF.textProperty().getValue(),
                 null,
                000,
                (Image)null);
        
        System.out.println(club.existsLogin(nickNameTF.textProperty().getValue()));
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/views/login.fxml"));
        Parent root= loader.load();
        IPC_FXMLCore.setRoot(root);
    }
    
}
