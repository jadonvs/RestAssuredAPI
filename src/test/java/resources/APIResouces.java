package resources;

public enum APIResouces {

	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resources;
	APIResouces(String resources) {
		this.resources=resources;
	}
	
	public String getAPIResources()
	{
		return resources;
	}

}
