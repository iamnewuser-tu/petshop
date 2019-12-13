<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<title><spring:message code="category.detail.label" /></title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading h3 text-center">
			<spring:message code="category.detail.label" />
		</div>
		<div class="panel-body">

			<spring:message code="category.detail.name.plhd" var="name_plhd" />
			

			<form:form class="form-horizontal" action="./save" method="post"
				modelAttribute="category" enctype="multipart/form-data">
				
<%-- 				<form:input path="user.id" type="text" value="${user.id}" /> --%>
				
				<div class="form-group">
					<label class="control-label col-sm-4" for="name"> <spring:message
							code="category.detail.name" />
					</label>
					<div class="col-sm-6">
						<form:input path="categoryName" type="text" class="form-control"
							id="categoryName" name="categoryName" placeholder="${name_plhd}"
							readonly="${mode == 'VIEW'}" />
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-6">
						<c:if test="${mode == 'VIEW'}">
							<button disabled="disabled" type="submit" class="btn btn-primary">
								<spring:message code="category.detail.btn.save" />
							</button>
						</c:if>
						<c:if test="${mode != 'VIEW'}">
							<button type="submit" class="btn btn-primary">
								<spring:message code="category.detail.btn.save" />
							</button>
						</c:if>
						<button type="button" onclick="location.href='./'"
							class="btn btn-default">
							<spring:message code="category.detail.btn.cancel" />
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>