package es.mundo.modelo;


import java.util.ArrayList;

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
		Pais pais=new Pais(habitantes, nombre, habitantes);
		
		// 2 maneras de hacerlo (elegir una de ellas):
		// 1era:
		// int id=paisdao.darAlta(pais); // me da un error al ejecutar
		int id=paisdao.darAlta(pais);
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

}
