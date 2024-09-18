Feature: Delete a pet from the store

  Scenario: Admin deletes a pet through the admin portal
    Given the API endpoint '/api/pet/{petId}' is ready to accept DELETE requests
    When a DELETE request is sent to '/api/pet/{petId}'
    Then the response should have a status code of 200
    And the response should confirm pet deletion in JSON format