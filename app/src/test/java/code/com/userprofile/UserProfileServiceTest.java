package code.com.userprofile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import code.com.database.DatabaseProcessor;
import code.com.model.UserProfile;
import code.com.service.UserProfileService;

/**
 * Created by sayirashai on 06-12-2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceTest
{
    @Mock
    Context mContext;



    @Test
    public void testInsertUserProfile()
    {
        UserProfileService mUserProfileService = new UserProfileService();
        DatabaseProcessor db = DatabaseProcessor.getInstance(mContext);

        UserProfile userProfile = new UserProfile();
        userProfile.setUspName("Aditya");
        userProfile.setUspMobNum("7207607121");

        long id = mUserProfileService.insertUserProfile(mContext,userProfile);
        System.out.println("The inserted record id is: "+id);
    }
}
