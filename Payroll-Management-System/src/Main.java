import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // The System is always running unless the "exit" is fed to the system.
        System.out.println("Welcome to Payroll Management");
        Scanner input = new Scanner(System.in);
        String loginUser = input.next();

        if (loginUser.equals("Malvin")){
            System.out.println("Payroll: PLEASE SELECT");
            System.out.println("1. Employee Details\n2. Payroll");

            String exec = input.next();

            while (!exec.equals("exit")){
                if (exec.equals("1")){
                    System.out.println("you selected 1");
                    exec = input.next();
                } else {
                    System.out.println("Invalid entry\n");

                    System.out.println("Payroll: PLEASE SELECT");
                    System.out.println("1. Employee Details\n2. Payroll");
                    exec = input.next();
                }
            }

        }
    }
}