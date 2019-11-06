package pt2;

import java.util.Date;

public class Socio {

	private int id;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String poblacion;
	private String cp;
	private String provincia;
	private String pais;
	private String edad;
	private Date fechaalta;
	private int cuota;
	
	public Socio(int id, String nombre, String apellidos, String direccion, String telefono, String poblacion,
			String cp, String provincia, String pais, String edad, Date fechaalta, int cuota) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.poblacion = poblacion;
		this.cp = cp;
		this.provincia = provincia;
		this.pais = pais;
		this.edad = edad;
		this.fechaalta = fechaalta;
		this.cuota = cuota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public Date getFechaalta() {
		return fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public int getCuota() {
		return cuota;
	}

	public void setCuota(int cuota) {
		this.cuota = cuota;
	}

	@Override
	public String toString() {
		return "Socio [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", poblacion=" + poblacion + ", cp=" + cp + ", provincia=" + provincia
				+ ", pais=" + pais + ", edad=" + edad + ", fechaalta=" + fechaalta + ", cuota=" + cuota + "]";
	}
	
	
}
