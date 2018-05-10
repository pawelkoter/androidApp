package pl.edu.pjwstk.pkoter.pamoapp.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import pl.edu.pjwstk.pkoter.pamoapp.R;

public class AddressListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] WEB_ADDRESSES = {
                "ABC",
                "DEF"
        };

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, WEB_ADDRESSES));
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
