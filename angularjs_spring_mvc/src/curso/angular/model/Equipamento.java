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
 * Modelo que representa a tabela de Equipamentos 
 * @author Michael
 *
 */
@Entity
public class Equipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String tag;
	
	private String equipamento;

	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="fabricante_fk")
	private Fabricante fabricante;	

	private String modelo;
	
	private String numeroSerie; 

	private String patrimonio;

	private String unidade;
	
	private String local;
	
	private String setor;
	
	private String servico;
	
	private String pre;
	
	private String insufamento;
	
	private String exaustao;
	
	private String manometro;
	
	private String tipo;
	
	//atributos de auditoria 
	private String status;

	private String data_exclusao; 
	
	private String data_alteracao; 
	
	private String data_inclusao; 
	
	private String usuario_exclusao; 
	
	private String usuario_alteracao; 
	
	private String usuario_inclusao; 
	



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}	
	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}

	public String getInsufamento() {
		return insufamento;
	}

	public void setInsufamento(String insufamento) {
		this.insufamento = insufamento;
	}

	public String getExaustao() {
		return exaustao;
	}

	public void setExaustao(String exaustao) {
		this.exaustao = exaustao;
	}

	public String getManometro() {
		return manometro;
	}

	public void setManometro(String manometro) {
		this.manometro = manometro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		Equipamento other = (Equipamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
