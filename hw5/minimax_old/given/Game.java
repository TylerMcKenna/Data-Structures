import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Game extends JPanel {

    public static void main(String[] args) {
        Minimax model = new Minimax(); // set-up the MVC architecture
        Mouse control = new Mouse(model);
        Game view = new Game(control);

        Toolkit kit = Toolkit.getDefaultToolkit(); // figure out measurements
        Dimension screen = kit.getScreenSize();
        int length = Math.min(screen.width, screen.height);
        length = (int)(Math.log(length) / Math.log(2));
        length = (int)Math.pow(2, length);
        int pad = (int)(Math.pow(2, -5) * length);

        JPanel container = new JPanel(); // this will contain the view
        container.setBorder(new EmptyBorder(pad, pad, pad, pad));
        container.setLayout(new BorderLayout());
        container.add(view, BorderLayout.CENTER);

        JFrame frame = new JFrame(); // this will contain the container
        frame.setSize(length / 2, length / 2);
        frame.add(container, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private Mouse control;

    public Game(Mouse control) {
        this.control = control;
        this.addMouseListener(control);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.removeAll();
        this.getParentFrame().setTitle(this.control.model.message);
        this.drawLattice(g);
        this.drawMoves(g);
    }

    private JFrame getParentFrame() {
        Container con = this.getParent();
        while (!(con instanceof JFrame))
            con = con.getParent();
        return (JFrame)con;
    }

    private void drawLattice(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3));
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        x1 = getWidth() / 3;
        x2 = x1;
        y2 = getHeight();
        g2.drawLine(x1, y1, x2, y2);
        g2.drawLine(2 * x1, y1, 2 * x2, y2);
        x1 = 0;
        x2 = getWidth();
        y1 = getHeight() / 3;
        y2 = y1;
        g2.drawLine(x1, y1, x2, y2);
        g2.drawLine(x1, 2 * y1, x2, 2 * y2);
    }

    private void drawMoves(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        int[] b = this.control.model.root.board;
        int w = (int)(getWidth() / 3);
        int h = (int)(getHeight() / 3);
        for (int i = 0; i < b.length; i++) {
            if (b[i] == 0)
                continue; // vacant space
            switch (i) {
                case 0: this.mark(0, 0, w, h, b[i], g2); break;
                case 1: this.mark(w, 0, 2 * w, h, b[i], g2); break;
                case 2: this.mark(2 * w, 0, 3 * w, h, b[i], g2); break;
                case 3: this.mark(0, h, w, 2 * h, b[i], g2); break;
                case 4: this.mark(w, h, 2 * w, 2 * h, b[i], g2); break;
                case 5: this.mark(2 * w, h, 3 * w, 2 * h, b[i], g2); break;
                case 6: this.mark(0, 2 * h, w, 3 * h, b[i], g2); break;
                case 7: this.mark(w, 2 * h, 2 * w, 3 * h, b[i], g2); break;
                case 8: this.mark(2 * w, 2 * h, 3 * w, 3 * h, b[i], g2); break;
            }
        }
    }

    private void mark(int x1, int y1, int x2, int y2, int turn, Graphics2D g2) {
        int xp = (x2 - x1) / 5, yp = (y2 - y1) / 5;
        if (turn == 1) {
            g2.drawLine(x1 + xp, y1 + xp, x2 - yp, y2 - yp);
            g2.drawLine(x2 - xp, y1 + yp, x1 + xp, y2 - yp);
        } else {
            g2.drawOval(x1 + xp, y1 + yp, x2 - x1 - 2 * xp, y2 - y1 - 2 * yp);
        }
    }
}

class Mouse extends MouseAdapter {

    public Minimax model;

    public Mouse(Minimax model) {
        this.model = model;
    }

    public void mousePressed(MouseEvent e) {
        Game gm = (Game)e.getSource();
        if (e.getButton() == MouseEvent.BUTTON3)
            this.model = new Minimax(); // this was a reset click
        else
            this.changeModel(e.getX(), e.getY(), gm.getWidth(), gm.getHeight());
        gm.repaint();
    }

    private void changeModel(int x, int y, int w, int h) {
        if (x < w / 3) {
            if (y < h / 3)
                this.model.play(0);
            if (y >= h / 3 && y < 2 * h / 3)
                this.model.play(3);
            if (y >= 2 * h / 3)
                this.model.play(6);
        } else if (x >= w / 3 && x < 2 * w / 3) {
            if (y < h / 3)
                this.model.play(1);
            if (y >= h / 3 && y < 2 * h / 3)
                this.model.play(4);
            if (y >= 2 * h / 3)
                this.model.play(7);
        } else if (x >= 2 * w / 3) {
            if (y < h / 3)
                this.model.play(2);
            if (y >= h / 3 && y < 2 * h / 3)
                this.model.play(5);
            if (y >= 2 * h / 3)
                this.model.play(8);
        }
    }
}
