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

//        g2.setColor(Color.ORANGE);
//
//        g2.fillRoundRect(startx, starty, width, height, arcWid, arcHei);
//
//        g2.fillRoundRect(startx, starty + (height + jian), width, height, arcWid, arcHei);
//
//        g2.fillRoundRect(startx, starty + 2 * (height + jian), width, height, arcWid, arcHei);
//
//        g2.fillRoundRect(startx, starty + 3 * (height + jian), width, height, arcWid, arcHei);
//
//        g2.setColor(Color.green);
//
//        g2.fillRect(startx + 20, starty + 3 * (height + jian) + 5, height - 10, height - 10);

        int k = 0;

        for (Integer i: MockFloor.getShelves().keySet()) {

            g2.setColor(Color.ORANGE);

            Shelf shelf = MockFloor.getShelves().get(i);

            int y;

            if(k == 0) {

                g2.fillRoundRect(startx, starty, width, height, arcWid, arcHei);

                y = starty;
            }else {

                g2.fillRoundRect(startx, starty + (height + jian)*k, width, height, arcWid, arcHei);

                y = starty + (height + jian)*k;

            }

            g2.setColor(Color.green);


//            int itemNum = 0;

//            for (Item item: setSize();)

            for (int j=0; j<shelf.contents.size(); j++) {

                g2.fillRect(startx + 30*j, y + 5, height - 10, height - 10);
            }

            System.out.println("item size " + shelf.contents.size());

            k++;
        }

    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(400, 400);
    }
}


class RobotPanel extends JPanel {


    static int scale = 30;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        g2.setColor(Color.RED);
//        g2.fillOval(50, 20, 20, 20);
//
//        g2.fillOval(80, 160, 20, 20);
//
//        g2.fillOval(70, 60, 20, 20);
//
//        g2.fillOval(20, 120, 20, 20);
//
//        g2.fillOval(40, 220, 20, 20);
//
//        g2.fillOval(90, 120, 20, 20);
//
//        g2.fillOval(120, 220, 20, 20);


        for (Robot robot : MockFloor.robots) {

            int centerx = robot.location.x * scale;

            int centery = robot.location.y*scale;

            int w = 20;

            int h = 20;


//            g2.fillOval(robot.location.x * scale, robot.location.y*scale, 20, 20);
//
//            g2.drawString(robot.NumberofRobot + "", robot.location.x * scale, robot.location.y * scale);
//
            g2.setColor(Color.RED);



            g2.fillOval(centerx - w/2, centery - h/2, w, h);

            g2.setColor(Color.BLACK);

            g2.drawString(robot.NumberofRobot + "", centerx - 3, centery + 5);

        }

    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(150, 400);
    }
}


public class Visualizer extends Thread{

    Inventory inventory;

    Floor floor;

//    List<Orders.Order> orders;

    List<Robot> robots;

    RobotPanel robotPanel;

    ShelfPanel shelfPanel;

    public Visualizer()
    {
        JFrame frame = new JFrame("Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);

        robotPanel = new RobotPanel();

        shelfPanel = new ShelfPanel();

        frame.add(robotPanel, BorderLayout.WEST);
        frame.add(shelfPanel, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);


    }

    void tick(int i)
    {
        robotPanel.repaint();

        shelfPanel.repaint();
    }


    void draw() {

    }

    void update() {

    }

    void clear() {

    }

    static void setUpGui() {

        JFrame frame = new JFrame("Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);

        frame.add(new RobotPanel(), BorderLayout.WEST);
        frame.add(new ShelfPanel(), BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void run() {
//        super.run();

        setUpGui();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setUpGui();
            }
        });

    }
}
