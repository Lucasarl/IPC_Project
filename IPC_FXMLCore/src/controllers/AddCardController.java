/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;
//#29a61ed1
import ipc_fxmlcore.IPC_FXMLCore;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.*;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AddCardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private BooleanProperty validCard;
    private BooleanProperty validSvc;
    
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField svc;

    public Member m;
    private Label errorPassword;
    @FXML
    private Button update;
    private Label nameRequired;
    private Label surnameRequired;
    @FXML
    private Label errorSvc;
    @FXML
    private Label errorCardNumber;
    
    //LOADS PROFILE DETAILS
    public void initMember(String nickName, String password) throws ClubDAOException, IOException {
        Club c=Club.getInstance();
        m=c.getMemberByCredentials(nickName, password);
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        validCard = new SimpleBooleanProperty();
        validSvc= new SimpleBooleanProperty();
        
        
        validCard.setValue(Boolean.FALSE);
        validSvc.setValue(Boolean.FALSE);
        
        
        /*BooleanBinding validFields = Bindings.and(validPassword, validCreditCard)
                 .and(validSvc);
         update.disableProperty().bind(Bindings.not(validFields));*/
        
         
        cardNumber.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkCreditCard();
        }});
        
        
        svc.focusedProperty().addListener((observableValue,oldVal,newVal)-> {
        if(!newVal){
            checkSvc();
        }});


    }    
    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.changeImage(m.getImage());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword());
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void updateInfo(ActionEvent event) throws IOException {
        if((!errorCardNumber.isVisible() && !errorSvc.isVisible())){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        // ó AlertType.WARNING ó AlertType.ERROR ó AlertType.CONFIRMATIONalert.setTitle("Diálogo de información");
        alert.setGraphic(new ImageView(this.getClass().getResource("/images/confirmation.png").toString()));
        alert.setHeaderText(null);
        ButtonType buttonTypeOne = new ButtonType("OK");
        alert.getButtonTypes().setAll(buttonTypeOne);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
      getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("myAlert");
        // ó null si no queremos cabecera
        alert.setContentText("The credit card has been added.");
        alert.showAndWait();
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        m.setCreditCard(cardNumber.textProperty().getValue());
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(m.getImage());
        ps.setInvisible();
        //SI CAMBIAS LA CONTRASEÑA PETA PORQUE ESTOY USANDO UN USUARIO EJEMPLO "A LA FUERZA"
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),cardNumber.textProperty().getValue(),svc.textProperty().getValue());
        IPC_FXMLCore.setRoot(root);}
    }
    private void checkCreditCard ()  {
        if(cardNumber.textProperty().getValue().length()!=16) {
            manageError(errorCardNumber, cardNumber, validCard);
        }
        
        else {
            manageCorrect(errorCardNumber, cardNumber, validCard);
        }
    }
    
     private void checkSvc(){
        if(svc.textProperty().getValue().length()!=3) {
            manageError(errorSvc,svc,validSvc);}
           
        else {
            manageCorrect(errorSvc,svc,validSvc);
        }
    }
}
