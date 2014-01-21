package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;

import android.content.Intent;
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
        FrameLayout controlPanel =(FrameLayout)findViewById(R.id.Frameview4);
        controlPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent OpcionesActiviyStart = new Intent(Principal.this, ExtrasMenuActivity.class);
            	startActivity(OpcionesActiviyStart);
                   
            }
        });  
        
        FrameLayout equiposPanel =(FrameLayout)findViewById(R.id.Frameview3);
        equiposPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent EquiposActiviyStart = new Intent(Principal.this, EquiposActivity.class);
            	startActivity(EquiposActiviyStart);
                   
            }
        });  
    
        FrameLayout directoPanel =(FrameLayout)findViewById(R.id.Frameview1);
        directoPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent EquiposActiviyStart = new Intent(Principal.this, DirectoActivity.class);
            	startActivity(EquiposActiviyStart);
                   
            }
        }); 
        
        FrameLayout clasificacionPanel =(FrameLayout)findViewById(R.id.Frameview2);
        clasificacionPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	Intent EquiposActiviyStart = new Intent(Principal.this, ClasificacionActivity.class);
            	startActivity(EquiposActiviyStart);
                   
            }
        });       
  
        
}
    

}//Fin de la clase