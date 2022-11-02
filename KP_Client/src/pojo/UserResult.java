package pojo;

public class UserResult {
    int id;
    String surname;
    String name;
    String otchestvo;
    String position;
    double finalZP;

    public UserResult(int id, String surname, String name, String otchestvo, String position, double finalZP) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.otchestvo = otchestvo;
        this.position = position;
        this.finalZP = finalZP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getFinalZP() {
        return finalZP;
    }

    public void setFinalZP(double finalZP) {
        this.finalZP = finalZP;
    }
}
