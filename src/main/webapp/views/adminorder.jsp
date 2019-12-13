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
	src="<c:url value="/resources/js/categories.js" />"></script>
<title><spring:message code="order.label" /></title>
</head>
<body>
	<form action="search" method="post">
		Search: <input type="text" id="txtSearch" name="txtSearch" placeholder="txtSearch" />
	</form>
	<div class="panel panel-default">
		<div id="abc" class="panel-heading h3 text-center">
			<spring:message code="order.header" />
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><spring:message code="order.table.id" /></th>
						<th><spring:message code="order.table.createDate" /></th>
						<th><spring:message code="order.table.totalPrice" /></th>
					</tr>
				<thead>
					<c:choose>
						<c:when test="${!empty orders}">
							<c:forEach items="${orders}" var="order">
								<tr>
									<td>${order.id}</td>
									<td>${order.createDate}</td>
									<td>${order.totalPrice}</td>
									
									
									<td>
										<button class="btn btn-info"
											onclick="getOrder(${order.id}, 'VIEW');">
											<spring:message code="order.btn.view" />
										</button>
										<button class="btn btn-primary"
											onclick="getOrder(${order.id}, 'EDIT');">
											<spring:message code="order.btn.edit" />
										</button>
										<button class="btn btn-danger"
											onclick="deleteOrder(${order.id});">
											<spring:message code="order.btn.delete" />
										</button>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<th colspan="5" class="text-center"><spring:message
										code="order.table.empty" /></th>
							</tr>
						</c:otherwise>
					</c:choose>

					<tr>
						<th colspan="5">
							<button onclick="location.href='addOrder'"
								class="btn btn-primary">
								<spring:message code="order.btn.add" />
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
						href="${pageContext.request.contextPath}/adminorder/?page=${loop.count}"><c:out
							value="${loop.count}" /></a>
				</button>
			
			</c:forEach>
		</div>
	</div>
</body>
</html>