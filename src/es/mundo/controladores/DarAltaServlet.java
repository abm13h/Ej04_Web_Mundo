package es.mundo.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.mundo.modelo.Negocio;

/**
 * Servlet implementation class DarAltaServlet
 */
@WebServlet("/DarAlta")
public class DarAltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	/* Constructor por defecto */
    public DarAltaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /* Método doGet */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    /* 1 Recuperar los datos de la URL */
   	/* 2 Adaptarlos si es necesario al tipo de datos del modelo*/
    
    // recibe un String, devuelve un String
    String nombre = request.getParameter("nombre"); 
    
    // recibe un String, le damos la vuelta para convertirlo en un entero con "Integer.parseInt("	
    int habitantes = Integer.parseInt(request.getParameter("habitantes")); 
 		
    /* 3 Pasarle los datos recuperados a la capa Negocio */
    Negocio negocio = new Negocio();
    int id=negocio.darAlta(nombre, habitantes);
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}