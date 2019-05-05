package Client.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.Util.Comunicado;
import Client.Util.Parceiro;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaDeMusicas extends JFrame {

	private JPanel contentPane;
	private JTextField txtCategoria;
	private JTextField txtPesquisa;
	private Parceiro servidor;
	private JButton btnPesquisar;

	public JanelaDeMusicas(Parceiro servidor) throws Exception {
		if (servidor == null)
			throw new Exception("Servidor indisponivel");

//		this.servidor = servidor;

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

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean comunicadoOk = true;

				try {
					servidor.receba(new Comunicado("COM", txtCategoria.getText(), txtPesquisa.getText()));

					Comunicado comunicado = servidor.envie();

					System.out.println("RESULTADO: " + comunicado.getComando());
					if (comunicado.getComando().equals("ERR"))
						comunicadoOk = false;

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null/* sem janela mãe */, "Tente novamente mais tarde!",
							"Erro de conectividade", JOptionPane.ERROR_MESSAGE);
					return;
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

		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}
}
