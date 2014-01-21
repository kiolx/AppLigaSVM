package pakete.contenedor.ligavoleibolsvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;

public class Principal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        
        final ImageButton buttonStreaming = (ImageButton) findViewById(R.id.imageButtonStreaming);
        buttonStreaming.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                   
            	AlertDialog.Builder builder = new AlertDialog.Builder(Principal.this);
                builder.setTitle("Opcion no disponible");
                builder.setMessage("Disponible en la proxima version");
                builder.setPositiveButton("OK",null);
                builder.create();
                builder.show();
            	
               	//Intent Streaming = new Intent(Principal.this, StreamingActivityVideo.class);
               	//startActivity(Streaming);                
            }
        });  
        
        Handler warro =new Handler();
        final Runnable r = new Runnable() {
            public void run() {

                TextView textultima = (TextView) findViewById(R.id.textViewUltimahora);
                textultima.setText(getContents("http://mobil.cavesquimo.es/ultimahora.txt"));
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
}

