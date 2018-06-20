/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author duraip
 */
public class Effort_Tracker_FXMController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label etluname;
    @FXML
    private Label etlpwd;
    @FXML
    private TextField ettfuname;
    @FXML
    private Label etltname;
    @FXML
    private ComboBox<String> etcbtname;
    @FXML
    private Label etlfc;
    @FXML
    private CheckBox etckbehrs;
    @FXML
    private CheckBox etckbffhrs;
    @FXML
    private CheckBox etckball;
    @FXML
    private TextArea etopta;
    @FXML
    private Button etebutton;
    @FXML
    private Button etfilpath;
    @FXML
    private TextField ettffilpath;
    @FXML
    private PasswordField ettfpwd;
    @FXML
    private Button taclear;
    @FXML
    private Label etsltname;
    @FXML
    private Button etsbad;
    @FXML
    private RadioButton etsrbadd;
    @FXML
    private ToggleGroup Sel1;
    @FXML
    private RadioButton etsrbdelete;
    @FXML
    private Label etsleid;
    @FXML
    private TextField etstfeid;
    @FXML
    private Label etslpname;
    @FXML
    private Label etslclev;
    @FXML
    private TextField etstfpname;
    @FXML
    private TextField etstfclev;
    @FXML
    private Button etsbgaccess;
    @FXML
    private Label etslupuname;
    @FXML
    private Label etslcpwd;
    @FXML
    private Label etslnpwd;
    @FXML
    private Label etslconpwd;
    @FXML
    private Button etsbup;
    @FXML
    private TextField etsrfuname;
    @FXML
    private PasswordField etspcpwd;
    @FXML
    private Label etslup;
    @FXML
    private TextField etstftname;
    @FXML
    private PasswordField etspnpwd;
    @FXML
    private PasswordField etspconpwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        updatedate();
        etfilpath.setDisable(true);
        ettffilpath.setEditable(false);
    }    

    @FXML
    private void Extract(ActionEvent event) 
    {
        if((ettfuname.getText().equals("")||ettfuname.getText().equals(" ")||ettfuname.getText().equals(null))||(ettfpwd.getText().equals("")||ettfpwd.getText().equals(" ")||ettfpwd.getText().equals(null)))
        {
            JOptionPane.showMessageDialog(null,"You havent entered your User Name and Password...!!!\nPlease enter those credentials and then proceed ...","Error",JOptionPane.ERROR_MESSAGE);             
        }
        else
        {
            int i=0;
            int insel=getSelected();
            String uname=ettfuname.getText();
            String pword=ettfpwd.getText();
            String stable=etcbtname.getValue();
            String ddqry="select count(distinct date) as date from "+stable;
            String dqry="select distinct(date) as dates from "+stable;
            String eqry1="select enterprise_id as eid,actual_effort as effort from eid empid,"+stable+" stab where empid.enterprise_id=stab.enterprise_id and stab.date='";
            String eqry2="' and empid.enterprise_id='";
            String eqry3="'";
            String feqry="";
            String ieqry="select enterprise_id as empid from eid";
            
            if(validate(uname,pword))
            {
                etfilpath.setDisable(false);
                ettffilpath.setEditable(true);
                try
                {
                    
                    Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:\\javaprg\\Pratice\\Effort_Tracker\\Effort_Tracker_Backup.accdb");
                    Statement ddsmt=con.createStatement();
                    Statement dsmt=con.createStatement();
                    Statement esmt=con.createStatement();
                    Statement iesmt=con.createStatement();
                    ResultSet ddrs=ddsmt.executeQuery(ddqry);
                    ResultSet drs=dsmt.executeQuery(dqry);
                    ResultSet ers=null;
                    ResultSet iers=iesmt.executeQuery(ieqry);
                    //ers=esmt.executeQuery(feqry);
                    float sum=0;
                    
                    int count=0;
                    
                    while(ddrs.next())
                    {
                        count=Integer.parseInt(ddrs.getString("date"));
                    }
                    
                    String ddate[]=new String[count];
                    while(drs.next())
                    {
                        ddate[i]=new String();
                        ddate[i]=drs.getString("dates");
                        i++;
                    }
                    etopta.appendText("\nDefaulters\n");
                    etopta.appendText("\nEnterprise ID\t\tHours Filled");
                    while(iers.next())
                    {
                         
                         for(i=0;i<ddate.length;i++)
                         {
                             feqry=eqry1+ddate[i]+eqry2+iers.getString("empid")+eqry3;
                             ers=esmt.executeQuery(feqry);
                             while(ers.next())
                             {
                                 sum+=ers.getFloat("effort");
                                 //etopta.appendText("\n"+iers.getString("empid")+"\t-\t"+sum);
                             }
                             //etopta.appendText(""+i+"\t"+ddate[i]+"\n");
                         }
                         if(insel!=100)
                         {
                             if(sum<insel)
                             {
                                 etopta.appendText("\n"+iers.getString("empid")+"\t-\t"+sum);
                             }
                         }
                         else
                         {
                             etopta.appendText("\n"+iers.getString("empid")+"\t-\t"+sum);
                         }
                         sum=0;
                    }
//                    etopta.appendText("Output\n");
//                    for(i=0;i<ddate.length;i++)
//                    {
//                        etopta.appendText(""+i+"\t"+ddate[i]+"\n");
//                    }
                    drs.close();
                    ddrs.close();
                    ers.close();
                    iers.close();
                    dsmt.close();
                    ddsmt.close();
                    iesmt.close();
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println("Caught in exception inside Extract method\nException is :\t"+e+"\nStack Trace...\n");
                    e.printStackTrace();
                }
                
            }
        }
    }

    @FXML
    private void SelectFile(ActionEvent event) 
    {
        try
        {
            
            FileChooser fc=new FileChooser();
            File selectedfile= fc.showSaveDialog(null);
            //String filname=filname.replaceAll("\\\\","\\\\\\\\");
            ettffilpath.setText(selectedfile.getPath());
        }
        catch(Exception e)
        {
            System.out.println("Caught in Exception while using File Chooser and the Exception is\n"+e);
        }    
        if(!(ettffilpath.getText().equals("")))
        {
            String fname=ettffilpath.getText();
            if(!ettffilpath.getText().equals(""))
            {
                if((""+ettffilpath.getText().charAt(ettffilpath.getText().length()-4)).equals("."))
                {
                     //if(tffnam.getText().substring((tffnam.getText().length()-(tffnam.getText().length()-4)),(tffnam.getText().length()+1)).equals(".xls"))
                     if((ettffilpath.getText().substring(((ettffilpath.getText().length())-4),(ettffilpath.getText().length())).equalsIgnoreCase(".csv"))||(ettffilpath.getText().substring(((ettffilpath.getText().length())-4),(ettffilpath.getText().length())).equalsIgnoreCase(".txt")))
                     {
                         write(fname,etopta.getText());
                     }
                     else
                     {
                         JOptionPane.showMessageDialog(null,"You can Save the contents in an CSV (or) txt file\nPlease choose a CSV (or) txt file and then try again...\n","Warning",JOptionPane.WARNING_MESSAGE);
                     }
                }
                else
                {
                    write(fname+".csv",etopta.getText());
                }
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Sorry,You haven't entered the file name...","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void TextClear(ActionEvent event) 
    {
        etopta.setText("");
    }

    @FXML
    private void Add_Delete(ActionEvent event) 
    {
        String tname=etstftname.getText();
        System.out.println("\nTable Name : \t"+tname);
        
        if(!(tname.equals("")||tname.equals(" ")||tname.equals(null)))
        {
            if((etsrbadd.getToggleGroup().getSelectedToggle().toString().substring((etsrbadd.getToggleGroup().getSelectedToggle().toString().indexOf('\'', 0)+1),(etsrbadd.getToggleGroup().getSelectedToggle().toString().lastIndexOf('\'')))).equals("Add"))
            {
                String taqry="insert into tables (table_names) values('"+tname+"')";
                if(present(tname))
                {
                     JOptionPane.showMessageDialog(null,"Table Name is Already present in Database\nPlease choose a different name and try adding...","Error",JOptionPane.ERROR_MESSAGE);             
                } 
                else
                {
                     try
                    {
                         Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:\\javaprg\\Pratice\\Effort_Tracker\\Effort_Tracker_Backup.accdb");
                         PreparedStatement taps=con.prepareStatement(taqry);
                         taps.executeUpdate();
                         updatedate();
                         JOptionPane.showMessageDialog(null,"Successfully Added "+tname+" into the data base");
                         //Statement tasmt=con.createStatement();
                         //ResultSet tars=tasmt.executeQuery(taqry+etstftname.getText()+"'");
                         taps.close();
                         con.close();
                    }
                    catch(Exception e)
                    {
                         System.out.println("Caught in exception inside Add_Delete Method \nAnd the exception is :\t"+e);
                         e.printStackTrace();
                    }
                }
            }
            if((etsrbadd.getToggleGroup().getSelectedToggle().toString().substring((etsrbadd.getToggleGroup().getSelectedToggle().toString().indexOf('\'', 0)+1),(etsrbadd.getToggleGroup().getSelectedToggle().toString().lastIndexOf('\'')))).equals("Delete"))
            {
                String taqry="delete from tables where table_names='"+tname+"'";
                
                if(!present(tname))
                {
                     JOptionPane.showMessageDialog(null,"Table doesn't exist so you can't delete this...","Error",JOptionPane.ERROR_MESSAGE);             
                } 
                else
                {
                     try
                    {
                         Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:\\javaprg\\Pratice\\Effort_Tracker\\Effort_Tracker_Backup.accdb");
                         PreparedStatement taps=con.prepareStatement(taqry);
                         taps.executeUpdate();
                         updatedate();
                         JOptionPane.showMessageDialog(null,"Successfully Deleted "+tname+" into the data base");
                         //Statement tasmt=con.createStatement();
                         //ResultSet tars=tasmt.executeQuery(taqry+etstftname.getText()+"'");
                         taps.close();
                         con.close();
                    }
                    catch(Exception e)
                    {
                         System.out.println("Caught in exception inside Add_Delete Method inside delete section \nAnd the exception is :\t"+e);
                         e.printStackTrace();
                    }
                }
            }
        }
        else
        {
             JOptionPane.showMessageDialog(null,"Table Name field is Blank\nPlease enter the name of the table and then proceed...","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    private void GrantAccess(ActionEvent event) 
    {
        String gaeid=etstfeid.getText();
        String gatname=etstftname.getText();    
        String gapname=etstfpname.getText();
        String gaclevel=etstfclev.getText();
        String gaqry="insert into authorized_users (user_name,password,team_name,project,career_level) values ('"+gaeid+"','Password1','"+gatname+"','"+gapname+"','"+gaclevel+"')";
        
        if(((gaeid.equals("")||gaeid.equals(" ")||gaeid.equals(null))||(gatname.equals("")||gatname.equals(" ")||gatname.equals(null))||(gapname.equals("")||gapname.equals(" ")||gapname.equals(null))||(gaclevel.equals("")||gaclevel.equals(" ")||gaclevel.equals(null))))
        {
            JOptionPane.showMessageDialog(null,"One of the below mandatory field is missing\n1.\tEnterprise ID\n2.\tProject\n3.\tTeam\n4.\tCareer Level\nEnter all required fields and try again...","Error",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(!vuser(gaeid))
            {
                try
                {
                     Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:\\javaprg\\Pratice\\Effort_Tracker\\Effort_Tracker_Backup.accdb");
                     PreparedStatement taps=con.prepareStatement(gaqry);
                     taps.executeUpdate();
                     JOptionPane.showMessageDialog(null,"Access granted successfully for "+gaeid);
                     
                     taps.close();
                     con.close();
                }
                catch(Exception e)
                {
                    System.out.println("Caught in Exception inside GrantAccess Method\nAnd the Exception is :\t"+e+"\n");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"The User Already has access","Warning",JOptionPane.WARNING_MESSAGE);
            }            
        }
    }

    @FXML
    private void Update_Password(ActionEvent event) 
    {
        String cpwd=etspcpwd.getText();
        String npwd=etspnpwd.getText();
        String conpwd=etspconpwd.getText();
        String eid=etsrfuname.getText();
        String upsql="update authorized_users set password='"+npwd+"' where user_name='"+eid+"'";
        
        if((cpwd.equals("")||cpwd.equals(" ")||cpwd.equals(null))||(npwd.equals("")||npwd.equals(" ")||npwd.equals(null))||(conpwd.equals("")||conpwd.equals(" ")||conpwd.equals(null))||(eid.equals("")||eid.equals(" ")||eid.equals(null)))
        {
            JOptionPane.showMessageDialog(null,"One of the following Mandatory field is left blank\n1.\tUser Name\n2.\tCurrent Password\n3.\tNew Password\n4.\tConfirmPassword\n","Error",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            try
            {
                if(!cpwd.equals(npwd))
                {
                    if(npwd.equals(conpwd))
                    {
                        if(validate(eid,cpwd))
                        {
                            try
                            {
                                Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:\\javaprg\\Pratice\\Effort_Tracker\\Effort_Tracker_Backup.accdb");
                                PreparedStatement uppsmt=con.prepareStatement(upsql);
                                uppsmt.executeUpdate();
                                JOptionPane.showMessageDialog(null,"Passwords Updated Successfully...");
                                uppsmt.close();
                                con.close();
                            }
                            catch(Exception e)
                            {
                                System.out.println("Caught in Exception inside Update Password Method\nAnd the Exception is :\t"+e);
                                e.printStackTrace();
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Passwords do not match\nPlease try again...","Warning",JOptionPane.WARNING_MESSAGE);
                        etspnpwd.setText("");
                        etspconpwd.setText("");
                        
                    }
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Your Current Password and New Password are same\nPlease choose a New Password to proceed...","Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(Exception e)
            {
                System.out.println("\n Caught in Exception inside Update_Password Method\nAnd the exception is :\t"+e);
                e.printStackTrace();
            }
        }
    }
    
    
    @FXML
    public static boolean validate(String uname,String pword)
    {
        String username="";
        String pwd="";
        int found=0;
        boolean output=false;
        String vqry="select user_name as uname,password as pwd from authorized_users";
        
        try
        {
            Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:\\javaprg\\Pratice\\Effort_Tracker\\Effort_Tracker_Backup.accdb");
            Statement vsmt=con.createStatement();
            ResultSet vrs=vsmt.executeQuery(vqry);
            while(vrs.next())
            {
                username=vrs.getString("user_name");
                pwd=vrs.getString("password");
                if(username.equals(uname)&&pwd.equals(pword))
                {
                    found=1;
                    break;
                }
            }
            vrs.close();
            vsmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Caught in Exception inside Validate Method and the exception is :\t"+e+"\nStack Trace...\n");
            e.printStackTrace();
        }
        
        
        if((uname.equals(username))&&(pword.equals(pwd)))
        {
            output=true;
        }
        else
        {
            if(!(uname.equals(username))&&(pword.equals(pwd)))
            {
                output=false;
                //JOptionPane.showMessageDialog(null,"Un-Authorized user...","Error",JOptionPane.ERROR_MESSAGE); 
                JOptionPane.showMessageDialog(null,"Invalid User !!!\nSorry you don't have Access...","Error",JOptionPane.ERROR_MESSAGE);             
            }
            else
            {
                if((uname.equals(username))&&!(pword.equals(pwd)))
                {
                    output=false;
                    JOptionPane.showMessageDialog(null,"UserName and Password doesn't match\nPlease check your Password...","Error",JOptionPane.ERROR_MESSAGE);             
                }
                else
                {
                    if(!(uname.equals(username))&&!(pword.equals(pwd)))
                    {
                        output=false;
                        JOptionPane.showMessageDialog(null,"Invalid user!!!\n Sorry you don't have Access...","Error",JOptionPane.ERROR_MESSAGE);             
                    }
                }
            }
        }
           
        return output;
    }
    
    @FXML
    public void updatedate()
    {
        try
        {
            etcbtname.getItems().clear();
            String itqry="select Table_Names as tvalues from tables";
            Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:\\javaprg\\Pratice\\Effort_Tracker\\Effort_Tracker_Backup.accdb");
            Statement itsmt=con.createStatement();
            ResultSet itrs=itsmt.executeQuery(itqry);
            while(itrs.next())
            {
                System.out.print("\n"+itrs.getString("tvalues")+"\n");
                etcbtname.getItems().addAll(""+itrs.getString("tvalues"));
            }
            itrs.close();
            itsmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Caught in exception inside Update Date Method \nException is :\t"+e+"\nStack Trace :\t");
            e.printStackTrace();
        }        
    }
    
    @FXML
    public int getSelected()
    {
        int sel=0;
        if((etckbehrs.isSelected())&&!(etckbffhrs.isSelected())&&!(etckball.isSelected()))
        {
            sel=27;
        }
        else
        {
            if(!(etckbehrs.isSelected())&&(etckbffhrs.isSelected())&&!(etckball.isSelected()))
            {
                sel=45;
            }
            else
            {
                if(!(etckbehrs.isSelected())&&!(etckbffhrs.isSelected())&&(etckball.isSelected()))
                {
                    sel=100;
                }
                else
                {
                     if(!(etckbehrs.isSelected())&&!(etckbffhrs.isSelected())&&!(etckball.isSelected()))
                     {
                         JOptionPane.showMessageDialog(null,"You haven't selected any option !!!\nPlease select any 1 option and then proceed...","Warning",JOptionPane.WARNING_MESSAGE);
                     }
                     else
                     {
                         JOptionPane.showMessageDialog(null,"You are not suppose to select more than 1 of them\nPlease select any one option and then proceed...","Warning",JOptionPane.WARNING_MESSAGE);
                     }
                }    
                
            }
        }
        return sel;
    }
    
     @FXML
     private boolean present(String ttname)
     {
         
         boolean op=true;
         String pqry="select * from tables";
         int s=0;
         try
         {
             Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:\\javaprg\\Pratice\\Effort_Tracker\\Effort_Tracker_Backup.accdb");
             Statement psmt=con.createStatement();
             ResultSet prs=psmt.executeQuery(pqry);
             while(prs.next())
             {
                 if(prs.getString("Table_Names").equals(ttname))
                 {
                     s=1;
                     break;
                 }
             }
             if(s==1)
             {
                 op=true;
             }
             else
             {
                 op=false;
             }
             prs.close();
             psmt.close();
             con.close();
         }
         catch(Exception e)
         {
             System.out.println("Caught in Exception inside Present Method\nAnd the exception is :\t"+e);
             e.printStackTrace();
         }
         return op; 
     }
    
     
    @FXML
    public boolean vuser(String ceid)
    {
        boolean op=true;
        String vuqry="select user_name as uname from authorized_users";
        int f=0;
        try
        {
            Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:\\javaprg\\Pratice\\Effort_Tracker\\Effort_Tracker_Backup.accdb");
            Statement vusmt=con.createStatement();
            ResultSet vurs=vusmt.executeQuery(vuqry);
            while(vurs.next())
            {
                if(vurs.getString("uname").equals(ceid))
                {
                    f=1;
                    break;
                }
            }
            if(f==1)
            {
                op=true;
            }
            else
            {
                op=false;
            }
            
            vurs.close();
            vusmt.close();
            con.close();
            
        }
        catch(Exception e)
        {
            System.out.println("Caught in Exception inside VUSer Method\nAnd the exception is :\t"+e);
            e.printStackTrace();
        }
        return op;
    }
    
    @FXML
    public static void write(String fname,String fcont)
    {
         try
            {
                 FileWriter fileWriter = new FileWriter(fname.replaceAll("\\\\", "/"));
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                 String fcontent=fcont.replaceAll("\t", ",");
                 //String updatedText = fcontent.replaceAll("\n", System.lineSeparator());          
                 //System.out.println(updatedText);
                 //bufferedWriter.write(updatedText);
                 bufferedWriter.write(fcontent);
                 bufferedWriter.close();
                 JOptionPane.showMessageDialog(null,"Successfully writen in to "+fname);
            }
            catch(IOException ex) 
            {
                 JOptionPane.showMessageDialog(null,"Error writing to file '"+fname+ "'","Error",JOptionPane.ERROR_MESSAGE);
                 //System.out.println("Error writing to file '"+fname+ "'");
            }        
    }
    
}
