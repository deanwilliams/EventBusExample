package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventBusTest {

	private JFrame frame;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventBusTest window = new EventBusTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EventBusTest() {
		initialize();
		EventBus eventBus = EventBusFactory.getInstance();
		eventBus.register(this);
	}
	
	@Subscribe
	public void handleChildEvent(TestEvent event) {
		System.out.println("Handle Child Event - " + event.getValue());
		try {
			textPane.getDocument().insertString(textPane.getDocument().getLength(), event.getValue() + "\n", null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Subscribe
	public void handleChildEvent2(TestEvent2 event) {
		System.out.println("Handle Child Event 2 - " + event.getValue());
		try {
			textPane.getDocument().insertString(textPane.getDocument().getLength(), event.getValue() + "\n", null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnOpenChild = new JButton("Open Child");
		btnOpenChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChildDialog child = new ChildDialog();
				child.setVisible(true);
			}
		});
		panel.add(btnOpenChild);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
	}

}
