Feature: GitHub Repository Creation

  Scenario: Create a new GitHub repository via API
    Given GitHub API is available
    When I send a POST request to create a new repository
    Then the repository should be created successfully with name "API-testing-repositorycall123"
