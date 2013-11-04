package com.company;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 10/31/13
 * Time: 8:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class Contact implements Serializable{
    private String name, email, phone;

    public Contact(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
        ContactList contactList = new ContactList();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
