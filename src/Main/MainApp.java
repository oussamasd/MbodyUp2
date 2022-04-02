package Main;


import com.mycompany.myapp.gui.WalkthruForm;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.mycompany.myapp.entities.Activitie;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.entities.Exercice;
import com.mycompany.myapp.gui.StatistiquePieForm;
import com.mycompany.myapp.gui.AjouteActForm;
import com.mycompany.myapp.gui.DetailForm;
import com.mycompany.myapp.services.ServiceActivitie;
import com.mycompany.myapp.services.ServiceExercice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MainApp {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
       String kk = "2022-03-04";
       String year = kk.substring(0, 4);
        System.out.println(year);
        String day = kk.substring(8, 10);
        System.out.println(day);
        String month = kk.substring(5, 7);
         
        System.out.println(month);
        System.out.println(kk);
        new WalkthruForm(theme).show();
        //new AjouteActForm(theme).show();
        
        /*showCat.put("ouss", 3.0);
        showCat.put("ahmed",5.0);
        showCat.put("lala", 5.0);*/
        
       

        //new StatistiquePieForm(theme ,showCat).show();
                 //ServiceActivitie.getInstance().addAct(new Activitie());

        //new DetailForm(theme).show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
