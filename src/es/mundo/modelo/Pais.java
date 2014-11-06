package es.mundo.modelo;

public class Pais {
    private int id;
    private String nombre;
    private int habitantes;
    
    // Borramos/asteriscamos el método que se ha creado por defecto 
    // public Pais(String nombre, int habitantes){}
    
    // Para crear los métodos con Eclipse:
    // 1) constructor vacío
    // 2) constructor con todos los campos
    // 3) Métodos getter and setter de todos los campos...
    
	
    public Pais(int id, String nombre, int habitantes) 
       {super();
		this.id = id;
		this.nombre = nombre;
		this.habitantes = habitantes;}
    
	public Pais() {super();}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	public int getHabitantes() {return habitantes;}
	public void setHabitantes(int habitantes) {this.habitantes = habitantes;}
}
