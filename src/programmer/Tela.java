package programmer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Tela extends JFrame{

	private JLabel lbNomeEmpresa;
	private JTextField tfNomeEmpresa;
	private JLabel lbSobre;
	private JTextField tfSobre;
	private JLabel lbData;
	private JTextField tfData;
	private JLabel lbSalario;
	private JTextField tfSalario;
	
	private JButton btInserir;
	private JButton btDelete;
	private JButton btEdite;

	
	private JTable tabela;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JScrollPane barraRolagem = new JScrollPane();
	
	
	private List<MyWork> workList = new ArrayList<MyWork>();
	
	public Tela() {
		setTitle("The best Work");
		setDefaultCloseOperation(Tela.EXIT_ON_CLOSE);// qnd fechar o programa vai encerrar
		setSize(700, 600);
	 	setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null); 
	
		lbNomeEmpresa = new JLabel("Empresa");
		lbNomeEmpresa.setBounds(20, 20, 150,20);
		tfNomeEmpresa = new JTextField("");
		tfNomeEmpresa.setBounds(20,50, 400,25);
		
		lbSobre = new JLabel("Soubre");
		lbSobre.setBounds(20, 80, 150,20);
		tfSobre = new JTextField("");
		tfSobre.setBounds(20,100, 400,25);
		
		lbData = new JLabel("Data");
		lbData.setBounds(470, 20, 150,20);
		tfData = new JTextField("");
		tfData.setBounds(470, 50, 100,25);
		
		lbSalario = new JLabel("Salario");
		lbSalario.setBounds(470, 80, 150,20);
		tfSalario = new JTextField("");
		tfSalario.setBounds(470,100, 100,25);
		
		btInserir = new JButton("Inserir");
		btInserir.setBounds(20, 130, 200,40);
		btInserir.addActionListener((evente) -> {
			salvarWork();
		});
		
		btDelete = new JButton("Delete");
		btDelete.setBounds(245, 130, 200,40);
		btDelete.addActionListener((evente) -> {
			deleteWork();
		});
		
		btEdite = new JButton("Edite");
		btEdite.setBounds(470, 130, 200,40);
		btEdite.addActionListener((evente) -> {
			editeWork();
		});
		
		
		
		tabela = new JTable(modelo);// criando a tabela
		modelo.addColumn("Empresa");
		modelo.addColumn("Sobre");
		modelo.addColumn("Data");
		modelo.addColumn("Salario");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(125);// tamanho da columa
		tabela.getColumnModel().getColumn(1).setPreferredWidth(325);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(50);

		barraRolagem = new JScrollPane(tabela);// criar nela a barra
		barraRolagem.setBounds(20, 180, 650, 350);
		add(barraRolagem);
		
		
		add(lbNomeEmpresa);
		add(tfNomeEmpresa);
		add(lbSobre);
		add(tfSobre);
		add(lbData);
		add(tfData);
		add(lbSalario);
		add(tfSalario);
		add(btInserir);
		add(btDelete);
		add(btEdite);
	
		atualizarTabela();
	}



	public void editeWork() {
		int posicao = tabela.getSelectedRow();
		if(posicao < 0) {
			JOptionPane.showMessageDialog(null, "Selecione um post");
		} else {
			String nomeEmpresa = tfNomeEmpresa.getText();
			String sobre = tfSobre.getText();
			String data = tfData.getText();
			String salario = tfSalario.getText();
			
			if (nomeEmpresa.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Informe o nome da empresa ou startup");
			} else if( sobre.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Informe a vaga de emprego");
			} else if( data.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Informe uma data");
			} else if( salario.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Informe o salario (OBRIGATORIO)");
			} else {
				MyWork work = workList.get(posicao);
					work.setNomeEmpresa(nomeEmpresa);
					work.setSobre(sobre);
					work.setData(data);
					work.setSalario(salario);
					
					salvarArquivo();
					atualizarTabela();
					tfNomeEmpresa.setText(" ");
					tfSobre.setText(" ");
					tfData.setText(" ");
					tfSalario.setText(" ");
			}
			
		}
	}

	public void deleteWork() {
		int posicao = tabela.getSelectedRow();//vai me dar a linha que esta selecionado 
		if(posicao < 0) {
			JOptionPane.showMessageDialog(null, "Selecione uma postagem");
		} else {
			int resposta = JOptionPane.showConfirmDialog(null, "Voce quer apagar este post?");
			if(resposta == 0) {//0 = sim
				workList.remove(posicao);
			salvarArquivo();
			atualizarTabela();
			}
	}
	}
		
	public void salvarWork() {
		String nomeEmpresa = tfNomeEmpresa.getText();
		String sobre = tfSobre.getText();
		String data = tfData.getText();
		String salario = tfSalario.getText();
		
		if (nomeEmpresa.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe o nome da empresa ou startup");
		} else if( sobre.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe a vaga de emprego");
		} else if( data.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe uma data");
		} else if( salario.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe o salario (OBRIGATORIO)");
		} else {
			
			MyWork work = new MyWork(nomeEmpresa, sobre, data, salario);
			workList.add(work);
			salvarArquivo();
			atualizarTabela();
			
			tfNomeEmpresa.setText(" ");
			tfSobre.setText(" ");
			tfData.setText(" ");
			tfSalario.setText(" ");
		}
		
	}

	public void atualizarTabela() {
		lerArquivo();
		modelo.setRowCount(0);
		for( MyWork work : workList) {

			modelo.addRow(new Object[] {
					work.getNomeEmpresa(),
					work.getSobre(),
					work.getData(),
					work.getSalario()
			});
			
		}
		
	}
	
	
	public void salvarArquivo() {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("C:\\Users\\LAILTON\\Documents\\ApaceEclipse\\MyWork\\arquivos\\works.txt");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(workList);
			objectOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
		public void lerArquivo() {
			FileInputStream fileImput;
			try {
				fileImput = new FileInputStream("C:\\Users\\LAILTON\\Documents\\ApaceEclipse\\MyWork\\arquivos\\works.txt");
				ObjectInputStream obj = new ObjectInputStream(fileImput);
				Object objre = obj.readObject();
				if(objre != null) {
					workList = (ArrayList<MyWork>) objre;
				}
				obj.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
	}

	
	
	
	
	
	
}