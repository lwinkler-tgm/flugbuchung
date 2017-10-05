package flugbuchung;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

public class Mainwindow {

	protected static Shell shlDbConnection;
	private Text user;
	private Text passwort;
	private Text mysqlhost;
	private Text mysqlport;
	private Text mysqldatabase;
	private Text postgrehost;
	private Text postgreport;
	private Text postgredatabase;
	private Text txtServername;
	private Text oracleport;
	private Text txtSid;
	private Text derbydatabase;
	private Group mySQLGroup;
	private Group postgreGroup;
	private Group oracleGroup;
	private Group derbyGroup;
	private Group submitbutton;
	static Combo vonflughafen;
	static Combo nachflughafen;
	static Text txtvorname;
	static Text txtnachname;
	static Text txtfnr;
	static Text txtrow;
	static Text txtseat;
	static Text txtairline;
	

	/**
	 * Fenster wird erstellt.
	 */
	public static void main(String[] args) {
		try {
			Mainwindow window = new Mainwindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 *Fenster wird geöffnet..
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlDbConnection.open();
		shlDbConnection.layout();
		while (!shlDbConnection.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Daten im Fenster werden erstellt.
	 */
	protected void createContents() {
		shlDbConnection = new Shell();
		shlDbConnection.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		shlDbConnection.setSize(614, 519);
		shlDbConnection.setText("DB Connection");
		
		submitbutton = new Group(shlDbConnection, SWT.NONE);
		submitbutton.setLocation(21, 10);
		submitbutton.setSize(556, 398);
		
		vonflughafen = new Combo(submitbutton, SWT.NONE);
		vonflughafen.setBounds(94, 20, 272, 28);
		
		nachflughafen = new Combo(submitbutton, SWT.NONE);
		nachflughafen.setBounds(94, 60, 272, 28);
		
		Label lblNach = new Label(submitbutton, SWT.NONE);
		lblNach.setBounds(3, 59, 46, 26);
		lblNach.setText("Nach:");
		
		Label lblVon = new Label(submitbutton, SWT.NONE);
		lblVon.setBounds(3, 20, 46, 26);
		lblVon.setText("Von:");
		submitbutton.setVisible(false);
		
		Button btnBesttigen = new Button(submitbutton, SWT.NONE);
		btnBesttigen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DriverJDBC.compareSelections();
			}
		});
		btnBesttigen.setText("Best\u00E4tigen");
		btnBesttigen.setBounds(387, 41, 90, 30);
		
		Label lblVorname = new Label(submitbutton, SWT.NONE);
		lblVorname.setText("Vorname:");
		lblVorname.setBounds(3, 109, 81, 26);
		
		Label lblNachname = new Label(submitbutton, SWT.NONE);
		lblNachname.setText("Nachname:");
		lblNachname.setBounds(3, 148, 81, 26);
		
		txtvorname = new Text(submitbutton, SWT.BORDER);
		txtvorname.setText("Leo");
		txtvorname.setBounds(94, 106, 272, 26);
		
		txtnachname = new Text(submitbutton, SWT.BORDER);
		txtnachname.setText("Winkler");
		txtnachname.setBounds(94, 148, 272, 26);
		
		txtfnr = new Text(submitbutton, SWT.BORDER);
		txtfnr.setText("872");
		txtfnr.setBounds(198, 239, 81, 26);
		
		Label lblFlugnr = new Label(submitbutton, SWT.NONE);
		lblFlugnr.setText("Flugnr:");
		lblFlugnr.setBounds(3, 239, 81, 26);
		
		txtrow = new Text(submitbutton, SWT.BORDER);
		txtrow.setText("21");
		txtrow.setBounds(199, 279, 80, 26);
		
		txtseat = new Text(submitbutton, SWT.BORDER);
		txtseat.setText("F");
		txtseat.setBounds(198, 315, 81, 26);
		
		Label lblReihe = new Label(submitbutton, SWT.NONE);
		lblReihe.setText("Reihe:");
		lblReihe.setBounds(3, 279, 81, 26);
		
		Label lblSitzplatz = new Label(submitbutton, SWT.NONE);
		lblSitzplatz.setText("Sitzplatz:");
		lblSitzplatz.setBounds(3, 315, 81, 26);
		
		Label lblAirline = new Label(submitbutton, SWT.NONE);
		lblAirline.setText("Airline:");
		lblAirline.setBounds(3, 200, 81, 26);
		
		txtairline = new Text(submitbutton, SWT.BORDER);
		txtairline.setText("UP");
		txtairline.setBounds(198, 200, 81, 26);
		
		Button btnSave = new Button(submitbutton, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DriverJDBC.insertPassenger();
			}
		});
		btnSave.setText("Save");
		btnSave.setBounds(303, 260, 90, 30);
		
		Group GroupAll = new Group(shlDbConnection, SWT.NONE);
		GroupAll.setBounds(10, 10, 579, 451);
		
		Label txtDBAuswahl = new Label(GroupAll, SWT.NONE);
		txtDBAuswahl.setBounds(138, 61, 215, 26);
		txtDBAuswahl.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		txtDBAuswahl.setText("Bitte DB Sprache ausw\u00E4hlen!");
		
		Label txtVerbindung = new Label(GroupAll, SWT.NONE);
		txtVerbindung.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		txtVerbindung.setText("Verbindung nicht m\u00F6glich!");
		txtVerbindung.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		txtVerbindung.setBounds(138, 61, 215, 26);
		txtVerbindung.setVisible(false);
		
		Label txtwarning = new Label(GroupAll, SWT.NONE);
		txtwarning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		txtwarning.setText("Bitte Daten richtig eingeben!");
		txtwarning.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		txtwarning.setBounds(138, 61, 215, 26);
		txtwarning.setVisible(false);
		
		derbyGroup = new Group(GroupAll, SWT.NONE);
		derbyGroup.setLocation(138, 61);
		derbyGroup.setSize(438, 52);
		derbyGroup.setVisible(false);
		
		Label jdbcderby = new Label(derbyGroup, SWT.NONE);
		jdbcderby.setText("jdbc:derby:");
		jdbcderby.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		jdbcderby.setBounds(129, 19, 91, 26);
		
		derbydatabase = new Text(derbyGroup, SWT.BORDER);
		derbydatabase.setText("database");
		derbydatabase.setBounds(224, 16, 75, 26);
		
		postgreGroup = new Group(GroupAll, SWT.NONE);
		postgreGroup.setBounds(96, 61, 480, 52);
		postgreGroup.setVisible(false);
		
		Label jdbypostgre = new Label(postgreGroup, SWT.NONE);
		jdbypostgre.setText("jdbc:postgres//");
		jdbypostgre.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		jdbypostgre.setBounds(10, 16, 114, 26);
		
		
		postgrehost = new Text(postgreGroup, SWT.BORDER);
		postgrehost.setText("host");
		postgrehost.setBounds(130, 16, 124, 26);
		
		Label postgredot = new Label(postgreGroup, SWT.NONE);
		postgredot.setText(":");
		postgredot.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		postgredot.setBounds(260, 19, 9, 26);
		
		postgreport = new Text(postgreGroup, SWT.BORDER);
		postgreport.setText("port");
		postgreport.setBounds(272, 16, 52, 26);
		
		Label postgredot2 = new Label(postgreGroup, SWT.NONE);
		postgredot2.setText("/");
		postgredot2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		postgredot2.setBounds(330, 19, 9, 26);
		
		postgredatabase = new Text(postgreGroup, SWT.BORDER);
		postgredatabase.setText("database");
		postgredatabase.setBounds(342, 16, 138, 26);
		
		user = new Text(GroupAll, SWT.BORDER);
		user.setBounds(138, 119, 438, 26);
		user.setText("user");
		
		passwort = new Text(GroupAll, SWT.BORDER | SWT.PASSWORD);
		passwort.setBounds(138, 173, 438, 26);
		passwort.setText("passwort");
		
		Button btnConnect = new Button(GroupAll, SWT.NONE);
		btnConnect.setBounds(263, 262, 90, 30);
		btnConnect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					
					InsertData();
					GroupAll.setVisible(false);
					submitbutton.setVisible(true);
					System.out.println("Daten wurden übermittelt");
	
				} catch (NumberFormatException message) {
					txtwarning.setVisible(true);
					txtDBAuswahl.setVisible(false);
					mySQLGroup.setVisible(false);
					postgreGroup.setVisible(false);
					oracleGroup.setVisible(false);
					derbyGroup.setVisible(false);
					System.out.println("Test***Felder sind nicht ausgefüllt");
				}
			}
		});
		btnConnect.setText("Connect");
		
		Label textURL = new Label(GroupAll, SWT.NONE);
		textURL.setBounds(13, 61, 77, 26);
		textURL.setText("URL:");
		
		Label userName = new Label(GroupAll, SWT.NONE);
		userName.setBounds(13, 119, 77, 26);
		userName.setText("User name:");
		
		Label textPassword = new Label(GroupAll, SWT.NONE);
		textPassword.setBounds(13, 173, 77, 26);
		textPassword.setText("Password");
		
		
		Button btnPostgreSQL = new Button(GroupAll, SWT.NONE);
		btnPostgreSQL.setBounds(251, 20, 90, 30);
		btnPostgreSQL.setText("PostgreSQL");
		
		Button btnOracle = new Button(GroupAll, SWT.NONE);
		btnOracle.setBounds(370, 20, 90, 30);
		
			btnOracle.setText("Oracle");
			
			
			mySQLGroup = new Group(GroupAll, SWT.NONE);
			mySQLGroup.setBounds(138, 56, 438, 52);
			mySQLGroup.setVisible(false);
			
			Label jdbcmysql = new Label(mySQLGroup, SWT.NONE);
			jdbcmysql.setBounds(3, 23, 91, 26);
			jdbcmysql.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
			jdbcmysql.setText("jdbc:mysql//");
			
			mysqlhost = new Text(mySQLGroup, SWT.BORDER);
			mysqlhost.setBounds(98, 20, 124, 26);
			mysqlhost.setText("host");
			
			Label mysqgldot = new Label(mySQLGroup, SWT.NONE);
			mysqgldot.setBounds(228, 23, 9, 26);
			mysqgldot.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
			mysqgldot.setText(":");
			
			mysqlport = new Text(mySQLGroup, SWT.BORDER);
			mysqlport.setBounds(240, 20, 52, 26);
			mysqlport.setText("port");
			
			Label mysqldot2 = new Label(mySQLGroup, SWT.NONE);
			mysqldot2.setBounds(298, 23, 9, 26);
			mysqldot2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
			mysqldot2.setText("/");
			
			mysqldatabase = new Text(mySQLGroup, SWT.BORDER);
			mysqldatabase.setBounds(310, 20, 128, 26);
			mysqldatabase.setText("database");
			
			Button btnMysql = new Button(GroupAll, SWT.NONE);
			btnMysql.setBounds(138, 20, 90, 30);
			
				btnMysql.setText("MySQL");
				
				oracleGroup = new Group(GroupAll, SWT.NONE);
				oracleGroup.setBounds(148, 56, 408, 52);
				oracleGroup.setVisible(false);
				
				Label jdbcoracle = new Label(oracleGroup, SWT.NONE);
				jdbcoracle.setText("jdbc:oracle//thin:@");
				jdbcoracle.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
				jdbcoracle.setBounds(10, 16, 138, 26);
				
				txtServername = new Text(oracleGroup, SWT.BORDER);
				txtServername.setBounds(152, 16, 90, 26);
				txtServername.setText("servername");
				
				Label oracledot2 = new Label(oracleGroup, SWT.NONE);
				oracledot2.setBounds(248, 16, 9, 26);
				oracledot2.setText(":");
				oracledot2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
				
				oracleport = new Text(oracleGroup, SWT.BORDER);
				oracleport.setBounds(259, 16, 52, 26);
				oracleport.setText("port");
				
				Label oracledot3 = new Label(oracleGroup, SWT.NONE);
				oracledot3.setBounds(317, 16, 9, 26);
				oracledot3.setText(":");
				oracledot3.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
				
				txtSid = new Text(oracleGroup, SWT.BORDER);
				txtSid.setBounds(335, 16, 52, 26);
				txtSid.setText("SID");
				
				
				Button btnApacheDerby = new Button(GroupAll, SWT.NONE);
				btnApacheDerby.setBounds(486, 20, 90, 30);
				btnApacheDerby.setText("Derby");
				
				//Textfeld der jeweiligen für die jeweilige Sprache wird angezeigt, nachdem der Button gedrückt wurde.
				btnApacheDerby.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						txtwarning.setVisible(false);
						derbyGroup.setVisible(true);
						mySQLGroup.setVisible(false);
						postgreGroup.setVisible(false);
						oracleGroup.setVisible(false);
						txtDBAuswahl.setVisible(false);
					}
				});
				
				
				btnMysql.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						txtwarning.setVisible(false);
						mySQLGroup.setVisible(true);
						derbyGroup.setVisible(false);
						postgreGroup.setVisible(false);
						oracleGroup.setVisible(false);
						txtDBAuswahl.setVisible(false);
					}
				});
			
			
			btnOracle.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					txtwarning.setVisible(false);
					oracleGroup.setVisible(true);
					derbyGroup.setVisible(false);
					mySQLGroup.setVisible(false);
					postgreGroup.setVisible(false);
					txtDBAuswahl.setVisible(false);
	
				}
			});
		
		btnPostgreSQL.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtwarning.setVisible(false);
				postgreGroup.setVisible(true);
				derbyGroup.setVisible(false);
				mySQLGroup.setVisible(false);
				oracleGroup.setVisible(false);
				txtDBAuswahl.setVisible(false);
			}
		});
		

	}
	//Fehlermeldung als Text wenn etwas nicht richtig eingegeben wurde
	public static void alertMessage(String message){
		
		if(message == "NumberFormatException"){
		Label alertText = new Label(shlDbConnection, SWT.NONE);
		alertText.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		alertText.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		alertText.setBounds(69, 10, 234, 20);
		alertText.setText("Bitte Daten richtig eingeben!");
		}
		
		if(message == "no_connection"){
			Label alertText = new Label(shlDbConnection, SWT.NONE);
			alertText.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			alertText.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
			alertText.setBounds(69, 10, 234, 20);
			alertText.setVisible(true);
			alertText.setText("Flug zwischen den Flughäfen ist nicht möglich!");
			}

		
	}
	//Daten werden in die Datenbank übertragen/Verbindung mit DB wird aufgebaut
	public void InsertData(){
		try{
		if(postgreGroup.getVisible()==true){
	
			 DriverJDBC postgresDB = new DriverJDBC();
			 postgresDB.conDBpostgre();
			 postgresDB.setHost(postgrehost.getText());
			 int castpostgreport = Integer.parseInt(postgreport.getText());
			 postgresDB.setPort(castpostgreport);
			 postgresDB.setDatabase(postgredatabase.getText());
			 postgresDB.setUser(user.getText());
			 postgresDB.setPasswort(passwort.getText());
			 
			 postgresDB.conDBpostgre();
			 postgresDB.getDataAirports();
			 
			}
		if(mySQLGroup.getVisible()==true){
		
			DriverJDBC mySQLDB = new DriverJDBC();
			mySQLDB.setHost(mysqlhost.getText());
			int castmysqlport = Integer.parseInt(mysqlport.getText());
			mySQLDB.setPort(castmysqlport);
			mySQLDB.setDatabase(mysqldatabase.getText());
			mySQLDB.setUser(user.getText());
			mySQLDB.setPasswort(passwort.getText());
			System.out.println("MySQL Daten werden übergeben");
			
			
				mySQLDB.conDBmySQL();
				mySQLDB.getDataAirports();
				
		
			
		}
		
		if(oracleGroup.getVisible()==true){
	
			DriverJDBC oracleDB = new DriverJDBC();
			oracleDB.setHost(txtServername.getText());
			int castoracleport = Integer.parseInt(oracleport.getText());
			oracleDB.setPort(castoracleport);
			oracleDB.setDatabase(txtSid.getText());
			oracleDB.setUser(user.getText());
			oracleDB.setPasswort(passwort.getText());
			
			oracleDB.conDBoracle();
			oracleDB.getDataAirports();
		}
		
		if(derbyGroup.getVisible()==true){
		
			DriverJDBC derbyDB = new DriverJDBC();
			derbyDB.setDatabase(derbydatabase.getText());
			derbyDB.setUser(user.getText());
			derbyDB.setPasswort(passwort.getText());
			
			derbyDB.conDBderby();
			derbyDB.getDataAirports();
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}

