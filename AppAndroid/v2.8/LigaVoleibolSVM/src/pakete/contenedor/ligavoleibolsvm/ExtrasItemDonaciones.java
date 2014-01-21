package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;

public class ExtrasItemDonaciones extends SherlockActivity {
	/** Called when the activity is first created. */
    EditText naamField,mailField ,mobielField,berichtField;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.layout_extras_donaciones);   
	    setTitle("SVM 2012/13 - Donaciones");
	    
        WebView wb = (WebView) findViewById(R.id.webViewDonaciones);
        wb.loadUrl("http://mobil.cavesquimo.es/donativos/formulario.php");
        wb.getSettings().setJavaScriptEnabled(true);
	}


}
