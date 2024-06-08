package example;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.*;

import java.util.List;

public class KeywordsDao extends AbstractDAO<Keywords> {
  public KeywordsDao(SessionFactory factory) {
    super(factory);
  }

  public List<Keywords> findAll() {
    return list(namedQuery("findAll"));
  }
}
