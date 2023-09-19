package ru.dvoretckii.DAO.Entities;

public enum Color {
    BLACK,
    WHITE,
    GREY,
    BROWN,
    RED;
    @Override
    public String toString() {
        return switch (this) {
            case BLACK -> "BLACK";
            case WHITE -> "WHITE";
            case GREY -> "GREY";
            case BROWN -> "BROWN";
            case RED -> "RED";
            default -> throw new IllegalArgumentException();
        };
    }
}
