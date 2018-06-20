/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author duraip
 */
public class Accenture_Tools_FXMLController implements Initializable 
{

    @FXML
    private Label label;
    @FXML
    private TextField huname;
    @FXML
    private PasswordField hpwd;
    @FXML
    private Button Bhlogin;
    @FXML
    private Label htfstatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void outRange(MouseEvent event) 
    {
        //htfstatus.setText("OutRange");
    }

    @FXML
    private void Login(ActionEvent event) 
    {
        if((huname.getText().equals("Pravin"))&&(hpwd.getText().equals("Blastoise")))
        {
            try
            {
                 Parent Window2=FXMLLoader.load(getClass().getResource("AccentureHome.fxml"));
                 Scene Window2Scene=new Scene(Window2);
                 Stage Window2Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 Window2Stage.setScene(Window2Scene);
                 Window2Stage.setTitle("Accenture Tools");
                 Window2Stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\Accenture.png"));
                 Window2Stage.setResizable(false);
                 Window2Stage.show();
            }
            catch(Exception e)
            {
                 System.out.println("Caught in Exception inside Accenture Tools in Controller\n Inside Login function\n abd the exception is \n"+e+"\n");
                 e.printStackTrace();
            }
//            try
//            {
//                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AccentureHome.fxml"));
//                Parent root1=(Parent)fxmlLoader.load();
//                Stage stage=new Stage();
//                stage.setResizable(false);
//                stage.setTitle("Accenture Tools");
//                stage.setScene(new Scene(root1));
//                stage.show();
//            }
//            catch(Exception e)
//            {
//                System.out.println("Caught in Exception inside Accenture Tools in Controller\n Inside Login function\n abd the exception is \n"+e+"\n");
//                e.printStackTrace();
//            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"UserName and Password Doesn't Match \nPlease try again...\n","Error",JOptionPane.ERROR_MESSAGE);
            huname.setPromptText("User Name");
            hpwd.setPromptText("Password");
        }
    }

    @FXML
    private void inRange(MouseEvent event) 
    {
        //htfstatus.setText("inRange");
    }
    
}
