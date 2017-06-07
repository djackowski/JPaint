import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DrawFrame extends JFrame {

    private String lineColorOptions[] = {"Green", "Red", "Blue"};
    private String lineThickOptions[] = {"Low", "Medium", "High"};
    private String lineStyleOptions[] = {"Straight", "Dashed"};
    private String fillStyleOptions[] = {};
    private String fillColorOptions[] = {"Red", "Green", "Blue"};
    private String shapeStyleOptions[] = {"Line", "Rectangle", "Oval"};

    private JComboBox lineColor;
    private JComboBox lineThick;
    private JComboBox lineStyle;
    private JComboBox fillStyle;
    private JComboBox fillColor;
    private JComboBox shapeStyle;
    private JButton undo;
    private JButton redo;
    private JButton clear;
    private JButton addPlugin;

    private DrawPanel drawPanel;

    public DrawFrame() {
        super("JPaint");

        drawPanel = new DrawPanel();

        undo = new JButton("Undo");
        redo = new JButton("Redo");
        addPlugin = new JButton("Add Plugin");
        clear = new JButton("Clear");

        lineColor = new JComboBox(lineColorOptions);
        lineThick = new JComboBox(lineThickOptions);
        lineStyle = new JComboBox(lineStyleOptions);
        fillStyle = new JComboBox(fillStyleOptions);
        fillColor = new JComboBox(fillColorOptions);
        shapeStyle = new JComboBox(shapeStyleOptions);

        lineColor.setRenderer(new MyComboBoxRenderer("Line Color"));
        lineColor.setSelectedIndex(-1);
        lineThick.setRenderer(new MyComboBoxRenderer("Line Thick"));
        lineThick.setSelectedIndex(-1);
        lineStyle.setRenderer(new MyComboBoxRenderer("Line Style"));
        lineStyle.setSelectedIndex(-1);
        fillStyle.setRenderer(new MyComboBoxRenderer("Fill Style"));
        fillStyle.setSelectedIndex(-1);
        fillColor.setRenderer(new MyComboBoxRenderer("Fill Color"));
        fillColor.setSelectedIndex(-1);
        shapeStyle.setRenderer(new MyComboBoxRenderer("Shape Style"));
        shapeStyle.setSelectedIndex(-1);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5));

        jPanel.add(undo);
        jPanel.add(redo);
        jPanel.add(clear);
        jPanel.add(addPlugin);
        jPanel.add(lineColor);
        jPanel.add(lineThick);
        jPanel.add(lineStyle);
        jPanel.add(fillStyle);
        jPanel.add(fillColor);
        jPanel.add(shapeStyle);

        add(jPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

        ItemListenerHandler handler = new ItemListenerHandler();

        shapeStyle.addItemListener( handler );
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawPanel.clearLast();
            }
        });

        redo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawPanel.redoLast();
            }
        });
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawPanel.clearDrawing();
            }
        });



    }

    private class ItemListenerHandler implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            // process filled checkbox events
         /*   if (event.getSource() == filled) {
                boolean checkFill = filled.isSelected() ? true : false; //
                panel.setCurrentShapeFilled(checkFill);
            }*/

            // determine whether combo box selected
            if (event.getStateChange() == ItemEvent.SELECTED) {
              /*  //if event source is combo box colors pass in colorArray at index selected.
                if (event.getSource() == colors) {
                    drawPanel.setCurrentShapeColor
                            (colorArray[colors.getSelectedIndex()]);
                }*/

                //else if event source is combo box shapes pass in index selected
                 if (event.getSource() == shapeStyle) {
                    drawPanel.setCurrentShapeType(shapeStyle.getSelectedIndex());
                }
            }

        } // end method itemStateChanged
    }

    class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
        private String _title;

        public MyComboBoxRenderer(String title) {
            _title = title;
        }

        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean hasFocus) {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
}
