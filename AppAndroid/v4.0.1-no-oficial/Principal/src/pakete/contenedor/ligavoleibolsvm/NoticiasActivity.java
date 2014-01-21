package pakete.contenedor.ligavoleibolsvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class NoticiasActivity extends SherlockListActivity {
	ArrayList<HashMap<String, String>> notboxList = new ArrayList<HashMap<String, String>>();
	 int[] escudos = new int[]{
		     R.drawable.rfevb,
		     R.drawable.almeria4,
		     R.drawable.teruel4,
		     R.drawable.zaragoza4,
		     R.drawable.cajasol4
		 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_noticias);
		setTitle("Noticias");
		
	    DBHelper dbHelper = new DBHelper(this);
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    
	    Cursor fila = db.rawQuery("SELECT * FROM noticias ORDER BY id_noticia ASC",null);
		//Nos aseguramos de que existe al menos un registro
		if (fila.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		    	    LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
					map.put("autor", fila.getString(1));
					map.put("noticia", fila.getString(2));
					map.put("fecha", fila.getString(3));
					map.put("hora", fila.getString(4));
					map.put("imagen", Integer.toString(devolverRecursoAsociado(fila.getString(1))));

					notboxList.add(map);

		     } while(fila.moveToNext()); }
		  fila.close();
		
	   	   //Close the Database and the Helper
	   	   db.close();
	   	   dbHelper.close();
	   	   
			SimpleAdapter adapter = new SimpleAdapter(NoticiasActivity.this, notboxList,
					R.layout.layout_noticias_items, new String[] { "imagen", "autor", "noticia",  "fecha"},
					new int[] { R.id.list_image_autor, R.id.autorString, R.id.textViewNoticia, R.id.textViewfecha});			

			// updating listview
			setListAdapter(adapter);	
	}	
	
	public int devolverRecursoAsociado(String autor){
		int imagen=0;
		if(autor.equals("RFEVB")) { imagen = escudos[0];}
		if(autor.equals("CVAlmeria")) { imagen = escudos[1];}
		if(autor.equals("CVTeruel")) { imagen = escudos[2];}
		if(autor.equals("CVZaragoza")) { imagen = escudos[3];}
		if(autor.equals("CajasolVoley")) { imagen = escudos[4];}
		return imagen;
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