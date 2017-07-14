<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>

		<div class="section"><div class="container"><div class="row">
				<div class="col-md-12">
						<div class="page-header">
								<h1 id="_erro">${erro}</h1>
								<h1 id="_mainTitle">${topico.titulo}
										<small id="_topicOwner">por ${topico.login}</small>
								</h1>
						</div>
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
								<li class="active" id="_bcActive">${topico.titulo}</li>
						</ul>
				</div>
		</div></div></div>
		<div class="section"><div class="container"><div class="row"><div class="col-md-12"><hr></div></div></div></div>
		<div class="section"><div class="container"><div class="row">
				<div class="col-md-12">
						<div class="panel panel-primary">
								<div class="panel-heading">
										<h3 class="panel-title" id="_topico">${topico.titulo}</h3>
								</div>
								<div class="panel-body">
										<p>${topico.conteudo}</p>
								</div>
						</div>
				</div>
		</div></div></div>
		<div class="section"><div class="container"><div class="row"><div class="col-md-12"><hr></div></div></div></div>
		<div class="section"><div class="container">
				<c:forEach var="comentario" items="${topico.comentarios}">
				<div class="row">
						<div class="col-md-12">
								<div class="panel panel-primary">
										<div class="panel-body">
												<div class="col-md-12">
														<p>${comentario.comentario}</p>
												</div>
										</div>
										<div class="panel-heading">
												<h3 class="panel-title">${comentario.login}</h3>
										</div>
								</div>
						</div>
				</div>
				<div class="row"><div class="col-md-12"><hr></div></div>
				</c:forEach>
				<div class="row">
						<div class="col-md-12">
								<form class="form-horizontal" role="form" method="post" action="/comentario">
										<div class="form-group">
												<div class="col-sm-12">
														<textarea class="form-control" id="_comment" name="comentario" rows="5"
														placeholder="Comentário"></textarea>
														<input type="hidden" name="idTopico" value="${topico.id}">
												</div>
										</div>
										<div class="form-group">
												<div class="col-sm-12">
														<button type="submit" class="btn btn-default">Adicionar</button>
												</div>
										</div>
								</form>
						</div>
				</div>
				<div class="row">
						<div class="col-md-12">
								<form class="form-horizontal" role="form">
										<div class="form-group"></div>
								</form>
						</div>
				</div>
		</div></div>
    
<%@ include file="/footer.jsp" %>
