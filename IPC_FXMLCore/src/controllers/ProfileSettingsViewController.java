/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import model.*;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProfileSettingsViewController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label familyName;
    @FXML
    private Label password;
    @FXML
    private Label nickname;
    @FXML
    private Label cardNumber;
    @FXML
    private Label svc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO2
        Image avatar=null;
        try {
        Club c=Club.getInstance();
        c.setInitialData(); //REINICIA LOS DATOS DEL CLUB
        String nickName="Ntonio";
        String passwordMember="erewrqdc";
        c.registerMember("Pedro","Antonio Palillo","643213454","Ntonio","erewrqdc","5402056301030199",321,avatar); 
        //registerMember debería usarse solo una vez en signup (si no various usuarios tendran el mismo nombre de usuario, yo solo lo uso aqui como prueba )
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        System.out.println(c.existsLogin("Ntonio"));
        name.textProperty().setValue(m.getName());
        familyName.textProperty().setValue(m.getSurname());
        password.textProperty().setValue(m.getPassword());
        nickname.textProperty().setValue(m.getNickName());
        cardNumber.textProperty().setValue(m.getCreditCard());
        String stringSvc=Integer.toString(m.getSvc());
        svc.textProperty().setValue(stringSvc);
        
        }
        
        catch(Exception e) {
            
        }
    }    
    
}
