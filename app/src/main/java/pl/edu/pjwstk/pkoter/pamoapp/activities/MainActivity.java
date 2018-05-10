package pl.edu.pjwstk.pkoter.pamoapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import pl.edu.pjwstk.pkoter.pamoapp.R;
import pl.edu.pjwstk.pkoter.pamoapp.fragments.AddressEditFragment;
import pl.edu.pjwstk.pkoter.pamoapp.fragments.AddressListFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean horizontalOrientation = findViewById(R.id.main_horizontal_container) != null;
        if (horizontalOrientation) {

            if (savedInstanceState != null) {
                return;
            }

            AddressListFragment firstFragment = new AddressListFragment();
            firstFragment.setArguments(getIntent().getExtras());

            AddressEditFragment addressEditFragment = (AddressEditFragment)
                    getFragmentManager().findFragmentById(R.id.address_edit_fragment);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.add(R.id.main_horizontal_container, firstFragment);

            if (addressEditFragment != null) {
                transaction.add(R.id.address_edit_fragment, addressEditFragment);
            }

            transaction.commit();
        }
    }
}
