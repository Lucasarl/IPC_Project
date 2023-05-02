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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
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
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.changeImage(m.getImage());
        //SI CAMBIAS LA CONTRASEÑA PETA PORQUE ESTOY USANDO UN USUARIO EJEMPLO "A LA FUERZA"
        ps.changeInfo(name.textProperty().getValue(),familyName.textProperty().getValue(),password.textProperty().getValue(),cardNumber.textProperty().getValue(),svc.textProperty().getValue());
        IPC_FXMLCore.setRoot(root);
    }
    
}
