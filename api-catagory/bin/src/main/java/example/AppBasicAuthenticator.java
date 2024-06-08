package example;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class AppBasicAuthenticator implements Authenticator<BasicCredentials, User>
{
    private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of(
       // "guest", ImmutableSet.of(),
        System.getenv("BASIC_AUTH_USER"), ImmutableSet.of("USER"),
        System.getenv("BASIC_AUTH_ADMIN"), ImmutableSet.of("ADMIN", "USER")
    );

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        final String BASIC_USER = System.getenv("BASIC_AUTH_USER");
        final String BASIC_USER_PASSWORD = System.getenv("BASIC_AUTH_USER_PASSWORD");

        final String BASIC_ADMIN = System.getenv("BASIC_AUTH_ADMIN");
        final String BASIC_ADMIN_PASSWORD = System.getenv("BASIC_AUTH_ADMIN_PASSWORD");

        if(credentials.getUsername().equals(BASIC_USER) 
            && credentials.getPassword().equals(BASIC_USER_PASSWORD))
        {
            return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
        }

        if(credentials.getUsername().equals(BASIC_ADMIN) 
            && credentials.getPassword().equals(BASIC_ADMIN_PASSWORD))
        {
            return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
        }

/*
        if (VALID_USERS.containsKey(credentials.getUsername()) && "password".equals(credentials.getPassword()))
        {
            return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
        }
*/
        return Optional.empty();
    }
}
