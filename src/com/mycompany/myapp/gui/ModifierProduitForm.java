/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;

/**
 *
 * @author user
 */
public class ModifierProduitForm extends BaseForm{
    Form current;
    public ModifierProduitForm(Resources res, Produit r )
    {
          super("Newsfeed",BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Produit");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        TextField nom = new TextField(r.getNom() , "nom" , 20 , TextField.ANY); 
        nom.setUIID("TextFieldBlack");
        
         TextField prix = new TextField( "prix" ,"");
          prix.setUIID("TextFieldBlack");
          TextField quantite = new TextField("quantite" ,"");
           quantite.setUIID("TextFieldBlack");
  
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
        btnModifier.addPointerPressedListener(l->{
            r.setNom(nom.getText());
             r.setPrix(Integer.parseInt(prix.getText()));
              r.setQuantite(Integer.parseInt(quantite.getText()));
           
            
          
        
        //appele de la fonction dans service
        if(ServiceProduit.getInstance().modifierProduit(r))
        {
            new ListProduitForm(res).show();
        }});
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e->{
            new ListProduitForm(res).show();
        });
        
        Label l1 = new Label("");
         Label l2 = new Label("");
          Label l3 = new Label("");
           Label l4 = new Label("");
            Label l5 = new Label("");
            
            Container content = BoxLayout.encloseY(
            l1,l2,
                    new FloatingHint(nom),
                    createLineSeparator(),
                    new FloatingHint(prix),
                    createLineSeparator(),
                    new FloatingHint(quantite),
                    createLineSeparator(),
                    l4,l5,
                    btnModifier,
                    btnAnnuler
            );
            add(content);
            show();
        
    }
    
}
