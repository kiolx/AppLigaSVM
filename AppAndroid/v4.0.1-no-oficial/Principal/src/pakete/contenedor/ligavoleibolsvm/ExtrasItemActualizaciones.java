package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
	    setTitle("Actualizaciones");
	    
	   texto1 = (TextView)findViewById(R.id.textViewTextoActualizaciones1);
	   texto2 = (TextView)findViewById(R.id.textViewTextoActualizaciones2);
	   texto4 = (TextView)findViewById(R.id.textViewTextoActualizaciones4);
       percent = (TextView)findViewById(R.id.textViewTextoActualizaciones3);
       barra = (ProgressBar)findViewById(R.id.progressBar1);
     
       percent.setText("50 %");
 	
       //Inicio de comprobar si hay actualizaicones
	    DBHelper dbHelper = new DBHelper(this);
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    String actualizacion = "";
	    
		Cursor fila = db.rawQuery("SELECT * FROM opciones ORDER BY id_opcion DESC",null);
		//Nos aseguramos de que existe al menos un registro
		if (fila.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
					actualizacion = fila.getString(2);
					
		     } while(fila.moveToNext()); }
		  fila.close();
          db.close();
	   	  dbHelper.close();	
       
        	   if(!actualizacion.equals(getString(R.string.appVersion))){
        	   barra.setVisibility(View.GONE);
        	   texto1.setText("Se ha encontrado una nueva");
        	   texto2.setText("version");
               percent.setText("Version más reciente: " + actualizacion);
               texto4.setText("Version actual: "+ getString(R.string.appVersion));
               texto4.setVisibility(View.VISIBLE);
        	   }else{
            	   barra.setVisibility(View.GONE);
            	   texto1.setText("No se han encontrado");
            	   texto2.setText("actualizaciones");
                   percent.setText("Version más reciente: " + actualizacion);
                   texto4.setText("Version actual: "+ getString(R.string.appVersion));
                   texto4.setVisibility(View.VISIBLE);       		    
        	   }

      
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
