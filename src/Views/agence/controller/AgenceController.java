/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.agence.controller;

import entities.Agence;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AgenceController extends AnchorPane{

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView EventImage;

    @FXML
    private Label title;

    @FXML
    private Label address;

    @FXML
    private Label contact;

    @FXML
    private Text description;

    @FXML
    private Button detailsBtn;
    
    
    
    public AgenceController() {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/Views/agence/fxml/Agence.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }  
    
    
    
    public void setData(Agence agence){
        title.setText(agence.getNomAgence());
        description.setText(agence.getChaine());
        contact.setText(String.valueOf(agence.getContactAgence()));
        title.setText(agence.getNomAgence());
        
      String url = "/Resources/images/agence_img/"+agence.getNom_image();
        System.out.println(url);
      try{
        Image img = new Image(url);
        EventImage.setImage(img);   
      }catch(Exception ex){
          System.out.println("Image not found");
      }
    }
    
}
