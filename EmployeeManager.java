package GP;

import java.sql.*;
import java.util.Scanner;

import static Project.CinemaManagement.CYAN;

public class EmployeeManager extends Theater {
    Scanner sc = new Scanner(System.in);
    String setCategory(int i) {
        switch (i) {
            case 1: return "Cleaning Staff";
            case 2: return "Box Office Staff";
            case 3: return "Screen Management Staff";
            default: return "Other";
        }
    }

    void addEmployee() throws SQLException {
        System.out.println(BLUE + "Enter Name : " + RESET);
        sc.nextLine();  // Consume newline
        String name = sc.nextLine();
        System.out.println(BLUE + String.format("%60s", "")+"Select category" + RESET);
        System.out.println(BLUE + String.format("%60s", "")+"Press 1 for Cleaning Staff" + RESET);
        System.out.println(BLUE + String.format("%60s", "")+"Press 2 for Box Office Staff" + RESET);
        System.out.println(BLUE + String.format("%60s", "")+"Press 3 for Screen Management Staff" + RESET);
        System.out.println(BLUE + String.format("%60s", "")+"Press 4 for Other" + RESET);

        int category = 0;
        boolean flag1 = false;

        while (flag1==false){
            System.out.print(BLUE+String.format("%60s", "")+" Enter Your Choice: " + RESET);
            try {
                category = sc.nextInt();
                flag1 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag1==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }
        double salary = 0;
        boolean flag2 = false;

        while (flag2==false){
            System.out.print(BLUE+String.format("%60s", "")+"Enter Salary : " + RESET);
            try {
                salary = sc.nextDouble();
                flag2 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag2==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }


        String sql = "insert into employee(eName, eCategory, eSalary) values(?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, name);
        pst.setString(2, setCategory(category));
        pst.setDouble(3, salary);
        boolean isAdded = pst.execute();
        if (!isAdded) {
            System.out.println(GREEN + String.format("%60s", "")+"Employee added Successfully" + RESET);
        } else {
            System.out.println(RED + String.format("%60s", "")+"Failed To Add!!!" + RESET);
        }
    }

    void removeEmployee() throws SQLException {
        int id = 0;
        boolean flag3 = false;

        while (flag3==false){
            System.out.print(BLUE+String.format("%60s", "")+"Enter Employee Id You Want to Remove : " + RESET);
            try {
                id = sc.nextInt();
                flag3 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag3==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }


        String sql = "delete from employee where empID = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        boolean isDeleted = pst.execute();
        if (!isDeleted) {
            System.out.println(GREEN + String.format("%60s", "")+"Employee Removed Successfully" + RESET);
        } else {
            System.out.println(RED + String.format("%60s", "")+"Failed To Delete!!!" + RESET);
        }
    }

    void updateEmployeeDetails() throws SQLException {
        int id = 0;
        boolean flag3 = false;

        while (flag3==false){
            System.out.print(BLUE+String.format("%60s", "")+"Enter Employee Id Update Details: " + RESET);
            try {
                id = sc.nextInt();
                flag3 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag3==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }
        sc.nextLine();  // Consume newline

        System.out.print(BLUE + String.format("%60s", "")+"Enter Updated Name : " + RESET);
        String name = sc.nextLine();

        System.out.println(BLUE + String.format("%60s", "")+"Select New Category" + RESET);
        System.out.println(BLUE + String.format("%60s", "")+"Press 1 for Cleaning Staff" + RESET);
        System.out.println(BLUE + String.format("%60s", "")+"Press 2 for Box Office Staff" + RESET);
        System.out.println(BLUE + String.format("%60s", "")+"Press 3 for Screen Management Staff" + RESET);
        System.out.println(BLUE + String.format("%60s", "")+"Press 4 for Other" + RESET);

        int category = 0;
        Boolean flag4 = false;

        while (flag4==false){
            System.out.print(BLUE+String.format("%60s", "")+"Enter Category : " + RESET);
            try {
                category = sc.nextInt();
                flag4 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag4==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }


        double salary =0;
        Boolean flag1 = false;

        while (flag1==false){
            System.out.print(BLUE+String.format("%60s", "")+"Enter Updated Salary : " + RESET);
            try {
                category = sc.nextInt();
                flag1 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag1==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }


        String sql = "update employee set eName = ?, eCategory = ?, eSalary = ? where empID = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, name);
        pst.setString(2, setCategory(category));
        pst.setDouble(3, salary);
        pst.setInt(4, id);
        boolean isUpdated = pst.execute();
        if (!isUpdated) {
            System.out.println(GREEN + String.format("%60s", "")+"Employee Details Updated Successfully" + RESET);
        } else {
            System.out.println(RED + String.format("%60s", "")+"Failed To Update Employee Details!!!" + RESET);
        }
    }

    void displayEmployeeDetailsByID() throws SQLException {
        int id = 0;
        Boolean flag4 = false;

        while (flag4==false){
            System.out.print(BLUE+String.format("%60s", "")+"Enter Employee id To Get Details : " + RESET);
            try {
                id = sc.nextInt();
                flag4 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag4==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }
        String sql = "select * from employee where empID = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println(CYAN + String.format("%60s", "")+"" + RESET);
            System.out.println(CYAN + String.format("%60s", "")+"Details Of empID = " + id + RESET);
            System.out.println(CYAN + String.format("%60s", "")+"Name : " + rs.getString(2) + RESET);
            System.out.println(CYAN + String.format("%60s", "")+"Category : " + rs.getString(3) + RESET);
            System.out.println(CYAN + String.format("%60s", "")+"Salary : " + rs.getDouble(4) + RESET);
            System.out.println(CYAN + String.format("%60s", "")+"" + RESET);
        }
    }

    void callEmployeeManager() throws SQLException {
        int choice = 0;
        do {
            System.out.println(BLUE + String.format("%60s", "")+"Press 1 for add Employee" + RESET);
            System.out.println(BLUE + String.format("%60s", "")+"Press 2 for remove Employee" + RESET);
            System.out.println(BLUE + String.format("%60s", "")+"Press 3 for Update Employee Details" + RESET);
            System.out.println(BLUE + String.format("%60s", "")+"Press 4 for Display Employee Details By ID" + RESET);
            System.out.println(BLUE + String.format("%60s", "")+"Press 5 for Exit" + RESET);


            Boolean flag4 = false;

            while (flag4==false){
                System.out.print(BLUE+String.format("%60s", "")+"Enter Your Choice : " + RESET);
                try {
                    choice = sc.nextInt();
                    flag4 = true;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                if(flag4==false){
                    System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
                }
            }

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    removeEmployee();
                    break;
                case 3:
                    updateEmployeeDetails();
                    break;
                case 4:
                    displayEmployeeDetailsByID();
                    break;
                case 5:
                    System.out.println(GREEN +  String.format("%60s", "")+"Thank You" + RESET);
                    break;
                default:
                    System.out.println(RED + String.format("%60s", "")+"Enter Valid Choice!!!" + RESET);
            }
        } while (choice != 5);
    }
}