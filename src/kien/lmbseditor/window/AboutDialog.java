package kien.lmbseditor.window;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;

public class AboutDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6215898347891178069L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 240);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[50px]", "[13px][][]"));
		{
			JLabel lblNewLabel = new JLabel("    This application is developed with following");
			contentPanel.add(lblNewLabel, "cell 0 0,alignx left,aligny top");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Apache Licensed Library:");
			contentPanel.add(lblNewLabel_1, "cell 0 1,alignx left");
		}
		{
			JButton btnNewButton = new JButton("<HTML>* <FONT color=\\\"#000099\\\"><U>JSONIC</U></FONT>");
			btnNewButton.setActionCommand("<HTML>* <FONT color=\\\"#000099\\\"><U>JSONIC</U></FONT></HTML>");
			btnNewButton.setOpaque(false);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						open(new URI("http://jsonic.osdn.jp/index.html"));
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setContentAreaFilled(false);
			btnNewButton.setBorderPainted(false);
			contentPanel.add(btnNewButton, "cell 0 2");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton apacheButton = new JButton("Open Apache License");
				apacheButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							open(new URI("http://www.apache.org/licenses/LICENSE-2.0"));
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(apacheButton);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AboutDialog.this.setVisible(false);
						AboutDialog.this.dispatchEvent(new WindowEvent(
								AboutDialog.this, WindowEvent.WINDOW_CLOSING));
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private static void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
