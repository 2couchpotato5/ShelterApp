package model;

public class Clothes {
    private String color;
    private String name;
    private int size;

    // Requires: size in Europe size chart , from 30 to 54
    public Clothes(String name, String color, int size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
