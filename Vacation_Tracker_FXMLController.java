/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author duraip
 */
public class Vacation_Tracker_FXMLController implements Initializable 
{

    @FXML
    private TabPane TabbedPane;
    @FXML
    private Tab TAddNewEntry;
    @FXML
    private AnchorPane TTAddNewEntry;
    @FXML
    private Label lab1;
    @FXML
    private TextField Atf1;
    @FXML
    private Label lab2;
    @FXML
    private TextField Atf2;
    @FXML
    private Label lab3;
    @FXML
    private Label lab4;
    @FXML
    private Label lab5;
    @FXML
    private Button BAddNewEntry;
    @FXML
    private Label AAlab6;
    @FXML
    private PasswordField Apwd;
    @FXML
    private ComboBox<String> ANcb1;
    @FXML
    private ComboBox<String> ANcb2;
    @FXML
    private ComboBox<String> ANcb3;
    @FXML
    private ComboBox<String> rdecb;
    @FXML
    private Button rdub;
    @FXML
    private Button rdbd;
    @FXML
    private Tab TSearch;
    @FXML
    private AnchorPane TTSearch;
    @FXML
    private TextArea Sta;
    @FXML
    private PasswordField Spwd;
    @FXML
    private Label Salab;
    @FXML
    private ComboBox<String> Scb1;
    @FXML
    private ComboBox<String> Scb2;
    @FXML
    private ComboBox<String> Scb3;
    @FXML
    private Label Stnlab;
    @FXML
    private Label Spnlab;
    @FXML
    private Label Sptlab;
    @FXML
    private Button Sbtn;
    @FXML
    private Button Sbtn2;
    @FXML
    private Tab TAddItem;
    @FXML
    private AnchorPane TTAddItem;
    @FXML
    private Label AIlab;
    @FXML
    private PasswordField AIpwd;
    @FXML
    private Label AIcnt;
    @FXML
    private TextField AItf1;
    @FXML
    private Button AIAddTeamName;
    @FXML
    private RadioButton rbapn;
    @FXML
    private ToggleGroup AddItem;
    @FXML
    private RadioButton rbatn;
    @FXML
    private RadioButton rbat;
    @FXML
    private RadioButton rbdpn;
    @FXML
    private RadioButton rbdtn;
    @FXML
    private RadioButton rbdt;
    @FXML
    private Tab TUpdateVacationInfo;
    @FXML
    private AnchorPane TTUpdateVacationInfo;
    @FXML
    private Label UIlaid;
    @FXML
    private Label UIltname;
    @FXML
    private Label UIvdate;
    @FXML
    private DatePicker UIdpic1;
    @FXML
    private ComboBox<String> UIcb2;
    @FXML
    private ComboBox<String> UIcb1;
    @FXML
    private Label UItype;
    @FXML
    private Button BUpdate;
    @FXML
    private Label UIlvedat;
    @FXML
    private DatePicker UIvedatdpic;
    @FXML
    private ComboBox<String> uiecb;
    @FXML
    private ListView<String> uilv;
    @FXML
    private Tab TRandomFetch;
    @FXML
    private AnchorPane TTRandomFetch;
    @FXML
    private Button RFFetchData;
    @FXML
    private Button RFExportToFile;
    @FXML
    private Button RFClear;
    @FXML
    private ComboBox<String> RFcbtname;
    @FXML
    private ComboBox<String> RFcbpname;
    @FXML
    private ComboBox<String> RFcbrtype;
    @FXML
    private ComboBox<String> RFcbtype;
    @FXML
    private DatePicker RFsdpic;
    @FXML
    private Label RFlaid;
    @FXML
    private Label RFlsdate;
    @FXML
    private Label RFltname;
    @FXML
    private Label RFlpname;
    @FXML
    private Label RFlrtype;
    @FXML
    private Label RFltype;
    @FXML
    private Label RFltabname;
    @FXML
    private Label RDlaid;
    @FXML
    private PasswordField RFpwd;
    @FXML
    private TextField RFtfaid;
    @FXML
    private TextArea RFta;
    @FXML
    private TextField RFtffname;
    @FXML
    private DatePicker RFedpic;
    @FXML
    private ComboBox<String> RFcbtabname;
    @FXML
    private Button bfname;
     @FXML
    public static String sataid="";
    @FXML
    private static String sactcmd="";
    @FXML
    private String spname="";
    @FXML 
    private String stname="";
    @FXML
    private String srtype="";
    @FXML
    private static String AIsitf;
    @FXML
    private String staid="";
    @FXML
    private String steid="";
    @FXML
    private static String stabname="";
    @FXML
    private String sopt="";
    @FXML
    private String smday1="";
    @FXML
    private String smnum1="";
    @FXML
    private String smday2="";
    @FXML
    private String smnum2="";
    @FXML
    ObservableList<String> empname =FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        bfname.setDisable(true);
        RFtffname.setEditable(false);
        ANcb1.getItems().addAll(" ");
        ANcb2.getItems().addAll(" ");
        ANcb3.getItems().addAll(" ");
        Scb1.getItems().addAll(" ");
        Scb2.getItems().addAll(" ");
        Scb3.getItems().addAll(" ");
        UIcb1.getItems().addAll(" ");
        UIcb1.getItems().addAll("Vacation");
        UIcb1.getItems().addAll("Working");
        UIcb1.getItems().addAll("Illness");
        UIcb1.getItems().addAll("Training");
        UIcb2.getItems().addAll(" ");
        RFcbtabname.getItems().addAll(" ");
        RFcbtname.getItems().addAll(" ");
        RFcbpname.getItems().addAll(" ");
        RFcbrtype.getItems().addAll(" ");
        RFcbtype.getItems().addAll(" ");
        RFcbtype.getItems().addAll("Vacation");
        RFcbtype.getItems().addAll("Working");
        RFcbtype.getItems().addAll("Illness");
        RFcbtype.getItems().addAll("Training");
        
        uiecb.getItems().addAll(" ");
        
