package GP;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.StreamSupport;

class MovieBooking extends Theater{
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
    public static final String BG_GREEN= "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_MAGENTA = "\033[45m";
    public static final String BG_WHITE = "\033[47m";
    public static final String BG_GRAY = "\033[100m";

    public static final String BG_BRIGHT_RED = "\033[101m";
    public static final String BG_BRIGHT_GREEN = "\033[102m";
    public static final String BG_BRIGHT_YELLOW = "\033[103m";


    static int movieChoice=999999;
    static int start;
    static int end;
    static  int food_bill=0;

    private static final int NUM_ROWS = 6;
    private static final int SEATS_PER_ROW = 11;
    public static boolean[][][] s= new boolean[25][NUM_ROWS][SEATS_PER_ROW];
    static Scanner sc=new Scanner(System.in);
    public static int[] a={5,4,5,3,4};
    public static String movie[]={"HANUMAN","MAIN ATAL HOON","SALAR","DUNKI","12th FAIL"};
    public static String phoneNumber;
    public static String userName;
    public static String seat="";
    public static String payment="";
    public static String status="";
    static String url = "jdbc:mysql://localhost:3306/ticket_master";
    static String username = "root";
    static String password = "";
    static int p;

    class Node{
        String mName;
        int mId;
        Node next;

        public Node(int mId,String mName) {
            this.mName = mName;
            this.mId = mId;
            next = null;
        }
    }
    static Node first = null;

    public void set() throws SQLException{
        Connection con1= DriverManager.getConnection(url, username, password);
        String sql = "select * from movie_name";
        PreparedStatement pst = con1.prepareStatement(sql);
        ResultSet rs= pst.executeQuery();
        MovieBooking mb = new MovieBooking();
        while (rs.next()) {
            mb.addLast(rs.getInt("index_no"),rs.getString("movie_name"));
        }
    }

    public void addLast(int mId,String mName){
        Node n=new Node(mId,mName);
        if(first==null){
            first=n;
        }else {
            Node temp = first;
            while (temp.next!=null){
                temp = temp.next;
            }
            temp.next = n;
        }
    }

    private static void displayFood ()throws Exception
    {
        int ch = 99999;
        boolean flagx = false;
        while (flagx==false)
        {
            st = con.createStatement();
            String sql = "select * from food";
            ResultSet rs1 = st.executeQuery(sql);
            while (rs1.next()){
                System.out.println("Press "+rs1.getInt(1)+". "+rs1.getString(2)+" Price: $"+rs1.getInt(3));
            }
            System.out.println("Press 0. For Exit");
            System.out.println(" choice food from above ");
            try {
                ch=sc.nextInt();

            }
            catch (Exception e){
                System.out.println("Enter Digit Only");
            }
            String sql2 = "select id from food";
            ResultSet rs2 = st.executeQuery(sql2);
            boolean flag = false;
            String s = "";
            while (flag==false) {
                while (rs2.next()) {
                    if (rs2.getInt(1) == ch) {
                        flag = true;
                        System.out.println("enter quantity");
                        int quantity = sc.nextInt();
                        Connection con2 = DriverManager.getConnection(url, username, password);
                        String s1 = "select quantity,price from food where id=? ";
                        PreparedStatement pst = con2.prepareStatement(s1);
                        pst.setInt(1, ch);
                        ResultSet rs = pst.executeQuery();
                        while (rs.next()) {
                            int q = rs.getInt("quantity");
                            int p = rs.getInt("price");
                            if (quantity < q) {
                                food_bill += food_bill + quantity * p;
                                String sql1 = "update food set quantity = quantity - "+quantity+" where id = "+ch;
                                st = con.createStatement();
                                st.executeUpdate(sql1);
                            } else {
                                System.out.println("no such quantity available");
                            }
                        }
                    }
                }
            }
            System.out.println("do you want order to something else Yes/no");
            s=sc.next();
            if(s.equalsIgnoreCase("no")){
                flagx = true;
            }
        }
        System.out.println("your bill= "+ food_bill);
    }

    private static void displayMovie() throws SQLException {
        MovieBooking mb = new MovieBooking();

        Node temp = mb.first;
        start = temp.mId;
        Node tempx = mb.first;
        while (tempx.next!=null){
            tempx = tempx.next;
        }
        end = tempx.mId;

        while (temp!=null){
            System.out.println( "  " + String.format("%55s", "" )+GREEN+"Press "+temp.mId+". For "+temp.mName+String.format("%18s","")+RESET);
            temp = temp.next;
        }
        System.out.println( "  " + String.format("%55s", "" )+GREEN+"Press 0. For Exit"+String.format("%18s",""));
        while(movieChoice>end || movieChoice<start) {
            System.out.print(GREEN+"  " + String.format("%55s", "" )+"Enter your choice of --> "+RESET);
            try {
                movieChoice = sc.nextInt();

                if(movieChoice<start || movieChoice>end){
                    displayMovie();
                }
                if(movieChoice==0)
                {
                    System.out.println( "  " + String.format("%45s", "" )+GREEN+"THANK YOU FOR VISITING OUR MOVIE BOOKING SYSTEM"+String.format("%18s",""));
                    lasChoice();
                }else {
                    if (movieChoice > end || movieChoice < start) {
                        System.out.println("\n" + BG_RED + "  " + String.format("%45s", "") + "Invalid choice. Please try again." + String.format("%55s", "") + RESET);
                        System.out.println(BG_RED + String.format("%60s", "") + "Invalid choice. Please try again." + String.format("%60s", "") + RESET);
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println(BG_RED+String.format("%60s", "")+"Invalid choice. Please try again."+String.format("%60s", "")+RESET);
            }
        }
        System.out.println(RESET);
        System.out.print("\033[H\033[2J");

            System.out.print("\033[H\033[2J");
            System.out.println();
            Node temp1 = mb.first;
            while (temp1.mId!=movieChoice){
                temp1 = temp1.next;
            }

            System.out.println( "  " + String.format("%55s", "" )+RED+"-------------------------------------"+RESET+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"|"+YELLOW+"            !! "+ temp1.mName+"!!          "+RESET+RED+"|"+String.format("%38s",""));
            System.out.println( "  " + String.format("%55s", "" )+RED+"-------------------------------------"+RESET+String.format("%38s",""));

    }


    private static void displayMenu() throws Exception{
        System.out.println(RESET);
        MovieBooking mb=new MovieBooking();
        mb.seat();
        System.out.println(GREEN+ "  " + String.format("%55s", "" )+"1. Display Seats");
        System.out.println( "  " + String.format("%55s", "" )+"2. Book Seat");
        System.out.println( "  " + String.format("%55s", "" )+"3. Cancel Booking");
        System.out.println( "  " + String.format("%55s", "" )+"4. View History Of Booking");
        System.out.println( "  " + String.format("%55s", "" )+"5. Exit");
        System.out.print( "  " + String.format("%55s", "" )+"Enter your choice: ");
        booking();
    }

    public static void seat() throws Exception{
        Node temp=first;
        Connection con= DriverManager.getConnection(url, username, password);
        String sql="select * from movie";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while (rs.next()) {
            int a=0;
            for (int i = 0; i < movie.length; i++) {
                if(movie[i].equalsIgnoreCase(rs.getString("movie_name"))){
                    a=i;
                    break;
                }
            }
            if(rs.getString("status").equalsIgnoreCase("booked")){
                String seat=rs.getString("seat_no");
                String seatN[]=seat.split(" ");
                for(int i=0;i<seatN.length;i++){
                    char se[]=seatN[i].toCharArray();
                    int row=(int)se[0]-48;
                    int col=(int)se[2]-48;
                    s[a][row][col]=true;
                }
            }
            else if(rs.getString("status").equalsIgnoreCase("cancled")){
                String seat=rs.getString("seat_no");
                String seatN[]=seat.split(" ");
                for(int i=0;i<seatN.length;i++){
                    char se[]=seatN[i].toCharArray();
                    int row=(int)se[0]-48;
                    int col=(int)se[2]-48;
                    s[a][row][col]=false;
                }
            }
        }
    }

    private static void booking() throws Exception{
        MovieBooking mb=new MovieBooking();

        while (true)
        {
            String choice = sc.next();
            switch (choice)
            {
                case "1":
                    displaySeats();
                    break;

                case "2":
                    mb.bookSeat();
                    break;

                case "3":
                    cancelBooking();
                    break;

                case "4":
                    showHistry();
                    break;

                case "5":
                    System.out.println( "  " + String.format("%55s", "" )+"Exiting program. Thank you!");
                    lasChoice();
                    break;

                default:{

                    System.out.println(BG_RED+ "  " + String.format("%45s", "" )+"Invalid choice. Please try again."+String.format("%55s", "")+RESET);
                    displayMenu();
                }
            }
            if(choice=="5")
            {
                break;
            }
        }
    }

    private static void displaySeats() throws Exception{
        System.out.print("\033[H\033[2J");
        System.out.println();
        System.out.println("  " + String.format("%55s", "" )+"------ Seating Chart ------"+RESET+String.format("%18s",""));
        for (int i = 0; i <= NUM_ROWS; i++) {
            System.out.println();
            System.out.print("  " + String.format("%55s", "" ));
            if(i>0 && i!=NUM_ROWS)
                System.out.print(YELLOW+i +"  "+RESET);
            else
                System.out.print(" ");
            for (int j = 0; j <= SEATS_PER_ROW; j++) {
                if(j==5 || j==7)
                {
                    System.out.print("  ");
                }
                if(i==0 && j!=0 && j != SEATS_PER_ROW)
                    System.out.print(YELLOW+" "+(j)+RESET+RESET);
                else if(j==0 && i==0)
                    System.out.print(" ");
                if (i>0 && j>0 && i<NUM_ROWS && j<SEATS_PER_ROW && s[movieChoice-1][i][j]) {
                    System.out.print(RED+"X "+RESET);
                } else if(i>0 && j>0 && i<NUM_ROWS &&  j<SEATS_PER_ROW){
                    System.out.print(GREEN+"O "+RESET);
                }
            }
            System.out.println();
        }
        displayMenu();
    }

    private static void bookSeat() throws Exception
    {
        Scanner sc=new Scanner(System.in);
        int z =0;

        boolean flag3 = false;
        while (flag3==false) {
            try {
                System.out.print(MAGENTA+String.format("%55s", "" )+"how many seats you want to book::->");
                z = sc.nextInt();
            } catch (NumberFormatException e) {
                System.out.println(BG_RED+String.format("%60s", "")+"Enter Digit Only"+String.format("%60s", "")+RESET);
            } catch (Exception x) {
                System.out.println("Exception"+x);
            }
            if(z>=1 && z<=60){
                flag3 = true;
                break;
            }else {
                flag3 = false;
            }
            sc.nextLine();
        }
        if(flag3==false){
            System.out.println(BG_RED+String.format("%60s", "")+"Enter Valid Number"+String.format("%60s", "")+RESET);
        }

        for(int i=1;i<=z;) {
            int row =0;

            boolean flag4 = false;
            while (flag4==false) {
                try {
                    System.out.print(MAGENTA+String.format("%55s", "" )+"Enter Row :->");
                    row = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(BG_RED+String.format("%60s", "")+"Enter Digit Only"+String.format("%60s", "")+RESET);

                } catch (Exception x) {
                    System.out.println("Exception"+x);
                }
                if(row>=1 && row<=6){
                    flag4 = true;
                    break;
                }else {
                    flag4 = false;
                }
                sc.nextLine();
            }
            if(flag4==false){
                System.out.println("Enter Valid Number");
            }


            int col =0;

            boolean flag5 = false;
            while (flag5==false) {
                try {
                    System.out.print(MAGENTA+String.format("%55s", "" )+"Enter Column :->");
                    col = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(BG_RED+String.format("%60s", "")+"Enter Digit Only"+String.format("%60s", "")+RESET);
                } catch (Exception x) {
                    System.out.println("Exception"+x);
                }
                if(col>=1 && col<=10){
                    flag5 = true;
                    break;
                }else {
                    flag5 = false;
                }
                sc.nextLine();
            }


            if (isValidSeat(row, col) && !s[movieChoice-1][row][col])
            {
                s[movieChoice-1][row][col] = true;
                i+=1;
                seat+=(row)+"-"+(col)+" ";

            } else
            {
                System.out.println(RED+"  " + String.format("%45s", "" )+"Invalid seat or seat already booked. Please try again."+ String.format("%38s", "" )+RESET);

            }
        }
        for(int i=0;true;i++)
        {
            System.out.println(BLUE+"" + String.format("%55s", "" )+"payment = $"+a[movieChoice-1]*z);
            p+=a[movieChoice-1]*z;
            System.out.println( "  " + String.format("%55s", "" )+GREEN+"1. ONLINE PAYMENT  2.CASH ON COUNTER 3. NET BENKING"+String.format("%18s",""));
            System.out.print( "  " + String.format("%55s", "" )+MAGENTA+"select option::->"+GREEN);
            System.out.print(YELLOW);
            int a=sc.nextInt();
            System.out.println(RESET);
            if(a==1)
            {
                System.out.println(GREEN+ String.format("%45s", "" )+"your payment received on online payment"+String.format("%40s","")+RESET);
                break;
            }
            else if(a==2)
            {
                System.out.println(GREEN+ String.format("%45s", "" )+"your payment received on cash counter"+String.format("%40s","")+RESET);
                break;
            }
            else if(a==3)
            {
                System.out.println(GREEN+ String.format("%45s", "" )+"your payment received on credit-card"+String.format("%40s","")+RESET);
                break;
            }
            else
            {
                System.out.println(BG_RED+"  " + String.format("%45s", "" )+"Invalid payment method. Please try again."+ String.format("%40s", "" )+RESET);
            }
        }

        System.out.println();
        System.out.println(BG_BRIGHT_GREEN+"  "+String.format("%55s", "")+"your seat is booked"+String.format("%68s", "")+RESET);
        status="Booked";
        payment=String.valueOf(p);
        database();
        displayMenu();
    }

    private static void cancelBooking() throws Exception
    {   seat="";
        System.out.print(RED+String.format("%55s", "" )+"how many seats you want to cancel::->"+YELLOW);
        int z=sc.nextInt();
        for(int i=1;i<=z;)
        {
            System.out.println();
            System.out.print(RED+String.format("%55s", "" )+"Enter row number -->"+YELLOW);
            int row = sc.nextInt();
            System.out.print(RED+String.format("%55s", "" )+"Enter seat number --> "+YELLOW);
            int col = sc.nextInt();
            if (isValidSeat(row, col) && s[movieChoice-1][row][col])
            {
                s[movieChoice-1][row][col] = false;
                seat+=row+"-"+col+" ";
                i+=1;
            }
            else
            {
                System.out.println(BG_RED+ String.format("%55s", "" )+"seat is not booked. Please try again."+ String.format("%55s", "" )+RESET);
            }
        }

        String mName = "";
        String sql1 = "select movie_name from movie_name where index_no = "+movieChoice;
        st = con.createStatement();
        ResultSet rs = st.executeQuery(sql1);
        while (rs.next()){
            mName = rs.getString(1);
        }

        String sql = "delete from movie where user_name = ? and movie_name = ? and seat_no = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,Theater.name);
        pst.setString(2,mName);
        pst.setString(3,seat);
        int r = pst.executeUpdate();
        status="cancled";
        payment="";
        displayMenu();
    }

    private static void showHistry() throws Exception
    {
        Connection con1= DriverManager.getConnection(url, username, password);
        String sql = "select * from movie";
        PreparedStatement pst = con1.prepareStatement(sql);
        ResultSet rs= pst.executeQuery();
        while (rs.next()) {
            if(rs.getString("user_name").equalsIgnoreCase(userName)){
                System.out.println(String.format("%55s", "")+rs.getString("movie_name")+" "+rs.getString("seat_no"));
            }
        }
        displayMenu();
    }

    private static boolean isValidSeat(int row, int seat)
    {
        return row >= 0 && row < NUM_ROWS && seat >= 0 && seat < SEATS_PER_ROW;
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

    public static void user() throws Exception{
        int ch = 0;
        MovieBooking mb = new MovieBooking();
        mb.set();
        do{
            System.out.println("press 1 to Book Ticket");
            System.out.println("press 2 to Order Food");
            System.out.println("press 3 to exit");
            System.out.println("enter your choice");
            try {
                ch=sc.nextInt();
            }catch (Exception e){
                System.out.println("Enter Digit Only!!!");
            }

            switch(ch)
            {
                case 1:
                    displayMovie();
                    displayMenu();
                    break;
                case 2:
                    displayFood();
                    break;
                case 3: System.out.println("EXITING .....");
                    lasChoice();
                    break;
                default:System.out.println("Invalid choice");
            }
            sc.nextLine();
        }while (ch!=3);
    }

    private static void database(){
        try {
            userName = Theater.name;

            String sql = "select phone_number from user where user_name = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                phoneNumber = rs.getString(1);
            }

            Connection con= DriverManager.getConnection(url, username, password);
            String sql1 = "INSERT INTO movie (user_name , phone_number , movie_name , seat_no , payment , status , date_time) VALUES (? , ? , ? , ? , ? , ? , ? )";
            PreparedStatement statement = con.prepareStatement(sql1);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            statement.setString(1, userName);
            statement.setString(2, phoneNumber);
            statement.setString(3, movie[movieChoice-1]);
            statement.setString(4, seat);
            statement.setString(5, payment);
            statement.setString(6, status);
            statement.setString(7, formattedDateTime);
            statement.executeUpdate();
            statement.close();
            con.close();
            System.out.println();
            System.out.println(RESET);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(BG_RED+String.format("%45s", "")+"Error: Duplicate entry or integrity constraint violation."+String.format("%50s", "") + RESET);
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(BG_RED+String.format("%45s", "")+"Error storing booking information in database."+String.format("%56s", "") + RESET);
            e.printStackTrace();
        }
    }
}
