<? header ("Content-Type:text/xml"); ?>
<? function mostrarContacto(){?>
    <datoscontacto>
        <nombre>Alejandro Lucas Rodriguez</nombre>
        <email>soporte@cavesquimo.es</email>
        <url>http://www.cavesquimo.es</url>
        <fuentedatos>Obtenidos de RFEVB</fuentedatos>
		<notafinal>Aplicacion realizada con la colaboracion del Club de Voleibol Esquimo para poder ver algunos datos relevantes de la Superliga de Voleibol Masculina. Para cualquier duda, sugerencia o incidencia, pueden utilizar la direccion de email proporcionada antes.</notafinal>
    </datoscontacto>
<? } ?>
<? function mostrarOpciones(){?>
    <datosopciones>
        <actualizacion>4.0.3</actualizacion>
        <jornadaactual>15</jornadaactual>
    </datosopciones>
<? } ?>
<? function mostrarDirectoPro($temporada, $limite){include("config.php");
$result = mysql_query("SELECT * FROM partidos WHERE temporada=$temporada ORDER by id_partido DESC", $conn);
if ($row = mysql_fetch_array($result)){ do {
echo '<datospartidos>';
echo '<local>'.utf8_encode($row["local"]).'</local>';
echo '<imagenlocal>'.utf8_encode($row["idimagenlocal"]).'</imagenlocal>';
echo '<visitante>'.utf8_encode($row["visitante"]).'</visitante>';
echo '<imagenvisitante>'.utf8_encode($row["idimagenvisitante"]).'</imagenvisitante>';
echo '<estadio>'.utf8_encode($row["estadio"]).'</estadio>';
echo '<fecha>'.utf8_encode($row["fecha"]).'</fecha>';
echo '<hora>'.utf8_encode($row["hora"]).'</hora>';
echo '<jornada>'.utf8_encode($row["jornada"]).'</jornada>';
echo '<rfevb>'.utf8_encode($row["rfevb"]).'</rfevb>';
echo '<streaming>22</streaming>';
echo '</datospartidos>';
$num_rows = mysql_num_rows($result);
} while ($row = mysql_fetch_array($result)); } } ?>
<? function mostrarClasificacion(){include("config.php"); 
 $result = mysql_query("SELECT * FROM clasificacion ORDER by puesto ASC", $conn);
if ($row = mysql_fetch_array($result)){ do {
echo '<datosclasificacion>';
echo '<puesto>'.utf8_encode($row["puesto"]).'</puesto>';
echo '<equipo>'.utf8_encode($row["equipo"]).'</equipo>';
echo '<pj>'.utf8_encode($row["jugados"]).'</pj>';
echo '<ptos>'.utf8_encode($row["puntos"]).'</ptos>';
echo '</datosclasificacion>';
$num_rows = mysql_num_rows($result);
} while ($row = mysql_fetch_array($result));  }}?>
<? function mostrarNoticias($limite){include("config.php"); 
$result = mysql_query("SELECT * FROM noticias ORDER by id_noticia DESC", $conn);
if ($row = mysql_fetch_array($result)){ do {
echo '<datosnoticias>';
echo '<autor>'.$row["autor"].'</autor>';
echo '<noticia> '.strip_tags($row["texto"]).'</noticia>';
echo '<fecha>'.$row["fecha"].'</fecha>';
echo '<hora>'.$row["hora"].'</hora>';
echo '</datosnoticias>';
$num_rows = mysql_num_rows($result);
} while ($row = mysql_fetch_array($result)); } }?>
<? function mostrarMVPysepteto(){include("config.php"); 
$result = mysql_query("SELECT * FROM mvpysepteto ORDER by id_mvpsepteto DESC", $conn);
if ($row = mysql_fetch_array($result)){ do {
echo '<datosmvpsepteto>';
echo '<urlsepteto>'.utf8_encode($row["urlsepteto"]).'</urlsepteto>';
echo '<urlmvp>'.utf8_encode($row["urlmvp"]).'</urlmvp>';
echo '<descripcionmvp>'.utf8_encode($row["textomvp"]).'</descripcionmvp>';
echo '</datosmvpsepteto>';
$num_rows = mysql_num_rows($result);
} while ($row = mysql_fetch_array($result)); } }?>
<? function mostrarCopaRey(){?>
    <datoscoparey>
        <cuartosp1e1>CMA Soria</cuartosp1e1>
        <cuartosp1e1img1>soria</cuartosp1e1img1>
        <cuartosp1e2>Cajasol Juvasa</cuartosp1e2>
        <cuartosp1e2img2>cajasol</cuartosp1e2img2>
        <cuartosp1fecha>25-01-2013</cuartosp1fecha>
        <cuartosp1hora>17:00</cuartosp1hora>
        <cuartosp1streaming>1328</cuartosp1streaming>
        <cuartosp2e1>Ushuaïa Ibiza Voley</cuartosp2e1>
        <cuartosp2e1img1>ibiza</cuartosp2e1img1>
        <cuartosp2e2>Vecindario ACE Gran Canaria</cuartosp2e2>
        <cuartosp2e2img2>canaria</cuartosp2e2img2>
        <cuartosp2fecha>25-01-2013</cuartosp2fecha>
        <cuartosp2hora>20:00</cuartosp2hora>
        <cuartosp2streaming>1329</cuartosp2streaming>
        <semip1e1>Vecindario ACE Gran Canaria</semip1e1>
        <semip1e1img1>canaria</semip1e1img1>
        <semip1e2>CAI Voleibol Teruel</semip1e2>
        <semip1e2img2>teruel</semip1e2img2>
        <semip1fecha>26-01-2013</semip1fecha>
        <semip1hora>18:00</semip1hora>
        <semip1streaming>1330</semip1streaming>
        <semip2e1>CMA Soria</semip2e1>
        <semip2e1img1>soria</semip2e1img1>
        <semip2e2>Unicaja Almeria</semip2e2>
        <semip2e1img2>almeria</semip2e1img2>
        <semip2fecha>26-01-2013</semip2fecha>
        <semip2hora>20:00</semip2hora>
        <semip2streaming>1331</semip2streaming>
        <finalp1e1>CAI Teruel</finalp1e1>
        <finalp1e1img1>teruel</finalp1e1img1>
        <finalp1e2>Unicaja Almeria</finalp1e2>
        <finalp1e2img2>almeria</finalp1e2img2>
        <finalp1fecha>27-01-2013</finalp1fecha>
        <finalp1hora>12:00</finalp1hora>
        <finalp1streaming>1332</finalp1streaming>
    </datoscoparey>
<? } ?>
<?
echo "<todoslosdatos>";
mostrarContacto();
mostrarOpciones();
mostrarNoticias();
mostrarMVPysepteto();
mostrarClasificacion();
mostrarDirectoPro("2013");
mostrarCopaRey();
echo "</todoslosdatos>";
?>