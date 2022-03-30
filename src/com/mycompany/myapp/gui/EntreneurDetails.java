/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entity.Categorie;
import com.mycompany.myapp.entity.Entreneur;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.EntreneurService;
import java.util.List;


public class EntreneurDetails extends BaseForm {

    private Resources theme;
    //private List<Categorie> categories;

    public EntreneurDetails(Form prevForm, Resources res, Entreneur e) {

        super("Details", BoxLayout.y());
        super.getTitleArea().setUIID("Container");
        super.setTitle("Details");
        super.getContentPane().setScrollVisible(false);
        super.getToolbar().getUnselectedStyle().setBorder(Border.createDashedBorder(1, 0), true);
        super.getToolbar().getUnselectedStyle().setBgTransparency(255);
        super.getToolbar().getUnselectedStyle().setFgColor(0);
        super.getToolbar().getUnselectedStyle().setBgColor(0);
        super.getToolbar().setHeight(500);

        Container list = new Container(BoxLayout.y());

        Label nom_l = new Label("Nom");
        TextField nom = new TextField(e.getNom(), "Nom");
        Label pre_l = new Label("Prénom");
        TextField prenom = new TextField(e.getPrenom(), "Prénom");
        Label age_l = new Label("Age");
        TextField age = new TextField(e.getAge(), "Age");
        Label type_l = new Label("Type");
        TextField type = new TextField(e.getType(), "Type");
        
        Picker typePicker = new Picker();
        typePicker.setType(Display.PICKER_TYPE_STRINGS);
        
        /*categories = new CategorieService().getAllCategorie();
        for (Categorie category : categories) {
            
            
            
            typePicker.add();
            
        }*/
         
        nom.getStyle().setFgColor(0);
        prenom.getStyle().setFgColor(0);
        age.getStyle().setFgColor(0);
        type.getStyle().setFgColor(0);
      
        Button edit = new Button("Modifier", FontImage.MATERIAL_EDIT, 5, "Modifier");

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                e.setNom(nom.getText());
                e.setPrenom(prenom.getText());
                e.setAge(age.getText());
                e.setType(type.getText());

                if (new EntreneurService().updateEntreneur(e)) {
                    Dialog dlg = new Dialog("Mise à Jour");

                    dlg.add(new SpanLabel("Entreneur mis à jour avec Succeés !!", "DialogBody"));
                    int h = Display.getInstance().getDisplayHeight();
                    dlg.setDisposeWhenPointerOutOfBounds(true);
                    dlg.show(h / 8 * 7, 0, 0, 0);

                }
            }
        }
        );

        list.addAll(nom_l, nom, pre_l, prenom, age_l, age, type_l, type, edit);

        add(list);

        this.getToolbar()
                .addCommandToLeftBar("Return", null, (evt) -> {
                    prevForm.showBack();
                }
                );
    }

}
