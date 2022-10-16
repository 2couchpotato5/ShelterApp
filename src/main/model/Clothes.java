package model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Clothes clothes = (Clothes) o;
        return size == clothes.size && color.equals(clothes.color) && name.equals(clothes.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name, size);
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
