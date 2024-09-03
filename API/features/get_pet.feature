Feature: Get a pet's details

  Scenario: User fetches a pet's details through the user portal
    Given the API endpoint '/api/pet/{petId}' is ready to accept GET requests
    When a GET request is sent to '/api/pet/{petId}'
    Then the response should have a status code of 200
    And the response should return the pet's details in JSON format