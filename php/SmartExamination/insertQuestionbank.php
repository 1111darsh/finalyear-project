<?php

$conn=mysqli_connect("localhost","root","","smartexamination") or die('error while connecting...');	

echo "connected<br>";
		
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$sql = "INSERT INTO `questions_bank` (`Qid`, `Question`, `a`, `b`, `c`, `d`, `ans`) VALUES
('HTML1','HTML stands for?','Hyper Text Markup Language','High Text Markup Language','Hyper Tabular Markup Language','None of these','Hyper Text Markup Language'),
('HTML2','which of the following tag is used to mark a begining of paragraph ?', '<TD>', '<br>', '<P>', '<TR>', '<P>'),
('HTML3','From which tag descriptive list starts ?', '<LL>', '<DD>', '<DL>', '<DS>', '<DL>'),
('HTML4','Correct HTML tag for the largest heading is', '<head>', '<h6>', '<heading>', '<h1>', '<h1>'),
('HTML5','The attribute of <form> tag', 'Method', 'Action', 'Both (a)&(b)', 'None of these', 'Both (a)&(b)'),
('HTML6','Markup tags tell the web browser', 'How to organise the page', 'How to display the page', 'How to display message box on page', 'None of these', 'How to display the page'),
('HTML7','www is based on which model?', 'Local-server', 'Client-server', '3-tier', 'None of these', 'Client-server'),
('HTML8','What are Empty elements and is it valid?', 'No, there is no such terms as Empty Element', 'Empty elements are element with no data', 'No, it is not valid to use Empty Element', 'None of these', 'Empty elements are element with no data'),
('HTML9','Which of the following attributes of text box control allow to limit the maximum character?', 'size', 'len', 'maxlength', 'all of these', 'maxlength'),
('HTML10','Web pages starts with which ofthe following tag?', '<Body>', '<Title>', '<HTML>', '<Form>', '<HTML>')";

//$sql = "INSERT INTO `question_bank` (`Qid`, `Question`, `a`, `b`, `c`, `d`, `ans`) VALUES ('2','5'.",".$a.",".$b.",". $c.",".$d.",".$ans.")";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully<br>";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
    }

$sql = "INSERT INTO `questions_criteria`(`Subject`, `Qid`, `attempt`, `right_attempt`, `avg`, `criteria`) VALUES
('HTML','HTML1','1','1','100','easy'),
('HTML','HTML2','1','1','100','easy'),
('HTML','HTML3','1','1','100','easy'),
('HTML','HTML4','1','1','100','easy'),
('HTML','HTML5','1','1','100','easy'),
('HTML','HTML6','1','1','100','easy'),
('HTML','HTML7','1','1','100','easy'),
('HTML','HTML8','1','1','100','easy'),
('HTML','HTML9','1','1','100','easy'),
('HTML','HTML10','1','1','100','easy')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully<br>";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}




$conn->close();


?>

</body>
</html>