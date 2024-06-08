package example;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.*;

import java.util.List;

public class CatagoryDao extends AbstractDAO<Catagory> {
  public CatagoryDao(SessionFactory factory) {
    super(factory);
  }

  public List<Catagory> findAll() {
    return list(namedQuery("findAll"));
  }
}
