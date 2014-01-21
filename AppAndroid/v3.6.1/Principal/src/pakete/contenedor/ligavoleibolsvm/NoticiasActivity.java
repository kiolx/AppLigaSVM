package pakete.contenedor.ligavoleibolsvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

public class NoticiasActivity extends SherlockListActivity {
	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jsonParser = new JSONParser();

	ArrayList<HashMap<String, String>> notboxList;

	// products JSONArray
	JSONArray notbox = null;

	// Outbox JSON url
	private static final String NOTBOX_URL = "http://mobil.cavesquimo.es/appsvm.php";
	
	// ALL JSON node names
	private static final String TAG_datosnoticias = "datosnoticias";
	private static final String TAG_autor = "autor";
	private static final String TAG_noticia = "noticia";
	private static final String TAG_fecha = "fecha";
	private static final String TAG_hora = "hora";
	private static final String TAG_imagen = "imagen";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_noticias);
		setTitle("Noticias");
		
		// Hashmap for ListView
        notboxList = new ArrayList<HashMap<String, String>>();
 
        // Loading OUTBOX in Background Thread
        new LoadOutbox().execute();
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
        	finish();
        	startActivity(new Intent(this, Principal.class));
        }
        return true;
    }
    
    @Override
    public void onBackPressed() {
    	finish();    	
    	startActivity(new Intent(this, Principal.class));
    }
       
	 int[] escudos = new int[]{
		     R.drawable.rfevb,
		     R.drawable.almeria_twitter,
		     R.drawable.terueltwitter,
		     R.drawable.zaragoza_twitter,
		     R.drawable.cajasol_twitter
		 };
	
    /**
	 * Background Async Task to Load all OUTBOX messages by making HTTP Request
	 * */
	class LoadOutbox extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NoticiasActivity.this);
			pDialog.setMessage("Cargando noticias ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
			
		}

		/**
		 * getting Outbox JSON
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("func", "mostrarTwitter"));
			
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(NOTBOX_URL, "GET",	params);

			// Check your log cat for JSON reponse
			Log.d("Outbox JSON: ", json.toString());

			try {
				notbox = json.getJSONArray(TAG_datosnoticias);
				// looping through All messages
				
				for (int i = 0; i < notbox.length(); i++) {
					JSONObject c = notbox.getJSONObject(i);

					// Storing each json item in variable
					String autor = c.getString(TAG_autor);
					String noticia = c.getString(TAG_noticia);
					String fecha = c.getString(TAG_fecha);
					String hora = c.getString(TAG_hora);
					int imagen =0;
					
					// creating new HashMap
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

					if(autor.equals("RFEVB")) { imagen = escudos[0];}
					if(autor.equals("CVAlmeria")) { imagen = escudos[1];}
					if(autor.equals("CVTeruel")) { imagen = escudos[2];}
					if(autor.equals("CVZaragoza")) { imagen = escudos[3];}
					if(autor.equals("CajasolVoley")) { imagen = escudos[4];}
					
					// adding each child node to HashMap key => value
					map.put(TAG_autor, autor);
					map.put(TAG_noticia, noticia);
					map.put(TAG_fecha, fecha);
					map.put(TAG_hora, hora);
					map.put(TAG_imagen, Integer.toString(imagen));
					// adding HashList to ArrayList
					notboxList.add(map);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
				
					SimpleAdapter adapter = new SimpleAdapter(
							NoticiasActivity.this, notboxList,
							R.layout.layout_noticias_items, new String[] { TAG_imagen, TAG_autor, TAG_noticia,  TAG_fecha},
							new int[] { R.id.list_image_autor, R.id.autorString, R.id.textViewNoticia, R.id.textViewfecha});			
					

					// updating listview
					setListAdapter(adapter);			
					
			               
			       }//Fin del public void run
			});//Fin del runnable
			
			        
						
		}//FIN del proexecute
	}//Fin class Load outbox
	
	
	
}