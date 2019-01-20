<!DOCTYPE html>
<html>
<head>
	<title></title>
	<form action="otpverify.php">
		<button>sand</button>
</head>
<body>

</body>
</html>
<?php
session_start();

$otp=rand(1000,9999);
$_SESSION["otp"] = $otp;
print($otp);


?> 