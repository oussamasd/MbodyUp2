/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author User
 */
public class Abonnement {
    private int id; 
    private String nom; 
    private String categorie; 
    private String description;
    private float  prix; 
    private String photo;

    public Abonnement(int id, String nom, String categorie, String description, float prix, String photo) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
    }

    public Abonnement(String nom, String categorie, String description, float prix, String photo) {
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
    }

    public Abonnement() {
       
    }

    public Abonnement(String valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Abonnement(String toString, String toString0, String format, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", nom=" + nom + ", categorie=" + categorie + ", description=" + description + ", prix=" + prix + ", photo=" + photo + '}';
    }
   
   
}
