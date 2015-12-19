package code.com.activity.followup.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import code.com.activity.followup.FollowUpItemActivity;
import code.com.activity.registration.RegistrationActivity;
import code.com.followup.R;


public class FollowUpFragment extends Fragment
{

    public FollowUpFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_followup, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.newfollowup);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(getActivity().getApplicationContext(), FollowUpItemActivity.class);

                getActivity().startActivity(intent);
            }
        });
        return view;
    }

}
