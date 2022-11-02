package Controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import Connectors.ObjectStreams;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class UserController {
    ObjectOutputStream oos = ObjectStreams.oos;
    ObjectInputStream ois = ObjectStreams.ois;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button extra;

    @FXML
    private Button hours;

    @FXML
    private Text name;

    @FXML
    private Text othestvo;

    @FXML
    private Text position;

    @FXML
    private Text result1;

    @FXML
    private Text result2;

    @FXML
    private Text surname;

    @FXML
    private Text userid;

    @FXML
    private Button zp;

    @FXML
    private Button zpph;

    @FXML
    void initialize() {
        try {
            String u_Surname = (String)ois.readObject();
            String u_Name = (String)ois.readObject();
            String u_Otchestvo = (String)ois.readObject();
            String u_Position = (String)ois.readObject();
            int u_Id = (int) ois.readObject();
            surname.setText(u_Surname);
            name.setText(u_Name);
            othestvo.setText(u_Otchestvo);
            position.setText(u_Position);
            userid.setText(""+u_Id);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        zp.setOnAction(event -> {
            try {
                oos.writeObject("Final");
            } catch (IOException e) {
                e.printStackTrace();
            }

            result1.setText("Заработная плата в этом месяце: ");
            try {
                result2.setText(""+(double)ois.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        hours.setOnAction(event -> {
            try {
                oos.writeObject("Hours");
                result1.setText("Всего рабочих часов в этом месяце: " + (double)ois.readObject());
                result2.setText("Часов на больничном в этом месяце: " + (double)ois.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        });

        extra.setOnAction(event -> {
            try {
                oos.writeObject("Extra");
                result1.setText("Премия в этом месяце: " + (double)ois.readObject());
                if ((boolean)ois.readObject())
                {
                    result2.setText("Премия в этом месяце будет начислена");
                }
                else
                {
                    result2.setText("Вы лишены премии в этом месяце");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        zpph.setOnAction(event -> {
            try {
                oos.writeObject("ZppH");
                result1.setText("Базовая ЗП в час на вашей должности: " + (double)ois.readObject());
                result2.setText("Надбавочная ЗП в час: " + (double)ois.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

}
