/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import ipc_fxmlcore.IPC_FXMLCore;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
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
    private BooleanProperty validNickname;
    
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
    @FXML
    private ImageView eyePassword;
    
    private BooleanProperty showPassword;
    private Tooltip tooltip;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nickname.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 try {
                     checkNickname();
                 } catch (ClubDAOException ex) {
                     Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException ex) {
                     Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 loginButton.fire();
}
         }
  
 );
         password.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 try {
                     checkPassword();
                 } catch (ClubDAOException ex) {
                     Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException ex) {
                     Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 loginButton.fire();
}
         }
  
 );   
        
        loginButton.setDisable(true);
        
        nickname.textProperty().addListener((observable, oldValue, newValue) -> {checkFieldsAndEnableButton();});
        password.textProperty().addListener((observable, oldValue, newValue) -> {checkFieldsAndEnableButton();});
    
        unvalidNickname.visibleProperty().set(false);
        unvalidPassword.visibleProperty().set(false);
        
        password.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            try {
                checkPassword();
            } catch (ClubDAOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});
        
        nickname.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            try {
                checkNickname();
            } catch (ClubDAOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});
        
        validPassword = new SimpleBooleanProperty();
        validPassword.setValue(Boolean.TRUE);
        
        validNickname = new SimpleBooleanProperty();
        validNickname.setValue(Boolean.TRUE);
        
        showPassword= new SimpleBooleanProperty();
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
        
        tooltip = new Tooltip();
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setAutoHide(false);
        tooltip.setMinWidth(50);
    }    

    @FXML
    private void loginClicked(ActionEvent event) throws ClubDAOException, IOException {
        Club club = Club.getInstance();
        Member member = club.getMemberByCredentials(nickname.getText(), password.getText());
        if(!unvalidPassword.isVisible() && !unvalidNickname.isVisible() ) {
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
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root = loader.load();
        IPC_FXMLCore.setRoot(root);
    }
    
    @FXML
    private void backClicked(ActionEvent event) throws IOException{
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("/views/initialPage.fxml"));
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
    
    private void checkPassword() throws ClubDAOException, IOException{
        Club club = Club.getInstance();
        Member member = club.getMemberByCredentials(nickname.getText(), password.getText());
        if(password.textProperty().getValue().length()<6) {
            validPassword.setValue(Boolean.FALSE);
            unvalidPassword.setText("The minimum length a password is 6 characters");
            showErrorMessage(unvalidPassword,password);
            password.requestFocus();
        }
        else if(member == null){
            validPassword.setValue(Boolean.FALSE);
            unvalidPassword.setText("Incorrect password");
            showErrorMessage(unvalidPassword,password);
            password.requestFocus();
        }
        
        else {
            manageCorrect(unvalidPassword, password, validPassword);
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
    
    @FXML
    private void visibilityPassword(MouseEvent event) throws FileNotFoundException{
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
    
    private void checkNickname() throws ClubDAOException, IOException {
        Club club = Club.getInstance();
        if(!club.existsLogin(nickname.getText())) {
            manageError(unvalidNickname, nickname, validNickname);
        }
        
        else {
            manageCorrect(unvalidNickname, nickname, validNickname);
        }
    }
}


