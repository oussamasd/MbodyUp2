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
//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author oussa
 */
public class ServiceCategory {
    public ArrayList<Category> categories;
    
    public static ServiceCategory instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCategory() {
         req = new ConnectionRequest();
    }

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;
    }
   
      public ArrayList<Category> parseCategory(String jsonText){
        try {
            System.out.println("yimchiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            categories=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> categoriesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)categoriesListJson.get("root");
            for(Map<String,Object> obj : list){
                Category t = new Category();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                if (obj.get("nom_cat")==null)
              t.setNom_Cat("hihi");
                else
                    t.setNom_Cat(obj.get("nom_cat").toString());
                
                
                
                categories.add(t);
                
            }
            
            
        } catch (IOException ex) {
            System.out.println("error in parseExercice****************************************");
        }
        return categories;
    }
       public ArrayList<Category> getAllCategories(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"category";
        System.out.println("===>"+url);
        req.setUrl("http://127.0.0.1:8000/category");
        //req.setUrl(url);
        req.setPost(false);
           System.out.println(req.getUrl());
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("ahaha");
                categories = parseCategory(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
           
        return categories;
    }
   /*public void getCat(){
        try {
            URL u = new URL("http://127.0.0.1:8000/category");
            HttpURLConnection hr =(HttpURLConnection)u.openConnection();
            InputStreamReader ip;
            ip = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(ip);
            
                
               
                StringBuffer sb=new StringBuffer();
                
                
                String line = br.readLine();
                while(line !=null){
                    System.out.println(line);
                    line=br.readLine();
                }
            
        } catch (Exception e) {
            System.out.println("ahha");
        }
    
     
    }*/
      
    
}
