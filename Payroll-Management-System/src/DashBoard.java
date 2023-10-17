import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
    private JTextField txtEName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JTextField txtIDNo;
    private JButton financesButton;
    private JButton deleteEmployeeButton;
    private JTable attendance;
    private JPanel finance;
    private JTextField txtTax;
    private JTextField txtFuneral;
    private JTextField txtMedical;
    private JPanel departments;
    private JLabel lblTax;
    private JLabel lblFuneral;
    private JLabel lblMedical;
    private JButton btnUpdateTax;
    private JButton btnUpdateFuuneral;
    private JButton btnUpdateMedical;
    private JButton btnNext;
    private JTextField txtAttName;
    private JTextField txtAttWorkID;
    private JButton btnPrev;
    private JButton btnClearAtt;
    private JTextField txtHoursWorked;
    private JTextField txtOvertime;
    private JButton btnEnterAtt;
    private JTextField txtWorkID;
    private JComboBox cmbDept;
    private JComboBox cmbGender;
    private JButton btnClear;
    private JButton btnAddEmplo;
    private JTextField txtEmail;
    private JButton btnCrtUser;
    private JPanel Payroll;
    private JPanel delEmplo;
    private JTextField textField1;
    private int stepRow;
    private int start;
    private Connection con;
    private Statement st;
    protected String currentUser = "mal";
    private ResultSet atteResult;

    public DashBoard(){
        setTitle("Payroll-Management-System");
        setSize(1700, 1100);
        setVisible(true);

        setBackground(new Color(32, 33, 36));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(Dash);
        setResizable(false);

        start = 1;
        loggedUser.setText(currentUser);

        cardLayout = (CardLayout)(centerPane.getLayout());

        // comboboxes for new employee
        cmbDept.addItem("HR");
        cmbDept.addItem("Finance");
        cmbDept.addItem("Operations");

        cmbGender.addItem("Female");
        cmbGender.addItem("Male");


        // Database Connection
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PayRollManagementSystem?user=root", "root", "hapa3000");
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            // Financial data
            String finQuery = "SELECT * FROM finance;";
            ResultSet finRes = st.executeQuery(finQuery);


            if (finRes.next()){
                lblTax.setText(finRes.getString("Tax"));
                lblFuneral.setText(finRes.getString("Funeral"));
                lblMedical.setText(finRes.getString("Medical"));
            }
            finRes.close();

            // Attendance connection
            String attJoin = "SELECT e.eName, a.Work_ID, a.Hours_Worked, a.Over_Time FROM attendance a JOIN employee e ON e.WorkID = a.Work_ID;";
            atteResult = st.executeQuery(attJoin);
            atteResult.last();
            stepRow = atteResult.getRow();

            if (atteResult.next()){
                txtAttName.setText(atteResult.getString("eName"));
                txtAttWorkID.setText(atteResult.getString("Work_ID"));
            }

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
        financesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPane, "finance");
            }
        });
        departmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPane, "departments");
            }
        });
        btnUpdateTax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTax.getText().equals(""))
                    JOptionPane.showMessageDialog(Dash, "The Tax field is empty");
                else {
                    int tax = Integer.parseInt(txtTax.getText());
                    if (tax > 100 || tax < 0) {
                        JOptionPane.showMessageDialog(Dash, "Invalid tax value: \nTax range from 0% to 100%");
                        txtTax.setText("");
                    } else {
                        String msg = "You are about to change tax percentage:\n* Old:" + lblTax.getText() + "\n* New: " + tax;
                        int choice = JOptionPane.showOptionDialog(Dash, msg, "Payroll", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, new String[]{"Yes", "No"}, "No");

                        if (choice == JOptionPane.YES_OPTION) {
                            try {
                                String newTax = txtTax.getText();
                                int rowsffected = st.executeUpdate("UPDATE finance SET Tax='" + newTax + "' LIMIT 1;");
                                lblTax.setText(newTax);
                                txtTax.setText("");
                            } catch (Exception u) {
                                System.out.println(u);
                            }
                        }
                    }
                }
            }
        });
        btnUpdateFuuneral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFuneral.getText().equals(""))
                    JOptionPane.showMessageDialog(Dash, "The Funeral field is empty");
                else {
                    int fun = Integer.parseInt(txtFuneral.getText());
                    if (fun > 100 || fun < 0) {
                        JOptionPane.showMessageDialog(Dash, "Invalid Funeral field value: \nFuneral range from 0% to 100%");
                        txtFuneral.setText("");
                    } else {
                        String msg = "You are about to change Funeral percentage:\n* Old:" + lblFuneral.getText() + "\n* New: " + fun;
                        int choice = JOptionPane.showOptionDialog(Dash, msg, "Payroll", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, new String[]{"Yes", "No"}, "No");

                        if (choice == JOptionPane.YES_OPTION) {
                            try {
                                String newFun = txtFuneral.getText();
                                int rowsffected = st.executeUpdate("UPDATE finance SET Tax='" + newFun + "' LIMIT 1;");
                                lblFuneral.setText(newFun);
                                txtFuneral.setText("");
                            } catch (Exception u) {
                                System.out.println(u);
                            }
                        }
                    }
                }
            }
        });
        btnUpdateMedical.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMedical.getText().equals(""))
                    JOptionPane.showMessageDialog(Dash, "The Medical field is empty");
                else {
                    int med = Integer.parseInt(txtMedical.getText());
                    if (med > 100 || med < 0) {
                        JOptionPane.showMessageDialog(Dash, "Invalid Medical field value: \nMedical range from 0% to 100%");
                        txtMedical.setText("");
                    } else {
                        String msg = "You are about to change Medical percentage:\n* Old:" + lblMedical.getText() + "\n* New: " + med;
                        int choice = JOptionPane.showOptionDialog(Dash, msg, "Payroll", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, new String[]{"Yes", "No"}, "No");

                        if (choice == JOptionPane.YES_OPTION) {
                            try {
                                String newMed = txtMedical.getText();
                                int rowsffected = st.executeUpdate("UPDATE finance SET Medical='" + newMed + "' LIMIT 1;");
                                lblMedical.setText(newMed);
                                txtMedical.setText("");
                            } catch (Exception u) {
                                System.out.println(u);
                            }
                        }
                    }
                }
            }
        });
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (start <= stepRow) {
                        String attJoin = "SELECT e.eName, a.Work_ID, a.Hours_Worked, a.Over_Time FROM attendance a JOIN employee e ON e.WorkID = a.Work_ID;";
                        atteResult = st.executeQuery(attJoin);
                        if (atteResult.absolute(start++)) {
                            txtAttName.setText(atteResult.getString("eName"));
                            txtAttWorkID.setText(atteResult.getString("Work_ID"));
                        }
                    } else {
                        JOptionPane.showMessageDialog(Dash, "No more entries");
                    }
                } catch (Exception at){
                    System.out.println(at);
                }
            }
        });
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (start >= 1) {
                        String attJoin = "SELECT e.eName, a.Work_ID, a.Hours_Worked, a.Over_Time FROM attendance a JOIN employee e ON e.WorkID = a.Work_ID;";
                        atteResult = st.executeQuery(attJoin);
                        if (atteResult.absolute(--start)) {
                            txtAttName.setText(atteResult.getString("eName"));
                            txtAttWorkID.setText(atteResult.getString("Work_ID"));
                        }
                    } else {
                        JOptionPane.showMessageDialog(Dash, "You are at the beginning");
                    }
                } catch (Exception at){
                    System.out.println(at);
                }
            }
        });
        btnClearAtt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtHoursWorked.setText("");
                txtOvertime.setText("");
            }
        });
        btnEnterAtt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String updateQuery = "UPDATE attendance SET HOURS_WORKED='"+txtHoursWorked.getText()+"', OVER_TIME='"+txtOvertime.getText()+"' WHERE Work_ID='"+txtAttWorkID.getText()+"';";
                    int rows = st.executeUpdate(updateQuery);

                } catch (Exception uatt){
                    System.out.println(uatt);
                }
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtEName.setText("");
                txtAddress.setText("");
                txtIDNo.setText("");
                txtWorkID.setText("");
                txtPhone.setText("");
                cmbDept.setSelectedIndex(1);
                cmbGender.setSelectedIndex(1);
                txtEmail.setText("");
            }
        });

        btnCrtUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String addUser = "";
                String addUpdate = "INSERT INTO employee VALUES('" + txtEName.getText() + "', '" + txtWorkID.getText() + "', '" + txtAddress.getText() + "', '" + cmbDept.getSelectedItem() + "', '" + txtPhone.getText() + "', '" + txtEmail.getText() + "', '"+txtIDNo.getText()+"', '" + cmbGender.getSelectedItem() + "');";


                String message = "New Employee:\n* Name: " + txtEName.getText() +
                        "\n* WorkID" + txtWorkID.getText() + "\nUser Type";

                int choice = JOptionPane.showOptionDialog(Dash, message, "Payroll", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, new String[]{"Admin", "Normal"}, "Normal");

                if (choice == 1){
                    addUser = "INSERT INTO Login VALUES('"+txtWorkID.getText()+"', '"+txtEName.getText()+"', 'normal');";
                } else if (choice == 0) {
                    addUser = "INSERT INTO Login VALUES('"+txtWorkID.getText()+"', '"+txtEName.getText()+"', 'admin');";
                }

                try{
                    if (!addUser.equals("")) {
                        st.executeUpdate(addUser);
                        st.executeUpdate(addUpdate);
                    }
                } catch(Exception adU){
                    System.out.println(adU);
                }
            }
        });
        payRollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPane, "Payroll");
            }
        });
    }

    private void createUIComponents() {
        icon = new JLabel(new ImageIcon("user.png"));
    }
}
