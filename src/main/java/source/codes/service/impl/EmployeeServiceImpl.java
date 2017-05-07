package source.codes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import source.codes.dao.EmployeeDAO;
import source.codes.model.Employee;
import source.codes.service.EmployeeService;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

  @Autowired
  @Qualifier("employeeDAOImpl")
  private EmployeeDAO employeeDAO;
  
  @Override
  public void saveEmployee(Employee employee) {
    employeeDAO.saveEmployee(employee);
  }

  @Override
  public List<Employee> findAllEmployee() {
    return employeeDAO.findAllEmployees();
  }

  @Override
  public void deleteEmployee(Employee employee) {
    employeeDAO.deleteEmployee(employee);
  }

  @Override
  public Employee findEmployeeById(Long id) {
    return employeeDAO.findEmployeeById(id);
  }

  @Override
  public void updateEmployee(Employee employee) {
    employeeDAO.updateEmployee(employee);
  }

  @Override
  public boolean isEmployeeSSNUnique(Long id, String ssn) {
    Employee employee = findEmployeeBySsn(ssn);
    return (employee == null || ((id != null) && (employee.getId().equals(id))));
  }

  @Override
  public Employee findEmployeeBySsn(String ssn) {
    return employeeDAO.findBySSN(ssn);
  }

  @Override
  public void deleteEmployeeBySSN(String ssn) {
    Employee employee = findEmployeeBySsn(ssn);
    if(null != employee) {
      employeeDAO.deleteEmployee(employee);
    }
  }

}
