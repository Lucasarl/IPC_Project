/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import ipc_fxmlcore.IPC_FXMLCore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author USER
 */

//MUY IMPORTANTE, SI CAMBIAS LA CONTRASEÑA PETA PORQUE PARA EXPERIMENTAR SIEMPRE REINICIO LAA BASE DE DATOS DE MOMENTO EN INITIALIZE
public class ProfileSettingsViewNocardController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button changeProf;
    @FXML
    private HBox hBox;
    @FXML
    private Label name;
    @FXML
    private Label familyName;
    @FXML
    private Label nickname;
    @FXML
    private Label maskedPassword;
    @FXML
    private Label password;
    private Label maskedCardNumber;
    private Label cardNumber;
    private Label maskedSvc;
    private Label svc;
    @FXML
    private ImageView eyePassword;
    @FXML
    private VBox vBox;
    @FXML
    private ImageView image;
    
     private boolean passwordHidden;
     
     public Club c;
    String nickName;//PARAMETROS PASADOS DEL LOGIN
    String passwordMember;
    @FXML
    private Label telephoneNumber;

   //METODO PARA ACTUALIZAR IMAGEN
    public void changeImage(Image i){
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        m.setImage(i);
        image.imageProperty().setValue(m.getImage());
    }
    
    
    //METODO PARA ACTUALIZAR DATOS
    public void changeInfo(String newName, String newFamilyName,String newPassword, String newTelephone){
       // System.out.println(nickName);
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        m.setName(newName);
        m.setSurname(newFamilyName);
        m.setPassword(newPassword);
        m.setTelephone(newTelephone);
        
        name.textProperty().setValue(m.getName());
        familyName.textProperty().setValue(m.getSurname());
        password.textProperty().setValue(m.getPassword());
        telephoneNumber.textProperty().setValue(m.getTelephone());
        
        passwordMember=newPassword;
    }
    
    //for getting login info, to later get member by credentials
    public void loginInfo(String n, String p) {
        nickName=n;
        passwordMember=p;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO2
        User u=User.getInstance();
        passwordMember=u.getPassword();
        nickName=u.getNickname();
        try {
        
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
        
        makeResizable();
        c=Club.getInstance();
        //c.setInitialData(); //REINICIA LOS DATOS DEL CLUB
       // nickName="Ntonio";
       // passwordMember="erewrqdc";
       // String urlImage = "src/images/men.PNG"; 
       // Image avatar=new Image(new FileInputStream(urlImage));
        // Si usamos, Image avatar=null, muestra default.png;
        //c.registerMember("Pedro","Antonio Palillo","643213454","Ntonio","erewrqdc",null, 0,avatar);
        //c=Club.getInstance();
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        //System.out.println(c.existsLogin("Ntonio"));
        name.textProperty().setValue(m.getName());
        familyName.textProperty().setValue(m.getSurname());
        password.textProperty().setValue(m.getPassword());
        nickname.textProperty().setValue(m.getNickName());
        image.imageProperty().setValue(m.getImage());
        telephoneNumber.textProperty().setValue(m.getTelephone());
        //PASSWORD MASKING
        
        password.setVisible(false);
        
        
        
        
        
        }
        catch(Exception e) {
            
        }
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException, ClubDAOException {
        //back to main screen
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/mainView.fxml"));
        Parent root=myLoader.load();
        MainViewController main=myLoader.getController();
        Club c=Club.getInstance();
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        //System.out.println(nickName);
        main.loginInfo(nickName, passwordMember);
        //main.setImage(m.getImage()); // para luego
        main.setImage(image.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void changeProfileInfo(ActionEvent event) throws IOException, ClubDAOException {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/changeProfileInfoNocard.fxml"));
        Parent root=myLoader.load();
        ChangeProfileInfoNocardController cpi=myLoader.getController();
        cpi.initMember(nickName,passwordMember);
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void changeProfilePicture(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
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
           new FileChooser.ExtensionFilter("Images", "*.png"));
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
       else{
           
       }
       //System.out.println("Cancel");
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
    private void addCard(ActionEvent event) throws ClubDAOException, IOException {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/addCard.fxml"));
        Parent root=myLoader.load();
        AddCardController a=myLoader.getController();
        a.initMember(nickName,passwordMember);
        a.setFromProfile(true);
        IPC_FXMLCore.setRoot(root);
    }


}
