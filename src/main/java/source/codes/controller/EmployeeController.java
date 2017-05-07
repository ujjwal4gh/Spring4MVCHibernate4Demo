package source.codes.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import source.codes.model.Employee;
import source.codes.service.EmployeeService;

@Controller
public class EmployeeController {
  
  @Autowired
  private EmployeeService employeeService;
  
  @Autowired
  private MessageSource messageSource;
  
  @RequestMapping(value = {"/","/list"}, method = RequestMethod.GET)
  public String listEmployees(ModelMap model) {
    List<Employee> employees = employeeService.findAllEmployee();
    model.addAttribute("employees", employees);
    return "allemployees";
  }
  
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String newEmployee(ModelMap model) {
    Employee employee = new Employee();
    model.addAttribute("employee", employee);
    model.addAttribute("edit", false);
    return "registration";
  }
  
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String saveEmployee(@Valid Employee employee, BindingResult result, ModelMap model) {
    if(result.hasErrors()) {
      return "registration";
    }
    
    if(!employeeService.isEmployeeSSNUnique(employee.getId(), employee.getSsn())) {
      FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
      result.addError(ssnError);
      return "registration";
    }
    
    employeeService.saveEmployee(employee);
    
    model.addAttribute("success", "Employee " + employee.getName() + "registered successfully");
    return "success";
  }
  
  @RequestMapping(value = "/edit-{ssn}-employee", method = RequestMethod.GET)
  public String editEmployee(@PathVariable String ssn, ModelMap model) {
    Employee employee = employeeService.findEmployeeBySsn(ssn);
    model.addAttribute("employee", employee);
    model.addAttribute("edit", true);
    return "registration";
  }
  
  @RequestMapping(value = "/edit-{ssn}-employee", method = RequestMethod.POST)
  public String updateEmployee(@Valid Employee employee, BindingResult result, ModelMap model, @PathVariable String ssn) {
    if(result.hasErrors()) {
      return "registration";
    }
    
    if(!employeeService.isEmployeeSSNUnique(employee.getId(), employee.getSsn())) {
      FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
      result.addError(ssnError);
      return "registration";
    }
    
    employeeService.updateEmployee(employee);
    
    model.addAttribute("success", "Employee " + employee.getName() + " updated successfully");
    return "success";
  }
  
  @RequestMapping(value = "/delete-{ssn}-employee", method = RequestMethod.GET)
  public String deleteEmployee(@PathVariable String ssn) {
    employeeService.deleteEmployeeBySSN(ssn);
    return "redirect:/list";
  }
  
}
