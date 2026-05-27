package pe.edu.upeu.api_orders.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pe.edu.upeu.api_orders.model.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderSteps {

    private ResponseEntity<Order> response;
    private final RestTemplate restTemplate = new RestTemplate();

    @Given("the order API is up")
    public void the_order_api_is_up() {
        // Aquí podrías verificar si el servidor responde.
    }

    @When("I send a POST request to {string} with customer {string} and amount {double}")
    public void i_send_a_post_request(String path, String customer, Double amount) {
        Order order = new Order(null, customer, amount);
        response = restTemplate.postForEntity(
                "http://localhost:8080" + path,
                order,
                Order.class
        );
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @Then("the returned amount should be {double}")
    public void the_returned_amount_should_be(Double expectedAmount) {
        assertEquals(expectedAmount, response.getBody().getAmount(), 0.001);
    }
}