package es.mundo.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.curso.excepciones.PaisVacioException;
import es.mundo.modelo.Negocio;

/**
 * Servlet implementation class ProcesarServlet
 */
@WebServlet("/Procesar")
public class ProcesarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recupero los datos de http://localhost:8090/Ej04_Web_Mundo/ConsultarUno?idPais=1&enviar=ENVIAR 
		   int id= Integer.parseInt(request.getParameter("id"));
		   String nombre=request.getParameter("nombre");
		   int habitantes=Integer.parseInt(request.getParameter("habitantes"));
		   String borrar=request.getParameter("borrar");
		   String actualizar=request.getParameter("actualizar");
		   
		   Negocio negocio = new Negocio();
		   String mensajeDoGet = "";
		   
		   // Proceso borrar. "mensaje" = filasBorradas
		   if(borrar!=null)
		   {
			mensajeDoGet = negocio.borrar(id);
		   }
		   if(actualizar!=null)
		   {
			try {
				mensajeDoGet = negocio.actualizar(id, nombre, habitantes);
			} catch (PaisVacioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   // llamamos al método "borrar" dentro de negocio.java
		   // negocio.borrar es un metodo String que recibe un "id" entero
		   // y devuelve un String "msg" que no tiene por qué 
		   // llamarse igual que nuestra variable mensajeDoGet
		   
		   
		   // meter el mensaje en el request
		   request.setAttribute("mensajeVistaMensajeJsp", mensajeDoGet);
		   
		   //redirigir a la vista el mensaje
		   RequestDispatcher rd=request.getRequestDispatcher("vistaMensaje.jsp");
		   rd.forward(request, response);
		   
		//
		//
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
