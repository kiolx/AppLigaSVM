package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class EquiposDetallesActivity extends SherlockActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Sherlock___Theme_DarkActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_equipos_detalles); 
        setTitle("Equipos"); 
        
        //Recibimos los datos que nos llegan del putextra del DirectoActivity
        Bundle extras = getIntent().getExtras();
        String cadenaLLegada = extras.getString("cadenaEnviar");
        
        TextView titleHistoria =(TextView)findViewById(R.id.textViewTituloResultados);
        TextView textoHistoria =(TextView)findViewById(R.id.textViewTextoHistoria);
        if(cadenaLLegada.equals("Andorra")){ textoHistoria.setText(R.string.ResumenAndorra);  titleHistoria.setText("CV Andorra"); }
        if(cadenaLLegada.equals("Zaragoza")){ textoHistoria.setText(R.string.ResumenZaragoza);  titleHistoria.setText("CV Zaragoza"); }
        if(cadenaLLegada.equals("Teruel")){ textoHistoria.setText(R.string.ResumenTeruel);  titleHistoria.setText("CAI Voleibol Teruel"); }
    
        if(cadenaLLegada.equals("Cajasol")){ textoHistoria.setText(R.string.ResumenCajasol);  titleHistoria.setText("Cajasol Juvasa"); }
        if(cadenaLLegada.equals("Vigo")){ textoHistoria.setText(R.string.ResumenVigo);  titleHistoria.setText("Club Vigo Voleibol"); }
        if(cadenaLLegada.equals("Soria")){ textoHistoria.setText(R.string.ResumenSoria);  titleHistoria.setText("CMA Soria Numancia"); }
 
        if(cadenaLLegada.equals("Lilla")){ textoHistoria.setText(R.string.ResumenLilla);  titleHistoria.setText("Ube L'illa Grau"); }
        if(cadenaLLegada.equals("Almeria")){ textoHistoria.setText(R.string.ResumenAlmeria);  titleHistoria.setText("Unicaja Almeria"); }
        if(cadenaLLegada.equals("Ibiza")){ textoHistoria.setText(R.string.ResumenIbiza);  titleHistoria.setText("Ushuaïa Ibiza Voley"); }
    
        if(cadenaLLegada.equals("Vecindario")){ textoHistoria.setText(R.string.ResumenCanaria);  titleHistoria.setText("Vecindario Ace Gran Canaria"); }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btnActualizar) {
        	finish();
        	startActivity(getIntent());
        } else if (item.getItemId() == R.id.btnBack) {
        	startActivity(new Intent(this, EquiposActivity.class));
        }
        return true;
    }
    
    @Override
    public void onBackPressed() {
    	startActivity(new Intent(this, EquiposActivity.class));
    }
    
    

}//Fin de la clase