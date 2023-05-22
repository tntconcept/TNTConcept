package com.autentia.tnt.test.utils;

import org.flywaydb.core.Flyway;
import org.junit.BeforeClass;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class TestContainer {
    protected static final MySQLContainer<?> mysql = new MySQLContainer<>(
            "mysql:8.0.32")
            .withDatabaseName("tntconcept")
            .withPrivilegedMode(true)
            .withExposedPorts(3306)
            .waitingFor(Wait.forHttp("/").forPort(3306));



    @BeforeClass
    public static void initDB() {
        mysql.start();
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:tc:mysql:8.0.32:///tntconcept?useSSL=false&serverTimezone=UTC&TC_MY_CNF=testcontainers/mysql", "tntconcept", "tntconcept").load();
        flyway.migrate();
    }
}
