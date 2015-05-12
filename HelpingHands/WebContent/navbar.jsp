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
        <li class="active"><a href="index.jsp">Home<span class="sr-only">(current)</span></a></li>
        <!-- <li><a href="donate.jsp">Donate</a></li> -->
        <c:choose>
        	<c:when test="${role == 'ADMIN'}">
        		<li><a href="volunteering.jsp">Volunteering List</a></li>
        	</c:when>
        	<c:otherwise>
        		<li><a href="volunteer.jsp">Volunteering List</a></li>
			</c:otherwise>
		</c:choose>			        		 		
        <!-- <li><a href="applyforhelp.jsp">Apply for help</a></li> -->
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Search Site</button>
      </form>
      <c:if test="${role == 'ADMIN'}">
 	  <ul class="nav navbar-nav navbar-right">        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Utilities<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="volunteerCSV.jsp">Upload Volunteering CSV</a></li>
            <li><a href="siteindex.jsp">ore..</a></li>
          </ul>
        </li>
      </ul>      
      </c:if>
 	  <ul class="nav navbar-nav navbar-right">        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">more... <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="siteindex.jsp">FAQs</a></li>
            <li><a href="siteindex.jsp">Site Index</a></li>
            <li><a href="#">Contact us</a></li>
            <li class="divider"></li>
            <li><a href="#">About</a></li>
          </ul>
        </li>
      </ul>      
      <ul class="nav navbar-nav navbar-right">
      	<c:if test="${isLoggedIn == false or (empty isLoggedIn) }">
			<li><a href="signin.jsp">Sign-In</a></li>
			<li><a href="SignupServlet">Sign-Up</a></li>	
		</c:if>			
		<c:if test="${isLoggedIn == true }">
			<li><a href="LogoutServlet">Sign-Out</a></li>	
		</c:if>			
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
  <div class="container-fluid">
	<c:if test="${isLoggedIn == true }">
		<ul class="nav navbar-nav navbar-right">
			<li>Welcome ${userName}</li>
		</ul>
	</c:if>
	</div>
  
</nav>
