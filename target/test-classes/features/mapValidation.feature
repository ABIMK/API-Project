Feature: Validating Place Api
@AddPlace
Scenario Outline: Verify if place is successfully added using AddPlaceAPI
		Given Add Place Payload with "<name>" "<address>" "<language>"
		When user calls "AddPlaceAPI" using "POST" request
		Then Api call success with status code 200
		And "status" in response body is "OK"
		And verify place_Id created maps to "<name>" using "GetPlaceAPI" and "name"
		
Examples:
|name      |address            |language|
|Danny Home| North Cross Street|English | 
#|Jenny Home| West wings Lane   |French  | 

@UpdatePlace
Scenario Outline: Verify new address is updated using GetPlaceAPI
		Given Update Place Payload with "<address>"
		When user calls "UpdatePlaceAPI" using "PUT" request
		Then Api call success with status code 200
		And "msg" in response body is "Address successfully updated"
		And verify place_Id created maps to "<address>" using "GetPlaceAPI" and "address"
		
Examples:
|address  	  | 
|Rich Way Road|
		
@DeletePlace
Scenario: Verify place is deleted using DeletePlaceAPI
		Given Delete Place Payload
		When user calls "DeletePlaceAPI" using "POST" request
		Then Api call success with status code 200
		And "status" in response body is "OK"
		
