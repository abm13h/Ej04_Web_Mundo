package es.curso.excepciones;

public class PaisVacioException extends Exception 
{
	// implementamos el método constructor
	public PaisVacioException()
	{
        super ("Clase PaisVacioException dentro de es.curso.excepciones. Mensaje: No se admiten paises vacios");
    }
}
