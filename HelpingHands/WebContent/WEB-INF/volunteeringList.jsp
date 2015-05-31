
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
	<h2 class="text-center">Volunteer Opportunities</h2>
	<div class="table-responsive">
 	<c:forEach items="${vilMap}" var="vilMap">
  		<table class="table">
  			<tr class="active">
  				<th>${vilMap.key}</th>
  			</tr>
			<!-- On rows -->
			
			<tr class="active">
				<th>Organization Name</th>
				<th>Category</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Start Date & Time</th>
				<th>Needed hrs.</th>
				<th>Filled hrs.</th>
				<th>Available hrs.</th>
			</tr>
			<!-- On cells (`td` or `th`) -->
				<c:forEach var="vil" items="${vilMap.value}">
					<tr>
  						<td class="active">${vil.orgName}</td>
	  					<td class="active">${vil.orgCategory}</td>
  						<td class="active">${vil.address.delivery} ${vil.address.city} ${vil.address.state} ${vil.address.country} ${vil.address.zip}</td>
  						<td class="active">${vil.elecCommu.elecCommuNum}</td>
  						<td class="active"></td>
  						<td class="active"></td>
  						<td class="active">0</td>
	  					<td class="active">${vil.manHrs}</td>
					</tr>
				</c:forEach>					  						
			</table>
		</c:forEach>			
	</div>
<myTags:Footer/>
</body>
</html>