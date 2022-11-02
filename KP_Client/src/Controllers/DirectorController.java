package Controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Connectors.ObjectStreams;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import pojo.User;

public class DirectorController {
    ObjectOutputStream oos = ObjectStreams.oos;
    ObjectInputStream ois = ObjectStreams.ois;

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private TextField amount_extramoney_field;

    @FXML
    private CheckBox isextramoney_checkbox;

    @FXML
    private Button set_extramoney_button;

    @FXML
    private Button set_isextramoney_button;

    @FXML
    private TextField amount_addzpph_field;

    @FXML
    private TableView<User> db_table;

    @FXML
    private TableColumn<User, String> name_column;

    @FXML
    private TableColumn<User, String> otchestvo_column;

    @FXML
    private TableColumn<User, String> position_column;

    @FXML
    private TextField amount_workhours_field;

    @FXML
    private Button set_workhours_button;

    @FXML
    private Button set_addzpph_button;

    @FXML
    private TableColumn<User, String> surname_column;

    @FXML
    private Button update_table_button;

    @FXML
    private TextField usertoorkId_field;

    @FXML
    private TableColumn<User, Integer> user_id_column;

    @FXML
    private TableColumn<User, Double> addZpph_column;

    @FXML
    private TableColumn<User, Double> extraMoney_column;

    @FXML
    private TableColumn<User, Double> illHours_column;

    @FXML
    private TableColumn<User, String> isExtraMoney_column;

    @FXML
    private TableColumn<User, Double> wotkHours_column;

    @FXML
    private Text name;

    @FXML
    private Text othestvo;

    @FXML
    private Text position;

    @FXML
    private Text surname;

    @FXML
    private Text userid;

    @FXML
    private Button zp;

    @FXML
    private Button zpph;

    @FXML
    private Text result1;

    @FXML
    private Text result2;

    @FXML
    private Button hours;

    @FXML
    private Button extra;

    @FXML
    private Label addSuccess_label;

    @FXML
    private TextField addUser_name_field;

    @FXML
    private TextField addUser_otchestvo_field;

    @FXML
    private TextField addUser_password_field;

    @FXML
    private TextField addUser_surname_field;

    @FXML
    private Button add_user_button;

    @FXML
    private TextField amount_baseZp_field;

    @FXML
    private Button delete_user_button;

    @FXML
    private Label finalZP_label;

    @FXML
    private Button pieChart_Update_button;

    @FXML
    private PieChart pie_ZpStat_chart;

    @FXML
    private ToggleGroup positions;

    @FXML
    private ToggleGroup positions_1;

    @FXML
    private RadioButton rb_position_buhg;

    @FXML
    private RadioButton rb_position_buhg1;

    @FXML
    private RadioButton rb_position_dir;

    @FXML
    private RadioButton rb_position_dir1;

    @FXML
    private RadioButton rb_position_manager;

    @FXML
    private RadioButton rb_position_manager1;

    @FXML
    private Button set_base_zp_button;

    @FXML
    private Label totalZP_label;

