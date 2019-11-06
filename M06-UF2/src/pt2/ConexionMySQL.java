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
		String url = "jdc:mysql://localhost:3306/bd_videoclub?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver cargado correctamente");
			Connection conn = DriverManager.getConnection(url, usuari, contr);
			System.out.println("Conexion creada correctamente");
			System.out.println("--------------------------------------------------");
			System.out.println("EJERCICIO 1");
			InsertarArrayListaSocio(conn, listaSocios);

			/*System.out.println("--------------------------------------------------");
			System.out.println("EJERCICIO 2");
			String name;
			System.out.println("Escribe el nombre que quieres buscar en la base de datos");
			name = leerString();
			cambiarDato(conn, name);

			System.out.println("--------------------------------------------------");
			System.out.println("EJERCICIO 7");
			int numId;
			System.out.println("Escribe el id que quieres eliminar de la base de datos");
			numId = leerInteger();
			eliminarPorID(conn, numId);*/
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("No ha cargado el driver");
		} catch (SQLException e2) {
			System.out.println("Excepcio del tipus SQL");
			e2.printStackTrace();
		}
	}
	
	//Ejercicio1
		public static void InsertarArrayListaSocio(Connection conn, ArrayList<Socio> array) {
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

					Socio socio1 = new Socio(id, nombre, apellidos, direccion, telefono, poblacion, cp, provincia, pais, edad, fechaalta, cuota);

					array.add(socio1);
				}

				System.out.println("--------------------------------------------------");
			} catch (SQLException e) {
				System.out.println("No encuentra las bases de datos");
				e.printStackTrace();
			}
		}
	
	
	
	
	/*private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String nameDB = "bd_videoclub";
	final private String host = "root";
	final private String user = "localhost";
	final private String passwd = "";

	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Setup the connection with the DB
		      connect = DriverManager
		          .getConnection("jdbc:mysql://" + host + "/"+ nameDB + "?"
		              + "user=" + user + "&password=" + passwd );
		      
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			
			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select * from socio");
			writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("insert into socio values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			// Parameters start with 1
			//Damos de alta al primer socio
			preparedStatement.setString(1, "Sean");
			preparedStatement.setString(2, "Saez Fuller");
			preparedStatement.setString(3, "AV. Constitucion");
			preparedStatement.setString(4, "622516430");
			preparedStatement.setString(5, "Castelldefels");
			preparedStatement.setString(6, "08860");
			preparedStatement.setString(7, "Barcelona");
			preparedStatement.setString(8, "ESPAÑA");
			preparedStatement.setString(9, "25");
			preparedStatement.setDate(10, new java.sql.Date(2015, 10, 5));
			preparedStatement.setInt(11, 8000);
			
			preparedStatement.executeUpdate();

			preparedStatement = connect
					.prepareStatement("SELECT NOMBRE, APELLIDOS, DIRECCION, TELEFONO, POBLACION, CP, PROVINCIA, PAIS, EDAD, FECHAALTA, CUOTA from ");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			// Remove again the insert comment
//			preparedStatement = connect
//					.prepareStatement("delete from feedback.comments where myuser= ? ; ");
//			preparedStatement.setString(1, "Test");
//			preparedStatement.executeUpdate();
//
//			resultSet = statement
//					.executeQuery("select * from feedback.comments");
//			writeMetaData(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		//  Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String nombre = resultSet.getString("NOMBRE");
			String apellidos = resultSet.getString("APELLIDOS");
			String direccion = resultSet.getString("DIRECCION");
			String telefono = resultSet.getString("TELEFONO");
			String poblacion = resultSet.getString("POBLACION");
			String cp = resultSet.getString("CP");
			String provincia = resultSet.getString("PROVINCIA");
			String pais = resultSet.getString("PAIS");
			String edad = resultSet.getString("EDAD");
			Date fechaalta = resultSet.getDate("FECHAALTA");
			int cuota = resultSet.getInt("CUOTA");
			
			System.out.println("Nombre: " + nombre);
			System.out.println("Apellidos: " + apellidos);
			System.out.println("Direccion: " + direccion);
			System.out.println("Telefono: " + telefono);
			System.out.println("Poblacion: " + poblacion);
			System.out.println("CP: " + cp);
			System.out.println("Provincia: " + provincia);
			System.out.println("Pais: " + pais);
			System.out.println("Edad: " + edad);
			System.out.println("Date: " + fechaalta);
			System.out.println("Comment: " + cuota);
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}*/
}


