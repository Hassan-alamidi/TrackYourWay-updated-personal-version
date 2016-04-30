<?php
	$config = parse_ini_file('config.ini');
	//password is empty because cloud 9 seems to break when adding passwords to the database
	//$dbconnect=mysqli_connect($config['dburl'],$config['username'],"",$config['dbname']);
	$password = "";
	$connect= new mysqli("127.0.0.1",$config['username'],$password,$config['dbname'], $config['dbPort']);
	//$connect= new mysqli($config['dburl'],$config['username'],$config['password'],$config['dbname'], 3306);
?>