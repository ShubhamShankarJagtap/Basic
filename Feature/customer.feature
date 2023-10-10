Feature: verify customer feature

@GetAllCustomer
  Scenario:Verify get all customer information in get all api response
Given I set up the request structure to get all customers
When I hit an api
  |method|endPoint|
  | GET      |customers|
  Then I verify all customers details in the response
  |Joda Consulting inc|

  @GetById
  Scenario: verify customer information in get by ID api
Given I set up the request structure to get all customers
 When I hit an api
    |method|endPoint|pathParam|
    |GET      |customers|    6     |