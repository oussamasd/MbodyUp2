/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.entities.Abonnement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author User
 */
public class ServiceAbonnement {
    public ArrayList<Abonnement> abonnements;
    
    public static ServiceAbonnement instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAbonnement() {
         req = new ConnectionRequest();
    }

    public static ServiceAbonnement getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnement();
        }
        return instance;
    }
      public boolean ajouterAbonnement(Abonnement t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "create";
    
       req.setUrl(url);
       req.setPost(false);
       req.addArgument("nom", t.getNom());
   
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<Abonnement> parseAbonnements(String jsonText){
   
        try {
            abonnements =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> exercicesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)exercicesListJson.get("root");
            for(Map<String,Object> obj : list){
                Abonnement t = new Abonnement();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                if (obj.get("nom")==null)
              t.setNom("null");
                else
                    t.setNom(obj.get("nom").toString());
                
                if (obj.get("categories")==null)
              t.setCategorie("null");
                else
                    t.setCategorie(obj.get("categories").toString());
                
                if (obj.get("Description")==null)
              t.setDescription("null");
                else
                    t.setDescription(obj.get("Description").toString());
                
                 float prix = Float.parseFloat(obj.get("prix").toString());
                t.setPrix((int)prix);
                abonnements.add(t);
                
         
            }
            
            
        } catch (IOException ex) {
            
        }
        return abonnements;
    }
    public ArrayList<Abonnement> getAllAbonnement(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"get/";
        System.out.println("===>"+url);
        req.setUrl("http://127.0.0.1:8000/abonnement/mobile/json/abonnement");
        
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                abonnements = parseAbonnements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return abonnements;
    }

    public ArrayList<Abonnement> parseAbonnements() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public boolean  Delete(int id){
       String url = Statics.BASE_URL + "{id}/" +id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
       NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}