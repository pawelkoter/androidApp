package pl.edu.pjwstk.pkoter.pamoapp.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.edu.pjwstk.pkoter.pamoapp.R;

public class AddressEditFragment extends Fragment {

    public AddressEditFragment() {
        // Required empty public constructor
    }

    public static AddressEditFragment newInstance(String param1, String param2) {
        AddressEditFragment fragment = new AddressEditFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address_edit, container, false);
    }

}
