

public class main {

DbUser myDbUser = null;


private void go()
{
	System.out.println("Bilililili...");
	myDbUser = new DbUser("201_coursework/src/cars");
	System.out.println();
	System.out.println();
	myDbUser.mileA("mileA");
	System.out.println();
	System.out.println();
	myDbUser.mileB("mileB");
	System.out.println();
	myDbUser.mileC("mileC");
	System.out.println();
	System.out.println();
	myDbUser.mileD("mileD");
	System.out.println();
	System.out.println();
	myDbUser.mileE("mileE");
	System.out.println();
	System.out.println();
	myDbUser.mileF("mileF");
	System.out.println("\n\nProcessing over");
	
	myDbUser.close();
}; // end of method "go"

public static void main(String [ ] args)
{
	main myMain = new main();
	myMain.go();
} // end of method "main"

} // end of class "Main"
