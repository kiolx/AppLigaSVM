<?php include("config.php"); ?>

{
    "datosclasificacion": [

<? $result = mysql_query("SELECT * FROM clasificacion ORDER by puesto ASC", $conn); $contador =0;
if ($row = mysql_fetch_array($result)){ do {
echo '{';
echo '"puesto": "'.$row["puesto"].'",';
echo '"equipo": "'.$row["equipo"].'",';
echo '"jornadas": "'.$row["jugados"].'",';
echo '"puntos": "'.$row["puntos"].'"';
echo '}';
$contador++;
$num_rows = mysql_num_rows($result);
if($contador < $num_rows ){ echo ',';}
} while ($row = mysql_fetch_array($result)); } ?>

]

}