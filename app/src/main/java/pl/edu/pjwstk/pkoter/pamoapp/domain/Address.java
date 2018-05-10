package pl.edu.pjwstk.pkoter.pamoapp.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Address {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String apartment;

    public int getId() {
        return id;
    }

    public Address setIdFluent(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Address setNameFluent(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Address setCountryFluent(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCityFluent(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreetFluent(String street) {
        this.street = street;
        return this;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public Address setHouseNumberFluent(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public String getApartment() {
        return apartment;
    }

    public Address setApartmentFluent(String apartment) {
        this.apartment = apartment;
        return this;
    }

    //java beans setters for room library
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
