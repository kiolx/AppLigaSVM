package pakete.contenedor.ligavoleibolsvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.actionbarsherlock.app.SherlockActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

public class StreamingActivityVideo extends SherlockActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Sherlock___Theme_DarkActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streaming_video); 

        Uri mPath  = Uri.parse("http://rfevbpago.it2.com/Campeonato_de_Espa_a_de_voley_playa_2012_Resumen_de_la_Final_Masculina_69.mp4");
        
        VideoView video = (VideoView) findViewById(R.id.videoView1);
        video.setVideoURI(mPath);
        video.requestFocus();
        video.start();
        
    }
}
 

