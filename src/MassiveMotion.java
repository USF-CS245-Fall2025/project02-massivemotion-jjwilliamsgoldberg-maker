/**
 * Julian Williams-Goldberg
 * CS245 (EJ)
 * Massive Motion provides the logical and UI code to create the celestial objects simulation
 * This file was provided by teaching staff but edited and added by self. 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main simulation class for celestial bodies.
 */
public class MassiveMotion extends JPanel implements ActionListener {
    
    private int width, height;
    private List<Body> bodies;
    private Timer timer;
    private Config config;
    
    /**
     * Creates simulation with default config file.
     */
    public MassiveMotion() {
        this("MassiveMotion.txt");
    }
    
    /**
     * Creates simulation with specified config file.
     */
    public MassiveMotion(String configFile) {
        config = new Config(configFile);
        
        width = config.getInt("window_size_x", 1024);
        height = config.getInt("window_size_y", 768);
        int delay = config.getInt("timer_delay", 75);
        String listType = config.getString("list", "arraylist");
        
        // Create list
        bodies = createList(listType);
        
        // Add star
        double starX = config.getDouble("star_position_x", width / 2.0);
        double starY = config.getDouble("star_position_y", height / 2.0);
        double starSize = config.getDouble("star_size", 30);
        double starVelX = config.getDouble("star_velocity_x", 0);
        double starVelY = config.getDouble("star_velocity_y", 0);
        
        bodies.add(new Body(starX, starY, starVelX, starVelY, starSize, Color.RED, true));
        
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));
        
        timer = new Timer(delay, this);
        timer.start();
    }
    
    /**
     * Creates the appropriate list type based on config.
     */
    private List<Body> createList(String type) {
        type = type.toLowerCase();
        if (type.equals("arraylist")) {
            return new ArrayList<Body>();
        } else if (type.equals("single")) {
            return new LinkedList<Body>();
        } else if (type.equals("double")) {
            return new DoublyLinkedList<Body>();
        } else if (type.equals("dummyhead")) {
            return new DummyHeadLinkedList<Body>();
        }
        return new ArrayList<Body>();
    }
    
    /**
     * Updates simulation and repaints each timer tick.
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move bodies
        for (int i = 0; i < bodies.size(); i++) {
            bodies.get(i).move();
        }
        
        // Remove off-canvas bodies
        for (int i = bodies.size() - 1; i >= 0; i--) {
            Body b = bodies.get(i);
            if (!b.isStar && (b.x < 0 || b.x > width || b.y < 0 || b.y > height)) {
                bodies.remove(i);
            }
        }
        
        // Generate new bodies
        double genX = config.getDouble("gen_x", 0.06);
        double genY = config.getDouble("gen_y", 0.06);
        double bodySize = config.getDouble("body_size", 10);
        double bodyVel = config.getDouble("body_velocity", 3);
        
        if (Math.random() < genX) {
            double x = Math.random() * width;
            double y = Math.random() < 0.5 ? 0 : height;
            double vx = (Math.random() - 0.5) * 2 * bodyVel;
            double vy = y == 0 ? Math.random() * bodyVel : -Math.random() * bodyVel;
            if (vx == 0) vx = 1;
            if (vy == 0) vy = 1;
            bodies.add(new Body(x, y, vx, vy, bodySize, Color.BLACK, false));
        }
        
        if (Math.random() < genY) {
            double x = Math.random() < 0.5 ? 0 : width;
            double y = Math.random() * height;
            double vx = x == 0 ? Math.random() * bodyVel : -Math.random() * bodyVel;
            double vy = (Math.random() - 0.5) * 2 * bodyVel;
            if (vx == 0) vx = 1;
            if (vy == 0) vy = 1;
            bodies.add(new Body(x, y, vx, vy, bodySize, Color.BLACK, false));
        }
        
        repaint();
    }
    
    /**
     * Draws all celestial bodies on the screen.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < bodies.size(); i++) {
            Body b = bodies.get(i);
            int d = (int)(2 * b.size);
            int x = (int)(b.x - b.size);
            int y = (int)(b.y - b.size);
            g.setColor(b.color);
            g.fillOval(x, y, d, d);
        }
        
        g.setColor(Color.GRAY);
        g.drawString("Bodies: " + bodies.size(), 10, 20);
    }
    
    /**
     * Main method to launch the simulation.
     */
    public static void main(String[] args) {
        String configFile = args.length > 0 ? args[0] : "MassiveMotion.txt";
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Massive Motion");
            MassiveMotion panel = new MassiveMotion(configFile);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    
    /**
     * Simple class to represent a celestial body.
     */
    private class Body {
        double x, y, vx, vy, size;
        Color color;
        boolean isStar;
        
        Body(double x, double y, double vx, double vy, double size, Color color, boolean isStar) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.size = size;
            this.color = color;
            this.isStar = isStar;
        }
        
        void move() {
            x += vx;
            y += vy;
        }
    }
}