package code.com.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by sayirashai on 06-12-2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class DatabaseProessorTest
{
    @Mock
    Context mMockContext;

    @Test
    public void testCreateDbInstance()
    {
        System.out.println("testCreateDbInstance");
        DatabaseProcessor db = DatabaseProcessor.getInstance(mMockContext);
        Assert.assertNotNull(db);
    }

    @Test
    public void testSingletonDbPattern()
    {
        DatabaseProcessor db = DatabaseProcessor.getInstance(mMockContext);
        System.out.println("testSingletonDbPattern");
        DatabaseProcessor newDb = DatabaseProcessor.getInstance(mMockContext);
        Assert.assertEquals(true, newDb == db);
    }
}
