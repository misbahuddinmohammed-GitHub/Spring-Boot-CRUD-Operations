package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jsp.springboot.entity.Employee;
import jsp.springboot.repository.EmployeeRepository;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployee()
	{
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getEmployeeById(Integer id)
	{
		return employeeRepository.findById(id);
	}
	
	@PutMapping("/employee")
	public Employee updateEmployee(Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployeeById(Employee employee)
	{
		employeeRepository.delete(employee);
	}
	
	public List<Employee> fetchEmployeeByName(String name)
	{
		return employeeRepository.findByName(name);
	}
	
	public List<Employee> fetchEmployeeByDesignationAndRole(String desgination, String role)
	{
		return employeeRepository.findByDesignationAndRole(desgination, role);
	}
	
	public Optional<Employee> fetchEmployeeByPhone(Long contact)
	{
		return employeeRepository.findByPhone(contact);
	}
	
	public List<Employee> fetchEmployeeBySalaryGreaterThan(Double salary)
	{
		return employeeRepository.findBySalaryGreaterThan(salary);
	}
	
	public List<Employee> fetchEmployeeBySalaryLessThan(Double salary)
	{
		return employeeRepository.findBySalaryLessThan(salary);
	}
	
	public List<Employee> fetchEmployeeBySalary()
	{
		return employeeRepository.getEmployeeBySalary();
	}
	
	public List<Employee> fetchEmployeeByRole(String role)
	{
		return employeeRepository.getEmployeeByRole(role);
	}
	
	public List<Employee> fetchEmployeeBySalaryAndDesignation(Double salary, String desgination)
	{
		return employeeRepository.getEmployeeBySalaryOrDesignation(salary, desgination);
	}
	
	public Page<Employee> getEmployeeByPagination(int pageNumber,int pageSize)
	{
		return employeeRepository.findAll(PageRequest.of(pageNumber, pageSize));
	}
	
	public List<Employee> getEmployeeBySorting(String field)
	{
		return employeeRepository.findAll(Sort.by(field).ascending());
	}
	
	public Page<Employee> getEmployeeByPaginationAndSorting(int pageNumber, int pageSize, String field)
	{
		return employeeRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
	}
}
