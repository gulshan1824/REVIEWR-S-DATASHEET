import java.util.Properties;
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

public class SendMailTesting {
	
	static String fileNm = "testing.xlsx";
	public static void main(String args[])
	{
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
        
       // aliasHolder.add("pparthasarathy@yodlee.com");
        aliasHolder.add("graj@yodlee.com");
        aliasHolder.add("akumar7@yodlee.com");
        aliasHolder.add("smahto@yodlee.com");
        
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
             String fileAttachment = "D://testing.xlsx";
              // create the message part
              MimeBodyPart messageBodyPart = new MimeBodyPart();
              MimeBodyPart attachmentBodyPart = new MimeBodyPart();

             String bodytext = "Hi,<br>";
           bodytext = bodytext +"<br>";
              bodytext = bodytext
            		  + "Please find Attachnment from last week Reviewer's Data  "+date
                      + "<br>"+"</Font><br><br><br>Thanks<br>Gulshan Raj";                               

             messageBodyPart.setContent(bodytext , "text/html");

              // Part two is attachment
              DataSource source = new FileDataSource(fileAttachment);
             attachmentBodyPart.setDataHandler(new DataHandler(source));
             attachmentBodyPart.setFileName("Reviewers_Data.xlsx");

              // Put parts in message
              Multipart multipart = new MimeMultipart();
              multipart.addBodyPart(messageBodyPart);
              multipart.addBodyPart(attachmentBodyPart);
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
