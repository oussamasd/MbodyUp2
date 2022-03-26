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
public class Commentaire {
    private int id ;
    private String iduser ;
    private int idActivity ;
    private String text;
    private String datecmnt;

    public Commentaire(int id, String iduser, int idActivity, String text, String datecmnt) {
        this.id = id;
        this.iduser = iduser;
        this.idActivity = idActivity;
        this.text = text;
        this.datecmnt = datecmnt;
    }

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public String getIduser() {
        return iduser;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public String getText() {
        return text;
    }

    public String getDatecmnt() {
        return datecmnt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDatecmnt(String datecmnt) {
        this.datecmnt = datecmnt;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", iduser=" + iduser + ", idActivity=" + idActivity + ", text=" + text + ", datecmnt=" + datecmnt + '}';
    }
    
    
    
}
