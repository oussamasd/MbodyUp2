/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entity;

public class Entreneur {

    private int id;
    private String nom;
    private String prenom;
    private String age;
    private String type;
    private Categorie cat;
    private String image;


  

    public Entreneur(int id, String nom, String prenom, String age, String type,Categorie cat,String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.type = type;
        this.cat = cat;
        this.image=image;
    }

    public Entreneur(String nom, String prenom, String age, String type,Categorie cat,String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.type = type;
        this.cat = cat;
        this.image = image;
    }
    
      public Categorie getCat() {
        return cat;
    }
      
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public Entreneur() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Entreneur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", type=" + type + '}';
    }

}
