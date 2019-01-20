package com.escrow.smartexamination.Fragement;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.escrow.smartexamination.R;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public BlankFragment() {




    }

        public static BlankFragment newInstance(int sectionNumber) {

            BlankFragment fragment = new BlankFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            Log.d(TAG, "newInstance: "+sectionNumber+ARG_SECTION_NUMBER);
            fragment.setArguments(args);

            return fragment;
        }
        // Required empty public constructor




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        TextView Questionnum = (TextView) rootView.findViewById(R.id.Qid);
        Questionnum.setText("Question "+getArguments().getInt(ARG_SECTION_NUMBER));
        return rootView;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_blank, container, false);
    }
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);
            Log.d(TAG, "SectionsPagerAdapter: "+fm);
        }

        @Override
        public Fragment getItem(int position) {

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return BlankFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 15;
        }
    }

}
