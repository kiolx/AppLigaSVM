package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

public class DirectoTodosActivity extends SherlockActivity {
	/** Called when the activity is first created. */
    EditText naamField,mailField ,mobielField,berichtField;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.layout_directo_todos);   
	    setTitle("Resultados");
	
        //Recibimos los datos que nos llegan del putextra del DirectoActivity
        Bundle extras = getIntent().getExtras();
        String cadenaLLegada = extras.getString("cadenaEnviar");
        
        final String [] cadenaenarray = cadena_a_dividir(cadenaLLegada);
        
        WebView wbResultados1 = (WebView) findViewById(R.id.webViewEquipo1);
        wbResultados1.loadUrl("http://mobil.cavesquimo.es/resultados.php?id="+ cadenaenarray[0]);
        wbResultados1.getSettings().setJavaScriptEnabled(true);
        
        WebView wbResultados2 = (WebView) findViewById(R.id.webViewEquipo2);
        wbResultados2.loadUrl("http://mobil.cavesquimo.es/resultados.php?id="+ cadenaenarray[1]);
        wbResultados2.getSettings().setJavaScriptEnabled(true);
        
        WebView wbResultados3 = (WebView) findViewById(R.id.webViewEquipo3);
        wbResultados3.loadUrl("http://mobil.cavesquimo.es/resultados.php?id="+ cadenaenarray[2]);
        wbResultados3.getSettings().setJavaScriptEnabled(true);
        
        WebView wbResultados4 = (WebView) findViewById(R.id.webViewEquipo4);
        wbResultados4.loadUrl("http://mobil.cavesquimo.es/resultados.php?id="+ cadenaenarray[3]);
        wbResultados4.getSettings().setJavaScriptEnabled(true);
         
        WebView wbResultados5 = (WebView) findViewById(R.id.webViewEquipo5);
        wbResultados5.loadUrl("http://mobil.cavesquimo.es/resultados.php?id="+ cadenaenarray[4]);
        wbResultados5.getSettings().setJavaScriptEnabled(true);
	}

	public String[] cadena_a_dividir (String cadenaLLegada) {

		String mon1 = cadenaLLegada.replace("[",""); //quitamos el final
		String mon2 = mon1.replace("]",""); //quitamos el principio

		String mon3[] = mon2.split(",");
		mon3[0] = mon3[0].replace(" ","");
		mon3[1] = mon3[1].replace(" ","");	
		mon3[2] = mon3[2].replace(" ","");
		mon3[3] = mon3[3].replace(" ","");
		mon3[4] = mon3[4].replace(" ","");
		
	  	return mon3;
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
        	startActivity(new Intent(this, DirectoActivity.class));
        }
        return true;
    }
    
    @Override
    public void onBackPressed() {
    	startActivity(new Intent(this, DirectoActivity.class));
    }
    

}
