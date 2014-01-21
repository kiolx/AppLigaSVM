package pakete.contenedor.ligavoleibolsvm;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;


public class ClasificacionActivity extends SherlockActivity {
	public String urlsepteto = ""; public String urlmvp=""; public String textomvpStr = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Sherlock___Theme_DarkActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_clasificacion);
		setTitle("Clasificacion");

       TableLayout tl = (TableLayout)findViewById(R.id.tablaClasificaciones);
		
	    DBHelper dbHelper = new DBHelper(this);
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    
		Cursor fila = db.rawQuery("SELECT * FROM clasificacion ORDER BY id_clasificacion ASC",null);
		int contador = 0;
		//Nos aseguramos de que existe al menos un registro
		if (fila.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
                   /* Crear una nueva fila dodne añadimos despues en el otro bucle las columnas con sus datos. */
	               TableRow tr = new TableRow(ClasificacionActivity.this);
	               tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	
						  //Agregamos la informacion de las columnas puesto
			              TextView t1 = new TextView(ClasificacionActivity.this);
			              t1.setTextColor((Color.parseColor("#000000")));
			              if(contador<4){t1.setBackgroundColor((Color.parseColor("#6fb3d0")));}else{t1.setBackgroundColor((Color.parseColor("#e8edad")));}
			              t1.setText(fila.getString(1));
			              t1.setTextSize(12);
			              t1.setTypeface(null, Typeface.BOLD);
			              t1.setGravity(Gravity.CENTER);
		       		      tr.addView(t1);	
		       		      
						  //Agregamos la informacion de las columnas equipo
			              TextView t2 = new TextView(ClasificacionActivity.this);
			              t2.setTextColor((Color.parseColor("#000000")));
			              if(contador<4){t2.setBackgroundColor((Color.parseColor("#6fb3d0")));}else{t2.setBackgroundColor((Color.parseColor("#e8edad")));}
			              t2.setText(fila.getString(2));
			              t2.setTextSize(12);
			              t2.setTypeface(null, Typeface.BOLD);
		       		      tr.addView(t2);	
		       		      
						  //Agregamos la informacion de las columnas partidos jugados
			              TextView t3 = new TextView(ClasificacionActivity.this);
			              t3.setTextColor((Color.parseColor("#000000")));
			              if(contador<4){t3.setBackgroundColor((Color.parseColor("#6fb3d0")));}else{t3.setBackgroundColor((Color.parseColor("#e8edad")));}
			              t3.setText(fila.getString(3));
			              t3.setTextSize(12);
			              t3.setTypeface(null, Typeface.BOLD);
			              t3.setGravity(Gravity.CENTER);
		       		      tr.addView(t3);
		       		      
						  //Agregamos la informacion de las columnas puntos ganados
			              TextView t4 = new TextView(ClasificacionActivity.this);
			              t4.setTextColor((Color.parseColor("#000000")));
			              if(contador<4){t4.setBackgroundColor((Color.parseColor("#6fb3d0")));}else{t4.setBackgroundColor((Color.parseColor("#e8edad")));}
			              t4.setText(fila.getString(4));
			              t4.setTextSize(12);
			              t4.setTypeface(null, Typeface.BOLD);
			              t4.setGravity(Gravity.CENTER);
		       		      tr.addView(t4);	
	               
                
	          /* Insertamos la fila con los 4 valores antes seleccioandos. */
	          tl.addView(tr,new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
	          
	          //Insertamos el separador horizontal
	          View v = new View(ClasificacionActivity.this);
	          RelativeLayout.LayoutParams viewLp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1);
	          viewLp.addRule(RelativeLayout.BELOW, 5);
	          viewLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
	          viewLp.setMargins(0, 3, 0, 0);

	          v.setLayoutParams(viewLp);
	          v.setBackgroundColor(0x808080);

	          tl.addView(v);
	          contador++;

		     } while(fila.moveToNext()); }
		
		   fila.close();
	   	   db.close();
	   	   dbHelper.close();
	   	   
	   	   descargarImagenAsincronicamente();
		
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
	    
    
    public void descargarImagenAsincronicamente(){
    	AsyncHttpClient client = new AsyncHttpClient();
    	String[] allowedContentTypes = new String[] { "image/png", "image/jpeg", "image/jpg" };
	    DBHelper dbHelper = new DBHelper(this);
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    
		Cursor fila = db.rawQuery("SELECT * FROM mvpysepteto ORDER BY id_mvpsepteto DESC",null);
		//Nos aseguramos de que existe al menos un registro
		if (fila.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {  
		    	 
		    	 urlsepteto = fila.getString(1);
		    	 urlmvp = fila.getString(2);
		    	textomvpStr= fila.getString(3);
		    	 
		     } while(fila.moveToNext()); }
		
		   fila.close();
	   	   db.close();
	   	   dbHelper.close();
	   	   
        client.get(urlsepteto, new BinaryHttpResponseHandler(allowedContentTypes) {
    	    @Override
    	    public void onSuccess(byte[] fileData) {
    	    	try{
    	    	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("data/data/pakete.contenedor.ligavoleibolsvm/SVM/septeto/septeto.jpg"));
    	    	bos.write(fileData);
    	    	bos.flush();
    	    	bos.close();  
                // load image view control    
    	    	Bitmap bitmap = BitmapFactory.decodeFile("data/data/pakete.contenedor.ligavoleibolsvm/SVM/septeto/septeto.jpg");
                ImageView esparto =(ImageView)findViewById(R.id.imageViewFantasmaSepteto);
                esparto.setImageBitmap(bitmap);
                LinearLayout cargadorHueco = (LinearLayout)findViewById(R.id.LinearLayoutCargadorSepteto);
         	    cargadorHueco.setVisibility(View.INVISIBLE);
    	    	}catch(Exception e){}
    	    }
    	});  
    	client.get(urlmvp, new BinaryHttpResponseHandler(allowedContentTypes) {
    	    @Override
    	    public void onSuccess(byte[] fileData) {
    	    	try{
    	    	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("data/data/pakete.contenedor.ligavoleibolsvm/SVM/mvp/mvp.jpg"));
    	    	bos.write(fileData);
    	    	bos.flush();
    	    	bos.close(); 
                // load image view control    
    	    	Bitmap bitmap = BitmapFactory.decodeFile("data/data/pakete.contenedor.ligavoleibolsvm/SVM/mvp/mvp.jpg");
                ImageView esparto =(ImageView)findViewById(R.id.imageViewFantasmaMVP);
                esparto.setImageBitmap(bitmap);
                TextView textomvpview = (TextView)findViewById(R.id.textviewTextoMVP);
                textomvpview.setText(textomvpStr);
                LinearLayout cargadorHueco = (LinearLayout)findViewById(R.id.LinearLayoutCargadorMVP);
         	    cargadorHueco.setVisibility(View.INVISIBLE);
    	       }catch(Exception e){}
    	    }
    	});
  

    }

}
