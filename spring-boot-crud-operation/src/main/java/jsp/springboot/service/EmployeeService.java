package jsp.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;

import jsp.springboot.dao.EmployeeDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Employee;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordsFoundException;
//import jsp.springboot.repository.EmployeeRepository;
import org.springframework.data.domain.Page;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee)
	{
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Employee record saved");
		structure.setData(employeeDao.saveEmployee(employee));
		return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployee()
	{
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		List<Employee> employees = employeeDao.getAllEmployee();
		
		if(!employees.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Employee record retieved");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.OK);
		}
		else {
			throw new NoRecordsFoundException("Employee Data is Not Available");
		}
	}
	
	public ResponseEntity<ResponseStructure<Employee>> getEmployeeById(Integer id)
	{
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		Optional<Employee> opt = employeeDao.getEmployeeById(id);
		
		if(opt.isPresent()) // checks whether the optional is empty or not
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record retrieved");
			structure.setData(opt.get()); // helps in retrieving data from optional
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Id Not Available In Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Employee employee)
	{
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Employee record updated");
		structure.setData(employeeDao.updateEmployee(employee));
		return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeById(Integer id)
	{
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		Optional<Employee> opt = employeeDao.getEmployeeById(id);
		if(opt.isPresent())
		{
			employeeDao.deleteEmployeeById(opt.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Employee record deleted");
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFoundException("Id Is Not Found In The Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeByName(String name)
	{
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		List<Employee> employees = employeeDao.fetchEmployeeByName(name);
		if(!employees.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record deleted");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Record Is Not Found In The Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeByDesignationAndRole(String desgination,String role)
	{
		List<Employee> employees = employeeDao.fetchEmployeeByDesignationAndRole(desgination, role);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		
		if(!employees.isEmpty()) {
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record retieved");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new IdNotFoundException("Employee Data is Not Available");
		}
	}
	
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeByPhone(Long contact)
	{
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		Optional<Employee> opt=employeeDao.fetchEmployeeByPhone(contact);
		if(opt.isPresent())
		{
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Employee Record Found");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFoundException("Record Is Not Found In Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeSalaryGreaterThan(Double salary)
	{
		List<Employee> employees = employeeDao.fetchEmployeeBySalaryGreaterThan(salary);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		
		if(!employees.isEmpty()) {
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record retieved");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new IdNotFoundException("Employee Data is Not Available In Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeSalaryLessThan(Double salary)
	{
		List<Employee> employees = employeeDao.fetchEmployeeBySalaryLessThan(salary);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		
		if(!employees.isEmpty()) {
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record retieved");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new IdNotFoundException("Employee Data is Not Available In Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeBySalary()
	{
		List<Employee> employees = employeeDao.fetchEmployeeBySalary();
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		
		if(!employees.isEmpty()) {
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record retieved");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new IdNotFoundException("Employee Data is Not Available In Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeByRole(String role)
	{
		List<Employee> employees = employeeDao.fetchEmployeeByRole(role);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		
		if(!employees.isEmpty()) {
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record retieved");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new IdNotFoundException("Employee Data is Not Available In Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeSalaryAndDesignation(Double salary,String desgination)
	{
		List<Employee> employees = employeeDao.fetchEmployeeBySalaryAndDesignation(salary,desgination);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		
		if(!employees.isEmpty()) {
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record retieved");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new IdNotFoundException("Employee Data is Not Available In Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<Page<Employee>>> getEmployeeByPagination(Integer pageNumber,Integer pageSize)
	{
		Page<Employee> pages = employeeDao.getEmployeeByPagination(pageNumber, pageSize);
		ResponseStructure<Page<Employee>> structure = new ResponseStructure<Page<Employee>>();
		if(!pages.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record retieved");
			structure.setData(pages);
			return new ResponseEntity<ResponseStructure<Page<Employee>>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Record Is Not Found In Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeBySorting(String field)
	{
		List<Employee> employees = employeeDao.getEmployeeBySorting(field);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if(!employees.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record retieved");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Record Is Not Found In Database");
		}
	}
	
	public ResponseEntity<ResponseStructure<Page<Employee>>> getEmployeeByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field)
	{
		Page<Employee> pages = employeeDao.getEmployeeByPaginationAndSorting(pageNumber, pageSize, field);
		ResponseStructure<Page<Employee>> structure = new ResponseStructure<Page<Employee>>();
		if(!pages.isEmpty())
		{
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Employee record retieved");
			structure.setData(pages);
			return new ResponseEntity<ResponseStructure<Page<Employee>>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Record Is Not Found In Database");
		}
	}
}
