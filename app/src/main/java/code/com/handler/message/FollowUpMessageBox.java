package code.com.handler.message;

import android.app.Activity;
import android.app.AlertDialog;

/**
 * Created by sayirashai on 06-12-2015.
 */
public class FollowUpMessageBox
{
    public FollowUpMessageBox()
    {}

    public FollowUpMessageBox(String title, String message, Activity context)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
