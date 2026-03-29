@UserService
Feature: User Service feature

  @getAllUsers
  Scenario: get all users
    Given the user service endpoint
    When the user sends a GET request to the user service endpoint
    Then all the users should be returned
    And the response should have page number greater than 0
    And the response should have per page value greater than 1
    And the response should have total value greater than 1
    And the response should have total pages value greater than 1
    And the each data id should not be null

  @getAllUsersByPageNumber
  Scenario: get all users by page number
    Given the user service endpoint
    When the user sends a GET request to the user service endpoint with query parameter "page" and value "2"
    Then all the users should be returned
    And the response should have page number "2"
    And the each data id should not be null

  @getUserById
  Scenario: get user by id
    Given the user service endpoint
    When the user sends a GET request to the user service endpoint with path parameter "id" and value "2"
    Then the user with id "2" should be returned


  @createUser
  Scenario: Create User
    Given the user service endpoint
    When the user sends a POST request to the user service endpoint with name "morpheus" and job "leader"
    Then the new user should be created
    And the response have the id should not be null
    And the response should have name "morpheus"
    And the response should have job "leader"

  @createMultipleUsers
  Scenario Outline: Create multiple users
    Given the user service endpoint
    When the user sends a POST request to the user service endpoint with "<name>" and "<job>"
    Then the new user should be created
    And the response have the id should not be null
    And the response should have name "<name>"
    And the response should have job "<job>"

    Examples:
      | name     | job     |
      | morpheus | leader  |
      | neo      | the one |
      | trinity  | hacker  |

@updateUser
Scenario: Update User
    Given the user service endpoint
    When the user sends a PUT request to the user service endpoint with path parameter "id" and value "2" and name "morpheus" and job "zion resident"
    Then the response should have name "morpheus" and job "zion resident"


  @deleteUser
  Scenario: Delete User
    Given the user service endpoint
    When the user sends a DELETE request to the user service endpoint with path parameter "id" and value "2"
    Then the response should be empty