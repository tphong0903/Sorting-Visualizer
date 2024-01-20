import javax.swing.*;
public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sort");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new Sort());
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}