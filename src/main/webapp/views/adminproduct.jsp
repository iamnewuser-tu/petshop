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
<script type="text/javascript"
	src="<c:url value="/resources/js/products.js" />"></script>
<title><spring:message code="product.label" /></title>
</head>
<body>
	<form action="search" method="post">
		Search: <input type="text" id="txtSearch" name="txtSearch" placeholder="txtSearch" />
	</form>
	<div class="panel panel-default">
		<div id="abc" class="panel-heading h3 text-center">
			<spring:message code="product.header" />
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><spring:message code="product.table.id" /></th>
						<th><spring:message code="product.table.name" /></th>
						<th><spring:message code="product.table.price" /></th>
						<th><spring:message code="product.table.unitInStock" /></th>
						<th><spring:message code="product.table.description" /></th>
						<th><spring:message code="product.table.userId" /></th>
						<th>Images</th>
						<th><spring:message code="product.table.action" /></th>
					</tr>
				<thead>
					<c:choose>
						<c:when test="${!empty products}">
							<c:forEach items="${products}" var="product">
								<tr>
									<td>${product.id}</td>
									<td>${product.productName}</td>
									<td>${product.price}</td>
									<td>${product.unitInStock}</td>
									<td>${product.description}</td>
									<td id="id">${product.user.id }</td>
									<td><img
										src="data:image/jpeg;base64,${product.base64ImageFile}"
										alt="No image" width="70px" height="50px"></td>
									<td>
										<button class="btn btn-info"
											onclick="getProduct(${product.id}, 'VIEW');">
											<spring:message code="product.btn.view" />
										</button>
										<button class="btn btn-primary"
											onclick="getProduct(${product.id}, 'EDIT');">
											<spring:message code="product.btn.edit" />
										</button>
										<button class="btn btn-danger"
											onclick="deleteProduct(${product.id});">
											<spring:message code="product.btn.delete" />
										</button>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<th colspan="5" class="text-center"><spring:message
										code="product.table.empty" /></th>
							</tr>
						</c:otherwise>
					</c:choose>

					<tr>
						<th colspan="5">
							<button onclick="location.href='addProduct'"
								class="btn btn-primary">
								<spring:message code="product.btn.add" />
							</button>
						</th>
					</tr>
					<tr>
<%-- 						<td id="userId">${user.id }</td>	 --%>
					</tr>
			</table>
			<c:forEach varStatus="loop" begin="0" end="${pages}">
				<button type="button" class="btn btn-secondary">
					<a
						href="${pageContext.request.contextPath}/adminproduct/?page=${loop.count}"><c:out
							value="${loop.count}" /></a>
				</button>
			
			</c:forEach>
		</div>
	</div>
</body>
</html>