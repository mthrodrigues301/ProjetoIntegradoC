package Client.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.Util.*;
import Helper.*;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;

public class JanelaDeMusicas extends JFrame {

	private JPanel contentPane;
	private JTextField txtPesquisa;
	private Parceiro servidor;
	private JButton btnPesquisar;
	private JComboBox<String> cmbCategoria;
	private JanelaDeCompras janelaDeCompras;
	private Lista<Musica> musicas;
	private JList<String> list;

	public JanelaDeMusicas(Parceiro servidor) throws Exception {
		if (servidor == null)
			throw new Exception("Servidor indisponivel");

		this.servidor = servidor;

		setTitle("Loja DuMa Musica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(140, 50, 241, 22);
		txtPesquisa.setColumns(10);
		contentPane.add(txtPesquisa);

//		txtCategoria = new JTextField();
//		txtCategoria.setColumns(10);
//		txtCategoria.setBounds(12, 51, 118, 22);
//		contentPane.add(txtCategoria);

		cmbCategoria = new JComboBox<String>();
		cmbCategoria.setBounds(12, 51, 118, 20);
		contentPane.add(cmbCategoria);

		try {
			this.servidor.receba(new Comunicado("CMB"));

			Comunicado comunicado = this.servidor.envie();

			if (comunicado.getComando().equals("EST")) {
				String estilo = comunicado.getComplemento1();

				if (estilo != null)
					cmbCategoria.addItem(estilo);
			}

//			for (;;) {
//				Comunicado comunicado = servidor.envie();
//
//				if (comunicado == null || comunicado.getComando().equals("FIM"))
//					break;
//				else if (comunicado.getComando().equals("EST")) {
//					String estilo = comunicado.getComplemento1();
//
//					if (estilo != null) {
//						cmbCategoria.addItem(estilo);
////						break;
//					}
//				}
//			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null/* sem janela mãe */, "Tente novamente mais tarde!",
					"Erro de conectividade", JOptionPane.ERROR_MESSAGE);
		}

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String Categoria = (String) cmbCategoria.getSelectedItem();
					String pesquisa = txtPesquisa.getText();
					servidor.receba(new Comunicado("CON", Categoria, pesquisa));

					Comunicado comunicado = servidor.envie();

					if (!comunicado.getComando().equals("ERR")) {
						musicas = new Lista<Musica>();
						musicas.insereItem(new Musica(comunicado.getComplemento1(), comunicado.getComplemento2(),
								comunicado.getComplemento3(), Integer.parseInt(comunicado.getComplemento4()),
								Double.parseDouble(comunicado.getComplemento5())));
						LoadList();
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null/* sem janela mãe */, "Tente novamente mais tarde!",
							"Erro de conectividade", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnPesquisar.setBounds(115, 101, 203, 25);
		contentPane.add(btnPesquisar);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(12, 29, 56, 16);
		contentPane.add(lblCategoria);

		JLabel lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setBounds(140, 29, 56, 16);
		contentPane.add(lblPesquisa);
		
		list = new JList<String>();
		list.setBounds(20, 133, 298, 117);
		contentPane.add(list);

		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}
	
	private void LoadList() {
		try {
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			while(!this.musicas.isVazia()) {
				DLM.addElement(this.musicas.getItem().toString());
				this.musicas.removeItem();
			}
			list.setModel(DLM);
		}catch(Exception ex) {}
	}
	
	public JanelaDeCompras getJanelaDeCompras() {
		while (this.janelaDeCompras == null)
			Thread.currentThread().yield();

		return this.janelaDeCompras;
	}

	private void janelaDeCompras() {
		try {
			this.janelaDeCompras = new JanelaDeCompras(this.servidor, this.musicas);
		} catch (Exception ex) {
		}
	}
}