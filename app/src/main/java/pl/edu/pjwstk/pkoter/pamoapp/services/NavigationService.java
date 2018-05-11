package pl.edu.pjwstk.pkoter.pamoapp.services;

import android.content.Intent;
import android.net.Uri;

import pl.edu.pjwstk.pkoter.pamoapp.domain.Address;

public class NavigationService {
    public static Intent buildNavigationIntent(Address a) {
        String address = a.getStreet() + " " + a.getHouseNumber() + ",+" + a.getCity() + " " + a.getCountry();
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        return mapIntent;
    }
}
