/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esiea.fc.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is a singleton. It create the contact List used in all the program.
 *
 */

 public final class ContactsList {

     private static volatile ContactsList instance = null;

     private Collection <Contact> contactsList = new ArrayList<Contact>();

     private ContactsList() {
         super();
     }

     public final static ContactsList getInstance() {

         if (ContactsList.instance == null) {


            synchronized(ContactsList.class) {
              if (ContactsList.instance == null) {
            	  ContactsList.instance = new ContactsList();
              }
            }
         }
         return ContactsList.instance;
     }

     public void addContact (Contact contact){
    	 contactsList.add(contact);
     }

     public int size (){
	return contactsList.size();

     }

     /**
      *
      * This method get back the contact at the position "position" in the contact list
      *
      */

public Contact getContact (int position){

    	 int count = 0;

    	 for (Contact contact : contactsList){
    		 if (count == position)
    			 return contact;

    		 count++;
    	 }

		return null;

      }

/**
 *
 * This method delete a contact of the contact list
 *
 */

	public void deleteContact (Contact contact){
		 contactsList.remove(contact);
	}

 }