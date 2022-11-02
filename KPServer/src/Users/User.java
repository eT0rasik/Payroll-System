package Users;

public abstract class User {
    String surname;
    String name;
    String otchestvo;
    int userId;
    int userPositionId;

    User(String gSurname, String gName, String gOtchestvo, int gUserId, int gUserPositionId)
    {
        surname = gSurname;
        name = gSurname;
        otchestvo = gOtchestvo;
        userId = gUserId;
        userPositionId = gUserPositionId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserPositionId() {
        return userPositionId;
    }

    public void setUserPositionId(int userPositionId) {
        this.userPositionId = userPositionId;
    }
}
