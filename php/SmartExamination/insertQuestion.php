<!DOCTYPE html>
<html>
<head>
	<title>insert question</title>
</head>
<body>
	<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
		<table>
			<tr>
				<td>subject</td>
				<td>
				<input type="text" name="Subject"/></td>
			</tr>
			<tr>
				
				<td>Qid</td>
				<td><input type="text" name="Qid"/></td>
			</tr>
			<tr>
				
				<td>Question</td>
				<td><input type="text" name="Question"/></td>
			</tr>
			<tr>
				<td>a</td>
				<td><input type="text" name="a"/></td>
			</tr>
			<tr>
				<td>b</td>
				<td><input type="text" name="b"/></td>
			</tr>
			<tr>
				<td>c</td>
				<td><input type="text" name="c"/></td>
			</tr>
			<tr>
				<td>d</td>
				<td><input type="text" name="d"/></td>
			</tr>
			<tr>
				<td>ans</td>
				<td><input type="radio" name="ans" value='a'/>a
					<input type="radio" name="ans" value='b'/>b
					<input type="radio" name="ans" value='c'/>c
					<input type="radio" name="ans" value='d'/>d</td>
			</tr>
			<tr>
				<td>attempt</td>
				<td><input type="number" name="attempt"/>
			</tr>
			<tr>
				<td>right_attempt</td>
				<td><input type="number" name="right_attempt"/></td>
			</tr>
			<tr><td colspan="2" ><input type="submit" name="submit" value="submit"> </td></tr>

		</table>
		
	</form>


<?php

$conn=mysqli_connect("localhost","root","","smartexamination") or die('error while connecting...');	

echo "connected";


if ($_SERVER["REQUEST_METHOD"] == "POST") {
 $Subject=$_POST['Subject'];
 $Qid= $_POST['Qid'];
 $Question= $_POST['Question'];
 $a= $_POST['a'];
 $b= $_POST['b'];
 $c= $_POST['c']; 
 $d= $_POST['d'];
 $ans= $_POST['ans'];
 $attempt=(int)$_POST['attempt'];
 $right_attempt=(int)$_POST['right_attempt'];
 $criteria;




if($ans='a')
 {
 	$ans=$a;
 }
 elseif($ans='b')
 {
 	$ans=$b;
 }
 elseif($ans='c')
 {
 	$ans=$c;
 }
 elseif($ans='d')
 {
 	$ans=$d;
 }
 else
 {
 	echo "problem in ans";
 }	


		
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$sql = "INSERT INTO `questions_bank` (`Qid`, `Question`, `a`, `b`, `c`, `d`, `ans`) VALUES ('".$Qid."','".$Question."','".$a."','".$b."','". $c."','".$d."','".$ans."')";

//$sql = "INSERT INTO `question_bank` (`Qid`, `Question`, `a`, `b`, `c`, `d`, `ans`) VALUES ('2','5'.",".$a.",".$b.",". $c.",".$d.",".$ans.")";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
    }

$sql = "INSERT INTO `questions_criteria`(`Subject`, `Qid`, `attempt`, `right_attempt`, `avg`, `criteria`) VALUES ('".$Subject."','".$Qid."','".$attempt."','".$right_attempt."','".$avg."','".$criteria."')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}



}
$conn->close();


?>

</body>
</html>