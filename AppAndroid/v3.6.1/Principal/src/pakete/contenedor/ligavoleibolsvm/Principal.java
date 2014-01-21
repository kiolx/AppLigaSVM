package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Principal extends SherlockActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Theme_Sherlock_Light_NoActionBar);	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal); 
  
   //Calculo y Ajuste de la pantalla//
   //////////////////////////////////
        Display display = getWindowManager().getDefaultDisplay(); 
        int anchoDisplay = display.getWidth();  // deprecated //utilizamos este metodo porque sosporte desde la api 1
        int altoDisplay = display.getHeight();  // deprecated
       
        LinearLayout adaptarHuecoLogo =(LinearLayout)findViewById(R.id.linarInterior2);
        
        int altoHuecoCalculado = altoDisplay -2*150 - 150;
        
        adaptarHuecoLogo.getLayoutParams().height = altoHuecoCalculado;
        adaptarHuecoLogo.getLayoutParams().width = anchoDisplay;
        adaptarHuecoLogo.requestLayout();
        
   
   //Metodos OnSetListener de los views, para que accion hacer cuando se pulse sobre ellos //
  //////////////////////////////////////////////////////////////////////////////////////////
        FrameLayout controlPanel =(FrameLayout)findViewById(R.id.Frameview6);
        controlPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.textView6);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	
            	Intent OpcionesActiviyStart = new Intent(Principal.this, ExtrasMenuActivity.class);
            	startActivity(OpcionesActiviyStart);
                   
            }
        });  
        
        FrameLayout equiposPanel =(FrameLayout)findViewById(R.id.Frameview3);
        equiposPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            	TextView cambioFondo =(TextView)findViewById(R.id.textView3);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	
            	Intent EquiposActiviyStart = new Intent(Principal.this, EquiposActivity.class);
            	startActivity(EquiposActiviyStart);
                   
            }
        });  
    
        FrameLayout directoPanel =(FrameLayout)findViewById(R.id.Frameview1);
        directoPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.textView1);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	
            	Intent EquiposActiviyStart = new Intent(Principal.this, DirectoActivity.class);
            	startActivity(EquiposActiviyStart);
                   
            }
        }); 
        
        FrameLayout clasificacionPanel =(FrameLayout)findViewById(R.id.Frameview2);
        clasificacionPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.textView2);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	
            	Intent EquiposActiviyStart = new Intent(Principal.this, ClasificacionActivity.class);
            	startActivity(EquiposActiviyStart);
                   
            }
        });   
        
        FrameLayout noticiasPanel =(FrameLayout)findViewById(R.id.Frameview4);
        noticiasPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.textView4);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	
            	Intent NoticiasActiviyStart = new Intent(Principal.this, NoticiasActivity.class);
            	startActivity(NoticiasActiviyStart);
                   
            }
        });
        
        FrameLayout copaReyPanel =(FrameLayout)findViewById(R.id.Frameview5);
        copaReyPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	TextView cambioFondo =(TextView)findViewById(R.id.textView5);
            	cambioFondo.setBackgroundResource(R.drawable.gradient_bg_hover);
            	
            	Intent CopaReyActiviyStart = new Intent(Principal.this, CopaReyActivity.class);
            	startActivity(CopaReyActiviyStart);
                   
            }
        });
        
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://mobil.cavesquimo.es/appsvm.php?func=mostrarVersion", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
         	       
         	   if(!response.equals(getString(R.string.appVersion))){
         		 AlertDialog alertDialog = new AlertDialog.Builder(Principal.this).create();
         		 alertDialog.setTitle("Actualizacion disponible");
         		 alertDialog.setMessage("Existe una nueva versión disponible, por favor actualizela.");
         		 alertDialog.setButton("Actualizar", new DialogInterface.OnClickListener() {
         		    public void onClick(DialogInterface dialog, int which) {
         		    	Intent intent = new Intent(Intent.ACTION_VIEW); 
         		    	intent.setData(Uri.parse("market://details?id=pakete.contenedor.ligavoleibolsvm")); 
         		    	startActivity(intent);
         		    }
         		 });
         		 alertDialog.setButton2("Cancelar", new DialogInterface.OnClickListener() {
          		    public void onClick(DialogInterface dialog, int which) {
          		       dialog.dismiss();
          		    }
          		 });
         		 alertDialog.setIcon(R.id.abs__home);
         		 alertDialog.show();              
         	   }

            }
        });
       
  
        
}
    
    @Override
    public void onBackPressed() {
   	  Intent startMain = new Intent(Intent.ACTION_MAIN);
    	startMain.addCategory(Intent.CATEGORY_HOME);
    	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(startMain);
    	
    	int pid = android.os.Process.myPid();
  	    android.os.Process.killProcess(pid);
  	    
    }
    

}//Fin de la clase