package pakete.contenedor.ligavoleibolsvm;


import com.actionbarsherlock.app.SherlockListActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;


public class ExtrasMenuActivity extends SherlockListActivity {

    private static class EfficientAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private Bitmap mIcon1;
        private Bitmap mIcon2;

        public EfficientAdapter(Context context) {
            // Cache the LayoutInflate to avoid asking for a new one each time.
            mInflater = LayoutInflater.from(context);

            // Icons bound to the rows.
            mIcon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow_black);
            mIcon2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow_red);
        }

        /**
         * The number of items in the list is determined by the number of speeches
         * in our array.
         *
         * @see android.widget.ListAdapter#getCount()
         */
        public int getCount() {
            return DATA.length;
        }

        /**
         * Since the data comes from an array, just returning the index is
         * sufficent to get at the data. If we were using a more complex data
         * structure, we would return whatever object represents one row in the
         * list.
         *
         * @see android.widget.ListAdapter#getItem(int)
         */
        public Object getItem(int position) {
            return position;
        }

        /**
         * Use the array index as a unique id.
         *
         * @see android.widget.ListAdapter#getItemId(int)
         */
        public long getItemId(int position) {
            return position;
        }

        /**
         * Make a view to hold each row.
         *
         * @see android.widget.ListAdapter#getView(int, android.view.View,
         *      android.view.ViewGroup)
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            // A ViewHolder keeps references to children views to avoid unneccessary calls
            // to findViewById() on each row.
            ViewHolder holder;

            // When convertView is not null, we can reuse it directly, there is no need
            // to reinflate it. We only inflate a new View when the convertView supplied
            // by ListView is null.
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.layout_menu_extras, null);

                // Creates a ViewHolder and store references to the two children views
                // we want to bind data to.
                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.text);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);

                convertView.setTag(holder);
            } else {
                // Get the ViewHolder back to get fast access to the TextView
                // and the ImageView.
                holder = (ViewHolder) convertView.getTag();
            }

            // Bind the data efficiently with the holder.
            holder.text.setText(DATA[position]);
            holder.icon.setImageBitmap((position & 1) == 1 ? mIcon1 : mIcon2);

            return convertView;
        }
        
        

        static class ViewHolder {
            TextView text;
            ImageView icon;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setListAdapter(new EfficientAdapter(this));
        setTitle("SVM 2012/13 - Informacion");
        
		ListView lv = getListView();        
        
        lv.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View childView,int position, long id) {
 
	            	if(position == 0){
	            	Intent ExtrasAcercade = new Intent(ExtrasMenuActivity.this, ExtrasItemInformacion.class);
	            	startActivity(ExtrasAcercade);                             
	            	}
	            	
	            	if(position == 1){
	            	Intent ExtrasSugerencias = new Intent(ExtrasMenuActivity.this, ExtrasItemSugerencias.class);
	            	startActivity(ExtrasSugerencias);                             
	            	}
	            	
	            	if(position == 2){
	            	Intent ExtrasDonaciones = new Intent(ExtrasMenuActivity.this, ExtrasItemDonaciones.class);
	            	startActivity(ExtrasDonaciones);                             
	            	}	            	
	            	
	            	if(position == 3){
	            	Intent BuscarActualizacion = new Intent(ExtrasMenuActivity.this, ExtrasItemActualizaciones.class);
	            	startActivity(BuscarActualizacion);                             
	            	}
	            	
	            	if(position == 4){
	            	Intent Opciones = new Intent(ExtrasMenuActivity.this, ExtrasItemDonaciones.class);
	            	startActivity(Opciones);                             
	            	}
	            }
	          				            
	          }); 
    }

    private static final String[] DATA = OpcionesDisponibles.sOpcionesDispniblesStrings;
}

class OpcionesDisponibles {
    public static final String[] sOpcionesDispniblesStrings = {
             "Acerca de", "Errores/sugerencias", "Donaciones", "Buscar Actualizaciones"
    };

}