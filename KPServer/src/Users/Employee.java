package Users;

import Controllers.DBController;

public class Employee extends User {

    public Employee(String gSurname, String gName, String gOtchestvo, int gUserId, int gUserPositionId)
    {
        super(gSurname, gName, gOtchestvo, gUserId, gUserPositionId);
    }

    public double getPositionZppH()
    {
        DBController dbController = new DBController();
        return dbController.getPositionZppHByPositionId(userPositionId);
    }

    public double getAddZppH()
    {
        DBController dbController = new DBController();
        return dbController.getAddZppHByUserId(userId);
    }

    public double getExtra()
    {
        DBController dbController = new DBController();
        return dbController.getExtraByUserId(userId);
    }

    public boolean getIsExtra()
    {
        DBController dbController = new DBController();
        return dbController.getIsExtraByUserId(userId);
    }

    public double getWorkingHours()
    {
        DBController dbController = new DBController();
        return dbController.getWorkHoursByUserId(userId);
    }

    public double getIllHours()
    {
        DBController dbController = new DBController();
        return dbController.getIllHoursByUserId(userId);
    }

    public double getFinalZp()
    {
        double result;
        result = (getPositionZppH()+getAddZppH()) * (getWorkingHours() - getIllHours()) + getPositionZppH() * getIllHours();
        if (getIsExtra())
        {
            result+=getExtra();
        }
        return result;
    }

    public double getTotalZp()
    {
        double result;
        result = (getPositionZppH()+getAddZppH()) * (getWorkingHours() - getIllHours()) + getPositionZppH() * getIllHours();
        result+=getExtra();
        return result;
    }

    public double getTotalBaseZP()
    {
        double result;
        result = getPositionZppH() * getWorkingHours();
        return result;
    }

    public double getTotalAddZppH()
    {
        double result;
        result = getAddZppH() * (getWorkingHours() - getIllHours());
        return result;
    }

    public double getTotalNotExtraMoney()
    {
        if (!getIsExtra())
        {
            return getExtra();
        }
        else
        {
            return 0;
        }
    }

    public double getTotalNotAddZppH()
    {
        double result;
        result = getIllHours() * getAddZppH();
        return result;
    }
}
