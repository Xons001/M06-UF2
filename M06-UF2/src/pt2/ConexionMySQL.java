package pt2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ConexionMySQL {

	public static int leerInteger() {
		Scanner lector = new Scanner(System.in);
		int numero = lector.nextInt();
		return numero;
	}

	public static String leerString() {
		Scanner lector = new Scanner(System.in);
		String texto= lector.nextLine();
		return texto;
	}
	public static void main(String[] args) {
		conexionMySQL();
	}

	private static void conexionMySQL() {
		ArrayList<Socio> listaSocios = new ArrayList<Socio>();

		String usuari = "root";
		String contr = "";
		String url = "jdbc:mysql://localhost:3306/bd_videoclub?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver cargado correctamente");
			Connection conn = DriverManager.getConnection(url, usuari, contr);
			System.out.println("Conexion creada correctamente");
			System.out.println("--------------------------------------------------");
			System.out.println("EJERCICIO 1");
			MostrarArrayListaSocio(conn/*, listaSocios*/);
			insertSocio(conn);
			MostrarArrayListaSocio(conn/*, listaSocios*/);

			System.out.println("--------------------------------------------------");
			System.out.println("EJERCICIO 2");
			cambiarDato(conn);

		

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("No ha cargado el driver");
		} catch (SQLException e2) {
			System.out.println("Excepcio del tipus SQL");
			e2.printStackTrace();
		}
	}

	//Ejercicio1
	public static void MostrarArrayListaSocio(Connection conn/*, ArrayList<Socio> array*/) {
		Statement statement;
		ResultSet rs;
		String consulta;
		try {
			statement = conn.createStatement();
			consulta = "select * from socio;";
			rs = statement.executeQuery(consulta);

			while (rs.next()) {
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellidos = rs.getString(3);
				String direccion = rs.getString(4);
				String telefono = rs.getString(5);
				String poblacion = rs.getString(6);
				String cp = rs.getString(7);
				String provincia = rs.getString(8);
				String pais = rs.getString(9);
				String edad = rs.getString(10);
				Date fechaalta = rs.getDate(11);
				int cuota = rs.getInt(12);

				System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellidos: " + apellidos + ", Direccion: " + direccion + ", Telefono: " + telefono + ", Poblacion: " + poblacion + ", Codigo Postal: " + cp + ", Provincia: " + provincia + ", Pais: " + pais + ", Edad: " + edad + ", Fecha de alta: " + fechaalta + ", Cuota: " + cuota);

				/*
				//todos los datos los estoy guardando en el objeto
				Socio socio = new Socio(id, nombre, apellidos, direccion, telefono, poblacion, cp, provincia, pais, edad, fechaalta, cuota);

				//lo printo
				System.out.println(socio.toString());

				//aqui los guardo en el arrayList
				array.add(socio);*/
			}


			System.out.println("--------------------------------------------------");
		} catch (SQLException e) {
			System.out.println("No encuentra las bases de datos");
			e.printStackTrace();
		}
	}

	//Aqui es donde hacemos el insert
	public static void insertSocio(Connection conn) {
		PreparedStatement preparedStatement;

		try {
			// PreparedStatements can use variables and are more efficient
			preparedStatement = conn
					.prepareStatement("insert into socio values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			//Damos de alta al primer socio
			preparedStatement.setInt(1, 10);
			preparedStatement.setString(2, "Sean");
			preparedStatement.setString(3, "Saez Fuller");
			preparedStatement.setString(4, "AV. Constitucion");
			preparedStatement.setString(5, "622516430");
			preparedStatement.setString(6, "Castelldefels");
			preparedStatement.setString(7, "08860");
			preparedStatement.setString(8, "Barcelona");
			preparedStatement.setString(9, "ESPAÑA");
			preparedStatement.setString(10, "25");
			preparedStatement.setDate(11, new java.sql.Date(2015, 10, 5));
			preparedStatement.setInt(12, 8000);

			preparedStatement.executeUpdate();

			System.out.println("--------------------------------------------------");
		} catch (SQLException e) {
			System.out.println("No encuentra las bases de datos");
			e.printStackTrace();
		}
	}

	//Ejercicio 2
	public static void cambiarDato(Connection conn) {

		Statement statement, statement2;
		ResultSet rs, rs2;
		String consulta, consulta2;

		PreparedStatement preparedStatement;

		try {
			statement = conn.createStatement();
			consulta = "select NOMBRE, APELLIDOS, CUOTA from socio where nombre='Sean';";
			rs = statement.executeQuery(consulta);

			while (rs.next()) {
				String nombre = rs.getString(1);
				String apellidos = rs.getString(2);
				int cuota = rs.getInt(3);

				System.out.println("Nombre: " + nombre + ", Apellidos: " + apellidos + ", Cuota: " + cuota);
			}

			String respuesta;
			System.out.println("Quieres modificar los datos?(Si/No)");

			boolean salir = false;
			while(salir == false) {
				respuesta = leerString();
				if(respuesta.equalsIgnoreCase("Si")) {
					//aqui empieza el update
					preparedStatement = conn.prepareStatement("update socio set cuota = ? where nombre = ?");
					preparedStatement.setInt(1, 8000);
					preparedStatement.setString(2, "Sean");
					preparedStatement.executeUpdate();
					salir = true;
				} else if (respuesta.equalsIgnoreCase("No")) {
					System.out.println("No lo modificamos");
					salir = true;
				} else {
					System.out.println("Lo escribiste incorrectamente, pruebalo de nuevo");
				}
			}

			System.out.println("Resultado despues de hacer o no el update");
			statement2 = conn.createStatement();
			consulta2 = "select NOMBRE, APELLIDOS, CUOTA from socio where nombre='Sean';";
			rs2 = statement2.executeQuery(consulta2);

			while (rs2.next()) {
				String nombre = rs2.getString(1);
				String apellidos = rs2.getString(2);
				int cuota = rs2.getInt(3);

				System.out.println("Nombre: " + nombre + ", Apellidos: " + apellidos + ", Cuota: " + cuota);
			}
			//MostrarArrayListaSocio(conn);
			System.out.println("--------------------------------------------------");
		} catch (SQLException e) {
			System.out.println("No encuentra las bases de datos");
			e.printStackTrace();
		}
	}
}


