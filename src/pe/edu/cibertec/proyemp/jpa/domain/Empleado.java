package pe.edu.cibertec.proyemp.jpa.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.edu.cibertec.proyemp.jpa.listener.EmpleadoListener;

@NamedQueries(
		{
			@NamedQuery(name="Empleado.getAll", 
			query="select e from Empleado e"),
			@NamedQuery(name="Empleado.getPorDepNombre",
			query="select e from Empleado e where e.departamento.nombre =:dep")
		}
		)

@EntityListeners(EmpleadoListener.class)
@Entity
public class Empleado  {
	
	@Id
	@GeneratedValue
	@Column(name="EMP_ID", nullable=false, unique=true)
	private Long id;
	
	@Column(name="EMP_NOMBRE", nullable=false, length=200)
	private String nombre;
	
	@Column(name="EMP_APELLIDO", nullable=false, length=50)
	private String empapellido;
	

	
	@ManyToOne
	@JoinColumn(name="DEP_ID")
	private Departamento departamento;
	
	
	@Column(name="EMP_SALARIO", precision=10, scale=1)
	private BigDecimal salario;
	
	@Column(name="EMP_FEC_ING")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@Column(name="EMP_DNI", columnDefinition="CHAR(8)")
	private String dni;
	
	@OneToOne
	@JoinColumn(name="EST_ID")
	private Estacionamiento estacionamiento;
	
	
	
	public Empleado(){}
	
	public Empleado(String nombre, Departamento departamento) {
		super();
		this.nombre = nombre;
		this.departamento = departamento;
	}
	
	public Empleado(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + "]";
	}

	public String getEmpapellido() {
		return empapellido;
	}

	public void setEmpapellido(String empapellido) {
		this.empapellido = empapellido;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Estacionamiento getEstacionamiento() {
		return estacionamiento;
	}

	public void setEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamiento = estacionamiento;
	}
	
	
	

}
