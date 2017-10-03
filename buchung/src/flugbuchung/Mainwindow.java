package flugbuchung;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;

public class Mainwindow {

	protected Shell shlDbConnection;
	private Text txtUser;
	private Text txtPasswort;
	private Text txtHost;
	private Text txtPort;
	private Text txtDatabase;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text txtThin;
	private Text txtServername;
	private Text text_3;
	private Text txtSid;
	private Text text_6;

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
		
		DriverJDBC dbConnect = new DriverJDBC();
		
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
		shlDbConnection.setSize(614, 443);
		shlDbConnection.setText("DB Connection");
		
		Label txtDBAuswahl = new Label(shlDbConnection, SWT.NONE);
		txtDBAuswahl.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		txtDBAuswahl.setText("Bitte DB Sprache ausw\u00E4hlen!");
		txtDBAuswahl.setBounds(135, 95, 215, 26);
		
		Group postgreGroup = new Group(shlDbConnection, SWT.NONE);
		postgreGroup.setVisible(false);
		postgreGroup.setBounds(93, 95, 480, 52);
		
		Label jdbcPostgre = new Label(postgreGroup, SWT.NONE);
		jdbcPostgre.setText("jdbc:postgres//");
		jdbcPostgre.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		jdbcPostgre.setBounds(10, 16, 114, 26);
		
		
		text = new Text(postgreGroup, SWT.BORDER);
		text.setText("host");
		text.setBounds(130, 16, 124, 26);
		
		Label label_3 = new Label(postgreGroup, SWT.NONE);
		label_3.setText(":");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		label_3.setBounds(260, 19, 9, 26);
		
		text_1 = new Text(postgreGroup, SWT.BORDER);
		text_1.setText("port");
		text_1.setBounds(272, 16, 52, 26);
		
		Label label_4 = new Label(postgreGroup, SWT.NONE);
		label_4.setText("/");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		label_4.setBounds(330, 19, 9, 26);
		
		text_2 = new Text(postgreGroup, SWT.BORDER);
		text_2.setText("database");
		text_2.setBounds(342, 16, 138, 26);
		
		txtUser = new Text(shlDbConnection, SWT.BORDER);
		txtUser.setText("user");
		txtUser.setBounds(135, 153, 438, 26);
		
		txtPasswort = new Text(shlDbConnection, SWT.BORDER);
		txtPasswort.setText("passwort");
		txtPasswort.setBounds(135, 207, 438, 26);
		
		Button btnConnect = new Button(shlDbConnection, SWT.NONE);
		
		btnConnect.setBounds(496, 297, 90, 30);
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
		
		
		Group mySQLGroup = new Group(shlDbConnection, SWT.NONE);
		mySQLGroup.setBounds(135, 90, 438, 52);
		mySQLGroup.setVisible(false);
		
		Button btnMysql = new Button(shlDbConnection, SWT.NONE);
	
		btnMysql.setText("MySQL");
		btnMysql.setBounds(135, 54, 90, 30);
		
		Label lblJdbcmysql = new Label(mySQLGroup, SWT.NONE);
		lblJdbcmysql.setBounds(3, 23, 91, 26);
		lblJdbcmysql.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		lblJdbcmysql.setText("jdbc:mysql//");
		
		txtHost = new Text(mySQLGroup, SWT.BORDER);
		txtHost.setBounds(98, 20, 124, 26);
		txtHost.setText("host");
		
		Label label = new Label(mySQLGroup, SWT.NONE);
		label.setBounds(228, 23, 9, 26);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		label.setText(":");
		
		txtPort = new Text(mySQLGroup, SWT.BORDER);
		txtPort.setBounds(240, 20, 52, 26);
		txtPort.setText("port");
		
		Label label_1 = new Label(mySQLGroup, SWT.NONE);
		label_1.setBounds(298, 23, 9, 26);
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		label_1.setText("/");
		
		txtDatabase = new Text(mySQLGroup, SWT.BORDER);
		txtDatabase.setBounds(310, 20, 128, 26);
		txtDatabase.setText("database");
		
		Group oracleGroup = new Group(shlDbConnection, SWT.NONE);
		oracleGroup.setBounds(79, 90, 507, 52);
		oracleGroup.setVisible(false);
		
		Label lblJdbcoracle = new Label(oracleGroup, SWT.NONE);
		lblJdbcoracle.setText("jdbc:oracle//");
		lblJdbcoracle.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		lblJdbcoracle.setBounds(10, 16, 97, 26);
		
		txtThin = new Text(oracleGroup, SWT.BORDER);
		txtThin.setText("thin");
		txtThin.setBounds(104, 16, 124, 26);
		
		Label label_5 = new Label(oracleGroup, SWT.NONE);
		label_5.setText(":@");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		label_5.setBounds(234, 19, 25, 26);
		
		txtServername = new Text(oracleGroup, SWT.BORDER);
		txtServername.setBounds(258, 16, 90, 26);
		txtServername.setText("servername");
		
		Label label_6 = new Label(oracleGroup, SWT.NONE);
		label_6.setBounds(354, 16, 9, 26);
		label_6.setText(":");
		label_6.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		
		text_3 = new Text(oracleGroup, SWT.BORDER);
		text_3.setBounds(365, 16, 52, 26);
		text_3.setText("port");
		
		Label label_2 = new Label(oracleGroup, SWT.NONE);
		label_2.setBounds(423, 16, 9, 26);
		label_2.setText(":");
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		
		txtSid = new Text(oracleGroup, SWT.BORDER);
		txtSid.setBounds(441, 16, 52, 26);
		txtSid.setText("SID");
		
		Group derbyGroup = new Group(shlDbConnection, SWT.NONE);
		derbyGroup.setVisible(false);
		derbyGroup.setBounds(135, 90, 438, 52);
		
		Label lblJdbcderby = new Label(derbyGroup, SWT.NONE);
		lblJdbcderby.setText("jdbc:derby:");
		lblJdbcderby.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		lblJdbcderby.setBounds(129, 19, 91, 26);
		
		text_6 = new Text(derbyGroup, SWT.BORDER);
		text_6.setText("database");
		text_6.setBounds(224, 16, 75, 26);
		
		
		Button btnApacheDerby = new Button(shlDbConnection, SWT.NONE);
		btnApacheDerby.setText("Derby");
		btnApacheDerby.setBounds(483, 54, 90, 30);
		
		
		btnMysql.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				mySQLGroup.setVisible(true);
				derbyGroup.setVisible(false);
				postgreGroup.setVisible(false);
				oracleGroup.setVisible(false);
				txtDBAuswahl.setVisible(false);
			}
		});
		
		btnPostgreSQL.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				postgreGroup.setVisible(true);
				derbyGroup.setVisible(false);
				mySQLGroup.setVisible(false);
				oracleGroup.setVisible(false);
				txtDBAuswahl.setVisible(false);
			}
		});
		
		
		btnOracle.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				oracleGroup.setVisible(true);
				derbyGroup.setVisible(false);
				mySQLGroup.setVisible(false);
				postgreGroup.setVisible(false);
				txtDBAuswahl.setVisible(false);
	
			}
		});
		
		btnApacheDerby.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				derbyGroup.setVisible(true);
				mySQLGroup.setVisible(false);
				postgreGroup.setVisible(false);
				oracleGroup.setVisible(false);
				txtDBAuswahl.setVisible(false);
			}
		});

	}	
	
	
}
