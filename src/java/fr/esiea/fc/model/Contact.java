/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esiea.fc.model;

import com.itc.fc4.model.Address;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contact {

    private String lastName;
    private String firstName;
    private String email;
    private Date dateNaissance;
    private boolean actif;
    private List <Address> listAdress = new ArrayList<Address>();

    public Contact (){

    }

    public Contact (String lastName, String firstName, String email, Date dateNaissance, boolean actif){
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.actif = actif;

    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress (String number, String street, String postalCode, String city){
        listAdress.add(new Address(number, street, postalCode, city));
    }

    public void setAddress (Address newAddress){
        listAdress.add(newAddress);
    }

    public Address getAdress (int position){

        return listAdress.get(position);
    }

    public List getAllAdress (){

        return listAdress;
    }

}
