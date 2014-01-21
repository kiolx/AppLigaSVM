package pakete.contenedor.ligavoleibolsvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class Principal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Theme_Sherlock_Light_NoActionBar);	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal); 
  
        final ImageButton buttonDirecto = (ImageButton) findViewById(R.id.imageButtonDirecto);
        buttonDirecto.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent Directo = new Intent(Principal.this, DirectoActivity.class);
            	startActivity(Directo);
            	                   
            }
        });  
        
        final ImageButton buttonInformacion = (ImageButton) findViewById(R.id.imageButtonInformacion);
        buttonInformacion.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent Informacion = new Intent(Principal.this, ExtrasActivity.class);
            	startActivity(Informacion);
            	
            }
        });  
        
        final ImageButton buttonClasificacion = (ImageButton) findViewById(R.id.imageButtonClasificacion);
        buttonClasificacion.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent Clasificacion = new Intent(Principal.this, ClasificacionActivity.class);
            	startActivity(Clasificacion);
                   
            }
        });  
        
        final ImageButton buttonEquipos = (ImageButton) findViewById(R.id.imageButtonEquipos);
        buttonEquipos.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
            	
                AlertDialog.Builder builder = new AlertDialog.Builder(Principal.this);
                
                builder.setTitle("Informacion");
                builder.setMessage("No disponible en esta version");
                builder.setPositiveButton("OK", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
               builder.show();
            	
            }
        });  
        
        Handler warro =new Handler();
        final Runnable r = new Runnable() {
            public void run() {

            	try {
                TextView textultima = (TextView) findViewById(R.id.textViewUltimahora);
                textultima.setText(getContents("http://mobil.cavesquimo.es/ultimahora.txt"));
            	}catch(Exception e){}
            	 	
           }
        };
        
        warro.postDelayed(r, 20);
}
    
    public static String getContents(String url) {
        String contents ="";
 
  try {
        URLConnection conn = new URL(url).openConnection();
 
        InputStream in = conn.getInputStream();
        contents = convertStreamToString(in);
   } catch (MalformedURLException e) {

   } catch (IOException e) {

   }
 
  return contents;
}
 
private static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
 
      BufferedReader reader = new BufferedReader(new    
                              InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();
         String line = null;
         try {
                while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                }
           } catch (IOException e) {
                e.printStackTrace();
           } finally {
                try {
                        is.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
            }
        return sb.toString();
  }

}//Fin de la clase