// Book + Library

// Book:
// private String title; private String author; private boolean isBorrowed;  cделали
// Методы:
// borrow(): нельзя взять уже взятую сделали
// returnBack(): нельзя вернуть уже доступную хз или правильно
// геттеры для title/author, но не “сетить” всё подряд без причины без причины? не сделала вообще сетеры только гетеры

// Library:
// хранит несколько книг (массив/список) хз или правильно
// addBook(Book book) сделали
// findByTitle(String title) (простая линейная проверка)
// printAvailableBooks()




// void printBorrowedBooks() da
// Что делает: печатает только взятые книги (isBorrowed == true).

// Дополнительно: если таких книг нет, вывести "No borrowed books". da
// int countAvailableBooks() da
// Что делает: возвращает количество доступных книг (isBorrowed == false) среди реально добавленных (0..size-1).

// int countBooksByAuthor(String author) da no mozno s equals? 
// Что делает: возвращает количество книг определенного автора.

// void printAllBooksWithStatus() da
// Что делает: печатает все книги с их статусом:
// AVAILABLE
// BORROWED
// Пример строки:
// Treasure Island - Robert Louis Stevenson [AVAILABLE]


class Book{
    private String m_title;
    private String m_author;
    private boolean m_isBorrowed;
    

    // constructor(s):
    Book(String title, String author, boolean isBorrowed){
        m_title = title;
        m_author = author;
        this.m_isBorrowed = isBorrowed;
    }
    
    // setters and getters
    public void setIsBorrowed(boolean bor){ 
        m_isBorrowed = bor;
    }

    
    public boolean getIsBorrowed(){
        return m_isBorrowed;
    }
    
    public String getTitle(){
        return m_title;
    }
    
    public String getAuthor(){
        return m_author;
    }

    public void borrow(){

        if(getIsBorrowed() == true){
            System.out.println("You can't borrow this book");
        }else{
            System.out.println("You can borrow this book");
            setIsBorrowed(true);
        }
    }
    public void returnBack(){
        if(getIsBorrowed() == true){
            System.out.println("You can return this book");
            setIsBorrowed(false);
        }else{
            System.out.println("You can't return this book");
        }
    }
    @Override
    public String toString(){
        return getTitle() + " - " + getAuthor();
    }
}

class Library {
    
    private Book [] arr;
    private int size = 0;
    
    Library(int capacity) {
        arr = new Book[capacity]; // 10
    }

    public void addBook(Book b){
        if (size >= arr.length) {
            System.out.println("Libraty is full, cannot add: " + b.getTitle());
            return;
        }
       arr[size] = b;
       size ++;
    }

    public Book findByTitle(String t){
       for (int i = 0; i < size; ++i) {
           Book book = arr[i];
           if (book != null && book.getTitle().equals(t)) {
               return book;
           }
       }
       
       return null;
    }
    
    // !
    public void printAvailableBooks(){
        System.out.println("Available books: ");
        for(int i = 0; i < arr.length; i++){
            Book book = arr[i];
            if (book != null) {
                System.out.println(book);
            }
        }
    }

    public void printBorrowedBooks(){
        for(int i = 0; i < size; i++){
            Book book = arr[i];
            if (book != null && book.getIsBorrowed() == true) {
                System.out.println("Borrowed books: " + book.getTitle() + " - " + book.getAuthor());
            } else {
                System.out.println("No borrowed books.");
            }
        }
    }
    public int countAvailableBooks(){
        int count = 0;
        for(int i = 0; i < size; i++){
            Book book = arr[i];
            if(book != null && book.getIsBorrowed() == false){
                count +=1 ;
            }
        }
        System.out.println("Number of available books is: " + count);
        return count;
    }
    public int countBooksByAuthor(String isAuthor){
        int count = 0;
        for(int i = 0; i < size; i++){
            Book book = arr[i];
            // if(book != null && book.getAuthor().equalsIgnoreCase(author))
            if(book != null && book.getAuthor() == isAuthor ){
                count +=1 ;
            }
        }
        System.out.println(isAuthor + " has " + count + " available books.");
        return count;
    }
    public void printAllBooksWithStatus(){
        System.out.println("Available books with status: ");
        String availableOrNot;
        for(int i = 0; i < arr.length; i++){
            Book book = arr[i];
            if (book != null) {
                if(book.getIsBorrowed() == true){
                    availableOrNot = " [BORROWED]";
                }
                else {
                    availableOrNot = " [AVAILABLE]";
                }
                System.out.println( book.getTitle() + " - " + book.getAuthor() + availableOrNot);
            }
        }
    }
}
    
public class Main {
    public static void main(String []args){
        Book [] arr = new Book[5];
        arr[0] = new Book("Don Quixote" , "Miguel de Cervantes", false);
        arr[1] = new Book("Alice's Adventures in Wonderland" , "Lewis Carroll", false);
        arr[2] = new Book("The Adventures of Huckleberry Finn" , "Mark Twain", true);
        arr[3] = new Book("The Adventures of Tom Sawyer" , "Mark Twain", false);
        arr[4] = new Book("Treasure Island" , "Robert Louis Stevenson", false);
        
        Library library = new Library(10);
        for(int i = 0; i < arr.length; i++){
            library.addBook(arr[i]);
        }
        
        library.printAvailableBooks();
        
        Book b = library.findByTitle("Treasure Island");
        if (b != null) {
            b.borrow();
        }

        library.countAvailableBooks();
        library.countBooksByAuthor("Mark Twain");
        library.printAllBooksWithStatus();
    }
}