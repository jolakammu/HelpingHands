<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
  				<th>${vilMap.key.yearNum} ${vilMap.key.monthName}</th>
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
				<c:forEach var="vil" items="${vilMap.value}">
					<tr>
						<td class="active"><a href="DeleteVolunteeringSchItem?volunteertemId=${vil.volunteertemId}"><img src="images/delete.jpg" border="0" height="20px" width="20px"></a></td>
  						<td class="active">${vil.orgName}</td>
	  					<td class="active">${vil.orgCategory}</td>
  						<td class="active">${vil.address.delivery} ${vil.address.city} ${vil.address.state} ${vil.address.country} ${vil.address.zip}</td>
  						<td class="active">${vil.elecCommu.elecCommuNum}</td>
  						<td class="active">${vil.workBeginDtTime}</td>
  						<td class="active">${vil.manHrs}</td>
  						<td class="active"><c:out value="${filledHrsMap[vil.volunteertemId]}"/></td>
	  					<td class="active"><c:out value="${vil.manHrs - filledHrsMap[vil.volunteertemId]}"/></td>
					</tr>
				</c:forEach>					  						
			</table>
		</c:forEach>			
	</div>
<myTags:Footer/>
</body>
</html>