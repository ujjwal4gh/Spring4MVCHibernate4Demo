package source.codes.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Size(min = 3, max = 50)
  @Column(name = "name", nullable = false)
  private String name;
  
  @NotEmpty
  @Column(name = "ssn", unique = true, nullable = false)
  private String ssn;
  
  @NotNull
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "joining_date", nullable = false)
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
  private LocalDate joiningDate;
  
  @NotNull
  @Digits(integer = 8, fraction = 2)
  @Column(name = "salary", nullable = false)
  private BigDecimal salary;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public LocalDate getJoiningDate() {
    return joiningDate;
  }

  public void setJoiningDate(LocalDate joiningDate) {
    this.joiningDate = joiningDate;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (null != id ? id.intValue() : 0);
    result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof Employee))
      return false;
    Employee other = (Employee) obj;
    if (id != other.id)
      return false;
    if (ssn == null) {
      if (other.ssn != null)
          return false;
    } else if (!ssn.equals(other.ssn))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + ", joiningDate="
        + joiningDate + ", salary=" + salary + ", ssn=" + ssn + "]";
  }
  
  
  
}