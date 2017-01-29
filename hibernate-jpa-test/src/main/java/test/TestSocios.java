package test;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ar.omar.hibernate.modelo.Socio;

public class TestSocios {
	
	//@PersistenceContext(unitName ="Persistencia")
	private static EntityManager em;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");;
	
	private static void insertInicial() {
		Socio s = new Socio("Perez", "Pepito", 31012550L, new GregorianCalendar(1979, 6, 6).getTime());
		Socio s2 = new Socio("Martinez", "Jose", 31012550L, new GregorianCalendar(1984, 10, 10).getTime());
		
		
		em.getTransaction().begin();
		em.persist(s);
		em.persist(s2);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	private static void imprimirTodo(){
		List<Socio> socios = (List<Socio>) em.createQuery("from Socio").getResultList();
		System.out.println("En esta db hay "+socios.size()+ " socios.");
		
		for (Socio soc : socios) {
			System.out.println(soc.toString());
		}
	}//x
	public static void main(String[] args) {
		em = emf.createEntityManager();
		insertInicial();
		
		em.getTransaction().begin();
		Socio e = em.find(Socio.class, 10);
		e.setNombre("David");
		e.setApellidos("lopez");
		
		em.getTransaction().commit();
		
		imprimirTodo();
		em.close();
		
	}
}
