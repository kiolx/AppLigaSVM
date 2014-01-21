package pakete.contenedor.ligavoleibolsvm;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.actionbarsherlock.app.SherlockActivity;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

public class DirectoDetallesActivity extends SherlockActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_streaming_videos); 
        
        //Recibimos los datos que nos llegan del putextra del DirectoActivity
        Bundle extras = getIntent().getExtras();
        String cadenaLLegada = extras.getString("cadenaEnviar");
        
        final String [] cadenaenarray = cadena_a_dividir(cadenaLLegada);
        
        WebView wbResultados = (WebView) findViewById(R.id.webViewResultadosDirecto);
        wbResultados.loadUrl("http://mobil.cavesquimo.es/resultados.php?id="+cadenaenarray[7]);
        wbResultados.getSettings().setJavaScriptEnabled(true); 
        
        TextView local = (TextView) findViewById(R.id.equipolocalString);
        TextView visitante = (TextView) findViewById(R.id.equipovisitanteString);
        TextView hora = (TextView) findViewById(R.id.horaString);
        TextView fecha = (TextView) findViewById(R.id.fechaString);
        TextView estadio= (TextView) findViewById(R.id.estadioString);
        ImageView imaglocal = (ImageView) findViewById(R.id.list_image_local);
        ImageView imagvisit = (ImageView) findViewById(R.id.list_image_visitante);
        
        local.setText(cadenaenarray[0]);
        visitante.setText(cadenaenarray[2]);
        hora.setText(cadenaenarray[6]);
        fecha.setText(cadenaenarray[5]);
        estadio.setText(cadenaenarray[4]);
        imaglocal.setBackgroundResource(Integer.parseInt(cadenaenarray[1]));
        imagvisit.setBackgroundResource(Integer.parseInt(cadenaenarray[3]));
        
       
        ScrollView contScroll = (ScrollView) findViewById(R.id.scrollViewStreamingDetalles);
        contScroll.fullScroll(View.FOCUS_UP);
        
        LinearLayout fondoCambiar = (LinearLayout) findViewById(R.id.LinearLayoutVideoStr);
        if(!cadenaenarray[8].equals("")){
        fondoCambiar.setBackgroundDrawable(downloadImage(cadenaenarray[8]));
        
        }
    }
 
  
public String[] cadena_a_dividir (String cadenaLLegada) {

	String mon1 = cadenaLLegada.replace("}",""); //quitamos el final
	String mon2 = mon1.replace("{",""); //quitamos el principio

    //Reescribimos valores de la cadena para pasarla limpia
	String mon3[] = mon2.split(",");
	mon3[0] = mon3[0].replaceAll("local=", "");
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

/**
* Devuelve una imagen desde una URL
* @param url Url de la imagen
* @return Una imagen
*/
private Drawable downloadImage(String imageUrl)
{
    try
    {
        URL url = new URL(imageUrl);
        InputStream is = (InputStream)url.getContent();
        return Drawable.createFromStream(is, "src");
    }
    catch (MalformedURLException e)
    {
        e.printStackTrace();
        return null;
    }
    catch (IOException e)
    {
        e.printStackTrace();
        return null;
    }
}

}