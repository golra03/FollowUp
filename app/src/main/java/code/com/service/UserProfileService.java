package code.com.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import code.com.database.DatabaseProcessor;
import code.com.model.ContactItem;
import code.com.model.UserProfile;

/**
 * Created by sayirashai on 06-12-2015.
 */
public class UserProfileService
{
    public static final String TABLE_NAME = "user_profile";
    private SQLiteDatabase db;
    private static final UserProfileService userProfileService = new UserProfileService();

    private UserProfileService()
    {

    }

    public static UserProfileService getUserProfileService()
    {
        return userProfileService;
    }

    public static abstract class UserProfileEntry implements BaseColumns
    {
        public static final String USER_ID = "usp_id";
        public static final String USER_NAME = "usp_name";
        public static final String USER_MOB_NUM = "usp_mob_num";
    }

    public static final String USER_PROFILE_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " ("
            + UserProfileEntry.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UserProfileEntry.USER_NAME + " TEXT,"
            + UserProfileEntry.USER_MOB_NUM + " TEXT NOT NULL);";

    public static final String USER_PROFILE_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public long insertUserProfile(UserProfile userProfile)
    {
        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserProfileEntry.USER_NAME, userProfile.getUspName());
        values.put(UserProfileEntry.USER_MOB_NUM, userProfile.getUspMobNum());

        long insertedRecordId = db.insert(TABLE_NAME,null,values);

        Cursor resultCursor = db.rawQuery("select * from user_profile", null);
        if(resultCursor.getCount() != 1)
            System.out.print("Nahin Hua Register!!");
        else
            System.out.print("Hua Register!!");
        return insertedRecordId;
    }

    public int updateUserProfile(UserProfile userProfile)
    {
        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserProfileEntry.USER_ID, userProfile.getUspId());
        values.put(UserProfileEntry.USER_NAME, userProfile.getUspName());
        values.put(UserProfileEntry.USER_MOB_NUM, userProfile.getUspMobNum());

        String selection = UserProfileEntry.USER_ID+" = ?";
        String[] selectionArgs = {String.valueOf(userProfile.getUspId())};

        int updatedRowsCount = db.update(TABLE_NAME, values, selection, selectionArgs);
        return updatedRowsCount;
    }

    public UserProfile getUserProfile()
    {
        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getReadableDatabase();

        // Define a projection that specifies which columns from the database you will actually use after this query.
        String[] projection = {
                UserProfileEntry.USER_ID,
                UserProfileEntry.USER_NAME,
                UserProfileEntry.USER_MOB_NUM
        };

        //Cursor resultCursor = db.query(TABLE_NAME, projection, null, null, null, null, null);
        Cursor resultCursor = db.rawQuery("select * from user_profile", null);

        System.out.print("resultCursor.getCount():" + resultCursor.getCount());

        UserProfile userProfile = new UserProfile();
        if(resultCursor != null && resultCursor.getCount() >= 1)
        {
            resultCursor.moveToFirst();

            //while(resultCursor.isAfterLast() == false)
            {

                userProfile.setUspId(resultCursor.getInt(resultCursor.getColumnIndex(UserProfileEntry.USER_ID)));
                userProfile.setUspName(resultCursor.getString(resultCursor.getColumnIndex(UserProfileEntry.USER_NAME)));
                userProfile.setUspMobNum(resultCursor.getString(resultCursor.getColumnIndex(UserProfileEntry.USER_MOB_NUM)));
            }
            resultCursor.close();
        }
        return userProfile;
    }

    public void deleteUserProfile(int uspId)
    {
        SQLiteDatabase db = DatabaseProcessor.getDbInstance().getWritableDatabase();
        String selection = UserProfileEntry.USER_ID + " = ?";
        String[] selectionArgs = { String.valueOf(uspId) };
        db.delete(TABLE_NAME, selection, selectionArgs);
    }

    public boolean isRegistrationDoneOnThisDevice()
    {
        UserProfile userProfile = getUserProfile();
        if(userProfile != null && userProfile.getUspId() != -1)
        {
            return true;
        }
        return false;
    }
}
