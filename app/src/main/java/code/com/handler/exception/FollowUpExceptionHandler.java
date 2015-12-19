package code.com.handler.exception;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;

import java.io.PrintWriter;
import java.io.StringWriter;

import code.com.handler.message.FollowUpMessageBox;

/**
 * Created by sayirashai on 06-12-2015.
 */
public class FollowUpExceptionHandler implements Thread.UncaughtExceptionHandler
{

    private final Activity context;
    private final String LINE_SEPARATOR = "\n";

    public FollowUpExceptionHandler(Activity context)
    {
        this.context = context;
    }
    @Override
    public void uncaughtException(Thread thread, Throwable exception)
    {
        reportTheError(exception);
        System.out.println("In uncaught exception "+exception);
        FollowUpMessageBox messageBox =
                new FollowUpMessageBox("FollowUpItem Error","Server experienced an error! Please restart the app. ", context);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

    private void reportTheError(Throwable exception)
    {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        String errorCause = stackTrace.toString();
        //Should go to cloud DB
        //Error_Report Table name
        //erp_id, erp_cause, erp_brand, erp_device, erp_model, erp_build_id, erp_product, erp_sdk_version, erp_build_version,
        //auto_incr_id, errorCause, Build.BRAND, Build.DEVICE, Build.MODEL, Build.ID, Build.PRODUCT, Build.VERSION.SDK,Build.VERSION.RELEASE
    }
}
