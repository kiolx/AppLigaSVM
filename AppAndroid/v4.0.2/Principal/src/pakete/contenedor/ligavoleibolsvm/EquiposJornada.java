package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EquiposJornada extends SherlockFragment {
 
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
		View view = inflater.inflate(R.layout.layout_equipos_detalles_jornada, container, false);		
		
		
	    TextView textoHistoria =(TextView)view.findViewById(R.id.textViewTextoHistoria);
	    if(cadenaLLegada.equals("Andorra")){ textoHistoria.setText(R.string.ResumenAndorra); }
	    if(cadenaLLegada.equals("Zaragoza")){ textoHistoria.setText(R.string.ResumenZaragoza); }
	    if(cadenaLLegada.equals("Teruel")){ textoHistoria.setText(R.string.ResumenTeruel); }

	    if(cadenaLLegada.equals("Cajasol")){ textoHistoria.setText(R.string.ResumenCajasol); }
	    if(cadenaLLegada.equals("Vigo")){ textoHistoria.setText(R.string.ResumenVigo);  }
	    if(cadenaLLegada.equals("Soria")){ textoHistoria.setText(R.string.ResumenSoria);  }

	    if(cadenaLLegada.equals("Lilla")){ textoHistoria.setText(R.string.ResumenLilla); }
	    if(cadenaLLegada.equals("Almeria")){ textoHistoria.setText(R.string.ResumenAlmeria);}
	    if(cadenaLLegada.equals("Ibiza")){ textoHistoria.setText(R.string.ResumenIbiza); }

	    if(cadenaLLegada.equals("Vecindario")){ textoHistoria.setText(R.string.ResumenCanaria);}
		
		return view;   	
    }
	
        
    

}//Fin de la clase