package es.mundo.modelo;


import java.util.ArrayList;

import es.curso.excepciones.PaisVacioException;
import es.mundo.modelo.Pais;
import es.mundo.integracion.PaisDAO;

public class Negocio {
    // lo creo como un atributo de la clase porque lo voy
	// a invocar desde muchos puntos del proyecto...
	private PaisDAO paisdao = new PaisDAO();
	
	public int darAlta(String nombre, int habitantes) 
	{
		//validar el país
		//mandar un correo a alguna institución
	  
	  //Pais pais=new Pais(nombre, habitantes); //me pide int,
		// 2 maneras de hacerlo (elegir una de ellas):
				// 1era:
				// int id=paisdao.darAlta(pais); // me da un error al ejecutar
		int id = 0;
		try {
			Pais pais=new Pais(habitantes, nombre, habitantes);
			
			id = paisdao.darAlta(pais);
			throw new PaisVacioException();
		} 
		catch (PaisVacioException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
		// 2nda:
		//return paisdao.darAlta(pais); // devolverá el id del País
	}

	public Pais consultarUno(int id) {
		// se podría validar si el que solicita la consulta tiene
		// autorización...
		Pais pais = paisdao.consultarUno(id);
		return pais; 
	}

	
	
	public ArrayList<Pais> consultarTodos() {
		// aquí las reglas si las hubiera...
		// llamar al DAO...
		ArrayList<Pais> paises=paisdao.consultarTodos();
		return paises;
		//
	
	}
	public ArrayList<Pais> consultarNombre(String nombre) 
	{
		// se podría validar si el que solicita la consulta tiene
		// autorización...
		ArrayList<Pais> paises = paisdao.consultarNombre(nombre);
		return paises; 
	}

	public String borrar(int id) {
		String msg;
		// reglas del Negocio... 
		// Verificar si el pais tiene deudas pendientes
		// mandar un correo al administrador
		// metodo que devuelve un numero entero...
		int paisesBorrados=paisdao.borrar(id);
		if(paisesBorrados>=1)
		{
			//creamos una variable tipo String para devolvérsela al Servlet
			msg="Se ha/han borrado " + paisesBorrados + " pais/paises";
			
		} else {msg="No se ha podido borrar. Quizá haya sido borrado por otro usuario.";}
		return msg;
	}

	public String actualizar(int id, String nombre, int habitantes) throws PaisVacioException {
		String msg;
		// reglas del Negocio... 
		// Verificar si el pais tiene deudas pendientes
		// mandar un correo al administrador
		// metodo que devuelve un numero entero...
		
		int filas=paisdao.actualizar(id, nombre, habitantes);
		if(filas>=1)
		{
			//creamos una variable tipo String para devolvérsela al Servlet
			msg="Se ha actualizado " + filas + " pais";
			
		} else {msg="No se ha podido actualizar";}
	
		return msg;
	}

}
