<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1 id="_title">${titulo}</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="breadcrumb">
						<li><a href="/home">Home</a></li>
						<li class="active">${titulo}</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="list-group">
					<c:forEach var="topico" items="${topicos}">
						<li class="list-group-item">
							<a href="/topicos?id=${topico.id}">${topico.titulo}. por ${topico.login}</a>
						</li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/footer.jsp" %>

