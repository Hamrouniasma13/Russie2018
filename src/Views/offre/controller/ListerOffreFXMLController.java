/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.offre.controller;

import Services.OffreService;
import entities.Offre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListerOffreFXMLController implements Initializable {
   
    @FXML
    private TextField titreOffre;
    @FXML
    private TextField typeOffre;
     @FXML
    private TextField dateAlleer;
    @FXML
    private TextField dateretour;
    @FXML
    private Button saveBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableView<Offre> tableOffre;
    @FXML
    private TableColumn<Offre, String> titreOffreCol;
    @FXML
    private TableColumn<Offre, String> typeOffreCol;  
    @FXML
    private TableColumn<Offre, String> dateAlleerCol; 
    @FXML
    private TableColumn<Offre, String> dateretourCol; 
    @FXML
    private TextField ajout_titre;
    @FXML
    private TextField ajout_type;
    @FXML
    private Button addBtn;
    @FXML
    private DatePicker ajout_datea;
    @FXML
    private DatePicker ajout_dater;
    @FXML
    private Button logoutBtn;
   

   
    private  SimpleObjectProperty<Offre> currentEntity = new SimpleObjectProperty<>();
    private  ObservableList<Offre> offresObservable = FXCollections.observableArrayList();
    private  OffreService OS = new OffreService();

    /**
     * Initializes the controller class.
     */
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
	initTextFieldListener();
    	initTableViewListener();
        initCurrentEntityListener();
       
        offresObservable.addAll(OS.getAll());
        tableOffre.setItems(offresObservable);
        currentEntity.setValue(new Offre());
    }

	private void initCurrentEntityListener() {
		currentEntity.addListener(new ChangeListener<Offre>() {
			@Override
			public void changed(ObservableValue<? extends Offre> observable, Offre oldValue, Offre newValue) {
				
				titreOffre.setText(newValue.getTitre_offre());
				typeOffre.setText(newValue.getType_offre());
				dateretour.setText(newValue.getDateretour());
				dateAlleer.setText(newValue.getDate_alleer());
				
				//disable the delete button if the entity is not saved in the database
				//deleteBtn.setDisable(newValue.getId()==null);
				
			}
		});
	}

	private void initTableViewListener() {
            
        titreOffreCol.setCellValueFactory(new PropertyValueFactory<>("Titre_offre"));
        typeOffreCol.setCellValueFactory(new PropertyValueFactory<>("Type_offre"));
        dateAlleerCol.setCellValueFactory(new PropertyValueFactory<>("Date_alleer"));
        dateretourCol.setCellValueFactory(new PropertyValueFactory<>("Dateretour"));

        tableOffre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Offre>() {
			@Override
			public void changed(ObservableValue<? extends Offre> observable, Offre oldValue, Offre newValue) {
				currentEntity.setValue(newValue !=null ? newValue : new Offre() );
			}
        });
	}

	private void initTextFieldListener() {
		titreOffre.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				currentEntity.getValue().setTitre_offre(newValue);
				
			}
		});
        
        typeOffre.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				currentEntity.getValue().setType_offre(newValue);
				
			}
		});
        
        dateAlleer.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				currentEntity.getValue().setDate_alleer(newValue);
				
			}
		});
        
        dateretour.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				currentEntity.getValue().setDateretour(newValue);
				
			}
		});
	}   
	
    @FXML
    private void  saveOffre(ActionEvent event) {
        
        Offre entity = currentEntity.get();
    	OS.updateOffre(entity);      
    	if(offresObservable.contains(entity))
    		offresObservable.set(offresObservable.indexOf(entity), entity);
    	else
    		offresObservable.add(entity);
    	
    	currentEntity.setValue(new Offre());
        
    }
    
    @FXML
    private void  deleteOffre(ActionEvent event) {
    	
    	Offre entity = currentEntity.get();
    	if(entity!=null){
    		OS.deleteOffre(entity.getId());
    		offresObservable.remove(entity);
    		currentEntity.setValue(new Offre());
    	}
    }
    
    @FXML
    void addOffre(ActionEvent event) {
        String titre = ajout_titre.getText();
        String type = ajout_type.getText();
        String datea = String.valueOf(ajout_datea.getValue());
        String dater = String.valueOf(ajout_dater.getValue());
        Offre o = new Offre(titre, type, datea, dater);
        OS.createOffre(o);

        if(offresObservable.contains(o))
    		offresObservable.set(offresObservable.indexOf(o), o);
    	else
    		offresObservable.add(o);
        
        offresObservable.clear();
        offresObservable.addAll(OS.getAll());
        
        currentEntity.setValue(new Offre());
        resetAddFields();

    }
    
    public void resetAddFields(){
       ajout_titre.setText(null);
       ajout_type.setText(null);
       ajout_datea.setValue(null);
       ajout_dater.setValue(null);
    }
    
    
    
    
}
