package pakete.contenedor.ligavoleibolsvm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.actionbarsherlock.app.SherlockActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import org.w3c.dom.Element;
import android.widget.EditText;

public class Precargador extends SherlockActivity {
	
	public String[] plantillasStr = {"andorra_1.png","andorra_2.png","andorra_3.png","andorra_4.png","andorra_5.png","andorra_6.png","andorra_7.png","andorra_8.png","andorra_9.png","andorra_10.png","andorra_11.png","andorra_12.png", "andorra_13.png","andorra_14.png","andorra_15.png",
			"zaragoza_1.png","zaragoza_2.png","zaragoza_3.png","zaragoza_4.png","zaragoza_5.png","zaragoza_6.png","zaragoza_7.png","zaragoza_8.png","zaragoza_9.png","zaragoza_10.png","zaragoza_11.png","zaragoza_12.png",
			"teruel_0.png","teruel_1.png","teruel_2.png","teruel_3.png","teruel_4.png","teruel_5.png","teruel_6.png","teruel_7.png","teruel_8.png","teruel_9.png","teruel_10.png","teruel_11.png","teruel_12.png",
			"cajasol_0.png","cajasol_1.png","cajasol_2.png","cajasol_3.png","cajasol_4.png","cajasol_5.png","cajasol_6.png","cajasol_7.png","cajasol_8.png","cajasol_9.png","cajasol_10.png","cajasol_11.png","cajasol_12.png",
			"vigo_0.png","vigo_1.png","vigo_2.png","vigo_3.png","vigo_4.png","vigo_5.png","vigo_6.png","vigo_7.png","vigo_8.png","vigo_9.png","vigo_10.png","vigo_11.png","vigo_12.png",
			"soria_1.png","soria_2.png","soria_3.png","soria_4.png","soria_5.png","soria_6.png","soria_7.png","soria_8.png","soria_9.png","soria_10.png",
			"lilla_1.png","lilla_2.png","lilla_3.png","lilla_4.png","lilla_5.png","lilla_6.png","lilla_7.png","lilla_8.png","lilla_9.png","lilla_10.png","lilla_11.png","lilla_12.png",
			"almeria_1.png","almeria_2.png","almeria_3.png","almeria_4.png","almeria_5.png","almeria_6.png","almeria_7.png","almeria_8.png","almeria_9.png","almeria_10.png","almeria_11.png","almeria_12.png",
			"ibiza_0.png","ibiza_1.png","ibiza_2.png","ibiza_3.png","ibiza_4.png","ibiza_5.png","ibiza_6.png","ibiza_7.png","ibiza_8.png","ibiza_9.png","ibiza_10.png","ibiza_11.png","ibiza_12.png",
			"canaria_1.png","canaria_2.png","canaria_3.png","canaria_4.png","canaria_5.png","canaria_6.png","canaria_7.png","canaria_8.png","canaria_9.png","canaria_10.png","canaria_11.png","canaria_12.png", "canaria_13.png","canaria_14.png","canaria_15.png", "canaria_16.png"};

