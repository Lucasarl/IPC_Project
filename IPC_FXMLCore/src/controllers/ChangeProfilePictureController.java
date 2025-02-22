/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import controllers.ProfileSettingsViewController;
import ipc_fxmlcore.IPC_FXMLCore;
import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import model.*;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class ChangeProfilePictureController implements Initializable {

    @FXML
    private ImageView def;
    @FXML
    private ImageView men1;
    @FXML
    private ImageView men2;
    @FXML
    private ImageView men3;
    @FXML
    private ImageView men4;
    @FXML
    private ImageView men5;
    @FXML
    private ImageView woman1;
    @FXML
    private ImageView woman2;
    @FXML
    private ImageView woman3;
    @FXML
    private ImageView woman4;
    @FXML
    private ImageView woman5;
    @FXML
    private ImageView woman6;
    
    private Member m;
    private Club c;
    @FXML
    private StackPane stackPane;
    /**
     * Initializes the controller class.
     * 
     * 
     */
    private boolean fromSignUp;
    
    public String nameS;
    public String surnameS;
    public String nickS;
    public String telS;
    public String passwordS;
    public String passwordRS;
    public String creditCS;
    public String svcS;
    
    public void setSignupData (String name, String surname, String nick, String tel, String password, String passwordR) {
        nameS=name;
        surnameS=surname;
        nickS=nick;
        telS=tel;
        passwordS=password;
        passwordRS=passwordR;
        
    }
    
    public void setFromSignup(boolean b) {
        fromSignUp=b;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{
     c =Club.getInstance();
     //Ajustamos el tamaño de las imagenes
     def.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     def.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     men1.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     men1.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));     
     men2.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     men2.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     men3.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     men3.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     men4.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     men4.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     men5.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     men5.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     woman1.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     woman1.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     woman2.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     woman2.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     woman3.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     woman3.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     woman4.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     woman4.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     woman5.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     woman5.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     woman6.fitWidthProperty().bind(Bindings.divide(stackPane.widthProperty(),6));
     woman6.fitHeightProperty().bind(Bindings.divide(stackPane.heightProperty(),4.16667));
     
     String urlImage = "src/images/default.PNG";
     Image avatar1=new Image(new FileInputStream(urlImage));
     urlImage="src/images/men.png";
     Image avatar2=new Image(new FileInputStream(urlImage));
     urlImage="src/images/men2.png";
     Image avatar3=new Image(new FileInputStream(urlImage));
     urlImage="src/images/men3.png";
     Image avatar4=new Image(new FileInputStream(urlImage));
     urlImage="src/images/men4.png";
     Image avatar5=new Image(new FileInputStream(urlImage));
     urlImage="src/images/men5.png";
     Image avatar6=new Image(new FileInputStream(urlImage));
     urlImage="src/images/woman.png";
     Image avatar7=new Image(new FileInputStream(urlImage));
     urlImage="src/images/woman2.png";
     Image avatar8=new Image(new FileInputStream(urlImage));
     urlImage="src/images/woman3.png";
     Image avatar9=new Image(new FileInputStream(urlImage));
     urlImage="src/images/woman4.png";
     Image avatar10=new Image(new FileInputStream(urlImage));
     urlImage="src/images/woman5.png";
     Image avatar11=new Image(new FileInputStream(urlImage));
     urlImage="src/images/woman6.png";
     Image avatar12=new Image(new FileInputStream(urlImage));
     def.imageProperty().setValue(avatar1);
     men1.imageProperty().setValue(avatar2);
     men2.imageProperty().setValue(avatar3);
     men3.imageProperty().setValue(avatar4);
     men4.imageProperty().setValue(avatar5);
     men5.imageProperty().setValue(avatar6);
     woman1.imageProperty().setValue(avatar7);
     woman2.imageProperty().setValue(avatar8);
     woman3.imageProperty().setValue(avatar9);
     woman4.imageProperty().setValue(avatar10);
     woman5.imageProperty().setValue(avatar11);
     woman6.imageProperty().setValue(avatar12);
    }
       catch(Exception e) {
       }
    }

    
    public void initMember(String nickname, String password) {
        m=c.getMemberByCredentials(nickname,password);
                }
    @FXML
    private void defaultSelected(MouseEvent event) throws IOException {
        if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(def.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null){
       FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        ps.changeImage(def.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(), m.getTelephone());
        ps.changeImage(def.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }}
    }

    @FXML
    private void men1Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(men1.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(men1.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(men1.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }
    }}

    @FXML
    private void men2Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(men2.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
       if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(men2.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(), m.getTelephone());
        ps.changeImage(men2.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }
    }}

    @FXML
    private void men3Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(men3.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(men3.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(men3.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        } }   }

    @FXML
    private void men4Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(men4.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(men4.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(men4.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }}
    }

    @FXML
    private void men5Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(men5.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(men5.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(men5.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }}
    }

    @FXML
    private void woman1Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(woman1.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(woman1.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(woman1.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }
    }}

    @FXML
    private void woman2Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(woman2.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(woman2.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(woman2.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }
    }}

    @FXML
    private void woman3Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(woman3.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(woman3.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(woman3.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }
    }}

    @FXML
    private void woman4Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setImage(woman4.imageProperty().getValue());
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(woman4.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(woman4.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }}
    }

    @FXML
    private void woman5Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(woman5.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(woman5.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(woman5.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }
    }}

    @FXML
    private void woman6Selected(MouseEvent event) throws IOException {
         if(fromSignUp) {
             FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
        Parent root=myLoader.load();
        SignUpController s=myLoader.getController();
        s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
        s.setImage(woman6.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeImage(woman6.imageProperty().getValue());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        IPC_FXMLCore.setRoot(root);
    } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(woman6.imageProperty().getValue());
        IPC_FXMLCore.setRoot(root);    
        }
    }}

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        //DE MOMENTO IR HACIA ATRAS VUELVE A REGISTRAR EL USUARIO Y LOS CAMBIOS PUEDEN NO GUARDARSE POR ELLO
        if(fromSignUp) {
              FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/signUp.fxml"));
              Parent root=myLoader.load();
             // m.setImage(image);
              SignUpController s=myLoader.getController();
              s.setData(nickS, nameS, surnameS, telS, passwordS, passwordRS);
              IPC_FXMLCore.setRoot(root);
        } else {
        if(m.getCreditCard()!=null) {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsView.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone(),m.getCreditCard(),Integer.toString(m.getSvc()));
        ps.changeImage(m.getImage());
        IPC_FXMLCore.setRoot(root);
        } else {
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource("/views/profileSettingsViewNocard.fxml"));
        Parent root=myLoader.load();
        ProfileSettingsViewNocardController ps=myLoader.getController();
        //NECESARIO SOLO DE MOMENTO
        ps.loginInfo(m.getNickName(), m.getPassword());
        ps.changeInfo(m.getName(),m.getSurname(),m.getPassword(),m.getTelephone());
        ps.changeImage(m.getImage());
        IPC_FXMLCore.setRoot(root);
        }
        }
    }
}
