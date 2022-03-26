/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Activitie;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Exercice;
import com.mycompany.myapp.services.ServiceActivitie;
import com.mycompany.myapp.services.ServiceCommentaire;
import com.mycompany.myapp.services.ServiceExercice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;



/**
 *
 * @author oussa
 */
public class DetailForm extends BaseForm {

    public DetailForm(Resources res , Activitie act ) {
         super("Details",new BoxLayout(BoxLayout.Y_AXIS));
         act.setExercices(ServiceExercice.getInstance().getAllExercices(act.getId()));
         System.out.println(act.getExercices());
//         Button back = new Button();
//         back.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SAVE, back.getUnselectedStyle()));
        System.out.println("//////////////////Commentair"+ServiceCommentaire.getInstance().getAllCommentaires(act.getId()));


//        Activitie act = activ;
//        Collection <String> a = new ArrayList<>();
//        a.add("http://127.0.0.1:8000/uploads/3d2ed39926f71268c09b412b0526b12d.jpeg");
//        a.add("https://image.shutterstock.com/image-photo/picture-beautiful-view-birds-260nw-1836263689.jpg");
//        act.setImages(a);

         //////////////////
         Label nom = new Label(act.getNom_Act());
         TextArea details = new TextArea(act.getCategory()+"|"+act.getDate_Act()+","+act.getTemp_act()+"|"+"paticiper par");
         TextArea desc = new TextArea(act.getDescription_Act());
         desc.setUIID("NewsTopLine");
         details.setUIID("NewsTopLine");
         desc.setEditable(false);
         details.setEditable(false);
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Details");
        getContentPane().setScrollVisible(false);
        
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, s, 3);
        getToolbar().addCommandToLeftBar("", icon, (e) -> new NewsfeedForm(res).show());
       // tb.addSearchCommand(e -> {});
        //pictures swap
        Tabs swipe = new Tabs();
      //  ArrayList<String> actimg = act.getImages();
        /*
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        Label sp3 = new Label();*/
       Collection <Image> picts = new ArrayList<>();
        if(act.getImages()== null){
           picts.add(res.getImage("news-item.jpg"));
           
        }else{
             EncodedImage enc;
       Image imgs = null;
       ImageViewer imgv;
       for(String item : act.getImages()){
            try{
       enc = EncodedImage.create("/load.png");
       //https://image.shutterstock.com/image-photo/picture-beautiful-view-birds-260nw-1836263689.jpg
       //String url="http://127.0.0.1:8000/uploads/3d2ed39926f71268c09b412b0526b12d.jpeg";
       imgs=URLImage.createToStorage(enc, item, item,URLImage.RESIZE_SCALE );
       
       }catch(IOException e){
           System.out.println("oooooo");
       //imgs=img;
       }
       picts.add(imgs);
       
       
       }
       
      
       
            
          }
        for(Image pic : picts){
         addTab(swipe, pic, new Label());
        }
    
        
        /*addTab(swipe, res.getImage("news-item.jpg"), spacer1);
        addTab(swipe, res.getImage("dog.jpg"), spacer2);
        addTab(swipe, res.getImage("news-item.jpg"), sp3);*/

                
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
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
                
                
        
       // Component.setSameSize(radioContainer, spacer1, spacer2 );
        add(LayeredLayout.encloseIn( swipe, radioContainer));
        add(nom);
        add(details);
        add(desc);
        add("*****Exercice*****");
        for(Exercice e : act.getExercices()){
        Exerciceget(res.getImage("news-item-2.jpg"), e.getNom_Exercice(),e.getDure_Exercice(), e.getDescription_Exercice());
        }
        //////////////////////Commentaire////////////////////
        showComm(act);
        TextArea com = new TextArea();
        
        Button send = new Button("send");
        addAll(com , send);
        
//        add(back);
        
      
    }
    private void showComm(Activitie act){
     ArrayList<Commentaire> com = ServiceCommentaire.getInstance().getAllCommentaires(act.getId());
     
       
      
       Container cnt = new Container();
       String tit = "*****Commentaire*****";
        cnt.add(tit);
     for (Commentaire c : com){
     Label nom = new Label(c.getIduser()+" : "+c.getDatecmnt());
     TextArea text = new TextArea(c.getText());
     nom.setUIID("NewsTopLine");
     text.setUIID("WelcomeMessage");
     text.setEditable(false);
     cnt.add( 
               BoxLayout.encloseY(
                     nom,
                       text
               ));
     }
   
    add(cnt);
    }
     private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    // , String likesStr, String commentsStr
    private void addTab(Tabs swipe, Image img, Label spacer) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        /*Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }*/
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            //new SpanLabel(text, "LargeWhiteText"),
                            //FlowLayout.encloseIn(likes, comments),
                            //spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    //khatya hedhi 
 
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    private void Exerciceget(Image img, String title, String tempex, String desc) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       TextArea tp = new TextArea(tempex);
       TextArea dec = new TextArea(desc);
       
       ta.setUIID("NewsTopLine");
       tp.setUIID("NewsTopLine");
       dec.setUIID("NewsTopLine");
       ta.setEditable(false);
       tp.setEditable(false);
       dec.setEditable(false);

   
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(),
                       tp,
                       dec
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    
}
