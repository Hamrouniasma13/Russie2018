/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.agence.controller;

import Services.AgenceService;
import entities.Agence;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListerAgenceFXMLController implements Initializable {
    
   @FXML
    private TableView<Agence> TableAgence;
    @FXML
    private TableColumn<Agence, String> nomAgence;
    @FXML
    private TableColumn<Agence, String> adresseAgence;  
    @FXML
    private TableColumn<Agence, Integer> ContactAgence; 
    @FXML
    private TableColumn<Agence, String> chaine; 
    @FXML
    private TableColumn<Agence,String> nom_image;  
    @FXML 
    private TextField filterField;
    
    @FXML
    private TextField nomAg;

    @FXML
    private TextField AdresseAgence;

    @FXML
    private TextArea descAgence;

    @FXML
    private TextField contactAgence;

    @FXML
    private Label imgtxt;
    
      @FXML
    private TextField nomAg11;

    @FXML
    private TextField AdresseAgence11;

    @FXML
    private TextArea descAgence11;

    @FXML
    private TextField contactAgence11;

    @FXML
    private Label imgtxt11;
    
    @FXML
    private Button addbtn;

    @FXML
    private Button uploadBtn;
    
    @FXML
    private Button uploadBtn11;
    
    @FXML
    private Button deletebtn;
     
    @FXML
    private Button updatebtn;
    
    AgenceService AS;
    ObservableList AgencesObservable; 
    Agence selectedAgence;
    File newImageFile;
    File imageFile;
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AS= new AgenceService();
        initTable();
        updatebtn.setDisable(true);
        deletebtn.setDisable(true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            initTable();
            FilteredList<Agence> filteredData = new FilteredList<>(AgencesObservable, p -> true);
            filteredData.setPredicate(agence -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (agence.getNomAgence().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (agence.getNomAgence().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
           SortedList<Agence> sortedData = new SortedList<>(filteredData);
           sortedData.comparatorProperty().bind(TableAgence.comparatorProperty());
           TableAgence.setItems(sortedData);
        });
         
        TableAgence.setRowFactory( tv -> {
        TableRow<Agence> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    selectedAgence = row.getItem();
                    setData(selectedAgence);
                    updatebtn.setDisable(false);
                    deletebtn.setDisable(false);
                }
            });
            return row ;
        });
        
        updatebtn.setOnMouseClicked(e->updateAgence());
        deletebtn.setOnMouseClicked(e->deleteAgence());
        addbtn.setOnMouseClicked(e->addAgence());
        uploadBtn11.setOnMouseClicked(e->SelectImage(true));
        uploadBtn.setOnMouseClicked(e->SelectImage(false));
    }  
    
    public void setData(Agence agence){
        imageFile=null;
        nomAg.setText(agence.getNomAgence());
        descAgence.setText(agence.getChaine());
        AdresseAgence.setText(agence.getAdresseAgence());
        contactAgence.setText(String.valueOf(agence.getContactAgence()));
        imgtxt.setText(agence.getNom_image());
    }
    
     public void resetFormulaire(){
        nomAg.setText(null);
        descAgence.setText(null);
        AdresseAgence.setText(null);
        contactAgence.setText(null);
        imgtxt.setText(null);
        nomAg11.setText(null);
        descAgence11.setText(null);
        AdresseAgence11.setText(null);
        contactAgence11.setText(null);
        imgtxt11.setText(null);
        selectedAgence = null;
        updatebtn.setDisable(true);
        deletebtn.setDisable(true);
    }
    
    private void updateAgence(){
        selectedAgence.setNomAgence(nomAg.getText());
        selectedAgence.setChaine(descAgence.getText());
        selectedAgence.setContactAgence(Integer.parseInt(contactAgence.getText()));
        selectedAgence.setAdresseAgence(AdresseAgence.getText());
         if(imageFile!=null){
            selectedAgence.setNom_image(imageFile.getName());
        }
        AS.update(selectedAgence);
        upload(false);
        initTable();
        resetFormulaire();
    }
    
    private void addAgence(){
        Agence agence = new Agence();
        upload(true);
        agence.setNomAgence(nomAg11.getText());
        agence.setChaine(descAgence11.getText());
        agence.setContactAgence(Integer.parseInt(contactAgence11.getText()));
        agence.setAdresseAgence(AdresseAgence11.getText());
        if(newImageFile!=null){
            agence.setNom_image(newImageFile.getName());
        }
        AS.createAgence(agence);
        initTable();
        resetFormulaire();
    }
    
     private void deleteAgence(){
        AS.delete(selectedAgence.getId());
        initTable();
        resetFormulaire();
    }
     
    
    public void initTable(){
        AgencesObservable = FXCollections.observableArrayList(AS.getAll(""));
        nomAgence.setCellValueFactory(new PropertyValueFactory<>("nomAgence"));
        adresseAgence.setCellValueFactory(new PropertyValueFactory<>("adresseAgence"));
        ContactAgence.setCellValueFactory(new PropertyValueFactory<>("ContactAgence"));
        chaine.setCellValueFactory(new PropertyValueFactory<>("chaine"));
        nom_image.setCellValueFactory(new PropertyValueFactory<>("nom_image"));
        TableAgence.setItems(AgencesObservable);

    }
    
    
    
    public void SelectImage(boolean newFile){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        Stage stage =new Stage();
        if(newFile){
           newImageFile = fileChooser.showOpenDialog(stage);
            if(newImageFile!=null){
                imgtxt11.setText(newImageFile.getName());
            }  
        }else{
            imageFile = fileChooser.showOpenDialog(stage);
            if(imageFile!=null){
                imgtxt.setText(imageFile.getName());
            }  
        }
       


    }

    public void upload(boolean newFile){
            String AbsolutePath="C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\Russie2018\\src\\Resources\\images\\agence_img\\";
            
            try {
                
                File newfile;
                FileChannel src;
                if(newFile){
                   newfile = new File(AbsolutePath+newImageFile.getName());
                   src = new FileInputStream(newImageFile).getChannel(); 
                }else{
                   newfile = new File(AbsolutePath+imageFile.getName());
                   src = new FileInputStream(imageFile).getChannel(); 
                }

                FileChannel dest = new FileOutputStream(newfile).getChannel();
                dest.transferFrom(src, 0, src.size());
                dest.close();
                imageFile=null;
                newImageFile=null;
            } catch (IOException e) {
            }
    }
    
    
    
    
    
}
