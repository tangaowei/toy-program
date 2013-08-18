import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class STimer {
	public static void main(String[] args) {
		new Thread() {
			int interval = 1000 * 60 * 60; // 1 hour
			public void run() {
				Timer t = new Timer(interval, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Take a rest!");
					}
				});
				t.setRepeats(true);
				t.start();
			}
		}.start();
		
		try {
			int st = 1000 * 60 * 60 * 24;	// 24 hours
			Thread.sleep(st);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
