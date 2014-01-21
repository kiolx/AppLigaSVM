<?php
header('Content-Type: text/html; charset=UTF-8');

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
		                                       
                            if(stristr($content,"RT")){ }else{
                            echo '<p>'.$content.' ';
                            echo ' '.$partes[0].'  '.$partes[1].' </p>';
			    $cadenaAcomparar = devuelveArrayNoticias($autor, $tweets);
			    $contador =0;
			     for($i=0; $i<$tweets; $i++){ 
			        if($cadenaAcomparar[$i] == $content) {$contador = $contador+1; }else{ /*nohacernada */}
		             }
			
			     if($contador==0){
                             $insertar = "INSERT INTO noticias (autor, texto, fecha, hora) VALUES ('$autor', '$content', '$partes[0]', '$partes[1]')";
                             mysql_query($insertar, $conn);
			     }
                           }

			}	
			
		}
	}	

}

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
?> 