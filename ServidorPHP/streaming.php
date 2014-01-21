
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="js/swfobject.js"></script>
</head>
<?php $ciudad = $_GET["ciudad"]; ?>
<body style="margin:0px; padding:0px;">
  
                <div id="livePlayer">
                </div>
                <script type="text/javascript">

                    var agent = navigator.userAgent;
                    var isIphone = (agent.indexOf('iPhone') != -1 || agent.indexOf('iPad') != -1 || agent.indexOf('iPod') != -1);
                    var isBlackBerry = (agent.indexOf('BlackBerry') != -1 || agent.indexOf('blackberry') != -1);

                    if (!isIphone && !isBlackBerry) {

                        var so = new SWFObject('player.swf', 'mpl', '960', '540', '9');
                        so.addParam('allowfullscreen', 'true');
                        so.addParam('allowscriptaccess', 'always');
                        so.addParam('wmode', 'opaque');
                        so.addVariable('type', 'RTMP');
                        so.addVariable('flashvars', '&autostart=false&autoplay=false&type=rtmp&streamer=rtmp://cloud2live.it2.com/meytel-user-3-live01/_definst_&file=<? echo $ciudad;?>&stretching=fill&abouttext=meytel.net');
                        so.addVariable('backcolor', '#282152');
                        so.addVariable('frontcolor', '#fff');
                       so.addVariable('image', 'http://www.rfevb.com/home/images/cabecera.gif');
                        so.addVariable('provider', 'rtmp');
                        so.write('livePlayer');

                    } else {
                        if (isIphone) {
                            $("#livePlayer").html('<video width="970" height="529" src="http://cloud2live.it2.com:1935/meytel-user-3-live01/_definst_/mp4:sevilla/playlist.m3u8"  controls="controls" autoplay ></video>');
                        }
                        else {
                            if (isBlackBerry) {
                                document.location = 'rtsp://cloud2live.it2.com:1935/meytel-user-3-live01/mp4:sevilla';
                            }
                        }

                    }
                </script>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-34967271-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>


</body>
</html>