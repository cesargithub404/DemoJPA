package pe.edu.cibertec.proyemp.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_ESTACIONAMIENTO")
public class Estacionamiento {
	
	@Id
	@GeneratedValue
	@Column(name="EST_ID")
	private Long id;
	
	@Column(name="EST_NUM_LOTE")
	private Integer numLote;
	
	@Column(name="EST_UBICACION")
	private String ubicacion;
	
	public Long getId(){
		return id;
	}


}
