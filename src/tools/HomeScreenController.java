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
public class HomeScreenController implements Initializable {

    @FXML
    private Button PWRGen;
    @FXML
    private Button pextract;
    @FXML
    private Button lextract;
    @FXML
    private ImageView HSIhicon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void rgen(ActionEvent event) 
    {
        try
        {
             FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("ELMS_R_Generator_FXML.fxml"));
             Parent root1=(Parent)fxmlLoader.load();
             Stage stage=new Stage();
             stage.setResizable(false);
             stage.setTitle("ELMS Data Extractor");
             stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\Window2Stage."));
             stage.setScene(new Scene(root1));
             stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Caught in Exception inside ELMS Tools in Controller\n Inside rgen function\n abd the exception is \n"+e+"\n");
            e.printStackTrace();
        }
    }

    @FXML
    private void prodETractor(ActionEvent event) 
    {
        try
        {
             FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("ELMS_Prod_Data_Analysis.fxml"));
             Parent root1=(Parent)fxmlLoader.load();
             Stage stage=new Stage();
             stage.setResizable(false);
             stage.setTitle("Prod Data Analysis");
             stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\ELMS-Splash.png"));
             stage.setScene(new Scene(root1));
             stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Caught in Exception inside ELMS Tools in Controller\n Inside prodETractor function\n abd the exception is \n"+e+"\n");
            e.printStackTrace();
        }
    }

    @FXML
    private void LogETract(ActionEvent event) 
    {
        try
        {
             FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Reading_Metric_Logs_FXML.fxml"));
             Parent root1=(Parent)fxmlLoader.load();
             Stage stage=new Stage();
             stage.setResizable(true);
             stage.setTitle("PCF Log Extractor");
             stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\LogExtractor.png"));
             stage.setScene(new Scene(root1));
             stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Caught in Exception inside ELMS ToolsHome Screen\n Inside LogETract function\n abd the exception is \n"+e+"\n");
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
                 Window2Stage.setTitle("Tools");
                 Window2Stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Tools\\MainHome.png"));
                 Window2Stage.setResizable(false);
                 Window2Stage.show();
            }
            catch(Exception e)
            {
                 System.out.println("Caught in Exception inside HomeScreenController in Controller\n Inside GoHome function\n abd the exception is \n"+e+"\n");
                 e.printStackTrace();
            }
    }
    
}
