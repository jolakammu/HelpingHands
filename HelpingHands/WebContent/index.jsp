
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
	<h1 class="text-center">HelpingHands</h1>
	<c:if test="${param.signupSuccess != null}">
		<div align="center">
			<h4 style="color: red;">Sign-up Successful</h4>
			<h4 style="color: red;">Welcome to HelpingHands ${userName}</h4>
		</div>
	</c:if>
	<div align="center">
		<h2>Overview</h2>	
			<p style="font-size: 20px">
				Helping Hands is a non-governmental, non-religious and non-profit social service organization, established in<br>
				2015, with the sole objective of serving people in need. Life surprises you when you are least prepared for it.  <br> 
				Helping hands was thus born to help anyone who needs a hand to stand and to this day, it continues to give<br>
				hope where there exists none. Helping Hands believes that everyone deserves to be loved
			</p>
		<h2>Our Family</h2>
			<p style="font-size: 20px">
			Helping Hands reach extends across multiple cities in Tamil Nadu, India. We are planning to open more. <br>
			It's been possible for us to do the impossible - give a roof to all who needed one, only due to the support of <br>
			well-wishers and donors. As a non-governmental, non-religious social service organization, we run our homes just <br>
			through the financial and emotional support of public. Like a dew drop that awakens the birth of a flower in a <br>
			seed, it is often a small deed that rains a world of hope to those in despair. Give a little today and <br> 
			experience abundant bliss in return! </p>
		<h2>Mission</h2>
			<p style="font-size: 20px">To help the needy in every possible way</p>
		<h2>Vission</h2>
			<p style="font-size: 20px">To help the needy in every possible way across the GLOBE</p>
	</div>		
	<myTags:Footer/>
</body>
</html>
