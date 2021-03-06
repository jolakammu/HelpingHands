
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
			
			<tr>		
				<th width="7%">&nbsp;</th>
				<th width="20%">Organization Name</th>
				<th width="10%">Category</th>
				<th width="20%">Address</th>
				<th width="10%">Phone</th>
				<th width="10%">Start Date & Time</th>
				<th width="10%">Scheduled hrs.</th>
			</tr>
			<!-- On cells (`td` or `th`) -->
				<c:forEach var="vil" items="${vsilMap.value}">
					<tr>
  						<td><a href="DeleteVolunteeringSchItem?volunteerSchItemId=${vil.volunteerSchItemId}"><img src="images/delete.jpg" border="0" height="20px" width="20px"></a></td>
  						<td>${vil.volunteerItems.orgName}</td>
	  					<td>${vil.volunteerItems.orgCategory}</td>
  						<td>${vil.volunteerItems.address.delivery} ${vil.volunteerItems.address.city} ${vil.volunteerItems.address.state} ${vil.volunteerItems.address.country} ${vil.volunteerItems.address.zip}</td>
  						<td>${vil.volunteerItems.elecCommu.elecCommuNum}</td>
  						<td>${vil.volunteerItems.workBeginDtTime}</td>
  						<td>${vil.signedManhrs}</td>
					</tr>
				</c:forEach>					  						
			</table>
		</c:forEach>	
		<c:if test="${empty vsilMap}">
			<table class="table">
			<tr>
				<th>Organization Name</th>
				<th>Category</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Start Date & Time</th>
				<th>Scheduled hrs.</th>
			</tr>
			<tr>
				<td colspan="6">Volunteer Opportunities not Scheduled</td>
			</tr>	
			</table>	
		</c:if>		
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
			<tr  class="active">
				<th width="20%">Organization Name</th>
				<th width="10%">Category</th>
				<th width="20%">Address</th>
				<th width="10%">Phone</th>
				<th width="10%">Start Date & Time</th>
				<th width="10%">Needed hrs.</th>
				<th width="10%">Filled hrs.</th>
				<th width="10%">Available hrs.</th>
				<th width="10%">Select. Hrs</th>
			</tr>
					<c:forEach var="vil" items="${vilMap.value}">
						<tr>
							<td>${vil.orgName}</td>
							<td>${vil.orgCategory}</td>
							<td>${vil.address.delivery}
								${vil.address.city} ${vil.address.state} ${vil.address.country}
								${vil.address.zip}</td>
							<td>${vil.elecCommu.elecCommuNum}</td>
							<td>${vil.workBeginDtTime}</td>
							<td>${vil.manHrs}</td>
							<td><c:out
									value="${filledHrsMap[vil.volunteertemId]}" /></td>
							<td><c:out
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
		<c:if test="${empty vilMap}">
			<table class="table">
			<tr>
				<th>Organization Name</th>
				<th>Category</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Start Date & Time</th>
				<th>Scheduled hrs.</th>
			</tr>
			<tr>
				<td colspan="6">Volunteer Opportunities not available</td>
			</tr>	
			</table>	
		</c:if>		
		<c:if test="${not empty vilMap}">				
 	    	<div class="form-group">
    			<div class="col-sm-offset-5 col-sm-10">
					<input type="submit" value="Save" id="Save" name="Save" class="btn btn-default">
					<input type="reset"  value="Clear" id="Clear" name="Clear" class="btn btn-default">
    			</div>
  			</div>
		</c:if>  			
	</form>					  						
	</div>
<myTags:Footer/>
</body>
</html>