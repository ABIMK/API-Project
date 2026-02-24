package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace or @UpdatePlace")
	public void beforeScenario() throws IOException {
		MapApi m=new MapApi();
		if(MapApi.place_id==null) {
			m.add_place_payload_with("Rahul", "Demonte Lane", "Spanish");
			m.user_calls_using_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using_and("Rahul", "GetPlaceAPI", "name");
		}
	}

}
