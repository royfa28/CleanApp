package com.example.cleanapp.Model;

public class TenantListModel {
    String name,number, IdTenant;
//constructor
    public TenantListModel() {
    }
    //getter setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIdTenant() {
        return IdTenant;
    }

    public void setIdTenant(String idTenant) {
        IdTenant = idTenant;
    }
}
