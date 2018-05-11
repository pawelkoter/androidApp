package pl.edu.pjwstk.pkoter.pamoapp.services;

import android.content.Context;

import pl.edu.pjwstk.pkoter.pamoapp.dataAccess.AddressDao;
import pl.edu.pjwstk.pkoter.pamoapp.dataAccess.AppDatabase;
import pl.edu.pjwstk.pkoter.pamoapp.domain.Address;

public class AddressService {

    private static volatile AddressService instance;

    private AddressService(){}

    public static AddressService getInstance() {
        if (instance == null) {
            instance = new AddressService();
        }

        return instance;
    }

    public void save(Address address, Context context) {
        AddressDao dao = AppDatabase.getInstance(context)
                .getAddressDao();

        if (address.getId() == 0) {
            dao.insert(address);
        } else {
            dao.update(address);
        }
    }
}
