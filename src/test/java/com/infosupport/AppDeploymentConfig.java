package com.infosupport;

import org.microshed.testing.SharedContainerConfiguration;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class AppDeploymentConfig implements SharedContainerConfiguration {

    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.30")
            .withEnv("MYSQL_ROOT_PASSWORD", "root")
            .withDatabaseName("jee-live-code")
            .withUsername("root")
            .withPassword("root")
            .withNetworkAliases("jee-testcontainers-it");

    // create docker container based on the Dockerfile
    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withAppContextRoot("/jakarta-ee-live-code")
            .withExposedPorts(9080, 9443)
            .withEnv("MYSQL_HOSTNAME", "jee-testcontainers-it")
            .withEnv("MYSQL_PORT", "3306")
            .withReadinessPath("/health/ready")
            .dependsOn(mysql);
}
