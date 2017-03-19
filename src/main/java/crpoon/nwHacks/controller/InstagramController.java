package crpoon.nwHacks.controller;

public class InstagramController {
	
	private static InstagramController instance;
	
	private InstagramController() {
	}
	
	public static InstagramController getInstance() {
		if (instance == null) {
			instance = new InstagramController();
		}
		return instance;
	}

}
