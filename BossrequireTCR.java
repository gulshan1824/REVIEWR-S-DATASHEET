import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BossrequireTCR {
	
	public static void main(String[] args) {
	    //Blank workbook
	    XSSFWorkbook workbook = new XSSFWorkbook();

	    //Create a blank sheet
	    XSSFSheet sheet = workbook.createSheet("TCR_SHEET");
	   // XSSFSheet sheet1 = workbook.createSheet("Number_of_BUGS_Reviewed");
	    
	    Map<String, Object[]> data = new TreeMap<String, Object[]>();
	   // Map<String, Object[]> data1 = new TreeMap<String, Object[]>();
	    String ft[][] = new String[500][500];
	   // String st1[][] =  new String[50][50];
	    String st2[][] =  new String[500][500];
	    String st3[][] =  new String[500][500];
	    data.put("1", new Object[]{"BUG-ID","REVIEWER'S NAME","COMPLEXITY","HOURS"});
	    //data1.put("1", new Object[]{"Number Of Bugs", "NAME"});
	    
	    
	    Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://192.168.57.101:3306/bugs";
			String connectionUser = "readonly";
			String connectionPassword = "readonly";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			int i = 0;
			int j = 0;
			int len =0,len1 =0,len2 =0;;
			rs = stmt.executeQuery("SELECT b.bug_id,p.login_name FROM bugs_activity b , profiles p where b.who = p.userid and b.bug_when>='2017-03-10 10:30:00' and p.login_name in ('akumari','csharma','abedi','dkumar2','siyyer','jsharma','maggarwal','pverma1','pkumar12','snagarkar','smukherjee1','skishor','Smachhindra','smahto','ygogula','akumar7','shui','graj','sdurga','smishra6','schowdhury1','sdhyani') and b.removed in('Waiting - Code Review 1') and b.added in('Waiting - Code Review 2') order by p.login_name;");
			System.out.println("Inside for  reviews");
			while (rs.next()) {
				len++;
				String id = rs.getString("b.bug_id");
				String firstName = rs.getString("login_name");
				//String lastName = rs.getString("realname");
				System.out.println("ID:-> " + id + ", First Name:-> " + firstName);
				ft[i][j]= id;
				ft[i][j+1]= firstName;
				i++;
				
			}
			i = 0;
			rs = stmt.executeQuery("select b1.bug_id,b1.added,p.login_name from bugs_activity b1,profiles p where  b1.fieldid = 117 and bug_when in(select b2.bug_when from bugs_activity b2 where b2.added in('Waiting - Code Review 2') and b2.removed in('Waiting - Code Review 1') and b2.bug_when >='2017-03-10 10:30:00') and p.login_name in ('akumari','csharma','abedi','dkumar2','siyyer','jsharma','maggarwal','pverma1','pkumar12','snagarkar','smukherjee1','skishor','Smachhindra','smahto','ygogula','akumar7','shui','graj','sdurga','smishra6','schowdhury1','sdhyani') and b1.who=p.userid order by p.login_name;");
			
			System.out.println("Inside for Complexity");
			while (rs.next()) {
				len1++;
				String id = rs.getString("b1.bug_id");
				String firstName = rs.getString("login_name");
				String complexity = rs.getString("b1.added");
				//String lastName = rs.getString("realname");
				System.out.println("ID:-> " + id + ", First Name:-> " + firstName + " ,Complexity:->"+complexity);
				
				st2[i][j]= id;
				st2[i][j+1]= firstName;
				st2[i][j+2] = complexity;
				i++;
			}
			 
			i =0;
			rs = stmt.executeQuery("select b1.bug_id,b1.added,p.login_name from bugs_activity b1,profiles p where  b1.fieldid = 40 and bug_when in(select b2.bug_when from bugs_activity b2 where b2.added in('Waiting - Code Review 2') and b2.removed in('Waiting - Code Review 1') and b2.bug_when >='2017-03-10 10:30:00') and p.login_name in ('akumari','csharma','abedi','dkumar2','siyyer','jsharma','maggarwal','pverma1','pkumar12','snagarkar','smukherjee1','skishor','Smachhindra','smahto','ygogula','akumar7','shui','graj','sdurga','smishra6','schowdhury1','sdhyani') and b1.who=p.userid order by p.login_name;");
			
			System.out.println("Inside for Hours");
			while (rs.next()) {
				
				len2++;
				String id = rs.getString("b1.bug_id");
				String firstname = rs.getString("login_name");
				String hrs = rs.getString("b1.added");
				//String lastName = rs.getString("realname");
				System.out.println("ID:-> " + id + ", Hours Given:-> " + hrs + " ,Name:-> "+firstname);
				
				st3[i][j]= id;
				st3[i][j+1]=firstname ;
				st3[i][j+2] = hrs;
				i++;
			}
			int p=0,q=0,r=0;
			
			int flag1 =0,flag2=0;
			for (int k =0;k<len;k++)
			{
				for(p=0;p<len1;p++)
				{
					if(ft[k][0].equalsIgnoreCase(st2[p][0]))
					{
						System.out.println("Found for Bug ID:->"+ft[k][0]);
						ft[k][j+2]=st2[p][j+2];
						flag1 =1;
						break;
					}
					
				}
				for(q =0;q<len2;q++)
				{
					if(ft[k][0].equalsIgnoreCase(st3[q][0]))
					{
						System.out.println("Found2 for Bug ID:->"+ft[k][0]);
						ft[k][j+3]=st3[q][j+2];
						flag2 =1;
						break;
					}
					
				}
				
				if(flag1 !=1)
				{
					ft[k][j+2] ="No Changes";
				}
				if(flag2 !=1)
				{
					ft[k][j+3] ="No Changes";
				}
				
				flag1 =0;
				flag2 =0;
			}
			
			
			for (int k1=0;k1<len;k1++)
			{
				System.out.println("Bug id:->"+ft[k1][j]+" Name:->"+ft[k1][j+1]+" Complexity:->"+ft[k1][j+2]+" Hours:->"+ft[k1][j+3]);
				data.put(Integer.toString(k1+2), new Object[]{ft[k1][j],ft[k1][j+1],ft[k1][j+2],ft[k1][j+3]});
			}
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	    

		 Set<String> keyset = data.keySet();

		    int rownum = 0;
		    for (String key : keyset) 
		    {
		        //create a row of excelsheet
		        XSSFRow row = sheet.createRow(rownum++);

		        //get object array of prerticuler key
		        Object[] objArr = data.get(key);

		        int cellnum = 0;

		        for (Object obj : objArr) 
		        {
		            Cell cell = row.createCell(cellnum++);
		            if (obj instanceof String) 
		            {
		                cell.setCellValue((String) obj);
		            }
		            else if (obj instanceof Integer) 
		            {
		                cell.setCellValue((Integer) obj);
		            }
		        }
		    }
	    
	    // rownum = 0;
	     
	    /* Set<String> keyset1 = data1.keySet();
	    for (String key : keyset1) 
	    {
	        //create a row of excelsheet
	        XSSFRow row = sheet1.createRow(rownum++);

	        //get object array of prerticuler key
	        Object[] objArr = data1.get(key);

	        int cellnum = 0;

	        for (Object obj : objArr) 
	        {
	            Cell cell = row.createCell(cellnum++);
	            if (obj instanceof String) 
	            {
	                cell.setCellValue((String) obj);
	            }
	            else if (obj instanceof Integer) 
	            {
	                cell.setCellValue((Integer) obj);
	            }
	        }
	    }*/
	    
	  //  rownum = 0;
	    
	    
	    try 
	    {
	        //Write the workbook in file system
	        FileOutputStream out = new FileOutputStream(new File("D://TCR-DATA.xlsx"));
	        workbook.write(out);
	        out.close();
	        System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
	    } 
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}

}
