package code.com.activity.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

import code.com.activity.BaseActivity;
import code.com.activity.followup.FollowUpListActivity;
import code.com.followup.R;
import code.com.handler.exception.FollowUpExceptionHandler;
import code.com.model.ContactItem;
import code.com.model.UserProfile;
import code.com.service.ContactService;
import code.com.service.UserProfileService;
import code.com.webservice.DataFetching;

public class RegistrationActivity extends BaseActivity
{
    private static final String CURR_ACTIVITY = "Registration_Activity";
    private EditText username;
    private EditText mobNum;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new FollowUpExceptionHandler(this));
        setContentView(R.layout.activity_registration);

        username  = (EditText) findViewById(R.id.input_name);
        username.requestFocus();
        mobNum    = (EditText) findViewById(R.id.input_number);
        registerBtn = (Button) findViewById(R.id.btn_register);

        populatefirst();
    }

    public void onRegisterButtonClick(View view)
    {
        if(validateUserInput())
        {
            onRegistrationSuccess();
        }
        else
        {
            onRegistrationFailed();
        }
    }

    public void onRegistrationFailed()
    {
        Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();
        registerBtn.setEnabled(true);
    }

    public void onRegistrationSuccess()
    {
        registerUser();
    }

    private void registerUser()
    {
        UserProfile usp = new UserProfile();
        usp.setUspName(username.getText().toString());
        usp.setUspMobNum(mobNum.getText().toString());

        UserProfileService userService = UserProfileService.getUserProfileService();
        userService.insertUserProfile(usp);

        Intent intent = new Intent(getApplicationContext(), FollowUpListActivity.class);
        startActivity(intent);
    }

    public boolean populatefirst()
    {
        System.out.println("RegistrationActivity::populatefirst()");

        UserProfileService userProfileService = UserProfileService.getUserProfileService();
        ContactService contactService = ContactService.getContactService();
        if(!contactService.isTableExists("contact",true)) {
            System.out.println("contact table does not exist already!!");
            return false;
        }
        ArrayList<ContactItem> StrniArray = contactService.getAllContacts();

        //String username = "",password = "";
        if(!StrniArray.isEmpty()) {
            ContactItem contact;
            contact = StrniArray.get(0);
            System.out.println("username is " + contact.getName());

//            username.setText(contact.getName());
//            System.out.println(contact.getName());
//            mobNum.setText(contact.getMobilenumber());
//            System.out.println(contact.getMobilenumber());
        }
        else {
            new DataFetching(RegistrationActivity.this).execute();
        }
        return true;
    }

    private boolean validateUserInput()
    {
        boolean validate = true;
        validate = validateUserName();
        if(validate)
        {
            validate = validateUserMobNumber();
        }
        return validate;
    }

    private boolean validateUserName()
    {
        if (username.getText().toString().isEmpty() || username.getText().toString().length() < 3)
        {
            username.setError("at least 3 characters");
            return false;
        }
        else if (username.getText().toString().length() > 20)
        {
            username.setError("at most 20 characters");
            return false;
        }
        else
        {
            username.setError(null);
            return true;
        }
    }

    private boolean validateUserMobNumber()
    {
        String mobNumber = mobNum.getText().toString();

        if(!Pattern.matches("[a-zA-Z]+", mobNumber))
        {
            if(mobNumber.length() < 10 || mobNumber.length() > 13)
            {
                mobNum.setError("Not Valid Number");
                return false;
            }
            return true;
        }
        else
        {
            return true;
        }
    }


}