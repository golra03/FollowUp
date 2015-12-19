package code.com.widget;

import java.util.List;

import code.com.followup.R;
import code.com.model.ContactItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactListAdapter extends ArrayAdapter<ContactItem>{

	private int resource; // store the resource layout id for 1 row

	public ContactListAdapter(Context _context, int _resource, List<ContactItem> _items) {
		super(_context, _resource, _items);
		resource = _resource;
	}

	// this should be override by subclass if necessary
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewGroup parentView;

		ContactItem item = getItem(position);

		if (convertView == null) {
	    	parentView = new LinearLayout(getContext()); // Assumption: the resource parent id is a linear layout
	    	String inflater = Context.LAYOUT_INFLATER_SERVICE;
	    	LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
	    	vi.inflate(resource, parentView, true);
	    } else {
	    	parentView = (LinearLayout) convertView;
	    }

		// set row items here
		populateDataForRow(parentView, item, position);

		return parentView;
	}

	// do all the data population for the row here
	// subclass overwrite this to draw more items
	public void populateDataForRow(View parentView, ContactItem item , int position){
		// default just draw the item only
		View infoView = parentView.findViewById(R.id.infoRowContainer_contact);
		TextView fullNameView = (TextView)infoView.findViewById(R.id.contact_list_item_name);
		TextView nicknameView = (TextView)infoView.findViewById(R.id.contact_list_item_username);

		if(item instanceof ContactItem){
			ContactItem contactItem = (ContactItem)item;
			fullNameView.setText("Name: " + contactItem.getName());
		}
	}
}
