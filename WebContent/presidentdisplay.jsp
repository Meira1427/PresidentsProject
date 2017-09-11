<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--allows us to do whens/otherwise/if  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!--formats dates and numbers  -->
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/stylesheet.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>U.S. Presidents</title>
</head>
<body>
    <div class="nav">
        <ul>
          <li><a href="home.do">Home</a></li>
          <li><a href="About.html">About</a></li>
        </ul>
    </div>
    <div class ="main">
      <img src ="${currentPresident.imageLink}" class ="picture">
    <h1>US Presidents</h1>
    <h3>${currentPresident.firstName} ${currentPresident.middleName} ${currentPresident.lastName}</h3>
    <h3>${currentPresident.party}</h3>
    <h3>${currentPresident.termRange}</h3>
    <p>Reason Left Office: ${currentPresident.reason}</p>
  	<br>
  	<form action="president.do" method="GET">
	  	<input type="radio" name="search" value="lastname">Last Name</input>
	  	<input type="radio" name="search" value="firstname">First Name</input>
	  	<input type="radio" name="search" value="party">Party</input>
	  	<input type="radio" name="search" value="term">Term Num</input>
	  	<br>
		Search: <input type="text" name="selection"></input>
		<input type="submit" name="submit" value="Submit"></input>
		<br>
		<br>
		<input type="submit" name="full" value="Reset Full List of Presidents"></input>
		<br>
		<br>
		<input type="submit" name="previous" value="Previous President"></input>
		<input class="left" type="submit" name="next" value="Next President"></input>	
	</form>
	</div>
  </body>
</html>