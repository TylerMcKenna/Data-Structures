import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sierpinski extends JPanel {
    public static void main(String[] args) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        JFrame frame = new JFrame();
        frame.setTitle("Sierpinksi's Triangle");
        frame.setSize(screenSize.width / 2, screenSize.height / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Sierpinski());
        frame.setVisible(true);
    }
    public void paintComponent(Graphics g) {
        CoordinateList ls = new CoordinateList();
        sierpinski(0, 0, getWidth(), getHeight(), ls);
        while (!ls.isEmpty()) {
            Coordinates c = ls.take();
            g.drawRect(c.x, c.y, c.w, c.h);
        }
    }
    // COMMENTED OLDER CODE
    public void sierpinski(int x, int y, int w, int h, CoordinateList ls) { // remove ,int count
        /*if (w > 1 && h > 1) {
        ls.add(x,y,w,h);
        sierpinski(x, y, w/2, h/2, ls);
        sierpinski(x + w/2, y, w/2, h/2, ls);
        sierpinski(x + w/4, y + h/2, w/2, h/2, ls);
        // recursively add the coordinates (x, y, w, h) to ls.
        */
        if (w <= 1 || h <= 1) {
            ls.add(x, y, w, h);
        } else {
            sierpinski(x+w/4, y, w/2, h/2, ls);
            sierpinski(x, y+h/2, w/2, h/2, ls);
            sierpinski(x+w/2, y+h/2, w/2, h/2, ls);
        }
    }
}