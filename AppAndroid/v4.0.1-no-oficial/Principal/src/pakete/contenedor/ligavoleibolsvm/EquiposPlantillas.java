package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class EquiposPlantillas extends SherlockFragment {
 
	public static String cadenaLLegada = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);   
	
	Bundle temp = this.getArguments();
	cadenaLLegada = temp.getString("cadenaEnviar");
	}	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState)
	{		
		View view = inflater.inflate(R.layout.layout_equipos_detalles_plantillas, container, false);		
		
	     GridView gv = (GridView)view.findViewById(R.id.gridViewPlantilla);
	        gv.setAdapter(new ImageAdapter(view.getContext(), cadenaLLegada));
	        
	        gv.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {	
	            /*	Integer[] imagenesTemp = null;
	            	
	        	    if(cadenaLLegada.equals("Andorra")){ imagenesTemp = andorraIds; }
	        	    if(cadenaLLegada.equals("Zaragoza")){ imagenesTemp = zaragozaIds; }
	        	    if(cadenaLLegada.equals("Teruel")){ imagenesTemp = teruelIds; }

	        	    if(cadenaLLegada.equals("Cajasol")){ imagenesTemp = cajasolIds; }
	        	    if(cadenaLLegada.equals("Vigo")){ imagenesTemp = vigoIds; }
	        	    if(cadenaLLegada.equals("Soria")){ imagenesTemp = soriaIds; }

	        	    if(cadenaLLegada.equals("Lilla")){ imagenesTemp = lillaIds;}
	        	    if(cadenaLLegada.equals("Almeria")){ imagenesTemp = almeriaIds; }
	        	    if(cadenaLLegada.equals("Ibiza")){ imagenesTemp = ibizaIds; }

	        	    if(cadenaLLegada.equals("Vecindario")){ imagenesTemp = canariaIds; }
	            	
	                Dialog dialog = new Dialog(getActivity());
	                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	                dialog.setContentView(R.layout.layout_equipos_detalles_plantillas_jugador);
	                ImageView image = (ImageView) dialog.findViewById(R.id.ImgPlantillaJugador);
	                image.setImageResource(imagenesTemp[position]);
	                dialog.setCanceledOnTouchOutside(true);	                
	                dialog.show();*/
	            }
	        });
	        
		return view;   	
    }
	
 /*   // references to our images
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
    };       */ 
    

}//Fin de la clase