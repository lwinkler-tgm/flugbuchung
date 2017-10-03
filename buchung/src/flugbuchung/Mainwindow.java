package flugbuchung;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Mainwindow {

	protected Shell shell;
	private Text txtUser;
	private Text txtPasswort;
	private Text txtPasswortBesttigen;
	private Text txtHost;

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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(614, 443);
		shell.setText("SWT Application");
		
		txtUser = new Text(shell, SWT.BORDER);
		txtUser.setText("user");
		txtUser.setBounds(135, 123, 438, 26);
		
		txtPasswort = new Text(shell, SWT.BORDER);
		txtPasswort.setText("passwort");
		txtPasswort.setBounds(135, 177, 438, 26);
		
		Button btnLogin = new Button(shell, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnLogin.setBounds(248, 301, 90, 30);
		btnLogin.setText("Connect");
		
		txtPasswortBesttigen = new Text(shell, SWT.BORDER);
		txtPasswortBesttigen.setText("passwort best\u00E4tigen");
		txtPasswortBesttigen.setBounds(135, 229, 438, 26);
		
		txtHost = new Text(shell, SWT.BORDER);
	
		txtHost.setBounds(248, 65, 325, 26);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 65, 77, 26);
		lblNewLabel.setText("URL:");
		
		Label lblUserName = new Label(shell, SWT.NONE);
		lblUserName.setText("User name:");
		lblUserName.setBounds(10, 123, 77, 26);
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setText("Password");
		lblPassword.setBounds(10, 177, 77, 26);
		
		Label lblPassword_1 = new Label(shell, SWT.NONE);
		lblPassword_1.setText("Password");
		lblPassword_1.setBounds(10, 229, 77, 26);
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				e.toString();
			}
		});
		combo.setItems(new String[] {"MySQL", "PostgreSQL", "Oracle", "Apache Derby"});
		
		combo.setBounds(135, 65, 107, 28);
		combo.setText("MySQL");

	}
}
