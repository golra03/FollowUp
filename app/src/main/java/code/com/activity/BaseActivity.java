package code.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import code.com.webservice.AsyncTaskCompleteListener;
import code.com.webservice.Bean;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener,AsyncTaskCompleteListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v){

        System.out.println("BaseActivity::onClick()");
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
        System.out.println("MainActivity::onTaskComplete()");
        System.out.println("Printing response "+((Bean)result).response);
        //Log.e("json response in listener callback", "onCreate =" + ((Bean)result).response);

        //populatefirst();
        //startActivity(new Intent(this, Login.class));
        return;
    }
}
