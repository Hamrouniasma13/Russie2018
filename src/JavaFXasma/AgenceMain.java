/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXasma;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AgenceMain extends Application{
    
    
      @Override
    public void start(Stage stage) throws Exception {
        	  
    	URL location = getClass().getResource("/Views/offre/fxml/ListerOffreFXML.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        Pane root = (Pane) fxmlLoader.load();
        Scene scene = new Scene(root);
        
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
    

