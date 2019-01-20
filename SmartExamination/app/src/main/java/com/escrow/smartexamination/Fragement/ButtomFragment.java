package com.escrow.smartexamination.Fragement;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.escrow.smartexamination.R;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ButtomFragment extends Fragment{


    public ButtomFragment() {



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final FragmentActivity fragmentBelongActivity = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_buttom, container, false);

        Button next = (Button) rootView.findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "next");
                Toast.makeText(fragmentBelongActivity, "You click next button.", Toast.LENGTH_SHORT).show();



            }
        });




        Button mark = (Button) rootView.findViewById(R.id.buttonMark);
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "next");
                Toast.makeText(fragmentBelongActivity, "You click mark button.", Toast.LENGTH_SHORT).show();
            }
        });

        Button previous = (Button) rootView.findViewById(R.id.buttonPrevious);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "next");
                Toast.makeText(fragmentBelongActivity, "You click previous button.", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;

    }


}
