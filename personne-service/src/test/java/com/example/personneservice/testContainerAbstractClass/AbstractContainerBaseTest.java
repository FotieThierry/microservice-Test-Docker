package com.example.personneservice.testContainerAbstractClass;

import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class AbstractContainerBaseTest {
    static final MariaDBContainer MARIA_DB_CONTAINER;
    static {
        MARIA_DB_CONTAINER = new MariaDBContainer(DockerImageName.parse("mariadb:latest"));
        MARIA_DB_CONTAINER.start();
    }
}
