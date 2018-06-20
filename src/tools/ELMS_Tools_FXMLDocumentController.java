/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package tools;

import java.io.FileInputStream;
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
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author duraip
 */
public class ELMS_Tools_FXMLDocumentController implements Initializable 
{

    @FXML
    private Button msblogin;
    @FXML
    private PasswordField mstfun;
    @FXML
    private PasswordField mstfpwd;
    @FXML
    private ImageView mcimg2;
    @FXML
    private ImageView mcimg1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try
        {
            mcimg1.setVisible(true);
            Image image = new Image(new FileInputStream("C:\\Program Files (x86)\\Tools\\LogExtractor.png")); 
            mcimg2.setImage(image);
        }
        catch(Exception e)
        {
            System.out.println("Caught in Exception in ELMS Tools Class inside Initialize Method\nAnd the Exception is : \t"+e+"\n");
            e.printStackTrace();
        }
        
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event) 
    {
        
        
        if(mstfun.getText().equals("ELMS_Perf")&&mstfpwd.getText().equals("perfy_run"))
        {
            try
            {
                 Parent Window2=FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
                 Scene Window2Scene=new Scene(Window2);
                 Stage Window2Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 Window2Stage.setScene(Window2Scene);
                 Window2Stage.setTitle("");
                 Window2Stage.setTitle("ELMS Tools");
                 Window2Stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\ELMS.PNG"));
                 Window2Stage.setResizable(false);
                 Window2Stage.show();
            }
            catch(Exception e)
            {
                 System.out.println("Caught in Exception inside ELMS Tools in Controller\n Inside Login function\n abd the exception is \n"+e+"\n");
                 e.printStackTrace();
            }
//            try
//            {
//                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
//                Parent root1=(Parent)fxmlLoader.load();
//                Stage stage=new Stage();
//                stage.setResizable(false);
//                stage.setTitle("Home Screen");
//                stage.setScene(new Scene(root1));
//                stage.show();
//            }
//            catch(Exception e)
//            {
//                System.out.println("Caught in Exception inside ELMS Tools in Controller\n Inside Login function\n abd the exception is \n"+e+"\n");
//                e.printStackTrace();
//            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"UserName and Password Doesn't Match \nPlease try again...\n","Error",JOptionPane.ERROR_MESSAGE);
            mstfun.setPromptText("User Name");
            mstfpwd.setPromptText("Password");
        }
    }
    
}
