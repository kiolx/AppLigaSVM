package pakete.contenedor.ligavoleibolsvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DirectoActivity extends SherlockListActivity {
	
	ArrayList<HashMap<String, String>> outboxList = new ArrayList<HashMap<String, String>>();
	 int[] escudos = new int[]{
		     R.drawable.cajasol4,  R.drawable.almeria4,  R.drawable.teruel4,  R.drawable.soria4,
		     R.drawable.ibiza4, R.drawable.vigo4,  R.drawable.lilla4,   R.drawable.zaragoza4,
		     R.drawable.andorra4,  R.drawable.canaria4,   R.drawable.image_bg
		 };
	 public Vector<String> vectorsito = new Vector<String>();
	 String jornada = null;
	 String jornadaactual = "9";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_directo);
		setTitle("En directo");
		
        //Recibimos los datos que nos llegan del putextra del DirectoActivity
        Bundle extras = getIntent().getExtras();
        String cadenaLLegada1 = extras.getString("cadenaEnviar");
		TextView textViewJornada = (TextView)findViewById(R.id.textViewJornada);
		 
	    DBHelper dbHelper = new DBHelper(DirectoActivity.this);
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    
		Cursor fila2 = db.rawQuery("SELECT * FROM opciones ORDER BY id_opcion ASC",null);
		//Nos aseguramos de que existe al menos un registro
		if (fila2.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		    	
		    	 jornadaactual = fila2.getString(4);
		    	 
		     } while(fila2.moveToNext()); }
		  fila2.close();
	    
	    Cursor fila = null;//inicializamos la variable
		if(cadenaLLegada1.equals("ultima")){fila = db.rawQuery("SELECT * FROM partidos WHERE jornada='"+jornadaactual+"' ORDER BY id_partido ASC",null);}else{fila = db.rawQuery("SELECT * FROM partidos WHERE jornada='"+cadenaLLegada1+"' ORDER BY id_partido ASC",null);}
		//Nos aseguramos de que existe al menos un registro
		if (fila.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		    	    LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
					map.put("local", fila.getString(1));
					map.put("imagenlocal", Integer.toString(devolverRecursoAsociado(fila.getString(2))));
					map.put("visitante", fila.getString(3));
					map.put("imagenvisitante", Integer.toString(devolverRecursoAsociado(fila.getString(4))));
					map.put("estadio", fila.getString(5));
					map.put("fecha", fila.getString(6));
					map.put("hora", fila.getString(7));
					map.put("rfevb", fila.getString(8));
					map.put("streaming", fila.getString(9));
					map.put("jornada", fila.getString(10));

					outboxList.add(map);
					vectorsito.add(fila.getString(8));//enviamos todos los ids de los partidos en un vector para el boton de todos los partidos
					textViewJornada.setText("Jornada "+fila.getString(10));
					jornada = fila.getString(10);//metemos en jornada la variable jornada para pasarlo al boton atras

		     } while(fila.moveToNext()); }
		  fila.close();
		
	   	   //Close the Database and the Helper
	   	   db.close();
	   	   dbHelper.close();
		
	
	   	//Actualizamos el listVIEW
		SimpleAdapter adapter = new SimpleAdapter(
				DirectoActivity.this, outboxList,
				R.layout.layout_directo_items, new String[] { "estadio", "fecha", "hora", "local", "visitante", "imagenlocal", "imagenvisitante"},
				new int[] { R.id.estadioString, R.id.fechaString, R.id.horaString, R.id.equipolocalString, R.id.equipovisitanteString, R.id.list_image_local, R.id.list_image_visitante});	
	   
        //Boton ver todos los partidos a la vez
        final Button btnAddMore = new Button(DirectoActivity.this);
        btnAddMore.setText("Ver todos los partidos");
        btnAddMore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            	Intent DirectoTodosActiviyStart = new Intent(DirectoActivity.this, DirectoTodosActivity.class);
            	DirectoTodosActiviyStart.putExtra("cadenaEnviar", vectorsito.toString());
            	startActivity(DirectoTodosActiviyStart);
                   
            }
        });
        ListView exArticlesList = (ListView)findViewById(android.R.id.list);
        exArticlesList.addFooterView(btnAddMore);
		
		setListAdapter(adapter);//Parece ser que tenemos que agregar el boton antes de actualizar el listView	
		
		ListView lv = getListView();	
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View childView,int position, long id) {

        	String cadenaEnviarDetalles =(String) parent.getItemAtPosition(position).toString();
              
        	Intent mostrarDetalles = new Intent(DirectoActivity.this, DirectoDetallesActivity.class);
        	mostrarDetalles.putExtra("cadenaEnviar", cadenaEnviarDetalles);
        	startActivity(mostrarDetalles);
               
            }
            				            
          }); 
        //Fin de actualizar el LIST VIEW
        
        //Botones de atras
        ImageButton flechaAtras =(ImageButton)findViewById(R.id.flechaatras);
        flechaAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	finish();
            	int valor = Integer.valueOf(jornada)-1;	if(valor<1){valor =1;}//como no existen jornadas menores a 1 dejamso el minimo en eso
            	startActivity(getIntent().putExtra("cadenaEnviar", String.valueOf(valor)));
 
            }
        });
        
        //Botones de adelante
        ImageButton flechaAlante =(ImageButton)findViewById(R.id.flechaadelante);
        flechaAlante.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	finish();
            	int valor = Integer.valueOf(jornada)+1;	if(valor>18){valor =18;}//como no existen jornadas mayores a 18, lo dejamos en 18
            	startActivity(getIntent().putExtra("cadenaEnviar", String.valueOf(valor)));
 
            }
        });
        

	}	
	        
	        public int devolverRecursoAsociado(String idimagen){
				int recurso_escudo =0;

				if(idimagen.equals("cajasol")) { recurso_escudo = escudos[0];}
				if(idimagen.equals("almeria")) { recurso_escudo = escudos[1];}
				if(idimagen.equals("teruel")) { recurso_escudo = escudos[2];}
				if(idimagen.equals("soria")) { recurso_escudo = escudos[3];}
				if(idimagen.equals("ibiza")) { recurso_escudo = escudos[4];}
				if(idimagen.equals("vigo")) { recurso_escudo = escudos[5];}
				if(idimagen.equals("lilla")) { recurso_escudo = escudos[6];}
				if(idimagen.equals("zaragoza")) { recurso_escudo = escudos[7];}
				if(idimagen.equals("andorra")) { recurso_escudo = escudos[8];}
				if(idimagen.equals("canaria")) { recurso_escudo = escudos[9];}	
	        	
				return recurso_escudo;
	        	
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