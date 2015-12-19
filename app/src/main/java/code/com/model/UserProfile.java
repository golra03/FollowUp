package code.com.model;

/**
 * Created by sayirashai on 06-12-2015.
 */
public class UserProfile
{
    private int    uspId;

    public UserProfile(int uspId, String uspName, String uspMobNum)
    {
        this.uspId = uspId;
        this.uspName = uspName;
        this.uspMobNum = uspMobNum;
    }

    public UserProfile()
    {
    }

    private String uspName;
    private String uspMobNum;

    public int getUspId()
    {
        return uspId;
    }

    public void setUspId(int uspId)
    {
        this.uspId = uspId;
    }

    public String getUspName()
    {
        return uspName;
    }

    public void setUspName(String uspName)
    {
        this.uspName = uspName;
    }

    public String getUspMobNum()
    {
        return uspMobNum;
    }

    public void setUspMobNum(String uspMobNum)
    {
        this.uspMobNum = uspMobNum;
    }
}
