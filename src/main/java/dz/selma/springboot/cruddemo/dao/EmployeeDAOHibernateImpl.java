package dz.selma.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dz.selma.springboot.cruddemo.entity.Employee;

@SuppressWarnings("deprecation")
@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define field for entitymanager
	private EntityManager entityManager;

	// se up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);

		// execute query and get result list
		List<Employee> employees = query.getResultList();

		// return the results

		return employees;
	}

	@Override
	public Employee findById(int id) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Employee employee = currentSession.get(Employee.class, id);

		// return the results

		return employee;
	}

	@Override
	public void save(Employee employee) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int id) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query query = currentSession.createQuery("delete from Employee e where e.id = :employeeId");//, Employee.class);

		query.setParameter("employeeId", id);

		// execute query
		query.executeUpdate();
	}

}
