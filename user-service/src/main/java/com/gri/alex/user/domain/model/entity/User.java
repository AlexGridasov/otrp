package com.gri.alex.user.domain.model.entity;

public class User extends BaseEntity<String> {

    private String address;
    private String city;
    private String phoneNo;

    public User(String id, String name, String address, String city, String phoneNo) {
        super(id, name);
        this.address = address;
        this.city = city;
        this.phoneNo = phoneNo;
    }

    private User(String id, String name) {
        super(id, name);
    }

    public static User getDummyUser() {
        return new User(null, null);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, address: %s, city: %s, phoneNo: %s}",
                id, name, address, city, phoneNo);
    }
}
