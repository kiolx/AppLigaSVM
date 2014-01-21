<?php

$url = file_get_contents("http://rfevb.com/home/cnac/svm/svm_calendario.asp");
$corte1 = strstr($url, 'Lugar');
$corte2 = strstr($corte1, 'Nota:', true);

$limpiado = strip_tags($corte2); 

$output = preg_replace(array('/\r/', '/\n/'), '', $limpiado);
$output2 = preg_replace('!\s+!', ' ', $output);

$eliminado = str_replace("Lugar ", "", $output2);

$pieces = explode(" ", $eliminado);

for($i=0;$i<350;$i++){

echo $pieces[$i];

for($n=45;$n<90;$n++){
if($pieces[$i] == $n){ echo "<br>";}
}

}

/*echo $eliminado;*/
?>