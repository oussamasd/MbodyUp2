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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.gui.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author user
 */
public class ServiceReclamation {
    public static ServiceReclamation instance = null;
    
    public static boolean resultOk=true;
    //initialaisation de la connection request
    private ConnectionRequest req;
    
    public static ServiceReclamation getInstance()
    {
        if(instance == null)
            instance = new ServiceReclamation();
        return instance;
    }
    
    
    public ServiceReclamation()
    {
        req = new ConnectionRequest();
    }
    
    public void ajouterReclamation(Reclamation reclamation)
    {
        String url =Statics.BASE_URL+"/addReclamation?nom="+reclamation.getNom()+"&description="+reclamation.getDescription();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public ArrayList<Reclamation>affichageReclamations()
    {
        ArrayList<Reclamation> result = new ArrayList<>();
        
        String url = "http://127.0.0.1:8000/displayReclamations";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try{
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps  = (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps)
                    {
                        Reclamation re = new Reclamation(); 
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                         String description = obj.get("description").toString();
                        
                         re.setId((int)id);
                         re.setNom(nom);
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
    
    public Reclamation DetailReclamation(int id , Reclamation reclamation)
    {
        String url  = Statics.BASE_URL+"/reclamation?"+id;
        req.setUrl(url);
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();
            try{
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
               
                reclamation.setNom(obj.get("nom").toString());
                reclamation.setDescription(obj.get("description").toString()
                );
                
            }
            catch(IOException ex)
            {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            System.out.println("data === "+str);
        }));
        
          NetworkManager.getInstance().addToQueueAndWait(req);
          return reclamation;
    }
    
    public boolean deleteReclamation(int id)
    {
        String url  = Statics.BASE_URL+"/delete?id="+id;
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
    
    public boolean modifierReclamation(Reclamation reclamation)
    {
        String url = Statics.BASE_URL +"/updateReclamation?id="+reclamation.getId()+"&nom="+reclamation.getNom()+"&description="+reclamation.getDescription();
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
