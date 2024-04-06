package ru.jxhwy.corp.quaddro;

public class Card {
    int score;
    String color;
    String shape;

    public Card(int score, String color, String shape) {
        this.score = score;
        this.color = color;
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Card{" +
            "score=" + score +
            ", color='" + color + '\'' +
            ", shape='" + shape + '\'' +
            '}';
    }
}
