/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import ipc_fxmlcore.IPC_FXMLCore;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author win
 */
public class SignUpController implements Initializable {

    @FXML
    private HBox title;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private Label nickNameError;
    @FXML
    private TextField nickNameTF;
    @FXML
    private Label phoneError;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private Label passwordError;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Label passwordConfirmationError;
    @FXML
    private PasswordField passwordConfirmationTF;
    @FXML
    private Label creditCardError;
    @FXML
    private TextField creditCardTF;
    @FXML
    private Label cvcError;
    @FXML
    private TextField cscTF;
    @FXML
    private Button cancelButton;
    @FXML
    private Button signUpButton;

    
    private BooleanProperty validPassword;
    
    private BooleanProperty validCreditCard;
    
    private BooleanProperty validSvc;  
    
    private BooleanProperty validName;
    
    private BooleanProperty validSurname;
    
    private BooleanProperty validTelephone;
    
    private BooleanProperty validNickname;
    
    private BooleanProperty validPasswordConfirmation;
    @FXML
    private Label firstNameError;
    @FXML
    private Label lastNameError;
    @FXML
    private ImageView image;
    
    public String cc;
    public String csc;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        validPassword = new SimpleBooleanProperty(); validPassword.setValue(Boolean.FALSE);
        
        validCreditCard = new SimpleBooleanProperty(); validCreditCard.setValue(Boolean.TRUE);
        
        validSvc = new SimpleBooleanProperty();validSvc.setValue(Boolean.TRUE);
        
        validName = new SimpleBooleanProperty(); validName.setValue(Boolean.FALSE);
        
        validSurname = new SimpleBooleanProperty(); validSurname.setValue(Boolean.FALSE);
        
        validTelephone = new SimpleBooleanProperty(); validTelephone.setValue(Boolean.FALSE);
        
        validNickname = new SimpleBooleanProperty(); validNickname.setValue(Boolean.FALSE);
        
        validPasswordConfirmation = new SimpleBooleanProperty(); validPasswordConfirmation.setValue(Boolean.FALSE);
        
