package jsp.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.springboot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

//	fetch employee by name
	List<Employee> findByName(String name);
	
	List<Employee> findBySalaryGreaterThan(Double salary);
	
	List<Employee> findBySalaryLessThan(Double salary);
	
	List<Employee> findByDesignationAndRole(String desgination, String role);
	
	Optional<Employee> findByPhone(Long contact);
	
	@Query("select e from Employee e where e.salary=50000.00")
	List<Employee> getEmployeeBySalary();
	
	@Query("select e from Employee where e.role=?1")
	List<Employee> getEmployeeByRole(String role);
	
	@Query("select e from Employee e where e.salary=:salary or e.desgination=:desgination")
	List<Employee> getEmployeeBySalaryOrDesignation(Double salary,String desgination);
}
