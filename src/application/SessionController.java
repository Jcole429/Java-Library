package application;

public class SessionController {
	
	private static User currentUser;
	
	public SessionController() {
		
	}
	
	public void createSession(User currentUser) {
		SessionController.currentUser = currentUser;
	}
	
	public void destroySession() {
		SessionController.currentUser = null;
	}
	
	public User currentUser() {
		return SessionController.currentUser;
	}
}
