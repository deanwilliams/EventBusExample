package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.common.eventbus.EventBus;

public class ChildDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChildDialog dialog = new ChildDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fireEvent() {
		// TODO
		EventBus eventBus = EventBusFactory.getInstance();
		eventBus.post(new TestEvent(generateString(new Random(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10)));
	}
	
	private void fireEvent2() {
		EventBus eventBus = EventBusFactory.getInstance();
		Random rand = new Random();
		int max = 1000;
		int min = 1;
		eventBus.post(new TestEvent2(rand.nextInt(max - min + 1) + min));
	}
	
	public static String generateString(Random rng, String characters, int length)
	{
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}

	/**
	 * Create the dialog.
	 */
	public ChildDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JButton btnFireEvent = new JButton("Fire Event");
			btnFireEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fireEvent();
				}
			});
			contentPanel.add(btnFireEvent);
		}
		{
			JButton btnFireEvent_1 = new JButton("Fire Event 2");
			btnFireEvent_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fireEvent2();
				}
			});
			contentPanel.add(btnFireEvent_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
