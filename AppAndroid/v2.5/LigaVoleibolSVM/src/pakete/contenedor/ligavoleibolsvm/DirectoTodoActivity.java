package pakete.contenedor.ligavoleibolsvm;

import com.actionbarsherlock.app.SherlockActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

public class DirectoTodoActivity extends SherlockActivity {
	private VideoView videoStr;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streaming_videos); 
        
        //Recibimos los datos que nos llegan del putextra del DirectoActivity
        Bundle extras = getIntent().getExtras();
        String cadenaLLegada = extras.getString("cadenaEnviar");
        
        final String [] cadenaenarray = cadena_a_dividir(cadenaLLegada);
        
        WebView wbResultados = (WebView) findViewById(R.id.webViewResultadosDirecto);
        wbResultados.loadUrl("http://mobil.cavesquimo.es/resultados.php?id="+cadenaenarray[7]);
        wbResultados.getSettings().setJavaScriptEnabled(true); 
 
        videoStr = (VideoView) findViewById(R.id.videoViewStreaming);
        videoStr.setVideoURI(Uri.parse(cadenaenarray[8]));
        videoStr.setMediaController(new MediaController(DirectoTodoActivity.this));
        videoStr.requestFocus(); 
        
        
       if(cadenaenarray[8]==""){ 
    	  //Cargamos aqui un layout que diga que no hay ningun video disponible
       	   View placeholder = (View) findViewById(R.id.placeholder);   
	       placeholder.setVisibility(View.GONE);
    	   videoStr.setBackgroundResource(R.drawable.notfound);
       }else{ 
    	   
       videoStr.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
       public void onPrepared(MediaPlayer arg0) {
               videoStr.start(); 
               if(videoStr.isPlaying()){
           	   View placeholder = (View) findViewById(R.id.placeholder);   
    	       placeholder.setVisibility(View.GONE);
               }
        }  });
        
        }//cierre del if
 
        
    }
    
  
public String[] cadena_a_dividir (String cadenaLLegada) {

	String mon1 = cadenaLLegada.replace("}",""); //quitamos el final
	String mon2 = mon1.replace("{",""); //quitamos el principio

    //Reescribimos valores de la cadena para pasarla limpia
	String mon3[] = mon2.split(",");
	mon3[0] = mon3[0].replaceAll(" local=", "");
	mon3[1] = mon3[1].replaceAll(" idimagenlocal=", "");
	mon3[2] = mon3[2].replaceAll(" visitante=", "");
	mon3[3] = mon3[3].replaceAll(" idimagenvisitante=", "");
	mon3[4] = mon3[4].replaceAll(" estadio=", "");
	mon3[5] = mon3[5].replaceAll(" fecha=", "");
	mon3[6] = mon3[6].replaceAll(" hora=", "");
	mon3[7] = mon3[7].replaceAll(" idpartido=", "");
	mon3[8] = mon3[8].replaceAll(" streaming=", "");
	
  	return mon3;
}

}