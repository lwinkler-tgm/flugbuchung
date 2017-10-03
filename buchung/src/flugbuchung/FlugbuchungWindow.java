package flugbuchung;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
public class FlugbuchungWindow {

	protected Shell shlFlugbuchung;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		try {
			FlugbuchungWindow window = new FlugbuchungWindow();
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
		
		Label label = new Label(shlFlugbuchung, SWT.NONE);
		label.setBounds(33, 44, 70, 20);

	}
}
