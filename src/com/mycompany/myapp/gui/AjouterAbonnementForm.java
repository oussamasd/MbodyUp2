/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.services.ServiceAbonnement;
//import java.awt.BorderLayout;
import java.util.Date;
import javafx.scene.control.ToolBar;

/**
 *
 * @author User
 */
public class AjouterAbonnementForm extends BaseForm {
    
    Form current;
 public AjouterAbonnementForm (Resources res) {
     super("Newsfeed",BoxLayout.y());
     Toolbar tb = new Toolbar(true);
     current = this ;
     setToolbar(tb);
     getTitleArea().setUIID("Container");
     setTitle("Ajouter Abonnement");
     getContentPane().setScrollVisible(false);
     
     tb.addSearchCommand(e-> {
         
     });
     
     Tabs swipe = new Tabs();
     
     Label s1 = new Label();
     Label s2 = new Label();
     
     addTab(swipe,res.getImage("s11.jpg"),"","",res); 

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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
//        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("s11.jpg"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
//            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
     
     TextField objet = new TextField("","entrer un objet!!");
     objet.setUIID("TextFieldBlack");
     addStringValue("Objet",objet);
     
     TextField description = new TextField("","entrer un description!!");
     description.setUIID("TextFieldBlack");
     addStringValue("description",description);
     
     Button btnAjouter = new Button ("Ajouter");
      addStringValue("",btnAjouter);
      
      btnAjouter.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             try {
                 if(objet.getText() =="" || description.getText()==" ") {
                     Dialog.show("veuiller verifier les donnes","", "anuuler","ok");
                 }
                 else {
                     InfiniteProgress ip = new InfiniteProgress();;
                     
                     final Dialog iDialog = ip.showInfiniteBlocking();
                     
                     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                     
                     Abonnement a = new Abonnement(String.valueOf(objet.getText()
                     ).toString(),
                             String.valueOf(description.getText()).toString(),
                             format.format(new Date()),
                             0);
                     System.err.println("datee"+a);
                     
                     ServiceAbonnement.getInstance().ajouterAbonnement(a);
                     
                     iDialog.dispose();// nahiw loading baad l'ajout
                     //new ListAbonnementForm(res).show();
                     refreshTheme();//actualisation
                             }
             } catch(Exception Ex) {
                 Ex.printStackTrace();
             }
         }
     });
      
 }

    private void addStringValue(String s, Component v) {
        add(com.codename1.ui.layouts.BorderLayout.west(new Label(s,"PaddelLabel"))
        .add(BorderLayout.CENTER,v ) );
        add(createLineSeparator(0xeeeeee));
     }

    private void addTab(Tabs swipe,Label spacer ,Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
        
        if(image.getHeight()< size) {
            image = image.scaledHeight(size);
        }
        
        if(image.getHeight()> Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(size);
        }
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label ("","ImageOverLay");
        
        Container page1 =
                LayeredLayout.encloseIn(imageScale,
                        overLay,
                        BorderLayout.south(BoxLayout.encloseY(
                                new SpanLabel(text,"LargeWhiteText"),
                                FlowLayout.encloseIn(null),
                                spacer
                        )
                        )
                
                
                );
        swipe.addTab("",res.getImage("s11.jpg"),page1);
        
    } 
    public void bindButtonSelection(Button btn,Label l) {
        btn.addActionListener(e-> {
            if (btn.isSelected()) {
                updateArrowPosition(btn,l );
            }
        });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
//        l.getUnselectedStyle().setMargin(LEFT,btn.getX() +btn.getWidth() /  2 - l.getWidth()/ 2 );
        l.getParent().repaint();
        
        
    }

    private void addTab(Tabs swipe, Image image, String string, String string0, Resources res) {
    }
}
