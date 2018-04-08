package Views;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminMenuController implements Initializable{

      @FXML
    private AnchorPane contentPane;

    @FXML
    private AnchorPane topBar;

    @FXML
    private Label labelAdministration;

    @FXML
    private Pane sidenavBG;

    @FXML
    private Pane sidenavLayer;

    @FXML
    private AnchorPane bottomPanel;

    @FXML
    private Pane loadFront;

    @FXML
    private Pane disconnect;

    @FXML
    private Pane sidenavFront;

    @FXML
    private Pane loadOffre;

    @FXML
    private Pane loadAgence;
    
    @FXML
    private Label menuTitle;
    
    @FXML
    private Label username;

    private boolean isAdmin=false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(User.getInstance()==null){
            System.out.println("no user");
        }else{
            username.setText(User.getInstance().getFname()+" "+User.getInstance().getLname());
            if(User.getInstance().getRoles().contains("ROLE_ADMIN"))
            {
                menuTitle.setText("Administration");
                isAdmin = true;
            }else{
                menuTitle.setText("Russie 2018");
                isAdmin = false;
            }
        }
        loadOffre.setOnMouseClicked(e->{contentPane.getChildren().clear(); openOffre();});
        loadAgence.setOnMouseClicked(e->{contentPane.getChildren().clear();openAgence();});
        disconnect.setOnMouseClicked(e->{onLogout();});
    }
    
    public void openOffre(){
        if(isAdmin)
        loadFXML("offre/fxml/ListerOffreFXML");
        else
        contentPane.getChildren().add(new Label("Offre Client"));
    }
    
    public void openAgence(){
        if(isAdmin)
            loadFXML("agence/fxml/ListerAgenceFXML");
        else
            loadFXML("agence/fxml/frontAgence");
    }
    
     private void loadFXML(String fxmlName) {
        Pane newLoadedPane = new Pane();
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource("/Views/" + fxmlName + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        contentPane.getChildren().add(newLoadedPane);
        newLoadedPane.sceneToLocal(200, 200);
       
        // The used google maps api doesn't support using multiple maps.
        // Reloading the page from inside fixes the problem when alternating between the maps.
       
    }
     
    public void onLogout()  {
            User.setInstance(null);
            URL location = getClass().getResource("/russie2018/Login.fxml");
            Stage st = (Stage) disconnect.getScene().getWindow();
            st.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            Pane root;
            try {
              root = (Pane) fxmlLoader.load();
              Scene scene = new Scene(root);
              stage.setScene(scene);
              stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }


}