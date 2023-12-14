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

    @Create_Customer
    Scenario: Verify create customer using valid details.
      Given I setup the request structure to create new customer
      |name| archived|
      |CyberSuccess2|      |
      When I hit an api
      |method|endPoint|
      |POST    |customers|
      Then I verify the customer is getting created successfully with the name "CyberSuccess2"
      When I set up the request structure to get all customers
      And I hit an api
      |method|endPoint|
      |GET      |customers |

      @CustomerSorting
        Scenario Outline: Verify customer response is getting sorted in asc and desc order by name
        Given I set up the request structure.
       |sort|<value>|
       When I hit an api
        |method|endPoint|
        |GET      |customers |
        Then I verify the response is getting sorted in "<order>"
        Examples:
          | value |order |
          |-name   |desc   |
          |name    | asc     |

        @NegativeForCreateCustomer
        Scenario: Verify the customer is not getting created with duplicate name
          Given I setup the request structure to create new customer
          |name                 |archived|
          |CyberSuccess2 |               |
          When I hit an api
        |method|endPoint|
          |POST  |customers  |
         Then I verify the status-code as 400 and error message
          |message| Customer with specified name already exists|

          @VerifyErrorMessages
          Scenario Outline: Verify customer should not be created with missing name
            Given I setup the request structure to create new customer
            |name|archived|
            |<name>|        |
            When I hit an api
            |method|endPoint|
            |POST      | customers|
          Then I verify the status-code as 400 and error message
            |message|<message>|
            Examples:
            |name|message|
            |empty    | String length must be between 1 and 255 |
            |              | Mandatory field is not specified         |

            @ArchivedVerification
            Scenario: Verify customer should not be created with missing archived
              Given I setup the request structure to create customer payload
              |          name       |archived|
              |Api Automation |        |
              When I hit an api
              |method|endPoint|
              |POST    |customers|
              Then I verify status code 200 and archived in the response.
              |false|

