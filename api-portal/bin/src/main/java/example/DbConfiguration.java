package example;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

/**
 * Created by harshvardhan on 12/07/18.
 */
public class DbConfiguration extends Configuration {

    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        final String DB_USER = System.getenv("DB_USER");
        final String DB_PASSWORD = System.getenv("DB_PASSWORD");
        database.setUser(DB_USER);
        database.setPassword(DB_PASSWORD);
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }

}
