package com.example.lyloudinev2;

public class Mesa {

    private long id;
    private String name;
    private String category;
    private int iconResource; // Nuevo campo para el icono

    public Mesa(long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    // Métodos getter y setter para el campo iconResource
    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return name;
    }
}
