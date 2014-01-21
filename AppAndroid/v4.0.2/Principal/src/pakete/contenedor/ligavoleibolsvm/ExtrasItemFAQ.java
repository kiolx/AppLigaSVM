package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.widget.EditText;

public class ExtrasItemFAQ extends SherlockActivity {
	/** Called when the activity is first created. */
    EditText naamField,mailField ,mobielField,berichtField;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.layout_extras_faq);   
	    setTitle("FAQ");
	    
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
