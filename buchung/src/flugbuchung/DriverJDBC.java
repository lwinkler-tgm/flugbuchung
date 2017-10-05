package flugbuchung;

//import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import org.postgresql.ds.PGSimpleDataSource;
//import oracle.jdbc.pool.OracleDataSource;

public class DriverJDBC {

	private static Connection con = null;
	private String host;
	private int port;
	private String user;
	private String passwort;
	private String database;
	private String vorname;
	private String nachname;
	
	private static String[]arraylist = null;	
	
	public void conDBmySQL() throws SQLException{
		
		
		try{
			   System.out.println("Verbindung mit Datenbank wird aufgebaut"); // Info das Methode funktioniert
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
	
	public void conDBpostgre(){
		
		try{
			   System.out.println("Verbindung mit Datenbank wird aufgebaut"); // Info das Methode funktioniert
			   Class.forName("org.postgresql.Driver");
			   con = DriverManager.getConnection( "jdbc:postgres://"+getHost()+":"+getPort()+"/"+getDatabase(), getUser(), getPasswort() );
			} catch (ClassNotFoundException cnfe) {
			   System.out.println("Klasse für die Datenbank nicht gefunden\n" + cnfe.getMessage());
			   System.out.println("Cause:" + cnfe.getLocalizedMessage());
			   System.exit(1);
			} catch (SQLException sqlverbindung) {
			   System.out.println("Verbindung fehlgeschlagen:");
			    System.out.println(sqlverbindung.getMessage());
			    System.exit(1);
			    } 
	}
	
	public void conDBoracle(){

		try{
			   System.out.println("Verbindung mit Datenbank wird aufgebaut"); // Info das Methode funktioniert
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			   con = DriverManager.getConnection( "jdbc:oracle:thin:@"+getHost()+":"+getPort()+":"+getDatabase(), getUser(), getPasswort() );
			} catch (ClassNotFoundException cnfe) {
			   System.out.println("Klasse für die Datenbank nicht gefunden\n" + cnfe.getMessage());
			   System.out.println("Cause:" + cnfe.getLocalizedMessage());
			
			} catch (SQLException sqlverbindung) {
			   System.out.println("Verbindung fehlgeschlagen:");
			    System.out.println(sqlverbindung.getMessage());
		
			    } 
	}
	
	public void conDBderby(){

		try{
			   System.out.println("Verbindung mit Datenbank wird aufgebaut"); // Info das Methode funktioniert
			   Class.forName("org.apache.derby.jdbc.EmbeddedDrive");
			   con = DriverManager.getConnection( "jdbc:derby:"+getDatabase(), getUser(), getPasswort() );
			} catch (ClassNotFoundException cnfe) {
			   System.out.println("Klasse für die Datenbank nicht gefunden\n" + cnfe.getMessage());
			   System.out.println("Cause:" + cnfe.getLocalizedMessage());
			
			} catch (SQLException sqlverbindung) {
			   System.out.println("Verbindung fehlgeschlagen:");
			    System.out.println(sqlverbindung.getMessage());
		
			    } 
	}
	
	//"Default" Befehl, um Datenbank zu durchsuchen
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
	//Nimmt Flughafen, Staat, Kürzel und gibt diese an die ComboBoxen weiter
	public static void getDataAirports(){
	
		try {
			String query = "SELECT countries.name, airports.name, airportcode FROM airports, countries WHERE airports.country = countries.code ORDER BY 1;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//ArrayList speichert die Daten
			
			ArrayList<String> airports = new ArrayList<String>();
			
			
			//Die Tabellen werden durchlaufen und nach format s.u. in das array gespeichert.
			while(rs.next()) {
				String country = rs.getString("countries.name");
				String airport = rs.getString("airports.name");
				String airportcode = rs.getString("airportcode");
				airports.add(airport+","+country);
			}
			
			//ArrayList wird in Array umgewandelt
			
			String[]array = airports.toArray(new String[airports.size()]);
			
			
			//ComboBoxen bekommen die Arrays
			for(String index:array){
				Mainwindow.vonflughafen.add(index);
				Mainwindow.nachflughafen.add(index);
			}
			
			
		//Port etc. wird wieder freigegeben
		rs.close();
		stmt.close();
		
		//Exception falls etwas schiefläuft
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	
	public static void compareSelections(){
		
		int vonFlughafenString = Mainwindow.vonflughafen.getSelectionIndex();
		int nachFlughafenString = Mainwindow.nachflughafen.getSelectionIndex();
		
		String vF = Mainwindow.vonflughafen.getItem(vonFlughafenString);
		String nF = Mainwindow.nachflughafen.getItem(nachFlughafenString);
		
		String[] splitStringvonF = vF.split(",");
		String[] splitStringnachF = nF.split(",");
		
		try {
			
			String query = 	("SELECT * FROM flights, "
							+"(SELECT airportcode as 'vonFlughafen' FROM airports "
							+"WHERE name='"+splitStringvonF[0]+"')vonFlughafenString, "
							+"(SELECT airportcode as 'nachFlughafen' FROM airports "
							+"WHERE name='"+splitStringnachF[0]+"')nachFlughafenString "
							+"WHERE vonFlughafen = departure_airport AND nachFlughafen = destination_airport;");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			ArrayList<String> flightConnection = new ArrayList<String>();
			
			while(rs.next()) {
				
				flightConnection.add(rs.getString("departure_airport")+rs.getString("destination_airport"));
			}
			
			String[] ArrtoString = flightConnection.toArray(new String[flightConnection.size()]);
			
			if(ArrtoString.length==0){
				System.out.println("Keine Flug zwischen "+splitStringvonF[0]+" und "+splitStringnachF[0]+" möglich!");
			}
			else{
				System.out.println("Flug vorhanden!");
			}
				
			rs.close();
			stmt.close();
			
			}
			
			catch(SQLException alert){
				alert.printStackTrace();
			}
			
	}
	
	public void insertPassenger(){
		
		
		int vonFlughafenString = Mainwindow.vonflughafen.getSelectionIndex();
		int nachFlughafenString = Mainwindow.nachflughafen.getSelectionIndex();
		
		String vF = Mainwindow.vonflughafen.getItem(vonFlughafenString);
		String nF = Mainwindow.nachflughafen.getItem(nachFlughafenString);
		
		String[] splitStringvonF = vF.split(",");
		String[] splitStringnachF = nF.split(",");
		
		String vonFlug = splitStringvonF[0];
		String nachFlug = splitStringnachF[0];
		
	}
	
	
	
	//Standard getter und setter methoden
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