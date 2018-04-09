package russie2018;
import entities.User;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Russie2018 extends Application{
    
    public static User CurrentUser;
      @Override
    public void start(Stage stage) throws Exception {
        	  
   	//URL location = getClass().getResource("/Views/agence/fxml/ListerOffreFXML.fxml");
   	URL location = getClass().getResource("/russie2018/Login.fxml");
   // 	URL location = getClass().getResource("/Views/agence/fxml/frontAgence.fxml");
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
    

