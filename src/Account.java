
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Account {
    String username, password, name;
    int id;
    String role;
    public Account(String username, String password, String name, int id, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.id = id;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
   
     public static void Register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("***              REGISTER ACCOUNT              ****");
        System.out.println("");
        System.out.println("Enter your username: ");
        String userName = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        System.out.println("Enter your Name: ");
        String name = sc.nextLine();
        int idSave = (int) (Math.random() * (90000 - 1000 + 1) + 1000);
        boolean found = false;
        Account account = new Account(userName, password, name, idSave, "reader");
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("ReaderAccount.txt", true));
            Scanner read = new Scanner(new FileInputStream("ReaderAccount.txt"));
            while (read.hasNextLine()) {
                String check = read.nextLine();
                if (check.contains(userName)) {
                    System.out.println("This account name is existed ! Choose other username");
                    Register();
                    found = true;
                    break;
                } else {
                    found = false;
                }
            }
            if (found == false) {
                ps.println("");
                ps.println(account.getUsername() + "_" + account.getPassword() + "_" + account.getName() + "_" + account.getId()+ "_" + account.getRole());
                System.out.println("Register successfully !");
                ps.close();
                read.close();
            }
        } catch (Exception e) {
            System.out.println("registration failed !");
        }
    }
     
     public static void login() {
        System.out.println("***              LOGIN  ACCOUNT           ****");
        boolean found = false;
        boolean adfound = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String userName = sc.nextLine();
        System.out.println("Enter your password:  ");
        String password = sc.nextLine();
        try {

            Scanner enter = new Scanner(new FileInputStream("ReaderAccount.txt"));
 
            while (enter.hasNextLine()) {
                String read = enter.nextLine();
                String[] line = read.split("_");              
                if (line[0].equals(userName) && line[1].equals(password) && line[4].contains("reader")) {
                    System.out.println("Log in Successfully!");
                    System.out.println("Welcome reader " + line[2]);
                    System.out.println("id: " + line[3]);
                    found = true;
                    break;
                }
            }
            Scanner adEnter = new Scanner(new FileInputStream("ReaderAccount.txt"));
            while (adEnter.hasNextLine()) {
                String adRead = adEnter.nextLine();
                String[] adLine = adRead.split("_");                
                if (adLine[0].equals(userName) && adLine[1].equals(password) && adLine[4].contains("admin")) {
                    System.out.println("Log in Successfully!");
                    System.out.println("Welcome admin " + adLine[2]);
                    System.out.println("id: " + adLine[3]);
                    adfound = true;
                    break;
                }
            }
            if (found) {
                Menu.readerMenu();// menu danh cho reader   
            } else if (adfound) {
                Menu.adMenu(); //menu danh cho admin
            }
            else{
                if (!found && !adfound) {
            System.out.println("This account does not exist");
            Menu.menuHome();
        }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read accounts");
            Menu.menuHome();
        }
    }
    
      public static void addAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("***              ADD ACCOUNT              ****");
        System.out.println("");
        System.out.println("Add reader username: ");
        String userName = sc.nextLine();
        System.out.println("add reader  password: ");
        String password = sc.nextLine();
        System.out.println("Enter reader Name: ");
        String name = sc.nextLine();
        int idSave = (int) (Math.random() * (90000 - 1000 + 1) + 1000);
        boolean found = false;
        Account account = new Account(userName , password, name, idSave, "reader");
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("ReaderAccount.txt", true));
            Scanner read = new Scanner(new FileInputStream("ReaderAccount.txt"));
            while (read.hasNextLine()) {
                String check = read.nextLine();
                if (check.contains(userName)) {
                    System.out.println("This account name is existed ! Choose other username");
                    Account.Register();
                    found = true;
                    break;
                } else {
                    found = false;
                }
            }
            if (found == false) {
                ps.println(account.getUsername() + "_" + account.getPassword() + "_" + account.getName() + "_" + account.getId() + "_" + account.getRole());
                ps.println("");
                System.out.println("Add successfully !");
                ps.close();
                read.close();
            }
        } catch (Exception e) {
            System.out.println("Add failed because of system error");
        }
    }
      
      public static void deleteAccount() {
        System.out.println("***      DELETE   ACCOUNT       ***");
        System.out.println("");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of reader you want to delete: ");
        String id = sc.nextLine();
        boolean found = false;
        List<String> store = new ArrayList<>();
        try {
            Scanner read = new Scanner(new FileInputStream("ReaderAccount.txt"));
            while (read.hasNextLine()) {
                String data = read.nextLine();
                if (data.contains(id)) {
                    found = true;
                    while (read.hasNextLine()) {
                        data = read.nextLine();
                        if (data.isEmpty()) {
                            break;
                        }
                    }
                } else {
                    store.add(data);
                }
            }
            PrintStream ps = new PrintStream(new FileOutputStream("ReaderAccount.txt"));
            for (String save : store) {
                ps.println(save);            
            }
            if(found){
                System.out.println("Delete Successfully !");
            }
            else{
                System.out.println("id not found !");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

       public static void DisplayAccountOfReader(){
    System.out.println("*** DISPLAY ACCOUNT AND INFORMATION OF READER ***\n");

try (Scanner sc = new Scanner(new FileInputStream("ReaderAccount.txt"))) {
        while (sc.hasNextLine()) {
            String save = sc.nextLine().trim(); // Loại bỏ khoảng trắng ở đầu và cuối dòng
            if (!save.isEmpty()) { // Kiểm tra xem dòng có rỗng không
                String[] arraySave = save.split("_");
                if (arraySave.length >= 4) {
                    System.out.println("Username: " + arraySave[0]);
                    System.out.println("Password: " + arraySave[1]);
                    System.out.println("Reader name: " + arraySave[2]);
                    System.out.println("id: " + arraySave[3]);
                    System.out.println();
                } else {
                    System.out.println("Error: Invalid data format in the file.");                  
                }
            }
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
     
                 public static void readerInformation(){
              Scanner sc =new Scanner(System.in);
              System.out.println("****        INFORMATON OF READER      ****");
              System.out.println("");
              System.out.println("Enter your reader name: ");
              String name = sc.nextLine();
              System.out.println("Enter your id: ");
              String id = sc.nextLine();
              boolean found = false;
              boolean find = false;
              boolean see = false;
              try{
              Scanner check = new Scanner(new FileInputStream("ReaderAccount.txt"));
              while(check.hasNextLine()){
                  String store = check.nextLine().trim();
                  String[] line = store.split("_");
                  if(line.length >= 4 && id.equals(line[3]) && name.equals(line[2])){
                        found = true;
                        break;
                  }               
              }
              if(found == true){
                  System.out.println("Verified successfully !");
                  System.out.println("");
              }
              else{
                  System.out.println("The information entered is incorrect !");
              }
              check.close();
              Scanner sive = new Scanner(new FileInputStream("ReaderInformation.txt"));
              System.out.println("Information of reader: ");
              System.out.println("");
              while(sive.hasNextLine()){
                  String save = sive.nextLine();
                  String [] sove = save.split("_");
                  if(sove[0].equals(name) && sove[1].equals(id)){
                       find = true;                    
                       while(sive.hasNextLine()){
                           save = sive.nextLine();
                           if(save.isEmpty()){
                               break;
                           }                         
                           System.out.println(save);
                       }
                       break;
                  }
              }
              sive.close();
              }
              catch(Exception e){
                  System.out.println("Error !" + e.getMessage());
              }
          }
       
          public static void registerInformation(){
              Scanner sc = new Scanner(System.in);
              System.out.println("****             REGISTER READER INFORMATION           ****");
              System.out.println("");
                System.out.println("Enter your reader name: ");
                String name = sc.nextLine();
                System.out.println("Enter your id: ");
                String id = sc.nextLine();
                System.out.println("Enter your full name: ");
                String fullName = sc.nextLine();
                System.out.println("Enter your birth: ");
                String birth = sc.nextLine();
                System.out.println("Enter your citizen identification: ");
                String number = sc.nextLine();
                System.out.println("Enter your phone number: ");
                int phone = sc.nextInt();
                System.out.println("Registered successfully !");
                try{
                    PrintStream ps = new PrintStream(new FileOutputStream("ReaderInformation.txt"));
                    ps.println("");
                    ps.println(name + "_" + id);
                    ps.println("Name: " + fullName);
                    ps.println("Date of birth: " + birth);
                    ps.println("Role: reader");
                    ps.println("Citizen identification: " + number);
                    ps.println("Phone number: " + phone);
                    ps.close();
                }
                catch(Exception e){
                    System.out.println("Error!");
                }
          }       
                 
}
