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
import com.mycompany.myapp.entities.Activitie;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.entities.Exercice;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author oussa
 */
public class ServiceActivitie {
      public ArrayList<Activitie> activities;
    
    public static ServiceActivitie instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceActivitie() {
         req = new ConnectionRequest();
    }

    public static ServiceActivitie getInstance() {
        if (instance == null) {
            instance = new ServiceActivitie();
        }
        return instance;
    }
   
      public ArrayList<Activitie> parseActivity(String jsonText){
        try {
            System.out.println("yimchiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            activities=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> activitiesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)activitiesListJson.get("root");
            for(Map<String,Object> obj : list){
                Activitie t = new Activitie();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                if (obj.get("nom_Act")==null)
              t.setNom_Act("null");
                else
                    t.setNom_Act(obj.get("nom_Act").toString());
                
                if (obj.get("date_Act")==null)
              t.setDate_Act("null");
                else
                    t.setDate_Act(obj.get("date_Act").toString());
                
                if (obj.get("temp_act")==null)
              t.setTemp_act("null");
                else
                    t.setTemp_act(obj.get("temp_act").toString());
                  if (obj.get("description_Act")==null)
              t.setDescription_Act("null");
                else
                    t.setDescription_Act(obj.get("description_Act").toString());
               
                Category c = new Category();
                float id2 = Float.parseFloat(obj.get("categoryId").toString());
                c.setId((int)id);
                c.setNom_Cat(obj.get("categoryNom").toString());
                
                    
                t.setCategory(c);
                java.util.List<Map<String,Object>> listEx=(java.util.List<Map<String,Object>>) obj.get("exercices");
                System.out.println("/////////////////////////"+listEx);
                 Collection<Exercice> ActEx= new ArrayList();
                 if(listEx != null){
                     for(Map<String,Object> item : listEx){
                     try{
                 Exercice e = new Exercice();
                     Category cc = new Category();
                     e.setId((int)Float.parseFloat(item.get("id").toString()));
                     e.setCategory(c);
                     e.setDescription_Exercice(item.get("description_Exercice").toString());
                     e.setDure_Exercice(item.get("dure_Exercice").toString());
                     e.setNom_Exercice(item.get("nom_Exercice").toString());
                     cc.setId((int)Float.parseFloat(item.get("category_id").toString()));
                     cc.setNom_Cat(item.get("category_nom").toString());
                     ActEx.add(e);
                     
                     }catch(Exception ex){
                         System.out.println("exercice list");
                     }
                 }
                     
                 }
             
                t.setExercices(ActEx);
                
                
                
                activities.add(t);
                
            }
            
            
        } catch (IOException ex) {
            System.out.println("error in parseExercice");
        }
        return activities;
    }
       public ArrayList<Activitie> getAllActivity(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"exercice";
        System.out.println("===>"+url);
        req.setUrl("http://127.0.0.1:8000/activity/json/All");
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                activities = parseActivity(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
           System.out.println("hahah");
        return activities;
    }
    
}
