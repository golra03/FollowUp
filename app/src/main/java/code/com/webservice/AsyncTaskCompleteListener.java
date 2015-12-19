package code.com.webservice;

/**
 * Created by neelimagupta on 29/11/15.
 */
public interface AsyncTaskCompleteListener<Bean>
{
    /**
     * Invoked when the AsyncTask has completed its execution.
     * @param result The resulting object from the AsyncTask.
     */
    public void onTaskComplete(Bean result);
}

