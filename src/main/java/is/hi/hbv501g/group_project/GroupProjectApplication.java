package is.hi.hbv501g.group_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

@SpringBootApplication
public class GroupProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupProjectApplication.class, args);
    }

}
