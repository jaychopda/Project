package GP;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;
import java.sql.*;

public class Theater {
        public static final String BLACK = "\033[30m";
        public static final String RED = "\033[31m";
        public static final String GREEN = "\033[32m";
        public static final String YELLOW = "\033[33m";
        public static final String BLUE = "\033[34m";
        public static final String MAGENTA = "\033[35m";
        public static final String WHITE = "\033[37m";
        public static final String RESET = "\033[0m";
        public static final String GRAY = "\033[90m";

        public static final String BRIGHT_RED = "\033[91m";
        public static final String BRIGHT_GREEN = "\033[92m";
        public static final String BRIGHT_YELLOW = "\033[93m";

        public static final String BG_BLACK = "\033[40m";
        public static final String BG_RED = "\033[41m";
        public static final String BG_GREEN = "\033[42m";
        public static final String BG_YELLOW = "\033[43m";
        public static final String BG_BLUE = "\033[44m";
        public static final String BG_MAGENTA = "\033[45m";
        public static final String BG_WHITE = "\033[47m";
        public static final String BG_GRAY = "\033[100m";

        public static final String BG_BRIGHT_RED = "\033[101m";
        public static final String BG_BRIGHT_GREEN = "\033[102m";
        public static final String BG_BRIGHT_YELLOW = "\033[103m";

        static Scanner sc=new Scanner(System.in);
        static String url = "jdbc:mysql://localhost:3306/ticket_master";
        static String username = "root";
        static String password = "";
        static PreparedStatement pst;
        static Statement st;
        static Connection con;
        static ResultSet rs;
        static boolean done=true;
        static String name;
        static EmployeeManager em = new EmployeeManager();
        static FoodManager fm=new FoodManager();
        static MovieManager mm=new MovieManager();
        static MovieBooking mb = new MovieBooking();


        public static void lasChoice() throws Exception{
            Scanner sc = new Scanner(System.in);

            System.out.println("\033[H\033[2J");
            System.out.println(YELLOW+String.format("%20s","")+"---------------"+String.format("%10s","")+"---------------"+String.format("%10s","")+"-----------");
            System.out.println(String.format("%20s","")+"|"+RED+"  1. SING UP "+YELLOW+"|"+String.format("%10s","")+"|"+RED+"   2. LOGIN  "+YELLOW+"|"+String.format("%10s","")+"|"+RED+" 3. EXIT "+YELLOW+"|");
            System.out.println(String.format("%20s","")+"---------------"+String.format("%10s","")+"---------------"+String.format("%10s","")+"-----------");
            System.out.println();
            System.out.print(RED+String.format("%60s","")+"Enter your choice(1/2/3)-->"+YELLOW);
            String choice = sc.nextLine();
            System.out.println();
            if(choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("3") || choice.equalsIgnoreCase("4")){
                switch (choice) {
                    case "1":
                        String pNumber="";
                        boolean flag1 = false;
                             while (flag1==false){
                                 System.out.print(BLUE+"\n"+String.format("%50s", "")+"Enter your username --> "+YELLOW);
                                 try {
                                     name=sc.nextLine();
                                 }catch (Exception e){
                                     System.out.println(e.getMessage());
                                 }
                                 char c[] = new char[name.length()];
                                 for(int j=0; j<c.length; j++){
                                     c[j] = name.charAt(j);
                                 }
                                 for(int i=0; i<c.length; i++){
                                     if((c[i]>='a'&&c[i]<='z')  || (c[i]>='A'&&c[i]<='Z') || (c[i]-48>=0 && c[i]-48<=9)){
                                         flag1 = true;
                                     }else {
                                         flag1 = false;
                                         break;
                                     }
                                 }
                                 if(flag1==false){
                                     System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Character"+String.format("%60s", "")+RESET);
                                 }
                             }
                        System.out.print(String.format("%60s", "")+"Enter the password-->");
                        String pass=sc.nextLine();

                             boolean flag2 = false;
                             while (flag2==false){
                                 System.out.print(BLUE+String.format("%50s", "")+"Enter your mobile number --> "+YELLOW);
                                 try {
                                     pNumber=sc.nextLine();
                                 }catch (Exception e){
                                     System.out.println(e.getMessage());
                                 }
                                 Integer c[] = new Integer[pNumber.length()];
                                 for(int j=0; j<c.length; j++){
                                     c[j] = (int)(pNumber.charAt(j)-48);
                                 }
                                 if(pNumber.length()==10){
                                     for(int i=0; i<c.length; i++){
                                         if(c[i]>=0&&c[i]<=9){
                                             flag2 = true;
                                         }
                                         else {
                                             flag2 = false;
                                             break;
                                         }
                                     }
                                     if(flag2==false){
                                         System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only Digit"+String.format("%60s", "")+RESET);
                                     }
                                 }else {
                                     System.out.println(BG_RED+String.format("%60s", "")+"Please Enter Only 10 Digit"+String.format("%60s", "")+RESET);

                                 }
                             }
                        addInDataBase(name,pass,pNumber);
                        System.out.println(BG_GREEN+WHITE+String.format("%60s", "")+"Sign up is done"+String.format("%60s", "")+RESET);
                        break;

                    case "2":
                        System.out.print(String.format("%60s", "")+"Enter the username-->");
                        name=sc.nextLine();
                        System.out.print(String.format("%60s", "")+"Enter the password-->");
                        pass=sc.nextLine();
                        login(name, pass);
                        System.out.println(BG_GREEN+WHITE+String.format("%60s", "")+"Login is done"+String.format("%60s", "")+RESET);
                        menu(name,pass);
                        break;
                    case "3":
                        System.out.println("\033[H\033[2J");
                         exit();
                        break;

                    default:
                        System.out.println(BG_RED+String.format("%60s", "")+"YOUR CHOICE IS INVALID PLEASE RE-ENTER"+String.format("%60s", "")+RESET);
                        try {
                            Thread.sleep(3000);
                        } catch (Exception e) {

                        }
                        break;
                }
            }
            else{
                System.out.println(BG_RED+String.format("%60s", "")+"invalid choice please chose(1/2/3/4)  wait a second"+String.format("%60s", "")+RESET);
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                lasChoice();
            }
            if(choice!="3"){
                lasChoice();
            }
        }

