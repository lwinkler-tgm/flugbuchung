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

	protected Shell shlDbConnection;
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
	static Combo vonflughafen;
	static Combo nachflughafen;

	/**
	 * Launch the application.
	 * @param args
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
	 * Open the window.
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
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDbConnection = new Shell();
		shlDbConnection.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		shlDbConnection.setSize(843, 662);
		shlDbConnection.setText("DB Connection");
		
		Group GroupAll = new Group(shlDbConnection, SWT.NONE);
		GroupAll.setBounds(13, 20, 579, 451);
		
		Label txtDBAuswahl = new Label(GroupAll, SWT.NONE);
		txtDBAuswahl.setBounds(138, 61, 215, 26);
		txtDBAuswahl.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		txtDBAuswahl.setText("Bitte DB Sprache ausw\u00E4hlen!");
		
		Group postgreGroup = new Group(GroupAll, SWT.NONE);
		postgreGroup.setBounds(13, 351, 480, 52);
		postgreGroup.setVisible(true);
		
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
				} catch (NumberFormatException message) {
					alertMessage("NumberFormatException");
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
			mySQLGroup.setBounds(13, 293, 438, 52);
			mySQLGroup.setVisible(true);
			
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
				
				Group oracleGroup = new Group(GroupAll, SWT.NONE);
				oracleGroup.setBounds(23, 396, 408, 52);
				oracleGroup.setVisible(true);
				
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
				
				Group derbyGroup = new Group(GroupAll, SWT.NONE);
				derbyGroup.setBounds(3, 205, 438, 52);
				derbyGroup.setVisible(true);
				
				Label jdbcderby = new Label(derbyGroup, SWT.NONE);
				jdbcderby.setText("jdbc:derby:");
				jdbcderby.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
				jdbcderby.setBounds(129, 19, 91, 26);
				
				derbydatabase = new Text(derbyGroup, SWT.BORDER);
				derbydatabase.setText("database");
				derbydatabase.setBounds(224, 16, 75, 26);
				
				
				Button btnApacheDerby = new Button(GroupAll, SWT.NONE);
				btnApacheDerby.setBounds(486, 20, 90, 30);
				btnApacheDerby.setText("Derby");
				
				Label lblVon = new Label(shlDbConnection, SWT.NONE);
				lblVon.setText("Von:");
				lblVon.setBounds(27, 495, 46, 26);
				
				Label lblNach = new Label(shlDbConnection, SWT.NONE);
				lblNach.setText("Nach:");
				lblNach.setBounds(27, 534, 46, 26);
				
				vonflughafen = new Combo(shlDbConnection, SWT.NONE);
				vonflughafen.setBounds(75, 495, 186, 28);
				
				nachflughafen = new Combo(shlDbConnection, SWT.NONE);
				nachflughafen.setBounds(75, 532, 186, 28);
				
				btnApacheDerby.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
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
				postgreGroup.setVisible(true);
				derbyGroup.setVisible(false);
				mySQLGroup.setVisible(false);
				oracleGroup.setVisible(false);
				txtDBAuswahl.setVisible(false);
			}
		});
		

	}
	
	public void alertMessage(String message){
		
		if(message == "NumberFormatException"){
		Label alertText = new Label(shlDbConnection, SWT.NONE);
		alertText.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		alertText.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		alertText.setBounds(69, 10, 234, 20);
		alertText.setText("Bitte Daten richtig eingeben!");
		}

		
	}
	
	public void InsertData(){
		
		if(mySQLGroup.getVisible()==true){
		
			DriverJDBC mySQLDB = new DriverJDBC();
			mySQLDB.setHost(mysqlhost.getText());
			int castmysqlport = Integer.parseInt(mysqlport.getText());
			mySQLDB.setPort(castmysqlport);
			mySQLDB.setDatabase(mysqldatabase.getText());
			mySQLDB.setUser(user.getText());
			mySQLDB.setPasswort(passwort.getText());
			
			try {
				mySQLDB.conDBmySQL();
				mySQLDB.getDataAirports();
				
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}
}
