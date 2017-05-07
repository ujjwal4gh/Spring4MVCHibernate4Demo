package source.codes.dao;

import java.util.List;

import source.codes.model.Employee;

public interface EmployeeDAO {
  
  public void saveEmployee(Employee employee);
  public List<Employee> findAllEmployees();
  public Employee findEmployeeById(Long id);
  public Employee findBySSN(String ssn);
  public void deleteEmployee(Employee employee);
  public void updateEmployee(Employee employee);
}
