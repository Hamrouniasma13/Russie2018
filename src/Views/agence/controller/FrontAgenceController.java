
package Views.agence.controller;

import Services.AgenceService;
import entities.Agence;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FrontAgenceController implements Initializable {

    @FXML
    private VBox container;

    @FXML
    private TextField filterField;

    @FXML
    private Button rechercheBtn;
    
    private String motCle="";

    
    AgenceService AS;
    ObservableList<Agence> agences; 
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AS= new AgenceService();
        rechercheBtn.setOnMouseClicked(e->{
            motCle = filterField.getText();
            initData();
        });
        initData();
    }  
    
    public void initData(){
        agences =  FXCollections.observableArrayList(AS.getAll(motCle));
        container.getChildren().removeIf(e -> true);
        
        agences.forEach((Agence a) -> {
            AgenceController ac = new AgenceController();
            ac.setData(a);
            container.setSpacing(15);
            container.getChildren().add(ac);
        });
    }
    
}
