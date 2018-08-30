package curso.angular.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Modelo que representa a tabela de Area 
 * @author Francisco
 *
 */
@Entity
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String tag;
	
	private String item;

	/*
	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="fabricante_fk")
	private Fabricante fabricante;	
	*/
	
	private String unidade;
	
	private String local;
	
	private String setor;
	
	private String servico;
	
	private String classe;
	
	private String contrato; 

	private String revisado;
	
	private String altura;
	
	private String volume;	
	
	private String status;	
	
	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	//
	private String caracteristicas;
	
	/*
	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="equipamento_fk")
	private Equipamento equipamento;	
	*/

	/*
	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="insumos_fk")
	private Insumos insumos;	
	*/
	
	/*
	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="instumentos_fk")
	private Instrumentos instrumentos;	
	*/
	
	
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public String getRevisado() {
		return revisado;
	}

	public void setRevisado(String revisado) {
		this.revisado = revisado;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
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
		Area other = (Area) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
