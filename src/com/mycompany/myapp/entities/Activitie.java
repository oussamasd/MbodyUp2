/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


import java.util.Collection;
import java.util.Date;
//import java.time.LocalTime;

/**
 *
 * @author oussa
 */
public class Activitie {
    private int id;

    
    private String nom_Act;

   
    private String date_Act;

   
    private String  temp_act;

   
    private String description_Act;

    
    private Category category;

   
    private Collection <Exercice> exercices;
    
    
    private Collection <String> images;
    
    private int nbr_comnt;

    public Activitie() {
    }

    public Activitie(String nom_Act, String date_Act, String temp_act, String description_Act, Category category, Collection<Exercice> exercices , Collection<String> images) {
        this.nom_Act = nom_Act;
        this.date_Act = date_Act;
        this.temp_act = temp_act;
        this.description_Act = description_Act;
        this.category = category;
        this.exercices = exercices;
        this.images = images;
    }

    public Activitie(int id, String nom_Act, String date_Act, String temp_act, String description_Act, Category category, Collection<Exercice> exercices) {
        this.id = id;
        this.nom_Act = nom_Act;
        this.date_Act = date_Act;
        this.temp_act = temp_act;
        this.description_Act = description_Act;
        this.category = category;
        this.exercices = exercices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_Act() {
        return nom_Act;
    }

    public void setNom_Act(String nom_Act) {
        this.nom_Act = nom_Act;
    }

    public String getDate_Act() {
        return date_Act;
    }

    public void setDate_Act(String date_Act) {
        this.date_Act = date_Act;
    }

    public String getTemp_act() {
        return temp_act;
    }

    public void setTemp_act(String temp_act) {
        this.temp_act = temp_act;
    }

    public String getDescription_Act() {
        return description_Act;
    }

    public void setDescription_Act(String description_Act) {
        this.description_Act = description_Act;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Collection<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(Collection<Exercice> exercices) {
        this.exercices = exercices;
    }

    public Collection<String> getImages() {
        return images;
    }

    public void setImages(Collection<String> images) {
        this.images = images;
    }

    public int getNbr_comnt() {
        return nbr_comnt;
    }

    public void setNbr_comnt(int nbr_comnt) {
        this.nbr_comnt = nbr_comnt;
    }

    @Override
    public String toString() {
        return "Activitie{" + "id=" + id + ", nom_Act=" + nom_Act + ", date_Act=" + date_Act + ", temp_act=" + temp_act + ", description_Act=" + description_Act + ", category=" + category + ", exercices=" + exercices + ", images=" + images + ", nbr_comnt=" + nbr_comnt + '}';
    }
    

   
    
    
    
}
