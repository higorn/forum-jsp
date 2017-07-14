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
						<li><a href="/topicos">Tópicos</a></li>
						<li class="active">${titulo}</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="section"><div class="container"><div class="row"><div class="col-md-12">
		<form class="form-horizontal" role="form" method="post" action="/topicos">
			<h3 id="_erro" style="color: red;">${erro}</h3>
			<div class="form-group">
				<div class="col-sm-2">
					<label for="_inputTitulo" class="control-label">Título</label>
				</div>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="_inputTitulo" name="titulo" placeholder="Título">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-2">
					<label for="_inputConteudo" class="control-label">Conteúdo</label>
				</div>
				<div class="col-sm-10">
					<textarea class="form-control" id="_inputConteudo" name="conteudo" placeholder="Conteúdo" rows="10"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="_submit" type="submit" name="cmd" value="salvar" class="btn btn-default">Salvar</button>
				</div>
			</div>
		</form>
	</div></div></div></div>

<%@ include file="/footer.jsp" %>
