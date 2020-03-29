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
<title><spring:message code="product.title" /></title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="product.jumbotron.h1" />
				</h1>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
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
					<strong><spring:message code="product.form.availInStock" /></strong>
					: ${product.unitsInStock}
				</p>
				<h4>${product.unitPrice}
					<spring:message code="product.form.currency" />
				</h4>
				<p>
					<a href="<spring:url value="/market/products" />"
						class="btn btn-default"> <span
						class="glyphicon-hand-left glyphicon"></span> <spring:message
							code="product.form.back.button" />
					</a> <a href="#" class="btn btn-warning btn-large"> <span
						class="glyphicon-shopping-cart glyphicon"> </span> <spring:message
							code="product.form.order.button" />
					</a>
				</p>
			</div>
		</div>
	</section>
</body>
</html>
