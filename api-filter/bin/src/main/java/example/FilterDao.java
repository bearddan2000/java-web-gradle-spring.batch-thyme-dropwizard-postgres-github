package example;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.*;

import java.util.List;

public class FilterDao extends AbstractDAO<Filter> {
  public FilterDao(SessionFactory factory) {
    super(factory);
  }

  public List<Filter> findByType(int filter) {
    return list(namedQuery("findByType").setInteger("filterType", filter));
  }
}
