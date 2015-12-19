package code.com.widget;

import android.widget.ListView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListAdapter;

public class FollowUpListView extends ListView{

	//protected boolean mIsFastScrollEnabled = false;

	// additional customization
	protected boolean inSearchMode = false; // whether is in search mode
	protected boolean autoHide = false; // alway show the scroller
	
	
	public FollowUpListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FollowUpListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public boolean isInSearchMode() {
		return inSearchMode;
	}

	public void setInSearchMode(boolean inSearchMode) {
		this.inSearchMode = inSearchMode;
	}
	
	
}
