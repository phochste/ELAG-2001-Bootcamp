<%-- 
    Document   : index
    Created on : Apr 30, 2011, 7:32:25 AM
    Author     : hochsten
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ELAG 2011 Bootcamp</title>
        <style type="text/css">
            body {
                font-family: helvetica;
            }
        </style>
    </head>
    <body>

<h1>ELAG 2011 - Digital Preservation Bootcamp</h1>

<h2>Meeting URL & Slides</h2>

<p>
<a href="https://connect.sunet.se/patrick/">https://connect.sunet.se/patrick/</a>
</p>

<p>
    <a href="docs/20110430_in_case_of_failure.pdf">Slides</a>
</p>

<h2>Something to watch while we setup the virtual meeting</h2>

<p>
  <a href="https://www.youtube.com/watch?v=YyxGIbtMS9E">The Atanasoff-Berry Computer In Operation</a>
</p>

<h2>Software</h2>

<p>
    <a href="http://java.com/en/">Java</a><br/>
    <a href="http://processing.org/download/">Processing</a><br/>
    <a href="http://www.gimp.org/">Gimp</a><br/>
    <a href="http://www.openoffice.org/">Open Office</a><br/>
    <a href="http://flying.guy.chez-alice.fr/Hexditor.jar">Hexditor.jar</a><br/>
    <a href="http://sourceforge.net/projects/droid/files/droid/6.0/droid-6.01.zip/download">droid-6.01.zip</a><br/>
    <a href="http://sourceforge.net/projects/jhove/files/jhove/JHOVE%201.6/jhove-1_6.zip/download">jhove-1.6.zip</a><br/>
    <a href="http://sourceforge.net/projects/xena/files/5.0.0/">Xena 5.0.0</a><br/>
    <a href="hhttp://www.dosbox.com/download.php?main=1">DOSBox</a><br/>
    <a href="http://sourceforge.net/projects/loc-xferutils/files/loc-bagger/bagger-2.1.1.zip/download">bagger-2.1.1.zip</a><br/>
</p>

<h2>Documentation</h2>

<p>
    <a href="docs">Papers</a><br/>
    <a href="http://www.slideshare.net/hochstenbach/20081007-workshop-bomvl-wp3">20081007 Workshop BOM-VL WP3</a><br/>
</p>

<h2>1. MTTF experiment</h2>
<p>
<a href="sharpshooter.zip">MTTF Simulator [ZIP]</a><br/>
<a href="sharpshooter/">MTTF Simulator [WWW]</a><br/>
</p>

<p>
Open the source code in a Processing window and hit 'Ctrl-R'.
</p>

<ul>
    <li><b>m</b> : decrease MTTF</li>
    <li><b>M</b> : increase MTTF</li>
    <li><b>t</b> : - 1 year</li>
    <li><b>T</b> : + 1 year</li>
    <li><b>SPACE</b> : start</li>
</ul>

<p>
Use the Excel spreadsheet below to calculate the reliability of systems using MTTF.<br/><br/>
<a href="mttf/MTTF.xlsx">Excel Experiment</a><br/>
</p>

<h2>2. Bit error experiment</h2>

<p>
<a href="demo_files.zip">Demo files [ZIP]</a><br/>
<a href="demo_files/">Demo files [WWW]</a><br/>
</p

<p>
Upload a file and specify the number of random bits you want to flip.
</p>

<form enctype='multipart/form-data' method='post' action='flip'>
    Upload: <input type='file' NAME='myupload' size='40'>
    Flip:
    <select name="count">
        <option value="0" selected>0</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="10">10</option>
        <option value="100">100</option>
        <option value="1000">1000</option>
    </select> bits
    <input type='submit' VALUE='Flip!'>
</form>

<h2>3. Visual defects experiment</h2>

<p>
<a href="imagediff.zip">Image Difference [ZIP]</a><br/>
<a href="imagediff/">Image Difference [WWW]</a><br/>
<a href="demo_files.zip">Demo files [ZIP]</a><br/>
<a href="demo_files/">Demo files [WWW]</a><br/>
</p>

<p>
Open the source code in a new Processing project and hit 'Ctr-R'.
You will be presented twice with a file chooser to load two images
in the application. The application will calculate the difference of
both images.
</p>

<p>
The demo files package contain sample .jpg  , .bmp , .tif and .jp2 [jpeg2000].
Use the bitflipper to randomly change the files. Check the visual differences.
</p>

<h2>4. Checksum experiment</h2>

<form enctype='multipart/form-data' method='post' action='md5'>
    File1: <input type='file' NAME='myupload1' size='40'> <br/>
    File2: <input type='file' NAME='myupload2' size='40'> <br/>
    File3: <input type='file' NAME='myupload3' size='40'>
    <input type='submit' VALUE='Sum!'>
</form>

<h2>5. Information representation experiment</h2>

<p>
    <a href="bitviewer.zip">Bitviewer [ZIP]</a><br/>
    <a href="bitviewer/">Bitviewer [WWW]</a><br/>
</p>

<p>
Copy and paste the source code in a new Processing project.
</p>

<p>
Sample text files: <a href="textencoding">textencoding project</a>.
</p>

<h2>6. Format characterization & validation experiment</h2>

<p>
<a href="demo_files.zip">Demo Files</a><br/>
</p>

<form enctype='multipart/form-data' method='post' action='truncate'>
    Upload: <input type='file' NAME='myupload' size='40'>
    Truncate by: <input type='text' NAME='count' VALUE='0' size='5'> bytes
    <input type='submit' VALUE='Truncate!'>
</form>

<h2>7. Migration and emulation experiment</h2>

<p>
 Download the <a href="demo_files/demo.wp">demo.wp</a> file.
 Use Droid or Jhove to characterize the file format. Use Open Office to migrate
 this file to PDF/A.
</p>


<p>
Download the <a href="tools/wp.zip">wp.zip</a> file. This zip file contains
a WordPerfect 5.1 installation. Run wp.exe in the DOSBox emulator.
Open the demo.wp file and examine the Print view. Notice any differences?
</p>

<h2>8. Submission Information Package experiment</h2>


<form enctype='multipart/form-data' method='post' action='bagit'>
    ZIP-Bag: <input type='file' NAME='myupload' size='40'>
    <input type='submit' VALUE='Deposit!'>
</form>

    </body>
</html>