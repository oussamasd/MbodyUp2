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
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Exercice;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author oussa
 */
public class ServiceCommentaire {
    public ArrayList<Commentaire> commentaires;
    
    public static ServiceCommentaire instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCommentaire() {
         req = new ConnectionRequest();
    }

    public static ServiceCommentaire getInstance() {
        if (instance == null) {
            instance = new ServiceCommentaire();
        }
        return instance;
    }
      public ArrayList<Commentaire> parseCommentaire(String jsonText){
        try {
            System.out.println("yimchiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            commentaires=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> commentairesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)commentairesListJson.get("root");
            for(Map<String,Object> obj : list){
                Commentaire t = new Commentaire();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                if (obj.get("iduser")==null)
              t.setIduser(null);
                else
                    t.setIduser(obj.get("iduser").toString());
                if (obj.get("textcommentaire")==null)
              t.setText(null);
                else
                    t.setText(obj.get("textcommentaire").toString());
                if (obj.get("dateCom")==null)
              t.setDatecmnt(null);
                else
                    t.setDatecmnt(obj.get("dateCom").toString());
                
                
                
                commentaires.add(t);
                
            }
            
            
        } catch (IOException ex) {
            System.out.println("error in parseComentaire****************************************");
        }
        return commentaires;
    }
       public ArrayList<Commentaire> getAllCommentaires(int idact){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"category";
        System.out.println("===>"+url);
        req.setUrl("http://127.0.0.1:8000/commentaiire/"+idact);
        //req.setUrl(url);
        req.setPost(false);
           System.out.println(req.getUrl());
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("ahaha");
                commentaires = parseCommentaire(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
           
        return commentaires;
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
