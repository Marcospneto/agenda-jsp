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

@WebServlet(urlPatterns = { "/AgendaController", "/main", "/salvar", "/select", "/update", "/delete" })
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
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			excluirContato(request, response);
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

	// Editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato que será editado
		String idconStr = request.getParameter("idcon");
		Long idcon = null;
		try {
			idcon = Long.parseLong(idconStr);
		} catch (Exception e) {
			System.out.println(e);
		}
		/*
		 * Teste para ter certeza que o servlet está recebendo o id do contato que será
		 * editado System.out.println(idcon);
		 */

		// Setar a variável Agenda
		contato.setId(idcon);

		// executar o metodo selecionarContato (DAO)
		agendaDao.selecionarContato(contato);
		/*
		 * //Teste de recebimento System.out.println(contato.getId());
		 * System.out.println(contato.getNome()); System.out.println(contato.getFone());
		 * System.out.println(contato.getEmail());
		 */

		// Setar os atributos do formulário com o conteúdo da Agenda
		request.setAttribute("idcon", contato.getId());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());

		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * //Teste de recebimento System.out.println(request.getParameter("idcon"));
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("fone"));
		 * System.out.println(request.getParameter("email"));
		 */

		// Recebimento do id do contato como string
		String idconStr = request.getParameter("idcon");
		// Converter a String em Long
		Long idcon = null;
		try {
			idcon = Long.parseLong(idconStr);
		} catch (Exception e) {
			System.out.println(e);
		}

		// Setar as variáveis Agenda
		contato.setId(idcon);
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// Executar o metodo alterar contato
		agendaDao.alterarContato(contato);
		// Redirecionar para o documento agenda.jsp (Atualizando as alterações)
		response.sendRedirect("main");

	}

	protected void excluirContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato a ser excluido (validador.js)
		String idconStr = request.getParameter("id");
		Long idcon = null;
		try {
			idcon = Long.parseLong(idconStr);
		} catch (Exception e) {
			System.out.println(e);
		}

		// setar a variável id Agenda
		contato.setId(idcon);

		// executar o método deletarContato (DAO) passando o objeto contato
		agendaDao.excluirContato(contato);

		// Redirecionar para o documento agenda.jsp (Atualizando as alterações)
		response.sendRedirect("main");

	}

}
