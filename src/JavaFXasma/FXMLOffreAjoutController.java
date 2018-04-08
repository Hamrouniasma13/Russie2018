/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXasma;

import Services.OffreService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entities.Offre;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLOffreAjoutController implements Initializable {

    @FXML
    private TextField Titre_offre;
    @FXML
    private TextField Type_offre;
     @FXML
    private TextField Date_alleer;
      @FXML
    private TextField Dateretour;
      
    @FXML
    private Button btn1;
    
    @FXML
    private Button btn2;
    
    private AgenceMain main;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    	
    }    
    
    @FXML
    private void  saveOffre(ActionEvent event) {
        
    }
    
    @FXML
    private void  deleteOffre(ActionEvent event) {
    	
    }

	public void setMain(AgenceMain main) {
		this.main = main;
	}

	public TextField getTitre_offre() {
		return Titre_offre;
	}

	public TextField getType_offre() {
		return Type_offre;
	}

	public TextField getDate_alleer() {
		return Date_alleer;
	}

	public TextField getDateretour() {
		return Dateretour;
	}
    
    public Button getBtn2() {
		return btn2;
	}
}
