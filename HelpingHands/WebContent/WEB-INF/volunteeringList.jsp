<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<sql:setDataSource dataSource="jdbc/DB" />

<c:import url="/WEB-INF/header.jsp"></c:import>
<title>HelpingHands</title>
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
				<tr class="active">
					<th width="7%">&nbsp;</th>
					<th width="20%">Organization Name</th>
					<th width="5%">Category</th>
					<th width="20%">Address</th>
					<th width="10%">Phone</th>
					<th width="10%">Start Date & Time</th>
					<th width="10%">Needed hrs.</th>
					<th width="10%">Filled hrs.</th>
					<th width="10%">Available hrs.</th>
				</tr>
				<c:forEach var="vil" items="${vilMap.value}">
					<tr>
						<td><a href="DeleteVolunteerItem?volunteerItemId=${vil.volunteertemId}"><img src="images/delete.jpg" border="0" height="20px" width="20px"></a></td>
  						<td>${vil.orgName}</td>
	  					<td>${vil.orgCategory}</td>
  						<td>${vil.address.delivery} ${vil.address.city} ${vil.address.state} ${vil.address.country} ${vil.address.zip}</td>
  						<td>${vil.elecCommu.elecCommuNum}</td>
  						<td>${vil.workBeginDtTime}</td>
  						<td>${vil.manHrs}</td>
  						<td><c:out value="${filledHrsMap[vil.volunteertemId]}"/></td>
	  					<td><c:out value="${vil.manHrs - filledHrsMap[vil.volunteertemId]}"/></td>
					</tr>
				</c:forEach>					  						
			</table>
		</c:forEach>
		<c:if test="${empty vilMap}">
  			<table class="table">
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
				<tr>
					<td colspan="8">Volunteer Opportunities not available</td>
				</tr>
			</table>				
		</c:if>			
	</div>
<myTags:Footer/>
</body>
</html>