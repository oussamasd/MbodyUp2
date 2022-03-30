/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entity.Categorie;
import com.mycompany.myapp.entity.Entreneur;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.EntreneurService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AjoutEntreneur extends BaseForm {

    List<Categorie> categories = new CategorieService().getAllCategorie();
    List<Map<String, Object>> entries = new ArrayList<>();

    public AjoutEntreneur(Form prevForm, Resources res) {
        super("Ajout", BoxLayout.y());
        super.getTitleArea().setUIID("Container");
        super.setTitle("Ajout");
        super.getContentPane().setScrollVisible(false);
        super.getToolbar().getUnselectedStyle().setBorder(Border.createDashedBorder(1, 0), true);
        super.getToolbar().getUnselectedStyle().setBgTransparency(255);
        super.getToolbar().getUnselectedStyle().setFgColor(0);
        super.getToolbar().getUnselectedStyle().setBgColor(0);

        super.getToolbar().setHeight(500);

        Container list = new Container(BoxLayout.y());
        Entreneur e = new Entreneur();

        TextField nom = new TextField("", "Nom");
        nom.getStyle().setFgColor(0);
        TextField prenom = new TextField("", "Prénom");
        prenom.getStyle().setFgColor(0);
        TextField age = new TextField("", "Age");
        age.getStyle().setFgColor(0);
        TextField type = new TextField("", "Type");

        for (Categorie category : categories) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("Line1", category.getType());
            entry.put("Line2", category.getTag());
            entries.add(entry);
        }

        ComboBox<Map<String, Object>> combo = new ComboBox<>(entries.toArray());
       
        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
 combo.setSelectedIndex(0);
 revalidate();
 repaint();
        type.getStyle().setFgColor(0);
        Button edit = new Button("Ajouter", FontImage.MATERIAL_EDIT, 5, "Ajouter");

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                e.setNom(nom.getText());
                e.setPrenom(prenom.getText());
                e.setAge(age.getText());
                String t = combo.getSelectedItem().entrySet().toArray()[combo.getSelectedIndex()].toString();
                t = t.substring(t.indexOf("=") + 1, t.length());
                e.setType(t);

                MultipartRequest cr = new MultipartRequest();
                String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                String filename = nom.getText() + "-" + prenom.getText() + ".jpg";
                e.setImage(nom.getText() + "-" + prenom.getText() + ".jpg");
                cr.setUrl(Statics.BASE_URL + "entreneur/json/upload");
                cr.setPost(true);
                String mime = "image/jpeg";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }

                cr.setFilename("file", filename);//any unique name you want

                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);
                Dialog.show("Success", "Image uploaded", "OK", null);

                if (new EntreneurService().addEntreneur(e)) {

                    Dialog alertDialog = new Dialog("Ajout");
                    Command ok = new Command("ok");
                    Button okButton = new Button(ok);
                    alertDialog.setLayout(BoxLayout.y());
                    Container c1 = new Container(); //not so useful here but when there are more buttons
                    c1.setLayout(BoxLayout.x());
                    alertDialog.add(new SpanLabel("Entreneur ajouté avec Succeés !!", "DialogBody"));
                    c1.add(okButton);
                    alertDialog.add(c1);
                    alertDialog.setDisposeWhenPointerOutOfBounds(true);
                    if (alertDialog.showDialog() == ok) {
                        new ListEntreneur(new NewsfeedForm(res), res).showBack();
                    } else {
                        // canceled or clicked outside
                    }
                }
            }
        }
        );

        list.addAll(nom, prenom, age, combo, edit);

        add(list);

        this.getToolbar()
                .addCommandToLeftBar("Return", null, (evt) -> {
                    prevForm.showBack();
                }
                );
    }

}
