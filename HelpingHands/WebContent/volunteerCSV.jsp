
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
<h2 class="text-center">Upload Volunteering Opportunities CSV file</h2>
<form action=SignupServlet method="post" class="form-horizontal" enctype="multipart/form-data">
  <c:if test="${not empty error}">
  	<div class="alert alert-danger" role="alert">${error}</div>
  </c:if>	
  <div class="form-group">
    <label for="fileinput" class="col-sm-2 control-label">File input</label>
    <div class="col-sm-4">
      <input type="file" class="form-control" id="file"  placeholder="choose file" maxlength="254" required="required">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <input type="submit" value="Upload" id="Upload" name="Upload" class="btn btn-default">
      <input type="reset"  value="Clear" id="Clear" name="Clear" class="btn btn-default">
    </div>
  </div>
  
</form>
<myTags:Footer/>
</body>
</html>