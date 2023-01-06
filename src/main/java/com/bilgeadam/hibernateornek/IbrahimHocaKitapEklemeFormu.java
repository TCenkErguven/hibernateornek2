package com.bilgeadam.hibernateornek;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class IbrahimHocaKitapEklemeFormu {


	private JFrame frame;
	private JTextField txtBaslik;
	private JTextField txtYazar;
	private JTextField txtFiyat;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IbrahimHocaKitapEklemeFormu window = new IbrahimHocaKitapEklemeFormu();
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
	public IbrahimHocaKitapEklemeFormu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Başlık :");
		lblNewLabel.setBounds(33, 74, 84, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblYazar = new JLabel("Yazar :");
		lblYazar.setBounds(33, 111, 84, 14);
		frame.getContentPane().add(lblYazar);
		
		JLabel lblFiyat = new JLabel("Fiyat :");
		lblFiyat.setBounds(33, 151, 84, 14);
		frame.getContentPane().add(lblFiyat);
		
		txtBaslik = new JTextField();
		txtBaslik.setBounds(127, 71, 131, 20);
		frame.getContentPane().add(txtBaslik);
		txtBaslik.setColumns(10);
		
		txtYazar = new JTextField();
		txtYazar.setBounds(127, 108, 131, 20);
		frame.getContentPane().add(txtYazar);
		txtYazar.setColumns(10);
		
		txtFiyat = new JTextField();
		txtFiyat.setBounds(127, 148, 131, 20);
		frame.getContentPane().add(txtFiyat);
		txtFiyat.setColumns(10);
		
		JButton btnNewButton = new JButton("Kaydet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapManager kitapManager = new KitapManager();
				kitapManager.setup();
				Kitap kitap = new Kitap();
				kitap.setBaslik(txtBaslik.getText());  // baslik değişkenine değer atandı
				kitap.setYazar(txtYazar.getText());  // yazar değişkenine değer atandı
				kitap.setFiyat(Integer.valueOf(txtFiyat.getText()));  // fiyat değişkenine değer atandı
				kitapManager.KayitEkle(kitap);
				kitapManager.exit();
				
			}
		});
		btnNewButton.setBounds(169, 203, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 288, 260, 155);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		KitapManager manager = new KitapManager();
		manager.setup();
		List<Kitap> kitapListesi = manager.listeGetir();
		TablodaGoster(kitapListesi);
		
	
		
	}
	
	public void ButtonEkle()
	{
		
		JButton btnNewButton_1 = new JButton("1");
		btnNewButton_1.setBounds(102, 299, 39, 23);
		btnNewButton_1.setVisible(true);
	}

	public void TablodaGoster(List<Kitap> kitapListesi)
	{
		   tableModel = new DefaultTableModel();
	        Object[] columnsName = new Object[4];
	        columnsName[0] = "kitap_id";
	        columnsName[1] = "Baslik";
	        columnsName[2] = "Yazar";
	        columnsName[3] = "Fiyat";
	        tableModel.setColumnIdentifiers(columnsName);
	        Object[] rowData = new Object[4];
	        
	        for(int i = 0; i < kitapListesi.size(); i++){
	            
	            rowData[0] = kitapListesi.get(i).getId();
	            rowData[1] = kitapListesi.get(i).getBaslik();
	            rowData[2] = kitapListesi.get(i).getYazar();
	            rowData[3] = kitapListesi.get(i).getFiyat();
	            tableModel.addRow(rowData);
	        }
	        table.setModel(tableModel);
	
	}
	
}