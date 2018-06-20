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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author duraip
 */
public class Tools_FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ImageView toolsimg1;
    @FXML
    private Button TBat;
    @FXML
    private ImageView toolsimg2;
    @FXML
    private Button TBet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void AccentureTools(ActionEvent event) 
    {
            try
            {
                 Parent Window2=FXMLLoader.load(getClass().getResource("Accenture_Tools_FXML.fxml"));
                 Scene Window2Scene=new Scene(Window2);
                 Stage Window2Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 Window2Stage.setScene(Window2Scene);
                 Window2Stage.setResizable(false);
                 Window2Stage.setTitle("Accenture Tools Login");
                 Window2Stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\Accenture.png"));
                 Window2Stage.show();
            }
            catch(Exception e)
            {
                 System.out.println("Caught in Exception inside Tool in Controller\n Inside AccentureTools function\n abd the exception is \n"+e+"\n");
                 e.printStackTrace();
            }
//            try
//            {
//                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Accenture_Tools_FXML.fxml"));
//                Parent root1=(Parent)fxmlLoader.load();
//                Stage stage=new Stage();
//                stage.setResizable(false);
//                stage.setTitle("Accenture Tools");
//                stage.setScene(new Scene(root1));
//                stage.show();
//            }
//            catch(Exception e)
//            {
//                System.out.println("Caught in Exception inside Tool in Controller\n Inside AccentureTools function\n abd the exception is \n"+e+"\n");
//                e.printStackTrace();
//            }
    }

    @FXML
    private void ELMSTools(ActionEvent event) 
    {
            try
            {
                 Parent Window2=FXMLLoader.load(getClass().getResource("ELMS_Tools_FXMLDocument.fxml"));
                 Scene Window2Scene=new Scene(Window2);
                 Stage Window2Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 Window2Stage.setScene(Window2Scene);
                 Window2Stage.setResizable(false);
                 Window2Stage.setTitle("ELMS Tools Login");
                 Window2Stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\ELMS.PNG"));
                 Window2Stage.show();
            }
            catch(Exception e)
            {
                 System.out.println("Caught in Exception inside Tools in Controller\n Inside ELMS Tools function\n abd the exception is \n"+e+"\n");
                 e.printStackTrace();
            }
//            try
//            {
//                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("ELMS_Tools_FXMLDocument.fxml"));
//                Parent root1=(Parent)fxmlLoader.load();
//                Stage stage=new Stage();
//                stage.setResizable(false);
//                stage.setTitle("ELMS Tools");
//                stage.setScene(new Scene(root1));
//                stage.show();
//            }
//            catch(Exception e)
//            {
//                System.out.println("Caught in Exception inside Tools in Controller\n Inside ELMS Tools function\n abd the exception is \n"+e+"\n");
//                e.printStackTrace();
//            }
    }
    
}
