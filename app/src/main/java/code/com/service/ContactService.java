package code.com.service;

import android.Manifest;
import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import code.com.database.DatabaseProcessor;
import code.com.model.ContactItem;

/**
 * Created by sayirashai on 06-12-2015.
 */
public class ContactService
{
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    public static final String TABLE_NAME_CONTACT = "contact";

    private static final ContactService ContactService = new ContactService();

    private static Context context;
    private ContactService()
    {
        context = getApplicationContext();
    }

    public static ContactService getContactService()
    {
        return ContactService;
    }

    public static abstract class ContactEntry implements BaseColumns
    {

        private static final String CONTACT_ID = "id";
        private static final String CONTACT_NAME = "name";
        private static final String CONTACT_MOB_NUM = "mobile";
        private static final String CONTACT_EMAIL = "email";
        private static final String CONTACT_PHONE = "phone";
    }

    public static final String CONTACT_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME_CONTACT + " ("
            + ContactEntry.CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ContactEntry.CONTACT_NAME + " TEXT,"
            + ContactEntry.CONTACT_MOB_NUM + " TEXT NOT NULL);";

    public static final String CONTACT_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME_CONTACT;

    public long insertContact(ContactItem contact)
    {
        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ContactEntry.CONTACT_NAME, contact.getName());
        values.put(ContactEntry.CONTACT_MOB_NUM, contact.getName());

        long insertedRecordId = db.insert(TABLE_NAME_CONTACT,null,values);
        return insertedRecordId;
    }

    public int updateContact(ContactItem contact)
    {
        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContactEntry.CONTACT_ID, contact.getId());
        values.put(ContactEntry.CONTACT_NAME, contact.getName());
        values.put(ContactEntry.CONTACT_MOB_NUM, contact.getMobilenumber());

        String selection = ContactEntry.CONTACT_ID+" = ?";
        String[] selectionArgs = {String.valueOf(contact.getId())};

        int updatedRowsCount = db.update(TABLE_NAME_CONTACT, values, selection, selectionArgs);
        return updatedRowsCount;
    }

    public Cursor getContact()
    {
        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getReadableDatabase();

        // Define a projection that specifies which columns from the database you will actually use after this query.
        String[] projection = {
                ContactEntry.CONTACT_ID,
                ContactEntry.CONTACT_NAME,
                ContactEntry.CONTACT_MOB_NUM
        };

        //Cursor resultCursor = db.query(TABLE_NAME, projection, null, null, null, null, null);
        Cursor resultCursor = db.rawQuery("Select * from contact", null);
        return resultCursor;
    }

    public void deleteContact(int uspId)
    {
        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getWritableDatabase();
        String selection = ContactEntry.CONTACT_ID + " = ?";
        String[] selectionArgs = { String.valueOf(uspId) };
        db.delete(TABLE_NAME_CONTACT, selection, selectionArgs);
    }

    public boolean isRegistrationDoneOnThisDevice()
    {
        Cursor userProfile = getContact();
        if(userProfile != null && userProfile.getCount() == 1)
        {
            userProfile.close();
            return true;
        }
        userProfile.close();
        return false;
    }

    public ArrayList<ContactItem> getAllContacts()
    {
        System.out.println("ContactService::getAllContacts()");
        ArrayList<ContactItem> array_list = new ArrayList<ContactItem>();

        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getReadableDatabase();
        Cursor res =  db.rawQuery("select * from contact", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            ContactItem contact = new ContactItem();
            contact.setId(res.getString(res.getColumnIndex(ContactEntry.CONTACT_ID)));
            contact.setName(res.getString(res.getColumnIndex(ContactEntry.CONTACT_NAME)));
            contact.setMobilenumber(res.getString(res.getColumnIndex(ContactEntry.CONTACT_MOB_NUM)));
            array_list.add(contact);
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    public ArrayList<ContactItem> getAllContactsInterface()
    {
        System.out.println("DBHelper::getAllContactsInterface()");
        ArrayList<ContactItem> array_list = new ArrayList<ContactItem>();

        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getReadableDatabase();
        Cursor res =  db.rawQuery("select * from contact", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            ContactItem contact = new ContactItem();
            contact.setId(res.getString(res.getColumnIndex(ContactEntry.CONTACT_ID)));
            contact.setName(res.getString(res.getColumnIndex(ContactEntry.CONTACT_NAME)));
            contact.setMobilenumber(res.getString(res.getColumnIndex(ContactEntry.CONTACT_MOB_NUM)));
            array_list.add(contact);
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    public boolean isTableExists(String tableName, boolean openDb) {

        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getReadableDatabase();
        if(openDb) {

            Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
            if (cursor != null)
            {
                if (cursor.getCount() > 0)
                {
                    cursor.close();
                    return true;
                }
                cursor.close();
            }
            return false;
        }
        return false;
    }

    public ArrayList<ContactItem> showContacts()
    {
        ArrayList<ContactItem> contacts = new ArrayList<ContactItem>();

        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                context.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            //requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
            System.out.println("Permission Issues!!");

        }
        else
        {
            // Android version is lesser than 6.0 or the permission is already granted.
            contacts = getContactNames();
            if(contacts.size() != 0)
            {
                System.out.println("Found ContactItem");
            }
            else
            {
                System.out.println("No Contacts");
            }
        }
        return contacts;
    }

    public ArrayList<ContactItem> getContactNames()
    {
        ArrayList<ContactItem> contacts = new ArrayList<ContactItem>();

        // Get the ContentResolver
        ContentResolver cr = context.getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        // Move the cursor to first. Also check whether the cursor is empty or not.
        if (cursor.moveToFirst())
        {
            // Iterate through the cursor
            do
            {
                ContactItem contact = new ContactItem();
                // Get the contacts name
                contact.setId(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.NAME_RAW_CONTACT_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                contact.setMobilenumber(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                contacts.add(contact);
            }
            while (cursor.moveToNext());
        }
        // Close the cursor
        cursor.close();
        return contacts;
    }

    public ContactItem getContactByName(String contactname)
    {
        // Get the ContentResolver
        ContentResolver cr = context.getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        ContactItem contact = new ContactItem();
        contact.setId("-1");
        // Move the cursor to first. Also check whether the cursor is empty or not.
        if (cursor.moveToFirst())
        {
            // Iterate through the cursor
            String contactnameTemp = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            if(contactnameTemp.compareToIgnoreCase(contactname) == 0 )
            do
            {
                // Get the contacts name
                contact.setId(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.NAME_RAW_CONTACT_ID)));
                contact.setName(contactnameTemp);
                contact.setMobilenumber(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                break;
            }
            while (cursor.moveToNext());
        }
        // Close the cursor
        cursor.close();
        return contact;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults)
    {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                // Permission is granted
                showContacts();
            } else
            {
                Toast.makeText(context, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Context getApplicationContext()
    {
        try {
            final Class<?> activityThreadClass =
                    Class.forName("android.app.ActivityThread");
            final Method method = activityThreadClass.getMethod("currentApplication");
            Application application;
            application = (Application) method.invoke(null, (Object[]) null);
            return application.getApplicationContext();
        } catch (final ClassNotFoundException e) {
            // handle exception
        } catch (final NoSuchMethodException e) {
            // handle exception
        } catch (final IllegalArgumentException e) {
            // handle exception
        } catch (final IllegalAccessException e) {
            // handle exception
        } catch (final InvocationTargetException e) {
            // handle exception
        }
        return null;
    }
}
