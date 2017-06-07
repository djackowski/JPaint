import java.awt.*;

public class Line extends Shape {

    public Line(int x1, int x2, int y1, int y2) {
        super(x1, x2, y1, y2);
    }

    public void draw(Graphics g) {
        g.drawLine( x1, y1, x2, y2); //draws the line
    }
}
