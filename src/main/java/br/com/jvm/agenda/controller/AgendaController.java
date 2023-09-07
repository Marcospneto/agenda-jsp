package br.com.jvm.agenda.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jvm.agenda.model.dao.AgendaDao;
import br.com.jvm.agenda.model.entidades.Agenda;

@WebServlet(urlPatterns = { "/AgendaController", "/main", "/salvar", "/select" })
public class AgendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AgendaDao agendaDao = new AgendaDao();
	Agenda contato = new Agenda();

	public AgendaController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			// invocar o metodo contatos
			contatos(request, response);
		} else if (action.equals("/salvar")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados Agenda
		ArrayList<Agenda> lista = agendaDao.listarContatos();
		// Encaminhas a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

		/*
		 * //Teste de recebimento da lista for (int i = 0; i < lista.size(); i++) {
		 * System.out.println(lista.get(i).getId());
		 * System.out.println(lista.get(i).getNome());
		 * System.out.println(lista.get(i).getFone());
		 * System.out.println(lista.get(i).getEmail()); }
		 */

	}

	// Novo Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento dos dados do formulario
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));

		// Setar as variaveis Agenda
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Invocar o metodo salvarContato passando o objeto contato
		agendaDao.salvarContato(contato);

		// Redirecionar para o documento agenda.jsp
		response.sendRedirect("index.html");
	}
	
	//Editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Recebimento do id do contato que será editado
		String idconStr = request.getParameter("idcon");
		Long idcon = null;
				try {
					idcon = Long.parseLong(idconStr);
				} catch (Exception e) {
					System.out.println(e);
				}
		/* Teste para ter certeza que o servlet está recebendo o id do contato que será editado
		System.out.println(idcon); */
		
		//Setar a variável Agenda
		contato.setId(idcon);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
