/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;


import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO2
        try {
        Club c=Club.getInstance();
        c.setInitialData(); //REINICIA LOS DATOS DEL CLUB
        String nickName="Ntonio";
        String passwordMember="erewrqdc";
        String urlImage = "src/images/men.PNG";
        Image avatar=new Image(new FileInputStream(urlImage));
        // Si usamos, Image avatar=null, muestra default.png;
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
        image.imageProperty().setValue(m.getImage());
        
        }
        
        catch(Exception e) {
            
        }
    }    
    
}
