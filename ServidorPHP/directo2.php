<?php include("config.php"); $func = $_GET["func"]; if($func=="mostrarDirecto"){?>

{
    "datosdirecto": [

<?  $result = mysql_query("SELECT * FROM partidos WHERE temporada='2013' ORDER by jornada DESC LIMIT 5", $conn); $contador =0;
if ($row = mysql_fetch_array($result)){ do {
echo '{';
echo '"local": "'.$row["local"].'",';
echo '"idimagenlocal": "'.$row["idimagenlocal"].'",';
echo '"visitante": "'.$row["visitante"].'",';
echo '"idimagenvisitante": "'.$row["idimagenvisitante"].'",';
echo '"estadio": "'.$row["estadio"].'",';
echo '"fecha": "'.$row["fecha"].'",';
echo '"hora": "'.$row["hora"].'",';
echo '"idpartido": "'.$row["rfevb"].'",';
echo '"streaming": "'.$row["streaming"].'"';
echo '}';
$contador++;
$num_rows = mysql_num_rows($result);
if($contador < $num_rows ){ echo ',';}
} while ($row = mysql_fetch_array($result)); } ?>

]

}

<?php } ?>