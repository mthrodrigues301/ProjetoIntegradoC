package Client.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.Util.Parceiro;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class JanelaDeMusicas extends JFrame {

	private JPanel contentPane;
	private JTextField txtCategoria;
	private JTextField txtPesquisa;
	private Parceiro servidor;

	public JanelaDeMusicas(Parceiro servidor) throws Exception {
System.out.println("toaqui na janela");
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

		txtCategoria = new JTextField();
		txtCategoria.setBounds(12, 50, 116, 22);
		contentPane.add(txtCategoria);
		txtCategoria.setColumns(10);

		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(140, 50, 241, 22);
		contentPane.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(115, 101, 203, 25);
		contentPane.add(btnPesquisar);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(12, 29, 56, 16);
		contentPane.add(lblCategoria);

		JLabel lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setBounds(140, 29, 56, 16);
		contentPane.add(lblPesquisa);

		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}
}
