package com.visa.prj.entity;

public class Mobile extends Product{
    private String connectivity;

    public Mobile() {
    }

    public Mobile(int id, String name, double price, String connectivity) {
        super(id, name, price);
        this.connectivity = connectivity;
    }

    public String getConnectivity() {
        return connectivity;
    }

    public void setConnectivity(String connectivity) {
        this.connectivity = connectivity;
    }

    @Override
    public boolean isExpensive() {
        if("3G".equals(getConnectivity()) && getPrice() > 4500) {
            return true;
        } else  if("5G".equals(getConnectivity()) && getPrice() > 30000) {
            return true;
        }
        return  false;
    }
}
