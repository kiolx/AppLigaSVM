package pakete.contenedor.ligavoleibolsvm;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;


public class ClasificacionActivity extends SherlockActivity {
	// declare internal using controls
	
	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jsonParser = new JSONParser();

	// creating new HashMap intro Arraylist
	ArrayList<HashMap<String, String>> listaMapa;
	
	//Array que te cagas
	public String[] elementos = new String[100];
	public int contador = 0;
	
	//Mapa Linkeado con orden
	LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	
	// products JSONArray
	JSONArray inbox = null;

	// Inbox JSON url
	private static final String INBOX_URL = "http://mobil.cavesquimo.es/appsvm.php";
	
	// ALL JSON node names
	private static final String TAG_datosclasificacion = "datosclasificacion";
	private static final String TAG_puesto = "puesto";
	private static final String TAG_equipo = "equipo";
	private static final String TAG_jornadas = "jornadas";
	private static final String TAG_puntos = "puntos";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Sherlock___Theme_DarkActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_clasificacion);
		setTitle("Clasificacion");
		// Hashmap for ArrayList
        listaMapa = new ArrayList<HashMap<String, String>>();
 
        // Loading INBOX in Background Thread
        new LoadInbox().execute();
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
        	startActivity(new Intent(this, Principal.class));
        }
        return true;
    }
    
    @Override
    public void onBackPressed() {
    	startActivity(new Intent(this, Principal.class));
    }
    	
	
	/**
	 * Background Async Task to Load all INBOX messages by making HTTP Request
	 * */
	class LoadInbox extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ClasificacionActivity.this);
			pDialog.setMessage("Obteniendo clasificacion ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * getting Inbox JSON
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("func", "mostrarClasificacion"));
			
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(INBOX_URL, "GET",	params);

			// Check your log cat for JSON reponse
			Log.d("Inbox JSON: ", json.toString());

			try {
				inbox = json.getJSONArray(TAG_datosclasificacion);
				// looping through All messages
				for (int i = 0; i < inbox.length(); i++) {
					JSONObject c = inbox.getJSONObject(i);

					// Storing each json item in variable
					String puesto = c.getString(TAG_puesto);
					String equipo = c.getString(TAG_equipo);
					String jornadas = c.getString(TAG_jornadas);
					String puntos = c.getString(TAG_puntos);

					
					// adding each child node to HashMap key => value
					map.put(TAG_puesto, puesto);
					map.put(TAG_equipo, equipo);
					map.put(TAG_jornadas, jornadas);
					map.put(TAG_puntos, puntos);
					
					elementos[contador] = puesto; contador++;
					elementos[contador] = equipo; contador++;
					elementos[contador] = jornadas; contador++;
					elementos[contador] = puntos; contador++;			

					// adding HashList to ArrayList
					listaMapa.add(map);
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
					int gruposalto = 0;
                    /* Find Tablelayout defined in main.xml */
			          TableLayout tl = (TableLayout)findViewById(R.id.tablaClasificaciones);
			          
					for(int n=0; n<10 ; n++){    //Diez equipos--10 iteraciones              
					
				               /* Create a new row to be added. */
				               TableRow tr = new TableRow(ClasificacionActivity.this);
				               tr.setLayoutParams(new LayoutParams(
				                              LayoutParams.MATCH_PARENT,
				                              LayoutParams.WRAP_CONTENT));
				               

					
								for (int i = 0; i <4; i++){
									
									  //Agregamos la fila con los datos del array
						              TextView t1 = new TextView(ClasificacionActivity.this);
						              t1.setTextColor((Color.parseColor("#000000")));
						              
						              if(gruposalto<12){ t1.setBackgroundColor((Color.parseColor("#6fb3d0")));
						              }else{ t1.setBackgroundColor((Color.parseColor("#e8edad")));  }
						              
						              t1.setText(elementos[gruposalto]);
						              t1.setTextSize(12);
						              t1.setTypeface(null, Typeface.BOLD);
						              if(i!=1){  t1.setGravity(Gravity.CENTER);  }
						              
		     		       		      tr.addView(t1);
		     		       		      gruposalto++;	
		     		       		      		     		       		      
								}
				               
                              
				          /* Insertamos la fila con los 4 valores antes seleccioandos. */
				          tl.addView(tr,new TableLayout.LayoutParams(
				                    LayoutParams.MATCH_PARENT,
				                    LayoutParams.WRAP_CONTENT));
				          
				          //Insertamos el separador horizontal
				          View v = new View(ClasificacionActivity.this);
				          RelativeLayout.LayoutParams viewLp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1);
				          viewLp.addRule(RelativeLayout.BELOW, 5);
				          viewLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
				          viewLp.setMargins(0, 3, 0, 0);

				          v.setLayoutParams(viewLp);
				          v.setBackgroundColor(0x808080);

				          tl.addView(v);

					}//Cierre del otro bucle
					

				}
			});

	        Handler warro =new Handler();
	        final Runnable r = new Runnable() {
	            public void run() {
			     //cargarImagenFondo();
	            new DownloadImageTask().execute("http://mobil.cavesquimo.es/septeto.png");
	            }
	        };
	        
	        warro.postDelayed(r, 20);
		}

	}
	

    
    class DownloadImageTask extends AsyncTask<String, Void, Drawable>
    {

         final ProgressDialog progressDialog = new ProgressDialog(ClasificacionActivity.this);

           protected void onPreExecute()
           {
               progressDialog.setTitle("");
               progressDialog.setMessage("Cargando imagen...");
               progressDialog.show();
           }

           protected Drawable doInBackground(String... urls)
           {
               Log.d("DEBUG", "drawable");

               return downloadImage(urls[0]);

           }

           protected void onPostExecute(Drawable imagen)
           {
               // load image view control
               ImageView esparto =(ImageView)findViewById(R.id.imageViewFantasma);
               esparto.setImageDrawable(imagen);
               progressDialog.dismiss();
           }

           /**
* Devuelve una imagen desde una URL
* @param url Url de la imagen
* @return Una imagen
*/
           private Drawable downloadImage(String imageUrl)
           {
               try
               {
                   URL url = new URL(imageUrl);
                   InputStream is = (InputStream)url.getContent();
                   return Drawable.createFromStream(is, "src");
               }
               catch (MalformedURLException e)
               {
                   e.printStackTrace();
                   return null;
               }
               catch (IOException e)
               {
                   e.printStackTrace();
                   return null;
               }
           }
    }
    

}
