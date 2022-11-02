package server;

import Controllers.DBController;
import Controllers.SignInController;
import Users.Buhgalter;
import Users.Director;
import Users.Employee;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    public static void main(String[] args)
    {
        int userIdToSet;
        int userPositionId = -1;
        int userId = -1;
        String surname = null;
        String name = null;
        String otchestvo = null;
        ServerSocket serverSocket = null;
        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        String clientMessage;
        DBController dbController = new DBController();
        boolean done = false;
        try {
            System.out.println("Запуск сервера...");
            serverSocket = new ServerSocket(2525);
            socket = serverSocket.accept();
            System.out.println("Соединение установлено");
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            clientMessage = (String) ois.readObject();
            while (!clientMessage.equals("Выход")) {
                    System.out.println("message recieved: " + clientMessage + "");
                    if (clientMessage.equals("Меню Входа")) {
                        boolean success = false;
                        SignInController signInController = new SignInController(oos, ois);
                        oos.writeObject(signInController.getCheck());
                        success = signInController.getCheck();
                        if (success) {
                            userPositionId = signInController.getPositionId();
                            surname = signInController.getSurname();
                            name = signInController.getName();
                            otchestvo = signInController.getOtchestvo();
                            userId = signInController.getUserId();
                        }
                    }
                    switch (userPositionId) {
                        case 1:
                            oos.writeObject("1");
                            clientMessage = (String) ois.readObject();
                            while (!done) {
                                switch (clientMessage) {
                                    case "Add_User":
                                        System.out.println("Command getted: Add_User");
                                        String aUSurname = (String) ois.readObject();
                                        String aUName = (String) ois.readObject();
                                        String aUOtchestvo = (String) ois.readObject();
                                        String aUPassword = (String) ois.readObject();
                                        String aUPosition = (String) ois.readObject();
                                        oos.writeObject(dbController.addUser(aUSurname, aUName, aUOtchestvo, aUPosition, aUPassword));
                                        break;
                                    case "Delete":
                                        System.out.println("Command getted: Delete");
                                        int idUserD = Integer.parseInt((String) ois.readObject());
                                        dbController.deleteUser(idUserD);
                                        break;
                                    case "FillTable":
                                        System.out.println("Command getted: FillTable");
                                        dbController.fiilTable(ois, oos);
                                        break;
                                    case "SetBaseZP":
                                        System.out.println("Command getted: SetBaseZP");
                                        double amount = Double.parseDouble((String) ois.readObject());
                                        dbController.setBaseZp(amount, ((String) ois.readObject()));
                                        break;
                                    case "SetUserAddZPpH":
                                        System.out.println("Command getted: SetUserAddZPpH");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double addZppHamount = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserAddZppH(userIdToSet, addZppHamount);
                                        break;
                                    case "SetUserExtraMoney":
                                        System.out.println("Command getted: SetUserExtraMoney");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double extraMoney = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserExtraMoney(userIdToSet, extraMoney);
                                        break;
                                    case "SetUserIllHours":
                                        System.out.println("Command getted: SetUserIllHours");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double illHours = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserIllHours(userIdToSet, illHours);
                                        break;
                                    case "SetUserIsExtraMoney":
                                        System.out.println("Command getted: SetUserIsExtraMoney");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        boolean is = (boolean) ois.readObject();
                                        dbController.setUserIsExtraMoney(userIdToSet, is);
                                        break;
                                    case "SetUserWorkHours":
                                        System.out.println("Command getted: SetUserWorkHours");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double workHours = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserWorkHours(userIdToSet, workHours);
                                        break;
                                    case "FinalZP":
                                        System.out.println("Command getted: FinalZP");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        int positionIdToSet = dbController.getUserPositionIdById(userIdToSet);
                                        Employee employee = new Employee(name, surname, otchestvo, userIdToSet, positionIdToSet);
                                        oos.writeObject(employee.getFinalZp());
                                        break;
                                    case "ResultTable":
                                        System.out.println("Command getted: ResultTable");
                                        dbController.resultTable(ois, oos);
                                        break;
                                }
                                clientMessage = (String) ois.readObject();
                            }
                            break;
                        case 2:
                            Buhgalter buhgalter = new Buhgalter(surname, name, otchestvo, userId, userPositionId);
                            oos.writeObject("2");
                            oos.writeObject(surname);
                            oos.writeObject(name);
                            oos.writeObject(otchestvo);
                            oos.writeObject(dbController.getPositionByPositionId(userPositionId));
                            oos.writeObject(userId);
                            clientMessage = (String) ois.readObject();
                            while (!done) {
                                switch (clientMessage) {
                                    case "ZppH":
                                        oos.writeObject(buhgalter.getPositionZppH());
                                        oos.writeObject(buhgalter.getAddZppH());
                                        break;
                                    case "Extra":
                                        oos.writeObject(buhgalter.getExtra());
                                        oos.writeObject(buhgalter.getIsExtra());
                                        break;
                                    case "Final":
                                        oos.writeObject(buhgalter.getFinalZp());
                                        break;
                                    case "Hours":
                                        oos.writeObject(buhgalter.getWorkingHours());
                                        oos.writeObject(buhgalter.getIllHours());
                                        break;
                                    case "Add_User":
                                        System.out.println("Command getted: Add_User");
                                        String aUSurname = (String) ois.readObject();
                                        String aUName = (String) ois.readObject();
                                        String aUOtchestvo = (String) ois.readObject();
                                        String aUPassword = (String) ois.readObject();
                                        String aUPosition = (String) ois.readObject();
                                        oos.writeObject(dbController.addUser(aUSurname, aUName, aUOtchestvo, aUPosition, aUPassword));
                                        break;
                                    case "Delete":
                                        System.out.println("Command getted: Delete");
                                        int idUserD = Integer.parseInt((String) ois.readObject());
                                        dbController.deleteUser(idUserD);
                                        break;
                                    case "FillTable":
                                        System.out.println("Command getted: FillTable");
                                        dbController.fiilTable(ois, oos);
                                        break;
                                    case "SetBaseZP":
                                        System.out.println("Command getted: SetBaseZP");
                                        double amount = Double.parseDouble((String) ois.readObject());
                                        dbController.setBaseZp(amount, ((String) ois.readObject()));
                                        break;
                                    case "SetUserAddZPpH":
                                        System.out.println("Command getted: SetUserAddZPpH");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double addZppHamount = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserAddZppH(userIdToSet, addZppHamount);
                                        break;
                                    case "SetUserExtraMoney":
                                        System.out.println("Command getted: SetUserExtraMoney");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double extraMoney = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserExtraMoney(userIdToSet, extraMoney);
                                        break;
                                    case "SetUserIllHours":
                                        System.out.println("Command getted: SetUserIllHours");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double illHours = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserIllHours(userIdToSet, illHours);
                                        break;
                                    case "SetUserIsExtraMoney":
                                        System.out.println("Command getted: SetUserIsExtraMoney");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        boolean is = (boolean) ois.readObject();
                                        dbController.setUserIsExtraMoney(userIdToSet, is);
                                        break;
                                    case "SetUserWorkHours":
                                        System.out.println("Command getted: SetUserWorkHours");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double workHours = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserWorkHours(userIdToSet, workHours);
                                        break;
                                    case "FinalZP":
                                        System.out.println("Command getted: FinalZP");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        int positionIdToSet = dbController.getUserPositionIdById(userIdToSet);
                                        Employee employee = new Employee(name, surname, otchestvo, userIdToSet, positionIdToSet);
                                        oos.writeObject(employee.getFinalZp());
                                        break;
                                }
                                clientMessage = (String) ois.readObject();
                            }
                            break;
                        case 3:
                            Director director = new Director(surname, name, otchestvo, userId, userPositionId);
                            oos.writeObject("3");
                            oos.writeObject(surname);
                            oos.writeObject(name);
                            oos.writeObject(otchestvo);
                            oos.writeObject(dbController.getPositionByPositionId(userPositionId));
                            oos.writeObject(userId);
                            clientMessage = (String) ois.readObject();
                            while (!done) {
                                switch (clientMessage) {
                                    case "ZppH":
                                        oos.writeObject(director.getPositionZppH());
                                        oos.writeObject(director.getAddZppH());
                                        break;
                                    case "Extra":
                                        oos.writeObject(director.getExtra());
                                        oos.writeObject(director.getIsExtra());
                                        break;
                                    case "Final":
                                        oos.writeObject(director.getFinalZp());
                                        break;
                                    case "Hours":
                                        oos.writeObject(director.getWorkingHours());
                                        oos.writeObject(director.getIllHours());
                                        break;
                                    case "Add_User":
                                        System.out.println("Command getted: Add_User");
                                        String aUSurname = (String) ois.readObject();
                                        String aUName = (String) ois.readObject();
                                        String aUOtchestvo = (String) ois.readObject();
                                        String aUPassword = (String) ois.readObject();
                                        String aUPosition = (String) ois.readObject();
                                        oos.writeObject(dbController.addUser(aUSurname, aUName, aUOtchestvo, aUPosition, aUPassword));
                                        break;
                                    case "Delete":
                                        System.out.println("Command getted: Delete");
                                        int idUserD = Integer.parseInt((String) ois.readObject());
                                        dbController.deleteUser(idUserD);
                                        break;
                                    case "FillTable":
                                        System.out.println("Command getted: FillTable");
                                        dbController.fiilTable(ois, oos);
                                        break;
                                    case "SetBaseZP":
                                        System.out.println("Command getted: SetBaseZP");
                                        double amount = Double.parseDouble((String) ois.readObject());
                                        dbController.setBaseZp(amount, ((String) ois.readObject()));
                                        break;
                                    case "SetUserAddZPpH":
                                        System.out.println("Command getted: SetUserAddZPpH");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double addZppHamount = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserAddZppH(userIdToSet, addZppHamount);
                                        break;
                                    case "SetUserExtraMoney":
                                        System.out.println("Command getted: SetUserExtraMoney");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double extraMoney = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserExtraMoney(userIdToSet, extraMoney);
                                        break;
                                    case "SetUserIllHours":
                                        System.out.println("Command getted: SetUserIllHours");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double illHours = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserIllHours(userIdToSet, illHours);
                                        break;
                                    case "SetUserIsExtraMoney":
                                        System.out.println("Command getted: SetUserIsExtraMoney");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        boolean is = (boolean) ois.readObject();
                                        dbController.setUserIsExtraMoney(userIdToSet, is);
                                        break;
                                    case "SetUserWorkHours":
                                        System.out.println("Command getted: SetUserWorkHours");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        double workHours = Double.parseDouble((String) ois.readObject());
                                        dbController.setUserWorkHours(userIdToSet, workHours);
                                        break;
                                    case "FinalZP":
                                        System.out.println("Command getted: FinalZP");
                                        userIdToSet = Integer.parseInt((String) ois.readObject());
                                        int positionIdToSet = dbController.getUserPositionIdById(userIdToSet);
                                        Employee employee = new Employee(name, surname, otchestvo, userIdToSet, positionIdToSet);
                                        oos.writeObject(employee.getFinalZp());
                                        break;
                                }
                                clientMessage = (String) ois.readObject();
                            }
                            break;
                        case 4:
                            Employee emp = new Employee(surname, name, otchestvo, userId, userPositionId);
                            oos.writeObject("4");
                            oos.writeObject(surname);
                            oos.writeObject(name);
                            oos.writeObject(otchestvo);
                            oos.writeObject(dbController.getPositionByPositionId(userPositionId));
                            oos.writeObject(userId);
                            clientMessage = (String) ois.readObject();
                            while (!done) {
                                switch (clientMessage) {
                                    case "ZppH":
                                        oos.writeObject(emp.getPositionZppH());
                                        oos.writeObject(emp.getAddZppH());
                                        break;
                                    case "Extra":
                                        oos.writeObject(emp.getExtra());
                                        oos.writeObject(emp.getIsExtra());
                                        break;
                                    case "Final":
                                        oos.writeObject(emp.getFinalZp());
                                        break;
                                    case "Hours":
                                        oos.writeObject(emp.getWorkingHours());
                                        oos.writeObject(emp.getIllHours());
                                        break;
                                }
                                clientMessage = (String) ois.readObject();
                            }
                            break;
                    }
                    clientMessage = (String) ois.readObject();
            }
        }
        catch (Exception ignored) {
        }
        finally {
            try {
                Objects.requireNonNull(ois).close();
                oos.close();
                socket.close();
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
