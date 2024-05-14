
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    
    public static void information() {
        try {
            Scanner sc = new Scanner(new FileInputStream("BookInformation.txt"));
            while (sc.hasNextLine()) {
                String read = sc.nextLine();
                System.out.println(read);
            }
        } catch (Exception e) {
            System.out.println("Display error !");
        }
    }
    
    public static void exit(){
        System.out.println("***        EXIT SUCCESSFULLY            ***");
        System.out.println("              Goodbye readers                  ");
        System.out.println("Wish you have a nice day! Thank you for using FPT Enemy's software.");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        try{
            Scanner sc = new Scanner(new FileInputStream("CatEmoji.txt"));
            while(sc.hasNextLine()){
                String HoChiMinh = sc.nextLine();
                System.out.println(HoChiMinh);
            }
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public static void displayAdBook() {
        System.out.println("***       DISPLAY BOOK INFORMATION     ***");
        System.out.println("");
        try {
            Scanner sc = new Scanner(new FileInputStream("book.txt"));
            while (sc.hasNextLine()) {
                String read = sc.nextLine();
                System.out.println(read);
            }
        } catch (Exception e) {
            System.out.println("Empty book !");
        }
    }
        
        public static void checkBorrowBook() {
    System.out.println("***       CHECK BORROW BOOK        ***");
    System.out.println("");
    System.out.println("Enter the name of the book you want to check: ");
    Scanner sc = new Scanner(System.in);
    String book = sc.nextLine();
    boolean found = false;
    List<String> store = new ArrayList<>();
    try {
        File file = new File("BookBorrow.txt");
        if (!file.exists()) {
            System.out.println("Error: BookBorrow.txt does not exist.");
            return;
        }
        Scanner read = new Scanner(file);
        while (read.hasNextLine()) {
            String save = read.nextLine().trim();
            if (save.contains(book)) {
                found = true;
                break; // Exit the loop when the book is found
            }
        }
        read.close();
        if (found) {
            System.out.println("This book has been borrowed by reader !");
        } else {
            System.out.println("The book is not currently borrowed.");
        }
    } catch (Exception e) {
        System.out.println("Books do not exist!");
    }
}
    
           public static void displayBook(){
        System.out.println("***        DISPLAY BOOKS IN LIBRARY         ***");
        System.out.println("");
        boolean found =false;
        try{
            Scanner sc = new Scanner(new FileInputStream("Book.txt"));
            while(sc.hasNextLine()){
                 String save = sc.nextLine();
                 if(save.startsWith("Book:")){
                     System.out.println(save);
                 }
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    } 
  
     public static void bookInformation(){
    System.out.println("***          BOOK INFORMATION              ***");
    System.out.println("");
    System.out.println("Enter the name of book you want to find information: ");
    Scanner sc = new Scanner(System.in);
    String book = sc.nextLine();
    boolean found = false;
    try{
        Scanner check = new Scanner(new FileInputStream("Book.txt"));
        while(check.hasNextLine()){
            String store = check.nextLine();
            if(store.startsWith("Book: ") && store.contains(book)){
                found = true;
                System.out.println(store);
                while(check.hasNextLine()){
                    store = check.nextLine();
                    if(store.isEmpty()){
                        break;
                    }
                    System.out.println(store);
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found !");
        }
    }
    catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    }
     
         public static void borrowBook() {
        System.out.println("*** BORROW BOOK ***\n");

        System.out.println("Enter the name of the book you want to borrow: ");
        Scanner sc = new Scanner(System.in);
        String bookName = sc.nextLine();
        boolean found = false;
        boolean borrowFound = false;
        try {
            Scanner read = new Scanner(new FileInputStream("Book.txt"));
            Scanner check = new Scanner(new FileInputStream("BookBorrow.txt"));
            PrintWriter write = new PrintWriter(new FileOutputStream("BookBorrow.txt", true));
            while (read.hasNextLine()) {
                String save = read.nextLine();
                boolean bookExistsInLibrary = save.contains(bookName);

                if (bookExistsInLibrary) {
                    found = true;

                    boolean bookAlreadyBorrowed = false;
                    while (check.hasNextLine()) {
                        String store = check.nextLine();
                        if (store.contains(bookName)) {
                            bookAlreadyBorrowed = true;
                            break;
                        }
                    }

                    if (bookAlreadyBorrowed) {
                        borrowFound = true;
                        System.out.println("This book has been borrowed by another user.");
                    } else {
                        write.println(bookName);
                        System.out.println("Borrowed books successfully");
                    }
                    break; // Stop searching once the book is found
                }
            }
            read.close();
            check.close();
            write.close();

            if (!found) {
                System.out.println("This book does not exist in the library");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    } 
     
                public static void giveBookBack() {
    System.out.println("***       GIVE BOOK BACK        ***");
    System.out.println("");
    System.out.println("Enter the name of the book you want to give back: ");
    Scanner sc = new Scanner(System.in);
    String book = sc.nextLine();
    boolean found = false;
    List<String> store = new ArrayList<>();
    try {
        File file = new File("BookBorrow.txt");
        if (!file.exists()) {
            System.out.println("Error: BookBorrow.txt does not exist.");
            return;
        }

        Scanner read = new Scanner(file);
        while (read.hasNextLine()) {
            String save = read.nextLine().trim();
            if (save.contains(book)) {
                found = true;
                break; // Exit the loop when the book is found
            }
            else{
                store.add(save);
            }
        }
        read.close();

        PrintStream ps = new PrintStream(new FileOutputStream("BookBorrow.txt"));
        for (String extant : store) {
            ps.println(extant);
        }
        ps.close();

        if (found) {
            System.out.println("Returned the book successfully!");
        } else {
            System.out.println("The book is not currently borrowed.");
        }
        ps.close();
    } catch (FileNotFoundException e) {
        System.out.println("Error: " + e.getMessage());
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
