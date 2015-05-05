
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
<h2 class="text-center">Sign-Up Page</h2>
<form class="form-horizontal">
  <div class="form-group">
    <label for="email" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-4">
      <input type="email" class="form-control" id="email" placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-2">
      <input type="password" class="form-control" id="password" placeholder="Password">
    </div>
  </div>

  <div class="form-group">
    <label for="name" class="col-sm-2 control-label">Name</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="name" placeholder="Name">
    </div>
  </div>
  <div class="form-group">
    <label for="delivery" class="col-sm-2 control-label">Delivery</label>
    <div class="col-sm-5">
      <input type="text" class="form-control" id="delivery" placeholder="13031 scofield farms dr">
    </div>
  </div>
  <div class="form-group">
    <label for="city" class="col-sm-2 control-label">City</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" id="city" placeholder="Austin">
    </div>
  </div>
  <div class="form-group">
    <label for="state" class="col-sm-2 control-label">State</label>
    <div class="col-sm-1">
      <input type="text" class="form-control" id="state" placeholder="TX">
    </div>
  </div>
  <div class="form-group">
    <label for="country" class="col-sm-2 control-label">Country</label>
    <div class="col-sm-1">
      <input type="text" class="form-control" id="country" placeholder="USA">
    </div>
  </div>
  <div class="form-group">
  <label for="country" class="col-sm-2 control-label">Purpose</label>
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> To Donate
        </label>
        <br>
        <label>
          <input type="checkbox"> To apply for help
        </label>
        <br>
        <label>
          <input type="checkbox"> To Volunteer
        </label>
      </div>
    </div>
  </div>
	

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Save</button>
      <a class="btn btn-default" href="#" role="button">Clear</a>
    </div>
  </div>
</form>
<myTags:Footer/>
</body>
</html>