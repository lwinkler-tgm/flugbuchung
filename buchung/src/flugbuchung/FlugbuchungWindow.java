package flugbuchung;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
public class FlugbuchungWindow {
	
	private static Combo VonFlughafen;
	private Combo NachFlughafen;

	protected Shell shlFlugbuchung;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		try {
			listeFlughafen();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			FlugbuchungWindow window = new FlugbuchungWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlFlugbuchung.open();
		shlFlugbuchung.layout();
		while (!shlFlugbuchung.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlFlugbuchung = new Shell();
		shlFlugbuchung.setSize(642, 463);
		shlFlugbuchung.setText("Flugbuchung");
		
		Label lblVon = new Label(shlFlugbuchung, SWT.NONE);
		lblVon.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		lblVon.setBounds(33, 41, 70, 20);
		lblVon.setText("Von");
		
		Label lblNach = new Label(shlFlugbuchung, SWT.NONE);
		lblNach.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		lblNach.setText("Nach");
		lblNach.setBounds(33, 94, 70, 20);
		
		Label lblHinflug = new Label(shlFlugbuchung, SWT.NONE);
		lblHinflug.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		lblHinflug.setText("Hinflug");
		lblHinflug.setBounds(33, 152, 70, 20);
		
		Label lblRckflug = new Label(shlFlugbuchung, SWT.NONE);
		lblRckflug.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		lblRckflug.setText("R\u00FCckflug");
		lblRckflug.setBounds(33, 198, 70, 20);
		
		VonFlughafen = new Combo(shlFlugbuchung, SWT.NONE);
		VonFlughafen.setBounds(175, 38, 186, 28);
		
		NachFlughafen = new Combo(shlFlugbuchung, SWT.NONE);
		NachFlughafen.setBounds(175, 91, 186, 28);
		
		Combo VonLand = new Combo(shlFlugbuchung, SWT.NONE);
		VonLand.setBounds(381, 38, 186, 28);
		
		Combo NachLand = new Combo(shlFlugbuchung, SWT.NONE);
		NachLand.setBounds(381, 91, 186, 28);

	}
	
	/*public static void listeFlughafen() throws SQLException{
		DriverJDBC dBData = new DriverJDBC();
		
		String[] fillList = dBData.showFromAirport("airport");
		VonFlughafen.setItems(fillList);
		
		
		//miep.showToAirport("airport");
	}*/
}
