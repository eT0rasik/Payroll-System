package Client;

import Connectors.ObjectStreams;
import Controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application {
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SignInMenu.fxml"));
        primaryStage.setTitle("КП");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            System.out.println("Подключение к серверу");
            Socket clientSocket = new Socket("127.0.0.1", 2525);
            System.out.println("Соединение установлено");
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectStreams.oos = oos;
            ObjectStreams.ois = ois;
            launch(args);

            oos.close();
            ois.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
