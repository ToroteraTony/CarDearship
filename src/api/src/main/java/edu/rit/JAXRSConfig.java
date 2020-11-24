package edu.rit;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class JAXRSConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
            Set<Class<?>> h = new HashSet<>();
            h.add(CompanyService.class);
            return h;

    }
}
