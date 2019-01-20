/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplenotepad;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Amr Elkady
 */
public class NotePad extends Application {
    Stage objstage;
    
    @Override
    public void start(Stage primaryStage) {
        objstage=primaryStage;
        
        NotePadInterfaceBase root = new NotePadInterfaceBase();
        
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("NotePad");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        /**
         * handle closing window 
         */
        
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            NotePadInterfaceBase.Closing();
            event.consume();
        });
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public  Stage getStage(){
        return objstage;
    }
    
}