        try
        {          
            
            //Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
            //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
            Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
            Statement st=con.createStatement();
            Statement st2=con.createStatement();
            Statement st3=con.createStatement();
            Statement sst=con.createStatement();
            Statement rdesst=con.createStatement();
            String sql="select ProjectName from pravin1.Project";
            String sql2="select TeamName from pravin1.Teams";
            String sql3="select distinct(ResourceType) from pravin1.Vacation_Tracker";
            String ssql="select * from pravin1.TableNames";
            String rdessql="select EnterpriseID from pravin1.Vacation_Tracker";
            ResultSet rs=st.executeQuery(sql);
            ResultSet rs2=st2.executeQuery(sql2);
            ResultSet rs3=st3.executeQuery(sql3);
            ResultSet srs=sst.executeQuery(ssql);
            ResultSet rdesrs=rdesst.executeQuery(rdessql);
            rdecb.getItems().addAll(" ");
            while(rdesrs.next())
            {
                rdecb.getItems().addAll(""+rdesrs.getString("EnterpriseID"));
                uiecb.getItems().addAll(""+rdesrs.getString("EnterpriseID"));
            }
            rdecb.getItems().addAll("Add New Entry");
            while(rs.next())
            {
                ANcb1.getItems().addAll(""+rs.getString("ProjectName"));
                Scb2.getItems().addAll(""+rs.getString("ProjectName"));
                //UIcb2.getItems().addAll(srs.getString("TableName"));
                RFcbpname.getItems().addAll(""+rs.getString("ProjectName"));
            }
            while(rs2.next())
            {
                ANcb2.getItems().addAll(""+rs2.getString("TeamName"));
                Scb1.getItems().addAll(""+rs2.getString("TeamName"));
                RFcbtname.getItems().addAll(""+rs2.getString("TeamName"));
            }
            while(rs3.next())
            {
                ANcb3.getItems().addAll(""+rs3.getString("ResourceType"));
                Scb3.getItems().addAll(""+rs3.getString("ResourceType"));
                RFcbrtype.getItems().addAll(""+rs3.getString("ResourceType"));
            }
            while(srs.next())
            {
                 UIcb2.getItems().addAll(""+srs.getString("TableName"));
                 RFcbtabname.getItems().addAll(""+srs.getString("TableName"));                 
            }

            srs.close();
            rs.close();
            rs2.close();
            rs3.close();
            rdesrs.close();
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Caught in Exception in Adding into Choice Button\t:\t"+e.getMessage(),"warning",JOptionPane.WARNING_MESSAGE);
            System.out.println("Caught in Exception in Adding into Choice Button\t:\t"+e.getMessage());
        }
    }    

    @FXML
    private void BAddNewEntry(ActionEvent event) 
    {
        if(Apwd.getText().equals(""))
        {
            //ta1.appendText("\nPlease enter your Authorization ID...");
            JOptionPane.showMessageDialog(null,"Please Enter your Authorization ID...","warning",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            if(authenticate(1))
            {
                  sactcmd="AddNewEntry";
                  
                  try
                  {
                      if((!(Atf1.getText().equals("")||Atf1.getText().equals("\0 ")))&&(!(Atf2.getText().equals("\0")||Atf2.getText().equals("")))&&(!(ANcb1.getValue().equals("null")||spname.equals(" ")))&&(!(ANcb3.getValue().equals("null")||ANcb3.getValue().equals(" ")))&&(!(ANcb2.getValue().equals("null")||ANcb2.getValue().equals(" "))))
                      {
                                String isql="insert into Vacation_Tracker (AccentureID,EnterpriseID,ProjectName,TeamName,ResourceType) values('"+Atf2.getText()+"','"+Atf1.getText()+"','"+ANcb1.getValue()+"','"+ANcb2.getValue()+"','"+ANcb3.getValue()+"')";           
                                try
                                {
                                    Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                                    //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                                    PreparedStatement ips=con.prepareStatement(isql);
                                    //ResultSet irs=ist.executeQuery(isql);
                                    //ta.append("\n Your Changes has Been SUCCESSFULLY Udated");
                                    ips.executeUpdate();
                                    JOptionPane.showMessageDialog(null,"Successfully Added the Record...");
                                    cbupdate("Employee");
                                    rdecb.setValue("Add New Entry");
                                    record(Apwd.getText());
                                    ips.close();
                                    con.close();
                                }
                                catch(Exception e)
                                {
                                      //JOptionPane.showMessageDialog(null,"Caught in Exception in update Button\t:\t"+e.getMessage());
                                      System.out.println("Caught in Exception while Clicking on Add New Entry\t:\t"+e.getMessage());
                                }
                      }
                      else
                      {
                                JOptionPane.showMessageDialog(null,"ERROR Occured while Adding NEW ENTRY \n1. Accenture ID\n2. Enterprise ID\n3. Project Name\n4. Team Name\n5.Resource Type\n one (or) more than one of these field is BLANK \n PLEASE CHECK...!!!","Error",JOptionPane.ERROR_MESSAGE); 
                                //ta.append("\nUpdate UNSUCCESSFUL ... ERROR Occured....!!!");
                      }
                  }
                  catch(Exception exe)
                  {
                      if((exe.toString().equals("java.lang.NullPointerException")))//&&((ANcb1.getValue().equals('\t'))||(ANcb2.getValue().equals('\t'))||(ANcb3.getValue().equals('\t')))&&(((Atf1.getText().equals(""))||(Atf1.getText().equals("\t")))&&((Atf2.getText().equals(""))||(Atf2.getText().equals("\t")))))
                      {
                          JOptionPane.showMessageDialog(null,"\nOne of the below field is left Blank...\n1.\tProject Name\n2.\tTeam Name\n3.\tResource Type\n","Error",JOptionPane.ERROR_MESSAGE);
                      }
                  }
                  
            }
            else
            {
                  //JOptionPane.showMessageDialog(null,"You are not an AUNTHICATED User....\nSorry you don't have access for Performing this Function...!!!");         
                  JOptionPane.showMessageDialog(null,"You are not an AUNTHICATED User....\nSorry you don't have access for Performing this Function...!!!","Error",JOptionPane.ERROR_MESSAGE);
            } 
        }
    }

    @FXML
    private void RDCboxAction(ActionEvent event) 
    {
        if((rdecb.getValue().equals(" ")))
        {
            Atf1.setText("");
            Atf2.setText("");
            Atf1.setEditable(false);
            Atf2.setEditable(false);
            ANcb1.setValue(" ");
            ANcb2.setValue(" ");
            ANcb3.setValue(" ");   
        }
        else
        {
             if(!(rdecb.getValue().equals("Add New Entry")))
             {
                 String rdepsq="select AccentureID,ProjectName,TeamName,ResourceType from pravin1.Vacation_Tracker where EnterpriseID='"+rdecb.getValue()+"'";
                 Atf1.setEditable(false);
                 Atf2.setEditable(false);
                 try
                 {
                       Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                       Statement rdepst=con.createStatement();
                       ResultSet rdeprs=rdepst.executeQuery(rdepsq);
                       while(rdeprs.next())
                       {
                           Atf1.setText("");
                           Atf2.setText(rdeprs.getString("AccentureID"));
                           ANcb1.setValue(rdeprs.getString("ProjectName"));
                           ANcb2.setValue(rdeprs.getString("TeamName"));
                           ANcb3.setValue(rdeprs.getString("ResourceType"));
                       }
                       
                 }
                 catch(Exception exe)
                 {
                       JOptionPane.showMessageDialog(null,"Caught in Exception When populating the Choice Button in Resource Directory Tab \n Exception is \n"+exe.getMessage(),"warning",JOptionPane.WARNING_MESSAGE);
                 }
             }
             else
             {
                 Atf1.setText("");
                 Atf2.setText("");
                 Atf1.setEditable(true);
                 Atf2.setEditable(true);
                 ANcb1.setValue(" ");
                 ANcb2.setValue(" ");
                 ANcb3.setValue(" ");
             }
        }
    }

    @FXML
    private void RDBUpdate(ActionEvent event) 
    {
        if(Apwd.getText().equals(""))
        {
            //ta1.appendText("\nPlease enter your Authorization ID...");
            JOptionPane.showMessageDialog(null,"Please Enter your Authorization ID...","warning",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            if(authenticate(1))
            {
                  if(!rdecb.getValue().equals("Add New Entry"))
                  {
                      sactcmd="AddNewEntry";
                      String rduq="update Vacation_Tracker set ProjectName='"+ANcb1.getValue()+"', TeamName='"+ANcb2.getValue()+"', ResourceType='"+ANcb3.getValue()+"' where AccentureID='"+Atf2.getText()+"'";
                      try
                      {
                          Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                          PreparedStatement rdps=con.prepareStatement(rduq);
                          rdps.executeUpdate();
                          JOptionPane.showMessageDialog(null,"\nYour Information has been Successfully Updated...\n");
                          rdps.close();
                          con.close();
                      }
                      catch(Exception exc)
                      {
                          JOptionPane.showMessageDialog(null,"Caught in Exception while Clicking Update Button under Resource Directory Tab \n Exception is \n"+exc.getMessage(),"warning",JOptionPane.WARNING_MESSAGE);
                      }                      
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(null,"\nThis is the First time you are going to create an entry....\nYou can't make an Update before creating\n","warning",JOptionPane.WARNING_MESSAGE);
                  }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"You are not an AUNTHICATED User....\nSorry you don't have access for Performing this Function...!!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    private void RDBDelete(ActionEvent event) 
    {
        sactcmd="Delete";
        if(Apwd.getText().equals(""))
         {
           JOptionPane.showMessageDialog(null,"Please Enter your Authorization ID...","warning",JOptionPane.WARNING_MESSAGE);
         }                
         else
         if(authenticate(1))
         {
             if(!Atf2.getText().equals(""))
             {
                 String dq="delete from pravin1.Vacation_Tracker where EnterpriseID='"+rdecb.getValue()+"'";
                 String temp=rdecb.getValue();
                 try
                 {
                     Connection dcon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");//Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                     PreparedStatement dps=dcon.prepareStatement(dq);
                     dps.executeUpdate();
                     JOptionPane.showMessageDialog(null,"Successfully Deleted "+temp+"'s Information...");
                     cbupdate("Employee");
                     rdecb.setValue("Add New Entry");
                 }
                 catch(Exception exe)
                 {
                     JOptionPane.showMessageDialog(null,"Caught in Exception While Deleting Employee Information under Resource Directory","warning",JOptionPane.WARNING_MESSAGE);
                 }
                 
             }
         }
    }

    @FXML
    private void SButton(ActionEvent event) 
    {
         stname=""+Scb1.getValue();
         spname=""+Scb2.getValue();
         srtype=""+Scb3.getValue();
         sactcmd="Search";
         
         if(Spwd.getText().equals(""))
                 JOptionPane.showMessageDialog(null,"Please Enter your Authorization ID...","warning",JOptionPane.WARNING_MESSAGE);
         else
         if(authenticate(2))
         {
           
           String sql=""; 
           if(!(spname.equals(" ")||spname.equals("null"))&&(stname.equals(" ")||stname.equals("null"))&&(srtype.equals(" ")||srtype.equals("null")))
           {
               sql="select * from pravin1.Vacation_Tracker where ProjectName='"+spname+"'";
           }
           else
           if((spname.equals(" ")||spname.equals("null"))&&!(stname.equals(" ")||stname.equals("null"))&&(srtype.equals(" ")||srtype.equals("null")))
           {
               sql="select * from pravin1.Vacation_Tracker where TeamName='"+stname+"'";      
           } 
           else
           if((spname.equals(" ")||spname.equals("null"))&&(stname.equals(" ")||stname.equals("null"))&&!(srtype.equals(" ")||srtype.equals("null")))
           {
              sql="select * from pravin1.Vacation_Tracker where ResourceType='"+srtype+"'"; 
           }
           else
           if(!(spname.equals(" ")||spname.equals("null"))&&!(stname.equals(" ")||stname.equals("null"))&&(srtype.equals(" ")||srtype.equals("null")))
           {
              sql="select * from pravin1.Vacation_Tracker where ProjectName='"+spname+"'and TeamName='"+stname+"'";
           }
           else 
           if(!(spname.equals(" ")||spname.equals("null"))&&(stname.equals(" ")||stname.equals("null"))&&!(srtype.equals(" ")||srtype.equals("null")))
           {
              sql="select * from pravin1.Vacation_Tracker where ProjectName='"+spname+"'and ResourceType='"+srtype+"'";
           }
           else
           if((spname.equals(" ")||spname.equals("null"))&&!(stname.equals(" ")||stname.equals("null"))&&!(srtype.equals(" ")||srtype.equals("null")))
           {
              sql="select * from pravin1.Vacation_Tracker where TeamName='"+stname+"'and ResourceType='"+srtype+"'";    
           }
           else
           if(!(spname.equals(" ")||spname.equals("null"))&&!(stname.equals(" ")||stname.equals("null"))&&!(srtype.equals(" ")||srtype.equals("null")))
           {
              sql="select * from pravin1.Vacation_Tracker where ProjectName='"+spname+"'and ResourceType='"+srtype+"'and TeamName='"+stname+"'";
           }
           else
              JOptionPane.showMessageDialog(null,"You haven't Selected any Search Criteria","Error",JOptionPane.ERROR_MESSAGE);
              //ta.append("\n You haven't Selected any Item");
          
             try
             {
                   Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                   //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                   Statement st=con.createStatement();
                   record(Spwd.getText());
                   ResultSet rs=st.executeQuery(sql);
                   
                   Sta.appendText("\n");
                   while(rs.next())
                   {
                       Sta.appendText("\nAccenture ID:\t"+rs.getInt("AccentureID")+"\n");
                       Sta.appendText("Enterprise ID:\t"+rs.getString("EnterpriseID")+"\n");
                       Sta.appendText("Project Name:\t"+rs.getString("ProjectName")+"\n");
                       Sta.appendText("Team Name:\t"+rs.getString("TeamName")+"\n");
                       //Sta.appendText("Resource Type:\t"+rs.getString("ResourceType")+"\n");
                       Sta.appendText("Resource Type:\t"+rs.getString("ResourceType")+"\n");
                   }
                   stname="";
                   spname="";
                   srtype="";
                   rs.close();
                   con.close();
              }
              catch(Exception e)
              {
                   //JOptionPane.showMessageDialog(null,"Caught in Exception\t:\t"+e.getMessage());
                   System.out.println("Caught in Exception\t:\t"+e.getMessage());
              }
         }
         else
         {
             //JOptionPane.showMessageDialog(null,"You are not an AUNTHICATED User....\nSorry you don't have access for Performing this Function...!!!");        
             JOptionPane.showMessageDialog(null,"You are not an AUNTHICATED User....\nSorry you don't have access for Performing this Function...!!!","Error",JOptionPane.ERROR_MESSAGE);
         }
    }

    @FXML
    private void SCButton(ActionEvent event) 
    {
        Sta.setText(""+"Output");
    }

    @FXML
    private void Sinit(Event event) {
    }

    @FXML
    private void BAIAddDelete(ActionEvent event) 
    {
        //sactcmd="AddProjectName";
         AIsitf=AItf1.getText();
         String sle="";
         String s="";
         if(AIpwd.getText().equals(""))
         {
             JOptionPane.showMessageDialog(null,"Please Enter your Authorization ID...","warning",JOptionPane.WARNING_MESSAGE);
         }                
         else
         {
             if(authenticate(3))
             {
                 if((AItf1.getText().equals(""))||(AItf1.getText().equals(" ")))
                 {
                     JOptionPane.showMessageDialog(null,"\nContent Field can not be left Blank....\n","Warning",JOptionPane.WARNING_MESSAGE);                 
                 }
                 else
                 {
                     sle=""+rbdt.getToggleGroup().getSelectedToggle().toString().substring(rbdt.getToggleGroup().getSelectedToggle().toString().indexOf('\'', 0)+1, rbdt.getToggleGroup().getSelectedToggle().toString().lastIndexOf('\''));                 
                     sactcmd=sle;
                     
                     if(sle.equalsIgnoreCase("Add Project Name"))
                     {
                         s="ProjectName";
                         if(validate(s,AItf1.getText()))
                         {
                              //ta.append(""+sitf);
                              String apnsql="insert into Project (ProjectName) values ('"+AItf1.getText()+"')";            
                              try
                              {
                                    Connection apncon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                                    //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                                    PreparedStatement apnps=apncon.prepareStatement(apnsql);
                                    apnps.executeUpdate();
                                    record(AIpwd.getText()); 
                                    JOptionPane.showMessageDialog(null,"Project Name has been Successfully added...");
                                    //ANcb1.getItems().addAll(AItf1.getText());
                                    //Scb2.getItems().addAll(AItf1.getText());
                                    //RFcbpname.getItems().addAll(AItf1.getText());
                                    //ta.append("\n Your Changes has Been SUCCESSFULLY Udated");
                                    cbupdate("ProjectName");
                                    apncon.commit();
                                    apnps.close();
                                    apncon.close();
                              }   
                              catch(Exception e)
                              {    
                                    //JOptionPane.showMessageDialog(null,"Caught in Exception in Add Table Button\t:\t"+e.getMessage());
                                    System.out.println("Caught in Exception while Adding Project Name\t:\t"+e.getMessage());
                              }
                         }
                         else
                         {
                              JOptionPane.showMessageDialog(null,"The Project Name is already existing...","warning",JOptionPane.WARNING_MESSAGE);   
                         }
                     }
                     if(sle.equalsIgnoreCase("Add Team Name"))
                     {
                         s="TeamName";
                         if(validate(s,AItf1.getText()))
                         {
                              //ta.append(""+sitf);
                              String apnsql="insert into Teams (TeamName) values ('"+AItf1.getText()+"')";            
                              try
                              {
                                    Connection apncon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                                    //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                                    PreparedStatement apnps=apncon.prepareStatement(apnsql);
                                    apnps.executeUpdate();
                                    record(AIpwd.getText()); 
                                    JOptionPane.showMessageDialog(null,"Team Name has been Successfully added...");
                                    //ANcb1.getItems().addAll(AItf1.getText());
                                    //Scb2.getItems().addAll(AItf1.getText());
                                    //RFcbpname.getItems().addAll(AItf1.getText());
                                    //ta.append("\n Your Changes has Been SUCCESSFULLY Udated");
                                    cbupdate("TeamName");
                                    apncon.commit();
                                    apnps.close();
                                    apncon.close();
                              }   
                              catch(Exception e)
                              {    
                                    //JOptionPane.showMessageDialog(null,"Caught in Exception in Add Table Button\t:\t"+e.getMessage());
                                    System.out.println("Caught in Exception while Adding Team Name\t:\t"+e.getMessage());
                              }
                         }
                         else
                         {
                              JOptionPane.showMessageDialog(null,"The Team Name is already existing...","warning",JOptionPane.WARNING_MESSAGE);   
                         }
                     }
                     if(sle.equalsIgnoreCase("Add Table"))
                     {
                         s="TableName";
                         if(validate(s,AItf1.getText()))
                         {
                              //ta.append(""+sitf);
                              String apnsql="insert into TableNames (TableName) values ('"+AItf1.getText()+"')";            
                              try
                              {
                                    Connection apncon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                                    //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                                    PreparedStatement apnps=apncon.prepareStatement(apnsql);
                                    apnps.executeUpdate();
                                    record(AIpwd.getText()); 
                                    JOptionPane.showMessageDialog(null,"Table Name has been Successfully added...");
                                    //ANcb1.getItems().addAll(AItf1.getText());
                                    //Scb2.getItems().addAll(AItf1.getText());
                                    //RFcbpname.getItems().addAll(AItf1.getText());
                                    //ta.append("\n Your Changes has Been SUCCESSFULLY Udated");
                                    cbupdate("TableName");
                                    apncon.commit();
                                    apnps.close();
                                    apncon.close();
                              }   
                              catch(Exception e)
                              {    
                                    //JOptionPane.showMessageDialog(null,"Caught in Exception in Add Table Button\t:\t"+e.getMessage());
                                    System.out.println("Caught in Exception while Adding Table Name\t:\t"+e.getMessage());
                              }
                         }
                         else
                         {
                              JOptionPane.showMessageDialog(null,"The Table Name is already existing...","warning",JOptionPane.WARNING_MESSAGE);   
                         }
                     }
                     if(sle.equalsIgnoreCase("Delete Project Name"))
                     {
                         s="ProjectName";
                         if(!validate(s,AItf1.getText()))
                         {
                              //ta.append(""+sitf);
                              String apnsql="delete from pravin1.Project where ProjectName='"+AItf1.getText()+"'";            
                              try
                              {
                                     Connection apncon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                                    //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                                    PreparedStatement apnps=apncon.prepareStatement(apnsql);
                                    apnps.executeUpdate();
                                    record(AIpwd.getText()); 
                                    JOptionPane.showMessageDialog(null,"Project Name has been Successfully Deleted...");
                                    //ANcb1.getItems().addAll(AItf1.getText());
                                    //Scb2.getItems().addAll(AItf1.getText());
                                    //RFcbpname.getItems().addAll(AItf1.getText());
                                    //ta.append("\n Your Changes has Been SUCCESSFULLY Udated");
                                    cbupdate("ProjectName");
                                    apncon.commit();
                                    apnps.close();
                                    apncon.close();
                              }   
                              catch(Exception e)
                              {    
                                    //JOptionPane.showMessageDialog(null,"Caught in Exception in Add Table Button\t:\t"+e.getMessage());
                                    System.out.println("Caught in Exception while Deleting Project Name\t:\t"+e.getMessage());
                              }
                         }
                         else
                         {
                              JOptionPane.showMessageDialog(null,"The Project Name Doesn't Exist...\n","warning",JOptionPane.WARNING_MESSAGE);   
                         }
                     }
                     if(sle.equalsIgnoreCase("Delete Team Name"))
                     {
                         s="TeamName";
                         if(!validate(s,AItf1.getText()))
                         {
                              //ta.append(""+sitf);
                              String apnsql="delete from pravin1.Teams where TeamName='"+AItf1.getText()+"'";            
                              try
                              {
                                    Connection apncon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                                    //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                                    PreparedStatement apnps=apncon.prepareStatement(apnsql);
                                    apnps.executeUpdate();
                                    record(AIpwd.getText()); 
                                    JOptionPane.showMessageDialog(null,"Team Name has been Successfully Deleted...");
                                    //ANcb1.getItems().addAll(AItf1.getText());
                                    //Scb2.getItems().addAll(AItf1.getText());
                                    //RFcbpname.getItems().addAll(AItf1.getText());
                                    //ta.append("\n Your Changes has Been SUCCESSFULLY Udated");
                                    cbupdate("TeamName");
                                    apncon.commit();
                                    apnps.close();
                                    apncon.close();
                              }   
                              catch(Exception e)
                              {    
                                    //JOptionPane.showMessageDialog(null,"Caught in Exception in Add Table Button\t:\t"+e.getMessage());
                                    System.out.println("Caught in Exception while Deleting Team Name\t:\t"+e.getMessage());
                              }
                         }
                         else
                         {
                              JOptionPane.showMessageDialog(null,"The Team Name Doesn't Exist...\n","warning",JOptionPane.WARNING_MESSAGE);   
                         }
                     }
                     if(sle.equalsIgnoreCase("Delete Table"))
                     {
                         s="TableName";
                         if(!validate(s,AItf1.getText()))
                         {
                              //ta.append(""+sitf);
                              String apnsql="delete from pravin1.TableNames where TableName='"+AItf1.getText()+"'";            
                              try
                              {
                                    Connection apncon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                                    //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                                    PreparedStatement apnps=apncon.prepareStatement(apnsql);
                                    apnps.executeUpdate();
                                    record(AIpwd.getText()); 
                                    JOptionPane.showMessageDialog(null,"Table Name has been Successfully Deleted...");
                                    //ANcb1.getItems().addAll(AItf1.getText());
                                    //Scb2.getItems().addAll(AItf1.getText());
                                    //RFcbpname.getItems().addAll(AItf1.getText());
                                    //ta.append("\n Your Changes has Been SUCCESSFULLY Udated");
                                    cbupdate("TableName");
                                    apncon.commit();
                                    apnps.close();
                                    apncon.close();
                              }   
                              catch(Exception e)
                              {    
                                    //JOptionPane.showMessageDialog(null,"Caught in Exception in Add Table Button\t:\t"+e.getMessage());
                                    System.out.println("Caught in Exception while Deleting Table Name\t:\t"+e.getMessage());
                              }
                         }
                         else
                         {
                              JOptionPane.showMessageDialog(null,"The Table Name Doesn't Exist...\n","warning",JOptionPane.WARNING_MESSAGE);   
                         }
                     }
                 }
             }
             else
             {
                 JOptionPane.showMessageDialog(null,"You are not an AUNTHICATED User....\nSorry you don't have access for Performing this Function...!!!","Error",JOptionPane.ERROR_MESSAGE);                 
             }
         }
    }

    @FXML
    private void BUpdate(ActionEvent event) 
    {
        //((UIdpic1.getValue().plusDays(i).getMonth()).toString()).substring(0, 3);
        int i=0;
        int mnum=UIdpic1.getValue().getMonthValue();
        int flag=0;
        String udsql="";
        String smname="";
        steid=uiecb.getValue();
        //System.out.println("\n\n STEID:\t"+steid);
        stabname=""+UIcb2.getValue();
        sopt=""+UIcb1.getValue();
        smnum1=""+UIdpic1.getValue().getMonthValue();
        smnum2=""+UIvedatdpic.getValue().getMonthValue();
        //smday1=""+UIdpic1.getValue().getDayOfMonth();
        int num;
           if(!(sopt.equals(" ")||sopt.equals("null"))&&!(steid.equals(" ")||steid.equals("null"))&&!(smnum1.equals(" ")||smnum1.equals("null"))&&!(smnum2.equals(" ")||smnum2.equals("null"))&&!(stabname.equals(" ")||stabname.equals("null")))
           {
               //if(present(steid,"1",stabname))
               //{
                  try
                  {        
                      while(!(UIvedatdpic.getValue().equals(UIdpic1.getValue().plusDays(i))))
                      {
                          if((!((UIdpic1.getValue().plusDays(i).getDayOfWeek().toString()).equals("SATURDAY"))&&!((UIdpic1.getValue().plusDays(i).getDayOfWeek().toString()).equals("SUNDAY"))))
                          {
                              num=UIdpic1.getValue().plusDays(i).getDayOfMonth();
                              switch(mnum)
                              {
                                  case 1:
                                      smname="Jan";
                                      break;
                                  case 2:
                                      smname="Feb";
                                      break;
                                  case 3:
                                      smname="Mar";
                                      break;
                                  case 4:
                                      smname="Apr";
                                      break;
                                  case 5:
                                      smname="May";
                                      break;
                                  case 6:
                                      smname="Jun";
                                      break;
                                  case 7:
                                      smname="July";
                                      break;
                                  case 8:
                                      smname="Aug";
                                      break;
                                  case 9:
                                      smname="Sep";
                                      break;
                                  case 10:
                                      smname="Oct";
                                      break;
                                  case 11:
                                      smname="Nov";
                                      break;
                                  case 12:
                                      smname="Dec";
                                      break;
                              }
                              if(num%10==1&&(num<11||num>20))
                                   smday1=""+num+"st"+smname;
                              else
                                   if(num%10==2&&(num<11||num>20))
                                        smday1=""+num+"nd"+smname;
                                   else
                                        if(num%10==3&&(num<11||num>20))
                                            smday1=""+num+"rd"+smname;
                                        else
                                             smday1=""+num+"th"+smname;
        
                             udsql="update "+stabname+" set "+smday1+"='"+sopt+"' where EnterpriseId='"+steid+"'";

                          
                          
                           //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                           Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                           PreparedStatement udps=con.prepareStatement(udsql);
                           flag=1;
                           //ta.append("\n Your Changes has Been SUCCESSFULLY Udated");
                           udps.executeUpdate();
                           uilv.getItems().clear();
                           updateListView();
                           i++;
                           udps.close();
                           con.close();
                         }
                         else
                         {
                            System.out.println("\nWorking");
                            i++;
                            continue;
                         }
                    }
                    if(flag==1)
                    {
                        JOptionPane.showMessageDialog(null,"Your Changes has Been SUCCESSFULLY Udated...");
                    }
                  }
            
                  catch(Exception e)
                  {
                       //JOptionPane.showMessageDialog(null,"Caught in Exception in update Button\t:\t"+e.getMessage());
                       System.out.println("Caught in Exception in update Button\t:\t"+e.getMessage());
                  }
                //}
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Update UNSUCCESSFUL ... ERROR Occured....!!!\n You missed one (or) more of these fields please check the below fields\n1. Enterprise ID\n2. Vacation Starte Date\n3. Vacation End Date\n4. Vacation Type\n5. Table Name\n","Error",JOptionPane.ERROR_MESSAGE); 
                //ta.append("\nenti ID:\t"+steid+"\nMonth num:\t"+smnum+"\nMonth Day:\t"+smday+"\nOption:\t"+sopt);                    for troubleshooting   
            }
    }

    @FXML
    private void UIECB(ActionEvent event) 
    {
        updateListView();
    }

    @FXML
    private void RFBFetchData(ActionEvent event) 
    {
        sactcmd="RandomFetch";
      
         if(RFpwd.getText().equals(""))
                 JOptionPane.showMessageDialog(null,"Please Enter your Authorization ID...","warning",JOptionPane.WARNING_MESSAGE);
         else
         {
             if(authenticate(6))
             {
                 //record(RFpwd.getText());
                 spname=RFcbpname.getValue();
                 stname=RFcbtname.getValue();
                 srtype=RFcbrtype.getValue();
                 stabname=RFcbtabname.getValue();
                 sopt=RFcbtype.getValue();
                 String aid=RFtfaid.getText();
                 String sqlrf="";//="select * from pravin1."+stabname;          // Query that is been used by the Fetch Data
                 String mn="";
                 String dsfx[]=new String[31];   //Data Suffix
                 String gvft[]=new String[32];   //Get Value From Table
                 String sdate="";
                 String edate="";
                 int line=1;
                 int imnum=0;
                 int j=0;
                 int i=0;
                 int vtcc=0;   //Vacation Taken Condition Check
                 int count =0;   
                 int im=0;
                 
                 if(!(sopt.equals("")||sopt.equals(" ")||sopt.equals("\0")||sopt.equals(null)||sopt.equals("null")))
                 {
                     if((!(stabname.equals("")||stabname.equals(" "))||!(stabname.equals("\0"))||!(stabname.equals(null))||!(stabname.equals("null"))))
                     {
                       try
                       {
                         if((aid.equals("")||aid.equals(" ")||aid.equals("\0")||aid.equals(null)||aid.equals("null")))
                         {
                             if(!(spname.equals(" ")||spname.equals(null))&&(stname.equals(null)||stname.equals(" "))&&(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ProjectName='"+spname+"')"; 
                             }
                             else
                             if((spname.equals(" ")||spname.equals(null))&&!(stname.equals(null)||stname.equals(" "))&&(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where TeamName='"+stname+"')";      
                             }  
                             else
                             if((spname.equals(" ")||spname.equals(null))&&(stname.equals(null)||stname.equals(" "))&&!(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ResourceType='"+srtype+"')"; 
                             }
                             else
                             if(!(spname.equals(" ")||spname.equals(null))&&!(stname.equals(null)||stname.equals(" "))&&(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ProjectName='"+spname+"' and TeamName='"+stname+"')"; 
                             }
                             else 
                             if(!(spname.equals(" ")||spname.equals(null))&&(stname.equals(null)||stname.equals(" "))&&!(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ProjectName='"+spname+"' and ResourceType='"+srtype+"')";
                             }
                             else
                             if((spname.equals(" ")||spname.equals(null))&&!(stname.equals(null)||stname.equals(" "))&&!(srtype.equals(null)||srtype.equals(" ")))
                             {   
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where TeamName='"+stname+"' and ResourceType='"+srtype+"')";
                             }
                             else
                             if(!(spname.equals(" ")||spname.equals(null))&&!(stname.equals(null)||stname.equals(" "))&&!(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where TeamName='"+stname+"' and ProjectName='"+spname +"' and ResourceType='"+srtype+"')";
                             }
                             else
                             {
                                 sqlrf="select * from pravin1."+stabname;
                                 //JOptionPane.showMessageDialog(null,"You haven't Selected one (or) more of the below Fields....\n1.\tTeam Name\n2.\tProject Name\n3.\tResource Type\n4.\tType\n5.\tTable Name");
                             }
//                             if(!(sqlrf.equals("")))
//                             {
                                 
//                             }
                         }
                         else
                         {
                           if(!stabname.equals(" "))
                          {
                           if(!present(aid,"1",stabname))
                           {
                               
                           }
                           else
                           {
                             if(!(spname.equals(" ")||spname.equals(null))&&(stname.equals(null)||stname.equals(" "))&&(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ProjectName='"+spname+"') and EnterpriseID='"+aid+"'"; 
                             }
                             else
                             if((spname.equals(" ")||spname.equals(null))&&!(stname.equals(null)||stname.equals(" "))&&(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where TeamName='"+stname+"') and EnterpriseID='"+aid+"'";      
                             }  
                             else
                             if((spname.equals(" ")||spname.equals(null))&&(stname.equals(null)||stname.equals(" "))&&!(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ResourceType='"+srtype+"') and EnterpriseID='"+aid+"'"; 
                             }
                             else
                             if(!(spname.equals(" ")||spname.equals(null))&&!(stname.equals(null)||stname.equals(" "))&&(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ProjectName='"+spname+"' and TeamName='"+stname+"') and EnterpriseID='"+aid+"'"; 
                             }
                             else 
                             if(!(spname.equals(" ")||spname.equals(null))&&(stname.equals(null)||stname.equals(" "))&&!(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ProjectName='"+spname+"' and ResourceType='"+srtype+"') and EnterpriseID='"+aid+"'";
                             }
                             else
                             if((spname.equals(" ")||spname.equals(null))&&!(stname.equals(null)||stname.equals(" "))&&!(srtype.equals(null)||srtype.equals(" ")))
                             {   
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where TeamName='"+stname+"' and ResourceType='"+srtype+"') and EnterpriseID='"+aid+"'";
                             }
                             else
                             if(!(spname.equals(" ")||spname.equals(null))&&!(stname.equals(null)||stname.equals(" "))&&!(srtype.equals(null)||srtype.equals(" ")))
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where TeamName='"+stname+"' and ProjectName='"+spname +"' and ResourceType='"+srtype+"') and EnterpriseID='"+aid+"'";
                             }
                             else
                             {
                                 sqlrf="select * from pravin1."+stabname+" where EnterpriseID='"+aid+"'";
                                 //JOptionPane.showMessageDialog(null,"You haven't Selected one (or) more of the below Fields....\n1.\tTeam Name\n2.\tProject Name\n3.\tResource Type\n4.\tType\n5.\tTable Name");
                             }
                           }
                          }
                         }
                         
                         
                         //RFta.appendText(" \nSuccess :\n 1. sopt\t"+sopt+"\n 2.stabname:\t"+stabname+"\n 3. srtype:\t"+srtype+"\n 4. spname:\t"+spname+"\n 5. stname:\t"+stname+"\n SQLrf :\t \t"+sqlrf);
                         
                     
                         while(i<31)
                         {
                            dsfx[i]=new String();
                            gvft[i]=new String();
                            i++;           
                         }
                         gvft[i]=new String();
                         String mname=RFcbtabname.getValue().substring(0,3);
                         int year=Integer.parseInt(RFcbtabname.getValue().substring((RFcbtabname.getValue().length()-4),RFcbtabname.getValue().length()));
                         switch(mname)
                         {
                             case "Jan":
                               im=1;
                               break;
                             case "Feb":
                                if(year%4==0)
                                  im=13;
                                else
                                  im=2;
                                  break;
                             case "Mar":
                                  im=3;
                                  break;
                             case "Apr":
                                  im=4;
                                  break;
                             case "May":
                                  im=5;
                                  break;
                             case "Jun":
                                  im=6;
                                  break;
                             case "Jul":
                                  im=7;
                                  break;
                             case "Aug":
                                  im=8;
                                  break;
                             case "Sep":
                                  im=9;
                                  break;
                             case "Oct":
                                  im=10;
                                  break;
                             case "Nov":
                                  im=11;
                                  break;
                             case "Dec":
                                  im=12;
                                  break;
                         }
                         switch(im)
                         {
                            case 1:
                                mn="Jan";
                                imnum=31;
                                break;           
                            case 2:
                                mn="Feb";
                                imnum=28;
                                break;
                            case 3:
                                mn="Mar";
                                imnum=31;
                                break;           
                            case 4:
                                mn="Apr";
                                imnum=30;
                                break;
                            case 5:
                                mn="May";
                                imnum=31;
                                break;           
                            case 6:
                                mn="Jun";
                                imnum=30;
                                break;
                            case 7:
                                mn="July";
                                imnum=31;
                                break;           
                            case 8:
                                mn="Aug";
                                imnum=31;
                                break;
                            case 9:
                                mn="Sep";
                                imnum=30;
                                break;           
                            case 10:
                                mn="Oct";
                                imnum=31;
                                break;
                            case 11:
                                mn="Nov";
                                imnum=30;
                                break;           
                            case 12:
                                mn="Dec";
                                imnum=31;
                                break;
                            case 13:
                                mn="Feb";
                                imnum=29;
                                break;
                            default:
                                JOptionPane.showMessageDialog(null,"Month u have selected is not Present (or) You haven't selected any month\t:\t","warning",JOptionPane.WARNING_MESSAGE);
                         }
                         dsfx[0]="1st";
                         dsfx[1]="2nd";
                         dsfx[2]="3rd";
                         dsfx[3]="4th";
                         dsfx[4]="5th";
                         dsfx[5]="6th";
                         dsfx[6]="7th";
                         dsfx[7]="8th";
                         dsfx[8]="9th";
                         dsfx[9]="10th";
                         dsfx[10]="11th";
                         dsfx[11]="12th";
                         dsfx[12]="13th";
                         dsfx[13]="14th";
                         dsfx[14]="15th";
                         dsfx[15]="16th";
                         dsfx[16]="17th";
                         dsfx[17]="18th";
                         dsfx[18]="19th";
                         dsfx[19]="20th";
                         dsfx[20]="21st";
                         dsfx[21]="22nd";
                         dsfx[22]="23rd";
                         dsfx[23]="24th";
                         dsfx[24]="25th";
                         dsfx[25]="26th";
                         dsfx[26]="27th";
                         dsfx[27]="28th";
                         dsfx[28]="29th";
                         dsfx[29]="30th";
                         dsfx[30]="31st";
                       }
                       catch(Exception eee)
                       {
                           if(eee.toString().equals("java.lang.StringIndexOutOfBoundsException: String index out of range: 3"))
                           {
                                //RFta.appendText("\n Success Caught in Exception \n and the exception is\n"+eee);
                                try
                                {
//                                        String dsql="select * from pravin1.mt[x] where  ";
//                                        Connection dcon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
//                                        
//                                        Statement dstmt=dcon.createStatement();
//                                        ResultSet drs=dstmt.executeQuery(dsql);                    
                                        sdate=RFsdpic.getValue().toString();
                                        edate=RFedpic.getValue().toString();
                                        
                                        //RFta.appendText("\n"+sdate);
                                        //RFta.appendText("\n"+edate);
                                        
                                        String at[]=new String[2];
                                        String mt[]=new String[100];
                                        String qry=null;
                                        String qry2="";
                                        String qry3="";
                                        String cname=null;
                                        String ss="";
                                        
                                        int scount=0;
                                        int ecount=0;
                                        int loop=0;
                                        int inncount=0;
                                        int present=0;
                                        String days="";
                                        int vcount=0;
                                        int c=2;
                                        int s=0;
                                        
                                        Connection rcon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                                        //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                                        Statement rst=rcon.createStatement();
                                        record(RFpwd.getText());
                                        for(int x=0;x<100;x++)
                                        {
                                            mt[x]=new String();
                                        }
                                        at[0]=new String(sdate);
                                        at[1]=new String(edate);
                                        int mc=0;
                                        int p;
                                        mt=findMissingTables(at);
                                       
                                       ResultSet rrs=null;
                                       
                                       
                                       
                                       //RFta.appendText("\nQuery Message"+qry); 
                                       //RFta.appendText("\nArrayLength:\t"+mt.length);
                                       if(!(RFcbtname.getValue().equals(" "))&&(RFcbpname.getValue().equals(" "))&&(RFcbrtype.getValue().equals(" ")))
                                       {
                                           qry2=" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where TeamName='"+RFcbtname.getValue()+"')";
                                       }
                                       if((RFcbtname.getValue().equals(" "))&&!(RFcbpname.getValue().equals(" "))&&(RFcbrtype.getValue().equals(" ")))
                                       {
                                           qry2=" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ProjectName='"+RFcbpname.getValue()+"')";
                                       }
                                       if((RFcbtname.getValue().equals(" "))&&(RFcbpname.getValue().equals(" "))&&!(RFcbrtype.getValue().equals(" ")))
                                       {
                                           qry2=" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ResourceType='"+RFcbrtype.getValue()+"')";
                                       }
                                       if(!(RFcbtname.getValue().equals(" "))&&!(RFcbpname.getValue().equals(" "))&&(RFcbrtype.getValue().equals(" ")))
                                       {
                                           qry2=" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ProjectName='"+RFcbpname.getValue()+"' and TeamName='"+RFcbtname.getValue()+"')";
                                       }
                                       if((RFcbtname.getValue().equals(" "))&&!(RFcbpname.getValue().equals(" "))&&!(RFcbrtype.getValue().equals(" ")))
                                       {
                                           qry2=" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ResourceType='"+RFcbrtype.getValue()+"' and ProjectName='"+RFcbpname.getValue()+"')";
                                       }
                                       if(!(RFcbtname.getValue().equals(" "))&&(RFcbpname.getValue().equals(" "))&&!(RFcbrtype.getValue().equals(" ")))
                                       {
                                           qry2=" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ResourceType='"+RFcbrtype.getValue()+"' and TeamName='"+RFcbtname.getValue()+"')";
                                       }
                                       if(!(RFcbtname.getValue().equals(" "))&&!(RFcbpname.getValue().equals(" "))&&!(RFcbrtype.getValue().equals(" ")))
                                       {
                                           qry2=" where EnterpriseID in (select EnterpriseID from pravin1.Vacation_Tracker where ResourceType='"+RFcbrtype.getValue()+"' and ProjectName='"+RFcbpname.getValue()+"' and TeamName='"+RFcbtname.getValue()+"')";
                                       }
                                       if(((RFcbtname.getValue().equals(" "))&&(RFcbpname.getValue().equals(" "))&&(RFcbrtype.getValue().equals(" "))&&!(RFtfaid.getText().equals(""))))
                                       {
                                              qry3=" where EnterpriseID='"+RFtfaid.getText()+"'";
                                              s=1;
                                       }
                                       else
                                       {
                                           if(!(RFtfaid.getText().equals("")))
                                           {
                                                qry3=" and EnterpriseID='"+RFtfaid.getText()+"'";
                                                s=1;
                                           }
                                       }
                                       
                                       for(;mt[loop]!=null;loop+=3)
                                       {
                                           qry="select * from pravin1."+mt[loop]+qry2+qry3;                                          
                                           RFta.appendText("\nMonth:\t"+mt[loop]+"\n"); 
                                           
                                           if(s==1)
                                           {
                                               if(!(present(RFtfaid.getText(),"2",mt[loop])))
                                               {
                                                   
                                               }
                                               else
                                               {
                                                 if(((RFcbtname.getValue().equals(" "))&&(RFcbpname.getValue().equals(" "))&&(RFcbrtype.getValue().equals(" "))&&!(RFtfaid.getText().equals(""))))
                                                 {
                                                     qry3=" where EnterpriseID='"+RFtfaid.getText()+"'";
                                                 }
                                                 else
                                                 {
                                                     if(!(RFtfaid.getText().equals("")))
                                                     {
                                                        qry3=" and EnterpriseID='"+RFtfaid.getText()+"'";
                                                     }
                                                 }
                                               }
                                           }
                                           
                                           //if(((mt[loop].substring(0,3).equalsIgnoreCase(RFsdpic.getValue().getMonth().toString().substring(0,3)))||(mt[loop].substring(0,4).equalsIgnoreCase(RFsdpic.getValue().getMonth().toString().substring(0,4))))&&(Integer.parseInt(mt[loop].substring(4,8)))==(RFsdpic.getValue().getYear()))
                                           if(((mt[loop].substring(0,3).equalsIgnoreCase(RFsdpic.getValue().getMonth().toString().substring(0,3))))&&(Integer.parseInt(mt[loop].substring(4,8)))==(RFsdpic.getValue().getYear()))
                                           {
                                                scount=RFsdpic.getValue().getDayOfMonth();
                                                ecount=Integer.parseInt(mt[c]);
                                           }
                                           else
                                           {
                                               if(((mt[loop].substring(0, 3).equalsIgnoreCase(RFedpic.getValue().getMonth().toString().substring(0, 3))))&&(Integer.parseInt(mt[loop].substring(4,8)))==(RFedpic.getValue().getYear()))
                                               {
                                                    scount=1;
                                                    ecount=RFedpic.getValue().getDayOfMonth();
                                               }
                                               else
                                               {
                                                   scount=1;
                                                   ecount=Integer.parseInt(mt[c]);
                                                  
                                               }
                                           }
                                           if((((mt[loop].substring(0,3).equalsIgnoreCase(RFsdpic.getValue().getMonth().toString().substring(0,3))))&&(Integer.parseInt(mt[loop].substring(4,8)))==(RFsdpic.getValue().getYear()))&&((mt[loop].substring(0, 3).equalsIgnoreCase(RFedpic.getValue().getMonth().toString().substring(0, 3))))&&(Integer.parseInt(mt[loop].substring(4,8)))==(RFedpic.getValue().getYear()))
                                           {
                                               scount=RFsdpic.getValue().getDayOfMonth();
                                               ecount=RFedpic.getValue().getDayOfMonth();
                                           }
                                           
                                           
                                           rrs=rst.executeQuery(qry);
                                           c+=3;
                                           while(rrs.next())
                                           {
                                                cname=rrs.getString("EnterpriseID");
                                                
                                                for(p=scount;p<=ecount;p++)
                                                {
                                                        if(rrs.getString(p+1).equalsIgnoreCase(RFcbtype.getValue()))
                                                        {
                                                            switch(p%10)
                                                            {
                                                                case 1:
                                                                    if(p==1||p==21||p==31)
                                                                    {
                                                                        ss="st";
                                                                    }
                                                                    else
                                                                        ss="th";
                                                                    break;
                                                                case 2:
                                                                    if(p==2||p==22)
                                                                        ss="nd";
                                                                    else
                                                                        ss="th";
                                                                    break;
                                                                case 3:
                                                                    if(p==3||p==23)
                                                                        ss="rd";
                                                                    else
                                                                        ss="th";
                                                                    break;
                                                                default:
                                                                    ss="th";
                                                                    
                                                            }
                                                            days+=""+p+ss+mt[loop].substring(0,3)+"\t";
                                                            present=1;
                                                            vcount++;                                                   
                                                        }
                                                }
                                                if(present==1)
                                                {
                                                    inncount++;
                                                    RFta.appendText("\n"+inncount+"\t"+cname+"\t");
                                                    RFta.appendText(days+"\t-\t"+vcount);
                                                }
                                                present=0;
                                                vcount=0;
                                                days="";
                                            }
                                            inncount=0;
                                            RFta.appendText("\n\n");
                                       }
                                       bfname.setDisable(false);
                                       RFtffname.setEditable(true);
                                }
                                catch(Exception exc)
                                {
                                    if(!(exc.toString().equals("java.lang.NullPointerException"))&&!(RFcbtabname.equals('\t')))
                                    {
                                        JOptionPane.showMessageDialog(null,"Caught in exception inside Randon Fetch(Date Validation)....\nand the exception is ....\n"+exc);
                                                                         
                                        //RFta.appendText("\nCaught in exception inside Randon Fetch(Date Validation)....\nand the exception is ....\t\n"+exc);
                                    }
                                    else
                                        if(!(exc.toString().equals("java.lang.NullPointerException"))&&(RFcbtabname.equals('\t')))
                                        {
                                             JOptionPane.showMessageDialog(null,"Tab Recognized");
                                             //RFta.appendText("hi:"+RFcbtabname.getValue());
                                        }
                                }
                           }
                       }
                     }
//                      else
//                      {
//                         
//                         RFta.appendText("else part");
//                         Table Name not mentioned or if table name is equal to null
//                      }
                      try
                      {
                            //String autudsql="update Authorized_Users set DateTime='"+currentDate.toString()+"' where AccentureID='"+taid.getText()+"'";
                            //String autudsql="insert into Authorized_Users (DateTime)"+"values (?)";
                            Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                            //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                            Statement stv=con.createStatement();
                            ResultSet rsrf=stv.executeQuery(sqlrf);
                            record(RFpwd.getText());    
                            while(rsrf.next())
                            {
                                gvft[31]=rsrf.getString("EnterpriseID");
                                j=0;
                                while(j<imnum)
                                {                    
                                    gvft[j]=rsrf.getString(dsfx[j].concat(mn));         //if this doesn't work try dsfx.concat(mn)                        
                         
                                    if(gvft[j].equals(sopt))
                                    {
                               
                                       vtcc=1;
                                    } 
                                    j++;                   
                                }
                     
                                 if(vtcc==1)
                                {
                                     vtcc=0;
                                     j=0;
                                     RFta.appendText("\n"+line+"\t"+gvft[31]+"\t");
                                     while(j<imnum)
                                     {
                                        if(gvft[j].equals(sopt))
                                        {
                                            count++;
                                            RFta.appendText("   "+dsfx[j]+mn);
                                        }
                                        j++;
                               
                                     }
                                     line++;
                                     RFta.appendText("\t-\t"+count);
                                     count=0;
                                }        
                            }
                            bfname.setDisable(false);
                            RFtffname.setEditable(true);
                            rsrf.close();
                            con.close();
                            
                      }
                      catch(Exception e)
                      { 
                            //JOptionPane.showMessageDialog(null,"Caught in Exception\t:\t"+e.getMessage()); 
                            System.out.println("Caught in Exception\t:\t"+e.getMessage());
                      }
                     
                 }
                 else
                 {
                     JOptionPane.showMessageDialog(null,"Type Field Can not be left blank...\n","warning",JOptionPane.WARNING_MESSAGE);
                 }
                  
               }
             else
             {
                
                //JOptionPane.showMessageDialog(null,"You are not an AUNTHICATED User....\nSorry you don't have access for Performing this Function...!!!","Error",JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(null,"You are not an AUNTHICATED User....\nSorry you don't have access for Performing this Function...!!!","Error",JOptionPane.ERROR_MESSAGE);
                
             }
         }
    }

    @FXML
    private void RFBExportToFile(ActionEvent event) 
    {
        if(!(RFtffname.getText().equals("")))
        {
            String fname=RFtffname.getText();
            if(!RFtffname.getText().equals(""))
            {
                if((""+RFtffname.getText().charAt(RFtffname.getText().length()-4)).equals("."))
                {
                     //if(tffnam.getText().substring((tffnam.getText().length()-(tffnam.getText().length()-4)),(tffnam.getText().length()+1)).equals(".xls"))
                     if((RFtffname.getText().substring(((RFtffname.getText().length())-4),(RFtffname.getText().length())).equalsIgnoreCase(".csv"))||(RFtffname.getText().substring(((RFtffname.getText().length())-4),(RFtffname.getText().length())).equalsIgnoreCase(".txt")))
                     {
                         write(fname,RFta.getText());
                     }
                     else
                     {
                         JOptionPane.showMessageDialog(null,"You can Save the contents in an CSV (or) txt file\nPlease choose a CSV (or) txt file and then try again...\n","Warning",JOptionPane.WARNING_MESSAGE);
                     }
                }
                else
                {
                    write(fname+".csv",RFta.getText());
                }
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Sorry,You haven't entered the file name...","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    private void RFBClear(ActionEvent event) 
    {
        RFta.setText("Output\n");
    }

    @FXML
    private void BFname(ActionEvent event) 
    {
        try
        {
            
            FileChooser fc=new FileChooser();
            File selectedfile= fc.showSaveDialog(null);
            //String filname=filname.replaceAll("\\\\","\\\\\\\\");
            RFtffname.setText(selectedfile.getPath());
        }
        catch(Exception e)
        {
            System.out.println("Caught in Exception while using File Chooser and the Exception is\n"+e);
        }        
    }
    
    @FXML
    public boolean authenticate(int op)
    {
        int found=0;
        switch(op)
        {
            case 1:
                sataid=Apwd.getText();
                break;
            case 2:
                sataid=Spwd.getText();
                break;
            case 3:
                sataid=AIpwd.getText();
                break;
            
            case 6:
                sataid=RFpwd.getText();
                break;
        }
        
        try
        {
             String autaid=new String();    
             String autsql="select AccentureID from pravin1.AuthorizedUsers";
             Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
             //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
             Statement autst=con.createStatement();
             ResultSet autrs=autst.executeQuery(autsql);
             while(autrs.next())
             {
                    autaid=autrs.getString("AccentureID");
                    if(autaid.equals(sataid))
                    {
                          found=1;
                          break;
                    }
             }  
        }       
        catch(Exception e)
        {
              //ta1.appendText("\nCaught in Exception While Authenticating\n Exception:\t");
              JOptionPane.showMessageDialog(null,"\nCaught in Exception While Authenticating\n Exception:\t","warning",JOptionPane.WARNING_MESSAGE);
        }
        if(found==1)
        {
             //ta1.appendText("\nAuthorized User....\nPlease Proceed......\n");
             //JOptionPane.showMessageDialog(null,"\nAuthorized User....\nPlease Proceed......\n");
             return(true);
        }
        else
        { 
             //ta1.appendText("\nYou are NOT Authorized User....\nYou do not have ACCESS......\n");
             //JOptionPane.showMessageDialog(null,"\nYou are NOT Authorized User....\nYou do not have ACCESS......\n");
             return(false);
        }
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
    
    @FXML
    public static boolean present(String tbv,String a,String x)
   {
       int found=0;
       try
       {  
           String eid;
           String csql;//="select EnterpriseID from pravin1."+stabname;
           if(a.equals(1))
           {
                csql="select EnterpriseID from pravin1."+x;
           }
           else
           {
                csql="select EnterpriseID from pravin1."+x;
           }
           Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
           //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
           Statement cst=con.createStatement();
           ResultSet crs=cst.executeQuery(csql);
           while(crs.next())
           {
                eid=crs.getString("EnterpriseID");
                if(eid.equals(tbv))
                {
                     found=1;
                     
                     break;
                }
           }
       }       
       catch(Exception e)
       {
              System.out.println("Caught in Exception While Authenticating\n Exception:\t"+e);
       }
       if(found==1)
       {
            
             //JOptionPane.showMessageDialog(null,"Valid EnterpriseID Please Proceed...");
             return(true);
       }
       else
       {
            JOptionPane.showMessageDialog(null,"InValid EnterpriseID...\n"+tbv+" is not present in "+x+"\n","Error",JOptionPane.ERROR_MESSAGE);
            return(false);
       }
   }
    
   @FXML
    public static void record(String ataid)
    {
        if((sactcmd.equals("Search")||sactcmd.equals("AddNewEntry")||sactcmd.equals("FetchData")||sactcmd.equals("AddTable")||sactcmd.equals("AddProjectName")||sactcmd.equals("AddTeamName")||sactcmd.equals("Delete")||sactcmd.equals("RandomFetch")))
        {
           String sdate="\0";
           sataid=ataid;
           //ta.append("\n"+ataid.getText());
           Date sysdate=new Date();
           SimpleDateFormat sd=new SimpleDateFormat("dd.MM.yyyy  'at' HH:mm:ss z");
           sd.setTimeZone(TimeZone.getTimeZone("IST"));
           sdate=sd.format(sysdate).toString();
           String uddatesql="update AuthorizedUsers set "+sactcmd+"='"+sdate+"' where AccentureID='"+sataid+"'";           
           String fcontent=""+ataid+"\t"+sactcmd+"\t-\t"+sdate+"\n";
           FileOutputStream fop=null;
           File file;
           //ta.append("\n IST Date in String Format:\t"+sdate);
           try
           {
               /*
                 file=new File("C:\\javaprg\\Pratice\\Vacation_Tracker\\Log.txt");
                 fop=new FileOutputStream(file);
                 if(!file.exists())
                 {
                      file.createNewFile();
                 }
                 byte[] content=fcontent.getBytes();
                 fop.write(content);
                 fop.flush();
                 fop.close();
                 */
                 FileWriter fileWriter = new FileWriter("C:/VacationTracker/log.txt",true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                 //String fcontent=fcont;
                 String updatedText = fcontent.replaceAll("\n", System.lineSeparator());          
                 //System.out.println(updatedText);
                 bufferedWriter.write(updatedText);
                 bufferedWriter.close();
                 
                 Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
                 //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
                 PreparedStatement uddateps=con.prepareStatement(uddatesql);
                 uddateps.executeUpdate();
                 uddateps.close();
                 con.close();
           }
            
            catch(Exception e)
            {
                 //JOptionPane.showMessageDialog(null,"Caught in Exception in update Button\t:\t"+e.getMessage());
                 System.out.println("Caught in Exception while Updating Date in Search ADD or Fetch Operation\t:\t"+e.getMessage());
            }
            finally
            {
                try
                {
                     if(fop!=null)
                     {
                          fop.close();
                     }
                }
                catch (IOException e) 
                {
		     e.printStackTrace();
		}
           }
            sactcmd="\0";
        }
    }
   
    @FXML
    public static String[] findMissingTables(String stn[])
    {
        String atname[]=new String[100];   // Available table names in MY Access
        String tbutname[]=new String[100]; // To Be Used Table Name   
        String tvipf[]=new String[100];   // Table Value in proper Format
        String tnp[]=new String[100];     //Table not Present
        int y;
        int acount=0;
        int index1=0;
        int index2=0;
//        for(y=0;y<100;y++)
//        {
//            atname[y]=new String();
//            tbutname[y]=new String();
//            tvipf[y]=new String();
//        }
        try
        {
            String tasql="select TableName from pravin1.TableNames";
            Connection tacon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
            //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
            Statement tastmt=tacon.createStatement();
            ResultSet tars=tastmt.executeQuery(tasql);
            int z=0;
            while(tars.next())
            {
                atname[z]=tars.getString("TableName");
                System.out.println(atname[z]);
                z++;
            }
            int syval=Integer.parseInt(stn[0].substring(0,4));
            int smval=Integer.parseInt(stn[0].substring(5,7));
            int eyval=Integer.parseInt(stn[1].substring(0,4));
            int emval=Integer.parseInt(stn[1].substring(5,7));
            int ycount=0;
            int mcount=0;
            if(syval>eyval)
            {
                JOptionPane.showMessageDialog(null,"\nSorry....\n The  date that you have entered is not in sequence please validate the time frame that you have given....","Error",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                
                int i;
                for(i=syval;i<eyval;i++);
                ycount=i-syval;
                for(i=smval;i<emval;i++);
                mcount=(i-smval)+(ycount*12);
            }
            
            int yvali=syval;
            int mvali=smval-1;
            int mvcount=0;
            String mc1="";
            int mc2=0;
            int index=0;

            while(mvcount<=mcount)
            {
                String mname="";
                
                if(mvali>=12)
                {
                    mvali%=12;
                    yvali++;
                }
                
                switch(mvali)
                {
                    case 0:
                        mname="Jan";
                        mc1="Month1";
                        mc2=31;
                        break;
                    case 1:
                        mname="Feb";
                        if(yvali%4==0)
                        {
                            mc1="Month2";
                            mc2=28;                            
                        }
                        else
                        {
                            mc1="Month13";
                            mc2=29;
                        }
                        break;
                    case 2:
                        mname="Mar";
                        mc1="Month3";
                        mc2=31;
                        break;
                    case 3:
                        mname="Apr";
                        mc1="Month4";
                        mc2=30;
                        break;
                    case 4:
                        mname="May";
                        mc1="Month5";
                        mc2=31;
                        break;
                    case 5:
                        mname="Jun";
                        mc1="Month6";
                        mc2=30;
                        break;
                    case 6:
                        mname="Jul";
                        mc1="Month7";
                        mc2=31;
                        break;
                    case 7:
                        mname="Aug";
                        mc1="Month8";
                        mc2=31;
                        break;
                    case 8:
                        mname="Sep";
                        mc1="Month9";
                        mc2=30;
                        break;
                    case 9:
                        mname="Oct";
                        mc1="Month10";
                        mc2=31;
                        break;
                    case 10:
                        mname="Nov";
                        mc1="Month11";
                        mc2=30;
                        break;
                    case 11:
                        mname="Dec";
                        mc1="Month12";
                        mc2=31;
                        break;
                }
                int p=0;
                tvipf[index]=mname+"_"+yvali;
//                tvipf[index+1]=mc1;
//                tvipf[index+2]=""+mc2;
                for(int i=0;atname[i]!=null;i++)
                {
                    if(tvipf[index].equalsIgnoreCase(atname[i]))
                    {
                        tbutname[index1]=tvipf[index];
                        tbutname[index1+1]=mc1;
                        tbutname[index1+2]=""+mc2;
                        index1+=3;
                        p=1;
                    }
                }
                if(p==0)
                {
                    tnp[index2]=tvipf[index];
                    tnp[index2+1]=mc1;
                    tnp[index2+2]=""+mc2;
                    index2+=3;
                }
                index+=3;
                mvali++;
                mvcount++;
            }
//            int i=0;
//            int j=0;
//            
//            for(;i<atname.length;i++)
//            {
//                for(;j<tvipf.length;j+=3)
//                {
//                    if(atname[i].equals(tvipf[j]))
//                    {
//                        tbutname[acount]=atname[i];
//                        tbutname[acount+1]=tvipf[j+1];
//                        tbutname[acount+2]=tvipf[j+2];
//                        System.out.println(tbutname[acount]);
//                        acount++;
//                    }
//                }
//            }
System.out.println("Table values that are not present in combo box but are in the range");

for(int j=0;tnp[j]!=null;j++)
{
     System.out.println(j+"\t"+tnp[j]);    
}
            
            tars.close();
            tastmt.close();
            tacon.close();
            return(tbutname);
            
        }
        catch(Exception tabava)
        {
            System.out.println("Caught in exception inside Random Fetch(Table Availablity Checking Method) \n And the exception is ...\n"+tabava);
        }
        
        return null;
    }
 
   @FXML
   public static boolean validate(String record,String val)
   {
       int found=0;
       boolean a=true;
    
       if(record.equalsIgnoreCase("ProjectName"))
       {
           try
           {  
               String pname;
               String tname;
               String vsql="select ProjectName from pravin1.Project";
               Connection vcon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
               //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
               Statement vst=vcon.createStatement();
               ResultSet vrs=vst.executeQuery(vsql);
               
               while(vrs.next())
               {
                    pname=vrs.getString("ProjectName");
                    if(pname.equalsIgnoreCase(val))
                    {
                         found=1;
                         break;
                    } 
               }
               vst.close();
               vcon.close();
           }       
           catch(Exception e)
           {
               System.out.println("Caught in Exception While Validating Project Name Case 1:\n Exception:\t"+e);
           }
           if(found==1)
           {
            
               //JOptionPane.showMessageDialog(null,"Project Name is already Existing...");
               a=false;
           }
           else
           {
               //JOptionPane.showMessageDialog(null,"InValid EnterpriseID...");
               a=true;
           }
       }
       if(record.equals("TeamName"))
       {
           try
           {  
               String pname;
               String tname;
               String vsql="select TeamName from pravin1.Teams";
               Connection vcon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
               //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
               Statement vst=vcon.createStatement();
               ResultSet vrs=vst.executeQuery(vsql);
               while(vrs.next())
               {
                    tname=vrs.getString("TeamName");
                    if(tname.equalsIgnoreCase(val))
                    {
                         found=1;
                         break;
                    } 
               }
               vst.close();
               vcon.close();
           }       
           catch(Exception e)
           {
               System.out.println("Caught in Exception While Validating Project Name Case 1:\n Exception:\t"+e);
           }
           if(found==1)
           {
            
               //JOptionPane.showMessageDialog(null,"Team Name is already Existing...");
               a=false;
           }
           else
           {
               //JOptionPane.showMessageDialog(null,"InValid EnterpriseID...");
               a=true;
           }
       }
       if(record.equals("TableName"))
       {
           try
           {  
               String pname;
               String tname;
               String vsql="select TableName from pravin1.TableNames";
               String temppp;
	       Connection vcon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
               //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
               Statement vst=vcon.createStatement();
               ResultSet vrs=vst.executeQuery(vsql);
               while(vrs.next())
               {
                    tname=vrs.getString("TableName");
                    if(tname.equalsIgnoreCase(val))
                    {
                         found=1;
                         break;
                    } 
               }
               vst.close();
               vcon.close();
           }       
           catch(Exception e)
           {
               System.out.println("Caught in Exception While Validating Project Name Case 1:\n Exception:\t"+e);
           }
           if(found==1)
           {
            
               //JOptionPane.showMessageDialog(null,"Team Name is already Existing...");
               a=false;
           }
           else
           {
               //JOptionPane.showMessageDialog(null,"InValid EnterpriseID...");
               a=true;
           }
       }
       
       return(a);
   }
   
   @FXML
   public void cbupdate(String val)
   {
           try
           {
               String cbuq="";
               Connection cbucon=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
               //Connection con=DriverManager.getConnection("jdbc:odbc:vt");
               Statement cbust=cbucon.createStatement();
               if(val.equalsIgnoreCase("ProjectName"))
               {
                   ANcb1.getItems().clear();
                   Scb2.getItems().clear();
                   RFcbpname.getItems().clear();
           
                   cbuq="select ProjectName from pravin1.Project";
                   
                   ResultSet cbupnrs=cbust.executeQuery(cbuq);
                   
                   ANcb1.getItems().addAll(" ");
                   Scb2.getItems().addAll(" ");
                   RFcbpname.getItems().addAll(" ");
                   while(cbupnrs.next())
                   {
                       ANcb1.getItems().addAll(cbupnrs.getString("ProjectName"));
                       Scb2.getItems().addAll(cbupnrs.getString("ProjectName"));
                       RFcbpname.getItems().addAll(cbupnrs.getString("ProjectName"));
                   }
                   cbupnrs.close();
                   
               }
               if(val.equalsIgnoreCase("TeamName"))
               {
                   ANcb2.getItems().clear();
                   Scb1.getItems().clear();
                   RFcbtname.getItems().clear();
           
                   cbuq="select TeamName from pravin1.Teams";
                   
                   
                   ResultSet cbutnrs=cbust.executeQuery(cbuq);
                   
                   ANcb2.getItems().addAll(" ");
                   Scb1.getItems().addAll(" ");
                   RFcbtname.getItems().addAll(" ");
                   while(cbutnrs.next())
                   {
                       ANcb2.getItems().addAll(cbutnrs.getString("TeamName"));
                       Scb1.getItems().addAll(cbutnrs.getString("TeamName"));
                       RFcbtname.getItems().addAll(cbutnrs.getString("TeamName"));
                   }
                   cbutnrs.close();
               }
               if(val.equalsIgnoreCase("TableName"))
               {
                   UIcb2.getItems().clear();
                   RFcbtabname.getItems().clear();
                              
                   cbuq="select TableName from pravin1.TableNames";
                   
                   
                   ResultSet cbutabnrs=cbust.executeQuery(cbuq);
                   
                   UIcb2.getItems().addAll(" ");
                   RFcbtabname.getItems().addAll(" ");
                   while(cbutabnrs.next())
                   {
                       UIcb2.getItems().addAll(cbutabnrs.getString("TableName"));
                       RFcbtabname.getItems().addAll(cbutabnrs.getString("TableName"));
                   }
                   cbutabnrs.close();
               }
               if(val.equalsIgnoreCase("Employee"))
               {
                   rdecb.getItems().clear();
                                                 
                   cbuq="select EnterpriseID from pravin1.Vacation_Tracker";
                   
                   ResultSet cbers=cbust.executeQuery(cbuq);
                   
                   rdecb.getItems().addAll(" ");
                   uiecb.getItems().addAll(" ");
                   while(cbers.next())
                   {
                       rdecb.getItems().addAll(cbers.getString("EnterpriseID"));
                       uiecb.getItems().addAll(cbers.getString("EnterpriseID"));
                   }
                   rdecb.getItems().addAll("Add New Entry");
                   cbers.close();
               }
               cbucon.close();
               
           }
           catch(Exception exc)
           {
               //JOptionPane.showMessageDialog(null,"\nSorry....\n The  date that you have entered is not in sequence please validate the time frame that you have given....","Error",JOptionPane.ERROR_MESSAGE);
               System.out.println("Caught in Exception while Updating the "+val+"Combobox\n and the exception is\n"+exc);
           }
   }
   
   @FXML
   public void updateListView()
   {
       if(!uiecb.getValue().equals(" "))
       {
          uilv.getItems().clear();
          try
          {
             String ulvq="select * from pravin1."+UIcb2.getValue()+" where EnterpriseId='"+uiecb.getValue()+"'";
             int i=2;
             Connection con=DriverManager.getConnection("jdbc:mysql://INITDURAIPL3C.corp.emc.com:3306/pravin1","pravin","Pravin@1");
             Statement ulvst=con.createStatement();
             ResultSet ulvrs=ulvst.executeQuery(ulvq);
             String dat[]=new String[31];
             for(int k=0;k<31;k++)
                 dat[k]=new String("");
             dat[0]="1st";
             dat[1]="2nd";
             dat[2]="3rd";
             dat[3]="4th";
             dat[4]="5th";
             dat[5]="6th";
             dat[6]="7th";
             dat[7]="8th";
             dat[8]="9th";
             dat[9]="10th";
             dat[10]="11th";
             dat[11]="12th";
             dat[12]="13th";
             dat[13]="14th";
             dat[14]="15th";
             dat[15]="16th";
             dat[16]="17th";
             dat[17]="18th";
             dat[18]="19th";
             dat[19]="20th";
             dat[20]="21st";
             dat[21]="22nd";
             dat[22]="23rd";
             dat[23]="24th";
             dat[24]="25th";
             dat[25]="26th";
             dat[26]="27th";
             dat[27]="28th";
             dat[28]="29th";
             dat[29]="30th";
             dat[30]="31th";
             
             while(ulvrs.next())
             {
                 empname.add(ulvrs.getString("EnterpriseID"));
                 for(;i<ulvrs.getMetaData().getColumnCount();i++)
                     empname.add(""+dat[i-2]+" "+UIcb2.getValue().substring(0,3)+"  -  "+ulvrs.getString(i));
                 //i++;
             }
             uilv.setItems(empname);
          }
          catch(Exception exc)
          {
                //JOptionPane.showMessageDialog(null,"Caught in Exceptin in Update Info while Populating the List View \n And the exception is:\n"+exc,"warning",JOptionPane.WARNING_MESSAGE);
              System.out.println("Caught in Exceptin in Update Info while Populating the List View \n And the exception is:\n"+exc);
          }     
       }
       else
       {
           uilv.getItems().clear();
       }
   }
    
}
