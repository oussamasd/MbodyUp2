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
public class Category {
     private int id;

     private String nom_Cat;

    public Category() {
    }

    public Category(int id, String nom_Cat) {
        this.id = id;
        this.nom_Cat = nom_Cat;
    }

    public Category(String nom_Cat) {
        this.nom_Cat = nom_Cat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_Cat() {
        return nom_Cat;
    }

    public void setNom_Cat(String nom_Cat) {
        this.nom_Cat = nom_Cat;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", nom_Cat=" + nom_Cat + '}';
    }
    

    
}
