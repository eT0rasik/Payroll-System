package Controllers;

import java.io.*;
import java.sql.SQLException;

public class SignInController {
    ObjectOutputStream oos;
    ObjectInputStream ois;
    boolean check = false;
    int positionId;
    int userId;
    String surname;
    String name;
    String otchestvo;

    public SignInController(ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException, SQLException {
        this.oos = oos;
        this.ois = ois;
        //Получение данных авторизации от клиента
        String surname = (String) ois.readObject();
        String name = (String) ois.readObject();
        String otchestvo = (String) ois.readObject();
        String password = (String) ois.readObject();
        //Проверка данных в БД
        DBController dbController = new DBController();
        this.check = dbController.ComparePasswordByFIO(surname, name, otchestvo, password);
        this.positionId = dbController.getUserPositionIdById(dbController.getUserIdByFIO(surname, name, otchestvo));
        this.userId = dbController.getUserIdByFIO(surname,name,otchestvo);
        this.surname = surname;
        this.name = name;
        this.otchestvo = otchestvo;
    }

    public boolean getCheck()
    {
        return check;
    }

    public int getPositionId() { return positionId; }

    public int getUserId() {
        return userId;
    }

    public String getSurname()
    {
        return this.surname;
    }

    public String getName()
    {
        return name;
    }

    public String getOtchestvo()
    {
        return otchestvo;
    }
}
