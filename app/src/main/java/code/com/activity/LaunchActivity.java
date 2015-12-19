package code.com.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import code.com.activity.followup.FollowUpListActivity;
import code.com.activity.registration.RegistrationActivity;
import code.com.database.DatabaseProcessor;
import code.com.followup.R;
import code.com.handler.exception.FollowUpExceptionHandler;
import code.com.model.UserProfile;
import code.com.service.UserProfileService;
import code.com.webservice.Bean;

public class LaunchActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new FollowUpExceptionHandler(this));
        //initialize database
        DatabaseProcessor.initializeDatabase(this);
        UserProfileService userService = UserProfileService.getUserProfileService();
        Intent intent = null;

        if(userService.isRegistrationDoneOnThisDevice())
        {
            UserProfile userProfile;
            userProfile = userService.getUserProfile();
//            if(userProfile != null && userProfile.getUspId() != =1)
//            {
//                return true;
//            }
            intent = new Intent(getApplicationContext(), FollowUpListActivity.class);
        }
        else
        {
            intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public void onClick(View v){

        System.out.println("LaunchActivity::onClick()");
        switch(v.getId()) {

//            case R.id.Loginbutton: {
////                Context context = getApplicationContext();
////                CharSequence text = "Hello Login!";
////                int duration = 1;//Toast.LENGTH_SHORT;
////
////                Toast toast = Toast.makeText(context, text, duration);
////                toast.show();
//
//                startActivity(new Intent(this, IconTextTabsActivity.class));
//                return;
//            }
//            case R.id.NewRegister: {
//
//                startActivity(new Intent(this, Register.class));
//            }
        }
    }

    @Override
    public void onTaskComplete(Object result)
    {
        System.out.println("LaunchActivity::onTaskComplete()");
        System.out.println("Printing response "+((Bean)result).response);
        //Log.e("json response in listener callback", "onCreate =" + ((Bean)result).response);

        //populatefirst();
        //startActivity(new Intent(this, Login.class));
        return;
    }
}
