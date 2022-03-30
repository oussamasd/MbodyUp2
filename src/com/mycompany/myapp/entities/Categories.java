/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author user
 */
public class Categories {
    private int id;
    private String nom;
     private String type;
    private String description;

    public Categories() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categories(String nom, String type, String description) {
        this.nom = nom;
        this.type = type;
        this.description = description;
    }
   

    public Categories(int id, String nom, String type, String description) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.description = description;
    }

    
    
}
