package pe.edu.cibertec.proyemp.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.edu.cibertec.proyemp.jpa.domain.Departamento;
import pe.edu.cibertec.proyemp.jpa.domain.Empleado;

public class JpaTest {
	
	private EntityManager manager;
	
	public JpaTest(EntityManager manager){
		this.manager = manager;
	}
	public static void main(String[]args){
		
		//Patron factory para obtener el EntityManagerFactory
		EntityManagerFactory factory
		=Persistence.createEntityManagerFactory("persistenceUnit");
		
		//Se extrae el EntityManager del factory
		EntityManager em = factory.createEntityManager();
		
		//Inyeccion de dependencias
		JpaTest test = new JpaTest(em);
		
		//Se obtiene el objeto EntityTransaction para definir la transaccion
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
			// inserts, updates y deletes
		test.crearEmpleados();
		test.crearEmpleadosEnCascada();
		tx.commit();
		
			//test.listarEmpleados();
			//test.listarEmpleadosPorDepartamentoId(new Long (1));
			//test.listarEmpleadosPorDepartamentoId("java");
			//test.obtenerEmpleadoxId(new Long(1));
		
		//---Querys Nombrados
		test.listarEmpleadosNQ();
	}
	
	private void listarEmpleadosNQ() {
		
		List<Empleado> empleados = manager.createNamedQuery(
				"Empleado.getAll",Empleado.class).getResultList();
		
		for (Empleado empleado : empleados){
			System.out.println(empleado);
		}
		
	}
	private void obtenerEmpleadoxId(Long empId) {
		
		// 1era forma
		//Empleado empleado = manager.createQuery(
			//	"from Empleado where id = ?",
				//Empleado.class)
				//.setParameter(1, empId)
				//.getSingleResult();
		
		// 2da forma
		Empleado empleado = manager.find(Empleado.class, empId);
		
		
		System.out.println(empleado);
	}
	private void listarEmpleadosPorDepartamentoId(String nombreDep) {
		
		List<Empleado> empleados = 
				manager.createQuery(
						"select e from Empleado e "
						+ "where e.departamento.nombre = :dep",
						Empleado.class)
						.setParameter("dep", nombreDep)
						.getResultList();
		
		manager.remove(empleados.get(0));
		
		for (Empleado emp: empleados){		
			System.out.println(emp);
		}
		
	
		
	}
	private void listarEmpleadosPorDepartamentoId(Long id) {

		List<Empleado> empleados = 
				manager.createQuery(
						"select e from Empleado e where departamento.id = ?",
						Empleado.class)
						.setParameter(1, id)
						.getResultList();
		
		for (Empleado emp: empleados){
			//System.out.println("Empleado: " +emp.getId()
				//							+ " , " + emp.getNombre());
			
			System.out.println(emp);
		}
		
	}
	
	private void listarEmpleados() {
		
		String jql= "select e from Empleado e";
		String jql2 = "from Empleado";
		
		List<Empleado> empleados = 
				manager.createQuery(jql2,
						Empleado.class).getResultList();
		
		for (Empleado emp: empleados){
			//System.out.println("Empleado: " +emp.getId()
				//							+ " , " + emp.getNombre());
			
			System.out.println(emp);
		}
		
	}
	private void crearEmpleadosEnCascada() {
		
		Departamento java = new Departamento("java");
		
		Empleado empl = new Empleado("Bob",java);
		Empleado emp2 = new Empleado("Maria",java);
		
		List<Empleado> empleados = Arrays.asList(empl,emp2);
		
		java.setEmpleados(empleados);
		
		manager.persist(java);
		
		
	}
	private void crearEmpleados(){
		
		Departamento java = new Departamento("java");
		manager.persist(java);
		
		Departamento java2 = new Departamento(".net");
		manager.persist(java2);
		
		Empleado empl = new Empleado("Bob",java);
		manager.persist(empl);
		
		Empleado emp2 = new Empleado("Juan",java);
		manager.persist(emp2);
		
		Empleado emp3 = new Empleado("Mike",java);
		manager.persist(emp3);
		
		Empleado emp4 = new Empleado("Mario",java);
		manager.persist(emp4);
		
		empl.setNombre("Cesar Evangelista");
		
		manager.remove(emp2);
	}
	

}
