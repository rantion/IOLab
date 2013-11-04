package com.company;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static ContactList contactList = new ContactList();
    private static boolean isRunning = true;

    private static PerformAction[] actions = {
            new Quit(),
            new PrintContacts(),
            new ViewSpecificContact(),
            new CreateNewContact()
    } ;


    public static void main(String[] args) {
        ContactList contacts = deserializeList();
        contactList = contacts;
        contacts.printContacts();
        System.out.println("Welcome to the contact manager!");
        do
        {
        promptAction();
        }
        while (isRunning);

    }

    public static void promptForAction() {
        for(int i=0; i < actions.length; i++)
            System.out.println("" +i+") "+ actions[i].getPrompt());

    }

    private static void promptAction() {
        int menuChoice = -1;
        do {
            try {//promptUser for the action
                promptForAction();
                //get the action number from the user
                menuChoice = getActionNumber();
                // execute the specified action
                if (isValidActionNumber(menuChoice)) {
                    executeAction(menuChoice);
                }
            } catch (InputMismatchException e) {
                scan.nextLine();
                System.out.println("Please enter a number between 0 and "+ (actions.length-1));
            }
        }
        while (!isValidActionNumber(menuChoice));
    }

    private static boolean isValidActionNumber(int actionNumber) {
     return (actionNumber > -1 && actionNumber < actions.length);
    }


    private static int getActionNumber() {
        int actionNumber = scan.nextInt();
        scan.nextLine();
        return actionNumber;

    }



    private static void executeAction(int menuChoice) {
        PerformAction action = actions[menuChoice];
           action.execute_Action();
    }

    private static void viewSpecificContact() {
        System.out.println("Please enter the number of the contact you would like to view: ");
        int contactNum = scan.nextInt();
        scan.nextLine();
        contactList.viewContact(contactNum);
    }


    private static void createNewContact() {
        System.out.println("Name: ");
        String newName = scan.nextLine();
        System.out.println("Email: ");
        String newEmail = scan.nextLine();
        System.out.println("Phone Number: ");
        String newPhone = scan.nextLine();
        Contact contact = new Contact(newName, newEmail, newPhone);
        contactList.addContact(contact);
        contactList.printContacts();

    }

    private static void saveList(){
        System.out.println("SaveList");
        try{
        FileOutputStream fileOut = new FileOutputStream("/Users/Rachel/Documents/IOfile.docx");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(contactList);
            out.close();
            System.out.printf("Serialized data is saved.");
        }
        catch(IOException i)
        {
        i.printStackTrace();
        }
    }

    private static ContactList deserializeList(){
        ContactList contactList1 = null;
        try{
            FileInputStream fileIn = new FileInputStream("/Users/Rachel/Documents/IOfile.docx");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            contactList1 = (ContactList) in.readObject();
            in.close();
            fileIn.close();

        }
        catch(IOException e){
          e.printStackTrace();
        }
        catch(ClassNotFoundException c){
            System.out.println("Contact List not found");
            c.printStackTrace();
        }
        return contactList1;
    }

    public static class Quit extends PerformAction{

        public Quit(){
            super("Quit");
        }
        public void execute_Action(){isRunning = false; saveList();}
    }

public static class PrintContacts extends PerformAction{
    public PrintContacts(){
        super("View all contacts");
    }
            public void execute_Action(){contactList.printContacts();}
        }

    public static class ViewSpecificContact extends PerformAction{

        public ViewSpecificContact(){
            super("View specific contact");
        }
        public void execute_Action(){viewSpecificContact();}
    }

    public static class CreateNewContact extends PerformAction{

        public CreateNewContact(){
            super("Create new contact");
        }
        public void execute_Action(){createNewContact();}
    }

}