package pe.edu.upeu.api_orders.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.upeu.api_orders.ApiOrdersApplication;


@CucumberContextConfiguration
@SpringBootTest (
        classes = ApiOrdersApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = "server.port=8080"
)
public class CucumberSpringConfiguration {
}
