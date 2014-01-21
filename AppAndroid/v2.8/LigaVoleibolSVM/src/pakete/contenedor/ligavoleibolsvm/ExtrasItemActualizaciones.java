package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;
import com.loopj.android.http.*;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ExtrasItemActualizaciones extends SherlockActivity {
	TextView percent,texto1,texto2,texto4;
	ProgressBar barra;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.layout_extras_actualizaciones);  
	    setTitle("SVM 2012/13 - Actualizaciones");
	    
	   texto1 = (TextView)findViewById(R.id.textViewTextoActualizaciones1);
	   texto2 = (TextView)findViewById(R.id.textViewTextoActualizaciones2);
	   texto4 = (TextView)findViewById(R.id.textViewTextoActualizaciones4);
       percent = (TextView)findViewById(R.id.textViewTextoActualizaciones3);
       barra = (ProgressBar)findViewById(R.id.progressBar1);
       
       AsyncHttpClient client = new AsyncHttpClient();
       percent.setText("50 %");
       client.get("http://mobil.cavesquimo.es/version.txt", new AsyncHttpResponseHandler() {
           @Override
           public void onSuccess(String response) {
        	       
        	   if(!response.equals(getString(R.string.appVersion))){
        	   barra.setVisibility(View.GONE);
        	   texto1.setText("Se ha encontrado una nueva");
        	   texto2.setText("version");
               percent.setText("Version más reciente: " + response);
               texto4.setText("Version actual: "+ getString(R.string.appVersion));
               texto4.setVisibility(View.VISIBLE);
        	   }else{
            	   barra.setVisibility(View.GONE);
            	   texto1.setText("No se han encontrado");
            	   texto2.setText("actualizaciones");
                   percent.setText("Version más reciente: " + response);
                   texto4.setText("Version actual: "+ getString(R.string.appVersion));
                   texto4.setVisibility(View.VISIBLE);       		    
        	   }

           }
       });
      
	}

		

}
