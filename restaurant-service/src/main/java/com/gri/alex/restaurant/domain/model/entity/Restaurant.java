package com.gri.alex.restaurant.domain.model.entity;

import java.util.List;

public class Restaurant extends BaseEntity<String> {

    private List<Table> tables;

    public Restaurant(String name, String id, List<Table> tables) {
        super(id, name);
        this.tables = tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", name: "
                + name + ", tables: " + tables + "}";
    }
}
