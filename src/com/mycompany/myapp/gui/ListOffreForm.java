/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceOffre;

/**
 *
 * @author User
 */
public class ListOffreForm extends BaseForm {
    public ListOffreForm(Form previous) {
        setTitle("List offres");

        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceOffre.getInstance().getAllOffre().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
