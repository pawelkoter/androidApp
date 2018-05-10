package pl.edu.pjwstk.pkoter.pamoapp.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pjwstk.pkoter.pamoapp.R;
import pl.edu.pjwstk.pkoter.pamoapp.dataAccess.AddressDao;
import pl.edu.pjwstk.pkoter.pamoapp.dataAccess.AppDatabase;
import pl.edu.pjwstk.pkoter.pamoapp.domain.Address;

public class AddressListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AddressDao addressDao = AppDatabase.getInstance(getContext()).getAddressDao();


        List<Address> addresses = addressDao.getAll();

        List<String> labels = new ArrayList<>();
        for (Address address : addresses) {
                        labels.add(address.getName()) ;
        }

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, labels));
    }

    @Override
    public void onStart() {
        super.onStart();

        boolean inLandscapeMode = getFragmentManager().findFragmentById(R.id.address_edit_fragment) != null;

        if (inLandscapeMode) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }
}
