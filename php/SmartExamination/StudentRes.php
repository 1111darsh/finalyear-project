<!DOCTYPE html>
<html>
<head>
	<title>Sutdent input</title>
</head>
<body>
	<form method="post" action="paper.php">
	<!-- <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>"> -->
		<table>
		<tr>
			<td>s_id</td>
			<td><input type="text" name="S_ID"></td>
		</tr>
		<tr>
			<td>subject</td>
			<td>
			<input type="checkbox" name="Subject" value="c" >c
			<input type="checkbox" name="Subject" value="html">html
			<input type="checkbox" name="Subject" value="java">java 
			</td>		
		</tr>
		<tr>
			<td>Number of question</td>.
			<td>
			<input type="radio" name="NOQ" value="5" checked>5
			<input type="radio" name="NOQ" value="10">10
			<input type="radio" name="NOQ" value="20">20
			<input type="radio" name="NOQ" value="50">50
			</td>
		</tr>
		<tr>



			<td><input type="submit" name="submit"></td>
		<td>here dehault paper criate area is easy 40 mediun 50 and hard 10</td>


		</tr>
		</table>
		<!-- <input type="text" name="Subject"> -->	
	</form>
<?php



if ($_SERVER["REQUEST_METHOD"] == "POST") {

$S_ID=$_POST['S_ID'];
$Subject=$_POST['Subject'];
#NOQ=5;

echo $Subject[0];
$NOQ=$_POST['NOQ'];
$Sutdent = array('S_ID' => $S_ID, 'Subject' => $Subject , 'NOQ' => $NOQ );


json_encode($Sutdent);

/*//API URL
$url = 'paper.php';

//create a new cURL resource
$ch = curl_init($url);

//attach encoded JSON string to the POST fields
curl_setopt($ch, CURLOPT_POSTFIELDS, $Subject);

//set the content type to application/json
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

//return response instead of outputting
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

//execute the POST request
$result = curl_exec($ch);

//close cURL resource
curl_close($ch);



header("Location:paper.php");*/
}
?>
</body>
</html>