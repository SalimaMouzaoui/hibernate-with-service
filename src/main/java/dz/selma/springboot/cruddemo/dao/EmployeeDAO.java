package dz.selma.springboot.cruddemo.dao;

import java.util.List;

import dz.selma.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	
	public Employee findById(int id);

	public void save(Employee employee);

	public void deleteById(int id);
}
