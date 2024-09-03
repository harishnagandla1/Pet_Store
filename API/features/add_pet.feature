Feature: Add a new pet to the store

  Scenario: Admin adds a new pet through the admin portal
    Given the API endpoint '/api/pet' is ready to accept POST requests
    When a POST request is sent to '/api/pet' with the payload body
      """
      {
        "petType": "Dog",
        "name": "Rover",
        "age": 2,
        "status": "Available"
      }
      """
    Then the response should have a status code of 201
    And the response should confirm pet creation in JSON format