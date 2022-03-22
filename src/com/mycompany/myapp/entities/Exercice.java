/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author oussa
 */
public class Exercice {
   
    private int id;

   
    private String nom_Exercice;

    
    private String dure_Exercice;

   
    private String description_Exercice;

    
    private Category category;

    public Exercice() {
    }

    public Exercice(int id, String nom_Exercice, String dure_Exercice, String description_Exercice, Category category) {
        this.id = id;
        this.nom_Exercice = nom_Exercice;
        this.dure_Exercice = dure_Exercice;
        this.description_Exercice = description_Exercice;
        this.category = category;
    }

    public Exercice(String nom_Exercice, String dure_Exercice, String description_Exercice, Category category) {
        this.nom_Exercice = nom_Exercice;
        this.dure_Exercice = dure_Exercice;
        this.description_Exercice = description_Exercice;
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    
    public int getId() {
        return id;
    }

    public String getNom_Exercice() {
        return nom_Exercice;
    }

    public void setNom_Exercice(String nom_Exercice) {
        this.nom_Exercice = nom_Exercice;
    }

    public String getDure_Exercice() {
        return dure_Exercice;
    }

    public void setDure_Exercice(String dure_Exercice) {
        this.dure_Exercice = dure_Exercice;
    }

    public String getDescription_Exercice() {
        return description_Exercice;
    }

    public void setDescription_Exercice(String description_Exercice) {
        this.description_Exercice = description_Exercice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Exercice{" + "id=" + id + ", nom_Exercice=" + nom_Exercice + ", dure_Exercice=" + dure_Exercice + ", description_Exercice=" + description_Exercice + ", category=" + category + '}';
    }
    

    
    

    
}
