Feature: Department

  @Department
  Scenario: Verify Department details
    Given I set up the request structure to get all department
    When I will hit get all department api
    |method|endpoint|
    | GET     | Departments|
    Then I verify all department details