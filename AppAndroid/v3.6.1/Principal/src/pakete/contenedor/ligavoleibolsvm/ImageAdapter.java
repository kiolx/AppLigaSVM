package pakete.contenedor.ligavoleibolsvm;

import android.widget.BaseAdapter;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.GridView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	public String cadenaLLegada = "";

    public ImageAdapter(Context c, String cadenaRecibida) {
        mContext = c;
        cadenaLLegada = cadenaRecibida;
    }

    public int getCount() {
    	Integer quenumerodevolver= 0;
  
	    if(cadenaLLegada.equals("Andorra")){ quenumerodevolver = andorraIds.length; }
	    if(cadenaLLegada.equals("Zaragoza")){ quenumerodevolver = zaragozaIds.length; }
	    if(cadenaLLegada.equals("Teruel")){ quenumerodevolver = teruelIds.length; }

	    if(cadenaLLegada.equals("Cajasol")){ quenumerodevolver = cajasolIds.length; }
	    if(cadenaLLegada.equals("Vigo")){ quenumerodevolver = vigoIds.length; }
	    if(cadenaLLegada.equals("Soria")){ quenumerodevolver = soriaIds.length; }

	    if(cadenaLLegada.equals("Lilla")){ quenumerodevolver = lillaIds.length; }
	    if(cadenaLLegada.equals("Almeria")){ quenumerodevolver = almeriaIds.length; }
	    if(cadenaLLegada.equals("Ibiza")){ quenumerodevolver = ibizaIds.length;  }

	    if(cadenaLLegada.equals("Vecindario")){ quenumerodevolver = canariaIds.length; }
    	
    	
        return quenumerodevolver;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(145, 155));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(3, 3, 3, 3);
        } else {
            imageView = (ImageView) convertView;
        }
        
	    if(cadenaLLegada.equals("Andorra")){ imageView.setImageResource(andorraIds[position]); }
	    if(cadenaLLegada.equals("Zaragoza")){ imageView.setImageResource(zaragozaIds[position]); }
	    if(cadenaLLegada.equals("Teruel")){ imageView.setImageResource(teruelIds[position]); }

	    if(cadenaLLegada.equals("Cajasol")){ imageView.setImageResource(cajasolIds[position]); }
	    if(cadenaLLegada.equals("Vigo")){ imageView.setImageResource(vigoIds[position]); }
	    if(cadenaLLegada.equals("Soria")){ imageView.setImageResource(soriaIds[position]); }

	    if(cadenaLLegada.equals("Lilla")){ imageView.setImageResource(lillaIds[position]); }
	    if(cadenaLLegada.equals("Almeria")){ imageView.setImageResource(almeriaIds[position]); }
	    if(cadenaLLegada.equals("Ibiza")){ imageView.setImageResource(ibizaIds[position]); }

	    if(cadenaLLegada.equals("Vecindario")){ imageView.setImageResource(canariaIds[position]); }
        
        return imageView;
    }
    
    // references to our images
    private Integer[] andorraIds = {
            R.drawable.andorra_1, R.drawable.andorra_2,
            R.drawable.andorra_3, R.drawable.andorra_4,
            R.drawable.andorra_5, R.drawable.andorra_6,
            R.drawable.andorra_7, R.drawable.andorra_8,
            R.drawable.andorra_9, R.drawable.andorra_10,
            R.drawable.andorra_11, R.drawable.andorra_12,
            R.drawable.andorra_13, R.drawable.andorra_14,
            R.drawable.andorra_15
    };
    
    private Integer[] zaragozaIds = {
            R.drawable.zaragoza_1, R.drawable.zaragoza_2,
            R.drawable.zaragoza_3, R.drawable.zaragoza_4,
            R.drawable.zaragoza_5, R.drawable.zaragoza_6,
            R.drawable.zaragoza_7, R.drawable.zaragoza_8,
            R.drawable.zaragoza_9, R.drawable.zaragoza_10,
            R.drawable.zaragoza_11, R.drawable.zaragoza_12
    };    
    
    private Integer[] teruelIds = { R.drawable.teruel_0,
            R.drawable.teruel_1, R.drawable.teruel_2,
            R.drawable.teruel_3, R.drawable.teruel_4,
            R.drawable.teruel_5, R.drawable.teruel_6,
            R.drawable.teruel_7, R.drawable.teruel_8,
            R.drawable.teruel_9, R.drawable.teruel_10,
            R.drawable.teruel_11, R.drawable.teruel_12
    }; 

    private Integer[] cajasolIds = { R.drawable.cajasol_0,
            R.drawable.cajasol_1, R.drawable.cajasol_2,
            R.drawable.cajasol_3, R.drawable.cajasol_4,
            R.drawable.cajasol_5, R.drawable.cajasol_6,
            R.drawable.cajasol_7, R.drawable.cajasol_8,
            R.drawable.cajasol_9, R.drawable.cajasol_10,
            R.drawable.cajasol_11, R.drawable.cajasol_12
    };   
    
    private Integer[] vigoIds = {R.drawable.vigo_0,
            R.drawable.vigo_1, R.drawable.vigo_2,
            R.drawable.vigo_3, R.drawable.vigo_4,
            R.drawable.vigo_5, R.drawable.vigo_6,
            R.drawable.vigo_7, R.drawable.vigo_8,
            R.drawable.vigo_9, R.drawable.vigo_10,
            R.drawable.vigo_11, R.drawable.vigo_12
    };    
    
    private Integer[] soriaIds = {
            R.drawable.soria_1, R.drawable.soria_2,
            R.drawable.soria_3, R.drawable.soria_4,
            R.drawable.soria_5, R.drawable.soria_6,
            R.drawable.soria_7, R.drawable.soria_8,
            R.drawable.soria_9, R.drawable.soria_10
    }; 
    
    private Integer[] lillaIds = {
            R.drawable.lilla_1, R.drawable.lilla_2,
            R.drawable.lilla_3, R.drawable.lilla_4,
            R.drawable.lilla_5, R.drawable.lilla_6,
            R.drawable.lilla_7, R.drawable.lilla_8,
            R.drawable.lilla_9, R.drawable.lilla_10,
            R.drawable.lilla_11, R.drawable.lilla_12
    };   
    
    private Integer[] almeriaIds = {
            R.drawable.almeria_1, R.drawable.almeria_2,
            R.drawable.almeria_3, R.drawable.almeria_4,
            R.drawable.almeria_5, R.drawable.almeria_6,
            R.drawable.almeria_7, R.drawable.almeria_8,
            R.drawable.almeria_9, R.drawable.almeria_10,
            R.drawable.almeria_11, R.drawable.almeria_12
    }; 
    
    private Integer[] ibizaIds = { R.drawable.ibiza_0,
            R.drawable.ibiza_1, R.drawable.ibiza_2,
            R.drawable.ibiza_3, R.drawable.ibiza_4,
            R.drawable.ibiza_5, R.drawable.ibiza_6,
            R.drawable.ibiza_7, R.drawable.ibiza_8,
            R.drawable.ibiza_9, R.drawable.ibiza_10,
            R.drawable.ibiza_11, R.drawable.ibiza_12
    };  
    
    private Integer[] canariaIds = {
            R.drawable.canaria_1, R.drawable.canaria_2,
            R.drawable.canaria_3, R.drawable.canaria_4,
            R.drawable.canaria_5, R.drawable.canaria_6,
            R.drawable.canaria_7, R.drawable.canaria_8,
            R.drawable.canaria_9, R.drawable.canaria_10,
            R.drawable.canaria_11, R.drawable.canaria_12,
            R.drawable.canaria_13, R.drawable.canaria_14,
            R.drawable.canaria_15, R.drawable.canaria_16
    }; 
    
}
