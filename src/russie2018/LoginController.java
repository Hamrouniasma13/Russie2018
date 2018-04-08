/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package russie2018;

import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class LoginController implements Initializable {

    
    @FXML
    private PasswordField pwTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private Button loginBtn;
    
    @FXML
    private Label errorMsg;
    
    ServiceUser serviceUser;

    @FXML
    void onLogin(ActionEvent event) throws SQLException, IOException {
        
        String email = emailTxt.getText();
        errorMsg.setText("");
        String password = pwTxt.getText();
        String message = serviceUser.Login(email, password);
        if(message.equals("successful")){
            Stage st = (Stage) loginBtn.getScene().getWindow();
            st.close();
            Stage stage = new Stage();
            URL location = getClass().getResource("/Views/AdminMenu.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            Pane root = (Pane) fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            errorMsg.setText(message);
        }
          
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceUser = new ServiceUser();
    }    
    
}
