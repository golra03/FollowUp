package code.com.model;

/**
 * Created by neelimagupta on 06/12/15.
 */
public class ContactItem extends Object
{
    private String id;
    private String name;
    private String mobilenumber;

    public ContactItem()
    {
    }

    public ContactItem(String id, String name, String username, String mobilenumber)
    {
        this.id = id;
        this.name = name;
        this.mobilenumber = mobilenumber;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setMobilenumber(String mobilenumber)
    {
        this.mobilenumber = mobilenumber;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getMobilenumber() { return mobilenumber; }
}
