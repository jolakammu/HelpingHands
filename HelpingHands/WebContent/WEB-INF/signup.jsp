
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
<form action="SignupServlet"  enctype="multipart/form-data" method="post"  class="form-horizontal">
  <c:if test="${not empty error}">
  	<div class="alert alert-danger" role="alert">${error}</div>
  </c:if>	
  <div class="form-group">
    <label for="email" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-4">
    	<input type="email" class="form-control" id="email"  name="email" placeholder="Email" maxlength="254" required="required" value="<c:out value="${email}"/>">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-2">
      <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required" maxlength="8">
    </div>
  </div>
	<div class="form-group">
    <label for="password" class="col-sm-2 control-label">Confirm Password</label>
    <div class="col-sm-2">
      <input type="password" class="form-control" id="password1" name="password1"placeholder="Confirm Password" required="required" maxlength="8">
    </div>
  </div>
  <div class="form-group">
    <label for="name" class="col-sm-2 control-label">Name</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="name" name="name" placeholder="Name" required="required" value="<c:out value="${name}"/>" maxlength="100">
    </div>
  </div>
  <div class="form-group">
    <label for="delivery" class="col-sm-2 control-label">Delivery</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="delivery" name="delivery" placeholder="13031 scofield farms dr" required="required" value="<c:out value="${delivery}"/>" maxlength="50">
    </div>
  </div>
  <div class="form-group">
    <label for="city" class="col-sm-2 control-label">City</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" id="city" name="city" placeholder="Austin" required="required" value="<c:out value="${city}"/>" maxlength="32">
    </div>
  </div>
  <div class="form-group">
    <label for="state" class="col-sm-2 control-label">State</label>
	<div class="col-sm-2">
    	<select name="state" id="state" class="form-control">
    		<c:forEach var="state" items="${stateList}">
        		<option value="${state.genCd}" ${state.genCd == selectedState ? 'selected="selected"' : ''}>${state.name}</option>
    		</c:forEach>
		</select>
    </div>  
  </div>
  <div class="form-group">
    <label for="country" class="col-sm-2 control-label">Country</label>
    <div class="col-sm-2">
    	<select name="country" id="country" class="form-control">
    		<c:forEach var="country" items="${countryList}">
        		<option value="${country.genCd}" ${country.genCd == selectCountry ? 'selected="selected"' : ''}>${country.name}</option>
    		</c:forEach>
		</select>
    </div>
  </div>
  <div class="form-group">
    <label for="phonenumber" class="col-sm-2 control-label">Work Phone</label>
    <div class="row">
    	<div class="col-xs-2">
      		<input type="tel" class="form-control" id="phonenumber" name="phonenumber" placeholder="NNNNNNNNNN" value="<c:out value="${phonenumber}"/>" size="10" maxlength="10">
		</div>      		
    </div>
  </div>
    
  <div class="form-group">
  	<label for="filename" class="col-sm-2 control-label">Supporting Documents</label>
    <div class="col-sm-8">
    	<input type="file" class="form-control" id="filename"  name="filename" placeholder="choose file" maxlength="254" title="Select a File">
    </div>
  </div>

<!-- 
  <div class="form-group">
  <label for="country" class="col-sm-2 control-label">Purpose</label>
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox" id="todonate" name="todonate"> To Donate
        </label>
        <br>
        <label>
          <input type="checkbox" id="applyforhelp" name="applyforhelp"> To apply for help
        </label>
        <br>
        <label>
          <input type="checkbox" id="tovolunteer" name="tovolunteer"> To Volunteer
        </label>
      </div>
    </div>
  </div>	
  -->
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <input type="submit" value="Sign-Up" id="signup" name="signup" class="btn btn-default">
      <input type="reset"  value="Clear" id="Clear" name="Clear" class="btn btn-default">
    </div>
  </div>
  <input type="hidden" class="form-control" id="action" name="action" value="SIGNUP">
  <input type="hidden" class="form-control" id="type" name="type" value="EXTERNAL">
</form>
<myTags:Footer/>
</body>
</html>