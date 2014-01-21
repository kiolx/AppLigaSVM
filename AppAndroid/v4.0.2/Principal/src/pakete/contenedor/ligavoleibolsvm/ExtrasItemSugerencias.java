package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class ExtrasItemSugerencias extends SherlockActivity {
	/** Called when the activity is first created. */
    EditText naamField,mailField ,mobielField,berichtField;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.layout_extras_sugerencias);   
	    setTitle("Errores/Sugerencias");

	    naamField = (EditText) findViewById(R.id.editTextNaam);
	    mailField = (EditText) findViewById(R.id.editTextMail);
	    mobielField = (EditText) findViewById(R.id.editTextMobiel);       
	    berichtField = (EditText)findViewById(R.id.editTextBericht);       

        final Button buttonEnviar = (Button) findViewById(R.id.buttonSend);
        buttonEnviar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
       
                if(naamField.getText().toString().length()==0)  
   	         {           
   	          naamField.setError( "Nombre requerido" );  
   	         }  
   	         else if(mailField.getText().toString().length()==0)  
   	         {           
   	          mailField.setError( "Email requerido" ); 
   	         }
   	         else if(mobielField.getText().toString().length()==20)  
   	         {           
   	           mobielField.setError( "Modelo telefono requerido" );
   	         }
   	         else if(berichtField.getText().toString().length()==0)  
   	         {           
   	          berichtField.setError( "Comentario requerido" );  
   	         }
   	         else
   	         {  
   	            String body=
   	         "Nombre : "+naamField.getText().toString()+"<br>Modelo telefono :"+mobielField.getText().toString()+
   	          "<br>Email :"+mailField.getText().toString()+"<br>Comentario :"+berichtField.getText().toString();  

   	            Intent email = new Intent(Intent.ACTION_SEND); 
   	            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"soporte@cavesquimo.es"});           
   	            email.putExtra(Intent.EXTRA_SUBJECT, "SugerenciaApp"); 
   	            email.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body)); 
   	            email.setType("message/rfc822");
   	            startActivityForResult(Intent.createChooser(email, "Ooopss!"),1); 
   	         }   
            	
            }
        }); 

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
    
    
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{

	 new AlertDialog.Builder(ExtrasItemSugerencias.this)
	.setMessage("Tu comentario ha sido enviado\nGracias")
	.setCancelable(false)
	.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int which) 
	{
	  dialog.cancel();
	    }
	})  
	    .show();
	}    


}
