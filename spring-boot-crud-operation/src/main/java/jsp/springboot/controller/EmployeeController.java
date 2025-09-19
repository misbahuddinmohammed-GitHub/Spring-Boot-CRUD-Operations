package jsp.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Employee;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordsFoundException;
import jsp.springboot.repository.EmployeeRepository;
import jsp.springboot.service.EmployeeService;

@RestController
public class EmployeeController {
	
//	@Autowired
//	private EmployeeRepository repository;
	
	@Autowired
	private EmployeeService employeeService;
	
	
//	insert a record
	@PostMapping("/employee")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee)
	{
//		repository.save(employee);
//		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//		structure.setStatusCode(HttpStatus.CREATED.value());
//		structure.setMessage("Employee record saved");
//		structure.setData(repository.save(employee));
		return employeeService.saveEmployee(employee);
	}
	
//	fetch all record
	@GetMapping("/employee")
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployee()
	{
////		List<Employee> employees = repository.findAll();
////		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
////		
////		if(!employees.isEmpty()) {
////			structure.setStatusCode(HttpStatus.OK.value());
////			structure.setMessage("Employee record retrieved");
////			structure.setData(employees);
//			return employeeService.getAllEmployee();
//		}
////		else {
////			structure.setStatusCode(HttpStatus.NO_CONTENT.value());
////			structure.setMessage("No records available");
////			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.OK);
//			throw new NoRecordsFoundException("Employee Data is Not Available");
//		}
		return employeeService.getAllEmployee();
	}
	
//	fetch record by id
	@GetMapping("/employee/{id}")
	public ResponseEntity<ResponseStructure<Employee>> getEmployeeById(@PathVariable Integer id)
	{
//		Optional<Employee> opt = repository.findById(id);
//		
//		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//		if(opt.isPresent()) // checks whether the optional is empty or not
//		{
//			structure.setStatusCode(HttpStatus.FOUND.value());
//			structure.setMessage("Employee record retrieved");
//			structure.setData(opt.get()); // helps in retrieving data from optional
//			return structure;
//		}
//		else
//		{
////			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
////			throw new IdNotFoundException("Id Not Available In Database");
////			structure.setMessage("Id not available in  DB");
////			return structure;
//		}
		return employeeService.getEmployeeById(id);
	}
	
//	update a record
	@PutMapping("/employee")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee)
	{
//		repository.save(employee);
		return employeeService.updateEmployee(employee);
	}
	
//	delete a record
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeById(@PathVariable Integer id)
	{
//		Optional<Employee> opt = repository.findById(id);
//		if(opt.isPresent())
//		{
//			repository.delete(opt.get());
//			return "Record Deleted";
//		}
//		else
//			return "Id is not available in DB";
		
		return employeeService.deleteEmployeeById(id);
	}
	
	
	// Using Custom Methods for Fetching and By using Query
	
		@GetMapping("/employee/name/{name}")
		public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeByName(@PathVariable String name)
		{
//			List<Employee> employees = repository.findByName(name);
//			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
//			
//			if(!employees.isEmpty()) {
//				structure.setStatusCode(HttpStatus.OK.value());
//				structure.setMessage("Employee record retieved");
//				structure.setData(employees);
//				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.OK);
//			}
//			else {
//				throw new NoRecordsFoundException("Employee Data is Not Available");
//			}
			
			return employeeService.fetchEmployeeByName(name);
		}
		
