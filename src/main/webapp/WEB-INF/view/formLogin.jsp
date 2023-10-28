<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<c:url value="/usuario" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="styles/reset.css">
	<link rel="stylesheet" href="styles/base.css">
	<link rel="stylesheet" href="styles/formLogin.css">
	<script src="resources/js/validaLogin.js"></script>
</head>
<body>

	<div class="titulo">
		<h2 class="titulo-cadastro">Fazer Login</h2>
	</div>

	<form class="formulario" id="formulario" action="${linkEntradaServlet }" method="post">
		<div class="form-group">
			<label class="form-label">Login </label>
			<input class="form-input" type="text" id="login" name="login" placeholder="insira seu login" />
			<span class="mensagem-erro" id="loginErro" style="display:none">Login precisa ter no m�nimo 3 caracteres</span>
		</div>
		
		<div class="form-group">
			<label class="form-label">Senha </label>
			<input class="form-input" type="password" id="senha" name="senha" placeholder="insira sua senha" />
			<span class="mensagem-erro" id="senhaErro" style="display:none">Senha precisa ter no m�nimo 8 caracteres</span>

			
		</div>
		
		<input type="hidden" name="acao" value="Login">
		<input class="form-submit" type="submit" value="Enviar" />
	</form>
	
	<a class="novo-usuario" href="usuario?acao=NovoUsuarioForm" >Criar conta</a>

</body>
</html>
