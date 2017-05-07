package source.codes.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import source.codes.dao.AbstractDAO;
import source.codes.dao.EmployeeDAO;
import source.codes.model.Employee;

@Repository
public class EmployeeDAOImpl extends AbstractDAO<Employee> implements EmployeeDAO {

  @Override
  public void saveEmployee(Employee employee) {
    persist(employee);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Employee> findAllEmployees() {
    Criteria criteria = getSession().createCriteria(Employee.class);
    return (List<Employee>) criteria.list();
  }

  @Override
  public void deleteEmployee(Employee employee) {
    delete(employee);
  }

  @Override
  public void updateEmployee(Employee employee) {
    update(employee);
  }
  
  @Override
  public Employee findEmployeeById(Long id) {
    return findById(id);
  }

  @Override
  public Employee findBySSN(String ssn) {
    Query query = getSession().createQuery("from Employee where ssn = ? ");
    query.setParameter(0, ssn);
    return (Employee) query.uniqueResult();
  }

}
