package example;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class KeywordsResource {

  KeywordsDao dao;

  public KeywordsResource(KeywordsDao dao){ this.dao = dao;}

  @Path("/keywords")
  @GET
  @Timed
  @UnitOfWork
  public List<Keywords> getKeyword() {
      return dao.findAll();
  }
}
