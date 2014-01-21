<? function mostrarTwitter($limite){include("config.php"); ?>
{ "datosnoticias": [
<?  $result = mysql_query("SELECT * FROM noticias ORDER by id_noticia DESC LIMIT $limite", $conn); $contador =0;
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
<?}?>
<? function mostrarResultadosDirecto($idpartido){ ?>
<div style="visibility:hidden;display:none;z-index:1; position:absolute;">
<?php
header('Content-Type: text/html; charset=UTF-8');
//Div oculto con la pagina de dataprojects
$id = $_GET["id"];
$pagina = file_get_contents("http://rfevb.dataproject-stats.com/dv_results.aspx?ID=".$idpartido);
echo $pagina;
?>
</div>


<link href="estilos.css" type="text/css" rel="stylesheet" />
<table width="290" border="0">

  <tr>
    <td></td>
    <td><span class="letratituloset">Ptos</span></td>
    <td><span class="letratituloset">Set 1</span></td>
    <td><span class="letratituloset">Set 2</span></td>
    <td><span class="letratituloset">Set 3</span></td>
    <td><span class="letratituloset">Set 4</span></td>
    <td><span class="letratituloset">Set 5</span></td>
  </tr>

  <tr>
    <td><span class="letratituloequipo"><script>document.write(document.getElementById("L_HomeTeam").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_TotHome").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_Set1Home").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_Set2Home").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_Set3Home").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_Set4Home").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_Set5Home").innerHTML);</script></span></td>
  </tr>
  
  <tr>
    <td><span class="letratituloequipo"><script>document.write(document.getElementById("L_GuestTeam").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_TotGuest").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_Set1Guest").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_Set2Guest").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_Set3Guest").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_Set4Guest").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_Set5Guest").innerHTML);</script></span></td>
  </tr>
  
   <tr>
    <td></td>
    <td><span class="letratitulotiempo">Time</span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_TimeSet1").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_TimeSet2").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_TimeSet3").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_TimeSet4").innerHTML);</script></span></td>
    <td align="center"><span class="letratitulonumero"><script>document.write(document.getElementById("L_TimeSet5").innerHTML);</script></span></td>
  </tr>

</table>
<?}?>
<? function mostrarDirecto($temporada, $limite){include("config.php"); ?>
{
    "datosdirecto": [

<?  $result = mysql_query("SELECT * FROM partidos WHERE temporada=$temporada ORDER by id_partido DESC LIMIT $limite", $conn); $contador =0;
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
<?}?>
<? function mostrarDirectoPro($temporada, $limite, $jornada){include("config.php"); if($jornada!=0){ ?>
{
    "datosdirecto": [

<?  $result = mysql_query("SELECT * FROM partidos WHERE temporada=$temporada AND jornada=$jornada ORDER by id_partido DESC LIMIT $limite", $conn); $contador =0;
if ($row = mysql_fetch_array($result)){ do {
echo '{';
echo '"local": "'.$row["local"].'",';
echo '"idimagenlocal": "'.$row["idimagenlocal"].'",';
echo '"visitante": "'.$row["visitante"].'",';
echo '"idimagenvisitante": "'.$row["idimagenvisitante"].'",';
echo '"estadio": "'.$row["estadio"].'",';
echo '"fecha": "'.$row["fecha"].'",';
echo '"hora": "'.$row["hora"].'",';
echo '"jornada": "'.$row["jornada"].'",';
echo '"idpartido": "'.$row["rfevb"].'",';
echo '"streaming": "'.$row["streaming"].'"';
echo '}';
$contador++;
$num_rows = mysql_num_rows($result);
if($contador < $num_rows ){ echo ',';}
} while ($row = mysql_fetch_array($result)); } ?>

]
}
<? }else{?>
{
    "datosdirecto": [

<?  $result = mysql_query("SELECT * FROM partidos WHERE temporada=$temporada ORDER by id_partido DESC LIMIT $limite", $conn); $contador =0;
if ($row = mysql_fetch_array($result)){ do {
echo '{';
echo '"local": "'.$row["local"].'",';
echo '"idimagenlocal": "'.$row["idimagenlocal"].'",';
echo '"visitante": "'.$row["visitante"].'",';
echo '"idimagenvisitante": "'.$row["idimagenvisitante"].'",';
echo '"estadio": "'.$row["estadio"].'",';
echo '"fecha": "'.$row["fecha"].'",';
echo '"hora": "'.$row["hora"].'",';
echo '"jornada": "'.$row["jornada"].'",';
echo '"idpartido": "'.$row["rfevb"].'",';
echo '"streaming": "'.$row["streaming"].'"';
echo '}';
$contador++;
$num_rows = mysql_num_rows($result);
if($contador < $num_rows ){ echo ',';}
} while ($row = mysql_fetch_array($result)); } ?>

]
}
<?} }?>
<? function mostrarClasificacion(){include("config.php"); ?>
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
<?}?>
<? function mostrarContacto(){?>
{
    "datoscontacto": {
        "nombre": "Alejandro Lucas Rodriguez",
        "email": "soporte@cavesquimo.es",
        "url": "http://www.cavesquimo.es",
		"fuentedatos": "Datos obtenidos de la RFEVB",
        "notafinal": "Aplicacion realizada con la colaboracion del  Club de Voleibol Esquimo para poder ver algunos datos relevantes de la Superliga de Voleibol Masculina. Para cualquier duda, sugerencia o incidencia, pueden utilizar la direccion de email proporcionada antes."
    }
}
<?}?>
<? function mostrarcopaRey(){
echo "<center><h2>Perdone las disculpas, estamos a la espera de completar esta sección con la información que publique la RFEVB</h2></center>";
}?>
<? function mostrarVersion($version){
echo $version;
}?>