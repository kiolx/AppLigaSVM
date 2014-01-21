package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ExtrasItemInformacion extends SherlockActivity {

	TextView txtNombre;
	TextView txtVersion;
	TextView txtEmail;
	TextView txtUrl;
	TextView txtFuentedatos;
	TextView txtNotafinal;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.AppTheme);	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_extras_acercade);
        setTitle("Acerca de");
		
		txtNombre = (TextView) findViewById(R.id.nombre);
		txtVersion = (TextView) findViewById(R.id.versionapp);
		txtEmail = (TextView) findViewById(R.id.email);
		txtUrl = (TextView) findViewById(R.id.url);
		txtFuentedatos = (TextView) findViewById(R.id.fuentedatos);
		txtNotafinal = (TextView) findViewById(R.id.notafinal);
		
	    DBHelper dbHelper = new DBHelper(ExtrasItemInformacion.this);
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    
		Cursor fila = db.rawQuery("SELECT nombre,email,url,fuentedatos,notafinal FROM contacto WHERE id_contacto='1'",null);
		//Nos aseguramos de que existe al menos un registro
		if (fila.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		 		// displaying all data in textview
		 		txtNombre.setText(fila.getString(0));
		 		txtEmail.setText("Email: " + fila.getString(1));
		 		txtUrl.setText("Url: " + fila.getString(2));
		 		txtFuentedatos.setText("Datos: " + fila.getString(3));
		 		txtNotafinal.setText("Nota: " + fila.getString(4));
		 		txtVersion.setText("Version: "+this.getString(R.string.appVersion));
		     } while(fila.moveToNext()); }
		  fila.close();
		  
	   	   //Close the Database and the Helper
	   	   db.close();
	   	   dbHelper.close();
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

}
