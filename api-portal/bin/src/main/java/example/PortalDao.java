package example;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.*;

import java.util.List;

public class PortalDao extends AbstractDAO<Portal> {
  public PortalDao(SessionFactory factory) {
    super(factory);
  }

  public List<Portal> findAll() {
    return list(namedQuery("findAll"));
  }
}
