package Client.View;

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
	private Lista<Musica> musicas;
	private JList<String> list;

	public JanelaDeMusicas(Parceiro servidor) throws Exception {
		if (servidor == null) {
			JOptionPane.showMessageDialog(null/* sem janela mãe */, "Tente novamente mais tarde!",
					"Servidor indisponivel", JOptionPane.ERROR_MESSAGE);
		}

		this.servidor = servidor;

		setTitle("Loja DuMa Musica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(140, 50, 404, 22);
		txtPesquisa.setColumns(10);
		contentPane.add(txtPesquisa);

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
			this.setVisible(false);
		}

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String Categoria = (String) cmbCategoria.getSelectedItem();
					String pesquisa = txtPesquisa.getText();
					if (pesquisa == null) {
						pesquisa = "";
					}

					servidor.receba(new Comunicado("CON", Categoria, pesquisa));

					Comunicado comunicado = servidor.envie();

					if (!comunicado.getComando().equals("ERR")) {
						musicas = new Lista<Musica>();
						musicas.insereItem(new Musica(comunicado.getComplemento1(), comunicado.getComplemento2(),
								comunicado.getComplemento3(), Integer.parseInt(comunicado.getComplemento4()),
								Double.parseDouble(comunicado.getComplemento5())));
						LoadList();
						
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null/* sem janela mãe */, "Tente novamente mais tarde!",
							ex.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnPesquisar.setBounds(556, 49, 102, 25);
		contentPane.add(btnPesquisar);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(12, 29, 56, 16);
		contentPane.add(lblCategoria);

		JLabel lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setBounds(140, 29, 56, 16);
		contentPane.add(lblPesquisa);

		list = new JList<String>();
		list.setBounds(12, 133, 646, 246);
		contentPane.add(list);

		JLabel lblMusicas = new JLabel("Musicas");
		lblMusicas.setBounds(12, 104, 56, 16);
		contentPane.add(lblMusicas);

		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	private void LoadList() {
		try {
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			while (!this.musicas.isVazia()) {
				DLM.addElement(this.musicas.getItem().toString());
				this.musicas.removeItem();
			}
			list.setModel(DLM);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null/* sem janela mãe */, "Tente novamente mais tarde!", ex.getMessage(),
					JOptionPane.ERROR_MESSAGE);
		}
	}
}