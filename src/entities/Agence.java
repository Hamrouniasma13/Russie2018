/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class Agence {
    private int id; 
    private String nomAgence;
    private String adresseAgence; 
    private int ContactAgence; 
    private String chaine ; 
    private String nom_image; 

    public Agence(){
        this.id = 0;
        this.nomAgence = "";
        this.adresseAgence = "";
        this.ContactAgence = 0;
        this.chaine = "";
        this.nom_image = "";
    }
    public Agence(int id, String nomAgence, String adresseAgence, int ContactAgence, String chaine, String nom_image) {
        this.id = id;
        this.nomAgence = nomAgence;
        this.adresseAgence = adresseAgence;
        this.ContactAgence = ContactAgence;
        this.chaine = chaine;
        this.nom_image = nom_image;
    }

    public Agence(String nomAgence, String adresseAgence, int ContactAgence, String chaine, String nom_image) {
        this.nomAgence = nomAgence;
        this.adresseAgence = adresseAgence;
        this.ContactAgence = ContactAgence;
        this.chaine = chaine;
        this.nom_image = nom_image;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAgence() {
        return nomAgence;
    }

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public String getAdresseAgence() {
        return adresseAgence;
    }

    public void setAdresseAgence(String adresseAgence) {
        this.adresseAgence = adresseAgence;
    }

    public int getContactAgence() {
        return ContactAgence;
    }

    public void setContactAgence(int ContactAgence) {
        this.ContactAgence = ContactAgence;
    }

    public String getChaine() {
        return chaine;
    }

    public void setChaine(String chaine) {
        this.chaine = chaine;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    @Override
    public String toString() {
        return "Agence{" + "id=" + id + ", nomAgence=" + nomAgence + ", adresseAgence=" + adresseAgence + ", ContactAgence=" + ContactAgence + ", chaine=" + chaine + ", nom_image=" + nom_image + '}';
    }
    
    
    
    
    
    
    
    
}
