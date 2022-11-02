package Controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import Connectors.ObjectStreams;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignInController {
    ObjectOutputStream oos = ObjectStreams.oos;
    ObjectInputStream ois = ObjectStreams.ois;
    boolean success = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text ErrText;

    @FXML
    private Button logIn_Button;

    @FXML
    private TextField name_field;

    @FXML
    private TextField otchestvo_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField surname_field;

    @FXML
    void initialize() {
        logIn_Button.setOnAction(event -> {
            try {
                oos.writeObject("Меню Входа");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String name = name_field.getText();
            String surname = surname_field.getText();
            String otchestvo = otchestvo_field.getText();
            String password = password_field.getText();
            try {
                oos.writeObject(surname);
                oos.writeObject(name);
                oos.writeObject(otchestvo);
                oos.writeObject(password);
                success = (Boolean) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (!success) {
                ErrText.setText("Неверный пароль или ФИО, повторите попытку");
            }
            if (success)
            {
                String serverMessage = null;
                logIn_Button.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                Parent root;
                Stage stage;
                try {
                    serverMessage = (String) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                switch (serverMessage)
                {
                    //Admin
                    case "1":
                        loader.setLocation(getClass().getResource("/Client/AdminMenu.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        root = loader.getRoot();
                        stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.showAndWait();
                        break;
                    //Buhgalter
                    case "3":
                        loader.setLocation(getClass().getResource("/Client/BuhgalterMenu.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        root = loader.getRoot();
                        stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                        break;
                    //Director
                    case "2":
                        loader.setLocation(getClass().getResource("/Client/DirectorMenu.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        root = loader.getRoot();
                        stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.showAndWait();
                        break;
                    //Employee
                    case "4":
                        loader.setLocation(getClass().getResource("/Client/UserMenu.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        root = loader.getRoot();
                        stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.showAndWait();
                        break;
                }
            }
        });

    }

}
