package woopaca;

public class Apple extends Fruit {

    private Color color;

    public Apple() {
    }

    public Apple(Integer weight) {
        super(weight);
    }

    public Apple(Integer weight, Color color) {
        super(weight);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + super.getWeight() +
                ", color=" + color +
                '}';
    }
}
