package source.codes.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

public class AbstractDAO<T> {
  
  @Autowired
  private SessionFactory sessionFactory;
  
  private final Class<T> genericType;
  
  @SuppressWarnings("unchecked")
  public AbstractDAO() {
    this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractDAO.class);
  }

  public void persist(T entity) {
    getSession().persist(entity);
  }
  
  public void delete(T entity) {
    getSession().delete(entity);
  }
  
  @SuppressWarnings("unchecked")
  public T findById(Long id) {
    return (T) getSession().get(genericType, id);
  }
  
  public void update(T entity) {
    getSession().update(entity);
  }
  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}
