/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import ipc_fxmlcore.IPC_FXMLCore;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author fbast
 */
public class LoginController implements Initializable {

    private BooleanProperty validPassword;
    
    @FXML
    private PasswordField password;
    @FXML
    private TextField nickname;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;
    @FXML
    private Label unvalidNickname;
    @FXML
    private Label unvalidPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        loginButton.setDisable(true);
        
        nickname.textProperty().addListener((observable, oldValue, newValue) -> {checkFieldsAndEnableButton();});
        password.textProperty().addListener((observable, oldValue, newValue) -> {checkFieldsAndEnableButton();});
    
        unvalidNickname.visibleProperty().set(false);
        unvalidPassword.visibleProperty().set(false);
        
        password.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkPassword();
        }});
        
        validPassword = new SimpleBooleanProperty();
        validPassword.setValue(Boolean.TRUE);
    }    

    @FXML
    private void loginClicked(ActionEvent event) throws ClubDAOException, IOException {
        Club club = Club.getInstance();
        Member member = club.getMemberByCredentials(nickname.getText(), password.getText());
        if(member == null){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText(null);
            alert.setContentText("The given profile is not registered");
            
            ButtonType regButton = new ButtonType("Register");
            ButtonType retryButton = new ButtonType("Retry");
            alert.getButtonTypes().setAll(regButton, retryButton);
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()){
                if(result.get() == regButton){
                    FXMLLoader myFXMLLoader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
                    Parent root = myFXMLLoader.load();
                    IPC_FXMLCore.setRoot(root);
                }
            }
            }else{
            User u=User.getInstance();
            u.setPassword(password.textProperty().getValue());
            u.setNickname(nickname.textProperty().getValue());
            FXMLLoader loader= new  FXMLLoader(getClass().getResource("/views/mainView.fxml"));
            Parent root = loader.load();
            IPC_FXMLCore.setRoot(root);
        }
    }
    
    private void checkFieldsAndEnableButton() {
        if (!nickname.getText().isEmpty() && !password.getText().isEmpty()) {
            loginButton.setDisable(false);
        } else {
            loginButton.setDisable(true);
        }
    }
    
    private void showErrorMessage(Label userMessage)
    {
        userMessage.visibleProperty().set(true);   
    }
    
    private void hideErrorMessage(Label userMessage)
    {
        userMessage.visibleProperty().set(false);
    }
    
    @FXML
    private void registerClicked(ActionEvent event) throws IOException{
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("/views/mainView.fxml"));
        Parent root = loader.load();
        IPC_FXMLCore.setRoot(root);
    }
    
    @FXML
    private void backClicked(ActionEvent event) throws IOException{
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("/views/mainView.fxml"));
        Parent root = loader.load();
        IPC_FXMLCore.setRoot(root);
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
    
    private void manageError(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.FALSE);
        showErrorMessage(errorLabel,textField);
        textField.requestFocus();
    }
    
    private void manageCorrect(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.TRUE);
        hideErrorMessage(errorLabel,textField);
    }
    
    private void checkPassword(){
        if(password.textProperty().getValue().length()<6) {
            manageError(unvalidPassword, password, validPassword);
        }
        
        else {
            manageCorrect(unvalidPassword, password, validPassword);
        }
    }
}


