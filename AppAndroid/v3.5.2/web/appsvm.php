<?php
$version = "3.5";
$func = $_GET["func"];
$idpartido = $_GET["idpartido"];
$temporada = $_GET["temporada"];
$limite = $_GET["limite"];
$usuario = $_GET["usuario"];
$imagenMVP = $_GET["imagenMVP"];
$imagenSepteto = $_GET["imagenSepteto"];
include("funciones.php");


if($func == "copaRey"){mostrarCopaRey();}
if($func == "mostrarContacto"){mostrarContacto();}
if($func == "mostrarVersion"){mostrarVersion($version);}
if($func == "mostrarClasificacion"){mostrarClasificacion();}
if($func == "mostrarTwitter"){mostrarTwitter("20");}
if($func == "mostrarDirecto"){mostrarDirecto("2013", "5");}
if($func == "mostrarImagen"){mostrarImagen($imagenSepteto,$imagenMVP);}
if($func == "mostrarResultadosDirecto"){mostrarResultadosDirecto($idpartido);}

?>