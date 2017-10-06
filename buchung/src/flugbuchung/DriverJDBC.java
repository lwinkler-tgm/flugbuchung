package flugbuchung;

//import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import org.postgresql.ds.PGSimpleDataSource;
//import oracle.jdbc.pool.OracleDataSource;

public class DriverJDBC {
	
	//Globale Variablen, um Daten auszulesen und zu bekommen durch get % set
	private static Connection con = null;
	private String host;
	private int port;
	private String user;
	private String passwort;
	private String database;
	private String vorname;
	private String nachname;
	
	private static String[]arraylist = null;	
	
	//Baut eine Verbindung mit der mysql datenbank auf
	public void conDBmySQL() throws SQLException{
		
		
		try{
			   System.out.println("Verbindung mit Datenbank wird aufgebaut"); // Info das die Methode aktiviert wurde.
			   Class.forName("org.gjt.mm.mysql.Driver").newInstance(); //Treiber wird geladen bzw. aktiviert.
			   con = DriverManager.getConnection( "jdbc:mysql://"+getHost()+":"+getPort()+"/"+getDatabase(), getUser(), getPasswort() );	//Daten werden von den Textfeldern geholt und in einem String gespeichert.
			} catch (ClassNotFoundException cnfe) {
			   System.out.println("Klasse für die Datenbank nicht gefunden\n" + cnfe.getMessage());
			   System.out.println("Cause:" + cnfe.getLocalizedMessage());
			} catch (SQLException sqlverbindung) {	//Wenn die DB nicht verbinden kann gibt es eine Fehlermeldung auf die Konsole
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
			   System.out.println("Verbindung mit Datenbank wird aufgebaut");  // Info das die Methode aktiviert wurde.
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
			   System.out.println("Verbindung mit Datenbank wird aufgebaut");  // Info das die Methode aktiviert wurde.
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
			   System.out.println("Verbindung mit Datenbank wird aufgebaut");  // Info das die Methode aktiviert wurde.
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
	
	//Befehl, um Datenbank zu durchsuchen zb. alle Daten einer Tabelle anzeigen.
	//diese werden bei der Konsole ausgegeben.
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
		//Alle aufgerufenen mysql befehle werden wieder beendet, damit sie nicht im hintergrund weiterlaufen.
		rs.close();
		stmt.close();
		con.close();
	}
	//Nimmt Flughafen, Staat, Kürzel und gibt diese an die ComboBoxen weiter
	public static void getDataAirports(){
	
		try {
			//Holt sich die Daten aus airport table ordnet sie.
			String query = "SELECT countries.name, airports.name, airportcode FROM airports, countries WHERE airports.country = countries.code ORDER BY 1;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//ArrayList speichert die Daten in eine Arraylist
			
			ArrayList<String> airports = new ArrayList<String>();
			
			
			//Die Tabellen werden durchlaufen und nach format s.u. in das array gespeichert.
			//Holt sich nur country und airports und fügt sie in das erstellte arraylist.
			while(rs.next()) {
				String country = rs.getString("countries.name");
				String airport = rs.getString("airports.name");
				//String airportcode = rs.getString("airportcode");
				airports.add(airport+","+country);
			}
			
			//ArrayList wird in ein Array umgewandelt, damit es in die combo box geaddet werden kann.
			
			String[]array = airports.toArray(new String[airports.size()]);
			
			
			//ComboBoxen bekommen die Daten des Arrays übergeben.
			for(String index:array){
				Mainwindow.vonflughafen.add(index);
				Mainwindow.nachflughafen.add(index);
			}
			
			
			//Alle aufgerufenen mysql befehle werden wieder beendet, damit sie nicht im hintergrund weiterlaufen.
		rs.close();
		stmt.close();
		
		//Exception falls etwas schiefläuft
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	
	//Methode vergleicht die ausgewählten Flughäfen miteinander, ob ein Flug zwischen diesen möglich ist.
	public static void compareSelections(){
		
		//Holt sich den Index des Arrays von den ausgewählten Comboboxen
		int vonFlughafenString = Mainwindow.vonflughafen.getSelectionIndex();
		int nachFlughafenString = Mainwindow.nachflughafen.getSelectionIndex();
		
		//Die Arrays werden nocheinmal aufgerufen und holt sich genau den ausgwählten wert durch index.
		String vF = Mainwindow.vonflughafen.getItem(vonFlughafenString);
		String nF = Mainwindow.nachflughafen.getItem(nachFlughafenString);
		
		//Die arrays werden getrennt, damit die daten verarbeitet werden können
		String[] splitStringvonF = vF.split(",");
		String[] splitStringnachF = nF.split(",");
		
		try {
			
			//Query, um die Daten der Airports zu bekommen
			String query = 	("SELECT * FROM flights, "
							+"(SELECT airportcode as 'vonFlughafen' FROM airports "
							+"WHERE name='"+splitStringvonF[0]+"')vonFlughafenString, "
							+"(SELECT airportcode as 'nachFlughafen' FROM airports "
							+"WHERE name='"+splitStringnachF[0]+"')nachFlughafenString "
							+"WHERE vonFlughafen = departure_airport AND nachFlughafen = destination_airport;");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//Arraylist, um die Daten zu speichern
			ArrayList<String> flightConnection = new ArrayList<String>();
			
			while(rs.next()) {
				
				//Holt sich departure und destination airport und fügt sie in die arraylist ein.
				flightConnection.add(rs.getString("departure_airport")+rs.getString("destination_airport"));
			}
			
			
			//Arraylist wird in ein Array umgewandelt.
			String[] ArrtoString = flightConnection.toArray(new String[flightConnection.size()]);
			
			//Wenn die länge des Arrays 0 ist ist kein flug zwischen den ausgewählten flughäfen vorhanden.
			if(ArrtoString.length==0){
				System.out.println("Keine Flug zwischen "+splitStringvonF[0]+" und "+splitStringnachF[0]+" möglich!");
			}
			//Ansonsten ist der flugvorhanden.
			else{
				System.out.println("Flug vorhanden!");
			}
			
			//Alle aufgerufenen mysql befehle werden wieder beendet, damit sie nicht im hintergrund weiterlaufen.
			rs.close();
			stmt.close();
			
			}
			
			catch(SQLException alert){
				alert.printStackTrace();
			}
			
	}
	
	//Methode sorgt dafür, dass die Daten in die DB gespeichert werden.
	public static void insertPassenger(){
		
		//Holt sich die indexwerte der ausgewählten flughäfen.
		int vonFlughafenString = Mainwindow.vonflughafen.getSelectionIndex();
		int nachFlughafenString = Mainwindow.nachflughafen.getSelectionIndex();
		
		//holt sich das passenden wert durch int
		String vF = Mainwindow.vonflughafen.getItem(vonFlughafenString);
		String nF = Mainwindow.nachflughafen.getItem(nachFlughafenString);
		
		//Der String wird aufgeteilt für die verarbeitung.
		String[] splitStringvonF = vF.split(",");
		String[] splitStringnachF = nF.split(",");
		
		//Da in den String Arrays nur einmal ein Wert vorhanden ist wird bei index 0 angegeben.
		String vonFlug = splitStringvonF[0];
		String nachFlug = splitStringnachF[0];
		
		//holt sich die eingegebenen werte aus den textfeldern.
		String getvorname = Mainwindow.txtvorname.getText();
		String getnachname = Mainwindow.txtnachname.getText();
		String flightnr = Mainwindow.txtfnr.getText();
		String airline = Mainwindow.txtairline.getText();
		String row = Mainwindow.txtrow.getText();
		String seat = Mainwindow.txtseat.getText();
		
		try{
			//Fügt die Daten in die DB ein
			
			//Es wird ein prepareStatement verwendet, da ansonsten die Daten nicht gespeichert werden können.
			String query = "INSERT INTO passengers VALUES(NULL,'"+getvorname+"','"+getnachname+"','"+airline+"','"+Integer.parseInt(flightnr)+"','"+row+"','"+seat+"');";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
			
			
			//Alle aufgerufenen mysql befehle werden wieder beendet, damit sie nicht im hintergrund weiterlaufen.
			stmt.close();
			
		}catch(SQLException alert){
			System.out.println("Passagier konnte nicht hinzugefügt werden!");
			alert.printStackTrace();
		}
		
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