//		fetch?designation=value&role=value2
		@GetMapping("/employee/fetch")
		public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeByDesignationAndRole(@RequestParam String desgination,@RequestParam String role)
		{
//			List<Employee> employees = repository.findByDesignationAndRole(desgination, role);
//			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
//			
//			if(!employees.isEmpty()) {
//				structure.setStatusCode(HttpStatus.FOUND.value());
//				structure.setMessage("Employee record retrieved");
//				structure.setData(employees);
//				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
//			}
//			else {
//				throw new IdNotFoundException("Employee Data is Not Available");
//			}
			
			return employeeService.fetchEmployeeByDesignationAndRole(desgination, role);
			
		}
		
		@GetMapping("/employee/contact/{contact}")
		public ResponseEntity<ResponseStructure<Employee>> findEmployeeByContact(@PathVariable Long contact)
		{
//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			Optional<Employee> opt=repository.findByPhone(contact);
//			if(opt.isPresent())
//			{
//				structure.setStatusCode(HttpStatus.OK.value());
//				structure.setMessage("Employee Record Found");
//				structure.setData(opt.get());
//				return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
//			}
//			else
//			{
//				throw new IdNotFoundException("Record Is Not Found In Database");
//			}
			
			return employeeService.findEmployeeByPhone(contact);
		}
		
		@GetMapping("/employee/salary/{salary}")
		public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeSalaryGreaterThan(@PathVariable Double salary)
		{
//			List<Employee> employees = repository.findBySalaryGreaterThan(salary);
//			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
//			
//			if(!employees.isEmpty()) {
//				structure.setStatusCode(HttpStatus.FOUND.value());
//				structure.setMessage("Employee record retieved");
//				structure.setData(employees);
//				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
//			}
//			else {
//				throw new IdNotFoundException("Employee Data is Not Available In Database");
//			}
			
			return employeeService.fetchEmployeeSalaryGreaterThan(salary);
		}
		
		
		@GetMapping("/employee/salary/{salary}")
		public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeSalaryLessThan(@RequestParam Double salary)
		{
//			List<Employee> employees = repository.findBySalaryLessThan(salary);
//			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
//			
//			if(!employees.isEmpty()) {
//				structure.setStatusCode(HttpStatus.FOUND.value());
//				structure.setMessage("Employee record retieved");
//				structure.setData(employees);
//				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
//			}
//			else {
//				throw new IdNotFoundException("Employee Data is Not Available In Database");
//			}
			
			return employeeService.fetchEmployeeSalaryLessThan(salary);
		}
		
		@GetMapping("/employee/salary")
		public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeBySalary()
		{
//			List<Employee> employees = repository.getEmployeeBySalary();
//			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
//			
//			if(!employees.isEmpty()) {
//				structure.setStatusCode(HttpStatus.FOUND.value());
//				structure.setMessage("Employee record retieved");
//				structure.setData(employees);
//				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
//			}
//			else {
//				throw new IdNotFoundException("Employee Data is Not Available In Database");
//			}
			
			return employeeService.fetchEmployeeBySalary();
		}
		
		@GetMapping("/employee/role/{role}")
		public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeByRole(@PathVariable String role)
		{
//			List<Employee> employees = repository.getEmployeeByRole(role);
//			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
//			
//			if(!employees.isEmpty()) {
//				structure.setStatusCode(HttpStatus.FOUND.value());
//				structure.setMessage("Employee record retieved");
//				structure.setData(employees);
//				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
//			}
//			else {
//				throw new IdNotFoundException("Employee Data is Not Available In Database");
//			}
			
			return employeeService.fetchEmployeeByRole(role);
		}
		
		
		@GetMapping("/employee/salary/{salary}/desgination/{desgination}")
		public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeSalaryAndDesignation(@PathVariable Double salary,@PathVariable String desgination)
		{
//			List<Employee> employees = repository.getEmployeeBySalaryOrDesignation(salary,desgination);
//			ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
//			
//			if(!employees.isEmpty()) {
//				structure.setStatusCode(HttpStatus.FOUND.value());
//				structure.setMessage("Employee record retieved");
//				structure.setData(employees);
//				return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
//			}
//			else {
//				throw new IdNotFoundException("Employee Data is Not Available In Database");
//			}
			
			return employeeService.fetchEmployeeSalaryAndDesignation(salary, desgination);
		}
		
		@GetMapping("/employee/page/{pageNumber}/{pageSize}")
		public ResponseEntity<ResponseStructure<Page<Employee>>> getEmployeeByPagination(@PathVariable Integer pageNumber, @PathVariable Integer pageSize)
		{
			return employeeService.getEmployeeByPagination(pageNumber, pageSize);
		}
		
		@GetMapping("/employee/sort/{field}")
		public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeBySorting(@PathVariable String field)
		{
			return employeeService.getEmployeeBySorting(field);
		}
		
		@GetMapping("/employee/page/{pageNumber}/{pageSize}{field}")
		public ResponseEntity<ResponseStructure<Page<Employee>>> getEmployeeByPaginationAndSorting(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field)
		{
			return employeeService.getEmployeeByPaginationAndSorting(pageNumber, pageSize, field);
		}
}


