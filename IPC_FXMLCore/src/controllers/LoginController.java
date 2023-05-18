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

    @FXML
    private Text userMessage;
    @FXML
    private PasswordField password;
    @FXML
    private TextField nickname;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Club c;
        try {
            c = Club.getInstance();
            c.setInitialData();
            Member m=c.registerMember("Pedro","Antonio Palillo","643213454","Ptonio","erewrqdc",null,321,null);
        } catch (ClubDAOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            
            ButtonType regButton = new ButtonType("Register");
            ButtonType retryButton = new ButtonType("Retry");
            alert.getButtonTypes().setAll(regButton, retryButton);
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()){
                if(result.get() == regButton){
                    FXMLLoader myFXMLLoader = new FXMLLoader(getClass().getResource("/view/register.fxml"));
                    Parent root = myFXMLLoader.load();
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
        /*FXMLLoader loader= new  FXMLLoader(getClass().getResource("/views/login.fxml"));
        Parent root = loader.load();
        //======================================================================
        // 2- creación de la escena con el nodo raiz del grafo de escena
        scene = new Scene(root);
        //======================================================================
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
        //     - se muestra el stage de manera no modal mediante el metodo show()
        stage.setScene(scene);
        stage.setMinWidth(700); //Hacer todas las escenas de este tamaño min
        stage.setMinHeight(500);
        stage.setTitle("profileSettingsView");
        //stage.setResizable(false);
        stage.show();
    */}


