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
public class Vacation_TrackerV215 extends Application 
{
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("Vacation_Tracker_FXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\Vacation Tracker - V 2.14\\unnamed.png"));
        stage.setTitle("Vacation Tracker - V 2.15");
        //stage.setIconified(true);
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
