package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleMapAddAPI;

public class TestDataBuilder {
	
	public GoogleMapAddAPI addPlaceTestBuilder(String name,String language, String address)
	{
		GoogleMapAddAPI gm=new GoogleMapAddAPI();
		gm.setAccuracy(50);
		gm.setName(name);
		gm.setPhone_number("(+91) 983 893 3937");
		gm.setAddress(address);
		gm.setWebsite("http://google.com");
		gm.setLanguage(language);
		List<String> mylist=new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		gm.setTypes(mylist);
		pojo.Location lc=new pojo.Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		gm.setLocation(lc);
		return gm;
	}

}
