package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Activitie;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.entities.Exercice;
import com.mycompany.myapp.services.ServiceActivitie;
import com.mycompany.myapp.services.ServiceCategory;
import com.mycompany.myapp.services.ServiceExercice;
import java.util.ArrayList;
import java.util.Collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oussa
 */
public class AjouteActForm extends BaseForm{
    public AjouteActForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField ActNom = new TextField("", "Nom Activité", 20, TextField.ANY);
       // TextField dateAct = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField tempAct = new TextField("", "Temps Activite", 20, TextField.ANY);
        TextField descriptionAct = new TextField("", "Description", 20, TextField.ANY);
        Picker dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE);

        //TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        ActNom.setSingleLineTextArea(false);
        tempAct.setSingleLineTextArea(false);
        descriptionAct.setSingleLineTextArea(false);
        Picker timePicker = new Picker();
        timePicker.setType(Display.PICKER_TYPE_TIME);
        timePicker.setTime(10 * 60);
        //String[] pp = new String[]{"hh","jj"};
       
        ServiceCategory.getInstance().getAllCategories();
        
        ArrayList<Category>aa=ServiceCategory.getInstance().getAllCategories();
        
         String[] pp = new String[aa.size()];
        int k=0;
        for(Category a:aa){
        pp[k]= ""+Integer.toString(a.getId())+"_"+ a.getNom_Cat();
        k++;
        }
       
        
        Picker stringPicker = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker.setStrings(pp);//new String[]{"hh","jj"}
        
        //stringPicker.setStrings("ll");
        
        stringPicker.setSelectedString("Category");
        Button next = new Button("ADD");
        //Button signIn = new Button("Sign In");
        //signIn.addActionListener(e -> previous.showBack());
        //signIn.setUIID("Link");
        //Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(ActNom),
                createLineSeparator(),
                new FloatingHint(tempAct),
                createLineSeparator(),
                 dateTimePicker,
                createLineSeparator(),
                timePicker,
                createLineSeparator(),
                new FloatingHint(descriptionAct),
                createLineSeparator(),
                stringPicker,
                createLineSeparator()
                
        );
        Button show = new Button("Show");
        show.addActionListener(l-> new NewsfeedForm(res).show());
        content.setScrollableY(true);
        Collection<CheckBox>lcb = new ArrayList<CheckBox>();
        for(Exercice e : ServiceExercice.getInstance().ShowAllExercices()){
        CheckBox cb1 = new CheckBox(""+Integer.toString(e.getId())+"_"+e.getNom_Exercice());
        lcb.add(cb1);
        content.add(cb1);
        
        }
        
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                show
        ));
        next.requestFocus();
        next.addActionListener(e -> {
        Activitie act = new Activitie();
        act.setDescription_Act(descriptionAct.getText());
        act.setNom_Act(ActNom.getText());
        act.setDate_Act(dateTimePicker.getText());
        act.setTemp_act(timePicker.getText());
        Category ca = new Category();
        ca.setNom_Cat(stringPicker.getText());
        act.setCategory(ca);
        //Collection<String>le=new ArrayList<String>();
        String[] d = new String[aa.size()];
        String ch ="";
        int o=0;
        for(CheckBox c :lcb){
        
        if(c.isSelected()){
          d[o]=c.getText();
           ch+="/"+c.getText();
          
                
        }
        o++;
            }
        
            //System.out.println(dateTimePicker.getText());
              //          System.out.println(timePicker.getText());

            if(ServiceActivitie.getInstance().addAct(act, d , ch)){
            
                 Dialog.show("Dialog Title", "This is the dialog body, it can contain anything...", "OK", null);
                 
            }
            
        
        });
    }
    
}
