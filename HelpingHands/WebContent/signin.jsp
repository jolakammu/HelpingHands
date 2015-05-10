
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<sql:setDataSource dataSource="jdbc/DB" />

<c:import url="header.jsp"></c:import>
<title>Helping Hands</title>
</head>
<body bgcolor="lightgrey">
	<c:import url="navbar.jsp"></c:import> 
	<h2>Sign-in</h2>

<form action="SignupServlet" method="post" class="form-horizontal">
  <c:if test="${not empty error}">
  	<div class="alert alert-danger" role="alert">${error }</div>
  </c:if>	
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-5">
      <input type="email" class="form-control" id="email"  name="email" placeholder="Email" maxlength="254" required="required" value="${email}">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-3">
      <input type="password" class="form-control" id="password" name="password" placeholder="Password" maxlength="8" required="required">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Remember me
        </label>
      </div>
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <input type="submit" value="Sign In" id="submit" name="Sign In" class="btn btn-default">
      <input type="reset"  value="Clear" id="Clear" name="Clear" class="btn btn-default">
    </div>
  </div>
  
</form>
<myTags:Footer/>
</body>
</html>