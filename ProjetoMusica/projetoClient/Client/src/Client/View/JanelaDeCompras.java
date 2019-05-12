package Client.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.Util.Lista;
import Client.Util.Musica;
import Client.Util.Parceiro;

import javax.swing.JLabel;
import javax.swing.JList;

public class JanelaDeCompras extends JFrame {

	private JPanel contentPane;
	private Parceiro servidor;
	private Lista<Musica> musicas;
	private JList<String> list;
	
	public JanelaDeCompras(Parceiro servidor, Lista<Musica> musicas)throws Exception {
		if (servidor == null)
			throw new Exception("Servidor indisponivel");
		
		if (musicas == null)
			throw new Exception("Servidor indisponivel");
		
		this.servidor = servidor;
		this.musicas = musicas;
		
		setTitle("Loja DuMa Musica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListaDeMusicas = new JLabel("Lista de Musicas:");
		lblListaDeMusicas.setBounds(10, 11, 89, 14);
		contentPane.add(lblListaDeMusicas);
		
		list = new JList<String>();
		list.setBounds(20, 40, 371, 195);
		LoadList();
		contentPane.add(list);
		
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}
	
	private void LoadList() {
		try {
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			while(!this.musicas.isVazia()) {
				DLM.addElement(this.musicas.getItem().toString());
			}
			list.setModel(DLM);
		}catch(Exception ex) {}
	}
}
