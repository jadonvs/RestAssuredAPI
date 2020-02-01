Feature: Testing Rest API using framework

@AddPlace
Scenario Outline: Google Add Place API Test End to End
	Given Add Place Payload with "<name>" "<language>" and "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call should be successful with 200 code
	And "status" in reponse body is "OK"
	And "scope" in reponse body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
Examples: 
	|name	 |language	|address		   |
	|AAhouse |English   |World cross center|
	|BBhouse |Spanish   |Sea cross center  |
	
Scenario: 