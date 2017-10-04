package flugbuchung;

//import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverJDBC {

	private static Connection con = null;
	private String host;
	private int port;
	private String user;
	private String passwort;
	private String database;
	
		
	/*public DriverJDBC(String host,String port,String user,String passwort,String database){
		setHost(host);
		setPort(port);
		setUser(user);
		setPasswort(passwort);
		setDatabase(database);
		
		//Standardport von MySQL 3306
	}*/
	
	public static void main(String[] args) throws SQLException {
		
	DriverJDBC test = new DriverJDBC();
	test.setHost("localhost");
	test.setPort(3306);
	test.setUser("root");
	test.setPasswort("Mittwoch");
	test.setDatabase("flightdata");
	test.conDBmySQL();
	
	
		
	}
	
	public void conDBmySQL() throws SQLException{
		
		try{
			   System.out.println("Verbindung mit Datenbank wird aufgebaut"); // damit du weißt, dass die Methode angesprungen wird
			   Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			   con = DriverManager.getConnection( "jdbc:mysql://"+getHost()+":"+getPort()+"/"+getDatabase(), getUser(), getPasswort() );
			} catch (ClassNotFoundException cnfe) {
			   System.out.println("Klasse für die Datenbank nicht gefunden\n" + cnfe.getMessage());
			   System.out.println("Cause:" + cnfe.getLocalizedMessage());
			} catch (SQLException sqlverbindung) {
			   System.out.println("Verbindung fehlgeschlagen:");
			    System.out.println(sqlverbindung.getMessage());
			    } catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		
	}
	
	
	

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
	
	
}