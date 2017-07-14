<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
	<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="/static/lib/jquery/dist/jquery.min.js"></script>
		<script type="text/javascript" src="/static/lib/bootstrap/dist/js/bootstrap.min.js"></script>
		<link href="/static/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="/static/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  </head>
  <body>
		<div class="navbar navbar-default navbar-inverse navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand"><span>Forum</span><br></a>
				</div>
				<div class="collapse navbar-collapse" id="navbar-ex-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" id="_usuario" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false">${usuario}<i class="fa fa-caret-down"></i></a>
							<ul class="dropdown-menu" role="menu">
								<li>
									<a href="#" aria-expanded="false">Perfil <i class="fa fa-user"></i></a>
								</li>
								<li>
									<a href="/logout">Sair <i class="fa fa-sign-out"></i></a>
								</li>
							</ul>
						</li>
					</ul>
					<form class="navbar-form navbar-right" method="POST" action="/topicos">
						<button id="_novo" type="submit" name="cmd" value="criar" class="btn btn-success">Novo Tópico</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="/ranking">Ranking</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
