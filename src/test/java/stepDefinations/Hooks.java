package stepDefinations;

import cucumber.api.java.Before;

public class Hooks {
	
	@Before
	public void beforeHooks()
	{
		System.out.println("Before Hooks exuected first");
	}

}
