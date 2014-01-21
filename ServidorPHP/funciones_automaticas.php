<? 
header('Content-Type: text/html; charset=UTF-8');

function mostrarImagen($imagenSepteto, $imagenMVP){
$src = array ($imagenSepteto, $imagenMVP);    
$imgBuf = array (); 
foreach ($src as $link) 
{ 
   switch(substr ($link,strrpos ($link,".")+1)) 
   { 
       case 'png': 
           $iTmp = imagecreatefrompng($link); 
           break; 
       case 'gif': 
           $iTmp = imagecreatefromgif($link); 
           break;                
       case 'jpeg':            
       case 'jpg': 
           $iTmp = imagecreatefromjpeg($link); 
           break;                
   } 
   array_push ($imgBuf,$iTmp); 
} 
$iOut = imagecreatetruecolor ("453","199") ; 
$transparent = imagecolorallocate($iOut, 0, 0, 0);
imagecopy ($iOut,$imgBuf[0],0,0,0,0,imagesx($imgBuf[0]),imagesy($imgBuf[0])); 
imagedestroy ($imgBuf[0]); 
imagecopy ($iOut,$imgBuf[1],305,0,0,0,imagesx($imgBuf[1]),imagesy($imgBuf[1])); 
imagedestroy ($imgBuf[1]); 
imagecolortransparent($iOut, $transparent);
imagepng($iOut, "septeto.png", 0,null); 
}

function vaciarTabla(){
include("config.php");
$delete = "DELETE FROM noticias";
mysql_query($delete, $conn);
}

function devuelveArrayNoticias($autor, $limite) { 
   include("config.php");
   $array = array();
   $result = mysql_query("SELECT * FROM noticias WHERE autor='$autor' ORDER by id_noticia DESC LIMIT $limite", $conn);
   if ($row = mysql_fetch_array($result)){ do {
   $array[] = $row["texto"];
   } while ($row = mysql_fetch_array($result)); } 

   return $array;
  //var_dump($array); 
}

function mostrarTwitter($autor, $hashtags, $usuario, $tweets) {
    include("config.php");
 	$feed = "http://search.twitter.com/search.atom?q=".$hashtags."from:".$usuario."&rpp=".$tweets;
 	$xml = simplexml_load_file($feed);
	foreach($xml->children() as $child) {
		foreach ($child as $value) {
			if($value->getName() == "updated") { //obtenemos los datos de la etiqueta updated
				$updated = $value . ""; //metemos el contenido de la etiqueta updated en su variable
                                $updatedLimpio = str_replace("Z", "", $updated);
                                $partes = explode("T", $updatedLimpio);
                                					
			}

			if($value->getName() == "content") { //obtenemos los datos de la etiqueta content
			     $content = $value . "";
                             $content = str_replace("'", "", $content);
		                                       
                            if(stristr($content,"RT")){ }else{
                            echo '<p>'.$content.' ';
                            echo ' '.$partes[0].'  '.$partes[1].' </p>';
			    $cadenaAcomparar = devuelveArrayNoticias($autor, $tweets);
			    $contador =0;
			     for($i=0; $i<$tweets; $i++){ 
			        if($cadenaAcomparar[$i] == $content) {$contador = $contador+1; }else{ /*nohacernada */}
		             }
			
			     if($contador==0){
					         if($partes[0]==""){$partes[0]="00-00-0000";}
							 if($partes[1]==""){$partes[1]="00:00";}
                             $insertar = "INSERT INTO noticias (autor, texto, fecha, hora) VALUES ('$autor', '$content', '$partes[0]', '$partes[1]')";
                             mysql_query($insertar, $conn);
			     }
                           }

			}	
			
		}
	}	

}


