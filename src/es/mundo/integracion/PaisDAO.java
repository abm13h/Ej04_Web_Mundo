package es.mundo.integracion;

import java.sql.Connection;
//import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.mundo.modelo.Pais;

public class PaisDAO { // esta es la Clase que se va a comunicar con la BBDD
    
	private Connection cx;
	
	//creamos 2 métodos (1 conectar 2 desconectar)
	
	private void conectar()
	{
		try 
		{// 2. Obtener la conexión dándole las credenciales. 
         Class.forName("com.mysql.jdbc.Driver");
		 cx= DriverManager.getConnection("jdbc:mysql://localhost:3306/mundo","root","root");
         cx.setAutoCommit(false);
        } catch (SQLException e) {e.printStackTrace();} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void desconectar()
	{     
	try{cx.close();} 
	catch (SQLException e){e.printStackTrace();}
    }
	
	public int darAlta(Pais pais) 
	{
		int idRetornar = 0;
		try 
		{
			// 1. Conectar
			conectar();
			
			// 2. preparar la consulta sql
			// "PreparedStatement" es un objeto que permite desde Java
			//  construir instrucciones SQL
			PreparedStatement ps;
			ps = cx.prepareStatement("INSERT INTO pais VALUES(?, ?, ?)");
			
			// 2.1 setear los interrogantes
			ps.setInt(1, 0); // el "0" podría contener "99999"
			                 // porque lo calcula MySQL
			ps.setString(2, pais.getNombre());
			ps.setInt(3, pais.getHabitantes());
			
			// 3. ejecutar la consulta sql
			// ps.executeQuery ... para "Select" que no modifica la BBDD
			int filasAfectadas = ps.executeUpdate();
			// 4. hacer el commit
			cx.commit();
						
			// no sabemos si el insert irá ok...
			if(filasAfectadas>=1)
			{
				idRetornar=ultimoId();
			}
			
			
			
			// 5. cerrar la conexión
			desconectar();

		} catch (SQLException e){e.printStackTrace();}
				
		return idRetornar; // ojo!
	}
	public Pais consultarUno(int id) 
	{   Pais p=new Pais();
		try {
			// 1. Conectar
			conectar();
			// 2. preparar la consulta sql
			// "PreparedStatement" es un objeto que permite desde Java
			//  construir instrucciones SQL
			PreparedStatement ps;
			ps = cx.prepareStatement("SELECT * FROM pais WHERE ID=?");
			// 2.1 setear los interrogantes
			ps.setInt(1, id);
			// 3. ejecutar la consulta sql que devuelve 1 ResultSet
			ResultSet rs = ps.executeQuery();
			//int filasAfectadas = rs.executeQuery(); //) ... para "Select" que no modifica la BBDD
					
			// 4. llenar el objeto pais con los datos de
			// respuesta de la Base de Datos que viene en 
			// un objeto del tipo ResultSet "rs".
			// Comprobamos si la consulta devuelve al menos 1 elemento...
			// Como la consulta la hacemos con la clave principal
			// sabemos que devolverá 1 sola fila por eso nos sirve
			// el "if" si no deberíamos usar "while" 
			
			if(rs.next())
			{// viene al menos 1 fila (tiene un next)
			   p.setId(rs.getInt("id"));
			   p.setNombre(rs.getString("nombre"));
			   p.setHabitantes(rs.getInt("habitantes"));
			}  
			
			// 5. cerrar la conexión
			desconectar();
	} catch (SQLException e) {e.printStackTrace();}
	  return p;
  }
	
	public ArrayList<Pais> consultarTodos() {
		ArrayList<Pais> paises=new ArrayList<Pais>();
		try {
		// 1 conectar
		conectar();
		// 2 preparar la consulta
		PreparedStatement ps;
		
			ps = cx.prepareStatement("SELECT * FROM pais");
		
		// 2.1 setear los interrogantes
		// en este caso no tenemos interrogantes porque no usamos where...
		
		// 3 ejecutar la consulta
		ResultSet rs = ps.executeQuery();
		
		// 4 bajar el resultado de la consulta y ponerlo en el ArrayList
		while(rs.next()){
			// entre el objeto "rs" y el objeto "arrayList"
			// me creo un objeto intermedio tipo Pais
			Pais p = new Pais();
			// muevo cada elemento del "rs" al objeto intermedio tipo Pais
			p.setId(rs.getInt("id"));
			p.setNombre(rs.getString("nombre"));
			p.setHabitantes(rs.getInt("habitantes"));
			// muevo cada elemento del objeto intermedio tipo Pais
			// al objeto "arrayList"
			paises.add(p);
		}
		
		// 5 desconectar
		   desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    
		} return paises;
	}		
	public int ultimoId(){
		int idRecuperado=0;
		try {
			// 1 conectar
			conectar();
			// 2 preparar la consulta
			PreparedStatement ps;
			
			ps = cx.prepareStatement("SELECT MAX(id) as ultimoId FROM pais");
			
			// 2.1 setear los interrogantes
			// en este caso no tenemos interrogantes porque no usamos where...
			
			// 3 ejecutar la consulta
			ResultSet rs = ps.executeQuery();
			
			// 4. llenar el objeto pais con los datos de
			// respuesta de la Base de Datos que viene en 
			// un objeto del tipo ResultSet "rs".
			// Comprobamos si la consulta devuelve al menos 1 elemento...
			// Como la consulta la hacemos con max(id)
			// sabemos que devolverá 1 sola fila por eso nos sirve
						
		    if(rs.next())
			{// viene al menos 1 fila (tiene un next)
				idRecuperado=rs.getInt("ultimoId");
			}  
			
			// 5 desconectar
			   desconectar();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		return idRecuperado;
	}
	public ArrayList<Pais> consultarNombre(String nombre) {
		ArrayList<Pais> paises=new ArrayList<Pais>();
		try {
		// 1 conectar
		conectar();
		// 2 preparar la consulta
		PreparedStatement ps = cx.prepareStatement("SELECT * FROM pais WHERE nombre LIKE ?");
		// 2.1 setear los interrogantes
		// en este caso no tenemos interrogantes porque no usamos where...
		ps.setString(1, "%" + nombre + "%");
		// 3 ejecutar la consulta
		ResultSet rs = ps.executeQuery();
		
		// 4 bajar el resultado de la consulta y ponerlo en el ArrayList
		while(rs.next()){
			// entre el objeto "rs" y el objeto "arrayList"
			// me creo un objeto intermedio tipo Pais
			Pais p = new Pais();
			// muevo cada elemento del "rs" al objeto intermedio tipo Pais
			p.setId(rs.getInt("id"));
			p.setNombre(rs.getString("nombre"));
			p.setHabitantes(rs.getInt("habitantes"));
			// muevo cada elemento del objeto intermedio tipo Pais
			// al objeto "arrayList"
			paises.add(p);
		}
		
		// 5 desconectar
		   desconectar();
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return paises;
    }
	public int borrar(int id) {
		int filasAfectadas = 0;
		try 
		{   // 1. Conectar
			conectar();
			
			// 2. preparar la consulta sql
			// "PreparedStatement" es un objeto que permite desde Java
			//  construir instrucciones SQL
			PreparedStatement ps;
			ps = cx.prepareStatement("DELETE FROM pais WHERE id=?");
			
			// 2.1 setear los interrogantes
			ps.setInt(1, id); // el id que viene de fuera
			
			// 3. ejecutar la consulta sql
			filasAfectadas = ps.executeUpdate();
			
			// 4. hacer el commit
			cx.commit();
			
			// 5. cerrar la conexión
			desconectar();

		} catch (SQLException e){e.printStackTrace();}
				
		return filasAfectadas; //
	}
	public int actualizar(int id, String nombre, int habitantes) 
	{
		int idRetornar = 0;
		try 
		{
			// 1. Conectar
			conectar();
			
			// 2. preparar la consulta sql
			// "PreparedStatement" es un objeto que permite desde Java
			//  construir instrucciones SQL
			PreparedStatement ps;
			ps = cx.prepareStatement("UPDATE pais SET nombre=?, habitantes=? WHERE ID=?");
			
			// 2.1 setear los interrogantes
			
			ps.setString(1, nombre);
			ps.setInt(2, habitantes);
			ps.setInt(3, id); // el id que viene de fuera
			
			// 3. ejecutar la consulta sql
			// ps.executeQuery ... para "Select" que no modifica la BBDD
			int filasAfectadas = ps.executeUpdate();
			// 4. hacer el commit
			cx.commit();
						
			// no sabemos si el insert irá ok...
			if(filasAfectadas>=1)
			{idRetornar=filasAfectadas;}
			
			// 5. cerrar la conexión
			desconectar();

		} catch (SQLException e){e.printStackTrace();}
				
		return idRetornar; // 
	}
}