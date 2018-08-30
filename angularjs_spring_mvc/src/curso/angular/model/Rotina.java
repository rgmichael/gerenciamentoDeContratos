package curso.angular.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

/**
 * Modelo que representa a tabela de rotina 
 * @author Francisco
 *
 */
@Entity
public class Rotina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="tipo_rotina_fk")
	private TipoRotina tipoRotina;
	
	private String tipoEnsaio;	

	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="equipamento_fk")
	private Equipamento equipamento;	

	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="instrumento_fk")
	private Instrumento instrumento;	

	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="area_fk")
	private Area area;	

	private String status;	
	
	//atributos de auditoria 

	private String data_exclusao; 
	
	private String data_alteracao; 
	
	private String data_inclusao; 
	
	private String usuario_exclusao; 
	
	private String usuario_alteracao; 
	
	private String usuario_inclusao; 
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoRotina getTipoRotina() {
		return tipoRotina;
	}

	public void setTipoRotina(TipoRotina tipoRotina) {
		this.tipoRotina = tipoRotina;
	}

	public String getTipoEnsaio() {
		return tipoEnsaio;
	}

	public void setTipoEnsaio(String tipoEnsaio) {
		this.tipoEnsaio = tipoEnsaio;
	}
	
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Instrumento getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

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
		Rotina other = (Rotina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
