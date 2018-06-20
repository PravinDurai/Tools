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
public class ELMSRGeneratorV10 extends Application 
{
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("ELMS_R_Generator_FXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Data Extractor");
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:/C:\\Program Files (x86)\\ELMS Data Extractor - V 2.21\\ELMS-extractor.png"));
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
