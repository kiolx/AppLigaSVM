package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class ExtrasDonaciones extends SherlockActivity {
	/** Called when the activity is first created. */
    EditText naamField,mailField ,mobielField,berichtField;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.extras_donaciones);   
	    setTitle("SVM 2012/13 - Donaciones");
	    
        WebView wb = (WebView) findViewById(R.id.webViewDonaciones);
        wb.loadUrl("http://mobil.cavesquimo.es/donativos/formulario.php");
        wb.getSettings().setJavaScriptEnabled(true);
	}


}
