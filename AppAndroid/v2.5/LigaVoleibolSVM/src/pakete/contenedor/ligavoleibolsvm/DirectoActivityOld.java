package pakete.contenedor.ligavoleibolsvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockListActivity;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class DirectoActivityOld extends SherlockListActivity {
	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jsonParser = new JSONParser();

	ArrayList<HashMap<String, String>> outboxList;

	// products JSONArray
	JSONArray outbox = null;

	// Outbox JSON url
	private static final String OUTBOX_URL = "http://mobil.cavesquimo.es/directo.php";
	
	// ALL JSON node names
	private static final String TAG_datosdirecto = "datosdirecto";
	private static final String TAG_local = "local";
	private static final String TAG_idimagenlocal = "idimagenlocal";
	private static final String TAG_visitante = "visitante";
	private static final String TAG_idimagenvisitante = "idimagenvisitante";
	private static final String TAG_estadio = "estadio";
	private static final String TAG_fecha = "fecha";
	private static final String TAG_hora = "hora";
	private static final String TAG_idpartido = "idpartido";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directo);
		setTitle("SVM 2012/13 - En directo");
		
		// Hashmap for ListView
        outboxList = new ArrayList<HashMap<String, String>>();
 
        // Loading OUTBOX in Background Thread
        new LoadOutbox().execute();
	}
	
	
    public void ObtenerResultados(String rfevb) {
                 Dialog dialog = new Dialog(DirectoActivityOld.this);
                dialog.setContentView(R.layout.resultados_directo);
                WebView wb = (WebView) dialog.findViewById(R.id.webViewResultados);
                wb.loadUrl("http://mobil.cavesquimo.es/resultados.php?id="+rfevb);
                wb.getSettings().setJavaScriptEnabled(true);
                dialog.setCancelable(true);
                dialog.setTitle("Resultados partido "+ rfevb);
                dialog.show();
    }	
    
    
	   // Array of integers points to images stored in /res/drawable-ldpi/
 int[] escudos = new int[]{
     R.drawable.cajasol,
     R.drawable.almeria,
     R.drawable.teruel,
     R.drawable.soria,
     R.drawable.ibiza,
     R.drawable.vigo,
     R.drawable.lilla,
     R.drawable.zaragoza,
     R.drawable.andorra,
     R.drawable.canaria,
     R.drawable.image_bg
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
			pDialog = new ProgressDialog(DirectoActivityOld.this);
			pDialog.setMessage("Cargando resultados ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting Outbox JSON
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(OUTBOX_URL, "GET",
					params);

			// Check your log cat for JSON reponse
			Log.d("Outbox JSON: ", json.toString());

			try {
				outbox = json.getJSONArray(TAG_datosdirecto);
				// looping through All messages
				int escudo_local =0;
				int escudo_visitante = 0;
				
				for (int i = 0; i < outbox.length(); i++) {
					JSONObject c = outbox.getJSONObject(i);

					// Storing each json item in variable
					String local = c.getString(TAG_local);
					String idimagenlocal = c.getString(TAG_idimagenlocal);
					String visitante = c.getString(TAG_visitante);
					String idimagenvisitante = c.getString(TAG_idimagenvisitante);
					String estadio = c.getString(TAG_estadio);
					String fecha = c.getString(TAG_fecha);
					String hora = c.getString(TAG_hora);
					String idpartido = c.getString(TAG_idpartido);
					
					// creating new HashMap
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();


					if(idimagenlocal.equals("cajasol")) { escudo_local = escudos[0];}
					if(idimagenvisitante.equals("cajasol")) { escudo_visitante = escudos[0];}
					
					if(idimagenlocal.equals("almeria")) { escudo_local = escudos[1];}
					if(idimagenvisitante.equals("almeria")) { escudo_visitante = escudos[1];}
					
					if(idimagenlocal.equals("teruel")) { escudo_local = escudos[2];}
					if(idimagenvisitante.equals("teruel")) { escudo_visitante = escudos[2];}

					if(idimagenlocal.equals("soria")) { escudo_local = escudos[3];}
					if(idimagenvisitante.equals("soria")) { escudo_visitante = escudos[3];}

					if(idimagenlocal.equals("ibiza")) { escudo_local = escudos[4];}
					if(idimagenvisitante.equals("ibiza")) { escudo_visitante = escudos[4];}

					if(idimagenlocal.equals("vigo")) { escudo_local = escudos[5];}
					if(idimagenvisitante.equals("vigo")) { escudo_visitante = escudos[5];}
					
					if(idimagenlocal.equals("lilla")) { escudo_local = escudos[6];}
					if(idimagenvisitante.equals("lilla")) { escudo_visitante = escudos[6];}
	
					if(idimagenlocal.equals("zaragoza")) { escudo_local = escudos[7];}
					if(idimagenvisitante.equals("zaragoza")) { escudo_visitante = escudos[7];}
					
					if(idimagenlocal.equals("andorra")) { escudo_local = escudos[8];}
					if(idimagenvisitante.equals("andorra")) { escudo_visitante = escudos[8];}
					
					if(idimagenlocal.equals("canaria")) { escudo_local = escudos[9];}
					if(idimagenvisitante.equals("canaria")) { escudo_visitante = escudos[9];}
					
					// adding each child node to HashMap key => value
					map.put(TAG_local, local);
					map.put(TAG_idimagenlocal, Integer.toString(escudo_local));
					map.put(TAG_visitante, visitante);
					map.put(TAG_idimagenvisitante, Integer.toString(escudo_visitante));
					map.put(TAG_estadio, estadio);
					map.put(TAG_fecha, fecha);
					map.put(TAG_hora, hora);
					map.put(TAG_idpartido, idpartido);

					// adding HashList to ArrayList
					outboxList.add(map);
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
							DirectoActivityOld.this, outboxList,
							R.layout.directo_items, new String[] { TAG_estadio, TAG_fecha, TAG_hora, TAG_local,  TAG_visitante, TAG_idimagenlocal, TAG_idimagenvisitante},
							new int[] { R.id.estadioString, R.id.fechaString, R.id.horaString, R.id.equipolocalString
									, R.id.equipovisitanteString, R.id.list_image_local, R.id.list_image_visitante});			
					

					// updating listview
					setListAdapter(adapter);			
					
					//Al hacer click en un elemento de la lista, enviar el link rfevb 
					ListView lv = getListView();
			        /* lv.setAdapter(adapter); funciona sin esto bien*/ 						
					
					
			        lv.setOnItemClickListener(new OnItemClickListener() {
				            public void onItemClick(AdapterView<?> parent, View childView,int position, long id) {

				            	//Metemos en la cadena todo el array que contiene cada ListView
				            	//eejmplo {cajasojuvasa, 13-00, CDM los montecilos,...}
				            	String mon =(String) parent.getItemAtPosition(position).toString();
				            	//Separamos la cadena por cada, 
				            	String mon2[] = mon.split(",");
				                 //Seleccionamos el 7, pero no el 7 del Arraydapter, sino el elemento 7
				                 //empezando desde 0, contando en el directo_items.xml, que es el id del partido	
				            	//y le quitamos el idpartido= y despues el } del final
				            	String mon3 = mon2[7].replaceAll(" idpartido=", "");
				            	//Reemplazamos caracter, con cadena da error
				            	String mon4 = mon3.replace("}","");
				            	
                               ObtenerResultados(mon4);//LLamamos a la funcion para mostrar los resultados en la pantalla
                               
				            }
				            				            
				          });   
			        
			        
			        

			               
			       }//Fin del public void run
			});//Fin del runnable
						
		}//FIN del proexecute
	}//Fin class Load outbox
	
	
	
}