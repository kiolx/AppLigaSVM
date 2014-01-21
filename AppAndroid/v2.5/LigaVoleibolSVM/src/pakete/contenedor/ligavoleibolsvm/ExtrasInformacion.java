package pakete.contenedor.ligavoleibolsvm;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ExtrasInformacion extends SherlockActivity {
	// All xml labels
	TextView txtNombre;
	TextView txtEmail;
	TextView txtUrl;
	TextView txtFuentedatos;
	TextView txtNotafinal;
	
	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jsonParser = new JSONParser();
	
	// Profile json object
	JSONObject profile;
	
	// Profile JSON url
	private static final String PROFILE_URL = "http://mobil.cavesquimo.es/contacto.php";
	
	// ALL JSON node names
	private static final String TAG_datoscontacto = "datoscontacto";
	private static final String TAG_nombre = "nombre";
	private static final String TAG_email = "email";
	private static final String TAG_url = "url";
	private static final String TAG_fuentedatos = "fuentedatos";
	private static final String TAG_notafinal = "notafinal";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.AppTheme);	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.extras_acercade);
        setTitle("SVM 2012/13 - Acerca de");
		
		txtNombre = (TextView) findViewById(R.id.nombre);
		txtEmail = (TextView) findViewById(R.id.email);
		txtUrl = (TextView) findViewById(R.id.url);
		txtFuentedatos = (TextView) findViewById(R.id.fuentedatos);
		txtNotafinal = (TextView) findViewById(R.id.notafinal);
		
        // Loading Profile in Background Thread
        new LoadProfile().execute();
	}

	/**
	 * Background Async Task to Load profile by making HTTP Request
	 * */
	class LoadProfile extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ExtrasInformacion.this);
			pDialog.setMessage("Cargando datos contacto ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting Profile JSON
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(PROFILE_URL, "GET", params);

			// Check your log cat for JSON reponse
			Log.d("Profile JSON: ", json.toString());

			try {
				// profile json object
				profile = json.getJSONObject(TAG_datoscontacto);
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
					// Storing each json item in variable
					try {
						String name = profile.getString(TAG_nombre);
						String email = profile.getString(TAG_email);
						String url = profile.getString(TAG_url);
						String datos = profile.getString(TAG_fuentedatos);
						String notafinal = profile.getString(TAG_notafinal);
						
						// displaying all data in textview
						txtNombre.setText(name);
						txtEmail.setText("Email: " + email);
						txtUrl.setText("Url: " + url);
						txtFuentedatos.setText("Datos: " + datos);
						txtNotafinal.setText("Nota: " + notafinal);
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});

		}

	}
}
