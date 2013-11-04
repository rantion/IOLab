package com.company;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 10/31/13
 * Time: 8:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContactList implements Serializable{
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    public ContactList(){

    }

    public void addContact(Contact contact){
       contacts.add(contact);
    }

    public void printContacts(){
        int index = 0;
        for(Contact c: contacts){
            System.out.println(index+ " - "+ c.getName());
            index ++;
        }
    }

    public void viewContact(int index){
        Contact specificContact = contacts.get(index);
        printContact(specificContact);

    }

    public void printContact(Contact contactToPrint){
        System.out.println("\nName: "+contactToPrint.getName()
                +"\nEmail: "+contactToPrint.getEmail()
                +"\nPhone: "+contactToPrint.getPhone()+"\n");


    }

}

