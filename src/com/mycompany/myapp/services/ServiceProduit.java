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
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.gui.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author user
 */
public class ServiceProduit {
    public static ServiceProduit instance = null;
    
    public static boolean resultOk=true;
    //initialaisation de la connection request
    private ConnectionRequest req;
    
    public static ServiceProduit getInstance()
    {
        if(instance == null)
            instance = new ServiceProduit();
        return instance;
    }
    
    
    public ServiceProduit()
    {
        req = new ConnectionRequest();
    }
    
    public void ajouterProduits(Produit produits)
    {
        String url =Statics.BASE_URL+"/addProduits?nom="+produits.getNom()+"&prix="+produits.getPrix()+"&quantite"+produits.getQuantite();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public ArrayList<Produit>affichageProduits()
    {
        ArrayList<Produit> result = new ArrayList<>();
        
        String url = "http://127.0.0.1:8000/displayProduits";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try{
                    Map<String,Object>mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps  = (List<Map<String,Object>>) mapProduits.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps)
                    {
                        Produit re = new Produit(); 
                        float id = Float.parseFloat(obj.get("id").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        float quantite = Float.parseFloat(obj.get("quantite").toString());
                        String nom = obj.get("nom").toString();
                        
                         String description = obj.get("description").toString();
                        
                         re.setId((int)id);
                         re.setNom(nom);
                         re.setPrix((int)prix);
                         re.setQuantite((int)quantite);
                         
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
//    
//    public Produit DetailReclamation(int id , Produit produit)
//    {
//        String url  = Statics.BASE_URL+"/reclamation?"+id;
//        req.setUrl(url);
//        String str  = new String(req.getResponseData());
//        req.addResponseListener(((evt) -> {
//            JSONParser jsonp = new JSONParser();
//            try{
//                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
//               
//                produit.setNom(obj.get("nom").toString());
//                produit.setPrix(obj.get("prix").toString();
//                );
//                
//            }
//            catch(IOException ex)
//            {
//                System.out.println("error related to sql :( "+ex.getMessage());
//            }
//            System.out.println("data === "+str);
//        }));
//        
//          NetworkManager.getInstance().addToQueueAndWait(req);
//          return reclamation;
//    }
//    
    public boolean deleteProduit(int id)
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
    
    public boolean modifierProduit(Produit produit)
    {
        String url = Statics.BASE_URL +"/updateProduits?id="+produit.getId()+"&nom="+produit.getNom()+"&prix="+produit.getPrix()+"&quantite="+produit.getQuantite();      req.setUrl(url);
        
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
