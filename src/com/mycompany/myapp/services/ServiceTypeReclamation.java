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

import com.mycompany.myapp.entities.TypeReclamation;
import com.mycompany.myapp.gui.Statics;
import static com.mycompany.myapp.services.ServiceTypeReclamation.resultOk;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import static com.mycompany.myapp.services.ServiceTypeReclamation.instance;

/**
 *
 * @author user
 */
public class ServiceTypeReclamation {
    
    public static ServiceTypeReclamation instance = null;
    
    public static boolean resultOk=true;
    //initialaisation de la connection request
    private ConnectionRequest req;
    
    public static ServiceTypeReclamation getInstance()
    {
        if(instance == null)
            instance = new ServiceTypeReclamation();
        return instance;
    }
    
    
    public ServiceTypeReclamation()
    {
        req = new ConnectionRequest();
    }
    
    
     public void ajouterTypeReclamation(TypeReclamation typeReclamation)
    {
        String url =Statics.BASE_URL+"/addTypeReclamation?nom="+typeReclamation.getNom();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     
     
      public ArrayList<TypeReclamation>affichageTypeReclamations()
    {
        ArrayList<TypeReclamation> result = new ArrayList<>();
        
        String url = "http://127.0.0.1:8000/displayTypeReclamations";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try{
                    Map<String,Object>mapTypeReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps  = (List<Map<String,Object>>) mapTypeReclamations.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps)
                    {
                        TypeReclamation re = new TypeReclamation(); 
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                        
                         re.setId((int)id);
                         re.setNom(nom);
                         
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
    
      
       public boolean deleteTypeReclamation(int id)
    {
        String url  = Statics.BASE_URL+"/Typedelete?id="+id;
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
      
      
      public boolean modifierTypeReclamation(TypeReclamation typereclamation)
    {
        String url = Statics.BASE_URL +"/updateTypeReclamation?id="+typereclamation.getId()+"&nom="+typereclamation.getNom();
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


