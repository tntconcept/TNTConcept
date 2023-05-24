package com.autentia.tnt.test.utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.junit.BeforeClass;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.MountableFile;

import java.util.List;

public class TestContainer {

    static final public MySQLContainer<?> mysql = new MySQLContainer<>(
            "mysql:8.0.32")
            .withDatabaseName("tntconcept")
            .withUsername("tntconcept")
            .withPassword("tntconcept")
            .withCopyFileToContainer(MountableFile.forClasspathResource("testcontainers/mysql/extra.cnf"), "/etc/mysql/conf.d/extra.cnf")
            .withExposedPorts(3306)
            .waitingFor(Wait.forHttp("/").forPort(3306));

    @BeforeClass
    public static void initDB() {
        mysql.setPortBindings(List.of("50400:3306"));
        mysql.start();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(mysql.getJdbcUrl());
        dataSource.setUser(mysql.getUsername());
        dataSource.setPassword(mysql.getPassword());
        // migrate the database
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
    }
}
