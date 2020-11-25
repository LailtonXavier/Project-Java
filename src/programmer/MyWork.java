package programmer;

import java.io.Serializable;

public class MyWork implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String nomeEmpresa;
	private String sobre;
	private String data;
	private String salario;
	
	public MyWork(String nomeEmpresa, String sobre, String data, String salario) {
		this.nomeEmpresa = nomeEmpresa;
		this.sobre = sobre;
		this.data = data;
		this.salario = salario;
	}
	public MyWork() {
		
	}
	
	
	
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getSobre() {
		return sobre;
	}
	public void setSobre(String sobre) {
		this.sobre = sobre;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}

	

}
