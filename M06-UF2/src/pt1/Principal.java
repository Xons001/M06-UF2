package pt1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {

	static ConexionDB conex = new ConexionDB();

	public static void createNewTable() {
		// SQLite connection string
		String url = "jdbc:sqlite:C:/sqlite/db/for-honor.db";

		// SQL statement for creating a new table
		String sqlFaccion = "CREATE TABLE IF NOT EXISTS faccion (\n"
				+ "    faccion_id integer PRIMARY KEY,\n"
				+ "    nombre_faccion text NOT NULL,\n"
				+ "    lore text NOT NULL\n"
				+ ");";

		// SQL statement for creating a new table
		String sqlPersonaje = "CREATE TABLE IF NOT EXISTS personaje (\n"
				+ "    	personaje_id integer PRIMARY KEY,\n"
				+ "    	nombre_personaje text NOT NULL,\n"
				+ "    	ataque integer,\n"
				+ "    	defensa integer,\n"
				+ "		faccion_id	integer,\n"
				+ "		FOREIGN KEY (faccion_id) REFERENCES	faccion(faccion_id)\n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sqlFaccion);
			stmt.execute(sqlPersonaje);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertarDatos() {
		String url = "jdbc:sqlite:C:/sqlite/db/for-honor.db";

		/* Datos que vamos a insertar en Faccion*/
		String nombreFaccion[]={"Caballeros","Vikingos","Samurais"};
		String lore[]={"Los caballeros tienen un pasado muy violento","Los vikingos les gusta el valhalla","Los samurais viven en las sombras"};

		/* Datos que vamos a insertar en Personaje*/
		String nombrePersonaje[] = {"Caballero1", "Caballero2", "Caballero3", "Vikingo1", "Vikingo2", "Samurai1", "Samurai2"};
		int ataque[]= {20, 25, 15, 50, 45, 24, 32};
		int defensa[]= {40, 30, 39, 76, 54, 25, 15};
		int faccion_id[]= {1, 1, 1, 2, 2, 3, 3};


		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement()) {
			for (int i = 0; i < nombreFaccion.length; i++) {
				stmt.executeUpdate("INSERT INTO faccion (nombre_faccion, lore) VALUES('"+nombreFaccion[i]+"','"+lore[i]+"')");
			}
			for (int j = 0; j < nombrePersonaje.length; j++) {
				stmt.executeUpdate("INSERT INTO personaje (nombre_personaje, ataque, defensa, faccion_id) VALUES('"+nombrePersonaje[j]+"','"+ataque[j]+"','"+defensa[j]+"','"+faccion_id[j]+"')");
			}

			//Consulta personaje
			ResultSet rs = stmt.executeQuery("SELECT * FROM personaje");


			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void consultaPersonaje() {
		String url = "jdbc:sqlite:C:/sqlite/db/for-honor.db";

		try (Connection conn = DriverManager.getConnection(url); 
				Statement stmt = conn.createStatement()) {

			//Consulta personaje
			ResultSet consultaPersonje = stmt.executeQuery("SELECT * FROM personaje");
			
			while (consultaPersonje.next())
			   {
			      System.out.println("nombre="+consultaPersonje.getObject("nombre_personaje")+
			         ", ataque="+consultaPersonje.getObject("ataque")+
			         ", defensa="+consultaPersonje.getObject("defensa")+
			         ", faccion_id="+consultaPersonje.getObject("faccion_id"));
			   }
			consultaPersonje.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void consultaCaballeros() {
		String url = "jdbc:sqlite:C:/sqlite/db/for-honor.db";

		try (Connection conn = DriverManager.getConnection(url); 
				Statement stmt = conn.createStatement()) {

			//Consulta personaje
			ResultSet consultaPersonje = stmt.executeQuery("SELECT * FROM personaje where faccion_id=1");
			
			while (consultaPersonje.next())
			   {
			      System.out.println("nombre="+consultaPersonje.getObject("nombre_personaje")+
			         ", ataque="+consultaPersonje.getObject("ataque")+
			         ", defensa="+consultaPersonje.getObject("defensa")+
			         ", faccion_id="+consultaPersonje.getObject("faccion_id"));
			   }
			consultaPersonje.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void consultaMasAtaqueSamurai() {
		String url = "jdbc:sqlite:C:/sqlite/db/for-honor.db";

		try (Connection conn = DriverManager.getConnection(url); 
				Statement stmt = conn.createStatement()) {

			//Consulta personaje
			ResultSet consultaPersonje = stmt.executeQuery("SELECT nombre_personaje, ataque, defensa, faccion_id FROM personaje where faccion_id=3 AND ataque=(select MAX(ataque) from personaje where faccion_id=3)");
			
			while (consultaPersonje.next())
			   {
			      System.out.println("nombre="+consultaPersonje.getObject("nombre_personaje")+
			         ", ataque="+consultaPersonje.getObject("ataque")+
			         ", defensa="+consultaPersonje.getObject("defensa")+
			         ", faccion_id="+consultaPersonje.getObject("faccion_id"));
			   }
			consultaPersonje.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	public static void main(String[] args) {

		conex.Conexion("for-honor.db");
		//createNewTable();
		//insertarDatos();
		//consultaPersonaje();
		//consultaCaballeros();
		consultaMasAtaqueSamurai();
	}
}
