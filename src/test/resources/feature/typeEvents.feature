Feature: TypeEvents End Point
  Background:
    * url 'http://localhost:8030'
    * header Accept = 'application/json'

  Scenario: Testing valid GET endpoint
    Given path 'typeevents/all'
    When method GET
    Then status 200
    * def first = response[0]
    And match first contains {titleEvent:"titleEvent 1"}


  Scenario: Add new TypeEvents OK response
    Given  path 'typeevents'
    And request { titleEvent: 'titleEvent 4' }
    When method POST
    Then status 201
    And def typeevents = response

  Scenario: Add new TypeEvents KO response
    Given  path 'typeevents'
    And request { titleEvent: 'titleEvent 4' }
    When method POST
    Then status 302
