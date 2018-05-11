package pl.edu.pjwstk.pkoter.pamoapp.dataAccess;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import pl.edu.pjwstk.pkoter.pamoapp.domain.Address;

@Dao
public interface AddressDao {

    @Query("SELECT * FROM address")
    List<Address> getAll();

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insert(Address address);

    @Update
    void update(Address address);

    @Delete
    void delete(Address address);
}
