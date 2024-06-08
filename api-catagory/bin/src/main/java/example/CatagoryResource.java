package example;

import com.codahale.metrics.annotation.Timed;

// import for database
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// importa for basic auth
import io.dropwizard.auth.Auth;
import javax.annotation.security.PermitAll;

import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CatagoryResource {

  CatagoryDao dao;

  public CatagoryResource(CatagoryDao dao){ this.dao = dao;}

  @PermitAll
  @Path("/catagory")
  @GET
  @Timed
  @UnitOfWork
  public List<Catagory> getCatagory(@Auth User user) {
      return dao.findAll();
  }
}
