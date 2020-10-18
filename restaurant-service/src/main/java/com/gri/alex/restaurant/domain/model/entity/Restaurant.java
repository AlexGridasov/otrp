package com.gri.alex.restaurant.domain.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends BaseEntity<String> {

    private List<Table> tables;
    private String address;

    public Restaurant(String name, String id, String address, List<Table> tables) {
        super(id, name);
        this.address = address;
        this.tables = tables;
    }

    private Restaurant(String name, String id) {
        super(id, name);
        this.tables = new ArrayList<>();
    }

    public static Restaurant getDummyRestaurant() {
        return new Restaurant(null, null);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, address: %s, tables: %s}",
                this.getId(), this.getName(), this.getAddress(), this.getTables());
    }
}
