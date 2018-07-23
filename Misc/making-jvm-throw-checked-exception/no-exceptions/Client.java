public class Client {
	
	public static void main(final String[] args) {
		new Thrower().throwCheckedException();
		System.out.println("Exception has not been thrown");
	}
}