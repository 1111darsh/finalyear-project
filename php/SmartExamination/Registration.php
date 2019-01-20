<?php

$json = file_get_contents('php://input');
$obj = json_decode($json);

#echo $obj->{'Subject'};
#echo $obj->{'NOQ'};


if ($_SERVER["REQUEST_METHOD"] == "POST") {

 $Sid;

 $Name = $obj->{'Name'};
 $Lastname = $obj->{'Lastname'};
 $email = $obj->{'Email'};
 $password = $obj->{'Password'};
 $contect_no = $obj->{'contect_no'};
 $degree = $obj->{'degree'};
 $graduate_year = $obj->{'graduate_year'};

 $conn=mysqli_connect("localhost","root","","smartexamination") or die('error while connecting...');	
 $password=md5($password);
 $Sid = $Name;
 date_default_timezone_set("Asia/Kolkata");
 $AcCreatedTime=date('y-m-d h:i:s');
 $otp=rand(1000,9999);

 $Sql_Query = "INSERT INTO `students`(`S_ID`, `Name`, `Lastname`, `email`, `password`, `contect_no`,`Account_Created_Date`,`OTP`) VALUES ('$Sid','$Name','$Lastname','$email','$password','$contect_no','$AcCreatedTime','$otp')";

 if($Sql_Query==1)
 {
 $response['error']=false;
 $response['msg']="Data Submit Successfully";
 }
 if(mysqli_query($conn,$Sql_Query)){

 //echo "Successfully Registration complated";	
 
 }
 else{
 
 //echo 'Try Again';
 
 }

 $Sql_Query = "INSERT INTO `students_qualification`(`S_ID`, `degree`, `graduate_year`) VALUES ('$Sid','$degree','$graduate_year')";

 if($Sql_Query==1)
 {
 $response['error']=false;
 $response['msg']="Data Submit Successfully";
 }

 if(mysqli_query($conn,$Sql_Query)){

 echo "Successfully Registration complated";	
 
 }
 else{
 
 echo 'Try Again';
 
 }
}
?> 