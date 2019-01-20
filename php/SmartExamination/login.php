<?php

$json = file_get_contents('php://input');
$obj = json_decode($json);

#echo $obj->{'Subject'};
#echo $obj->{'NOQ'};


if ($_SERVER["REQUEST_METHOD"] == "POST") {

 

 $email = $obj->{'Email'};
 $password = $obj->{'Password'};

 $conn=mysqli_connect("localhost","root","","smartexamination") or die('error while connecting...');    
 $password=md5($password);
 



$query1=mysqli_query($conn," SELECT * FROM `students` WHERE email='$email'");

	


 
 while($row1=mysqli_fetch_array($query1,MYSQLI_BOTH)){;

    //echo  array($row1);
    //echo     $password;
    
    if(strcmp($row1["password"], $password)==0)
        { echo "succesful" ;

        date_default_timezone_set("Asia/Kolkata");
        $LastLoginTime=date('y-m-d h:i:s');
        $sql = "UPDATE students SET Last_Login_Time='$LastLoginTime' WHERE email='$email'";
        mysqli_query($conn, $sql);
    			
		}
    else
        { echo "Password is incorrect" ;    } 

    return 0;
        
            //$s=array($row1[0]=>$paper);

}

echo "Email is not registerd";

/*if (($row1=mysqli_fetch_array($query1,MYSQLI_BOTH)) {
		echo "string";
		# code...
	}
*/

}
?> 