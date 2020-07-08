<?php
$id = ((int)$_POST["down"]);
if ($id= "down")
{

// It will be called downloaded.pdf
header('Content-Disposition: attachment; filename="fact.pdf"');

// The PDF source is in original.pdf
readfile('fact.pdf');
}
?>