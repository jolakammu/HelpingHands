
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<sql:setDataSource dataSource="jdbc/DB" />

<c:import url="header.jsp"></c:import>
<title>HelpingHands</title>
</head>
<body bgcolor="lightgrey">
	<c:import url="navbar.jsp"></c:import> 	
	<h1 class="text-center">Helping Hands</h1>
	<div align="center">
		<h3>Overview</h3>	
		<h3>Our Family</h3>
		<h3>Mission</h3>
		<h3>Vission</h3>
	</div>		
	<myTags:Footer/>
</body>
</html>
