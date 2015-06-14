
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<sql:setDataSource dataSource="jdbc/DB" />

<c:import url="/WEB-INF/header.jsp"></c:import>
<title>Helping Hands</title>
</head>
<body bgcolor="lightgrey">
	<c:import url="/WEB-INF/navbar.jsp"></c:import> 	
	<h1 class="text-center">Helping Hands</h1>
	<c:if test="${param.signupSuccess != null}">
		<div align="center">
			<h4>Sign-up Successful</h4>
			<h4>Welcome to Helping Hands ${userName}</h4>
		</div>
	</c:if>
	<div align="center">
		<h3>Overview</h3>	
		<h3>Our Family</h3>
		<h3>Mission</h3>
		<h3>Vission</h3>
	</div>		
	<myTags:Footer/>
</body>
</html>
