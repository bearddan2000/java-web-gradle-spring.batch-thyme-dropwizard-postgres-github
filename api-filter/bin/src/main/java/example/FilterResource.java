package example;

import com.codahale.metrics.annotation.Timed;

// import for hibernate
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// importa for basic auth
import io.dropwizard.auth.Auth;
import javax.annotation.security.PermitAll;

import java.util.List;

@Path("/filter")
@Produces(MediaType.APPLICATION_JSON)
public class FilterResource {

  FilterDao dao;

  final int BUILD = 1;
  final int LANG = 2;
  final int PLATFORM = 3;
  final int TECH = 4;

  public FilterResource(FilterDao dao){ this.dao = dao;}

  @PermitAll
  @Path("/lang")
  @GET
  @Timed
  @UnitOfWork
  public List<Filter> getLang(@Auth User user) {
      return dao.findByType(LANG);
  }

  @PermitAll
  @Path("/platform")
  @GET
  @Timed
  @UnitOfWork
  public List<Filter> getPlatform(@Auth User user) {
      return dao.findByType(PLATFORM);
  }

  @PermitAll
  @Path("/build")
  @GET
  @Timed
  @UnitOfWork
  public List<Filter> getBuild(@Auth User user) {
      return dao.findByType(BUILD);
  }

  @PermitAll
  @Path("/tech")
  @GET
  @Timed
  @UnitOfWork
  public List<Filter> getTech(@Auth User user) {
      return dao.findByType(TECH);
  }
}
