package ru.dvoretckii.Entities;
public enum Color {
    Black,
    White,
    Grey,
    Brown,
    Red;
    @Override
    public String toString() {
        return switch (this) {
            case Black -> "Black";
            case White -> "White";
            case Grey -> "Grey";
            case Brown -> "Brown";
            case Red -> "Red";
            default -> throw new IllegalArgumentException();
        };
    }
}
