import java.awt.*;

public class DrawRectangle extends Shape {
    public DrawRectangle(int x1, int x2, int y1, int y2) {
        super(x1, x2, y1, y2);
    }

    public void draw(Graphics g) {
        g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
    }

    public int getUpperLeftX() {
        return Math.min(getX1(), getX2());
    }

    public int getUpperLeftY() {
        return Math.min(getY1(), getY2());
    }

    public int getWidth() {
        return Math.abs(getX1() - getX2());
    }

    public int getHeight() {
        return Math.abs(getY1() - getY2());
    }
}
