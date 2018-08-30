package curso.angular.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

/**
 * Modelo que representa a tabel de Cliente do banco
 * @author alex
 *
 */
@Entity
public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//@NotNull(message="Selecione um horário")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String status;

	private String obs; 
	
	private String dia; 
	
	private String hora;
	
	private String foto;

	
	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="usuario_fk")
	private Usuario usuario;// = new Usuario();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="cliente_fk")
	private Usuario cliente;// = new Usuario();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="barbeiro_fk")
	private Usuario barbeiro;// = new Usuario();
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="servico_fk")
	private Servico servico; //= new Servico();
	

	private ArrayList<Servico> servicos ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	


	public Usuario getBarbeiro() {
		return barbeiro;
	}

	public void setBarbeiro(Usuario barbeiro) {
		this.barbeiro = barbeiro;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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
		Agenda other = (Agenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public ArrayList<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(ArrayList<Servico> servicos) {
		this.servicos = servicos;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	

}
