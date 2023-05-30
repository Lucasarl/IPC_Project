/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;


import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.*;
import controllers.ChangeProfilePictureController;
import ipc_fxmlcore.IPC_FXMLCore;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProfileSettingsViewController implements Initializable  {

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

    public Club c;
    String nickName;//PARAMETROS PASADOS DEL LOGIN
    String passwordMember;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button changeProf;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;
    
    private boolean passwordHidden;
    private boolean cardHidden;
    private boolean svcHidden;
    @FXML
    private ImageView eyePassword;
    @FXML
    private ImageView eyeCardNumber;
    @FXML
    private ImageView eyeSvc;
    @FXML
    private Label maskedPassword;
    @FXML
    private Label maskedCardNumber;
    @FXML
    private Label maskedSvc;
    @FXML
    private Label telephoneNumber;
    /**
     * Initializes the controller class.
     */
    
    //METODO PARA ACTUALIZAR IMAGEN
    public void changeImage(Image i){
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        m.setImage(i);
        image.imageProperty().setValue(m.getImage());
    }
    
    public void setInvisible() {
        password.setVisible(false);
        cardNumber.setVisible(false);
        svc.setVisible(false);
        
    }
    //METODO PARA ACTUALIZAR DATOS
    public void changeInfo(String newName, String newFamilyName,String newPassword, String newTelephone, String newCreditCard, String newSvc){
        //System.out.println(passwordMember);
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        m.setName(newName);
        m.setSurname(newFamilyName);
        m.setPassword(newPassword);
        m.setCreditCard(newCreditCard);
        m.setSvc(Integer.parseInt(newSvc));
        String stringSvc=Integer.toString(m.getSvc());
        if(m.getSvc()==0){
            svc.textProperty().setValue("000");
        } else if(stringSvc.length()==2) {
            svc.textProperty().setValue("0"+stringSvc);}
        else if(stringSvc.length()==1) {
            svc.textProperty().setValue("00"+stringSvc);
        }
        else {
            svc.textProperty().setValue(stringSvc);
        }
        m.setTelephone(newTelephone);
        name.textProperty().setValue(m.getName());
        familyName.textProperty().setValue(m.getSurname());
        nickname.textProperty().setValue(m.getNickName());
        password.textProperty().setValue(m.getPassword());
        telephoneNumber.textProperty().setValue(m.getTelephone());
        cardNumber.textProperty().setValue(m.getCreditCard());
        passwordMember=newPassword;
    }
    
    //for getting login info, to later get member by credentials, ALWAYS PASS NICKNAME AND PASSWORD BEFORE RETURNING TO THIS SCENE
    public void loginInfo(String n, String p) {
        nickName=n;
        passwordMember=p;
        setInvisible();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO2
        try {
        c=Club.getInstance();
        //PASSWORD MASKING, CARD MASKING, AND SECURITY NUMBER MASKING
            
        password.textProperty().addListener((v,oldVal,newVal)->{
            String s="";
            for(int i=0;i<newVal.length();i++){
                s+="*";
            }
            maskedPassword.textProperty().setValue(s);
        });
        
        password.visibleProperty().addListener((v,oldVal,newVal)->{
            if(password.isVisible()) {
                maskedPassword.textProperty().setValue("");
            } else {
                String s="";
                for(int i=0;i<password.textProperty().getValue().length();i++){
                      s+="*";
            }
            maskedPassword.textProperty().setValue(s);
            }
        });
        
         cardNumber.textProperty().addListener((v,oldVal,newVal)->{
            if(cardNumber!=null && cardNumber.textProperty().getValue().length()>4){
            String s=newVal.substring(0, 4);
            for(int i=4;i<newVal.length();i++){
                s+="*";
            }
            maskedCardNumber.textProperty().setValue(s);
        }});
         
         cardNumber.visibleProperty().addListener((v,oldVal,newVal)->{
            if(cardNumber!=null && cardNumber.textProperty().getValue().length()>4){
            if(cardNumber.isVisible()) {
                maskedCardNumber.textProperty().setValue("");
            } else {
                String s=cardNumber.textProperty().getValue().substring(0, 4);
                for(int i=4;i<cardNumber.textProperty().getValue().length();i++){
                      s+="*";
            }
            maskedCardNumber.textProperty().setValue(s);
            }}
        });
        
         svc.textProperty().addListener((v,oldVal,newVal)->{
             if(svc!=null){
            String s="";
            for(int i=0;i<newVal.length();i++){
                s+="*";
            }
            maskedSvc.textProperty().setValue(s);
        }});
        
        svc.visibleProperty().addListener((v,oldVal,newVal)->{
            if(svc!=null){
            if(svc.isVisible()) {
                maskedSvc.textProperty().setValue("");
            } else {
                String s="";
                for(int i=0;i<svc.textProperty().getValue().length();i++){
                      s+="*";
            }
            maskedSvc.textProperty().setValue(s);
            }
        }});
        User u=User.getInstance();
        nickName=u.getNickname();
        passwordMember=u.getPassword();
        makeResizable();
        c=Club.getInstance();
        //c.setInitialData(); //REINICIA LOS DATOS DEL CLUB
        //nickName="Ntonio";
        //passwordMember="erewrqdc";
        //String urlImage = "src/images/men.PNG"; 
        //Image avatar=new Image(new FileInputStream(urlImage));
        // Si usamos, Image avatar=null, muestra default.png;
        //c.registerMember("Pedro","Antonio Palillo","643213454","Ntonio","erewrqdc","5402123478659807",321,avatar);
        //registerMember debería usarse solo una vez en signup (si no various usuarios tendran el mismo nombre de usuario, yo solo lo uso aqui como prueba )
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        //System.out.println(c.existsLogin("Ntonio"));
        name.textProperty().setValue(m.getName());
        familyName.textProperty().setValue(m.getSurname());
        password.textProperty().setValue(m.getPassword());
        nickname.textProperty().setValue(m.getNickName());
        cardNumber.textProperty().setValue(m.getCreditCard());
        telephoneNumber.textProperty().setValue(m.getTelephone());
        String stringSvc=Integer.toString(m.getSvc());
        svc.textProperty().setValue(stringSvc);
        image.imageProperty().setValue(m.getImage());
        
        //PASSWORD MASKING
        
        password.setVisible(false);
        cardNumber.setVisible(false);
        svc.setVisible(false);
        
        
        
        
        }
        catch(Exception e) {
            
        }
    }    

    @FXML
    private void goBack(ActionEvent event) throws ClubDAOException, IOException {
        //back to main screen
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/mainView.fxml"));
        Parent root=myLoader.load();
        MainViewController main=myLoader.getController();
        Club c=Club.getInstance();
        Member m=c.getMemberByCredentials(nickName,passwordMember);
       // System.out.println(nickName);
        main.loginInfo(nickName, passwordMember);
        //main.setImage(m.getImage()); // para luego
        main.setImage(image.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
    }

    public String  returnNickname(){
        return nickName;
    }
    
    public String returnPassword() {
        return passwordMember;
    }
    @FXML
    private void changeProfileInfo(ActionEvent event) throws IOException, ClubDAOException {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/changeProfileInfo.fxml"));
        Parent root=myLoader.load();
        ChangeProfileInfoController cpi=myLoader.getController();
        cpi.initMember(nickName,passwordMember); //
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void changeProfilePicture(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
         Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/images/Logo.png").toString()));
        //Cambia el icono por uno propio
        //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(this.getClass().getResourceAsStream("images/icon.png")));
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
      getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("myAlert");
        alert.setTitle("Change profile picture");
        alert.setHeaderText("Would you like to browse for a profile picture on your computer or use one of our custom images?");
        alert.setContentText("Choose an option");
        ButtonType buttonTypeBrowse = new ButtonType("Browse");
        ButtonType buttonTypeSelect = new ButtonType("Custom images");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeBrowse, buttonTypeSelect, buttonTypeCancel);
        alert.getDialogPane().getChildren().forEach(node -> {
    if (node instanceof ButtonBar) {
        ButtonBar buttonBar = (ButtonBar) node;
        buttonBar.getButtons().forEach(possibleButtons -> {
            if (possibleButtons instanceof Button) {
                Button b = (Button) possibleButtons;
                if (b.getText().equals("Cancel")) {
                    b.getStyleClass().add("cancel");
                }
            }
        });
    }
});
       Optional<ButtonType> result = alert.showAndWait();
       if (result.isPresent()) {
       if (result.get() == buttonTypeBrowse){
           FileChooser fileChooser = new FileChooser();
           fileChooser.setTitle("Open resource");
           fileChooser.getExtensionFilters().addAll(
           new ExtensionFilter("Images", "*.png"));
           File selectedFile = fileChooser.showOpenDialog(
     ((Node)event.getSource()).getScene().getWindow());
           if (selectedFile != null) {
                String url = selectedFile.getPath();
                Image avatar = new Image(new FileInputStream(url));
                image.imageProperty().setValue(avatar); 
                Member m=c.getMemberByCredentials(nickName,passwordMember);
                m.setImage(avatar);
            } 
          
       }
       else if (result.get() == buttonTypeSelect){
            //Cambiamos de escena 
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/changeProfilePicture.fxml"));
        Parent root=myLoader.load();
        ChangeProfilePictureController cp=myLoader.getController();
        cp.initMember(nickName,passwordMember);
        IPC_FXMLCore.setRoot(root);
        
       }
       else {
           
       }
       
}
    }
    

    private void makeResizable() {
       
        
    }

    @FXML
    private void visibilityPassword(MouseEvent event) throws FileNotFoundException {
        if(!password.isVisible()) {
            String showEyeURL = "src/images/eye2.png"; 
            Image showEye=new Image(new FileInputStream(showEyeURL));
            eyePassword.imageProperty().setValue(showEye);
            maskedPassword.setVisible(false);
            password.setVisible(true);
        } else {
            String hiddenEyeURL = "src/images/eye1.png"; 
            Image hiddenEye=new Image(new FileInputStream(hiddenEyeURL));
            eyePassword.imageProperty().setValue(hiddenEye);
            maskedPassword.setVisible(true);
            password.setVisible(false);
        }
        
    }

    @FXML
    private void visibilityCardNumber(MouseEvent event) throws FileNotFoundException {
        if(!cardNumber.isVisible()) {
            String showEyeURL = "src/images/eye2.png"; 
            Image showEye=new Image(new FileInputStream(showEyeURL));
            eyeCardNumber.imageProperty().setValue(showEye);
            maskedCardNumber.setVisible(false);
            cardNumber.setVisible(true);
        } else {
            String hiddenEyeURL = "src/images/eye1.png"; 
            Image hiddenEye=new Image(new FileInputStream(hiddenEyeURL));
            eyeCardNumber.imageProperty().setValue(hiddenEye);
            maskedCardNumber.setVisible(true);
            cardNumber.setVisible(false);
        }
    }

    @FXML
    private void visibilitySvc(MouseEvent event) throws FileNotFoundException {
        if(!svc.isVisible()) {
            String showEyeURL = "src/images/eye2.png"; 
            Image showEye=new Image(new FileInputStream(showEyeURL));
            eyeSvc.imageProperty().setValue(showEye);
            maskedSvc.setVisible(false);
            svc.setVisible(true);
        } else {
            String hiddenEyeURL = "src/images/eye1.png"; 
            Image hiddenEye=new Image(new FileInputStream(hiddenEyeURL));
            eyeSvc.imageProperty().setValue(hiddenEye);
            maskedSvc.setVisible(true);
            svc.setVisible(false);
        }
    }

    @FXML
    private void removeCard(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.WARNING);
         Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/images/Logo.png").toString()));
        //Cambia el icono por uno propio
        //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(this.getClass().getResourceAsStream("images/icon.png")));
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
      getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("myAlert");
        alert.setTitle("Remove credit card");
        alert.setHeaderText("Are you sure you want to remove your credit card?");
        alert.setContentText(null);
        ButtonType buttonTypeRemove = new ButtonType("Remove Card");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeRemove, buttonTypeCancel);
        alert.getDialogPane().getChildren().forEach(node -> {
    if (node instanceof ButtonBar) {
        ButtonBar buttonBar = (ButtonBar) node;
        buttonBar.getButtons().forEach(possibleButtons -> {
            if (possibleButtons instanceof Button) {
                Button b = (Button) possibleButtons;
                if (b.getText().equals("Cancel")) {
                    b.getStyleClass().add("cancel");
                }
            }
        });
    }
});
       Optional<ButtonType> result = alert.showAndWait();
       if (result.isPresent()) {
       if (result.get() == buttonTypeRemove){
           Member m=c.getMemberByCredentials(nickName,passwordMember);
           List<Booking> b=c.getUserBookings(m.getNickName());
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(nickName,passwordMember);
        //System.out.println(passwordMember);
        ps.changeInfo(name.textProperty().getValue(), familyName.textProperty().getValue(), passwordMember, telephoneNumber.textProperty().getValue());
        ps.changeImage(image.imageProperty().getValue());
        m.setCreditCard(null);
        IPC_FXMLCore.setRoot(root);
          
       }
}
    }
}
