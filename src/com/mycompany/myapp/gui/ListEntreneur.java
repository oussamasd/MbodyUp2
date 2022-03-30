/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entity.Entreneur;
import com.mycompany.myapp.services.EntreneurService;
import java.util.ArrayList;


public class ListEntreneur extends BaseForm {
    
     private Resources theme;
      public ArrayList<Entreneur> entreneurs;

    public ListEntreneur(Form previous,Resources res) {
        super("Entreneurs", BoxLayout.y());
        super.getTitleArea().setUIID("Container");
        super.setTitle("Entreneurs");
        super.getContentPane().setScrollVisible(false);
        super.getToolbar().getUnselectedStyle().setBorder(Border.createDashedBorder(1, 0),true);
        super.getToolbar().getUnselectedStyle().setBgTransparency(255);
        super.getToolbar().getUnselectedStyle().setFgColor(0);
        super.getToolbar().getUnselectedStyle().setBgColor(0);
        super.getToolbar().setHeight(500);
        
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");

        this.getToolbar().addCommandToOverflowMenu("Ajouter", FontImage.createMaterial(FontImage.MATERIAL_ADD,s), e -> {new AjoutEntreneur(this, res).show();});
        this.getToolbar().addCommandToOverflowMenu("PDF", FontImage.createMaterial(FontImage.MATERIAL_FILE_UPLOAD,s), e->{new EntreneurService().EmailPdf();});
        
        
        
        entreneurs = new EntreneurService().getAllEntreneurs();
        Container list = new Container(BoxLayout.y());
                list.setScrollableY(true);
          
         for (Entreneur entreneur : entreneurs) {
                    MultiButton mb = new MultiButton(entreneur.getNom()+" "+entreneur.getPrenom());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(200, 200, 0xffff0000), true);
URLImage background = URLImage.createToStorage(placeholder, entreneur.getImage(),
        "http://127.0.0.1:8000/uploads/"+entreneur.getImage());
background.fetch();

    
                    
                    mb.add(BoxLayout.X_AXIS, background.scaledEncoded(200, 200));
                    mb.setTextLine2("Age : "+entreneur.getAge());
                    mb.setTextLine3("Type : "+entreneur.getType());
                    
                    
                    mb.addLongPressListener((evt) -> {
                        if(Dialog.show("Supprimer", "Confirmer la supprission de l'entreneur "+entreneur.getNom()+" "+entreneur.getPrenom(), "Suppriner", "Annuler")){
                            new EntreneurService().deleteEntreneur(entreneur);
                        }
                    });
                    mb.addActionListener((evt) -> {
                        new EntreneurDetails(this, theme, entreneur).show();
                    });
                    list.add(mb);
		}
         	this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                    previous.showBack();
                });
                this.add(list);
                
                
    }
    
      
      
    
}
