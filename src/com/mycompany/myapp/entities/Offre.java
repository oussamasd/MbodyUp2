/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Objects;
//import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author User
 */
public class Offre {
    private Abonnement nom_abonnement;
    private int id; 
    private String nom; 
    private String categorie; 
    private Date delai;

    public Offre(Abonnement nom_abonnement, int id, String nom, String categorie, Date delai) {
        this.nom_abonnement = nom_abonnement;
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.delai = delai;
    }

    public Offre(int id, String nom, String categorie, Date delai) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.delai = delai;
    }

    public Offre() {
        
    }
  
    
    public Offre(String valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Offre(String toString, String toString0, String format, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return "Offre{" + "nom_abonnement=" + nom_abonnement + ", id=" + id + ", nom=" + nom + ", categorie=" + categorie + ", delai=" + delai + '}';
    }
    

    public Abonnement getNom_abonnement() {
        return nom_abonnement;
    }

    public void setNom_abonnement(Abonnement nom_abonnement) {
        this.nom_abonnement = nom_abonnement;
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

    public Date getDelai() {
        return delai;
    }

    public void setDelai(Date delai) {
        this.delai = delai;
    }

    public void setNom_abonnement(String anull) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getNom(String anull) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getCategorie(String anull) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getDelai(String anull) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}

