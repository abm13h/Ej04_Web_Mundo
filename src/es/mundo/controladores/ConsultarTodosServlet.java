package es.mundo.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.mundo.modelo.Negocio;
import es.mundo.modelo.Pais;

/**
 * Servlet implementation class consultarTodosServlet
 */
@WebServlet("/ConsultarTodos")
public class ConsultarTodosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarTodosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// invocar al Negocio
		Negocio negocio=new Negocio();
		// 1er escribo...
		// ArrayList<Pais> paises=negocio.consultarTodos();
		// me pide importar "Pais" de "es.mundo.modelo"
		ArrayList<Pais> paises=negocio.consultarTodos();
		
		// meter el arrayList en el request
		request.setAttribute("listado", paises);
		// redirigir al código jsp "mostrarTodos"
		RequestDispatcher rd;
		rd=request.getRequestDispatcher("mostrarTodos.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
