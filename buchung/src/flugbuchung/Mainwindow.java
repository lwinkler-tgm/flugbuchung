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
	private Text txtThin;
	private Text txtServername;
	private Text oracleport;
	private Text txtSid;
	private Text derbydatabase;
	private Group mySQLGroup;

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
		shlDbConnection.setSize(614, 552);
		shlDbConnection.setText("DB Connection");
		
		Label txtDBAuswahl = new Label(shlDbConnection, SWT.NONE);
		txtDBAuswahl.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		txtDBAuswahl.setText("Bitte DB Sprache ausw\u00E4hlen!");
		txtDBAuswahl.setBounds(135, 95, 215, 26);
		
		Group postgreGroup = new Group(shlDbConnection, SWT.NONE);
		postgreGroup.setVisible(true);
		postgreGroup.setBounds(10, 385, 480, 52);
		
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
		
		user = new Text(shlDbConnection, SWT.BORDER);
		user.setText("user");
		user.setBounds(135, 153, 438, 26);
		
		passwort = new Text(shlDbConnection, SWT.BORDER);
		passwort.setText("passwort");
		passwort.setBounds(135, 207, 438, 26);
		
		Button btnConnect = new Button(shlDbConnection, SWT.NONE);
		btnConnect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					InsertData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnConnect.setBounds(260, 296, 90, 30);
		btnConnect.setText("Connect");
		
		Label textURL = new Label(shlDbConnection, SWT.NONE);
		textURL.setBounds(10, 95, 77, 26);
		textURL.setText("URL:");
		
		Label userName = new Label(shlDbConnection, SWT.NONE);
		userName.setText("User name:");
		userName.setBounds(10, 153, 77, 26);
		
		Label textPassword = new Label(shlDbConnection, SWT.NONE);
		textPassword.setText("Password");
		textPassword.setBounds(10, 207, 77, 26);
		
		
		Button btnPostgreSQL = new Button(shlDbConnection, SWT.NONE);
	
		
		btnPostgreSQL.setBounds(248, 54, 90, 30);
		btnPostgreSQL.setText("PostgreSQL");
		
		Button btnOracle = new Button(shlDbConnection, SWT.NONE);
	
		btnOracle.setText("Oracle");
		btnOracle.setBounds(367, 54, 90, 30);
		
		
		mySQLGroup = new Group(shlDbConnection, SWT.NONE);
		mySQLGroup.setBounds(10, 327, 438, 52);
		mySQLGroup.setVisible(true);
		
		Button btnMysql = new Button(shlDbConnection, SWT.NONE);
	
		btnMysql.setText("MySQL");
		btnMysql.setBounds(135, 54, 90, 30);
		
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
		
		Group oracleGroup = new Group(shlDbConnection, SWT.NONE);
		oracleGroup.setBounds(10, 432, 507, 52);
		oracleGroup.setVisible(true);
		
		Label jdbcoracle = new Label(oracleGroup, SWT.NONE);
		jdbcoracle.setText("jdbc:oracle//");
		jdbcoracle.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		jdbcoracle.setBounds(10, 16, 97, 26);
		
		txtThin = new Text(oracleGroup, SWT.BORDER);
		txtThin.setText("thin");
		txtThin.setBounds(104, 16, 124, 26);
		
		Label oracledot = new Label(oracleGroup, SWT.NONE);
		oracledot.setText(":@");
		oracledot.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		oracledot.setBounds(234, 19, 25, 26);
		
		txtServername = new Text(oracleGroup, SWT.BORDER);
		txtServername.setBounds(258, 16, 90, 26);
		txtServername.setText("servername");
		
		Label oracledot2 = new Label(oracleGroup, SWT.NONE);
		oracledot2.setBounds(354, 16, 9, 26);
		oracledot2.setText(":");
		oracledot2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		
		oracleport = new Text(oracleGroup, SWT.BORDER);
		oracleport.setBounds(365, 16, 52, 26);
		oracleport.setText("port");
		
		Label oracledot3 = new Label(oracleGroup, SWT.NONE);
		oracledot3.setBounds(423, 16, 9, 26);
		oracledot3.setText(":");
		oracledot3.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		
		txtSid = new Text(oracleGroup, SWT.BORDER);
		txtSid.setBounds(441, 16, 52, 26);
		txtSid.setText("SID");
		
		Group derbyGroup = new Group(shlDbConnection, SWT.NONE);
		derbyGroup.setVisible(true);
		derbyGroup.setBounds(0, 239, 438, 52);
		
		Label jdbcderby = new Label(derbyGroup, SWT.NONE);
		jdbcderby.setText("jdbc:derby:");
		jdbcderby.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		jdbcderby.setBounds(129, 19, 91, 26);
		
		derbydatabase = new Text(derbyGroup, SWT.BORDER);
		derbydatabase.setText("database");
		derbydatabase.setBounds(224, 16, 75, 26);
		
		
		Button btnApacheDerby = new Button(shlDbConnection, SWT.NONE);
		btnApacheDerby.setText("Derby");
		btnApacheDerby.setBounds(483, 54, 90, 30);
		
		
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
		

	}
	
	public void InsertData() throws SQLException{
		
		if(mySQLGroup.getVisible()==true){
		
			DriverJDBC mySQLDB = new DriverJDBC();
			mySQLDB.setHost(mysqlhost.getText());
			int castmysqlport = Integer.parseInt(mysqlport.getText());
			mySQLDB.setPort(castmysqlport);
			mySQLDB.setDatabase(mysqldatabase.getText());
			mySQLDB.setUser(user.getText());
			mySQLDB.setPasswort(passwort.getText());
			
			mySQLDB.conDBmySQL();
			
			try {
				FlugbuchungWindow window = new FlugbuchungWindow();
				window.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
