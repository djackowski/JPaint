import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    private List<Shape> shapes;
    private List<Shape> clearedShapes;
    private Shape currentShape;
    private ShapeType currentShapeType;

    private BufferedImage image;


    public DrawPanel() {
        shapes = new ArrayList<Shape>();
        clearedShapes = new ArrayList<Shape>();

        currentShapeType = ShapeType.LINE;

        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape shape : shapes) {
            shape.draw(g);
        }
        if (currentShape != null)
            currentShape.draw(g);

        if (image == null) {
            image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_3BYTE_BGR);
        }
        g.drawImage(image, 0, 0, null);


    }

    public void clearLast() {
        if (!shapes.isEmpty()) {
            clearedShapes.add(shapes.remove(0));
            repaint();
        }
    }

    public void redoLast() {
        if (!clearedShapes.isEmpty()) {
            shapes.add(clearedShapes.remove(0));
            repaint();
        }
    }

    public void clearDrawing() {
        shapes.clear();
        clearedShapes.clear();
        repaint();
    }

    private class MouseHandler extends MouseAdapter {

        public void mousePressed(MouseEvent event) {
            switch (currentShapeType) {
                case LINE:
                    currentShape = new Line(event.getX(), event.getY(),
                            event.getX(), event.getY());
                    System.out.println(event.getX() + "  " + event.getY());
                    break;
                case RECTANGLE:
                    currentShape = new DrawRectangle(event.getX(), event.getY(),
                            event.getX(), event.getY());
                    break;
                case OVAL:
                    currentShape = new DrawElipse(event.getX(), event.getY(),
                            event.getX(), event.getY());
                    System.out.println(event.getX() + "  " + event.getY());
                    break;

            }
        }

        public void mouseReleased(MouseEvent event) {
            currentShape.setX2(event.getX());
            currentShape.setY2(event.getY());

            shapes.add(currentShape);

            currentShape = null;
            clearedShapes.clear();
            repaint();

        }


        public void mouseDragged(MouseEvent event) {
            currentShape.setX2(event.getX());
            currentShape.setY2(event.getY());

            repaint();

        }

    }

    public void setCurrentShapeType(int i) {
        switch (i) {
            case 0:
                currentShapeType = ShapeType.LINE;
                break;
            case 1:
                currentShapeType = ShapeType.RECTANGLE;
                break;
            case 2:
                currentShapeType = ShapeType.OVAL;
                break;

        }
    }

    public enum ShapeType {
        LINE,
        OVAL,
        RECTANGLE
    }
}
