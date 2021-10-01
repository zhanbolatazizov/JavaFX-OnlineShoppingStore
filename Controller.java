package sample;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameField;

    @FXML
    private Button singinButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    void initialize() {

        singinButton.setOnAction(event -> {
            String usernameText = usernameField.getText().trim();
            String passwordText = passwordField.getText().trim();

            if(!usernameText.equals("") && !passwordText.equals("")){
                loginUser(usernameText, passwordText);
            } else {
                System.out.println("Username and password is empty");
            }
        });

        registerButton.setOnAction(event -> {
            registerButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/register.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } );

    }

    private void loginUser(String usernameText, String passwordText) {
        DataBase dataBase = new DataBase();
        User user = new User();
        user.setUsername(usernameText);
        user.setPassword(passwordText);
        ResultSet resultSet = dataBase.login(user);

        int log = 0;

        try{
            while(resultSet.next()) {
                log++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(log >= 1) {
            scene("/sample/homePage.fxml");
        }

    }

    public void scene(String scene) {
        singinButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(scene));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}