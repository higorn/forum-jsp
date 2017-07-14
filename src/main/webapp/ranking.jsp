<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>

	<div class="section"><div class="container"><div class="row">
		<div class="col-md-12">
			<h1 id="_title">${titulo}</h1>
		</div>
	</div></div></div>
	<div class="section"><div class="container"><div class="row">
		<div class="col-md-12">
			<ul class="breadcrumb">
				<li>
					<a href="/">Home</a>
				</li>
				<li>
					<a href="/topicos">Tópicos</a>
				</li>
				<li class="active">${titulo}</li>
			</ul>
		</div>
	</div></div></div>
	<div class="section"><div class="container"><div class="row">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>Nome</th>
						<th>Login</th>
						<th>Pontos</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="usuario" items="${usuarios}" varStatus="loop">
					<tr>
						<td>${loop.index+1}</td>
						<td>${usuario.nome}</td>
						<td>${usuario.login}</td>
						<td>${usuario.pontos}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div></div></div>

<%@ include file="/footer.jsp" %>