function actualizarClasificacion(){
include("config.php"); 
$delete = "DELETE FROM clasificacion";
mysql_query($delete, $conn);

$pagina_inicio = file_get_contents('http://www.rfevb.com/home/cnac/svm/svm.asp');
$depurado1 = strstr($pagina_inicio, '<tr class="peq">');

$cadena= $depurado1;
$maximo = strlen($cadena);
$cadena_comienzo = '<tr class="peq">';
$cadena_fin = "</table>";
$total = strpos($cadena,$cadena_comienzo);
$total2 = strpos($cadena,$cadena_fin);
$total3 = ($maximo - $total2 - 8);
$final = substr ($cadena,$total,-$total3);

$final_limpio = strip_tags($final);

$sinsalto = eregi_replace("[\n|\r|\n\r]", ' ', $final_limpio);  //Limpiamos la cadena de saltos de lineas

$sinespacios=ereg_replace("( ){2,}"," ",$sinsalto); //Limpiamos los espacios repetidos que encontremos

$partes = explode(" ", $sinespacios); // trozeamos la cadena cada vez que encontremos un espacio y lo guardamos automaticamente en plan partes[1], partes[2]

for($i=0;$i<70;$i++){ //Creamos Bucle que recorre las 70 palabras que resultan de la separación anterior y vamos haciendo la insercción en la base de datos

//Inicio Buscamos e insertamos el equipo Cajasol, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="CAJASOL"){

$puesto = $partes[$i-1];
$equipo = $partes[$i]." ".$partes[$i+1];
$jugados = $partes[$i+2];
$puntos = $partes[$i+3];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo Cajasol, así como sus puntos y los partidos jugados en la base de datos Mysql


//Inicio Buscamos e insertamos el equipo Cajasol, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="Cajasol"){

$puesto = $partes[$i-1];
$equipo = $partes[$i]." ".$partes[$i+1];
$jugados = $partes[$i+2];
$puntos = $partes[$i+3];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo Cajasol, así como sus puntos y los partidos jugados en la base de datos Mysql


//Inicio Buscamos e insertamos el equipo Uorsa Vigo, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="UBE"){

$puesto = $partes[$i-1];
$equipo = $partes[$i]." ".$partes[$i+1]." ".$partes[$i+2];
$jugados = $partes[$i+3];
$puntos = $partes[$i+4];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo Uorsa Vigo, así como sus puntos y los partidos jugados en la base de datos Mysql

//Inicio Buscamos e insertamos el equipo Uorsa Vigo, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="Club"){

$puesto = $partes[$i-1];
$equipo = $partes[$i]." ".$partes[$i+1];
$jugados = $partes[$i+2];
$puntos = $partes[$i+3];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo Uorsa Vigo, así como sus puntos y los partidos jugados en la base de datos Mysql

//Inicio Buscamos e insertamos el equipo Unicaja Almeria, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="Unicaja"){

$puesto = $partes[$i-1];
$equipo = $partes[$i]." ".$partes[$i+1];
$jugados = $partes[$i+2];
$puntos = $partes[$i+3];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo Unicaja Almeria, así como sus puntos y los partidos jugados en la base de datos Mysql

//Inicio Buscamos e insertamos el equipo Ortodent Caravaca 2010, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="Andorra"){

$puesto = $partes[$i-1];
$equipo = $partes[$i];
$jugados = $partes[$i+1];
$puntos = $partes[$i+2];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo Ortodent Caravaca 2010, así como sus puntos y los partidos jugados en la base de datos Mysql

//Inicio Buscamos e insertamos el equipo Multicaja Fabregas Sport, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="Ibiza"){

$puesto = $partes[$i-2];
$equipo = $partes[$i-1]." ".$partes[$i]." ".$partes[$i+1];
$jugados = $partes[$i+2];
$puntos = $partes[$i+3];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo Multicaja Fabregas Sport, así como sus puntos y los partidos jugados en la base de datos Mysql

//Inicio Buscamos e insertamos el equipo CMA de Soria Numancia, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="CAI"){

$puesto = $partes[$i-1];
$equipo = $partes[$i]." ".$partes[$i+1] ;
$jugados = $partes[$i+2];
$puntos = $partes[$i+3];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo CMA de Soria Numancia, así como sus puntos y los partidos jugados en la base de datos Mysql


//Inicio Buscamos e insertamos el equipo CMA de Soria Numancia, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="CMA"){

$puesto = $partes[$i-1];
$equipo = $partes[$i]." ".$partes[$i+1]." ".$partes[$i+2];
$jugados = $partes[$i+3];
$puntos = $partes[$i+4];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo CMA de Soria Numancia, así como sus puntos y los partidos jugados en la base de datos Mysql


//Inicio Buscamos e insertamos el equipo CMA de Soria Numancia, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="C.V."){

$puesto = $partes[$i-1];
$equipo = $partes[$i]." ".$partes[$i+1];
$jugados = $partes[$i+2];
$puntos = $partes[$i+3];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo CMA de Soria Numancia, así como sus puntos y los partidos jugados en la base de datos Mysql

//Inicio Buscamos e insertamos el equipo CV 7 Islas Vecindario, así como sus puntos y los partidos jugados en la base de datos Mysql
if ($partes[$i] =="Vecindario"){

$puesto = $partes[$i-1];
$equipo = $partes[$i]." ".$partes[$i+1]." ".$partes[$i+2]." ".$partes[$i+3];
$jugados = $partes[$i+4];
$puntos = $partes[$i+5];
 $insertar = "INSERT INTO clasificacion (puesto, equipo, jugados, puntos) 
 VALUES ('$puesto' , '$equipo', '$jugados', '$puntos')";
 mysql_query($insertar, $conn);

}
//Fin de Buscamos e insertamos el equipo CV 7 Islas Vecindario, así como sus puntos y los partidos jugados en la base de datos Mysql
}

}//Fin de la funcion


//Actualizamos los comentarios de Twitter
vaciarTabla();

mostrarTwitter("CajasolVoley", "","CajasolVoley", 5);
echo "<br/><br/>";
mostrarTwitter("RFEVB", "%23SVM ","RFEVB", 7);
echo "<br/><br/>";
mostrarTwitter("CVAlmeria", "","cvalmeria", 7);
echo "<br/><br/>";
mostrarTwitter("CVZaragoza", "","ClubVoleibolZGZ", 7);
echo "<br/><br/>";
mostrarTwitter("CVTeruel", "","CVTeruel", 7);

//Actualizamos la clasificacion
actualizarClasificacion();


?> 