	public String dondeBuscar = "Interno";
	public String ruta = "http://mobil.cavesquimo.es/";
	public String rutaParseo = "http://mobil.cavesquimo.es/appsvm.php";
	public int estadoAlante = 0;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Theme_Sherlock_Light_NoActionBar);	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_precargador);
        
        EditText mensajes = (EditText)findViewById(R.id.editTextPrecargador);
        mensajes.append("* Cargando pantalla de inicio\n");  

            ArrancarDatosDirApp();
            ArrancarDatosBDApp();
            descargarSeptetoMVPFicheroParseo();
            leerYparsear();
            
            int delay = 2000; // delay for 1 sec. 
            int period = 1000; // repeat every 10 sec. 
            final Timer timer = new Timer(); 
            timer.scheduleAtFixedRate(new TimerTask() { 
                    public void run() { 
                    	if(estadoAlante==10){
                    		timer.cancel();
                        	Intent ArranquePrincipal = new Intent(Precargador.this, Principal.class);
                        	startActivity(ArranquePrincipal);
                            finish();
                    	}
                    } 
                }, delay, period); 
}
	
    
	public void ArrancarDatosDirApp(){
        EditText mensajes = (EditText)findViewById(R.id.editTextPrecargador);
        mensajes.append("* Creando directorios\n");  	
		//Generamos los directorios donde almacenar las fotos 
		crearDirectorioINT();
		
	    crearSubDirectorioINT("galeriafotos");
		crearSubDirectorioINT("plantillas");
		crearSubDirectorioINT("mvp");
		crearSubDirectorioINT("septeto");
		crearSubDirectorioINT("parseo");

	}
	
    public void ArrancarDatosBDApp(){
        EditText mensajes = (EditText)findViewById(R.id.editTextPrecargador);
        mensajes.append("* Creando base de datos\n");  	
        mensajes.append("* Insertando registros\n");  	
     	
        this.deleteDatabase("svm.db");//Borramos la db por si las moscas y generamos una nueva      
        
        //Iniciamos la Base de datos        
   	    DBHelper dbHelper = new DBHelper(Precargador.this);
   	    SQLiteDatabase db = dbHelper.getWritableDatabase();

   	   //Insertamos los Links de las plantillas
   	   int contador =0; String temp = "";
   	   for(int i=0;i<plantillasStr.length;i++){
   	   String[] explodeo = plantillasStr[i].split("_");
   	   if(temp.equals(explodeo[0])){}else{contador=0;}
   	   ContentValues valuesPlantillas = new ContentValues();
   	   String ruta = "http://mobil.cavesquimo.es/descargas/plantillas/"+plantillasStr[i];
   	   valuesPlantillas.put("ruta", ruta);
       valuesPlantillas.put("equipo", explodeo[0]);
       valuesPlantillas.put("posicion", contador);
       db.insert(DBHelper.TABLAplantillas, null, valuesPlantillas);
       temp = explodeo[0];
       contador++;
   	   }
   	   
   	   //Insertamo los datos de las Opciones por primer arranke al no existir la DB
       ContentValues valuesOpciones = new ContentValues();
       valuesOpciones.put("version", this.getString(R.string.appVersion));
       valuesOpciones.put("actualizacion", this.getString(R.string.appVersion));
       valuesOpciones.put("primeravez", "si");
       db.insert(DBHelper.TABLAopciones, null, valuesOpciones);
       
       //Insertamos la url de la imagen y un identificador para ver el cambio de imagen
       ContentValues valuesMVP = new ContentValues();
       valuesMVP.put("ruta", "http://mobil.cavesquimo.es/descargas/mvp/mvp.png");
       valuesMVP.put("filesizehash", "100");
       db.insert(DBHelper.TABLAmvp, null, valuesMVP);       
 
       //Insertamos la url de la imagen y un identificador para ver el cambio de imagen
       ContentValues valuesSepteto = new ContentValues();
       valuesSepteto.put("ruta", "http://mobil.cavesquimo.es/descargas/septeto/septeto.png");
       valuesSepteto.put("filesizehash", "100");
       db.insert(DBHelper.TABLAsepteto, null, valuesSepteto); 
       
       //Insertamos la url de la imagen y un identificador para ver el cambio de imagen
       ContentValues valuesContacto = new ContentValues();
       valuesContacto.put("nombre", "Alejandro Lucas Rodriguez");
       valuesContacto.put("email", "soporte@cavesquimo.es");
       valuesContacto.put("url", "http://www.cavesquimo.es");
       valuesContacto.put("fuentedatos", "RFEVB");
       valuesContacto.put("notafinal", "ErrorNoDataReceived");
       db.insert(DBHelper.TABLAcontacto, null, valuesContacto);   	   

   	   //Close the Database and the Helper
   	   db.close();
   	   dbHelper.close();
   	 	
    }
    
    //Descargador de septeto e mvp
    public void descargarSeptetoMVPFicheroParseo(){
        try {
            URL url = new URL("http://mobil.cavesquimo.es/descargas/parseo/parseo.php");
            URLConnection conexion = url.openConnection();
            conexion.connect();
            int lenghtOfFile = conexion.getContentLength();
            InputStream is = url.openStream();
            FileOutputStream fos = new FileOutputStream("data/data/pakete.contenedor.ligavoleibolsvm/SVM/parseo/parseo.xml");
            byte data[] = new byte[1024];
            int count = 0;
            long total = 0;
            int progress = 0;
            while ((count = is.read(data)) != -1) {
                total += count;
                int progress_temp = (int) total * 100 / lenghtOfFile;
                if (progress_temp % 10 == 0 && progress != progress_temp) {
                    progress = progress_temp;
                }
                fos.write(data, 0, count);
            }
            is.close();
            fos.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        EditText mensajes = (EditText)findViewById(R.id.editTextPrecargador);
        mensajes.append("* Archivo descargado\n"); 
    }
       
	public void crearDirectorioINT(){
		File folder = new File("data/data/pakete.contenedor.ligavoleibolsvm/SVM");
		boolean success = true;
		if (!folder.exists()) {
		    success = folder.mkdir();
		}if (success) {
		    // Do something on success
		} else {
		    // Do something else on failure 
		}
		
	}
	
	public void crearSubDirectorioINT(String carpeta){
		File folder = new File("data/data/pakete.contenedor.ligavoleibolsvm/SVM/"+carpeta);
		boolean success = true;
		if (!folder.exists()) {
		    success = folder.mkdir();
		}
		if (success) {
		    // Do something on success
		} else {
		    // Do something else on failure 
		}
		
	}
    
	
	public void leerYparsear() { 
		/*http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/*/
		EditText mensajes = (EditText)findViewById(R.id.editTextPrecargador);  
		DBHelper dbHelper = new DBHelper(Precargador.this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();	
		String nombreStr = "";  String emailStr = "";  String urllStr = ""; String fuentedatosStr = ""; String notafinalStr =""; String actualizacion="";
		try {
			mensajes.append("* Empezando parseo\n");
			File fXmlFile = new File("data/data/pakete.contenedor.ligavoleibolsvm/SVM/parseo/parseo.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
	 
			/*mensajes.append("NodoElemento :" + doc.getDocumentElement().getNodeName());*/
			/*Inicio inserccion de version*/
			NodeList nList3 = doc.getElementsByTagName("datosopciones");
	 		for (int temp = 0; temp < nList3.getLength(); temp++) { 
			   Node nNode3 = nList3.item(temp);
			   if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
			      Element eElement = (Element) nNode3;
			      actualizacion = getTagValue("actualizacion", eElement);
			   }}
			
			db.execSQL("UPDATE opciones SET actualizacion='"+actualizacion+"' WHERE id_opcion='1'");
			/*FIN inserccion de version*/
			
			/*Inicio inserccion de datoscontacto*/
			NodeList nList = doc.getElementsByTagName("datoscontacto");
	 		for (int temp = 0; temp < nList.getLength(); temp++) { 
			   Node nNode = nList.item(temp);
			   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			      Element eElement = (Element) nNode;
			      nombreStr = getTagValue("nombre", eElement);
			      emailStr = getTagValue("email", eElement);
			      urllStr = getTagValue("url", eElement);
			      fuentedatosStr = getTagValue("fuentedatos", eElement);
			      notafinalStr = getTagValue("notafinal", eElement); 
			   }}
			
			db.execSQL("UPDATE contacto SET nombre='"+nombreStr+"' , email='"+emailStr+"' , url='"+urllStr+"' , fuentedatos='"+fuentedatosStr+"', notafinal='"+notafinalStr+"' WHERE id_contacto='1'");
			/*FIN inserccion de datoscontacto*/
			
			/*Inicio inserccion de partidos*/
			NodeList nList2 = doc.getElementsByTagName("datospartidos");
	 		for (int temp = 0; temp < nList2.getLength(); temp++) { 
			   Node nNode2 = nList2.item(temp);
			   if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
			      Element eElement = (Element) nNode2;
			      String local = getTagValue("local", eElement);
			      String imagenlocal = getTagValue("imagenlocal", eElement);
			      String visitante = getTagValue("visitante", eElement);
			      String imagenvisitante = getTagValue("imagenvisitante", eElement);
			      String estadio = getTagValue("estadio", eElement); 
			      String fecha = getTagValue("fecha", eElement);
			      String hora = getTagValue("hora", eElement);
			      String rfevb = getTagValue("rfevb", eElement);
			      String streaming = getTagValue("streaming", eElement);
			      String jornada = getTagValue("jornada", eElement);
			     db.execSQL("INSERT INTO partidos (local,imagenlocal,visitante,imagenvisitante,estadio,fecha,hora,rfevb,streaming,jornada) VALUES ('"+local+"','"+imagenlocal+"','"+visitante+"','"+imagenvisitante+"','"+estadio+"','"+fecha+"','"+hora+"','"+rfevb+"','"+streaming+"','"+jornada+"')");
			   }}			
			 /*FIN inserccion de partidos*/
	 		
			/*Inicio inserccion de clasificacion*/
			NodeList nList4 = doc.getElementsByTagName("datosclasificacion");
	 		for (int temp = 0; temp < nList4.getLength(); temp++) { 
			   Node nNode4 = nList4.item(temp);
			   if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
			      Element eElement = (Element) nNode4;
			      String puesto = getTagValue("puesto", eElement);
			      String equipo = getTagValue("equipo", eElement);
			      String pj = getTagValue("pj", eElement);
			      String ptos = getTagValue("ptos", eElement);
			     db.execSQL("INSERT INTO clasificacion (puesto,equipo,pj,ptos) VALUES ('"+puesto+"','"+equipo+"','"+pj+"','"+ptos+"')");
			   }}			
			 /*FIN inserccion de clasificacion*/
	 
			/*Inicio inserccion de clasificacion*/
			NodeList nList5 = doc.getElementsByTagName("datosnoticias");
	 		for (int temp = 0; temp < nList5.getLength(); temp++) { 
			   Node nNode5 = nList5.item(temp);
			   if (nNode5.getNodeType() == Node.ELEMENT_NODE) {
			      Element eElement = (Element) nNode5;
			      String autor = getTagValue("autor", eElement);
			      String noticia = getTagValue("noticia", eElement);
			      String fecha = getTagValue("fecha", eElement);
			      String hora = getTagValue("hora", eElement);
			     db.execSQL("INSERT INTO noticias (autor,noticia,fecha,hora) VALUES ('"+autor+"','"+noticia+"','"+fecha+"','"+hora+"')");
			   }}			
			 /*FIN inserccion de clasificacion*/
	 		
			mensajes.append("* Base de datos actualizada\n");
		  } catch (Exception e) {
			e.printStackTrace();
			mensajes.append("* Error en el parseo\n");
		  }
		//Cerramos base de datos
		db.close();
	   	dbHelper.close(); 
	   	estadoAlante=10;//orden de arrancar el otro activity apra el timer
	  }
	 
	  private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 
	      Node nValue = (Node) nlList.item(0);
	 
		return nValue.getNodeValue();
	  }


}//Fin de la clase