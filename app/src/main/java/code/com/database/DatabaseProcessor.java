package code.com.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

import code.com.service.ContactService;
import code.com.service.UserProfileService;

/**
 * Created by sayirashai on 05-12-2015.
 */
public class DatabaseProcessor extends SQLiteOpenHelper
{
    private static DatabaseProcessor dbInstance;

    private static final String DATABASE_NAME = "FollowUp.db";
    private static final int DATABASE_VERSION = 1;

    private DatabaseProcessor(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized void initializeDatabase(Context context)
    {
        if (dbInstance == null)
        {
            dbInstance = new DatabaseProcessor(context.getApplicationContext());
            SQLiteDatabase db = dbInstance.getReadableDatabase();
            dbInstance.onUpgrade(db,-1,-1);
        }
    }

    public static DatabaseProcessor getDbInstance()
    {
        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase dbHelper)
    {
        dbHelper.execSQL(UserProfileService.USER_PROFILE_CREATE_TABLE);
        dbHelper.execSQL(ContactService.CONTACT_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbHelper, int oldVersion, int newVersion)
    {
        //Here we can add all delete queries
        dbHelper.execSQL(UserProfileService.USER_PROFILE_DELETE_ENTRIES);
        dbHelper.execSQL(ContactService.CONTACT_DELETE_ENTRIES);
        onCreate(dbHelper);
    }

    public static void closeDbConnection()
    {
        dbInstance.close();
    }
}