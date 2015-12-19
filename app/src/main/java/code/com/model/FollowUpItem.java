package code.com.model;

/**
 * Created by neelimagupta on 18/12/15.
 */
public class FollowUpItem
{
    private String followupID;

    public String getFollowupID()
    {
        return followupID;
    }

    public void setFollowupID(String followupID)
    {
        this.followupID = followupID;
    }

    public String getContactName()
    {
        return contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    private String context;
    private ContactItem contact;

    public String getContactId()
    {
        return contactId;
    }

    public void setContactId(String contactId)
    {
        this.contactId = contactId;
    }

    private String contactId;
    private String contactName;
    private int year, month, day;
    private boolean acceptance;

    public String getContext()
    {
        return context;
    }

    public void setContext(String context)
    {
        this.context = context;
    }

    public ContactItem getContact()
    {
        return contact;
    }

    public void setContact(ContactItem contact)
    {
        this.contact = contact;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public boolean isAcceptance()
    {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance)
    {
        this.acceptance = acceptance;
    }
}
