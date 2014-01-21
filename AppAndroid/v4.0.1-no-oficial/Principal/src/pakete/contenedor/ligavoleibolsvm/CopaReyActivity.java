package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;

public class CopaReyActivity extends SherlockActivity {
	/** Called when the activity is first created. */
    EditText naamField,mailField ,mobielField,berichtField;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.layout_coparey);   
	    setTitle("Copa del Rey");
	    
        WebView wb = (WebView) findViewById(R.id.webViewCopaRey);
        wb.loadUrl("http://mobil.cavesquimo.es/appsvm.php?func=copaRey");
        wb.getSettings().setJavaScriptEnabled(true);
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
