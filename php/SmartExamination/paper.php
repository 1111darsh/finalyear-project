
<?php

$json = file_get_contents('php://input');
$obj = json_decode($json);

#echo $obj->{'Subject'};
#echo $obj->{'NOQ'};


if ($_SERVER["REQUEST_METHOD"] == "POST") {

$Subject=$obj->{'Subject'};
#$NOQ=5;

$NOQ=$obj->{'NOQ'};

$hard=(int)($NOQ/5);
$medium=(int)($NOQ/2);
$easy=$NOQ-$medium-$hard;

echo $NOQ.$hard.$medium.$easy."\n";

$paper=array();

$conn=mysqli_connect("localhost","root","","smartexamination") or die('error while connecting...');	

$i=0;
do {
$q1=mysqli_query($conn,"SELECT COUNT(Qid) AS Q FROM questions_criteria WHERE criteria='hard' AND Subject='".$Subject."'");
while($row2=mysqli_fetch_array($q1,MYSQLI_BOTH)){;
	

	$dbhard=$row2["Q"];
	//echo $newhard;
}
if($dbhard<$hard)
{
	$newhard=$dbhard;
}
else
{
	$newhard=$hard;	
}


$query1=mysqli_query($conn,"SELECT `Subject`, `Qid`, `attempt`, `right_attempt`, `avg`, `criteria` FROM `questions_criteria` WHERE (attempt= ALL(SELECT MIN(attempt)+$i FROM questions_criteria WHERE Subject='".$Subject."') AND criteria='hard' and Subject='".$Subject."') LIMIT ".$newhard."");

while($row1=mysqli_fetch_array($query1,MYSQLI_BOTH)){;
	//echo $row1["Qid"];
	$query=mysqli_query($conn,"SELECT `Qid`, `Question`, `a`, `b`, `c`, `d`, `ans` FROM `questions_bank` WHERE Qid='".$row1['Qid']."'" );
		while($row=mysqli_fetch_array($query,MYSQLI_BOTH) AND sizeof($paper)<$newhard){

			array_push($paper,array("Qid"=>$row[0],"Question"=>$row[1],"a"=>$row[2],"b"=>$row[3],"c"=>$row[4],"d"=>$row[5],"ans"=>$row[6]));	
		}				//$s=array($row1[0]=>$paper);
}
$i++;
//echo sizeof($paper);
} while (sizeof($paper)<$newhard);	# code...



$medium=$medium+($hard-$newhard);


$i=0;
do {
$q1=mysqli_query($conn,"SELECT COUNT(Qid) AS Q FROM questions_criteria WHERE criteria='medium' AND Subject='".$Subject."'");
while($row2=mysqli_fetch_array($q1,MYSQLI_BOTH)){;
	$dbmedium=$row2["Q"];
	// echo $newmedium;
}

if($dbmedium<$medium)
{
	$newmedium=$dbmedium;
}
else
{
	$newmedium=$medium;
}


$query1=mysqli_query($conn,"SELECT `Subject`, `Qid`, `attempt`, `right_attempt`, `avg`, `criteria` FROM `questions_criteria` WHERE (attempt= ALL(SELECT MIN(attempt)+$i FROM questions_criteria WHERE Subject='".$Subject."') AND criteria='medium' and Subject='".$Subject."') LIMIT ".$newmedium."");

while($row1=mysqli_fetch_array($query1,MYSQLI_BOTH)){;
	//echo $row1["Qid"];
	$query=mysqli_query($conn,"SELECT `Qid`, `Question`, `a`, `b`, `c`, `d`, `ans` FROM `questions_bank` WHERE Qid='".$row1['Qid']."'" );
		while($row=mysqli_fetch_array($query,MYSQLI_BOTH) AND sizeof($paper)<$newmedium+$newhard){

			array_push($paper,array("Qid"=>$row[0],"Question"=>$row[1],"a"=>$row[2],"b"=>$row[3],"c"=>$row[4],"d"=>$row[5],"ans"=>$row[6]));	
		}				//$s=array($row1[0]=>$paper);
}
$i++;
//echo sizeof($paper);
} while (sizeof($paper)<$newmedium+$newhard);


$easy=$easy+($medium-$newmedium);

$i=0;
do {
$q1=mysqli_query($conn,"SELECT COUNT(Qid) AS Q FROM questions_criteria WHERE criteria='easy' AND Subject='".$Subject."'");
while($row2=mysqli_fetch_array($q1,MYSQLI_BOTH)){;
	$dbeasy=$row2["Q"];
	//echo $neweasy;
}

if($dbeasy<$easy)
{
	$neweasy=$dbeasy;
}
else
{
	$neweasy=$easy;	
}

$query1=mysqli_query($conn,"SELECT `Subject`, `Qid`, `attempt`, `right_attempt`, `avg`, `criteria` FROM `questions_criteria` WHERE (attempt= ALL(SELECT MIN(attempt)+$i FROM questions_criteria WHERE Subject='".$Subject."') AND criteria='easy' and Subject='".$Subject."') LIMIT ".$neweasy."");

while($row1=mysqli_fetch_array($query1,MYSQLI_BOTH)){;
	//echo $row1["Qid"];
	$query=mysqli_query($conn,"SELECT `Qid`, `Question`, `a`, `b`, `c`, `d`, `ans` FROM `questions_bank` WHERE Qid='".$row1['Qid']."'" );
		while($row=mysqli_fetch_array($query,MYSQLI_BOTH) AND sizeof($paper)<$neweasy+$newmedium+$newhard){

			array_push($paper,array("Qid"=>$row[0],"Question"=>$row[1],"a"=>$row[2],"b"=>$row[3],"c"=>$row[4],"d"=>$row[5],"ans"=>$row[6]));	
		}	
				//$s=array($row1[0]=>$paper);
}
echo $neweasy;
$i++;
echo sizeof($paper)."\n";
} while (sizeof($paper)<$neweasy+$newmedium+$newhard);



//if()

echo $newhard.$newmedium.$neweasy;



//echo $newhard.$newmedium."\n";
//	echo sizeof($paper);
echo json_encode(array('Question_Paper' =>$paper));
}
else
{
	echo "There is no input";
}
?>