        public static synchronized void banner(){
            String hLine="|";
            String line="-";
            int a=32;

            for(int i=0;i<=33;i++){
                System.out.print("\033[3"+((i%7)+1)+"m");
                System.out.print(String.format("%50s",""));
                for (int j = 0; j < a; j++) {
                    System.out.print(" ");
                }
                System.out.print("\033[4"+((i%7)+1)+"m "+line+"\033[0m");
                line+="--";
                a--;
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                System.out.print("\033[H\033[2J");
            }
            System.out.print("\033[H\033[2J");
            a=65;
            line="-";
            for(int i=0;i<=33;i++){
                System.out.print("\033[3"+((i%7)+1)+"m");
                System.out.print(String.format("%59s","")+"\033[4"+((i%7)+1)+"m"+" ------------------------------------------------------------------ "+"\033[0m");
                System.out.println("\n"+String.format("%59s", "")+"\033[4"+((i%7)+1)+"m"+hLine+"\033[0m"+String.format("%25s", "")+"Theater Managment"+String.format("%24s", "")+"\033[4"+((i%7)+1)+"m"+hLine+"\033[0m");
                System.out.print(String.format("%59s",""));
                System.out.print("\033[4"+((i%7)+1)+"m "+line+"\033[0m");
                for (int j = 0; j < a; j++) {
                    System.out.print(" ");
                }
                System.out.print("\033[4"+((i%7)+1)+"m"+line+"\033[0m");
                line+="-";
                a-=2;
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                System.out.println();
                System.out.print("\033[H\033[2J");
            }

            for (int i = 0; i < 10; i++) {
                System.out.print(String.format("%59s","")+"\033[4"+((i%7)+1)+"m"+" ----------------------------------------------------------------- "+"\033[0m");
                System.out.println("\n"+String.format("%59s", "")+"\033[4"+((i%7)+1)+"m"+hLine+String.format("%25s", "")+"Theater Managment"+String.format("%24s", "")+hLine+"\033[0m");
                System.out.print(String.format("%59s","")+"\033[4"+((i%7)+1)+"m"+" ----------------------------------------------------------------- "+"\033[0m");
                System.out.println();
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                System.out.print("\033[H\033[2J");
            }
            System.out.print(String.format("%59s","")+"\033[33m"+" ------------------------------------------------------------------ ");
            System.out.println("\n"+String.format("%59s", "")+hLine+String.format("%25s", "")+"Theater Managment"+String.format("%24s", "")+hLine);
            System.out.println(String.format("%59s","")+" ------------------------------------------------------------------ "+"\033[0m");
            System.out.println();
        }


