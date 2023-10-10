Feature: login

  Scenario Outline: Verify login functionality with valid credentials
    Given I launch the browser.
    When I navigate to the url "https://www.saucedemo.com"
    And I enter the  valid "<username>" and "<password>"
    And I enter the click button
    Then I navigated to homePage of website
    And I close the browser
    Examples:
      | username | password |
    | standard_user| secret_sauce|