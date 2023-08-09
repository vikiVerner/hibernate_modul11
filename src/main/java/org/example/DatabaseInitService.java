package org.example;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {
    public void createTable(String connectionUrl)  {


        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        flyway.migrate();
    }
}
