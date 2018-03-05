package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;


public abstract class User {
    private final StringProperty nameFX;
    private final StringProperty addressFX;
    private final StringProperty cardFX;
    private final StringProperty phoneFX;

    private String name;
    private String address;
    private String phoneNumber;
    private String cardNumber;
    private String password;

    public User(String name, String address, String phoneNumber, String cardNumber, String password) {
        this.name = name;
        this.nameFX = new SimpleStringProperty(name);
        this.address = address;
        this.addressFX = new SimpleStringProperty(address);
        this.phoneNumber = phoneNumber;
        this.phoneFX = new SimpleStringProperty(phoneNumber);
        this.cardNumber = cardNumber;
        this.cardFX = new SimpleStringProperty(cardNumber);
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StringProperty nameProperty() {
        return nameFX;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StringProperty addressProperty() {return addressFX;}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringProperty phoneProperty() {return phoneFX;}

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StringProperty cardProperty() {return cardFX;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
