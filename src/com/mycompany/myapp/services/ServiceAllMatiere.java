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
import com.mycompany.myapp.entities.Category;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public class ServiceAllMatiere {
  
    
        public ArrayList<Category> categories;
    
    public static ServiceAllMatiere instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAllMatiere() {
         req = new ConnectionRequest();
    }

    public static ServiceAllMatiere getInstance() {
        if (instance == null) {
            instance = new ServiceAllMatiere();
        }
        return instance;
    }



    public ArrayList<Category> parseTasks(String jsonText){
        try {
            System.out.println("33333333333333333");
            categories=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> matiereListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)matiereListJson.get("root");
            for(Map<String,Object> obj : list){
                System.out.println("44444444444444444444");
                Category t = new Category();
                float id = Float.parseFloat(obj.get("id").toString());
                String  nomcat = (obj.get("nom_Cat").toString());
                
                t.setId((int)id);
                t.setNom_Cat(obj.get("nom_Cat").toString());               
                
            


                


                categories.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return categories;
    }
    
         public ArrayList<Category> getaMatiere(){
        String url = Statics.BASE_URL+"category";
             System.out.println(url);
        req.setUrl(url);
             System.out.println("1111111111");
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("222222222222222222");
                categories = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
         /* public ArrayList<Matiere> getMatiereByName(String Name){
        String url = Statics.BASE_URL+"getMatiereByName/"+Name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matiere = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matiere;
    } */  
}