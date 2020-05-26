package com.eg.libraryhelperandroid.bookdetail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.eg.libraryhelperandroid.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {
    private TextView tv_summary;

    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        tv_summary = view.findViewById(R.id.tv_summary);
        tv_summary.setText(TabData.summary);
        return view;
    }

}
