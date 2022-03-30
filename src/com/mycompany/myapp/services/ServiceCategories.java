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

import com.mycompany.myapp.entities.Categories;
import com.mycompany.myapp.gui.Statics;
import static com.mycompany.myapp.services.ServiceCategories.resultOk;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import static com.mycompany.myapp.services.ServiceCategories.instance;

/**
 *
 * @author user
 */
public class ServiceCategories {
    
    public static ServiceCategories instance = null;
    
    public static boolean resultOk=true;
    //initialaisation de la connection request
    private ConnectionRequest req;
    
    public static ServiceCategories getInstance()
    {
        if(instance == null)
            instance = new ServiceCategories();
        return instance;
    }
    
    
    public ServiceCategories()
    {
        req = new ConnectionRequest();
    }
    
    
     public void ajouterCategorie(Categories categorie)
    {
        String url =Statics.BASE_URL+"/addCategories?nom="+categorie.getNom()+"&type="+categorie.getType()+"&description="+categorie.getDescription();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     
     
      public ArrayList< Categories>affichageCategorie()
    {
        ArrayList<Categories> result = new ArrayList<>();
        
        String url = "http://127.0.0.1:8000/displayCategories";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try{
                    Map<String,Object>mapCategories = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps  = (List<Map<String,Object>>) mapCategories.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps)
                    {
                        Categories re = new Categories(); 
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nom = obj.get("nom").toString();
                        String type = obj.get("type").toString();
                        String description = obj.get("description").toString();
                        
                        
                         re.setId((int)id);
                         re.setNom(nom);
                         re.setType(type);
                         re.setDescription(description);
                         result.add(re);
                    }
                    
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        
         NetworkManager.getInstance().addToQueueAndWait(req);
         return result;
    }
    
      
       public boolean deleteCategorie(int id)
    {
        String url  = Statics.BASE_URL+"/Categoriesdelete?id="+id;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
      
      
      public boolean modifierCategorie(Categories categories)
    {
        String url = Statics.BASE_URL +"/updateCategories?id="+categories.getId()+"&nom="+categories.getNom()+"&type="+categories.getType()+"&description="+categories.getDescription();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
          NetworkManager.getInstance().addToQueueAndWait(req);
          return resultOk;
    }
    
      
      
}


