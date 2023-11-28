Feature: LaunchWebSite

@Scenario Outline: Verify the launching of browser.
@Given  I Open the "<browser>"
@When I enter the url "https://www.flipkart.com"
Then I verify the website is launched.
Examples:
    | browser |
    | chrome  |
    |edge     |
    |firefox  |