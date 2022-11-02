package pojo;

public class User {
    int id;
    String surname;
    String name;
    String otchestvo;
    String position;
    double addZppH;
    double extraMoney;
    String isExtraMoney;
    double workHours;
    double illHours;

    public User(int id, String surname, String name, String otchestvo, String position, double addZppH, double extraMoney, String isExtraMoney, double workHours, double illHours) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.otchestvo = otchestvo;
        this.position = position;
        this.addZppH = addZppH;
        this.extraMoney = extraMoney;
        this.isExtraMoney = isExtraMoney;
        this.workHours = workHours;
        this.illHours = illHours;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public String getPosition() {
        return position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getAddZppH() {
        return addZppH;
    }

    public void setAddZppH(double addZppH) {
        this.addZppH = addZppH;
    }

    public double getExtraMoney() {
        return extraMoney;
    }

    public void setExtraMoney(double extraMoney) {
        this.extraMoney = extraMoney;
    }

    public String getIsExtraMoney() {
        return isExtraMoney;
    }

    public void setIsExtraMoney(String isExtraMoney) {
        this.isExtraMoney = isExtraMoney;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public double getIllHours() {
        return illHours;
    }

    public void setIllHours(double illHours) {
        this.illHours = illHours;
    }
}
