Feature: Order API

  Scenario: Create a new order via REST with discount
    Given the order API is up
    When I send a POST request to "/api/orders" with customer "Elvis Garcia" and amount 1200.0
    Then the response status should be 201
    And the returned amount should be 1080.0