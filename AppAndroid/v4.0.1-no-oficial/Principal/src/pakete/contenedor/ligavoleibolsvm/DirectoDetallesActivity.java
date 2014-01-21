package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class DirectoDetallesActivity extends SherlockActivity {
	public String jornada = "";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_streaming_videos); 
        setTitle("Resultados");
        
        //Recibimos los datos que nos llegan del putextra del DirectoActivity
        Bundle extras = getIntent().getExtras();
        String cadenaLLegada = extras.getString("cadenaEnviar");
        
        final String [] cadenaenarray = cadena_a_dividir(cadenaLLegada);
        
        jornada = cadenaenarray[9];
        
        WebView wbResultados = (WebView) findViewById(R.id.webViewResultadosDirecto);
        wbResultados.loadUrl("http://mobil.cavesquimo.es/appsvm.php?func=mostrarResultadosDirecto&idpartido="+cadenaenarray[7]);
        wbResultados.getSettings().setJavaScriptEnabled(true); 
        
        TextView local = (TextView) findViewById(R.id.equipolocalString);
        TextView visitante = (TextView) findViewById(R.id.equipovisitanteString);
        TextView hora = (TextView) findViewById(R.id.horaString);
        TextView fecha = (TextView) findViewById(R.id.fechaString);
        TextView estadio= (TextView) findViewById(R.id.estadioString);
        ImageView imaglocal = (ImageView) findViewById(R.id.list_image_local);
        ImageView imagvisit = (ImageView) findViewById(R.id.list_image_visitante);
        
        local.setText(cadenaenarray[0]);
        visitante.setText(cadenaenarray[2]);
        hora.setText(cadenaenarray[6]);
        fecha.setText(cadenaenarray[5]);
        estadio.setText(cadenaenarray[4]);
        imaglocal.setBackgroundResource(Integer.parseInt(cadenaenarray[1]));
        imagvisit.setBackgroundResource(Integer.parseInt(cadenaenarray[3]));

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
        	onBackPressed();
        }
        return true;
    }
    
  
public String[] cadena_a_dividir (String cadenaLLegada) {

	String mon1 = cadenaLLegada.replace("}",""); //quitamos el final
	String mon2 = mon1.replace("{",""); //quitamos el principio

    //Reescribimos valores de la cadena para pasarla limpia
	String mon3[] = mon2.split(",");
	mon3[0] = mon3[0].replaceAll("local=", "");
	mon3[1] = mon3[1].replaceAll(" imagenlocal=", "");
	mon3[2] = mon3[2].replaceAll(" visitante=", "");
	mon3[3] = mon3[3].replaceAll(" imagenvisitante=", "");
	mon3[4] = mon3[4].replaceAll(" estadio=", "");
	mon3[5] = mon3[5].replaceAll(" fecha=", "");
	mon3[6] = mon3[6].replaceAll(" hora=", "");
	mon3[7] = mon3[7].replaceAll(" rfevb=", "");
	mon3[8] = mon3[8].replaceAll(" streaming=", "");
	mon3[9] = mon3[9].replaceAll(" jornada=", "");
		
  	return mon3;
}

}