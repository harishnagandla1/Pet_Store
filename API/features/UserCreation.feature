Feature: User creation through API

Scenario Outline: Successful creation of users with different data sets
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
| name       | job            |
| John Doe   | Software Engineer |
| Jane Smith | Data Scientist |
| Bob Martin | Product Manager |
