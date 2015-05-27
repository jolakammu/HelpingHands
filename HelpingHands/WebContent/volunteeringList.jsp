
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
  		<table class="table">
  			<tr class="active">
  				<th>January 2016</th>
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
			
			<c:forEach items="${vilArray}" var="vil">
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
		
		<br>
		
		  		<table class="table">
  			<tr class="active">
  				<th>February 2016</th>
  			</tr>
			<!-- On rows -->
			<tr class="active">
				<th>Organization Name</th>
				<th>Category</th>
				<th>Address</th>
				<th>Work Phone</th>
				<th>Mobile Phone</th>
				<th>Start Date & Time</th>
				<th>Needed hrs.</th>
				<th>Filled hrs.</th>
				<th>Available hrs.</th>
			</tr>
			<!-- On cells (`td` or `th`) -->
			<tr>
  				<td class="active">Capital Area Food Bank</td>
  				<td class="active">Food</td>
  				<td class="active">900 S Congress Avenue, Austin, TX</td>
  				<td class="active">(512)-239-3000</td>
  				<td class="active">(512)-239-3000</td>
  				<td class="active">01/01/2016</td>
  				<td class="active">10</td>
  				<td class="active">8</td>
  				<td class="active">2</td>
			</tr>  		
		</table>
		
	</div>
	
	
<myTags:Footer/>
</body>
</html>