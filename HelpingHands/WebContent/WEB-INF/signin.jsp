
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
	<h2>Sign-in</h2>

<form action="login" method="post" class="form-horizontal">
  <c:if test="${not empty error}">
  	<div class="alert alert-danger" role="alert">${error }</div>
  </c:if>	
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Email<span style="color: red;">*</span></label>
    <div class="col-sm-5">
      <input type="email" class="form-control" id="email" name="email" placeholder="Enter a valid Email address" maxlength="254" value="${cookie.email.value}"  required title="Provide Email">
    </div> 
    <div class="help-block with-errors"></div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Password<span style="color: red;">*</span></label>
    <div class="col-sm-3">
      <input type="password" class="form-control" id="password" name="password" placeholder="Password" maxlength="8" required="required" title="Provide password">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox" id="remember" name="remember" value="YES"  <c:if test="${cookie.remember.value=='YES'}">checked="checked"</c:if>> Remember me
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
<span style="color: red;">*</span> mandatory
<myTags:Footer/>
</body>
</html>