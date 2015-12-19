package code.com.webservice;

/**
 * Created by neelimagupta on 29/11/15.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import code.com.database.DatabaseProcessor;
import code.com.model.ContactItem;
import code.com.service.ContactService;
import code.com.service.UserProfileService;

public class DataFetching extends AsyncTask
{
    private static final String TAG_CONTACTS = "contacts";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PHONE_MOBILE = "mobile";

//    public static final String TABLE_NAME_CONTACT = "contact";
//    private static final String CONTACT_ID = "id";
//    private static final String CONTACT_NAME = "name";
//    private static final String CONTACT_MOB_NUM = "mobile";
//    private static final String CONTACT_EMAIL = "email";
//    private static final String CONTACT_PHONE = "phone";

    private Context ctx;
    private ProgressDialog dialog;

    private StringBuilder builder = null;
    private InputStream is = null;

    private JSONObject objContects = null;
    JSONArray contacts = null;
    AsyncTaskCompleteListener listener;
    Bean bean;

    private String returnResponse;

    private DatabaseProcessor dbConn;  //DB Service

    public DataFetching(Context context)
    {
        dbConn = DatabaseProcessor.getDbInstance();
        this.ctx = context;
        listener = (AsyncTaskCompleteListener)ctx;
    }

    public String makeXMLRequest()
    {
        try
        {
            URL url = new URL("http://api.androidhive.info/contacts/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.getDoOutput();
            String readStream = readStream(con.getInputStream());
            return readStream;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }

    private static String readStream(InputStream in)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected Object doInBackground(Object[] params)
    {
         try
         {
             String resData = makeXMLRequest();
             objContects = new JSONObject(resData.trim());
             bean = new Bean();
             bean.response = objContects.toString();
             parseAndInsertObjects(resData);
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }

         Log.e("json response", "doInBackGround =" + bean.response);
         return bean;
    }


    private void parseAndInsertObjects(String contJsonStr)
    {
        if (contJsonStr != null)
        {
            try
            {
                JSONObject jsonObj = new JSONObject(contJsonStr);
                contacts = jsonObj.getJSONArray(TAG_CONTACTS);

                System.out.println("no of contact object:"+ contacts.length());

                for (int i = 0; i < contacts.length(); i++)
                {
                    JSONObject c = contacts.getJSONObject(i);

                    String id = c.getString(TAG_ID);
                    String name = c.getString(TAG_NAME);
                    String email = c.getString(TAG_EMAIL);
                    System.out.println("ID of contact object:"+ id);
                    // Phone node is JSON Object
                    JSONObject phone = c.getJSONObject(TAG_PHONE);
                    String mobile = phone.getString(TAG_PHONE_MOBILE);

                    ContactItem contact = new ContactItem(id,name,email,mobile);
                    ContactService contactService = ContactService.getContactService();

                    contactService.insertContact(contact);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        dialog = new ProgressDialog(ctx);
        dialog.setTitle("Data Fetching");
        dialog.setMessage("Fetching...");
        dialog.show();
    }


    @Override
    public void onPostExecute(Object bean)
    {
        super.onPostExecute(bean);
        Log.e("data in onPost", "result = " + ((Bean) bean).response);
        returnResponse = ((Bean)bean).response;
        System.out.println("Here is the response: "+((Bean)bean).response);
        listener.onTaskComplete(bean);
        dialog.dismiss();
    }

    public String getReturnString()
    {
        return returnResponse;
    }
}