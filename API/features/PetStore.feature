Feature: User Management API services

Scenario Outline: Create a new user
Given the API endpoint '/api/users' is ready to accept POST requests
When a POST request is sent to '/api/users' with the payload body:
"""
{
"name": "<name>",
"job": "<job>"
}
"""
Then the response should have a status code of 201
And the response should confirm user creation in JSON format

Examples:
| name  | job         |
| John  | Engineer    |
| Alice | Accountant  |
| Bob   | Manager     |

Scenario Outline: Update an existing user's information
Given the API endpoint '/api/users/<id>' is ready to accept PUT requests
When a PUT request is sent to '/api/users/<id>' with the payload body:
"""
{
"name": "<name>",
"job": "<job>"
}
"""
Then the response should have a status code of 200
And the response should confirm user update in JSON format

Examples:
| id | name  | job         |
| 1  | John  | Manager     |
| 2  | Alice | HR          |
| 3  | Bob   | Engineer    |

Scenario Outline: Delete a user
Given the API endpoint '/api/users/<id>' is ready to accept DELETE requests
When a DELETE request is sent to '/api/users/<id>'
Then the response should have a status code of 204

Examples:
| id |
| 1  |
| 2  |
| 3  |

Scenario Outline: Get user by ID
Given the API endpoint '/api/users/<id>' is ready to accept GET requests
When a GET request is sent to '/api/users/<id>'
Then the response should have a status code of 200
And the response should return the user information in JSON format

Examples:
| id |
| 1  |
| 2  |
| 3  |
