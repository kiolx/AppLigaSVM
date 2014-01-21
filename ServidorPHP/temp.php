<?php include("config.php"); ?>
{ "datosnoticias": [
<?  $result = mysql_query("SELECT * FROM noticias ORDER by id_noticia DESC LIMIT 20", $conn); $contador =0;
if ($row = mysql_fetch_array($result)){ do {
echo '{';
echo '"autor": "'.$row["autor"].'",';
echo '"noticia": '.json_encode(strip_tags($row["texto"])).',';
echo '"fecha": "'.$row["fecha"].'",';
echo '"hora": "'.$row["hora"].'"';
echo '}';
$contador++;
$num_rows = mysql_num_rows($result);
if($contador < $num_rows ){ echo ',';}
} while ($row = mysql_fetch_array($result)); } ?>
]}