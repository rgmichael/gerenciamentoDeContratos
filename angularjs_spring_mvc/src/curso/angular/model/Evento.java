package curso.angular.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Modelo que representa a tabela de Fabricantes do banco
 * @author mike
 *
 */ 
@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;
	private String start;
	private String end_time;
	private String descricao;
	private String cor;
	
	private String data_exclusao; 
	
	private String data_alteracao; 
	
	private String data_inclusao; 
	
	private String usuario_exclusao; 
	
	private String usuario_alteracao; 
	
	private String usuario_inclusao; 
	
	

	public String getData_exclusao() {
		return data_exclusao;
	}

	public void setData_exclusao(String data_exclusao) {
		this.data_exclusao = data_exclusao;
	}

	public String getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(String data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	public String getData_inclusao() {
		return data_inclusao;
	}

	public void setData_inclusao(String data_inclusao) {
		this.data_inclusao = data_inclusao;
	}

	public String getUsuario_exclusao() {
		return usuario_exclusao;
	}

	public void setUsuario_exclusao(String usuario_exclusao) {
		this.usuario_exclusao = usuario_exclusao;
	}

	public String getUsuario_alteracao() {
		return usuario_alteracao;
	}

	public void setUsuario_alteracao(String usuario_alteracao) {
		this.usuario_alteracao = usuario_alteracao;
	}

	public String getUsuario_inclusao() {
		return usuario_inclusao;
	}

	public void setUsuario_inclusao(String usuario_inclusao) {
		this.usuario_inclusao = usuario_inclusao;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
