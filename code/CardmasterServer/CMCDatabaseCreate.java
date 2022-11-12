package webrunner.cardmaster;
public class CMCDatabaseCreate {


	public static void main(String args[])  {
	
	 	System.out.println("Attempting to create main Database...");
		String returns = CardmasterDatabase.CreateCMCDatabase();
		System.out.println(returns);
		if (returns.equals("Finished")) {
				System.out.println("Database created or already exists!");
		}
		
		
		System.out.println("Attempting to create message Database...");
		returns = CardmasterMessageSystem.CreateDatabase();
		System.out.println(returns);
		if (returns.equals("Finished")) {
				System.out.println("Database created or already exists!");
		}
		
		
		
		System.out.println("Attempting to create storeDatabase...");
		returns = CardmasterDBStoreSystem.CreateDatabase();
		System.out.println(returns);
		if (returns.equals("Finished")) {
				System.out.println("Database created or already exists!");
		}
		
	
			
	}

}
	