    @FXML
    private TextField user_to_del_id_field;

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
            ObjectStreams.oos.writeObject("FillTable");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (true)
        {
            try {
                if ((((String) ObjectStreams.ois.readObject()).equals("End!"))) break;
                usersData.add(new User((int)ObjectStreams.ois.readObject(),
                        (String)ObjectStreams.ois.readObject(),
                        (String)ObjectStreams.ois.readObject(),
                        (String)ObjectStreams.ois.readObject(),
                        (String)ObjectStreams.ois.readObject(),
                        (double)ObjectStreams.ois.readObject(),
                        (double)ObjectStreams.ois.readObject(),
                        (String) ObjectStreams.ois.readObject(),
                        (double)ObjectStreams.ois.readObject(),
                        (double)ObjectStreams.ois.readObject()));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        }

        user_id_column.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        surname_column.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        name_column.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        otchestvo_column.setCellValueFactory(new PropertyValueFactory<User, String>("otchestvo"));
        position_column.setCellValueFactory(new PropertyValueFactory<User, String>("position"));
        addZpph_column.setCellValueFactory(new PropertyValueFactory<User, Double>("addZppH"));
        extraMoney_column.setCellValueFactory(new PropertyValueFactory<User, Double>("extraMoney"));
        isExtraMoney_column.setCellValueFactory(new PropertyValueFactory<User, String>("isExtraMoney"));
        illHours_column.setCellValueFactory(new PropertyValueFactory<User, Double>("illHours"));
        wotkHours_column.setCellValueFactory(new PropertyValueFactory<User, Double>("workHours"));

        db_table.setItems(usersData);

        update_table_button.setOnAction(event -> {
            ObservableList<User> usersDataRepeat = FXCollections.observableArrayList();
            try {
                ObjectStreams.oos.writeObject("FillTable");
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true)
            {
                try {
                    if ((((String) ObjectStreams.ois.readObject()).equals("End!"))) break;
                    usersDataRepeat.add(new User((int)ObjectStreams.ois.readObject(),
                            (String)ObjectStreams.ois.readObject(),
                            (String)ObjectStreams.ois.readObject(),
                            (String)ObjectStreams.ois.readObject(),
                            (String)ObjectStreams.ois.readObject(),
                            (double)ObjectStreams.ois.readObject(),
                            (double)ObjectStreams.ois.readObject(),
                            (String) ObjectStreams.ois.readObject(),
                            (double)ObjectStreams.ois.readObject(),
                            (double)ObjectStreams.ois.readObject()));
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }

            db_table.getItems().clear();

            user_id_column.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
            surname_column.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
            name_column.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
            otchestvo_column.setCellValueFactory(new PropertyValueFactory<User, String>("otchestvo"));
            position_column.setCellValueFactory(new PropertyValueFactory<User, String>("position"));
            addZpph_column.setCellValueFactory(new PropertyValueFactory<User, Double>("addZppH"));
            extraMoney_column.setCellValueFactory(new PropertyValueFactory<User, Double>("extraMoney"));
            isExtraMoney_column.setCellValueFactory(new PropertyValueFactory<User, String>("isExtraMoney"));
            illHours_column.setCellValueFactory(new PropertyValueFactory<User, Double>("illHours"));
            wotkHours_column.setCellValueFactory(new PropertyValueFactory<User, Double>("workHours"));

            db_table.setItems(usersDataRepeat);
        });

        set_addzpph_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("SetUserAddZPpH");
                ObjectStreams.oos.writeObject(usertoorkId_field.getText());
                ObjectStreams.oos.writeObject(amount_addzpph_field.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        set_workhours_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("SetUserWorkHours");
                ObjectStreams.oos.writeObject(usertoorkId_field.getText());
                ObjectStreams.oos.writeObject(amount_workhours_field.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        set_extramoney_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("SetUserExtraMoney");
                ObjectStreams.oos.writeObject(usertoorkId_field.getText());
                ObjectStreams.oos.writeObject(amount_extramoney_field.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        set_isextramoney_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("SetUserIsExtraMoney");
                ObjectStreams.oos.writeObject(usertoorkId_field.getText());
                if (isextramoney_checkbox.isSelected())
                {
                    ObjectStreams.oos.writeObject(true);
                } else
                {
                    ObjectStreams.oos.writeObject(false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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

        pieChart_Update_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("UpdatePieChart");
                double totalZP = (double)ObjectStreams.ois.readObject();
                double finalZP = (double)ObjectStreams.ois.readObject();
                double allExtraMoney = (double)ObjectStreams.ois.readObject();
                double allNotExtraMoney = (double)ObjectStreams.ois.readObject();
                double allAddZppH = (double)ObjectStreams.ois.readObject();
                double allNotAddZppH = (double)ObjectStreams.ois.readObject();
                double allBaseZP = (double)ObjectStreams.ois.readObject();

                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Базавая ЗП", allBaseZP),
                        new PieChart.Data("Премия", allExtraMoney - allNotExtraMoney),
                        new PieChart.Data("Не выпл. премия", allNotExtraMoney),
                        new PieChart.Data("Надбавка", allAddZppH),
                        new PieChart.Data("Не выпл. надбавка", allNotAddZppH));

                pieChartData.forEach(data -> data.nameProperty().bind(
                        Bindings.concat(
                                data.getName()," ", data.pieValueProperty(), " BYN"
                        )
                ));

                pie_ZpStat_chart.getData().clear();
                pie_ZpStat_chart.getData().addAll(pieChartData);

                totalZP_label.setText("Максимальная суммарная ЗП: " + (totalZP+allNotAddZppH));
                finalZP_label.setText("Итоговая суммарная ЗП: " + finalZP);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        add_user_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("Add_User");
                String aUSurname = addUser_surname_field.getText();
                String aUName = addUser_name_field.getText();
                String aUOtchestvo = addUser_otchestvo_field.getText();
                String aUPassword = addUser_password_field.getText();
                String position = null;
                if (rb_position_buhg.isSelected()) position = rb_position_buhg.getText();
                if (rb_position_manager.isSelected()) position = rb_position_manager.getText();
                if (rb_position_dir.isSelected()) position = rb_position_dir.getText();
                ObjectStreams.oos.writeObject(aUSurname);
                ObjectStreams.oos.writeObject(aUName);
                ObjectStreams.oos.writeObject(aUOtchestvo);
                ObjectStreams.oos.writeObject(aUPassword);
                ObjectStreams.oos.writeObject(position);
                int addUserId = (int)ObjectStreams.ois.readObject();
                addSuccess_label.setText("Пользователь добавлен. ID: " + addUserId);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        set_base_zp_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("SetBaseZP");
                ObjectStreams.oos.writeObject(amount_baseZp_field.getText());
                String position = null;
                if (rb_position_buhg1.isSelected()) position = rb_position_buhg1.getText();
                if (rb_position_manager1.isSelected()) position = rb_position_manager1.getText();
                if (rb_position_dir1.isSelected()) position = rb_position_dir1.getText();
                ObjectStreams.oos.writeObject(position);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        delete_user_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("Delete");
                ObjectStreams.oos.writeObject(user_to_del_id_field.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
