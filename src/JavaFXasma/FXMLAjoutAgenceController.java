/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXasma;

import Services.AgenceService;
import java.util.*;
import entities.Agence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;








/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLAjoutAgenceController implements Initializable {
    
     @FXML
    private TextField nomAgence;
    @FXML
    private TextField adresseAgence;
     @FXML
    private TextField ContactAgence;
      @FXML
    private TextField chaine;
      @FXML
      private TextField nom_image; 
    @FXML
    private Button btn;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
 @FXML
    private void  CreateAgence(ActionEvent event) {
        Agence agence= new Agence(nomAgence.getText(),adresseAgence.getText(),Integer.parseInt(ContactAgence.getText()),chaine.getText(),nom_image.getText());
        AgenceService AS= new AgenceService();
        AS.createAgence(agence);
        
        try {
            Parent root= FXMLLoader.load(getClass().getResource("ListerAgenceFXML.fxml"));
            btn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjoutAgenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
