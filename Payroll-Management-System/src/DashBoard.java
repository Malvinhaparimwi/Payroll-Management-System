import javax.swing.*;

public class DashBoard extends JFrame{
    private JPanel DashBoard;

    public void start() {
        setTitle("Payroll-Management-System");
        setSize(1000, 900);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(DashBoard);
        setResizable(false);
    }
}
