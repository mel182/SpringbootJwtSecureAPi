Feature: Prevent user/admin from retrieving the list of admins
  Scenario: user/admin makes a call to GET /admin/all
    Given check if server is up and running for "https://localhost:3000"
    When the client calls /admin/all
    Then the client receives status code of 200
    And the client receives server version 1.0