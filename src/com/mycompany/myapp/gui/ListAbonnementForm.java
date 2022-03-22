/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Command;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.services.ServiceAbonnement;

import java.util.ArrayList;
/**
 *
 * @author User
 */
public class ListAbonnementForm extends BaseForm {

    private Object previous;
    public ListAbonnementForm() {
        setTitle("List Abonnement");
              ServiceAbonnement es;
        es = new ServiceAbonnement();
        ArrayList<Abonnement> list = es.getAllAbonnement();

        {
           
            for (Abonnement r : list) {

          
 
                Container c3 = new Container(BoxLayout.y());
               
                 SpanLabel cat= new SpanLabel("Nom de l'abonnement :" + r.getNom());
                  SpanLabel cat2= new SpanLabel("Ctégories :" + r.getCategorie());
                 SpanLabel cat1= new SpanLabel("Description :" + r.getDescription());
                 SpanLabel cat3= new SpanLabel("Prix :" + r.getPrix());
                 SpanLabel cat4= new SpanLabel("Photo :" + r.getPhoto());
                 
               
                     
                      
                        c3.add(cat);
                        c3.add(cat1);
                        c3.add(cat2);
                        c3.add(cat3);
       
                        /* Button Delete =new Button("Delete");
         c3.add(Delete);
            Delete.getAllStyles().setBgColor(0xF36B08);
            Delete.addActionListener(e -> {
               Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette abonnement?\nCette action est irréversible!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                       es.Delete(r.getId());
                     
                        alert.dispose();
                        refreshTheme();
                    }

                
                
                }
                
                       
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
           //   new ListAbonnementForm(previous).show();
              
                
             
            });
                       
                        
           System.out.println("");
                   */
     
  add(c3);
                
            
         // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
           //  , e-> previous.showBack()); // Revenir vers l'interface précédente
                
            }
     
    }

    }
}
