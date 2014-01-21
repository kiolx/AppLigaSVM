<? include("config.php"); ?>

<?php
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
if ($partes[$i] =="Ushuaïa"){

$puesto = $partes[$i-1];
$equipo = $partes[$i]." ".$partes[$i+1]." ".$partes[$i+2];
$jugados = $partes[$i+3];
$puntos = $partes[$i+4];
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


?>