/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author duraip
 */
public class Reading_Metric_Logs extends Application 
{
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("Reading_Metric_Logs_FXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Log Extractor");
        stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Log Extractor - V 1.0\\LogExtractor.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
