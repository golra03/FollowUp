package code.com.activity.followup.fragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import code.com.database.DatabaseProcessor;
import code.com.followup.R;
import code.com.service.ContactService;
import code.com.service.UserProfileService;
import code.com.model.ContactItem;
import code.com.widget.ContactListAdapter;
import code.com.widget.ContactListView;


public class ContactFragment extends Fragment
{
    public static final String TABLE_NAME = "user_profile";
    public ContactFragment()
    {
    }

    ArrayList<ContactItem> filterList;
    ArrayList<ContactItem> contactList;

    private ContactListView listview;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View aView = inflater.inflate(R.layout.fragment_contact, container, false);

        ContactService contactService = ContactService.getContactService();

        //contactList = contactService.getAllContactsInterface();
        contactList = contactService.showContacts();
        ContactListAdapter adapter = new ContactListAdapter(getActivity(), R.layout.data_form_contacts_list_item, contactList);

        listview = (ContactListView)aView.findViewById(R.id.listView_contact);
        //listview = (ContactListView)getActivity().findViewById(R.id.listView_contact);
        //listview.setFastScrollEnabled(true);
        listview.setAdapter(adapter);

        // use this to process individual clicks
        // cannot use OnClickListener as the touch event is overrided by IndexScroller
        // use last touch X and Y if want to handle click for an individual item within the row
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView parent, View v, int position,
                                    long id)
            {
                //ArrayList<ContactItem> searchList = inSearchMode ? filterList : contactList;

                Toast.makeText(getActivity(), "Rahul Golash OnItemClick()", Toast.LENGTH_LONG).show();

                //float lastTouchX = listview.getScroller().getLastTouchDownEventX();
                //if (lastTouchX < 45 && lastTouchX > -1)
                //{
                //  Toast.makeText(getActivity(), "User image is clicked ( " + searchList.get(position).getItemForIndex() + ")", Toast.LENGTH_SHORT).show();
                //} else
                //  Toast.makeText(getActivity(), "Nickname: " + searchList.get(position).getItemForIndex(), Toast.LENGTH_SHORT).show();

            }
        });

        return aView;
        //container.get
    }

}
