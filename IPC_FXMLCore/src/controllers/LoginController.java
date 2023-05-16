/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

    @FXML
    private Text userMessage;
    @FXML
    private PasswordField password;
    @FXML
    private TextField nickname;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButtton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loginButton.setDisable(true);
        
        nickname.textProperty().addListener((observable, oldValue, newValue) -> {checkFieldsAndEnableButton();});
        password.textProperty().addListener((observable, oldValue, newValue) -> {checkFieldsAndEnableButton();});
    
        userMessage.visibleProperty().set(false);
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
            
            ButtonType registerButton = new ButtonType("Regsiter");
            ButtonType retryButton = new ButtonType("Retry");
            alert.getButtonTypes().setAll(registerButton, retryButton);
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()){
                if(result.get() == registerButton){
                    FXMLLoader myFXMLLoader = new FXMLLoader(getClass().getResource("/view/register.fxml"));
                    Parent root = myFXMLLoader.load();
                }
            }
            }else{
                nickname.getScene().getWindow().hide();
                FXMLLoader myFXMLLoader = new FXMLLoader(getClass().getResource("/view/mainMenu.fxml"));
                Parent root = myFXMLLoader.load();
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

}
