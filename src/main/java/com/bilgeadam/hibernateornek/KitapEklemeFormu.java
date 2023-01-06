package com.bilgeadam.hibernateornek;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import javax.persistence.Query;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.Color;

public class KitapEklemeFormu {

	private JFrame frame;
	private JTextField textBaslik;
	private JTextField textYazar;
	private JTextField textFiyat;
	private JLabel lblSonuc;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnAltıOn;
	private JButton btnBirBes;
	private JButton btnDevami;
	private KitapManager kitapManager = new KitapManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapEklemeFormu window = new KitapEklemeFormu();
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
	public KitapEklemeFormu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(160, 82, 45));
		frame.setBounds(100, 100, 548, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBaslik = new JLabel("Başlık :");
		lblBaslik.setBounds(49, 56, 46, 14);
		frame.getContentPane().add(lblBaslik);
		
		JLabel lblNewLabel = new JLabel("Yazar :");
		lblNewLabel.setBounds(49, 105, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fiyat :");
		lblNewLabel_1.setBounds(49, 152, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textBaslik = new JTextField();
		textBaslik.setBounds(125, 53, 124, 20);
		frame.getContentPane().add(textBaslik);
		textBaslik.setColumns(10);
		
		textYazar = new JTextField();
		textYazar.setColumns(10);
		textYazar.setBounds(125, 102, 124, 20);
		frame.getContentPane().add(textYazar);
		
		textFiyat = new JTextField();
		textFiyat.setColumns(10);
		textFiyat.setBounds(125, 149, 124, 20);
		frame.getContentPane().add(textFiyat);
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapManager manager = new KitapManager();
				manager.setup();
				String baslik = textBaslik.getText();
				String yazar = textYazar.getText();
				int fiyat = Integer.parseInt(textFiyat.getText());
				
				/*
				 *Kitap kitap = new Kitap();
				 *kitap.setBaslik(txtBaslik.getText());
				 *kitap.setYazar(txtYazar.getText());
				 *kitap.setFiyat(txtFiyat.getText());
				 * manager.KayitEkle(Kitap kitap);
				 */
				manager.KayitEkle(baslik, yazar, fiyat);
				manager.exit();
				lblSonuc.setText("Kayıt Başarılı");
			}
		});
		btnKaydet.setBounds(147, 180, 89, 23);
		frame.getContentPane().add(btnKaydet);
		
		lblSonuc = new JLabel("");
		lblSonuc.setBounds(69, 292, 230, 14);
		frame.getContentPane().add(lblSonuc);
		
		JPanel panel = new JPanel();
		panel.setBounds(49, 236, 431, 131);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 431, 131);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnBirBes = new JButton("1");
		btnBirBes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 1;
				KitapGetir(i);
				
			}
		});
		btnBirBes.setBounds(110, 391, 73, 23);
		frame.getContentPane().add(btnBirBes);
		
		btnAltıOn = new JButton("2");
		btnAltıOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 2;
				KitapGetir(i);
		
			}
		});
		btnAltıOn.setBounds(214, 391, 73, 23);
		frame.getContentPane().add(btnAltıOn);
		
		btnDevami = new JButton("3");
		btnDevami.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 3;
				KitapGetir(i);
				
			}
		});
		btnDevami.setBounds(319, 391, 73, 23);
		frame.getContentPane().add(btnDevami);
		

		KitapGetir();
	}
	
	public void KitapGetir() {
		
		KitapManager kitapManager = new KitapManager();
		kitapManager.setup();
		tableModel = new DefaultTableModel();
		
		Object[] kolon = new Object[4];
		kolon[0] = "Id";
		kolon[1] = "Başlık";
		kolon[2] = "Yazar";
		kolon[3] = "Message";
		tableModel.setColumnIdentifiers(kolon);
		
		Object[] satir = new Object[4];
		List<Kitap>kitapList = kitapManager.listeGetir();
		
		for(Kitap i : kitapList) {
			satir[0] = i.getId();
			satir[1] = i.getBaslik();
			satir[2] = i.getYazar();
			satir[3] = i.getFiyat();
			tableModel.addRow(satir);
		}
		table.setModel(tableModel);
	}
public void KitapGetir(int count) {
		
		
		int endingpoint = count*5;
		int startingpoint = endingpoint-5;
		List<Kitap>subKitap = null;
		kitapManager.setup();
		tableModel = new DefaultTableModel();
		
		Object[] kolon = new Object[4];
		kolon[0] = "Id";
		kolon[1] = "Başlık";
		kolon[2] = "Yazar";
		kolon[3] = "Message";
		tableModel.setColumnIdentifiers(kolon);
		
		Object[] satir = new Object[4];
		List<Kitap>kitapList = kitapManager.listeGetir();
		if(endingpoint<kitapList.size()) {
			subKitap = kitapList.subList(startingpoint,endingpoint);
		}else {
			subKitap = kitapList.subList(startingpoint,(kitapList.size()-1));
		}
		
		for(Kitap i : subKitap){
			satir[0] = i.getId();
			satir[1] = i.getBaslik();
			satir[2] = i.getYazar();
			satir[3] = i.getFiyat();
			tableModel.addRow(satir);
		}
		table.setModel(tableModel);
	}


	
}
