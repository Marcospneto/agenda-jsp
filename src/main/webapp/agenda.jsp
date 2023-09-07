<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="br.com.jvm.agenda.model.entidades.Agenda"%>
<%@ page import="java.util.ArrayList" %>



<%ArrayList<Agenda> lista = (ArrayList<Agenda>) request.getAttribute("contatos");%>
<% 
 /*	
	
	for (int i = 0; i < lista.size(); i++){
		out.println(lista.get(i).getId());
		out.println(lista.get(i).getNome());
		out.println(lista.get(i).getFone());
		out.println(lista.get(i).getEmail());
		
	} */
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/favicon.pgn">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1">Novo contato</a>
	
	<table id = "tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
				
				
			</tr>
		</thead>
		
		<tbody>
			<% for (int i = 0; i < lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getId() %></td>
					<td><%=lista.get(i).getNome() %></td>
					<td><%=lista.get(i).getFone() %></td>
					<td><%=lista.get(i).getEmail() %></td>
					<td><a href="select?idcon=<%=lista.get(i).getId() %>" class="Botao1"> Editar </a></td>
				</tr>
			
			
			<% } %>
		</tbody>
	
	
	
	</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>