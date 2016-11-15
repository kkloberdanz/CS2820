package production;

import java.util.List;


import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Wenzhi Ding
 *
 */

class ShelfPanel extends JPanel {


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int width = 300;

        int height = 50;

        int jian = 20;

        int startx = 50;

        int starty = 50;

        int arcWid = 20;

        int arcHei = 20;

        g2.setColor(Color.ORANGE);

        g2.fillRoundRect(startx, starty, width, height, arcWid, arcHei);

        g2.fillRoundRect(startx, starty + (height + jian), width, height, arcWid, arcHei);

        g2.fillRoundRect(startx, starty + 2 * (height + jian), width, height, arcWid, arcHei);

        g2.fillRoundRect(startx, starty + 3 * (height + jian), width, height, arcWid, arcHei);

        g2.setColor(Color.green);

        g2.fillRect(startx + 20, starty + 3 * (height + jian) + 5, height - 10, height - 10);

    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(400, 400);
    }
}


class RobotPanel extends JPanel {


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        g2.setColor(Color.RED);
        g2.fillOval(50, 20, 20, 20);

        g2.fillOval(80, 160, 20, 20);

        g2.fillOval(70, 60, 20, 20);

        g2.fillOval(20, 120, 20, 20);

        g2.fillOval(40, 220, 20, 20);

        g2.fillOval(90, 120, 20, 20);

        g2.fillOval(120, 220, 20, 20);

    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(150, 400);
    }
}


public class Visualizer {

    Inventory inventory;

    Floor floor;

    List<Orders.Order> orders;

    List<Robot> robots;

    void draw() {

    }

    void update() {

    }

    void clear() {

    }

    private static void setUpGui() {

        JFrame frame = new JFrame("Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);

        frame.add(new RobotPanel(), BorderLayout.WEST);
        frame.add(new ShelfPanel(), BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setUpGui();
            }
        });

    }
}
