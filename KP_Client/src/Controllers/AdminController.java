package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Connectors.ObjectStreams;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.User;
import pojo.UserResult;

public class AdminController {

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TextField amount_extramoney_field;

    @FXML
    private TextField amount_illhours_field;

    @FXML
    private CheckBox isextramoney_checkbox;

    @FXML
    private Button set_extramoney_button;

    @FXML
    private Button set_illhours_button;

    @FXML
    private Button set_isextramoney_button;

    @FXML
    private TextField amount_addzpph_field;

    @FXML
    private TextField amount_baseZp_field;

    @FXML
    private TableView<User> db_table;

    @FXML
    private TableView<UserResult> result_table;

    @FXML
    private Button delete_user_button;

    @FXML
    private TableColumn<User, String> name_column;

    @FXML
    private TableColumn<User, String> otchestvo_column;

    @FXML
    private TableColumn<User, String> position_column;

    @FXML
    private ToggleGroup positions;

    @FXML
    private ToggleGroup positions1;

    @FXML
    private TextField amount_workhours_field;

    @FXML
    private Button set_workhours_button;

    @FXML
    private RadioButton rb_position_adm;

    @FXML
    private RadioButton rb_position_adm1;

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
    private Button set_addzpph_button;

    @FXML
    private Button set_base_zp_button;

    @FXML
    private TableColumn<User, String> surname_column;

    @FXML
    private Button update_table_button;

    @FXML
    private TextField usertoorkId_field;

    @FXML
    private TableColumn<User, Integer> user_id_column;

    @FXML
    private TextField user_to_del_id_field;

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
    private Button finalZpResult_button_1;

    @FXML
    private Label finalZpResult_label;

    @FXML
    private TableColumn<UserResult, Integer> resultID_column;

    @FXML
    private TableColumn<UserResult, String> resultName_column;

    @FXML
    private TableColumn<UserResult, String> resultOtxhestvo_column;

    @FXML
    private TableColumn<UserResult, String> resultPositon_column;

    @FXML
    private TableColumn<UserResult, String> resultSurname_column;

    @FXML
    private TableColumn<UserResult, Double> resultZP_column;

    @FXML
    private Button resultTableUpdate_button;

    @FXML
    private PieChart pie_ZpStat_chart;

    @FXML
    private Button pieChart_Update_button;

    @FXML
    private Label finalZP_label;

    @FXML
    private Label totalZP_label;

    @FXML
    void initialize() {
        try {
            ObjectStreams.oos.writeObject("FillTable");
        } catch (IOException e) {
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

        add_user_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("Add_User");
                String aUSurname = addUser_surname_field.getText();
                String aUName = addUser_name_field.getText();
                String aUOtchestvo = addUser_otchestvo_field.getText();
                String aUPassword = addUser_password_field.getText();
                String position = null;
                if (rb_position_adm.isSelected()) position = rb_position_adm.getText();
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

        delete_user_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("Delete");
                ObjectStreams.oos.writeObject(user_to_del_id_field.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        set_base_zp_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("SetBaseZP");
                ObjectStreams.oos.writeObject(amount_baseZp_field.getText());
                String position = null;
                if (rb_position_adm1.isSelected()) position = rb_position_adm1.getText();
                if (rb_position_buhg1.isSelected()) position = rb_position_buhg1.getText();
                if (rb_position_manager1.isSelected()) position = rb_position_manager1.getText();
                if (rb_position_dir1.isSelected()) position = rb_position_dir1.getText();
                ObjectStreams.oos.writeObject(position);
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        set_illhours_button.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("SetUserIllHours");
                ObjectStreams.oos.writeObject(usertoorkId_field.getText());
                ObjectStreams.oos.writeObject(amount_illhours_field.getText());
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

        finalZpResult_button_1.setOnAction(event -> {
            try {
                ObjectStreams.oos.writeObject("FinalZP");
                ObjectStreams.oos.writeObject(usertoorkId_field.getText());
                finalZpResult_label.setText("Результат: " + (double)ObjectStreams.ois.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        resultTableUpdate_button.setOnAction(event -> {
            ObservableList<UserResult> usersDataRepeat = FXCollections.observableArrayList();
            try {
                ObjectStreams.oos.writeObject("ResultTable");
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true)
            {
                try {
                    if ((((String) ObjectStreams.ois.readObject()).equals("End!"))) break;
                    usersDataRepeat.add(new UserResult((int)ObjectStreams.ois.readObject(),
                            (String)ObjectStreams.ois.readObject(),
                            (String)ObjectStreams.ois.readObject(),
                            (String)ObjectStreams.ois.readObject(),
                            (String)ObjectStreams.ois.readObject(),
                            (double)ObjectStreams.ois.readObject()));
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }

            result_table.getItems().clear();

            resultID_column.setCellValueFactory(new PropertyValueFactory<UserResult, Integer>("id"));
            resultSurname_column.setCellValueFactory(new PropertyValueFactory<UserResult, String>("surname"));
            resultName_column.setCellValueFactory(new PropertyValueFactory<UserResult, String>("name"));
            resultOtxhestvo_column.setCellValueFactory(new PropertyValueFactory<UserResult, String>("otchestvo"));
            resultPositon_column.setCellValueFactory(new PropertyValueFactory<UserResult, String>("position"));
            resultZP_column.setCellValueFactory(new PropertyValueFactory<UserResult, Double>("finalZP"));

            result_table.setItems(usersDataRepeat);
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
    }

}
