package webrunner.cardmaster;
public class ExtraData {
		public ExtraData() {
			name = "";
			dataa = "";
			datab = "";	
			
		}
		public ExtraData(String n, String da, String db) {
			name = n;
			dataa = da;
			datab = db;	
			
			
		}
		public String toString() {
			return name + "#" + dataa + "#" + datab + "#";	
			
			
		}
		public String name;
		public String dataa;
		public String datab;	
		
		
		
	}