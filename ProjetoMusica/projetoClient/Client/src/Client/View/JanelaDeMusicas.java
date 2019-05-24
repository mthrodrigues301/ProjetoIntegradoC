package Client.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Client.Util.*;
import Helper.*;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;

public class JanelaDeMusicas extends JFrame {

	private JPanel contentPane;
	private JTextField txtPesquisa;
	private Parceiro servidor;
	private JButton btnPesquisar;
	private JComboBox<String> cmbCategoria;
	private Lista<Musica> musicas;
	private JList<Musica> listMusicas;
	private JList<Musica> listCarrinho;
	private JButton Remover;
	private Comunicado comunicado;
	private double precoTotal = 0;
	private int tempoTotal = 0;

	// MODEL
	private DefaultListModel<Musica> dmMusicas = new DefaultListModel<Musica>();
	private DefaultListModel<Musica> dmCarrinho = new DefaultListModel<Musica>();
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	public JanelaDeMusicas(Parceiro servidor) throws Exception {
		if (servidor == null) {
			JOptionPane.showMessageDialog(null/* sem janela mãe */, "Tente novamente mais tarde!",
					"Servidor indisponivel", JOptionPane.ERROR_MESSAGE);
			return;
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
			servidor.receba(new Comunicado("CMB"));
			this.cmbCategoria.addItem("Todas");

			for (;;) {
				comunicado = servidor.envie();
				if (comunicado.getComando().equals("FIC") || comunicado.getComando().equals("ERR"))
					break;

				String estilo = comunicado.getComplemento3();
				if (estilo != null)
					this.cmbCategoria.addItem(estilo);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null/* sem janela mãe */, "Tente novamente mais tarde!",
					"Erro de conectividade", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
		}

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String combo = cmbCategoria.getSelectedItem().toString();
					String pesq = txtPesquisa.getText();
					if (combo.equals("Todas"))
						combo = "TODAS";

					if (pesq.equals(""))
						pesq = "VAZIO";

					servidor.receba(new Comunicado("CON", combo, pesq));
					musicas = new Lista<Musica>();
					for (;;) {
						comunicado = servidor.envie();

						if (comunicado.getComando().equals("ERR")) {
							dmCarrinho.clear();
							dmMusicas.clear();
							txtPesquisa.setText("");
							throw new Exception("Erro! Tente novamente mais tarde");
						}

						if (comunicado.getComando().equals("FIC")) {
							LoadList();
							break;
						}

						musicas.insereItem(new Musica(comunicado.getComplemento1(), comunicado.getComplemento2(),
								comunicado.getComplemento3(), Integer.parseInt(comunicado.getComplemento4()),
								Double.parseDouble(comunicado.getComplemento5())));
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null/* sem janela mãe */, ex.getMessage(), "Erro!",
							JOptionPane.ERROR_MESSAGE);
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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 109, 264, 246);
		contentPane.add(scrollPane);

		this.listMusicas = new JList<Musica>();
		scrollPane.setViewportView(listMusicas);
		this.listMusicas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblMusicas = new JLabel("Musicas");
		lblMusicas.setBounds(12, 92, 56, 16);
		contentPane.add(lblMusicas);

		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double desconto = 0;
				if (dmCarrinho.isEmpty()) {
					JOptionPane.showMessageDialog(null/* sem janela mãe */, null, "Erro!", JOptionPane.ERROR_MESSAGE);
				} 
				else {
					for (int i = 0; i < dmCarrinho.getSize(); i++) {
						precoTotal += dmCarrinho.getElementAt(i).getPreco();
						tempoTotal += dmCarrinho.getElementAt(i).getDuracao();
						dmCarrinho.removeElementAt(i);
					}
					dmCarrinho.clear();
					
					if (tempoTotal >= 1800 && tempoTotal < 3600) {
						String preco = String.format("%.2f", precoTotal);
						desconto = precoTotal * 0.9;
						String resultado = String.format("%.2f", desconto);
						JOptionPane.showMessageDialog(null, "Preço total: R$" + preco + "\nDesconto: 10%"
								+ "\nPreço com Desconto: R$" + resultado);
					}
					else if (tempoTotal >= 3600 && tempoTotal < 5400) {
						String preco = String.format("%.2f", precoTotal);
						desconto = precoTotal * 0.8;
						String resultado = String.format("%.2f", desconto);
						JOptionPane.showMessageDialog(null, "Preço total: R$" + preco + "\nDesconto: 20%"
								+ "\nPreço com Desconto: R$" + resultado);
					}
					else if (tempoTotal > 5400) {
						String preco = String.format("%.2f", precoTotal);
						desconto = precoTotal * 0.7;
						String resultado = String.format("%.2f", desconto);
						JOptionPane.showMessageDialog(null, "Preço total: R$" + preco + "\nDesconto: 30%"
								+ "\nPreço com Desconto: R$" + resultado);
					} 
					else {
						String preco = String.format("%.2f", precoTotal);
						JOptionPane.showMessageDialog(null, "Preço total: R$" + preco);
					}
					desconto = 0;
					precoTotal = 0;
					tempoTotal = 0;
				}
			}
		});
		btnFinalizarCompra.setBounds(482, 366, 176, 23);
		contentPane.add(btnFinalizarCompra);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (listMusicas == null)
						throw new Exception("Sem itens na lista de Musicas, faça uma pesquisa!");

					if (listMusicas.getSelectedValue() == null)
						throw new Exception("Selecione uma musica para adicionar ao carrinho!");

					dmCarrinho.addElement(listMusicas.getSelectedValue());
					listCarrinho.setModel(dmCarrinho);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null/* sem janela mãe */, ex.getMessage(), "Erro!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdicionar.setToolTipText("Adicionar ao carrinho");
		btnAdicionar.setBounds(286, 154, 98, 38);
		contentPane.add(btnAdicionar);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(394, 109, 264, 246);
		contentPane.add(scrollPane_1);

		this.listCarrinho = new JList<Musica>();
		scrollPane_1.setViewportView(listCarrinho);
		this.listCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblCarrinho = new JLabel("Carrinho");
		lblCarrinho.setBounds(394, 93, 70, 14);
		contentPane.add(lblCarrinho);

		Remover = new JButton("Remover");
		Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (listCarrinho == null)
						throw new Exception("Sem itens na lista de Carrinho!");

					int index = listCarrinho.getSelectedIndex();

					if (index < 0)
						throw new Exception("Selecione uma musica para remover do Carrinho!");

					dmCarrinho.removeElementAt(index);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null/* sem janela mãe */, ex.getMessage(), "Erro!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		Remover.setToolTipText("Remover ao carrinho");
		Remover.setBounds(286, 243, 98, 38);
		contentPane.add(Remover);

		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	private void LoadList() {
		try {
			if (!this.musicas.isVazia()) {
				this.listMusicas.removeAll();
				this.dmMusicas = new DefaultListModel<Musica>();

				while (!this.musicas.isVazia()) {
					this.dmMusicas.addElement(this.musicas.getItem());
					this.musicas.removeItem();
				}
				this.listMusicas.setModel(this.dmMusicas);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null/* sem janela mãe */, ex.getMessage(), "Erro!",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
