package source.codes.service;

import java.util.List;

import source.codes.model.Employee;

public interface EmployeeService {
  
  public void saveEmployee(Employee employee);
  public List<Employee> findAllEmployee();
  public void deleteEmployee(Employee employee);
  public Employee findEmployeeById(Long id);
  public void updateEmployee(Employee employee);
  public boolean isEmployeeSSNUnique(Long id, String ssn);
  public Employee findEmployeeBySsn(String ssn);
  public void deleteEmployeeBySSN(String ssn);
  
}
