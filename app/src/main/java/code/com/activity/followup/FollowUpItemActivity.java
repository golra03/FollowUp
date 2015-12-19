package code.com.activity.followup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

import code.com.activity.BaseActivity;
import code.com.followup.R;
import code.com.model.FollowUpItem;
import code.com.model.FollowUpItem;
import code.com.service.ContactService;

public class FollowUpItemActivity extends BaseActivity
{
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private TextView timeView;
    private int year, month, day;
    private int hourOfDay,minute;
    private boolean is24HourView;
    private TextView contactname, followupcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_up_item);

        contactname = (TextView) findViewById(R.id.etContact);
        followupcontext = (TextView) findViewById(R.id.etContext);

        dateView = (TextView) findViewById(R.id.etxt_followupdate);
        timeView = (TextView) findViewById(R.id.etxt_followuptime);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        hourOfDay = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        is24HourView = true;//Calendar.get(Calendar.AM_PM);
        showTime(hourOfDay,minute,is24HourView);

        dateView.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    InputMethodManager imm = (InputMethodManager) getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(dateView.getWindowToken(), 0);
                    setDate(v);
                }
            }
        });

        timeView.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    InputMethodManager imm = (InputMethodManager) getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(timeView.getWindowToken(), 0);
                    setTime(v);
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view)
    {
        showDialog(111);
    }

    @SuppressWarnings("deprecation")
    public void setTime(View view)
    {
        showDialog(222);
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if (id == 111)
        {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        if (id == 222)
        {
            return new TimePickerDialog(this, myTimeListener, hourOfDay, minute, is24HourView);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3)
        {
            // TODO Auto-generated method stub
            year = arg1;
            month = arg2;
            day = arg3;
            showDate(year, month, day);
        }
    };

    private TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker view, int arg1, int arg2)
        {
            hourOfDay = arg1;
            minute = arg2;
            showTime(hourOfDay, minute, true);
        }
    };

    private void showDate(int year, int month, int day)
    {
        dateView.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    private void showTime(int hourOfDay,int minute, boolean is24HourView)
    {
        int hour = (hourOfDay == 0) ? 12 : hourOfDay;
        timeView.setText(new StringBuilder().append(hour).append("/").append(minute).append(" ").append("PM"));
    }

    public void onSendButtonClick(View view)
    {
        FollowUpItem followUpItem = new FollowUpItem();

        FollowUpItem contactItem;
        ContactService contactService = ContactService.getContactService();
//        contactItem = contactService.getContactByName((String) contactname.getText());
//
//        followUpItem.setContact(contactItem);
//        followUpItem.setContactId(contactItem.getId());
//        followUpItem.setContactName((String) contactname.getText());
//        followUpItem.setContext((String) followupcontext.getText());


    }
}
