package flugbuchung;

import java.net.ConnectException;
import java.sql.*;

public class DriverJDBC {

	private static Connection con = null;
	private String host;
	private String port;
	private String user;
	private String passwort;
	private String database;
	
		
	/*public DriverJDBC(String host,String port,String user,String passwort,String database){
		setHost(host);
		setPort(port);
		setUser(user);
		setPasswort(passwort);
		setDatabase(database);
	}*/
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
	
	public void testDBmySQL(String table) throws SQLException{
		String query = "SELECT * FROM " + table;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		int columns = rs.getMetaData().getColumnCount();
		System.out.println("Alle Einträge von "+table);
		System.out.println();
		for (int i = 1; i <= columns; i++)
            System.out.print(rs.getMetaData().getColumnLabel(i) + "\t\t");
        System.out.println();
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                System.out.print(rs.getString(i) + "\t\t");
            }
            System.out.println();
        }
        rs.close();
        stmt.close();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
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


 
