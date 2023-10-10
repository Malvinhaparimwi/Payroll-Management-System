import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DashBoard extends JFrame{
    private JPanel Dash;
    private CardLayout cardLayout;
    private JLabel companyName;
    private JLabel employer;
    private JLabel reference;
    private JLabel lblReference;
    private JLabel empl;
    private JLabel loggedUser;
    private JLabel icon;
    private JButton new_EmployeeButton;
    private JButton attendanceButton;
    private JButton departmentsButton;
    private JPanel centerPane;
    private JButton payRollButton;
    private JPanel newEmplo;
    private JPanel Attend;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton financesButton;
    private JButton deleteEmployeeButton;

    private Connection con;

    public DashBoard(){
        setTitle("Payroll-Management-System");
        setSize(1700, 1100);
        setVisible(true);

        setBackground(new Color(32, 33, 36));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(Dash);
        setResizable(false);

        cardLayout = (CardLayout)(centerPane.getLayout());

        // Database Connection
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PayRollManagementSystem?user=root", "root", "hapa3000");
            Statement st = con.createStatement();

        } catch (Exception E){
            System.out.println(E.getMessage());
        }

        new_EmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPane, "newEmplo");
            }
        });
        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPane, "Attend");
            }
        });
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("user.png"));
    }
}
