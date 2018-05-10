package pl.edu.pjwstk.pkoter.pamoapp.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pl.edu.pjwstk.pkoter.pamoapp.R;
import pl.edu.pjwstk.pkoter.pamoapp.dataAccess.AddressDao;
import pl.edu.pjwstk.pkoter.pamoapp.dataAccess.AppDatabase;
import pl.edu.pjwstk.pkoter.pamoapp.domain.Address;

public class AddressEditFragment extends Fragment {

    private Address mAddress;
    private EditText mNameEdit;
    private EditText mCountryEdit;
    private EditText mCityEdit;
    private EditText mStreetEdit;
    private EditText mHouseNumberEdit;
    private EditText mApartmentEdit;

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
        View view = inflater.inflate(R.layout.fragment_address_edit, container, false);

        mNameEdit = view.findViewById(R.id.address_edit_name);
        mCountryEdit = view.findViewById(R.id.address_edit_country);
        mCityEdit = view.findViewById(R.id.address_edit_city);
        mStreetEdit = view.findViewById(R.id.address_edit_street);
        mHouseNumberEdit = view.findViewById(R.id.address_edit_house_number);
        mApartmentEdit = view.findViewById(R.id.address_edit_apartment);

        if (mAddress == null) {
            mAddress = new Address();
        }

        mNameEdit.setText(mAddress.getName());
        mCountryEdit.setText(mAddress.getCountry());
        mCityEdit.setText(mAddress.getCity());
        mStreetEdit.setText(mAddress.getStreet());
        mHouseNumberEdit.setText(mAddress.getHouseNumber());
        mApartmentEdit.setText(mAddress.getApartment());

        Button saveBtn = view.findViewById(R.id.save_address_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Address address = buildAddress();
                saveAddress(address);
            }
        });

        return view;
    }

    private Address buildAddress() {
        String name = mNameEdit.getText().toString();
        String country = mCountryEdit.getText().toString();
        String city = mCityEdit.getText().toString();
        String street = mStreetEdit.getText().toString();
        String houseNumber = mHouseNumberEdit.getText().toString();
        String apartment = mApartmentEdit.getText().toString();

        Address address = mAddress
                .setNameFluent(name)
                .setCountryFluent(country)
                .setCityFluent(city)
                .setStreetFluent(street)
                .setHouseNumberFluent(houseNumber)
                .setApartmentFluent(apartment);

        return address;
    }

    private void saveAddress(Address address) {
        AddressDao addressDao = AppDatabase.getInstance(getContext()).getAddressDao();
        addressDao.insert(address);
    }
}
