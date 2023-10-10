import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class login extends JFrame{
    private JPanel login;
    private JLabel lblLogin;
    private JButton OKButton;
    private JButton btnCancel;
    private JTextField txtUser;
    private JTextField txtPass;
    private JLabel userName;
    private JLabel passWord;

    public login() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PayRollManagementSystem?user=root", "root", "hapa3000");
                    String userName = txtUser.getText();
                    String passWord = txtPass.getText();

                    Statement query = con.createStatement();
                    String sql = "select * from Login where Work_ID='"+userName+"' and Pass_Word='"+passWord+"'";
                    ResultSet result = query.executeQuery(sql);


                    if (result.next()){
                        dispose();
                        String userType = result.getString("User_Type");

                        if (userType.equals("admin")){
                            var dash = new DashBoard();
                        } else if (userType.equals("normal")){
                            JOptionPane.showMessageDialog(login, "No for you yet");
                        }

                    } else {
                        JOptionPane.showMessageDialog(login, "Incorrect credentials");
                        txtPass.setText("");
                        txtUser.setText("");
                    }

                    con.close();

                } catch (Exception E){
                    System.out.println(E.getMessage());
                }
            }
        });
    }

    public void startApp(){
        setTitle("Payroll-Management-System");
        setSize(800, 800);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(login);
        setResizable(false);

        customTextField(txtPass);
        customTextField(txtUser);
    }

    private void customTextField(JTextField field){
        field.setBorder(new LineBorder(Color.BLACK));
    }
}
