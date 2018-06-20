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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author duraip
 */
public class AccentureHomeController implements Initializable 
{

    @FXML
    private ImageView atimg1;
    @FXML
    private ImageView atimg2;
    @FXML
    private ImageView atimg3;
    @FXML
    private Button atbvt;
    @FXML
    private Button atbet;
    @FXML
    private Button atbsm;
    @FXML
    private ImageView ATIHicon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void VacationTracker(ActionEvent event) 
    {
            try
            {
                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Vacation_Tracker_FXML.fxml"));
                Parent root1=(Parent)fxmlLoader.load();
                Stage stage=new Stage();
                stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\VacationPlan.png"));
                stage.setResizable(false);
                stage.setTitle("Vacation Tracker V - 2.15");
                stage.setScene(new Scene(root1));
                stage.show();
            }
            catch(Exception e)
            {
                System.out.println("Caught in Exception inside Accenture Home in Controller\n Inside Vacation Tracker function\n abd the exception is \n"+e+"\n");
                e.printStackTrace();
            }  
    }

    @FXML
    private void EffortTracker(ActionEvent event) 
    {
            try
            {
                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Effort_Tracker_FXM.fxml"));
                Parent root1=(Parent)fxmlLoader.load();
                Stage stage=new Stage();
                stage.setResizable(false);
                stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\EffortTracker.png"));
                stage.setTitle("Effort Tracker V - 1.1");
                stage.setScene(new Scene(root1));
                stage.show();
            }
            catch(Exception e)
            {
                System.out.println("Caught in Exception inside Accenture Home in Controller\n Inside Efort Tracker function\n abd the exception is \n"+e+"\n");
                e.printStackTrace();
            }
    }

    @FXML
    private void mail(ActionEvent event) 
    {
            try
            {
                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("AccentureHome.fxml"));
                Parent root1=(Parent)fxmlLoader.load();
                Stage stage=new Stage();
                stage.setResizable(false);
                stage.setTitle("Log Extractor V 1.0");
                stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\LogExtractor.png"));
                stage.setScene(new Scene(root1));
                stage.show();
            }
            catch(Exception e)
            {
                System.out.println("Caught in Exception inside Accenture Home in Controller\n Inside Mail function\n abd the exception is \n"+e+"\n");
                e.printStackTrace();
            }
    }

    @FXML
    private void GoHome(MouseEvent event) 
    {
        try
            {
                 Parent Window2=FXMLLoader.load(getClass().getResource("Tools_FXMLDocument.fxml"));
                 Scene Window2Scene=new Scene(Window2);
                 Stage Window2Stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 Window2Stage.setScene(Window2Scene);
                 Window2Stage.setResizable(false);
                 Window2Stage.setTitle("Tools");
                 Window2Stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\MainHome.png"));
                 Window2Stage.show();
            }
            catch(Exception e)
            {
                 System.out.println("Caught in Exception inside AccentureHomeController in Controller\n Inside GoHome function\n abd the exception is \n"+e+"\n");
                 e.printStackTrace();
            }
    }
    
}
