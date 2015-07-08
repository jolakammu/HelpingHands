
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<sql:setDataSource dataSource="jdbc/DB" />

<c:import url="/WEB-INF/header.jsp"></c:import>
<title>HelpingHands</title>
</head>
<body bgcolor="lightgrey">
	 <c:import url="/WEB-INF/navbar.jsp"></c:import>
	<h2 class="text-center">Volunteer Page</h2>
<myTags:Footer/>
</body>
</html>