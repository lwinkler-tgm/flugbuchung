package flugbuchung;

//import java.net.ConnectException;
import java.sql.*;

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
		
	/*DriverJDBC test = new DriverJDBC();
	test.setHost("localhost");
	test.setPort(3306);
	test.setUser("root");
	test.setPasswort("Mittwoch");
	test.setDatabase("flightdata");
	test.conDBmySQL();
	try {
		test.showAll("airports");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
		
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
			if(con.isClosed() == false){
				System.out.println("Verbindung aufgebaut");
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
	
	public String[] showFromAirport(String nameOfTable) throws SQLException {
        // Statement mit Benennung der Tablle
		String[]list = null;
        String query = "SELECT name,airportcode FROM " + nameOfTable;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int columns = rs.getMetaData().getColumnCount();
        System.out.println("Alle Einträge zu den Personen ");
        System.out.println();
        // Ich zeige die Tabellenspaltennamen an.
        for (int i = 1; i <= columns; i++)
            System.out.print(rs.getMetaData().getColumnLabel(i) + "\t\t");
        System.out.println();
        System.out.println();
        // Ich zeige den Inhalt der Tabelle an. Normaler Weise würde man die
        // Ergebnisse in eine Liste schreiben und diese zurück liefern.
        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                System.out.print(rs.getString(i) + "\t\t");
                list[i] = rs.getString(i);
            }
            System.out.println();
        }
        // Ich schließe die Streams wieder und gebe die Tabelle wieder frei.
        rs.close();
        stmt.close();
        return list;
    }
	
	  public void showToAirport(String nameOfTable) throws SQLException {
	        // Statement mit Benennung der Tablle
	        String query = "SELECT name,airportcode FROM " + nameOfTable;
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int columns = rs.getMetaData().getColumnCount();
	        System.out.println("Alle Einträge zu den Personen ");
	        System.out.println();
	        // Ich zeige die Tabellenspaltennamen an.
	        for (int i = 1; i <= columns; i++)
	            System.out.print(rs.getMetaData().getColumnLabel(i) + "\t\t");
	        System.out.println();
	        System.out.println();
	        // Ich zeige den Inhalt der Tabelle an. Normaler Weise würde man die
	        // Ergebnisse in eine Liste schreiben und diese zurück liefern.
	        while (rs.next()) {
	            for (int i = 1; i <= columns; i++) {
	                System.out.print(rs.getString(i) + "\t\t");
	            }
	            System.out.println();
	        }
	        // Ich schließe die Streams wieder und gebe die Tabelle wieder frei.
	        rs.close();
	        stmt.close();
	    }
}


 
