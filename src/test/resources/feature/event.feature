Feature: Event End Point
  Background:
    * url 'http://localhost:8030'
    * header Accept = 'application/json'

  Scenario: Testing valid GET endpoint
    Given path 'event/all'
    When method GET
    Then status 200
    * def first = response[0]
    And match first contains {libelle:"libelle 1"}



  Scenario: Add new Event OK response
    Given  path 'event'
    And request { decription: 'description 4' }
    When method POST
    Then status 201
    And def question = response

  Scenario: Add new Question KO response
    Given  path 'event'
    And request { decription: 'decription 4' }
    When method POST
    Then status 302
