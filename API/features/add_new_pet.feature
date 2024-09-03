Feature: Add new pets to the store through API

  Scenario Outline: Successful creation of pets with different data sets
    Given the API endpoint '/pet' is ready to accept POST requests
    When a POST request is sent to '/pet' with the payload body:
      """
      {
        "category": {
          "id": "<categoryId>",
          "name": "<categoryName>"
        },
        "name": "<petName>",
        "photoUrls": [
          "<photoUrl>"
        ],
        "tags": [
          {
            "id": "<tagId>",
            "name": "<tagName>"
          }
        ],
        "status": "<status>"
      }
      """
    Then the response should have a status code of 200
    And the response should confirm pet creation in JSON format

    Examples:
      | categoryId | categoryName | petName | photoUrl      | tagId | tagName | status   |
      | 1          | Dogs         | Fluffy  | fluffy.com    | 0     | puppy   | available|
      | 2          | Cats         | Kitty   | kitty.com     | 1     | kitten  | sold     |