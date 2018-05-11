package pl.edu.pjwstk.pkoter.pamoapp.fragments;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pl.edu.pjwstk.pkoter.pamoapp.R;
import pl.edu.pjwstk.pkoter.pamoapp.domain.Address;
import pl.edu.pjwstk.pkoter.pamoapp.services.AddressService;

public class AddressEditFragment extends Fragment {
    private static final String ARG_ADDRESS = "ADDRESS";

    private Address mAddress;
    private EditText mNameEdit;
    private EditText mCountryEdit;
    private EditText mCityEdit;
    private EditText mStreetEdit;
    private EditText mHouseNumberEdit;
    private EditText mApartmentEdit;
    private Button mDeleteButton;

    private OnAddressSavedListener mOnAddressSavedListener;

    public interface OnAddressSavedListener {
        void onAddressSaved();
    }

    public AddressEditFragment() {
        // Required empty public constructor
    }

    public static AddressEditFragment newInstance(Address address) {

        AddressEditFragment fragment = new AddressEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ADDRESS, address);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAddress = (Address) getArguments().getSerializable(ARG_ADDRESS);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = null;

        if (context instanceof Activity){
            activity = (Activity) context;
        }

        if (activity instanceof OnAddressSavedListener) {
            mOnAddressSavedListener = (OnAddressSavedListener) activity;
        }
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
        mDeleteButton = view.findViewById(R.id.delete_address_btn);

        if (mAddress == null) {
            mAddress = new Address();
        }

        setupForm();

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        Button saveBtn = view.findViewById(R.id.save_address_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Address address = buildAddress();
                saveAddress(address);
                mOnAddressSavedListener.onAddressSaved();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            AddressService.getInstance().delete(mAddress, getContext());
            mOnAddressSavedListener.onAddressSaved();
        }
    }

    public void editAddress(Address address) {
        mAddress = address;
        setupForm();
    }

    private void setupForm() {
        mNameEdit.setText(mAddress.getName());
        mCountryEdit.setText(mAddress.getCountry());
        mCityEdit.setText(mAddress.getCity());
        mStreetEdit.setText(mAddress.getStreet());
        mHouseNumberEdit.setText(mAddress.getHouseNumber());

        mDeleteButton.setEnabled(mAddress.getId() > 0 );
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
        AddressService.getInstance().save(address, getContext());
    }

    private void showDialog() {
        DialogFragment dialogFragment = new DeleteAddressDialogFragment();
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(getFragmentManager(), "delete");
    }
}
