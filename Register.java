package sample;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Register {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameField;

    @FXML
    private Button buttonSingIn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField nameID;

    @FXML
    private TextField surnameID;

    @FXML
    private TextField emailID;

    @FXML
    void initialize() {
        buttonSingIn.setOnAction(event -> {
            singInNewUser();

        });
    }

    private void singInNewUser() {
        DataBase dataBase = new DataBase();

        String name = nameID.getText();
        String surname = surnameID.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailID.getText();

        User user = new User(name, surname, username, password, email);

        dataBase.singIN(user);
        buttonSingIn.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/homePage.fxml"));

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

