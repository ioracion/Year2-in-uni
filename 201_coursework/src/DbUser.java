import java.sql.*;
import java.io.*;

// this is the class through which all Database calls go
public class DbUser extends DbBasic {

	static private final int STR_SIZE = 25;
	
	String catalog = null;
	String schemaPattern = null;
	String tableNamePattern = null;
	String columnNamePattern = null;
	String[] types = {"TABLE"};
	
	private ResultSet rs,rs2 = null;	
	String schema = null;
	String insert = "INSERT INTO ";
	String create = "CREATE TABLE ";
	String query=null;
	ResultSetMetaData rsm;
	int count,count2 = 0;

	public void mileA(String file){
		try{				
			//get metadata
			DatabaseMetaData dbmd = con.getMetaData();
			Statement stmt = con.createStatement();
			rs2 = dbmd.getTables(catalog, schemaPattern, tableNamePattern, types);
										
			while(rs2.next()){
					
				query = "SELECT * FROM "+rs2.getString("TABLE_NAME");
				rs = stmt.executeQuery(query);
				rsm = rs.getMetaData();
				while(rs.next()){
						
					System.out.print(insert);
					System.out.print(rs2.getString("TABLE_NAME"));
					System.out.print(" VALUES ( ");
					newFile("/Users/macbookair/Desktop//output/"+file+".txt",insert+rs2.getString("TABLE_NAME")+" VALUES ( ");
					
					for(int y=1; y<=rsm.getColumnCount();y++){						
						if(y==rsm.getColumnCount()){
							System.out.print(rs.getString(y));
							newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString(y));
						}
						else{
							System.out.print(rs.getString(y)+",\t");
							newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString(y)+",\t");
						}
						
					}
					System.out.print(" );");
					newFile("/Users/macbookair/Desktop//output/"+file+".txt"," );\n");
					System.out.println();
				}
			}
				
			
		}catch(Exception e){e.printStackTrace();}
	}
	
	
	public void mileB(String file){
		try{			
			//get metadata
			DatabaseMetaData dbmd = con.getMetaData();		
			Statement stmt = con.createStatement();
			rs2 = dbmd.getTables(catalog, schemaPattern, tableNamePattern, types);
			
			while(rs2.next()){
				query = "SELECT * FROM "+rs2.getString("TABLE_NAME");
				rs = stmt.executeQuery(query);
				rsm = rs.getMetaData();
				
				while(rs.next()){
					System.out.print(insert);
					System.out.print(rs2.getString("TABLE_NAME"));
					System.out.print(" VALUES ( ");
					newFile("/Users/macbookair/Desktop//output/"+file+".txt",insert+rs2.getString("TABLE_NAME")+" VALUES ( ");
					for(int y=1; y<=rsm.getColumnCount();y++){
						if(rsm.getColumnType(y) == Types.VARCHAR){
							if(y==rsm.getColumnCount()){
								System.out.print("'"+rs.getString(y)+"'");
								newFile("/Users/macbookair/Desktop//output/"+file+".txt","'"+rs.getString(y)+"'");
							}
							else{
								System.out.print("'"+rs.getString(y)+"',\t");
								newFile("/Users/macbookair/Desktop//output/"+file+".txt","'"+rs.getString(y)+"',\t");
							}
						}
						else{
							if(y==rsm.getColumnCount()){
								System.out.print(rs.getString(y));
								newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString(y));
							}
							else{
								System.out.print(rs.getString(y)+",\t");
								newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString(y)+",\t");
							}
						}
						
					}
					System.out.print(" );");
					newFile("/Users/macbookair/Desktop//output/"+file+".txt"," );\n");
					System.out.println();
				}			
			}
			
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void mileC(String file){
		try{			
			//get metadata
			DatabaseMetaData dbmd = con.getMetaData();	
			Statement stmt = con.createStatement();
			rs2 = dbmd.getTables(catalog, schemaPattern, tableNamePattern, types);
			while(rs2.next()){
				
				query = "SELECT * FROM "+rs2.getString("TABLE_NAME");
				rs = stmt.executeQuery(query);
				rsm = rs.getMetaData();
				
				rs = dbmd.getColumns(catalog, schemaPattern, rs2.getString("TABLE_NAME"), columnNamePattern);
				System.out.print(create+rs2.getString("TABLE_NAME")+"(\n");
				newFile("/Users/macbookair/Desktop//output/"+file+".txt",create+rs2.getString("TABLE_NAME")+"(\n");
				while(rs.next()){					
					if(rs.getRow() == rsm.getColumnCount()){												
						System.out.println(rs.getString(4) +" " + rs.getString(6));
						newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString(4) +" " + rs.getString(6));
					}
					else{
						System.out.println(rs.getString(4) +" " + rs.getString(6) +",");
						newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString(4) +" " + rs.getString(6) +",");
					}
				}				
				System.out.println(");\n");
				newFile("/Users/macbookair/Desktop//output/"+file+".txt"," );\n\n");
			}
			
			
		}catch(Exception e){e.printStackTrace();};		
		mileB(file);
	}
	
	public void mileD(String file){
			
		try{
			//get metadata
			DatabaseMetaData dbmd = con.getMetaData();	
			Statement stmt = con.createStatement();
			rs2 = dbmd.getTables(catalog, schemaPattern, tableNamePattern, types);
	
			while(rs2.next()){
				
				query = "SELECT * FROM "+rs2.getString("TABLE_NAME");
				rs = stmt.executeQuery(query);
				rsm = rs.getMetaData();
				
				rs = dbmd.getColumns(catalog, schemaPattern, rs2.getString("TABLE_NAME"), columnNamePattern);
				System.out.print(create+rs2.getString("TABLE_NAME")+"(\n");
				newFile("/Users/macbookair/Desktop//output/"+file+".txt",create+rs2.getString("TABLE_NAME")+"(\n");
				
				while(rs.next()){					
					System.out.println(rs.getString(4) +" " + rs.getString(6) +",");
					newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString(4) +" " + rs.getString(6) +",");
				}				
				
				//primary key
				//count num of a table
				rs = dbmd.getPrimaryKeys(catalog, schema, rs2.getString("TABLE_NAME"));	
				while(rs.next()){
					count++;		
				}
				//print
				rs = dbmd.getPrimaryKeys(catalog, schema, rs2.getString("TABLE_NAME"));	
				System.out.print("primary key ( ");
				newFile("/Users/macbookair/Desktop//output/"+file+".txt","primary key ( ");
				while(rs.next()){
					count--;
					if(count > 0){
						System.out.print(rs.getString("COLUMN_NAME")+", ");
						newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString("COLUMN_NAME")+", ");
					}
					else{
						System.out.print(rs.getString("COLUMN_NAME"));
						newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString("COLUMN_NAME"));
					}			
				}
				System.out.println(" )\n);");
				newFile("/Users/macbookair/Desktop//output/"+file+".txt"," )\n);\n\n");				
				System.out.println();
				count = 0;
			}
			
			
		}catch(Exception e){e.printStackTrace();};
		mileB(file);
	}
	
	public void mileE(String file){
		try{		
			//get metadata
			DatabaseMetaData dbmd = con.getMetaData();	
			Statement stmt = con.createStatement();
			rs2 = dbmd.getTables(catalog, schemaPattern, tableNamePattern, types);
	
			while(rs2.next()){
				
				query = "SELECT * FROM "+rs2.getString("TABLE_NAME");
				rs = stmt.executeQuery(query);
				rsm = rs.getMetaData();
				
				rs = dbmd.getColumns(catalog, schemaPattern, rs2.getString("TABLE_NAME"), columnNamePattern);
				System.out.print(create+rs2.getString("TABLE_NAME")+"(\n");
				newFile("/Users/macbookair/Desktop//output/"+file+".txt",create+rs2.getString("TABLE_NAME")+"(\n");
				
				while(rs.next()){					
					System.out.println(rs.getString(4) +" " + rs.getString(6) +",");
					newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString(4) +" " + rs.getString(6) +",");
				}				
				
				//primary key
				//count num of a table
				rs = dbmd.getPrimaryKeys(catalog, schema, rs2.getString("TABLE_NAME"));	
				while(rs.next()){
					count++;		
				}
				//print
				rs = dbmd.getPrimaryKeys(catalog, schema, rs2.getString("TABLE_NAME"));	
				System.out.print("primary key ( ");
				newFile("/Users/macbookair/Desktop//output/"+file+".txt","primary key ( ");
				while(rs.next()){
					count--;
					if(count > 0){
						System.out.print(rs.getString("COLUMN_NAME")+", ");
						newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString("COLUMN_NAME")+", ");
					}
					else{
						System.out.print(rs.getString("COLUMN_NAME"));
						newFile("/Users/macbookair/Desktop//output/"+file+".txt",rs.getString("COLUMN_NAME"));
					}			
				}
				System.out.println(" )");
				newFile("/Users/macbookair/Desktop//output/"+file+".txt"," )");
				
				//foreign key
				//count
				rs = dbmd.getImportedKeys(catalog, schema, rs2.getString("TABLE_NAME"));
				while(rs.next()){
					count2++;
				}
				
				//only process if there is a foreign key
				if(count2 != 0){
					//print
					System.out.print(",");
					newFile("/Users/macbookair/Desktop//output/"+file+".txt",",");
					rs = dbmd.getImportedKeys(catalog, schema, rs2.getString("TABLE_NAME"));
					while(rs.next()){
						count2--;
						if(count2 > 0){
							System.out.println("foreign key ("+ rs.getString("FKCOLUMN_NAME") + ") references " + rs.getString("PKTABLE_NAME") + "(" + rs.getString("PKCOLUMN_NAME") + "),");
							newFile("/Users/macbookair/Desktop//output/"+file+".txt","foreign key ("+ rs.getString("FKCOLUMN_NAME") + ") references " + rs.getString("PKTABLE_NAME") + "(" + rs.getString("PKCOLUMN_NAME") + "),");
						}
						else{
							System.out.println("foreign key ("+ rs.getString("FKCOLUMN_NAME") + ") references " + rs.getString("PKTABLE_NAME") + "(" + rs.getString("PKCOLUMN_NAME") + ")");
							newFile("/Users/macbookair/Desktop//output/"+file+".txt","foreign key ("+ rs.getString("FKCOLUMN_NAME") + ") references " + rs.getString("PKTABLE_NAME") + "(" + rs.getString("PKCOLUMN_NAME") + ")");
						}
					}		
				}
				
				System.out.println(");");
				newFile("/Users/macbookair/Desktop//output/"+file+".txt",");\n\n");								
				System.out.println();
				count = 0;
				count2 = 0;
			}
			
			
		}catch(Exception e){e.printStackTrace();};
		mileB(file);
	}
	
	public void mileF(String file){
		mileE(file);
	}
	
	// this method takes a String, converts it into an array of bytes;
	// copies those bytes into a bigger byte array (STR_SIZE worth), and
	// pads any remaining bytes with spaces. Finally, it converts the bigger
	// byte array back into a String, which it then returns.
	// e.g. if the String was "s_name", the new string returned is
	// "s_name                    " (the six characters followed by 18 spaces).
	private String pad( String in ) {
		byte [] org_bytes = in.getBytes( );
		byte [] new_bytes = new byte[STR_SIZE];
		int upb = in.length( );

		if ( upb > STR_SIZE )
			upb = STR_SIZE;

		for ( int i = 0; i < upb; i++ )
			new_bytes[i] = org_bytes[i];

		for ( int i = upb; i < STR_SIZE; i++ )
			new_bytes[i] = ' ';

		return new String( new_bytes );
	}

	/*
	 * Creates a connection to the named database
	 */
	DbUser ( String dbName ) {
		super( dbName );
	}

	public static void newFile(String filePathAndName, String fileContent) {
        try {
            File myFilePath = new File(filePathAndName.toString());
            if (!myFilePath.exists()) { 
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath,true);
            PrintWriter myFile = new PrintWriter(resultFile);
     
            myFile.print(fileContent);
            resultFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
