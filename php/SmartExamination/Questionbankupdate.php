<?php
$json = file_get_contents('php://input');
$obj = json_decode($json);

if ($_SERVER["REQUEST_METHOD"] == "POST") {


	for ($i=0; $i <sizeof($obj); $i++) { 
		$Qid = $obj[$i]->Qid;
		$statesOfAns = $obj[$i]->statesOfAns; 



		//echo $Qid.$statesOfAns;




		$conn=mysqli_connect("localhost","root","","smartexamination") or die('error while connecting...');    


		$query1=mysqli_query($conn," SELECT * FROM `questions_criteria` WHERE Qid='$Qid'");
		while($row1=mysqli_fetch_array($query1,MYSQLI_BOTH)){;

		

		$attempt=$row1["attempt"];
		
		$attempt++;
		
		$right_attempt=$row1["right_attempt"];




		if(strcmp($statesOfAns,"true")==0)
			$right_attempt++;



		$avg = (int)($right_attempt/$attempt*100);

		$criteria="easy";

		if($avg>=70)
		{
			$criteria="easy";
		}
		elseif ($avg>=30) {
			$criteria="medium";
		}
		else
		{
			$criteria="hard";
		}


		echo $row1["attempt"].$row1["right_attempt"];

		echo "\t".$avg."\n";



		$sql = "UPDATE questions_criteria SET attempt='$attempt', right_attempt='$right_attempt', avg='$avg', criteria='$criteria' WHERE Qid='$Qid'";
        mysqli_query($conn, $sql);




		}




		  
	}
}





?>