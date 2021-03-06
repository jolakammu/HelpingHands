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
	<h2 class="text-center">Supporting Documents</h2>
	
	<div class="col-md-4 col-md-offset-4">
		<form action="UploadServlet"  enctype="multipart/form-data" method="post" class="form-horizontal">
  			<c:if test="${not empty error}">
  				<div class="alert alert-danger" role="alert">${error}</div>
  			</c:if>	
  			<div class="form-group">
    			<label for="filename" class="col-sm-2 control-label">Upload<span style="color: red;">*</span></label>
    			<div class="col-sm-10">
      				<input type="file" class="form-control" id="filename"  name="filename" placeholder="choose file" maxlength="254" required="required" title="Select a File">
    			</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<input type="submit" value="Upload" id="Upload" name="Upload" class="btn btn-default">
      				<input type="reset"  value="Clear" id="Clear" name="Clear" class="btn btn-default">
    			</div>
  			</div>
		</form>
	</div>


		<div class="col-md-4 col-md-offset-4">
		<form name="form" action="" method="post" >
			<div class="col-xs-10">			
				<table class="table table-condensed table-striped">
				<tr class="active">
				<th class="col-sm-1" colspan="3">Upload Supporting Documents</th>
				</tr>
  					<tr class="active">
  						<th class="col-sm-1">Document Name</th>
  						<th class="col-sm-1">Delete</th>
  					</tr>
  					<c:forEach items="${documentArray}"  var="documentArray">
						<tr class="active">
							<td><a href="OpenDocumentServlet?docId=${documentArray.docId}">${documentArray.fileName}</a>  </td>
							<td><a href="DeleteDocumentServlet?docId=${documentArray.docId}"><img src="images/delete.jpg" border="0" height="20px" width="20px"></a></td>
						</tr>
					</c:forEach>		
					<c:if test="${empty documentArray}">
						<tr class="active">
							<td colspan="3">No supporting Documents available</td>
						</tr>	
					</c:if>										
				</table>
			</div>
		</div>
	</form>
	<span style="color: red;">*</span>
</body>
</html>