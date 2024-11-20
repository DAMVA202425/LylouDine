package com.example.lyloudine;

public class Mesa {
    private final String name;
    private final String price;

    public Mesa(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}

