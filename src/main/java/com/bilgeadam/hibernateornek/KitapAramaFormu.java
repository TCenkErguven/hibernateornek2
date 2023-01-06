package com.bilgeadam.hibernateornek;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class KitapAramaFormu {
	protected SessionFactory sessionFactory;

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapAramaFormu window = new KitapAramaFormu();
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
	public KitapAramaFormu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 446, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(49, 56, 342, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAra = new JButton("Ara");
		btnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapManager manager = new KitapManager();
				manager.setup();
				manager.AramayaGoreGetir(textField.getText());
			}
		});
		btnAra.setBounds(302, 99, 89, 23);
		frame.getContentPane().add(btnAra);

		
	}
}
