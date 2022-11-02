package Controllers;

import Users.Employee;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
public class DBController {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "1503ROME";
    public static final String URL = "jdbc:mysql://localhost:3306/final_kp_users_database";
    public static Statement statement;
    public static Statement statement2;
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    static {
        try {
            statement2 = connection.createStatement();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public int getUserIdByFIO(String gSurname, String gName, String gOtchestvo) throws ClassNotFoundException, SQLException {
        int Id = -1;
        Class.forName("com.mysql.cj.jdbc.Driver");
        ResultSet resultSet = statement.executeQuery("SELECT UserId FROM users WHERE Surname = " + "'" + gSurname + "'" +
                " AND Name = " + "'" + gName + "'" + " AND Otchestvo = " + "'" + gOtchestvo + "'");
        if(resultSet.next()) Id = resultSet.getInt("UserId");
        return Id;
    }

    public boolean CompareIdPassword(int Id, String Password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ResultSet resultSet = statement.executeQuery("SELECT UserPass FROM authdata WHERE UserId = '" + Id + "'" );
        if (resultSet.next()) return resultSet.getString("UserPass").equals(Password);
        return false;
    }

    public boolean ComparePasswordByFIO(String gSurname, String gName, String gOtchestvo, String Password) throws SQLException, ClassNotFoundException {
        int id = getUserIdByFIO(gSurname, gName, gOtchestvo);
        if (id == -1) return false;
        return CompareIdPassword(id, Password);
    }

    public int getUserPositionIdById(int userId)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement.executeQuery("select PositionId FROM users WHERE UserId = '" + userId + "'");
            if(resultSet.next()) return resultSet.getInt("PositionId");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getPositionByPositionId(int positionId)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement.executeQuery("select Position FROM positions WHERE PositionId = '" + positionId + "'");
            if(resultSet.next()) return resultSet.getString("Position");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getPositionIdByPosition(String position)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement.executeQuery("select PositionId FROM positions WHERE Position = '" + position + "'");
            if(resultSet.next()) return resultSet.getInt("PositionId");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public double getPositionZppHByPositionId(int positionId)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String position = getPositionByPositionId(positionId);
            ResultSet resultSet = statement.executeQuery("select ZPpH FROM positionzpph WHERE Position = '" + position + "'");
            if(resultSet.next()) return resultSet.getDouble("ZPpH");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getAddZppHByUserId(int userId)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement.executeQuery("select AddZPpH FROM userzpphinfo WHERE UserId = '" + userId + "'");
            if(resultSet.next()) return resultSet.getDouble("AddZPpH");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getExtraByUserId(int userId)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement.executeQuery("select ExtraMoney FROM userzpphinfo WHERE UserId = '" + userId + "'");
            if(resultSet.next()) return resultSet.getDouble("ExtraMoney");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public boolean getIsExtraByUserId(int userId)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement.executeQuery("select IsExtraMoney FROM userzpphinfo WHERE UserId = '" + userId + "'");
            if(resultSet.next()) return resultSet.getBoolean("IsExtraMoney");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getWorkHoursByUserId(int userId)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement.executeQuery("select WorkHours FROM userzpphinfo WHERE UserId = '" + userId + "'");
            if(resultSet.next()) return resultSet.getDouble("WorkHours");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getIllHoursByUserId(int userId)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement.executeQuery("select IllHours FROM userzpphinfo WHERE UserId = '" + userId + "'");
            if(resultSet.next()) return resultSet.getDouble("IllHours");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public void fiilTable(ObjectInputStream ois, ObjectOutputStream oos)
    {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement2.executeQuery("SELECT * FROM users");
            while (resultSet.next())
            {
                String isExtra;
                oos.writeObject("Continue");
                oos.writeObject(resultSet.getInt("UserId"));
                oos.writeObject(resultSet.getString("Surname"));
                oos.writeObject(resultSet.getString("Name"));
                oos.writeObject(resultSet.getString("Otchestvo"));
                oos.writeObject(getPositionByPositionId(resultSet.getInt("PositionId")));
                oos.writeObject(getAddZppHByUserId(resultSet.getInt("UserId")));
                oos.writeObject(getExtraByUserId(resultSet.getInt("UserId")));
                if (getIsExtraByUserId(resultSet.getInt("UserId")))
                {
                    isExtra = "Да";
                }
                else
                {
                    isExtra = "Нет";
                }
                oos.writeObject(isExtra);
                oos.writeObject(getWorkHoursByUserId(resultSet.getInt("UserId")));
                oos.writeObject(getIllHoursByUserId(resultSet.getInt("UserId")));
            }
            oos.writeObject("End!");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public int addUser(String surname, String name, String othestvo, String position, String password)
    {
        int posId = getPositionIdByPosition(position);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String insertRequest = "INSERT INTO users (Surname, Name, Otchestvo, PositionId) VALUES(?,?,?,?)";
            PreparedStatement prST = connection.prepareStatement(insertRequest);
            prST.setString(1, surname);
            prST.setString(2, name);
            prST.setString(3, othestvo);
            prST.setInt(4, posId);
            prST.executeUpdate();
            insertRequest = "INSERT INTO authdata (UserPass) Values(?)";
            prST = connection.prepareStatement(insertRequest);
            prST.setString(1, password);
            prST.executeUpdate();
            insertRequest = "INSERT INTO userzpphinfo (AddZPpH, ExtraMoney, IllHours, IsExtraMoney, WorkHours) Values(?,?,?,?,?)";
            prST = connection.prepareStatement(insertRequest);
            prST.setDouble(1, 0.0);
            prST.setDouble(2, 0.0);
            prST.setDouble(3, 0.0);
            prST.setBoolean(4, false);
            prST.setDouble(5, 0.0);
            prST.executeUpdate();
            return getUserIdByFIO(surname, name, othestvo);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void deleteUser(int userIdD)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String delRequest = "DELETE FROM authdata WHERE UserId = ?";
            PreparedStatement prST = connection.prepareStatement(delRequest);
            prST.setInt(1, userIdD);
            prST.executeUpdate();
            delRequest = "DELETE FROM userzpphinfo WHERE UserId = ?";
            prST = connection.prepareStatement(delRequest);
            prST.setInt(1, userIdD);
            prST.executeUpdate();
            delRequest = "DELETE FROM users WHERE UserId = ?";
            prST = connection.prepareStatement(delRequest);
            prST.setInt(1, userIdD);
            prST.executeUpdate();
            System.out.println("Пользователь " + userIdD + " удалён");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBaseZp(double amount, String position)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String delRequest = "UPDATE positionzpph SET ZPpH = ? WHERE Position = ?";
            PreparedStatement prST = connection.prepareStatement(delRequest);
            prST.setDouble(1, amount);
            prST.setString(2, position);
            prST.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUserAddZppH(int UserId, double AddZPpH)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String delRequest = "UPDATE userzpphinfo SET AddZPpH = ? WHERE UserId = ?";
            PreparedStatement prST = connection.prepareStatement(delRequest);
            prST.setDouble(1, AddZPpH);
            prST.setInt(2, UserId);
            prST.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void setUserExtraMoney(int UserId, double amount)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String delRequest = "UPDATE userzpphinfo SET ExtraMoney = ? WHERE UserId = ?";
            PreparedStatement prST = connection.prepareStatement(delRequest);
            prST.setDouble(1, amount);
            prST.setInt(2, UserId);
            prST.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUserIllHours(int UserId, double amount)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String delRequest = "UPDATE userzpphinfo SET IllHours = ? WHERE UserId = ?";
            PreparedStatement prST = connection.prepareStatement(delRequest);
            prST.setDouble(1, amount);
            prST.setInt(2, UserId);
            prST.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUserIsExtraMoney(int UserId, boolean is)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String delRequest = "UPDATE userzpphinfo SET IsExtraMoney = ? WHERE UserId = ?";
            PreparedStatement prST = connection.prepareStatement(delRequest);
            prST.setBoolean(1, is);
            prST.setInt(2, UserId);
            prST.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUserWorkHours(int UserId, double amount)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String delRequest = "UPDATE userzpphinfo SET WorkHours = ? WHERE UserId = ?";
            PreparedStatement prST = connection.prepareStatement(delRequest);
            prST.setDouble(1, amount);
            prST.setInt(2, UserId);
            prST.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void resultTable(ObjectInputStream ois, ObjectOutputStream oos)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement2.executeQuery("SELECT * FROM users");
            while (resultSet.next())
            {
                String surname = resultSet.getString("Surname");
                String name = resultSet.getString("Name");
                String otchestvo = resultSet.getString("Otchestvo");
                int userId = resultSet.getInt("UserId");
                int userPositionId = resultSet.getInt("PositionId");
                Employee emp = new Employee(surname, name, otchestvo, userId, userPositionId);
                oos.writeObject("Continue");
                oos.writeObject(userId);
                oos.writeObject(surname);
                oos.writeObject(name);
                oos.writeObject(otchestvo);
                oos.writeObject(getPositionByPositionId(userPositionId));
                oos.writeObject(emp.getFinalZp());
            }
            oos.writeObject("End!");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void pieChartUpdate(ObjectInputStream ois, ObjectOutputStream oos)
    {
        try {
            double allBaseZP = 0;
            double allAddZppH = 0;
            double allExtraMoney = 0;
            double allNotExtraMoney = 0;
            double totalZP = 0;
            double finalZP = 0;
            double allNotAddZppH = 0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet resultSet = statement2.executeQuery("SELECT * FROM users");
            while (resultSet.next())
            {
                String surname = resultSet.getString("Surname");
                String name = resultSet.getString("Name");
                String otchestvo = resultSet.getString("Otchestvo");
                int userid = resultSet.getInt("UserId");
                int userpositionid = resultSet.getInt("PositionId");
                Employee employee = new Employee(surname, name, otchestvo, userid, userpositionid);
                totalZP += employee.getTotalZp();
                finalZP += employee.getFinalZp();
                allExtraMoney += employee.getExtra();
                allBaseZP += employee.getTotalBaseZP();
                allAddZppH += employee.getTotalAddZppH();
                allNotExtraMoney += employee.getTotalNotExtraMoney();
                allNotAddZppH += employee.getTotalNotAddZppH();
            }
            oos.writeObject(totalZP);
            oos.writeObject(finalZP);
            oos.writeObject(allExtraMoney);
            oos.writeObject(allNotExtraMoney);
            oos.writeObject(allAddZppH);
            oos.writeObject(allNotAddZppH);
            oos.writeObject(allBaseZP);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
