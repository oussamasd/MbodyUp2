/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ListReclamationForm extends BaseForm{
    Form current;
    Reclamation rec = new Reclamation();
    public ListReclamationForm(Resources res)
    {
        
        super("Newsfeed",BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        tb.addSearchCommand((e)->{
            
        });
        
        Tabs swipe  = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();
        
       addTab(swipe,s1,res.getImage("complaint.png"),"","",res);
        
        //
        
        
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Ajouter Reclamation", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Modifier Reclamation", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Mes Reclamations", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               new AjoutReclamationForm(res).show();
        });
         liste.addActionListener((e) -> {
                new ModifierReclamationForm(res,rec).show();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        ArrayList<Reclamation>list = ServiceReclamation.getInstance().affichageReclamations();
        for(Reclamation rec : list)
        {
            String urlImage ="profile-background.jpg";
            
            Image placeHolder = Image.createImage(120,90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder,false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
            
            addButton(urlim,rec,res);
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
        
    }
    
    
    
    private void addTab(Tabs swipe,Label spacer ,Image image, String string, String text, Resources res) {
        int size  = Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
        if(image.getHeight() < size)
        {
            image  = image.scaledHeight(size);
        }
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2)
        {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overlay = new Label("","ImageOverlay");
        
        Container page1 = 
                LayeredLayout.encloseIn(
                        imageScale,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText")
                                        
                                       ,spacer
                                )
                        )
                );
        swipe.addTab("",res.getImage("profile-background.jpg"),page1);
        
    }
    
    
    public void bindButtonSelection(Button btn , Label l)
    {
        btn.addActionListener(e->{
            if(btn.isSelected()){
                updateArrowPosition(btn,l);
            }
        });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
        
    }

    private void addButton(Image img, Reclamation rec,Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width,height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image) ;
        
        Label nomTxt = new Label("nom : "+rec.getNom(),"NewsTopLine2");
        Label descriptionTxt = new Label("description : "+rec.getDescription(),"NewsTopLine2");
        
        createLineSeparator();
        
       
        
//        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(descriptionTxt)));
        
//butt supprimer
        Label lSupprimer = new Label(" Supprimer ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupprimer.setIcon(supprimerImage);
        lSupprimer.setTextPosition(RIGHT);
        //click butt suprim
        lSupprimer.addPointerPressedListener(l->{
            Dialog dig = new Dialog("supression");
            if(dig.show("Supression","Vous Voulez supprimer cette Reclamation ?","Annuler","oui"))
            {
                dig.dispose();
            }
            else{
                dig.dispose();
                if(ServiceReclamation.getInstance().deleteReclamation(rec.getId()))
                {
                    new ListReclamationForm(res).show();
                }
            }
        });
        
        //Update Icon
        Label lModifier = new Label(" Modifier ");
        lModifier.setUIID("NewsTopLine");
        Style ModifierStyle = new Style(lModifier.getUnselectedStyle());
        ModifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, ModifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l->{
           // System.out.println("hello update");
           new ModifierReclamationForm(res,rec).show();
        });
        
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                BoxLayout.encloseX(nomTxt,lModifier,lSupprimer),
                BoxLayout.encloseX(descriptionTxt)
                ));
        
        add(cnt);
    }
}
