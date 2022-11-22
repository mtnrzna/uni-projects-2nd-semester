package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Manage<fori> {
    protected ArrayList<Book> books ;
    protected Scanner scanner = new Scanner(System.in);

    public Manage(){
        this.books = new ArrayList<Book>();
        menu();
    }

    private void menu() {
        System.out.println("What do you want ?");
        System.out.println("book add");
        System.out.println("book delete <id>");
        System.out.println("book loan <id>");
        System.out.println("book return <id>");
        System.out.println("book search");
        String input = scanner.nextLine();
        selectItem(input);
    }

    private void selectItem(String input) {
        String inputs[] = input.split(" ");
        if(inputs[0].equals("book")){
            if (inputs[1].equals("add")){
                bookAdd();
            }
            else if (inputs[1].equals("delete")){
                bookDelete(inputs[2]);
            }
            else if (inputs[1].equals("loan")){
                bookLoan(inputs[2]);
            }
            else if (inputs[1].equals("return")){
                bookReturn(inputs[2]);
            }
            else if (inputs[1].equals("search")){
                bookSearch();
            }
        }
        else {
            System.out.println("Invalid");
        }
        menu();
    }

    private void bookSearch() {
        System.out.println("--name <name>");
        System.out.println("--id <id>");
        System.out.println("--all");
        String input = scanner.nextLine();
        selectSearchItem(input);
    }

    private void selectSearchItem(String input) {
        String inputs[] = input.split(" ");
        if (inputs[0].equals("--name")){
            bookSearchName(inputs[1]);
        }
        else if (inputs[0].equals("--id")){
            bookSearchId(inputs[1]);
        }
        else if (inputs[0].equals("--all")){
            bookSearchAll();
        }
        else {
            System.out.println("Invalid");
        }
        menu();
    }

    private void bookSearchAll() {
        int n = books.size();
        int indexes[] = new int[n];
        for (int i = 0; i < n ; i++) {
            indexes[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (books.get(indexes[i]).getYear() < books.get(indexes[j]).getYear()){
                    int t = indexes[i];
                    indexes[i] = indexes[j];
                    indexes[j] = t;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            books.get(indexes[i]).details();
        }

    }

    private void bookSearchId(String id) {
        int index = findId(id);
        if (index != -1) {
            books.get(index).details();
        }
        else {
            System.out.println("Not Found !!");
        }
    }

    private void bookSearchName(String name) {
        int index = findName(name);
        if (index != -1) {
            books.get(index).details();
        }
        else {
            System.out.println("Not Found !!");
        }
    }

    private void bookReturn(String id) {
        int index = findId(id);
        if(index != -1 && books.get(index).getStatus() == "loan") {
            books.get(index).setStatus("free");
            int size = books.get(index).getLoans().size();
            books.get(index).getLoans().get(size - 1).setReturnDate(new Date());
            System.out.println("Done !!");
        }
        else {
            System.out.println("You can not return it !!");
        }
    }

    private void bookLoan(String id) {
        int index = findId(id);
        if(index != -1 && books.get(index).getStatus() == "free") {
            Loan loan = new Loan();
            System.out.println("Enter your name :");
            loan.setBorrower(scanner.nextLine());
            books.get(index).getLoans().add(loan);
            books.get(index).setStatus("loan");
            System.out.println("Done !!");
        }
        else {
            System.out.println("You can not borrow it !!");
        }
    }

    private void bookDelete(String id) {
        int index = findId(id);
        if (index != -1) {
            books.remove(index);
            System.out.println("Done !");
        }
        else
        {
            System.out.println("Not Found !!");
        }
    }

    private void bookAdd() {
        Book book = new Book();
        System.out.println("Enter name :");
        book.setName(scanner.nextLine());
        System.out.println("Enter author name :");
        book.setAuthorName(scanner.nextLine());
        System.out.println("Enter id :");
        book.setId(scanner.nextLine());
        System.out.println("Enter year :");
        book.setYear(scanner.nextInt());

        books.add(book);
        System.out.println("Done !!");

    }

    public int findId(String id){
        System.out.println(id);
        for (int i = 0; i < this.books.size(); i++) {
            if (books.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public int findName(String name){
        for (int i = 0; i < this.books.size(); i++) {
            if (books.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
}
