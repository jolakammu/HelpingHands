<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>  

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.jsp"><img src="images/hh.jpg" height="30px"></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      <c:choose>
  	  	<c:when test="${servlet == 'home'}">
        	<li class="active"><a href="home">Home<span class="sr-only">(current)</span></a></li>
		</c:when>
		<c:otherwise>
        	<li><a href="home">Home<span class="sr-only">(current)</span></a></li>		
		</c:otherwise>
	  </c:choose>		        	
        <!-- <li><a href="DonateServlet">Donate</a></li> -->
        <c:if test="${isLoggedIn == true }">
        	<c:choose>
        		<c:when test="${role == 'ADMIN'}">
        			<li><a href="VolunteeringListServlet?nav=Y">Manage Volunteering List</a></li>
        		</c:when>
        		<c:otherwise>
        			<li><a href="VolunteeringSchListServlet?nav=Y">Volunteering List</a></li>
				</c:otherwise>
			</c:choose>			        		 		
        	<!-- <li><a href="WebContent/WEB_INF/applyforhelp.jsp">Apply for help</a></li> -->
		</c:if>        	
        <c:if test="${isLoggedIn == true }">
        	<c:choose>
        		<c:when test="${role != 'ADMIN'}">
        			<li><a href="SupportingDocumentServlet?nav=Y">Supporting Documents</a></li>
        		</c:when>
			</c:choose>			        		 		
        	<!-- <li><a href="WebContent/WEB_INF/applyforhelp.jsp">Apply for help</a></li> -->
		</c:if>        	
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Search Site</button>
      </form>
 	  <ul class="nav navbar-nav navbar-right">        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">more... <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="">FAQs</a></li>
            <li><a href="">Site Index</a></li>
            <li><a href="#">Contact us</a></li>
            <li class="divider"></li>
            <li><a href="thankyou.jsp">Thank You</a></li>
            <li><a href="#">About</a></li>
          </ul>
        </li>
      </ul>      
      <c:if test="${role == 'ADMIN' and isLoggedIn == true}">
	 	  <ul class="nav navbar-nav navbar-right">        
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Admin Utilities<span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="LoadVolunteerItemsServlet">Upload Volunteering CSV</a></li>
	            <li><a href="">User Maintenance</a></li>
	          </ul>
	        </li>
	      </ul>      
      </c:if>
      
      <ul class="nav navbar-nav navbar-right">
      	<c:if test="${isLoggedIn == false or (empty isLoggedIn) }">
			<c:choose>
  				<c:when test="${servlet == 'login'}">
					<li class="active"><a href="login?nav=Y">Sign-In</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="login?nav=Y">Sign-In</a></li>
				</c:otherwise>
			</c:choose>													
			<c:choose>
  				<c:when test="${servlet == 'signup'}">
					<li class="active"><a href="signup?nav=Y">Sign-Up</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="signup?nav=Y">Sign-Up</a></li>
				</c:otherwise>					
			</c:choose>
		</c:if>						
		<c:if test="${isLoggedIn == true }">
			<li><a href="logout">Sign-Out</a></li>	
		</c:if>			
      </ul>
    </div> <!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
  <div class="container-fluid">
	<c:if test="${isLoggedIn == true }">
		<ul class="nav navbar-nav navbar-right">
			<li>Welcome ${userName}<c:if test="${role == 'ADMIN' and isLoggedIn == true}"> (Admin) </c:if></li>
		</ul>
	</c:if>
	</div>
  
</nav>