        signUpButton.setDisable(true);
        
        
        firstNameTF.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 if(firstNameTF.textProperty().getValue().equals("")){
                validName.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                firstNameError.setText("You must fill this part");
            } else if(containsDigit(firstNameTF.textProperty().getValue())){
                validName.setValue(Boolean.FALSE);
                //signUpButton.setDisable(true);
                firstNameError.setText("This field must only contain letters.");
            }
            else{
                validName.setValue(Boolean.TRUE);
                firstNameError.setText("*");
                checkFieldsAndEnableButton();
                signUpButton.fire();
            }
                 
                 
}
         }
  
 );
        lastNameTF.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 
                 if(lastNameTF.textProperty().getValue().equals("")){
                validSurname.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                lastNameError.setText("You must fill this part");
            }
            else if(containsDigit(lastNameTF.textProperty().getValue())){
                validSurname.setValue(Boolean.FALSE);
                //signUpButton.setDisable(true);
                lastNameError.setText("This field must only contain letters.");
            }
            else{
                validSurname.setValue(Boolean.TRUE);
                lastNameError.setText("*");
                checkFieldsAndEnableButton();
                 signUpButton.fire();
            }
}
         }
  
 );
        phoneNumberTF.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 if(phoneNumberTF.textProperty().getValue().equals("")){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("You must fill this part");
            }
            else if(containsLetter(phoneNumberTF.textProperty().getValue())){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("Only numbers are allowed");
            }
            else if(phoneNumberTF.textProperty().getValue().length()!=9){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("Your number can have 9 digits");
            }
            else{
                validTelephone.setValue(Boolean.TRUE);
                phoneError.setText("*");
                checkFieldsAndEnableButton();
                signUpButton.fire();
            }
                 
}
         }
  
 );
        nickNameTF.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 try{
                if(nickNameTF.textProperty().getValue().equals("")){
                    validNickname.setValue(Boolean.FALSE);
                    signUpButton.setDisable(true);
                    nickNameError.setText("You must fill this part");
                }
                else if(!validNickName(nickNameTF.textProperty().getValue())){
                    validNickname.setValue(Boolean.FALSE);                    
                    signUpButton.setDisable(true);
                    nickNameError.setText("Nickname used");
                }
                else{
                    validNickname.setValue(Boolean.TRUE);
                    nickNameError.setText("*");
                    checkFieldsAndEnableButton();
                    signUpButton.fire();
                }
            }catch(Exception e){System.out.println(e.getMessage());}
            
        
                 
}
         }
  
 );
        passwordTF.setOnKeyPressed( event -> {
             if(event.getCode()==KeyCode.ENTER){
                 if(passwordTF.textProperty().getValue().equals("")){
                validPassword.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordError.setText("You must fill this part");
            }
            
            else if(passwordTF.textProperty().getValue().length()<6){
                validPassword.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordError.setText("You need at least 6 characters");
            }
            else{
                validPassword.setValue(Boolean.TRUE);
                passwordError.setText("*");
                checkFieldsAndEnableButton();
                 signUpButton.fire();
            }
                
}
         }
  
 );
        passwordConfirmationTF.setOnKeyPressed( event -> {
            if(event.getCode()==KeyCode.ENTER){
            if(passwordConfirmationTF.textProperty().getValue().equals("")){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordConfirmationError.setText("You must fill this part");
            }
            else if(!passwordConfirmationTF.textProperty().getValue().equals(passwordTF.textProperty().getValue())){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordConfirmationError.setText("Passwords must be equal");
            }
            else{
                validPasswordConfirmation.setValue(Boolean.TRUE);
                passwordConfirmationError.setText("*");
                checkFieldsAndEnableButton();
                signUpButton.fire();
            }
         }}
  
 );
        
  
   
        firstNameTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && firstNameTF.textProperty().getValue().equals("")){
                validName.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                firstNameError.setText("You must fill this part");
            } else if(!newValue && containsDigit(firstNameTF.textProperty().getValue())){
                validName.setValue(Boolean.FALSE);
                //signUpButton.setDisable(true);
                firstNameError.setText("This field must only contain letters.");
            }
            else{
                validName.setValue(Boolean.TRUE);
                firstNameError.setText("*");
                checkFieldsAndEnableButton();
            }
        });
        lastNameTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && lastNameTF.textProperty().getValue().equals("")){
                validSurname.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                lastNameError.setText("You must fill this part");
            }
             else if(containsDigit(lastNameTF.textProperty().getValue())){
                validSurname.setValue(Boolean.FALSE);
                //signUpButton.setDisable(true);
                lastNameError.setText("This field must only contain letters.");
            }
            else{
                validSurname.setValue(Boolean.TRUE);
                lastNameError.setText("*");
                checkFieldsAndEnableButton();
            }
        });
        
        nickNameTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            try{
                if(!newValue && nickNameTF.textProperty().getValue().equals("")){
                    validNickname.setValue(Boolean.FALSE);
                    signUpButton.setDisable(true);
                    nickNameError.setText("You must fill this part");
                }
                else if(!newValue &&
                        !validNickName(nickNameTF.textProperty().getValue())){
                    validNickname.setValue(Boolean.FALSE);                    
                    signUpButton.setDisable(true);
                    nickNameError.setText("Nickname used");
                }
                else{
                    validNickname.setValue(Boolean.TRUE);
                    nickNameError.setText("*");
                    checkFieldsAndEnableButton();
                }
            }catch(Exception e){System.out.println(e.getMessage());}
            
        });
        
        phoneNumberTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && phoneNumberTF.textProperty().getValue().equals("")){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("You must fill this part");
            }
            else if(!newValue && containsLetter(phoneNumberTF.textProperty().getValue())){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("Only numbers are allowed");
            }
            else if(!newValue && phoneNumberTF.textProperty().getValue().length()!=9){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("Your number can have 9 digits");
            }
            else{
                validTelephone.setValue(Boolean.TRUE);
                phoneError.setText("*");
                checkFieldsAndEnableButton();
            }
        });
        
        passwordTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && passwordTF.textProperty().getValue().equals("")){
                validPassword.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordError.setText("You must fill this part");
            }
            
            else if(!newValue && passwordTF.textProperty().getValue().length()<6){
                validPassword.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordError.setText("You need at least 6 characters");
            }
            else{
                validPassword.setValue(Boolean.TRUE);
                passwordError.setText("*");
                checkFieldsAndEnableButton();
            }
        });
        
        /*creditCardTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && creditCardTF.textProperty().getValue().length()!=16 && creditCardTF.textProperty().getValue().length()!=0){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                creditCardError.setText("Credit card must be 16 characters long");
            } else if(!newValue && cscTF.textProperty().getValue().equals("") && creditCardTF.textProperty().getValue().length()==16) {
                
            cvcError.setText("You must fill this field if you include your credit card.");
            validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
        }
            else if(!newValue && creditCardTF.textProperty().getValue().length()==0 && cscTF.textProperty().getValue().length()==3) {
            creditCardError.setText("You must fill this field if you include your CSC.");
            validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true); }
            else {
                validPasswordConfirmation.setValue(Boolean.TRUE);
                creditCardError.setText("");
                checkFieldsAndEnableButton();
            }
        });
        
         cscTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && cscTF.textProperty().getValue().length()!=3 && cscTF.textProperty().getValue().length()!=0){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                cvcError.setText("Credit card must be 3 digits long");
                
            }  else if(!newValue && containsLetter(cscTF.textProperty().getValue())) {
             cvcError.setText("CSC must not contain letters.");
            validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
        } else if(!newValue && creditCardTF.textProperty().getValue().equals("") && cscTF.textProperty().getValue().length()!=0) {
            creditCardError.setText("You must fill this field if you include your CSC.");
            validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
        } else if(!newValue && creditCardTF.textProperty().getValue().length()==16 && cscTF.textProperty().getValue().length()==0 ) {
            creditCardError.setText("You must fill this field if you include your credit card.");
            validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
        }
            else {
                validPasswordConfirmation.setValue(Boolean.TRUE);
                cvcError.setText("");
                checkFieldsAndEnableButton();
                
            }
        });*/
        
         passwordConfirmationTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && passwordConfirmationTF.textProperty().getValue().equals("")){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordConfirmationError.setText("You must fill this part");
            }
            else if(!newValue && !passwordConfirmationTF.textProperty().getValue().equals(passwordTF.textProperty().getValue())){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                passwordConfirmationError.setText("Passwords must be equal");
            }
            else{
                validPasswordConfirmation.setValue(Boolean.TRUE);
                passwordConfirmationError.setText("*");
                checkFieldsAndEnableButton();
            }
        });
    }
    
    private boolean validNickName(String nickname)throws ClubDAOException, IOException{
        Club club = Club.getInstance();
        if(!club.existsLogin(nickname)) {
            return true;
        } 
        return false;
    }
    
    private int specialLength(String s){
        int res = 0;
        for(int i = 0; i<s.length(); i++){
            if(!Character.isWhitespace(s.charAt(i))){
                res++;
            }
        }
        return res;
    }
    private boolean containsLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    private boolean containsDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    
    private void checkFieldsAndEnableButton() {
        if(validPassword.getValue() &&
           validName.getValue() &&
           validSurname.getValue() &&
           validTelephone.getValue() &&
           validNickname.getValue() &&
           validPasswordConfirmation.getValue()){
        
           signUpButton.setDisable(false); 
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/views/initialPage.fxml"));
        Parent root= loader.load();
        IPC_FXMLCore.setRoot(root);
    }

    @FXML
    private void handleSignUp(ActionEvent event) throws ClubDAOException, IOException{
        if( !passwordConfirmationTF.textProperty().getValue().equals(passwordTF.textProperty().getValue())){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                //signUpButton.setDisable(true);
                passwordConfirmationError.setText("Passwords must be equal");
               // passwordConfirmationTF.requestFocus();
            }  
        else if(containsDigit(firstNameTF.textProperty().getValue())) {
               validName.setValue(Boolean.FALSE);
                //signUpButton.setDisable(true);
                firstNameError.setText("The field must only contain letters.");
        }  
        else if(containsDigit(lastNameTF.textProperty().getValue())){
                validSurname.setValue(Boolean.FALSE);
                //signUpButton.setDisable(true);
                lastNameError.setText("This field must only contain letters.");
            }
        else {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/images/Logo.png").toString()));
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
      getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("myAlert");
        alert.setTitle("Add a card");
        alert.setHeaderText("Do you want to add a card to pay for your bookings?");
        alert.setContentText(null);
        ButtonType buttonTypeAdd = new ButtonType("Add a card");
        ButtonType buttonTypeCancel = new ButtonType("Not right now", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeAdd, buttonTypeCancel);
        alert.getDialogPane().getChildren().forEach(node -> {
    if (node instanceof ButtonBar) {
        ButtonBar buttonBar = (ButtonBar) node;
        buttonBar.getButtons().forEach(possibleButtons -> {
            if (possibleButtons instanceof Button) {
                Button b = (Button) possibleButtons;
                if (b.getText().equals("Not right now")) {
                    b.getStyleClass().add("cancel");
                }
            }
        });
    }
});
       

        
            String c;
        Club club = Club.getInstance();
/*        if(cscTF.textProperty().getValue().equals("")){
            cscTF.textProperty().setValue("000");
            c=null;
        } else {
            c= creditCardTF.textProperty().getValue();
        }*/
        
        
        club.registerMember(
                firstNameTF.textProperty().getValue(),
                lastNameTF.textProperty().getValue(),
                phoneNumberTF.textProperty().getValue(),
                nickNameTF.textProperty().getValue(),
                passwordTF.textProperty().getValue(),
                null,
                000,
                image.imageProperty().getValue());
        
       // System.out.println(club.existsLogin(nickNameTF.textProperty().getValue()));
       Optional<ButtonType> result = alert.showAndWait();
       if (result.isPresent()) {
       if (result.get() == buttonTypeAdd){
            User u=User.getInstance();
            u.setPassword(passwordTF.textProperty().getValue());
            u.setNickname(nickNameTF.textProperty().getValue());
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/views/addCard.fxml"));
            Parent root= loader.load();
            AddCardController l=loader.getController();
            l.setSignup(true);
            IPC_FXMLCore.setRoot(root);
       } else {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
         stage = (Stage) alert.getDialogPane().getScene().getWindow();
         stage.getIcons().add(new Image(this.getClass().getResource("/images/Logo.png").toString()));
        // ó AlertType.WARNING ó AlertType.ERROR ó AlertType.CONFIRMATIONalert.setTitle("Diálogo de información");
        alert.setGraphic(new ImageView(this.getClass().getResource("/images/confirmation.png").toString()));
        alert.setHeaderText(null);
        ButtonType buttonTypeOne = new ButtonType("OK");
        alert.getButtonTypes().setAll(buttonTypeOne);
         dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
      getClass().getResource("/styles/dialogBoxes.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("myAlert");
        // ó null si no queremos cabecera
        alert.setContentText("Your account has been created. Please log in.");
        alert.showAndWait();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/views/login.fxml"));
        Parent root= loader.load();
        IPC_FXMLCore.setRoot(root);
    }}}}
    
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
            } 
          
       }
       else if (result.get() == buttonTypeSelect){
            //Cambiamos de escena 
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/views/changeProfilePicture.fxml"));
        Parent root= loader.load();
        ChangeProfilePictureController c=loader.getController();
        c.setFromSignup(true);
        c.setSignupData(firstNameTF.textProperty().getValue(),  lastNameTF.textProperty().getValue(), nickNameTF.textProperty().getValue(), phoneNumberTF.textProperty().getValue(), passwordTF.textProperty().getValue(), passwordConfirmationTF.textProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        
       }
       else {
           
       }
       
        
        
    }
    }
    
    public void setImage (Image i) {
        image.imageProperty().setValue(i);
    }
    
    public void setData(String nick, String name, String surname, String tel, String password, String passwordConf) {
        nickNameTF.textProperty().setValue(nick);
        phoneNumberTF.textProperty().setValue(tel);
        firstNameTF.textProperty().setValue(name);
        lastNameTF.textProperty().setValue(surname);
        passwordTF.textProperty().setValue(password);
        passwordConfirmationTF.textProperty().setValue(passwordConf);
       
        
        if(firstNameTF.textProperty().getValue().equals("")){
                validName.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                //firstNameError.setText("You must fill this part");
            }
            else{
                validName.setValue(Boolean.TRUE);
                firstNameError.setText("*");
                checkFieldsAndEnableButton();
            }
        
            if(lastNameTF.textProperty().getValue().equals("")){
                validSurname.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                //lastNameError.setText("You must fill this part");
            }
            else{
                validSurname.setValue(Boolean.TRUE);
                lastNameError.setText("*");
                checkFieldsAndEnableButton();
            }
        
        
        
            try{
                if(nickNameTF.textProperty().getValue().equals("")){
                    validNickname.setValue(Boolean.FALSE);
                    signUpButton.setDisable(true);
                    //nickNameError.setText("You must fill this part");
                }
                else if(
                        !validNickName(nickNameTF.textProperty().getValue())){
                    validNickname.setValue(Boolean.FALSE);                    
                    signUpButton.setDisable(true);
                    nickNameError.setText("Nickname used");
                }
                else{
                    validNickname.setValue(Boolean.TRUE);
                    nickNameError.setText("*");
                    checkFieldsAndEnableButton();
                }
            }catch(Exception e){System.out.println(e.getMessage());}
            
        
        
        
            if(phoneNumberTF.textProperty().getValue().equals("")){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
               // phoneError.setText("You must fill this part");
            }
            else if( containsLetter(phoneNumberTF.textProperty().getValue())){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("Only numbers are allowed");
            }
            else if( phoneNumberTF.textProperty().getValue().length()!=9){
                validTelephone.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                phoneError.setText("Your number can have 9 digits");
            }
            else{
                validTelephone.setValue(Boolean.TRUE);
                phoneError.setText("*");
                checkFieldsAndEnableButton();
            }
       
        
            if(passwordTF.textProperty().getValue().equals("")){
                validPassword.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
            }
            
            else if( passwordTF.textProperty().getValue().length()<6){
                validPassword.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
                //passwordError.setText("You need at least 6 characters");
            }
            else{
                validPassword.setValue(Boolean.TRUE);
                passwordError.setText("*");
                checkFieldsAndEnableButton();
            }
        
        
            if(passwordConfirmationTF.textProperty().getValue().equals("")){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                signUpButton.setDisable(true);
            }
            else if( !passwordConfirmationTF.textProperty().getValue().equals(passwordTF.textProperty().getValue())){
                validPasswordConfirmation.setValue(Boolean.FALSE);
                //signUpButton.setDisable(true);
                passwordConfirmationError.setText("Passwords must be equal");
                passwordConfirmationTF.requestFocus();
            }
            else{
                validPasswordConfirmation.setValue(Boolean.TRUE);
                passwordConfirmationError.setText("*");
                checkFieldsAndEnableButton();
            }
        
    }
    
}


