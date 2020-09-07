package Utils;

import javafx.scene.paint.Color;

public class Rectangle extends javafx.scene.shape.Rectangle {

    Color baseColor;

    Rectangle()
    {
        super();
    }

    public Color getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }
}
