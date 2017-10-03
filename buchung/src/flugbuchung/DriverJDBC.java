package flugbuchung;

import java.net.ConnectException;
import java.sql.*;

public class DriverJDBC {

	private static Connection con = null;
	private static String host = "localhost";
	private static String port = "3306";
	private static String user = "root";
	private static String passwort = "Mittwoch";
	private static String database = "flightdata";
	
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		
		DriverJDBC db = new DriverJDBC();
		
		db.conDB();
		System.out.println();
		db.testDB("airlines");
		
		
	}
	
	public static void conDB() throws SQLException{
		
		try{
			   System.out.println("Verbindung mit Datenbank wird aufgebaut"); // damit du weißt, dass die Methode angesprungen wird
			   Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			   con = DriverManager.getConnection( "jdbc:mysql://"+host+":"+port+"/"+database, user, passwort );
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
	
	public void testDB(String table) throws SQLException{
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
}


 
