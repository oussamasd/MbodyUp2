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
public class TypeReclamation {
    private int id;
    private String nom;

    public TypeReclamation() {
    }

    public TypeReclamation(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public TypeReclamation(String nom) {
        this.nom = nom;
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
    
    
}
