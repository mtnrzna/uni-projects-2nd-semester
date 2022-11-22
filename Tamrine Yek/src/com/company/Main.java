package com.company;

import java.util.Scanner;
public class Main {
    public int showingMenu(){
        System.out.println("Please Choose Between Following Options: ");
        System.out.println("1-Entering New Data");
        System.out.println("2-Sort Data");
        System.out.println("3-Search");
        System.out.println("4-Exit");
        int choice;
        do {
            System.out.println("Your Choice is :");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
        } while (choice > 4 && choice < 1);
        return choice;
    }

    public static void main(String[] args) {
        Main x = new Main();
        // dafee aval e run shodan e barnaame, dataii voojood nadare pas
        // menuii ham tabiatan voojood nakhahad daasht
        // menu baad az vared kardan e avalin serie e adaad namayesh daade mishavad
        System.out.println("First Please Enter The Number Of Integers You Wanna Enter: ");
        int choice;
        int index;
        Scanner scanner = new Scanner(System.in);
        index = scanner.nextInt();
        System.out.println("Please Enter The Integers");
        int[] numbers = new int[index];
        x.input(numbers , index);

        do {
            choice = x.showingMenu();
            switch (choice){
                case 1:
                    System.out.println("Please Enter The Number Of Integers You Wanna Enter: ");
                    Scanner scanner0 = new Scanner(System.in);
                    index = scanner0.nextInt();
                    System.out.println("Please Enter The Integers: ");
                    x.input(numbers , index); //ejraaye taabe e masool e khoondan e adaad
                    break;

                case 2:
                    x.sortingAndOutput(numbers , index);
                    System.out.println("\n");
                    break;

                case 3:
                    System.out.println("Please Enter The Number You Wanna Find In The Array");
                    int wantToSearchThisNumber;
                    Scanner scanner1 = new Scanner(System.in);
                    wantToSearchThisNumber = scanner1.nextInt();
                    x.search(numbers , index , wantToSearchThisNumber); //ejraaye taabe e masool e sarch
                    break;

                default:
                    continue;
            }
        } while(choice != 4); // age 4 vared shod az barnaame khaarej mishe

    }



    public void input(int[] numbers , int index){
        for(int i = 0; i < index; i++){
            Scanner scanner = new Scanner(System.in);
            numbers[i] = scanner.nextInt();
        }
    }



    public void sortingAndOutput(int[] numbers , int index) {
        int temp;
        for (int i = 0; i < index - 1; i++) {
            for (int j = i + 1; j < index; j++) {
                if (numbers[i] > numbers[j]) {
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        System.out.println("Here Are The Integers From Smallest To Greatest: ");
        for(int i = 0; i < index; i++) {
            System.out.print(numbers[i] + "  ");
        }
    }



    public void search(int[] numbers , int index , int x) {
        boolean foundIt = false;
        for (int i = 0; i < index; i++) {
            if (numbers[i] == x)
                foundIt = true;
        }
        if (foundIt == true)
            System.out.println("Yup, Found it :)");
        else
            System.out.println("Nope, The Number You've Entered Isn't In The Array :/");

    }
}
