Feature: Update a pet's status

  Scenario: Admin updates a pet's status through the admin portal
    Given the API endpoint '/api/pet/{petId}' is ready to accept PUT requests
    When a PUT request is sent to '/api/pet/{petId}' with the payload body
      """
      {
        "status": "Sold"
      }
      """
    Then the response should have a status code of 200
    And the response should confirm pet status update in JSON format