package example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

// imports for basic auth
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class Main extends Application<DbConfiguration> {
    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public String getName() {
        return "dev";
    }

    @Override
    public void initialize(Bootstrap<DbConfiguration> bootstrap) {
        // nothing to do yet
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(DbConfiguration configuration,
                    Environment e) {
        //****** Dropwizard security - custom classes ***********/
        e.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                    .setAuthenticator(new AppBasicAuthenticator())
                    .setAuthorizer(new AppAuthorizer())
                    .setRealm("BASIC-AUTH-REALM")
                    .buildAuthFilter()));
        e.jersey().register(RolesAllowedDynamicFeature.class);
        e.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        
        // nothing to do yet
        FilterDao dao = new FilterDao(hibernate.getSessionFactory());
        final FilterResource resource = new FilterResource(dao);
        e.jersey().register(resource);
    }

    private HibernateBundle<DbConfiguration> hibernate = new HibernateBundle<DbConfiguration>(Filter.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DbConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
}
