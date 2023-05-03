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
import java.io.IOException;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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

    /**
     * Initializes the controller class.
     */
    
    //METODO PARA ACTUALIZAR IMAGEN
    public void changeImage(Image i){
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        m.setImage(i);
        image.imageProperty().setValue(m.getImage());
    }
    
    
    //METODO PARA ACTUALIZAR DATOS
    public void changeInfo(String newName, String newFamilyName,String newPassword, String newCreditCard, String newSvc){
        Member m=c.getMemberByCredentials(nickName,passwordMember);
        m.setName(newName);
        m.setSurname(newFamilyName);
        m.setPassword(newPassword);
        m.setCreditCard(newCreditCard);
        m.setSvc(Integer.parseInt(newSvc));
        
        name.textProperty().setValue(m.getName());
        familyName.textProperty().setValue(m.getSurname());
        password.textProperty().setValue(m.getPassword());
        cardNumber.textProperty().setValue(m.getCreditCard());
        String stringSvc=Integer.toString(m.getSvc());
        svc.textProperty().setValue(stringSvc);
    }
    
    //for getting login info, to later get member by credentials
    public void loginInfo(String n, String p) {
        nickName=n;
        passwordMember=p;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO2
        try {
       //image.fitWidthProperty().bind(Bindings.divide(borderPane.widthProperty(),6));
       //image.fitHeightProperty().bind(Bindings.divide(borderPane.heightProperty(),5));
       //changeProf.prefWidthProperty().bind(Bindings.divide(borderPane.widthProperty(),4.5));
       /*hBox.widthProperty().addListener((n,oldVal,newVal)-> {
           hBox.setMargin(vBox,new Insets(10,10,10,borderPane.getWidth()/60));
       })  ;  */ 
       
       
        makeResizable();
        c=Club.getInstance();
        c.setInitialData(); //REINICIA LOS DATOS DEL CLUB
        nickName="Ntonio";
        passwordMember="erewrqdc";
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

    @FXML
    private void goBack(ActionEvent event) {
        //back to main screen
    }

    @FXML
    private void changeProfileInfo(ActionEvent event) throws IOException, ClubDAOException {
        
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/changeProfileInfo.fxml"));
        Parent root=myLoader.load();
        ChangeProfileInfoController cpi=myLoader.getController();
        cpi.initMember(nickName,passwordMember);
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void changeProfilePicture(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
       else
       System.out.println("Cancel");
}
    }
    

    private void makeResizable() {
       
        
    }
}
