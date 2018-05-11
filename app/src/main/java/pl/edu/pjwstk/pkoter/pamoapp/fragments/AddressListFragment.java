package pl.edu.pjwstk.pkoter.pamoapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.edu.pjwstk.pkoter.pamoapp.R;
import pl.edu.pjwstk.pkoter.pamoapp.dataAccess.AppDatabase;
import pl.edu.pjwstk.pkoter.pamoapp.domain.Address;

public class AddressListFragment extends Fragment {

    private OnNewAddressButtonClickListener mNewAddressButtonClickListener;
    private OnAddressClickListener mOnAddressClickListener;

    public interface OnNewAddressButtonClickListener {
        void onNewAddressButtonClick();
    }

    public interface OnAddressClickListener {
        void onAddressClick(Address address);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = null;

        if (context instanceof Activity){
            activity = (Activity) context;
        }

        if (activity instanceof OnNewAddressButtonClickListener) {
            mNewAddressButtonClickListener = (OnNewAddressButtonClickListener) activity;
        }

        if (activity instanceof OnAddressClickListener) {
            mOnAddressClickListener = (OnAddressClickListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list, container, false);
        Context context = getContext();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.new_address_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewAddressButtonClickListener.onNewAddressButtonClick();
            }
        });

        List<Address> addresses = getAddresses(context);

        RecyclerView.Adapter adapter = new AddressViewAdapter(addresses, mOnAddressClickListener);

        RecyclerView addressListView = (RecyclerView) view.findViewById(R.id.address_list);

        addressListView.setLayoutManager(new LinearLayoutManager(context));
        addressListView.setAdapter(adapter);

        return view;
    }

    public void refreshList() {
        Context context = getActivity();
        List<Address> addresses = getAddresses(context);

        RecyclerView addressListView = (RecyclerView) getView().findViewById(R.id.address_list);
        RecyclerView.Adapter adapter = new AddressViewAdapter(addresses, mOnAddressClickListener);
        addressListView.setAdapter(adapter);
    }

    private List<Address> getAddresses(Context context) {
        return AppDatabase.getInstance(context)
                .getAddressDao()
                .getAll();
    }
}
