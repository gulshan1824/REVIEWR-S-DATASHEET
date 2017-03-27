import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.Set;

public class NewMailTesting {
	
	static String fileNm = "testing.xlsx";
	static Map<String, Object[]> data = new TreeMap<String, Object[]>();
	static Map<String, Object[]> data1 = new TreeMap<String, Object[]>();
	static Map<String, Object[]> data2 = new TreeMap<String, Object[]>();
	static Map<String, Object[]> data3 = new TreeMap<String, Object[]>();
    
   
	
	
	public static void main(String args[])
	{
		
		 data.put("1", new Object[]{"BUG ID", "NAME"});
		    data1.put("1", new Object[]{"Number Of Bugs", "NAME"});
		    data2.put("1", new Object[]{"BUG ID", "NAME"});
		    data3.put("1", new Object[]{"BUG ID", "NAME"});
	    
	    Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://192.168.57.101:3306/bugs";
			String connectionUser = "readonly";
			String connectionPassword = "readonly";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			int i = 1;
			rs = stmt.executeQuery("SELECT b.bug_id,p.login_name FROM bugs_activity b , profiles p where b.who = p.userid and b.bug_when>='2017-03-10 19:30:00' and p.login_name in ('akumari','csharma','abedi','dkumar2','siyyer','jsharma','maggarwal','pverma1','pkumar12','snagarkar','smukherjee1','skishor','Smachhindra','smahto','ygogula','akumar7','shui','graj','sdurga','smishra6','schowdhury1','sdhyani') and removed in('Waiting - Code Review 1','Waiting - Code Review 2','Failed in review') and added in('Analyzed','New')");
			System.out.println("Inside for False failed in reviews");
			while (rs.next()) {
				i++;
				String id = rs.getString("b.bug_id");
				String firstName = rs.getString("login_name");
				//String lastName = rs.getString("realname");
				System.out.println("ID: " + id + ", First Name: " + firstName);
				data.put(Integer.toString(i), new Object[]{id, firstName});
			}
			
			
			
			int i1 = 1;
			rs1 = stmt.executeQuery("SELECT count(distinct b.bug_id) as Number_of_BUGS,p.login_name FROM bugs_activity b , profiles p where b.who = p.userid and b.bug_when>='2017-03-10 19:30:00' and p.login_name in ('akumari','csharma','abedi','dkumar2','siyyer','jsharma','maggarwal','pverma1','pkumar12','snagarkar','smukherjee1','skishor','Smachhindra','smahto','ygogula','akumar7','shui','graj','sdurga','smishra6','schowdhury1','sdhyani') and b.removed in('Waiting - Code Review 2','Waiting - Code Review 1') and b.added in('Reviewed','Failed in review','Waiting - Code Review 2') group by p.login_name order by Number_of_BUGS;");
			System.out.println("Inside for Number for Bugs Reviewed");
			while (rs1.next()) {
				
				
				i1++;
				String id = rs1.getString("Number_of_BUGS");
				String firstName = rs1.getString("login_name");
				//String lastName = rs.getString("realname");
				System.out.println("Number Of Bugs " + id + ", First Name: " + firstName);
				data2.put(Integer.toString(i1), new Object[]{id, firstName});
			}
			
			int i2 = 1;
			rs2 = stmt.executeQuery("SELECT b.bug_id,p.login_name from bugs_activity b , profiles p where p.userid = b.who and b.removed not in ('---') and b.added in ('Complex','Research Item – Complex','High','Medium','Low','Very Low') and p.login_name in('akumari','csharma','abedi','dkumar2','siyyer','jsharma','maggarwal','pverma1','pkumar12','snagarkar','smukherjee1','skishor','Smachhindra','smahto','ygogula','akumar7','shui','graj','sdurga','smishra6','schowdhury1','sdhyani')and b.bug_id in(SELECT b.bug_id FROM bugs_activity b , profiles p where b.who = p.userid and b.bug_when>='2017-03-10 19:30:00' and p.login_name in ('akumari','csharma','abedi','dkumar2','siyyer','jsharma','maggarwal','pverma1','pkumar12','snagarkar','smukherjee1','skishor','Smachhindra','smahto','ygogula','akumar7','shui','graj','sdurga','smishra6','schowdhury1','sdhyani') and removed in('Waiting - Code Review 2') and added in('Reviewed')) and b.bug_id not in (select b.bug_id from bugs_activity b where b.added in ('Open after DOOT','Open after reassigned','Waiting - Verification','New') and b.removed in('Dependent on other teams','Reassigned','Closed'))");
			System.out.println("Inside for BUGS For Complexity Change");
			while (rs2.next()) {
				
				
				i2++;
				String id = rs2.getString("bug_id");
				String firstName = rs2.getString("login_name");
				//String lastName = rs.getString("realname");
				System.out.println("BUG ID N1 " + id + ", Login Name: " + firstName);
				data1.put(Integer.toString(i2), new Object[]{id, firstName});
			}
			
			
			int i3 = 1;
			rs3 = stmt.executeQuery("select b.bug_id , p.login_name from bugs_activity  b, profiles p where b.bug_when>='2017-03-10 19:30:00' and  b.added not in (0.00) and b.removed not in(0.00) and b.fieldid =40 and b.who = p.userid and p.login_name in('akumari','csharma','smahto','snagarkar','ygogula','akumar7','jsharma') and p.userid in(select p.userid  from bugs_activity b ,profiles p where b.added in ('Reviewed') and b.removed in('Waiting - Code Review 2') and b.bug_when>='2017-03-10 19:30:00') and b.bug_id not in (select b.bug_id from bugs_activity b where b.added in ('Open after DOOT','Open after reassigned','New','Reopen closed bugs') and b.removed in('Dependent on other teams','Reassigned','Closed'))and b.bug_id not in (SELECT bug_id FROM bugs_fulltext b where comments like '%This bug was initially created as a clone of%')");
			System.out.println("Inside for BUGS For Hours Change");
			while (rs3.next()) {
				
				
				i3++;
				String id = rs3.getString("bug_id");
				String firstName = rs3.getString("login_name");
				//String lastName = rs.getString("realname");
				System.out.println("BUG ID N2 " + id + ", Login Name: " + firstName);
				data3.put(Integer.toString(i3), new Object[]{id, firstName});
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		
		
		try {
			sendMail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void sendMail() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host","192.168.211.175");
        Session session = Session.getInstance(props, null);
        String from = "graj@yodlee.com";

        Vector<String> aliasHolder = new Vector<String>();
       // aliasHolder.add("akumari@yodlee.com");
       /* aliasHolder.add("shanjra@yodlee.com");
        aliasHolder.add("stummara@yodlee.com");
        aliasHolder.add("aupadhyay@yodlee.com");
        aliasHolder.add("pparthasarathy@yodlee.com");*/
        
        //aliasHolder.add("pparthasarathy@yodlee.com");
        aliasHolder.add("graj@yodlee.com");
       // aliasHolder.add("akumar7@yodlee.com");
      //  aliasHolder.add("smahto@yodlee.com");
        
  //     aliasHolder.add("snagarkar@yodlee.com");
     //   aliasHolder.add("amishra2@yodlee.com");
        
        
        Vector<String> aliasHolderCC = new Vector<String>();
        //aliasHolderCC.add("pparthasarathy@yodlee.com");
      // aliasHolderCC.add("bsingh1@yodlee.com");
       //aliasHolderCC.add("gnattu@yodlee.com");
       // aliasHolderCC.add("akumar7@yodlee.com");
      //aliasHolderCC.add("snagarkar@yodlee.com");
    

        InternetAddress fromAddress = null;
        MimeMessage msg = new MimeMessage(session);
        for (int i = 0; i < aliasHolder.size(); i++) {
              System.out.println("2^^^^^^^aliasHolder:"+aliasHolder.elementAt(i));
              String add = aliasHolder.get(i);
              InternetAddress to = new InternetAddress(add);
              msg.addRecipient(Message.RecipientType.TO, to);
        }
        for (int i = 0; i < aliasHolderCC.size(); i++) {
              System.out.println("2^^^^^^^aliasHolderCC:"+aliasHolderCC.elementAt(i));
              String add = aliasHolderCC.get(i);
              InternetAddress cc = new InternetAddress(add);
              msg.addRecipient(Message.RecipientType.CC, cc);
        }

        try {
              msg.setFrom();
              java.util.Date date=new java.util.Date();
              fromAddress = new InternetAddress(from);
              msg.setFrom(fromAddress);
              //msg.addRecipient(Message.RecipientType.CC, to);
              
              msg.setSubject("For Testing Purpose "+date);
              //msg.setSentDate(new Date());
              msg.setText("Attachment");
              /*
              * ************************Test for
              * attachment*************************
              */
             String fileAttachment = "D://TCR-DATA.xlsx";
             String fileAttachment2 = "D://FCR-DATA.xlsx";
              // create the message part
              String dt1= new String();
              int jt=0;
              Set<String> keyset1 = data2.keySet();
              for (String key : keyset1) 
              {
            	  
            	  if(jt==0)
            	  {
            		  jt++;
            		  continue;
            		 
            	  }
            	  jt++;
            	  dt1 = dt1+"<tr>";
              

                  Object[] objArr = data2.get(key);

                  for (Object obj : objArr) 
                  {
                     
                      if (obj instanceof String) 
                      {
                    	 dt1=dt1+ "<td>"+(String) obj+"</td>";
                      }
            
                  }
                  dt1 = dt1 + "</tr>";
              }
              
              
              
              
              String dt2= new String();
              int jt1=0;
              Set<String> keyset2 = data.keySet();
              for (String key : keyset2) 
              {
            	  
            	  if(jt1==0)
            	  {
            		  jt1++;
            		  continue;
            		 
            	  }
            	  jt1++;
            	  dt2 = dt2+"<tr>";
              

                  Object[] objArr = data.get(key);

                  for (Object obj : objArr) 
                  {
                     
                      if (obj instanceof String) 
                      {
                    	 dt2=dt2+ "<td>"+(String) obj+"</td>";
                      }
            
                  }
                  dt2 = dt2 + "</tr>";
              }
              
              if(jt1==1)
              {
            	  dt2 = "<tr><td>Congrats</td><td>NO Process Gaps in this Case</td></tr>";
              }
              
              
              
              String dt3= new String();
              int jt2=0;
              Set<String> keyset3 = data1.keySet();
              for (String key : keyset3) 
              {
            	  
            	  if(jt2==0)
            	  {
            		  jt2++;
            		  continue;
            		 
            	  }
            	  jt2++;
            	  dt3 = dt3+"<tr>";
              

                  Object[] objArr = data1.get(key);

                  for (Object obj : objArr) 
                  {
                     
                      if (obj instanceof String) 
                      {
                    	 dt3=dt3+ "<td>"+(String) obj+"</td>";
                      }
            
                  }
                  dt3 = dt3 + "</tr>";
              }
              
              if(jt2==1)
              {
            	  dt3 = "<tr><td>Congrats</td><td>NO Process Gaps in this Case</td></tr>";
              }
              
              
              
              
              String dt4= new String();
              int jt3=0;
              Set<String> keyset4 = data3.keySet();
              for (String key : keyset4) 
              {
            	  
            	  if(jt3==0)
            	  {
            		  jt3++;
            		  continue;
            		 
            	  }
            	  jt3++;
            	  dt4 = dt4+"<tr>";
              

                  Object[] objArr = data3.get(key);

                  for (Object obj : objArr) 
                  {
                     
                      if (obj instanceof String) 
                      {
                    	 dt4=dt4+ "<td>"+(String) obj+"</td>";
                      }
            
                  }
                  dt4 = dt4 + "</tr>";
              }
              
              if(jt3==1)
              {
            	  dt4 = "<tr><td>Congrats</td><td>NO Process Gaps in this Case</td></tr>";
              }
              
              
              
              MimeBodyPart messageBodyPart = new MimeBodyPart();
              MimeBodyPart attachmentBodyPart = new MimeBodyPart();
              MimeBodyPart attachmentBodyPart2 = new MimeBodyPart();

             String bodytext = "Hi,<br>";
           bodytext = bodytext +"<br>";
              bodytext = bodytext
            		  + "Please find Attachnment from previous 2 days Reviewer's Data  "+date
                      + "<br>"+"</Font><br><br><br>Thanks<br>Gulshan Raj"+
            		  "<br><br><br><br>"+
                      
						"<html>"+
						
"<style type='text/css'>"+
					    "table td { border-collapse: collapse; }"+
					    ".msoFix { mso-table-lspace:-1pt; mso-table-rspace:-1pt; }"+
					  "</style>"+	
					    
			/*"divv.horizontalgap {"+
							  "float: left;"+
							  "overflow: hidden;"+
							  "height: 1px;"+
							  "width: 0px;"+
							"}"+*/

"<div style='max-width:720px !important;'>"+

"<head>BUGS REVIEWED &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; WFS NOT PROPERlY CHANGED&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;COMPLEXITY CHANGE&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HOURS CHANGED</head>"+
"<table class='msoFix' border='50' border-top-width: 50px cellpadding='2' border-collapse:separate  border-spacing:50px 50px  align='left'>"+
"<tr><b>"+
"<td colspan='1' bgcolor='green'><center>NO OF BUGS</center></td>"+
"<td colspan='1' bgcolor='green'><center>REVIEWER</center></td>"+
"</tr>"+
dt1+
"</table>"+


/*"<div class='horizontalgap' style='width:10px'></div>"+*/


"<table class='msoFix' border-collapse:separate  border-spacing:50px 50px border-top-width: 50px  border='50' cellpadding='2' align='left'>"+
"<tr><b>"+
"<td colspan='1' bgcolor='green'><center>BUG-ID</center></td>"+
"<td colspan='1' bgcolor='green'><center>DEVELOPER</center></td>"+
dt2+
"</table>"+


"<table class='msoFix' border='50' cellpadding='2' align='left'>"+
"<tr><b>"+
"<td colspan='1' bgcolor='green'><center>&nbsp;&nbsp;&nbsp;&nbsp;BUG - ID&nbsp;&nbsp;&nbsp;&nbsp;</center></td>"+
"<td colspan='1' bgcolor='green'><center>REVIEWER</center></td>"+
dt3+
"</table>"+


/*"<div class='horizontalgap' style='width:10px'></div>"+*/

"<table class='msoFix'  border='50' cellpadding='2' align='left'>"+
"<tr><b>"+
"<td colspan='1' bgcolor='green'><center>BUG-ID</center></td>"+
"<td colspan='1' bgcolor='green'><center>DEVELOPER</center></td>"+
dt4+
"</table></div></html>";   
              
              
              
              
              

             messageBodyPart.setContent(bodytext , "text/html");

              // Part two is attachment
              DataSource source = new FileDataSource(fileAttachment);
             attachmentBodyPart.setDataHandler(new DataHandler(source));
             attachmentBodyPart.setFileName("TCR-SHEET.xlsx");
             
             DataSource source2 = new FileDataSource(fileAttachment2);
             attachmentBodyPart2.setDataHandler(new DataHandler(source2));
             attachmentBodyPart2.setFileName("FCR-SHEET.xlsx");

              // Put parts in message
              Multipart multipart = new MimeMultipart();
              multipart.addBodyPart(messageBodyPart);
              multipart.addBodyPart(attachmentBodyPart);
              multipart.addBodyPart(attachmentBodyPart2);
              msg.setContent(multipart);
              /* ******************************************************************** */
              Transport.send(msg);

              System.out.println("2^^^^^^^fileAttachment "+fileNm);
              System.out.println("2^^^^^^^bodytext  "+bodytext);
        } catch (MessagingException mex) {
              System.out.println("2^^^^^^^^^^^^^^^^^^^send failed, exception: "+ mex);
        }
  }

	

}
