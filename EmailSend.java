/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author duraip
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.*;

public class EmailSend 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        try
        {
//            String host="smtp.gmail.com";
//            String user="pravin.94.durai@gmail.com";
//            String pass="smpsmn1234";
//            String to="pravin.92.rokz@gmail.com";
//            String from="pravin.94.durai@gmail.com";
//            String subject="Yoo Man finally worked";
//            String messageText="This is your sample Mail";
//            boolean sessionDebug=false;
//            
//            Properties props=System.getProperties();
//            
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", host);
//            props.put("mail.smtp.port", "465");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.required", "true");
            
            
            
            String host="mailhub.lss.emc.com";
            String user="pravin.durai@emc.com";
            String pass="Magmar@012";
            String to="Sandeep.Kumar3@emc.com";//shibu.khan.syeed@accenture.com
            String from="pravin.durai@emc.com";
            String subject="Yoo Man finally worked";
            String messageText="This is again a sample Mail which i am sending from my Net Beans IDE for wishing you Good Morning!\nYoo Sandeep! ...";
            boolean sessionDebug=false;
            
            Properties props=System.getProperties();
            
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "25");
            //props.put("mail.tls.port", "587");
            //props.put("mail.tls.port", "587");
            //props.put("mail.ssl.port", "auto");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            
            
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession=Session.getDefaultInstance(props,null);
            mailSession.setDebug(sessionDebug);
            Message msg=new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress address=(new InternetAddress(to));
            msg.setRecipient(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);
            Transport transport=mailSession.getTransport("smtp");
            transport.connect(host,user,pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Mail Sent Successfully");
            
        }
        catch(Exception e)
        {
            System.out.println("Caught in exception\nAnd the exception is :\t"+e+"\n");
            e.printStackTrace();
        }
    }
    
}
