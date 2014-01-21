<? 
header ("Content-type: image/png"); 
$imagenMVP = "http://rfevb.com/home/cnac/svm/estadisticas/12-13/jornada08.jpg";
$imagenSepteto = "http://rfevb.com/home/cnac/svm/estadisticas/jugadores/2012-2013/victor_viciana_b.jpg";
$src = array ($imagenMVP, $imagenSepteto);    
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
?>