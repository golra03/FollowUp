package code.com.widget;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import code.com.followup.R;
import code.com.model.FollowUpItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FollowUpListAdapter extends ArrayAdapter<FollowUpItem>{

	private int resource; // store the resource layout id for 1 row

	public FollowUpListAdapter(Context _context, int _resource, List<FollowUpItem> _items) {
		super(_context, _resource, _items);
		resource = _resource;
	}
	
	// this should be override by subclass if necessary
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewGroup parentView;
		
		FollowUpItem item = getItem(position);
		
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
	public void populateDataForRow(View parentView, FollowUpItem item , int position){
		// default just draw the item only
		View infoView = parentView.findViewById(R.id.infoRowContainer_followup);
		TextView nameView = (TextView)infoView.findViewById(R.id.followup_list_item_name);
	}
}
