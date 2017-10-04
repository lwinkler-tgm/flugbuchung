package flugbuchung;

//import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import org.postgresql.ds.PGSimpleDataSource;
//import oracle.jdbc.pool.OracleDataSource;

public class DriverJDBC {

	private static Connection con = null;
	private String host;
	private int port;
	private String user;
	private String passwort;
	private String database;
	private static String[]arraylist = null;
	
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
	
	
	public void getDataTable(String tablename) throws SQLException{
		
		String query = "SELECT * FROM "+ tablename;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		int columns = rs.getMetaData().getColumnCount();

		while(rs.next()){
			for (int i = 1; i <= columns; i++) {
			System.out.println(rs.getString(i));
			}
		}
		rs.close();
		stmt.close();
		con.close();
	}
	
	public static void getDataAirports(){
	
		try {
			String query = "select countries.name,airports.name from airports,countries WHERE airports.country = countries.code ORDER BY 1;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//ArrayList für alle Elemente
			
			ArrayList<String> airports = new ArrayList<String>();
			while(rs.next()) {
				String country = rs.getString("countries.name");
				String airport = rs.getString("airports.name");
				airports.add(airport+"|"+country);
			}
			
			//Umwandlung in Array anschließend Zuweisung für die jeweiligen Dropdowns
			
			String[]array = airports.toArray(new String[airports.size()]);
			
			for(String index:array){
				Mainwindow.vonflughafen.add(index);
				Mainwindow.nachflughafen.add(index);
			}
			
		
		rs.close();
		stmt.close();
		con.close();
		
		}catch(SQLException e){
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

	public String[] getArray() {
		return arraylist;
	}

	public void setArray(String[] array) {
		this.arraylist = array;
	}
	
	
}