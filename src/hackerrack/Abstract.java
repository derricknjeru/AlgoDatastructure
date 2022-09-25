package hackerrack;

import java.util.*;

abstract class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    abstract void display();
}

// Declare your class here. Do not use the 'public' access modifier.
// Declare the price instance variable

/**
 * Class Constructor
 *
 * @param title The book's title.
 * @param author The book's author.
 * @param price The book's price.
 **/
// Write your constructor here

/**
 * Method Name: display
 * <p>
 * Print the title, author, and price in the specified format.
 **/
// Write your method here

// End class

class MyBook extends Book {
    int myPrice;

    public MyBook(String title, String author, int price) {
        super(title, author);
        myPrice = price;
    }

    @Override
    void display() {
        System.out.printf("Title: %s", title);
        System.out.printf("\nAuthor: %s", author);
        System.out.printf("\nPrice: %d", myPrice);
    }
}

public class Abstract {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        String author = scanner.nextLine();
        int price = scanner.nextInt();
        scanner.close();

        Book book = new MyBook(title, author, price);
        book.display();
    }


}
