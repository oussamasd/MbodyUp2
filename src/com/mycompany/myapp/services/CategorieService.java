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
import com.mycompany.myapp.gui.Statics;
import com.mycompany.myapp.utils.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategorieService {

    public ArrayList<Categorie> categories;

    public static CategorieService instance = null;
    public boolean resultOk;
    public Categorie categorie;
    private ConnectionRequest req;

    public CategorieService() {

        req = DataSource.getInstance().getRequest();
    }

    public boolean addCategorie(Categorie c) {
        String url = Statics.BASE_URL + "categorie/json/new";
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("type", String.valueOf(c.getType()));
        req.addArgument("tag", String.valueOf(c.getTag()));

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

    public boolean updateCategorie(Categorie c) {
        String url = Statics.BASE_URL + "categorie/json/" + c.getId() + "/edit";
        req.setUrl(url);
        req.setPost(true);

        req.addArgument("type", String.valueOf(c.getType()));
        req.addArgument("tag", String.valueOf(c.getTag()));

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

    public boolean deleteCategorie(Categorie c) {
        String url = Statics.BASE_URL + "categorie/json/delete/" + c.getId();
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

    public ArrayList<Categorie> parseCategories(String jsonText) throws IOException {
        categories = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> categoriesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) categoriesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Categorie c = new Categorie();
            int id = (int) Float.parseFloat(obj.get("id").toString());
            c.setId(id);
            String type = obj.get("type").toString();
            String tag = obj.get("Tage").toString();
            c.setType(type);
            c.setTag(tag);
            categories.add(c);
        }
        return categories;
    }

    public Categorie parseCategorie(String jsonText) throws IOException {
        JSONParser j = new JSONParser();
        Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        Categorie c = new Categorie();
        int id = (int) Float.parseFloat(obj.get("id").toString());
        c.setId(id);
        String type = obj.get("type").toString();
        String tag = obj.get("Tage").toString();
        c.setType(type);
        c.setTag(tag);
        return c;
    }

    public ArrayList<Categorie> getAllCategorie() {
        String url = Statics.BASE_URL + "categorie/json/getAll";
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
                    categories = parseCategories(new String(req.getResponseData()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }

    public Categorie getCategorie(int id) {
        String url = Statics.BASE_URL + "categorie/json/" + id;
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
                    categorie = parseCategorie(new String(req.getResponseData()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categorie;
    }
}
