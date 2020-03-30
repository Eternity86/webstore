<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<%-- <script src="/webstore/js/angular-1.7.9/angular.js"></script>
<script src="/webstore/js/angular-1.7.9/angular-route.js"></script>
<script src="/webstore/js/angular-1.7.9/angular-resource.js"></script> --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-route.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-resource.js"></script>
<script src="/webstore/js/controllers.js"></script>
<title><spring:message code="product.title" /></title>
</head>
<body>
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="?language=ru">Русский</a>|<a href="?language=en">English</a>|<a
				href="?language=nl">Nederlands</a> <a
				href="<c:url value="/logout" />">Logout</a>
		</div>
	</section>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="product.jumbotron.h1" />
				</h1>
			</div>
		</div>
	</section>
	<section class="container" data-ng-app="cartApp">
		<div class="row">
			<div class="col-md-5">
				<img src="<c:url value="/img/${product.productId}.png"></c:url>"
					alt="image" style="width: 100%" />
			</div>
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong><spring:message code="product.form.itemCode" /></strong> :
					<span class="label label-warning">${product.productId}</span>
				</p>
				<p>
					<strong><spring:message code="product.form.manufacturer" /></strong>
					: ${product.manufacturer}
				</p>
				<p>
					<strong><spring:message code="product.form.category" /></strong> :
					${product.category}
				</p>
				<p>
					<a href="<c:url value="/pdf/${product.productId}.pdf"></c:url>"><strong><spring:message
								code="product.form.manual" /></strong></a>
				</p>
				<p>
					<strong><spring:message code="product.form.availInStock" /></strong>
					: ${product.unitsInStock}
				</p>
				<h4>${product.unitPrice}
					<spring:message code="product.form.currency" />
				</h4>
				<p data-ng-controller="cartCtrl">
					<a href="<spring:url value="/market/products" />"
						class="btn btn-default"> <span
						class="glyphicon-hand-left glyphicon"></span> <spring:message
							code="product.form.back.button" />
					</a> <a href="#" class="btn btn-warning btn-large"
						data-ng-click="addToCart('${product.productId}')"> <span
						class="glyphicon-shopping-cart glyphicon"> </span> <spring:message
							code="product.form.order.button" />
					</a> <a href="<spring:url value="/cart" />" class="btn btn-default">
						<span class="glyphicon-hand-right glyphicon"></span> View Cart
					</a>
				</p>
			</div>
		</div>
	</section>
</body>
</html>
