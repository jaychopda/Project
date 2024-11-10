package GP;

import java.sql.*;
import java.util.Scanner;

class FoodManager extends Theater {
    Scanner sc = new Scanner(System.in);

    // ANSI escape codes for colors
    private static final String RESET = "\033[0m";  // Reset to default color
    private static final String GREEN = "\033[0;32m";  // Green
    private static final String RED = "\033[0;31m";  // Red
    private static final String BLUE = "\033[0;34m";  // Blue
    private static final String CYAN = "\033[0;36m";  // Cyan

    void addFood() throws SQLException {
        sc.nextLine();  // Consume newline
        System.out.println(BLUE + String.format("%60s", "")+"Enter new food name:" + RESET);
        String fname = sc.nextLine();

        boolean flag2 = false;
        int qun=0;
        while (flag2==false){
            System.out.print(BLUE+String.format("%50s", "")+" Enter quantity: " + RESET);
            try {
                qun = sc.nextInt();
                flag2 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag2==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }

        boolean flag3 = false;
        int price=0;
        while (flag3==false){
            System.out.print(BLUE+String.format("%50s", "")+" Enter quantity: " + RESET);
            try {
                price = sc.nextInt();
                flag3 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag3==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }

        System.out.println();

        String query = "insert into food (food_name, price, quantity) values(?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, fname);
        pst.setInt(2, price);
        pst.setInt(3, qun);
        int r = pst.executeUpdate();
        if (r > 0) {
            System.out.println(GREEN + String.format("%60s", "")+"New Item Added Successfully" + RESET);
        } else {
            System.out.println(RED + String.format("%60s", "")+"New Item Insertion Failed" + RESET);
        }
    }

    void removeFood() throws SQLException {
        sc.nextLine();  // Consume newline
        System.out.print(BLUE + String.format("%60s", "")+"Enter food name to remove: " + RESET);
        String fname1 = sc.nextLine();
        String q1 = "delete from food where food_name = ?";
        PreparedStatement pst1 = con.prepareStatement(q1);
        pst1.setString(1, fname1);
        int r = pst1.executeUpdate();  // Use executeUpdate for delete operations
        if (r > 0) {
            System.out.println(GREEN + String.format("%60s", "")+"Food Removed Successfully" + RESET);
        } else {
            System.out.println(RED + String.format("%60s", "")+"Failed To Remove Food" + RESET);
        }
    }

    void updateQuantity() throws SQLException {
        String sql = "select * from food";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println(BLUE + String.format("%60s", "")+"Press " + rs.getInt(1) + " for Update " + rs.getString(2) + " Existing Quantity = " + rs.getInt(4) + RESET);
        }
        boolean flag4 = false;
        int choice=0;
        while (flag4==false){
            System.out.print(BLUE+String.format("%50s", "")+" Enter Your Choice: " + RESET);
            try {
                choice = sc.nextInt();
                flag4 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag4==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }

        boolean flag5 = false;
        int quantity=0;
        while (flag5==false){
            System.out.print(BLUE+String.format("%50s", "")+" Enter Quantity you Want to add: " + RESET);
            try {
                quantity= sc.nextInt();
                flag5 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag5==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }

        String sql1 = "update food set quantity = quantity + ? where id = ?";
        PreparedStatement pst = con.prepareStatement(sql1);
        pst.setInt(1, quantity);
        pst.setInt(2, choice);
        int r = pst.executeUpdate();  // Use executeUpdate for update operations
        if (r > 0) {
            System.out.println(GREEN + String.format("%60s", "")+"Food Quantity Updated Successfully" + RESET);
        } else {
            System.out.println(RED + String.format("%60s", "")+"Failed To Update Quantity!!!" + RESET);
        }
    }

    public void callFoodManager() throws Exception {
        int ch = 0;
        do {
            System.out.println(BLUE + String.format("%60s", "")+"Press 1 to ADD NEW ITEM" + RESET);
            System.out.println(BLUE + String.format("%60s", "")+"Press 2 to DELETE ITEM" + RESET);
            System.out.println(BLUE + String.format("%60s", "")+"Press 3 to UPDATE QUANTITY" + RESET);
            System.out.println(BLUE + String.format("%60s", "")+"Press 4 TO EXIT" + RESET);
            boolean flag6 = false;

            while (flag6==false){
                System.out.print(BLUE+String.format("%50s", "")+" Enter Your Choice: " + RESET);
                try {
                    ch = sc.nextInt();
                    flag6 = true;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                if(flag6==false){
                    System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
                }
            }

            switch (ch) {
                case 1:
                    addFood();
                    break;
                case 2:
                    removeFood();
                    break;
                case 3:
                    updateQuantity();
                    break;
                case 4:
                    System.out.println(GREEN + String.format("%60s", "")+"Thank You For Visiting the Food Section" + RESET);
                    break;
                default:
                    System.out.println(RED + String.format("%60s", "")+"Invalid choice" + RESET);
            }
        } while (ch != 4);
    }
}