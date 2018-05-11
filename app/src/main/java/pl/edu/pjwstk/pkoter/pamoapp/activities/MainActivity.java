package pl.edu.pjwstk.pkoter.pamoapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import pl.edu.pjwstk.pkoter.pamoapp.R;
import pl.edu.pjwstk.pkoter.pamoapp.fragments.AddressEditFragment;
import pl.edu.pjwstk.pkoter.pamoapp.fragments.AddressListFragment;

public class MainActivity extends Activity
        implements AddressListFragment.OnNewAddressButtonClickListener,
                   AddressEditFragment.OnAddressSavedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean verticalOrientation = findViewById(R.id.main_vertical_container) != null;
        if (verticalOrientation) {

            if (savedInstanceState != null) {
                return;
            }

            AddressListFragment addressListFragment = new AddressListFragment();
            addressListFragment.setArguments(getIntent().getExtras());

            AddressEditFragment addressEditFragment = (AddressEditFragment)
                    getFragmentManager().findFragmentById(R.id.address_edit_fragment);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.add(R.id.main_vertical_container, addressListFragment);

            if (addressEditFragment != null) {
                transaction.add(R.id.address_edit_fragment, addressEditFragment);
            }

            transaction.commit();
        }
    }

    @Override
    public void onNewAddressButtonClick() {
        AddressEditFragment addressEditFragment = (AddressEditFragment)
                getFragmentManager().findFragmentById(R.id.address_edit_fragment);

        boolean horizontalOrientation = addressEditFragment != null;
        if (horizontalOrientation) {
            //do nothing for now
        } else {
            AddressEditFragment newFragment = new AddressEditFragment();
//            Bundle args = new Bundle();
//            args.putInt(MyWebViewFragment.ARG_POSITION, position);
//            newFragment.setArguments(args);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main_vertical_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onAddressSaved() {
        getFragmentManager().popBackStackImmediate();

        boolean verticalOrientation = findViewById(R.id.main_vertical_container) != null;

        AddressListFragment listFragment;

        if(verticalOrientation) {
            listFragment = (AddressListFragment)
                    getFragmentManager().findFragmentById(R.id.main_vertical_container);

        } else {
            listFragment = (AddressListFragment)
                    getFragmentManager().findFragmentById(R.id.address_list_fragment);
        }

        listFragment.refreshList();
    }
}
