package GP;

import java.sql.*;
import java.util.Scanner;

public class MovieManager extends Theater {
    Scanner sc = new Scanner(System.in);



    void addMovie() throws SQLException {
        sc.nextLine();  // Consume newline
        System.out.print(BLUE + String.format("%60s", "")+"Enter Movie Name : " + RESET);
        String mName = sc.nextLine();
        int price = 0;
        boolean flag1 = false;
        while (flag1==false){
            System.out.print(BLUE+String.format("%60s", "")+"Enter Movie Price : "+YELLOW);
            try {
                price=sc.nextInt();
                flag1 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag1==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }


        String sql = "insert into movie_name(movie_name, price) values(?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, mName);
        pst.setInt(2, price);
        int rowsAffected = pst.executeUpdate();  // Use executeUpdate for insert operations
        if (rowsAffected > 0) {
            System.out.println(GREEN + String.format("%60s", "")+"Movie added Successfully" + RESET);
        } else {
            System.out.println(RED + String.format("%60s", "")+"Failed To Add!!!" + RESET);
        }
    }

    void removeMovie() throws SQLException {
        String sql = "select * from movie_name";
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(BLUE + String.format("%60s", "")+"Press " + rs.getInt(1) + " for Delete " + rs.getString(2) + RESET);
        }
        int id = 0;
        Boolean flag2 = false;
        while (flag2==false){
            System.out.print(BLUE+String.format("%60s", "")+" Enter Your Choice: " + RESET);
            try {
                id = sc.nextInt();
                flag2 = true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(flag2==false){
                System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
            }
            sc.nextLine();
        }

        String sql1 = "delete from movie_name where index_no = ?";
        PreparedStatement pst = con.prepareStatement(sql1);
        pst.setInt(1, id);
        int rowsAffected = pst.executeUpdate();  // Use executeUpdate for delete operations
        if (rowsAffected > 0) {
            System.out.println(GREEN + String.format("%60s", "")+"Movie Removed Successfully" + RESET);
        } else {
            System.out.println(RED + String.format("%60s", "")+"Failed To Remove!!!" + RESET);
        }
    }

    void callMovieManager() throws SQLException {
        int choice = 0;
        do {
            System.out.println(BLUE + String.format("%60s", "")+"Press 1 for add Movie" + RESET);
            System.out.println(BLUE + String.format("%60s", "")+"Press 2 for remove Movie" + RESET);
            System.out.println(BLUE + String.format("%60s", "")+"Press 3 for Exit" + RESET);
            boolean flag3 = false;

            while (flag3==false){
                System.out.print(BLUE+String.format("%60s", "")+" Enter Your Choice: " + RESET);
                try {
                    choice = sc.nextInt();
                    flag3 = true;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                if(flag3==false){
                    System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
                }
            }

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    removeMovie();
                    break;
                case 3:
                    System.out.println(GREEN + String.format("%60s", "")+"Thank You!!!" + RESET);
                    break;
                default:
                    System.out.println(RED + String.format("%60s", "")+"Enter Valid Choice!!!!" + RESET);
            }
        } while (choice != 3);
    }
}