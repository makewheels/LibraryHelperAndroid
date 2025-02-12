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
public class CatalogFragment extends Fragment {
    private TextView tv_catalog;

    public CatalogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        tv_catalog = view.findViewById(R.id.tv_catalog);
        tv_catalog.setText(TabData.catalog);
        return view;
    }

}
