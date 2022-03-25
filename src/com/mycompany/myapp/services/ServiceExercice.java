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
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.entities.Exercice;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author oussa
 */
public class ServiceExercice {
     public ArrayList<Exercice> exercices;
    
    public static ServiceExercice instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceExercice() {
         req = new ConnectionRequest();
    }

    public static ServiceExercice getInstance() {
        if (instance == null) {
            instance = new ServiceExercice();
        }
        return instance;
    }
   
      public ArrayList<Exercice> parseExercice(String jsonText){
        try {
            System.out.println("yimchiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            exercices=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> exercicesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)exercicesListJson.get("root");
            for(Map<String,Object> obj : list){
                Exercice t = new Exercice();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                if (obj.get("nom_Exercice")==null)
              t.setNom_Exercice("null");
                else
                    t.setNom_Exercice(obj.get("nom_Exercice").toString());
                
                if (obj.get("dure_Exercice")==null)
              t.setDure_Exercice("null");
                else
                    t.setDure_Exercice(obj.get("dure_Exercice").toString());
                
                if (obj.get("description_Exercice")==null)
              t.setDescription_Exercice("null");
                else
                    t.setDescription_Exercice(obj.get("description_Exercice").toString());
                if (obj.get("categoryId")==null){
              t.setCategory(new Category());
                }
                else
                {
                    Category c = new Category();
                    float id2 = Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                    c.setNom_Cat(obj.get("categoryNom").toString());
                    
                    t.setCategory(c);
                }
                exercices.add(t);
                
            }
            
            
        } catch (IOException ex) {
            System.out.println("error in parseExercice");
        }
        return exercices;
    }
       public ArrayList<Exercice> getAllExercices(int id){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"exercice";
        System.out.println("===>"+url);
        req.setUrl("http://127.0.0.1:8000/exercice/"+id);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                exercices = parseExercice(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
           System.out.println("hahah");
        return exercices;
    }
}
