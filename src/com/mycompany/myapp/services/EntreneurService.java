/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entity.Categorie;
import com.mycompany.myapp.entity.Entreneur;
import com.mycompany.myapp.gui.Statics;
import com.mycompany.myapp.utils.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntreneurService {

    public ArrayList<Entreneur> entreneurs;

    public static EntreneurService instance = null;
    public boolean resultOk;
    public Entreneur entreneur;
    private ConnectionRequest req;

    public EntreneurService() {
        this.entreneur = new Entreneur();
        req = DataSource.getInstance().getRequest();
    }

    public boolean addEntreneur(Entreneur c) {
        String url = Statics.BASE_URL + "entreneur/json/new";
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("nom", String.valueOf(c.getNom()));
        req.addArgument("prenom", String.valueOf(c.getPrenom()));
        req.addArgument("type", String.valueOf(c.getType()));
        req.addArgument("age", String.valueOf(c.getAge()));
        req.addArgument("image",String.valueOf(c.getImage()));
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
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
    
    public boolean EmailPdf()
    {
         String url = Statics.BASE_URL + "entreneur/json/pdf";
        req.setUrl(url);
         InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
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

    public ArrayList<Entreneur> parseEntreneurs(String jsonText) throws IOException {
        entreneurs = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> entreneursListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) entreneursListJson.get("root");

        for (Map<String, Object> obj : list) {
            Entreneur c = new Entreneur();
            int id = (int) Float.parseFloat(obj.get("id").toString());
            c.setId(id);
            String nom = obj.get("nom").toString();
            String prenom = obj.get("prenom").toString();
            String age = obj.get("age").toString();
            String type = obj.get("type").toString();
            String image = obj.get("image").toString();
            Map<String,Object> cat = (Map<String,Object>)obj.get("ncategorie");
            Categorie categ = new Categorie((int) Float.parseFloat(cat.get("id").toString()), cat.get("type").toString(), cat.get("tage").toString());
            c.setNom(nom);
            c.setPrenom(prenom);
            c.setAge(age);
            c.setType(type);
            c.setCat(categ);
            c.setImage(image);
            entreneurs.add(c);
        }
        return entreneurs;

    }

    public Entreneur parseEntreneur(String jsonText) throws IOException {
        JSONParser j = new JSONParser();
        Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        Entreneur c = new Entreneur();
        int id = (int) Float.parseFloat(obj.get("id").toString());
        c.setId(id);
        String nom = obj.get("nom").toString();
        String prenom = obj.get("prenom").toString();
        String age = obj.get("age").toString();
        String type = obj.get("type").toString();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setAge(age);
        c.setType(type);
        return c;
    }

    public ArrayList<Entreneur> getAllEntreneurs() {
        String url = Statics.BASE_URL + "entreneur/json/getAll";
        req.setUrl(url);
        req.removeAllArguments();
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    entreneurs = parseEntreneurs(new String(req.getResponseData()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return entreneurs;
    }

    public Entreneur getEntreneur(int id) {
        String url = Statics.BASE_URL + "entreneur/json/" + id;
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    entreneur = parseEntreneur(new String(req.getResponseData()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return entreneur;
    }

    public boolean updateEntreneur(Entreneur c) {
        String url = Statics.BASE_URL + "entreneur/json/" + c.getId() + "/edit";
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("nom", String.valueOf(c.getNom()));
        req.addArgument("prenom", String.valueOf(c.getPrenom()));
        req.addArgument("type", String.valueOf(c.getType()));
        req.addArgument("age", String.valueOf(c.getAge()));
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
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

    public boolean deleteEntreneur(Entreneur c) {
        String url = Statics.BASE_URL + "entreneur/json/delete/" + c.getId();
        req.setUrl(url);
        req.setPost(true);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
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
