package net.harunote.springredis.config.db;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author CodeVillains
 * @description :
 */
@SpringBootTest
public class DbConnectionTest {

    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String DB_USERNAME;
    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;
    @Test
    public void testConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            //  checks whether the connection is valid.
            boolean isValid = connection.isValid(1); // 5 seconds timeout
            if (isValid) {
                System.out.println("Connection is valid");
            } else {
                System.out.println("Connection is invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
