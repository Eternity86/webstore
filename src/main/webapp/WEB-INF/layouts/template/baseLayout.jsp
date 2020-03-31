<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<%-- <script src="/webstore/js/angular-1.7.9/angular.js"></script>
<script src="/webstore/js/angular-1.7.9/angular-route.js"></script>
<script src="/webstore/js/angular-1.7.9/angular-resource.js"></script> --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-route.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-resource.min.js"></script>
<script src="/webstore/js/controllers.js"></script>
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<section class="container">
		<div class="pull-right" style="padding-right: 50px">
			<a href="?language=ru">Русский</a>|<a href="?language=en">English</a>|<a
				href="?language=nl">Nederlands</a> <a
				href="<c:url value="/logout" />">Logout</a>
		</div>
	</section>
	<div class="container">
		<div class="jumbotron">
			<div class="header">
				<ul class="nav nav-pills pull-right">
					<tiles:insertAttribute name="navigation" />
				</ul>
				<h3 class="text-muted">Web Store</h3>
			</div>
			<h1>
				<tiles:insertAttribute name="heading" />
			</h1>
			<p>
				<tiles:insertAttribute name="tagline" />
			</p>
		</div>
		<div class="row">
			<tiles:insertAttribute name="content" />
		</div>
		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>