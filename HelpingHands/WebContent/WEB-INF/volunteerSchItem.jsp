
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
	<h2 class="text-center">Volunteer Scheduled Opportunities</h2>
	<h4>Scheduled</h4>
	<hr>
	<div class="table-responsive">
 	<c:forEach items="${vsilMap}" var="vsilMap">
  		<table class="table">
  			<tr class="active">
  				<th>${vsilMap.key.yearNum} ${vsilMap.key.monthName}</th>
  			</tr>
			<!-- On rows -->
			
			<tr class="active">
				<th>&nbsp;</th>
				<th>Organization Name</th>
				<th>Category</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Start Date & Time</th>
				<th>Scheduled hrs.</th>
			</tr>
			<!-- On cells (`td` or `th`) -->
				<c:forEach var="vil" items="${vsilMap.value}">
					<tr>
  						<td class="active"><a href="DeleteVolunteeringSchItem?volunteerSchItemId=${vil.volunteerSchItemId}"><img src="images/delete.jpg" border="0" height="20px" width="20px"></a></td>
  						<td class="active">${vil.volunteerItems.orgName} ${vil.volunteerSchItemId}</td>
	  					<td class="active">${vil.volunteerItems.orgCategory}</td>
  						<td class="active">${vil.volunteerItems.address.delivery} ${vil.volunteerItems.address.city} ${vil.volunteerItems.address.state} ${vil.volunteerItems.address.country} ${vil.volunteerItems.address.zip}</td>
  						<td class="active">${vil.volunteerItems.elecCommu.elecCommuNum}</td>
  						<td class="active">${vil.volunteerItems.workBeginDtTime}</td>
  						<td class="active">${vil.signedManhrs}</td>
					</tr>
				</c:forEach>					  						
			</table>
		</c:forEach>			
	</div>

	<h4>Available</h4>
	<hr>
	<div class="table-responsive">
	<form action="CreateVolunteeringItemSch" method="post" class="form-horizontal">
 	<c:forEach items="${vilMap}" var="vilMap">
  		<table class="table">
  			<tr class="active">
  				<th>${vilMap.key.yearNum} ${vilMap.key.monthName}</th>
  			</tr>
			<tr class="active">
				<th>Organization Name</th>
				<th>Category</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Start Date & Time</th>
				<th>Needed hrs.</th>
				<th>Filled hrs.</th>
				<th>Available hrs.</th>
				<th>Select. Hrs</th>
			</tr>
					<c:forEach var="vil" items="${vilMap.value}">
						<tr>
							<td class="active">${vil.orgName}</td>
							<td class="active">${vil.orgCategory}</td>
							<td class="active">${vil.address.delivery}
								${vil.address.city} ${vil.address.state} ${vil.address.country}
								${vil.address.zip}</td>
							<td class="active">${vil.elecCommu.elecCommuNum}</td>
							<td class="active">${vil.workBeginDtTime}</td>
							<td class="active">${vil.manHrs}</td>
							<td class="active"><c:out
									value="${filledHrsMap[vil.volunteertemId]}" /></td>
							<td class="active"><c:out
									value="${vil.manHrs - filledHrsMap[vil.volunteertemId]}" /></td>
							<td><select name="${vil.volunteertemId}"
								id="${vil.volunteertemId}">
									<option value="0">0</option>
									<c:forEach begin="1"
										end="${vil.manHrs - filledHrsMap[vil.volunteertemId]}"
										varStatus="loop">
										<option value="<c:out value="${loop.count}"/>"><c:out
												value="${loop.count}" /></option>
									</c:forEach>
							</select></td>
						</tr>
					</c:forEach>
				</table>
		</c:forEach>			
 	    <div class="form-group">
    		<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Save" id="Save" name="Save" class="btn btn-default">
				<input type="reset"  value="Clear" id="Clear" name="Clear" class="btn btn-default">
    		</div>
  		</div>
	</form>					  						
	</div>
<myTags:Footer/>
</body>
</html>