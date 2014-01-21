<div style="visibility:hidden;display:none;z-index:1; position:absolute;">
<?php
header('Content-Type: text/html; charset=UTF-8');
//Div oculto con la pagina de dataprojects
$id = $_GET["id"];
$pagina = file_get_contents("http://rfevb.dataproject-stats.com/dv_results.aspx?ID=".$id);
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