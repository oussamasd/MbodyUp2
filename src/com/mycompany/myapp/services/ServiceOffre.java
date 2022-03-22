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
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author User
 */
public class ServiceOffre {
     public ArrayList<Offre> offres;
    
    public static ServiceOffre instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceOffre() {
         req = new ConnectionRequest();
    }

    public static ServiceOffre getInstance() {
        if (instance == null) {
            instance = new ServiceOffre();
        }
        return instance;
    }
    public boolean ajouterOffre(Offre t) {
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
     public ArrayList<Offre> parseOffres(String jsonText){
   
        try {
            offres =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> exercicesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)exercicesListJson.get("root");
            for(Map<String,Object> obj : list){
                Offre t = new Offre();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                if (obj.get("Nom_abonnement")==null)
              t.setNom_abonnement("null");
                else
                    t.setNom_abonnement(obj.get("Nom_abonnement").toString());
                
                if (obj.get("nom")==null)
              t.setNom("null");
                else
                    t.setNom(obj.get("nom").toString());
                
                if (obj.get("Categorie")==null)
              t.setCategorie("null");
                else
                    t.setCategorie(obj.get("Categorie").toString());
                
              
              
                
                offres.add(t);
                
         
            }
            
            
        } catch (IOException ex) {
            
        }
        return offres;
    }
      public ArrayList<Offre> getAllOffre(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"get/";
        System.out.println("===>"+url);
        req.setUrl("http://127.0.0.1:8000/offre/mobile/json");
        
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offres = parseOffres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offres;
    }

    public ArrayList<Offre> parseOffres() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