        public static void menu(String name,String pass) throws Exception{
            con = DriverManager.getConnection(url, username, password);
            String sql="select * from manager";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           while(rs.next())
           {
               if(name.equals("m9876")&&pass.equals("mahesh@9876")) {
                   if (rs.getString(4).equals("m9876") && rs.getString(3).equals("mahesh@9876")) {
                       em.callEmployeeManager();
                       break;
                   }
               }
               else if(name.equals("r9345")&&pass.equals("ramesh@9345")) {
                   if (rs.getString(4).equals("r9345") && rs.getString(3).equals("ramesh@9345")) {
                       fm.callFoodManager();
                       break;
                   }
               }

               else if(name.equals("s9123")&&pass.equals("suresh@9123")) {
                   if (rs.getString(4).equals("s9123") && rs.getString(3).equals("suresh@9123")) {
                       mm.callMovieManager();
                       break;
                   }
               }
               else
               {
                   mb.user();
               }
           }
        }

        public static void login(String name,String pass)throws Exception{
            con = DriverManager.getConnection(url, username, password);
            String sql="select * from manager";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            Boolean flag = false;
            while (rs.next()) {
                if(rs.getString(4).equals(name) && rs.getString(3).equals(pass)){
                    flag = true;
                }
            }

            String sql1 = "select * from user";
            st = con.createStatement();
            ResultSet rs1 = st.executeQuery(sql1);
            while (rs1.next()) {
                if(rs1.getString(2).equals(name) && rs1.getString(3).equals(pass)){
                    flag = true;
                }
            }

            if(!flag){
                System.out.println(BG_RED+String.format("%60s", "")+"User name or password not found or invalid"+String.format("%60s", "")+RESET);
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                   //  TODO: handle exception
                }
                lasChoice();
            }

        }


        public static void addInDataBase(String name,String pass,String number) throws Exception{
            con = DriverManager.getConnection(url, username, password);
            String sql="INSERT INTO user (user_name , password , phone_number , date_and_time) VALUES (?,?,?,?)";
            String sql2="select * from user";
            PreparedStatement pst2=con.prepareStatement(sql2);
            rs=pst2.executeQuery();
            while(rs.next()){
                if(rs.getString("user_name").equals(name) || rs.getString("phone_number").equals(number)){
                    done=false;
                    break;
                }
            }
            if(done){
                pst=con.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, pass);
                pst.setString(3, number);
                pst.setString(4, dateAndTime());
                pst.executeUpdate();
            }
            else{
                System.out.println(BG_RED+String.format("%60s", "")+"User name or phone number alredy registred"+String.format("%60s", "")+RESET);
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                done=true;
                lasChoice();
            }

        }


        public static String dateAndTime(){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedNow = now.format(formatter);
            return formattedNow;
        }

        public static void exit(){
            for (int i = 0; i < 1; i++) {
                System.out.print(String.format("%59s","")+"\033[4"+((i%7)+1)+"m"+" ------------------------------------------------------------ "+"\033[0m");
                System.out.println("\n"+String.format("%59s", "")+"\033[4"+((i%7)+1)+"m"+"|"+String.format("%25s", "")+" THANK YOU "+String.format("%24s", "")+"|"+"\033[0m");
                System.out.print(String.format("%59s","")+"\033[4"+((i%7)+1)+"m"+" ------------------------------------------------------------ "+"\033[0m");
                System.out.println();

                System.out.println();
                try {
                    Thread.sleep(200);
                } catch (Exception e) {

                }
                System.out.print("\033[H\033[2J");
            }
            System.exit(0);
        }
        public static void main(String[] args) throws Exception {
            banner();
            lasChoice();
        }
    }