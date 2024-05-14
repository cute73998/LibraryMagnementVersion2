import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookList {
   List<Book> books;

    public BookList() {
        this.books = new ArrayList<>();
    }
     
    public  void addBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("****         ADD   BOOK      ****");
        System.out.println("");
        System.out.println("Enter the name of book you want to add: ");
        String bookName = sc.nextLine();
        System.out.println("Enter the author name of book: ");
        String authorName = sc.nextLine();
        System.out.println("Enter the year the book was published: ");
        String year = sc.nextLine();
        System.out.println("Succesfully !");
        Book book = new Book(bookName , authorName, year);
        books.add(book);       
        try {
            PrintStream write = new PrintStream(new FileOutputStream("Book.txt", true));
            for(Book save : books){
                write.println("");
                write.println(save.toString());
            }          
            write.close();
        } catch (Exception e) {
            System.out.println("Add failed!");
        }
    }
  
       public  void removeBook() {
        System.out.println("***     REMOVE   BOOK     ***");
        System.out.println("");
        List<String> store = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of book you want to remove: ");
        String book = sc.nextLine();
        boolean found = false;
        try {
            Scanner read = new Scanner(new FileInputStream("Book.txt"));
            while (read.hasNextLine()) {
                String search = read.nextLine();
                if (search.startsWith("Book: ") && search.contains(book)) {
                    found = true;
                    while (read.hasNextLine()) {
                        search = read.nextLine();
                        if (search.isEmpty()) {
                            break;
                        }
                    }
                } else {
                    store.add(search);
                }
            }
            read.close();
            if (!found) {
                System.out.println("The book " + book + " is not found.");
                return;
            }
            PrintStream prin = new PrintStream(new FileOutputStream("Book.txt"));
            for (String line : store) {
                prin.println(line);
            }
            System.out.println("Remove book successfully!");
            prin.close();
        } catch (Exception e) {
            System.out.println("Failed to remove the book.");
        }
    }                     
}
