/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author duraip
 */
public class ELMS_Prod_Data_AnalysisController implements Initializable 
{

    @FXML
    private Tab unot;
    @FXML
    private AnchorPane pAlys;
    @FXML
    private DatePicker undpsd;
    @FXML
    private DatePicker undped;
    @FXML
    private Button unbextract;
    @FXML
    private Label unlsd;
    @FXML
    private Label unled;
    @FXML
    private ProgressBar unpbar;
    @FXML
    private TextArea unta;
    @FXML
    private Label palstl;
    @FXML
    private Label palssml;
    @FXML
    private CheckBox ptcbclp;
    @FXML
    private CheckBox ptcbunot;
    @FXML
    private CheckBox ptcbess;
    @FXML
    private CheckBox psmcbhb;
    @FXML
    private CheckBox psmcbdb;
    @FXML
    private CheckBox psmcbmb;
    @FXML
    private Label palshl;
    @FXML
    private ImageView simv;
    @FXML
    private Label sl;
    @FXML
    private BarChart<String[], int[]> bgraph;
    @FXML
    private NumberAxis gucountl;
    @FXML
    private CategoryAxis gdsnl;
    @FXML
    private String filname="";
    @FXML
    private String clpcb="";
    @FXML
    private String uncb="";
    @FXML
    private String esscb="";
    @FXML
    private String hbcb="";
    @FXML
    private String dbcb="";
    @FXML
    private String mbcb="";
    @FXML
    int dposition=0;
    @FXML
    int m=0;
    @FXML
    String sfilname="";
    @FXML
    String mfilname="";
    @FXML
    String ufilname="";
                          
    
    /**
     * Initializes the controller class.
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
    
    class PUNotificationEData implements Runnable
    {

        @Override
        public void run() 
        { 
             String dburl="jdbc:oracle:thin:@//elmoraprd01.isus.emc.com:1800/elmpapp";
             String dbuname="POET_RDONLY";
             String dbpword="t1m3";
             String sDat="";
             String eDat="";
             int selt=0;
             int sday=Integer.parseInt(undpsd.getValue().toString().substring(8, 10));
             int smonth=Integer.parseInt(undpsd.getValue().toString().substring(5, 7));
             int syear=Integer.parseInt(undpsd.getValue().toString().substring(0, 4));
             int eday=Integer.parseInt(undped.getValue().toString().substring(8, 10));
             int emonth=Integer.parseInt(undped.getValue().toString().substring(5, 7));
             int eyear=Integer.parseInt(undped.getValue().toString().substring(0, 4));
             int hwcount=0;
             int dwcount=0;
             int mwcount=0;
             int totdfpb=0;
             int j=0;
             int pi=1;
             int hb=0;
             int db=0;
             int mb=0;
             String sdatpfmat="";  
             String edatpfmat="";
             String qry1="";
             String qry2="";
             String qry3="";
             String qry12="";
             String qry22="";
             String qry32="";
             String qry13="";
             String qry23="";
             String qry33="";
             String fqry="";
             String hday="";
             String stime="";
             String etime="";
             String ctab="";
            
             /* 
             
                 Aleternate and the best solution is to
                 1.   Find the checked tables and number tem 1 2 and 3 and add them to a string variable
                 2.   Switch variable % 10
                 3.   end of switch don't forget to add variable/10
                 4.   generate the Query and inside the swich case but calculate the progress indication outside the Switch Case
                 5.   iterate the Switch case till the Variable in the String value becomes 0
                 6. Inside Switch case only check how the values has to be printed in the file like daily basis or monthly basis
             
             */
             
             if(clpcb.equals("clp"))
             {
                 qry1="select count(*) from poetk.emc_ui_clp_usage where esrs_received_timestamp>='";
                 qry2="' and esrs_received_timestamp<'";
                 qry3="' order by 1 asc";
                 ctab+=1;
                 selt++;
             }
             if(uncb.equals("unot"))
             {
                 qry12="select count(*) from poetk.emc_ui_usage_notification where STATUS like '%SUCCESS%' and create_date>='";
                 qry22="' and create_date<'";
                 qry32="' order by 1 asc";
                 ctab+=2;
                 selt++;
             }
             if(esscb.equals("ess"))
             {
                 qry13="select count(*) from poetk.emc_ui_entitlement_snapshot where create_timestamp>='";
                 qry23="' and create_timestamp<'";
                 qry33="' order by 1 asc";
                 ctab+=3;
                 selt++;
             }
             
             unpbar.setStyle("-fx-accent: red;");
             if(hbcb.equals("hb")&&!dbcb.equals("db")&&!mbcb.equals("mb"))
             {
                 while((undpsd.getValue().plusDays(j).isBefore(undped.getValue()))||(undpsd.getValue().plusDays(j).isEqual(undped.getValue())))
                 {
                     j++;
                 }
                 j*=24;
                 //sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                 //edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
             }
             if(!hbcb.equals("hb")&&dbcb.equals("db")&&!mbcb.equals("mb"))
             {
                 while((undpsd.getValue().plusDays(j).isBefore(undped.getValue()))||(undpsd.getValue().plusDays(j).isEqual(undped.getValue())))
                 {
                     j++;
                 }
             }
             if(!hbcb.equals("hb")&&!dbcb.equals("db")&&mbcb.equals("mb"))
             {
                 while((undpsd.getValue().plusMonths(j).isBefore(undped.getValue()))||(undpsd.getValue().plusMonths(j).isEqual(undped.getValue())))
                 {
                     j++;
                 }
             }
             if(!hbcb.equals("hb")&&dbcb.equals("db")&&mbcb.equals("mb"))
             {
                 while((undpsd.getValue().plusDays(j).isBefore(undped.getValue()))||(undpsd.getValue().plusDays(j).isEqual(undped.getValue())))
                 {
                     j++;
                 }
             }
             if(hbcb.equals("hb")&&dbcb.equals("db")&&!mbcb.equals("mb"))
             {
                 while((undpsd.getValue().plusDays(j).isBefore(undped.getValue()))||(undpsd.getValue().plusDays(j).isEqual(undped.getValue())))
                 {
                     j++;
                 }
                 j=j*24;
             }
             if(hbcb.equals("hb")&&!dbcb.equals("db")&&mbcb.equals("mb"))
             {
                 while((undpsd.getValue().plusDays(j).isBefore(undped.getValue()))||(undpsd.getValue().plusDays(j).isEqual(undped.getValue())))
                 {
                     j++;
                 }
                 j=j*24;
             }
             if(hbcb.equals("hb")&&dbcb.equals("db")&&mbcb.equals("mb"))
             {
                 while((undpsd.getValue().plusDays(j).isBefore(undped.getValue()))||(undpsd.getValue().plusDays(j).isEqual(undped.getValue())))
                 {
                     j++;
                 }
                 j*=24;
             }
             
             j=j*selt;
             totdfpb=j+3;
             
             while((Integer.parseInt(ctab)%10)>0)
             {
                 switch(Integer.parseInt(ctab)%10)
                 {
                     case 1:
                     {
                         
                          
                          dposition=filname.indexOf('.');
                          for(m=dposition-1;filname.charAt(m)!='/';m--);
                          unta.appendText("\n Before the Last Slash count :\t"+m);
                          sfilname=filname.substring(0,m+1);
                          unta.appendText("\nFile Name Before :\t"+sfilname);
                          mfilname=filname.substring(m+1,dposition);
                          unta.appendText("\nMiddle File Name :\t"+mfilname);
                          ufilname=sfilname+mfilname+"_CLP.csv";
                          unta.appendText("\nUpdated FilName :\t"+ufilname);
                         
                         //totdfpb=j+3;
//                         int qtotdfpb=totdfpb/4;
//                         int htotdfpb=totdfpb/2;
//                         int ttotdfpb=totdfpb/3;
                         unpbar.setProgress(pi/(totdfpb+0.0));
                         pi++;
                         System.out.print("\nTotal Days:\t"+totdfpb);
                         try
                         {
                              
                              //fileWriter.append("Usage Notification Id,Channel,Status,Product Line Code,Company Pub X Ref Number,File ID,Create Date,Update Date,Instance SWID,Entitlement SWID,Gateway Serial Number\n");
                              //fileWriter.append("Usage ID,Instance ID,Usage Product ID,Usage Qty,Processing Timestamp,ESRS Received Timestamp,Usage File Timestamp,VE Instance SWID\n");
                              
                              //"\nUsage Notification Id:\t"+ers.getString("usage_notification_id")+"\tChannel:\t"+ers.getString("channel")+"\tStatus:\t"+ers.getString("status")+"\tProduct Line Code:\t"+ers.getString("product_line_code")+"\tCompany Pub X Ref Number:\t"+ers.getString("company_pub_x_ref_number")+"\tFile id:\t"+ers.getString("file_id")+"\tStart Date:\t"+ers.getDate("create_date")+"\tEnd Date:\t"+ers.getDate("update_date")+"\tInstance Swid :\t"+ers.getString("instance_swid")+"\tEntitlement_swid :\t"+ers.getString("entitlement_swid")+"\tGateway Serial Number :\t"+ers.getString("gateway_serial_number")
                              
                              FileWriter fileWriter = new FileWriter(ufilname);
                              Class.forName("oracle.jdbc.driver.OracleDriver");
                              Connection con = DriverManager.getConnection(dburl,dbuname,dbpword);
                              Statement esmt=con.createStatement();
                              ResultSet ers=null;
                              if(undpsd.getValue().isAfter(undped.getValue()))
                              {
                                 JOptionPane.showMessageDialog(null,"Sorry Can not Proceed..\nBecause the Start Date "+undpsd.getValue()+" is ahead of end date\n"+undped.getValue(),"Error",JOptionPane.ERROR_MESSAGE);
                                 //System.out.println("Sorry Can not Proceed..\nBecause the Start Date is ahead of end date\n"+undpsd.getValue().plusDays(1));
                              }
                              else
                              {
                                 
                                 System.out.println("\nSelected Dates are :\t");
                                 int i=0;
                                 Thread.sleep(50);
                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                 pi++;
                                 int s=0;
                                 while((undpsd.getValue().plusDays(i).isBefore(undped.getValue()))||(undpsd.getValue().plusDays(i).isEqual(undped.getValue())))
                                 {
                                     
                                     //sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                     
                                     //edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                     //if you need to take the count on daily basis then just change the "edatpfmat" parameter 'i' count to "i+1" 
                                     System.out.println("\n"+undpsd.getValue().plusDays(i));
                                     //System.out.print("\nMonth :\t"+undpsd.getValue().plusDays(i+1).getMonth().toString().substring(0, 3));
                                     //Hourly Basis loop
                                     
                                     
                                         
                                     if(hbcb.equals("hb")&&!dbcb.equals("db")&&!mbcb.equals("mb"))
                                     {
                                         
                                         if(i==0)
                                             fileWriter.append("Hours,Count\n");
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         fileWriter.append("\n\n"+sdatpfmat+"\n");
                                         unta.appendText("\n\n"+sdatpfmat+"\n");
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                             //qry2="' and create_date<'"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             //qry2="' and esrs_received_timestamp <='"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             fqry=qry1+sdatpfmat+stime+".00.00.000000000 "+hday+qry2+edatpfmat+stime+".59.59.999999999 "+hday+qry3;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 //System.out.println("\nUsage Notification Id:\t"+ers.getString("usage_notification_id")+"\tChannel:\t"+ers.getString("channel")+"\tStatus:\t"+ers.getString("status")+"\tProduct Line Code:\t"+ers.getString("product_line_code")+"\tCompany Pub X Ref Number:\t"+ers.getString("company_pub_x_ref_number")+"\tFile id:\t"+ers.getString("file_id")+"\tStart Date:\t"+ers.getTimestamp("create_date")+"\tEnd Date:\t"+ers.getTimestamp("update_date")+"\tInstance Swid :\t"+ers.getString("instance_swid")+"\tEntitlement_swid :\t"+ers.getString("entitlement_swid")+"\tGateway Serial Number :\t"+ers.getString("gateway_serial_number"));
                                                 //System.out.println("\nUsage ID:\t"+ers.getString("usage_id")+"\tInstance ID:\t"+ers.getString("instance_id")+"\tUsage Product ID:\t"+ers.getString("usage_product_id")+"\tUsage Qty:\t"+ers.getString("usage_qty")+"\tProcessing Timestamp:\t"+ers.getTimestamp("processing_timestamp")+"\tESRS Received Timestamp:\t"+ers.getTimestamp("esrs_received_timestamp")+"\tUsage File Timestamp:\t"+ers.getTimestamp("usage_file_timestamp")+"\tVE Instance SWID:\t"+ers.getString("ve_instance_swid"));
                                                 //System.out.println("\n"+ers.getInt("count(*)"));
                                                 System.out.println(""+stime+"\t"+hday+" :\t"+ers.getInt("count(*)"));
                                                 //fileWriter.append(ers.getString("usage_id")+","+ers.getString("instance_id")+","+ers.getString("usage_product_id")+","+ers.getString("usage_qty")+","+ers.getTimestamp("processing_timestamp")+","+ers.getTimestamp("esrs_received_timestamp")+","+ers.getTimestamp("usage_file_timestamp")+","+ers.getString("ve_instance_swid")+"\n");
                                                 //fileWriter.append("\n"+,"+ers.getInt("count(*)"));
                                                 //fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 hwcount+=ers.getInt("count(*)");
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+hwcount);
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                             unta.appendText("\n"+stime+" "+hday+" :\t"+hwcount);
                                             dwcount+=hwcount;
                                             
                                             //fileWriter.append("\n,,,,,,,,,,,,Hour Wise Count,"+hwcount+"\n");
                              
                                             hwcount=0;
                                             
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                         }
                          
                                         /*
                                             
                                             Use Both these only when you are going to collect day wise
                                         
                                             unta.appendText("\nDay wise Count :\t"+sdatpfmat+" :\t"+dwcount);
                                             fileWriter.append("\n,,,,,,,,,,,,,,,,"+sdatpfmat+","+dwcount+"\n");
                                         
                                         */

                                         //fileWriter.append("\n,,,,,,Day Wise Count,"+dwcount+"\n");
                                         
                                         Thread.sleep(20);     
                                         unpbar.setProgress(pi/(totdfpb+0.0));
                                         pi++;    
                                         System.out.println("\nDay Wise Count:\t"+dwcount);
                                         mwcount+=dwcount;
                                         dwcount=0;
                                         /*
                                             1. Execute the Query
                                             2. Write the contents to 1st csv file
                                             3. Open file chooser when you click on extract button
                                             4. make this as a thread ( Seperate Class ) for Updating the Progress Bar
                                             5. For Updating the Progress Calculate the total number of days between selected range and increase the progress count by 1 when each day is completed
                                         */
                                         //System.out.println("\n"+undpsd.getValue().plusDays(i+1).getMonthValue());
                                    
                                     /*
                                         
                                         Use this entire block when you want to print Month Value
                                         
                                         if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                         {
                                              
                                     //            Write the count values in the second csv file
                                             
                                             unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().getMonth()+" :\t"+mwcount);
                                             fileWriter.append(",,,,,,,,,,,,,,,,,,,,Month Count,"+mwcount+"\n");
                                             //fileWriter.append("\n,,,,,,,,,,Month Count,"+mwcount+"\n");
                                             mwcount=0;
                                             //System.out.println("Success");                        
                                         }
                                     
                                     */ 
                                     
                                         //fileWriter.close();
                                     }
                                     
                                     if(!hbcb.equals("hb")&&dbcb.equals("db")&&!mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i+1).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i+1).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         //fileWriter.append("\n\n"+sdatpfmat+"\n");
//                                         for(int k=1;k<=24;k++)
//                                         {
//                                             if(k>12)
//                                             {
//                                                 if(k!=24)
//                                                 {
//                                                     hday="PM";
//                                                 }
//                                                 else
//                                                 {
//                                                     hday="AM";
//                                                 }
//                                                 if(k!=12)
//                                                 {
//                                                     if(k==24)
//                                                     {
//                                                         stime=" 12";
//                                                     }
//                                                     else
//                                                     {
//                                                         stime=" "+(k%12);
//                                                     }
//                                                 } 
//                                             }
//                                             else
//                                             {
//                                                 if(k!=12)
//                                                 {
//                                                     hday="AM";
//                                                 }  
//                                                 else
//                                                 {
//                                                     hday="PM";
//                                                 }
//                                                 stime=" "+k;
//                                             }
                                             
                                             fqry=qry1+sdatpfmat+qry2+edatpfmat+qry3;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 //System.out.println("\nUsage Notification Id:\t"+ers.getString("usage_notification_id")+"\tChannel:\t"+ers.getString("channel")+"\tStatus:\t"+ers.getString("status")+"\tProduct Line Code:\t"+ers.getString("product_line_code")+"\tCompany Pub X Ref Number:\t"+ers.getString("company_pub_x_ref_number")+"\tFile id:\t"+ers.getString("file_id")+"\tStart Date:\t"+ers.getTimestamp("create_date")+"\tEnd Date:\t"+ers.getTimestamp("update_date")+"\tInstance Swid :\t"+ers.getString("instance_swid")+"\tEntitlement_swid :\t"+ers.getString("entitlement_swid")+"\tGateway Serial Number :\t"+ers.getString("gateway_serial_number"));
                                                 //System.out.println("\nUsage ID:\t"+ers.getString("usage_id")+"\tInstance ID:\t"+ers.getString("instance_id")+"\tUsage Product ID:\t"+ers.getString("usage_product_id")+"\tUsage Qty:\t"+ers.getString("usage_qty")+"\tProcessing Timestamp:\t"+ers.getTimestamp("processing_timestamp")+"\tESRS Received Timestamp:\t"+ers.getTimestamp("esrs_received_timestamp")+"\tUsage File Timestamp:\t"+ers.getTimestamp("usage_file_timestamp")+"\tVE Instance SWID:\t"+ers.getString("ve_instance_swid"));
                                                 //System.out.println("\n"+ers.getInt("count(*)"));
                                                 System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                                                 //fileWriter.append(ers.getString("usage_id")+","+ers.getString("instance_id")+","+ers.getString("usage_product_id")+","+ers.getString("usage_qty")+","+ers.getTimestamp("processing_timestamp")+","+ers.getTimestamp("esrs_received_timestamp")+","+ers.getTimestamp("usage_file_timestamp")+","+ers.getString("ve_instance_swid")+"\n");
                                                 //fileWriter.append("\n"+,"+ers.getInt("count(*)"));
                                                 //fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 dwcount+=ers.getInt("count(*)");
                                                 fileWriter.append(sdatpfmat+",\t"+ers.getInt("count(*)")+",");
                                                 unta.appendText("\n"+sdatpfmat+" :\t"+dwcount);
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             fileWriter.append("\n");
                              
                                             dwcount=0;
                                             
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }

                                              mwcount+=dwcount;
                                             
                                              
                                     }
                                     
                                     if(!hbcb.equals("hb")&&!dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i+1).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i+1).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nMonth,count\n");
                                         }
                                         if(s==1)
                                         {
                                             //fileWriter.append("\n"+undpsd.getValue().plusDays(i-1).getMonth()+"\n");
                                             s=0;
                                         }
                                             

                                             
                                             fqry=qry1+sdatpfmat+qry2+edatpfmat+qry3;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 //System.out.println("\nUsage Notification Id:\t"+ers.getString("usage_notification_id")+"\tChannel:\t"+ers.getString("channel")+"\tStatus:\t"+ers.getString("status")+"\tProduct Line Code:\t"+ers.getString("product_line_code")+"\tCompany Pub X Ref Number:\t"+ers.getString("company_pub_x_ref_number")+"\tFile id:\t"+ers.getString("file_id")+"\tStart Date:\t"+ers.getTimestamp("create_date")+"\tEnd Date:\t"+ers.getTimestamp("update_date")+"\tInstance Swid :\t"+ers.getString("instance_swid")+"\tEntitlement_swid :\t"+ers.getString("entitlement_swid")+"\tGateway Serial Number :\t"+ers.getString("gateway_serial_number"));
                                                 //System.out.println("\nUsage ID:\t"+ers.getString("usage_id")+"\tInstance ID:\t"+ers.getString("instance_id")+"\tUsage Product ID:\t"+ers.getString("usage_product_id")+"\tUsage Qty:\t"+ers.getString("usage_qty")+"\tProcessing Timestamp:\t"+ers.getTimestamp("processing_timestamp")+"\tESRS Received Timestamp:\t"+ers.getTimestamp("esrs_received_timestamp")+"\tUsage File Timestamp:\t"+ers.getTimestamp("usage_file_timestamp")+"\tVE Instance SWID:\t"+ers.getString("ve_instance_swid"));
                                                 //System.out.println("\n"+ers.getInt("count(*)"));
                                                 System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                                                 //fileWriter.append(ers.getString("usage_id")+","+ers.getString("instance_id")+","+ers.getString("usage_product_id")+","+ers.getString("usage_qty")+","+ers.getTimestamp("processing_timestamp")+","+ers.getTimestamp("esrs_received_timestamp")+","+ers.getTimestamp("usage_file_timestamp")+","+ers.getString("ve_instance_swid")+"\n");
                                                 //fileWriter.append("\n"+,"+ers.getInt("count(*)"));
                                                 //fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 dwcount+=ers.getInt("count(*)");
                                                 //fileWriter.append("\n"+sdatpfmat+",\t"+ers.getInt("count(*)")+",");
                                                 unta.appendText("\n"+sdatpfmat+" :\t"+dwcount);
                                                 
                                             }
                                             
                                              mwcount+=dwcount;
                                              dwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                              
                                              if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                              {
                                              
                                     //            Write the count values in the second csv file
                                                 s=1;
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append(""+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 //fileWriter.append("\n,,,,,,,,,,Month Count,"+mwcount+"\n");
                                                 mwcount=0;
                                                 //System.out.println("Success");                        
                                              }
                                              else
                                              {
                                                 if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                                 {
                                                      Thread.sleep(5);     
                                                      unpbar.setProgress(pi/(totdfpb+0.0));
                                                      pi++;
                                                      unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                      fileWriter.append(""+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 }
                                              }
                                     }
                                     
                                     
                                     if(!hbcb.equals("hb")&&dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i+1).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i+1).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nDay,count\n");
                                         }
                                         if(s==1)
                                         {
                                             //fileWriter.append("\n"+undpsd.getValue().plusDays(i-1).getMonth()+"\n");
                                             s=0;
                                         }
                                             

                                             
                                             fqry=qry1+sdatpfmat+qry2+edatpfmat+qry3;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 //System.out.println("\nUsage Notification Id:\t"+ers.getString("usage_notification_id")+"\tChannel:\t"+ers.getString("channel")+"\tStatus:\t"+ers.getString("status")+"\tProduct Line Code:\t"+ers.getString("product_line_code")+"\tCompany Pub X Ref Number:\t"+ers.getString("company_pub_x_ref_number")+"\tFile id:\t"+ers.getString("file_id")+"\tStart Date:\t"+ers.getTimestamp("create_date")+"\tEnd Date:\t"+ers.getTimestamp("update_date")+"\tInstance Swid :\t"+ers.getString("instance_swid")+"\tEntitlement_swid :\t"+ers.getString("entitlement_swid")+"\tGateway Serial Number :\t"+ers.getString("gateway_serial_number"));
                                                 //System.out.println("\nUsage ID:\t"+ers.getString("usage_id")+"\tInstance ID:\t"+ers.getString("instance_id")+"\tUsage Product ID:\t"+ers.getString("usage_product_id")+"\tUsage Qty:\t"+ers.getString("usage_qty")+"\tProcessing Timestamp:\t"+ers.getTimestamp("processing_timestamp")+"\tESRS Received Timestamp:\t"+ers.getTimestamp("esrs_received_timestamp")+"\tUsage File Timestamp:\t"+ers.getTimestamp("usage_file_timestamp")+"\tVE Instance SWID:\t"+ers.getString("ve_instance_swid"));
                                                 //System.out.println("\n"+ers.getInt("count(*)"));
                                                 System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                                                 //fileWriter.append(ers.getString("usage_id")+","+ers.getString("instance_id")+","+ers.getString("usage_product_id")+","+ers.getString("usage_qty")+","+ers.getTimestamp("processing_timestamp")+","+ers.getTimestamp("esrs_received_timestamp")+","+ers.getTimestamp("usage_file_timestamp")+","+ers.getString("ve_instance_swid")+"\n");
                                                 //fileWriter.append("\n"+,"+ers.getInt("count(*)"));
                                                 //fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 dwcount+=ers.getInt("count(*)");
                                                 fileWriter.append("\n"+sdatpfmat+",\t"+ers.getInt("count(*)")+",");
                                                 unta.appendText("\n"+sdatpfmat+" :\t"+dwcount);
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              mwcount+=dwcount;
                                              dwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                              
                                              if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                              {
                                              
                                     //            Write the count values in the second csv file
                                                 s=1;
                                                 
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append("\n,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 //fileWriter.append("\n,,,,,,,,,,Month Count,"+mwcount+"\n");
                                                 mwcount=0;
                                                 //System.out.println("Success");                        
                                              }
                                              else
                                              {
                                                 if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                                 {
                                                      unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                      fileWriter.append("\n,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 }
                                              }
                                     }
                                     
                                     if(hbcb.equals("hb")&&dbcb.equals("db")&&!mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nHour,count\n");
                                         }
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                             //qry2="' and create_date<'"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             //qry2="' and esrs_received_timestamp <='"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             fqry=qry1+sdatpfmat+stime+".00.00.000000000 "+hday+qry2+edatpfmat+stime+".59.59.999999999 "+hday+qry3;
                                             

                                             
                                             //fqry=qry1+sdatpfmat+qry2+edatpfmat+qry3;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 //System.out.println("\nUsage Notification Id:\t"+ers.getString("usage_notification_id")+"\tChannel:\t"+ers.getString("channel")+"\tStatus:\t"+ers.getString("status")+"\tProduct Line Code:\t"+ers.getString("product_line_code")+"\tCompany Pub X Ref Number:\t"+ers.getString("company_pub_x_ref_number")+"\tFile id:\t"+ers.getString("file_id")+"\tStart Date:\t"+ers.getTimestamp("create_date")+"\tEnd Date:\t"+ers.getTimestamp("update_date")+"\tInstance Swid :\t"+ers.getString("instance_swid")+"\tEntitlement_swid :\t"+ers.getString("entitlement_swid")+"\tGateway Serial Number :\t"+ers.getString("gateway_serial_number"));
                                                 //System.out.println("\nUsage ID:\t"+ers.getString("usage_id")+"\tInstance ID:\t"+ers.getString("instance_id")+"\tUsage Product ID:\t"+ers.getString("usage_product_id")+"\tUsage Qty:\t"+ers.getString("usage_qty")+"\tProcessing Timestamp:\t"+ers.getTimestamp("processing_timestamp")+"\tESRS Received Timestamp:\t"+ers.getTimestamp("esrs_received_timestamp")+"\tUsage File Timestamp:\t"+ers.getTimestamp("usage_file_timestamp")+"\tVE Instance SWID:\t"+ers.getString("ve_instance_swid"));
                                                 //System.out.println("\n"+ers.getInt("count(*)"));
                                                 //System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                                                 //fileWriter.append(ers.getString("usage_id")+","+ers.getString("instance_id")+","+ers.getString("usage_product_id")+","+ers.getString("usage_qty")+","+ers.getTimestamp("processing_timestamp")+","+ers.getTimestamp("esrs_received_timestamp")+","+ers.getTimestamp("usage_file_timestamp")+","+ers.getString("ve_instance_swid")+"\n");
                                                 //fileWriter.append("\n"+,"+ers.getInt("count(*)"));
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 hwcount+=ers.getInt("count(*)");
                                                 //fileWriter.append("\n"+sdatpfmat+",\t"+ers.getInt("count(*)")+",");
                                                 unta.appendText("\n"+stime+" "+hday+" \t"+ers.getInt("count(*)"));
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              dwcount+=hwcount;
                                              hwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                            /*
                                              if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                              {
                                              
                                     //            Write the count values in the second csv file
                                                 s=1;
                                                 
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append("\n,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 //fileWriter.append("\n,,,,,,,,,,Month Count,"+mwcount+"\n");
                                                 mwcount=0;
                                                 //System.out.println("Success");                        
                                              }
                                              else
                                              {
                                                 if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                                 {
                                                      unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                      fileWriter.append("\n,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 }
                                              }
                                            */
                                         }
                                         unta.appendText("\n                "+sdatpfmat+","+dwcount);
                                         fileWriter.append("\n,,,,,,"+sdatpfmat+","+dwcount);
                                         dwcount=0;
                                     }
                                     
                                     
                                     if(hbcb.equals("hb")&&!dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nHour,count\n");
                                         }
                                         if(s==1)
                                         {
                                             s=0;
                                         }
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                             //qry2="' and create_date<'"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             //qry2="' and esrs_received_timestamp <='"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             fqry=qry1+sdatpfmat+stime+".00.00.000000000 "+hday+qry2+edatpfmat+stime+".59.59.999999999 "+hday+qry3;
                                             

                                             
                                             //fqry=qry1+sdatpfmat+qry2+edatpfmat+qry3;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 //System.out.println("\nUsage Notification Id:\t"+ers.getString("usage_notification_id")+"\tChannel:\t"+ers.getString("channel")+"\tStatus:\t"+ers.getString("status")+"\tProduct Line Code:\t"+ers.getString("product_line_code")+"\tCompany Pub X Ref Number:\t"+ers.getString("company_pub_x_ref_number")+"\tFile id:\t"+ers.getString("file_id")+"\tStart Date:\t"+ers.getTimestamp("create_date")+"\tEnd Date:\t"+ers.getTimestamp("update_date")+"\tInstance Swid :\t"+ers.getString("instance_swid")+"\tEntitlement_swid :\t"+ers.getString("entitlement_swid")+"\tGateway Serial Number :\t"+ers.getString("gateway_serial_number"));
                                                 //System.out.println("\nUsage ID:\t"+ers.getString("usage_id")+"\tInstance ID:\t"+ers.getString("instance_id")+"\tUsage Product ID:\t"+ers.getString("usage_product_id")+"\tUsage Qty:\t"+ers.getString("usage_qty")+"\tProcessing Timestamp:\t"+ers.getTimestamp("processing_timestamp")+"\tESRS Received Timestamp:\t"+ers.getTimestamp("esrs_received_timestamp")+"\tUsage File Timestamp:\t"+ers.getTimestamp("usage_file_timestamp")+"\tVE Instance SWID:\t"+ers.getString("ve_instance_swid"));
                                                 //System.out.println("\n"+ers.getInt("count(*)"));
                                                 //System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                                                 //fileWriter.append(ers.getString("usage_id")+","+ers.getString("instance_id")+","+ers.getString("usage_product_id")+","+ers.getString("usage_qty")+","+ers.getTimestamp("processing_timestamp")+","+ers.getTimestamp("esrs_received_timestamp")+","+ers.getTimestamp("usage_file_timestamp")+","+ers.getString("ve_instance_swid")+"\n");
                                                 //fileWriter.append("\n"+,"+ers.getInt("count(*)"));
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 hwcount+=ers.getInt("count(*)");
                                                 //fileWriter.append("\n"+sdatpfmat+",\t"+ers.getInt("count(*)")+",");
                                                 unta.appendText("\n"+stime+" "+hday+" \t"+ers.getInt("count(*)"));
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              dwcount+=hwcount;
                                              hwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                            
                                         }
//                                         unta.appendText("\n                "+sdatpfmat+","+dwcount);
//                                         fileWriter.append("\n,,,,,,"+sdatpfmat+","+dwcount);
                                         mwcount=dwcount;
                                         dwcount=0;
                                         if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                         {
                                              
                                     //            Write the count values in the second csv file
                                             s=1;
                                                 
                                             unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                             fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                             //fileWriter.append("\n,,,,,,,,,,Month Count,"+mwcount+"\n");
                                             mwcount=0;
                                             //System.out.println("Success");                        
                                         }
                                         else
                                         {
                                             if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                             {
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                             }
                                         }
                                     }
                                     
                                     
                                     if(hbcb.equals("hb")&&dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nHour,count\n");
                                         }
                                         if(s==1)
                                         {
                                             s=0;
                                         }
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                             //qry2="' and create_date<'"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             //qry2="' and esrs_received_timestamp <='"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             fqry=qry1+sdatpfmat+stime+".00.00.000000000 "+hday+qry2+edatpfmat+stime+".59.59.999999999 "+hday+qry3;
                                             

                                             
                                             //fqry=qry1+sdatpfmat+qry2+edatpfmat+qry3;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 //System.out.println("\nUsage Notification Id:\t"+ers.getString("usage_notification_id")+"\tChannel:\t"+ers.getString("channel")+"\tStatus:\t"+ers.getString("status")+"\tProduct Line Code:\t"+ers.getString("product_line_code")+"\tCompany Pub X Ref Number:\t"+ers.getString("company_pub_x_ref_number")+"\tFile id:\t"+ers.getString("file_id")+"\tStart Date:\t"+ers.getTimestamp("create_date")+"\tEnd Date:\t"+ers.getTimestamp("update_date")+"\tInstance Swid :\t"+ers.getString("instance_swid")+"\tEntitlement_swid :\t"+ers.getString("entitlement_swid")+"\tGateway Serial Number :\t"+ers.getString("gateway_serial_number"));
                                                 //System.out.println("\nUsage ID:\t"+ers.getString("usage_id")+"\tInstance ID:\t"+ers.getString("instance_id")+"\tUsage Product ID:\t"+ers.getString("usage_product_id")+"\tUsage Qty:\t"+ers.getString("usage_qty")+"\tProcessing Timestamp:\t"+ers.getTimestamp("processing_timestamp")+"\tESRS Received Timestamp:\t"+ers.getTimestamp("esrs_received_timestamp")+"\tUsage File Timestamp:\t"+ers.getTimestamp("usage_file_timestamp")+"\tVE Instance SWID:\t"+ers.getString("ve_instance_swid"));
                                                 //System.out.println("\n"+ers.getInt("count(*)"));
                                                 //System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                                                 //fileWriter.append(ers.getString("usage_id")+","+ers.getString("instance_id")+","+ers.getString("usage_product_id")+","+ers.getString("usage_qty")+","+ers.getTimestamp("processing_timestamp")+","+ers.getTimestamp("esrs_received_timestamp")+","+ers.getTimestamp("usage_file_timestamp")+","+ers.getString("ve_instance_swid")+"\n");
                                                 //fileWriter.append("\n"+,"+ers.getInt("count(*)"));
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 hwcount+=ers.getInt("count(*)");
                                                 //fileWriter.append("\n"+sdatpfmat+",\t"+ers.getInt("count(*)")+",");
                                                 unta.appendText("\n"+stime+" "+hday+" \t"+ers.getInt("count(*)"));
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              dwcount+=hwcount;
                                              hwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                            
                                         }
                                         unta.appendText("\n                "+sdatpfmat+" :\t"+dwcount);
                                         fileWriter.append("\n,,,,,"+sdatpfmat+","+dwcount);
                                         mwcount+=dwcount;
                                         dwcount=0;
                                         if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                         {
                                              
                                     //            Write the count values in the second csv file
                                             s=1;
                                                 
                                             unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                             fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                             //fileWriter.append("\n,,,,,,,,,,Month Count,"+mwcount+"\n");
                                             mwcount=0;
                                             //System.out.println("Success");                        
                                         }
                                         else
                                         {
                                             if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                             {
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                             }
                                         }
                                     }
                                     
                                     i++;
                                 }
                                     
                                     
                                    
                                 Thread.sleep(50);
                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                 pi++;
                              }
                              //unpbar.setStyle("-fx-accent: green;");
                              //unpbar.setProgress(1);
                              //File simv.setImage("");
                              fileWriter.close();
                              ers.close();
                              esmt.close();
                              con.close();
                              Thread.holdsLock(false);
                              //System.out.println("\nQuerry:\t"+fqry);
                              //System.out.print(~10);
                         }
                         catch(Exception exc)
                         {
                             System.out.println("\nException inside case 1 :\n"+exc);
                         }
             
                         break;
                     }
                     case 2:
                     {
                         
                         
                          dposition=filname.indexOf('.');
                          for(m=dposition-1;filname.charAt(m)!='/';m--);
                          unta.appendText("\n Before the Last Slash count :\t"+m);
                          sfilname=filname.substring(0,m+1);
                          unta.appendText("\nFile Name Before :\t"+sfilname);
                          mfilname=filname.substring(m+1,dposition);
                          unta.appendText("\nMiddle File Name :\t"+mfilname);
                          ufilname=sfilname+mfilname+"_Usage_Notification.csv";
                          unta.appendText("\nUpdated FilName :\t"+ufilname);
                         
                         //totdfpb=j+3;
                         //Chages made for Progress indicator to go continuously but not sure it will work or not
                         //totdfpb=j;
                         
//                         int qtotdfpb=totdfpb/4;
//                         int htotdfpb=totdfpb/2;
//                         int ttotdfpb=totdfpb/3;
                         unpbar.setProgress(pi/(totdfpb+0.0));
                         pi++;
                         System.out.print("\nTotal Days:\t"+totdfpb);
                         try
                         {
                              
                              //fileWriter.append("Usage Notification Id,Channel,Status,Product Line Code,Company Pub X Ref Number,File ID,Create Date,Update Date,Instance SWID,Entitlement SWID,Gateway Serial Number\n");
                              //fileWriter.append("Usage ID,Instance ID,Usage Product ID,Usage Qty,Processing Timestamp,ESRS Received Timestamp,Usage File Timestamp,VE Instance SWID\n");
                              
                              //"\nUsage Notification Id:\t"+ers.getString("usage_notification_id")+"\tChannel:\t"+ers.getString("channel")+"\tStatus:\t"+ers.getString("status")+"\tProduct Line Code:\t"+ers.getString("product_line_code")+"\tCompany Pub X Ref Number:\t"+ers.getString("company_pub_x_ref_number")+"\tFile id:\t"+ers.getString("file_id")+"\tStart Date:\t"+ers.getDate("create_date")+"\tEnd Date:\t"+ers.getDate("update_date")+"\tInstance Swid :\t"+ers.getString("instance_swid")+"\tEntitlement_swid :\t"+ers.getString("entitlement_swid")+"\tGateway Serial Number :\t"+ers.getString("gateway_serial_number")
                              
                              FileWriter fileWriter = new FileWriter(ufilname);
                              Class.forName("oracle.jdbc.driver.OracleDriver");
                              Connection con = DriverManager.getConnection(dburl,dbuname,dbpword);
                              Statement esmt=con.createStatement();
                              ResultSet ers=null;
                              if(undpsd.getValue().isAfter(undped.getValue()))
                              {
                                 JOptionPane.showMessageDialog(null,"Sorry Can not Proceed..\nBecause the Start Date "+undpsd.getValue()+" is ahead of end date\n"+undped.getValue(),"Error",JOptionPane.ERROR_MESSAGE);
                                 //System.out.println("Sorry Can not Proceed..\nBecause the Start Date is ahead of end date\n"+undpsd.getValue().plusDays(1));
                              }
                              else
                              {
                                 
                                 System.out.println("\nSelected Dates are :\t");
                                 int i=0;
                                 Thread.sleep(50);
                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                 pi++;
                                 int s=0;
                                 while((undpsd.getValue().plusDays(i).isBefore(undped.getValue()))||(undpsd.getValue().plusDays(i).isEqual(undped.getValue())))
                                 {
                                     
                                     
                                     System.out.println("\n"+undpsd.getValue().plusDays(i));
                                         
                                     if(hbcb.equals("hb")&&!dbcb.equals("db")&&!mbcb.equals("mb"))
                                     {
                                         
                                         if(i==0)
                                             fileWriter.append("Hours,Count\n");
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         fileWriter.append("\n\n"+sdatpfmat+"\n");
                                         unta.appendText("\n\n"+sdatpfmat+"\n");
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                             //qry2="' and create_date<'"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             //qry2="' and esrs_received_timestamp <='"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             fqry=qry12+sdatpfmat+stime+".00.00.000000000 "+hday+qry22+edatpfmat+stime+".59.59.999999999 "+hday+qry32;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 
                                                 System.out.println(""+stime+"\t"+hday+" :\t"+ers.getInt("count(*)"));
                                                 
                                                 hwcount+=ers.getInt("count(*)");
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+hwcount);
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                             unta.appendText("\n"+stime+" "+hday+" :\t"+hwcount);
                                             dwcount+=hwcount;
                                             
                                             hwcount=0;
                                             
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                         }
                          
                                         Thread.sleep(20);     
                                         unpbar.setProgress(pi/(totdfpb+0.0));
                                         pi++;    
                                         System.out.println("\nDay Wise Count:\t"+dwcount);
                                         mwcount+=dwcount;
                                         dwcount=0;
                          
                                     }
                                     
                                     if(!hbcb.equals("hb")&&dbcb.equals("db")&&!mbcb.equals("mb"))
                                     {
                          
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i+1).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i+1).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                          
                                             
                                             fqry=qry12+sdatpfmat+qry22+edatpfmat+qry32;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                          
                                                 System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                          
                                                 dwcount+=ers.getInt("count(*)");
                                                 fileWriter.append(sdatpfmat+",\t"+ers.getInt("count(*)")+",");
                                                 unta.appendText("\n"+sdatpfmat+" :\t"+dwcount);
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             fileWriter.append("\n");
                              
                                             dwcount=0;
                                             
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }

                                              mwcount+=dwcount;
                                              
                                     }
                                     
                                     if(!hbcb.equals("hb")&&!dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i+1).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i+1).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nMonth,count\n");
                                         }
                                         if(s==1)
                                         {
                                             s=0;
                                         }
                                             

                                             
                                             fqry=qry12+sdatpfmat+qry22+edatpfmat+qry32;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 
                                                 System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                                                 
                                                 dwcount+=ers.getInt("count(*)");
                                                 
                                                 unta.appendText("\n"+sdatpfmat+" :\t"+dwcount);
                                                 
                                             }
                                             
                                              mwcount+=dwcount;
                                              dwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                              
                                              if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                              {
                                              
                                     
                                                 s=1;
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append(""+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                     
                                                 mwcount=0;
                                     
                                              }
                                              else
                                              {
                                                 if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                                 {
                                                      Thread.sleep(5);     
                                                      unpbar.setProgress(pi/(totdfpb+0.0));
                                                      pi++;
                                                      unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                      fileWriter.append(""+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 }
                                              }
                                     }
                                     
                                     
                                     if(!hbcb.equals("hb")&&dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                     
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i+1).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i+1).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nDay,count\n");
                                         }
                                         if(s==1)
                                         {
                                             s=0;
                                         }
                                             

                                             
                                             fqry=qry12+sdatpfmat+qry22+edatpfmat+qry32;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                             
                                                 System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                                             
                                                 dwcount+=ers.getInt("count(*)");
                                                 fileWriter.append("\n"+sdatpfmat+",\t"+ers.getInt("count(*)")+",");
                                                 unta.appendText("\n"+sdatpfmat+" :\t"+dwcount);
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              mwcount+=dwcount;
                                              dwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                              
                                              if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                              {
                                              
                                     
                                                 s=1;
                                                 
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append("\n,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 //fileWriter.append("\n,,,,,,,,,,Month Count,"+mwcount+"\n");
                                                 mwcount=0;
                                                 //System.out.println("Success");                        
                                              }
                                              else
                                              {
                                                 if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                                 {
                                                      unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                      fileWriter.append("\n,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 }
                                              }
                                     }
                                     
                                     if(hbcb.equals("hb")&&dbcb.equals("db")&&!mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nHour,count\n");
                                         }
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                             
                                             fqry=qry12+sdatpfmat+stime+".00.00.000000000 "+hday+qry22+edatpfmat+stime+".59.59.999999999 "+hday+qry32;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 hwcount+=ers.getInt("count(*)");
                                                 unta.appendText("\n"+stime+" "+hday+" \t"+ers.getInt("count(*)"));
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              dwcount+=hwcount;
                                              hwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                            
                                         }
                                         unta.appendText("\n                "+sdatpfmat+","+dwcount);
                                         fileWriter.append("\n,,,,,,"+sdatpfmat+","+dwcount);
                                         dwcount=0;
                                     }
                                     
                                     
                                     if(hbcb.equals("hb")&&!dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                         
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nHour,count\n");
                                         }
                                         if(s==1)
                                         {
                                             s=0;
                                         }
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                         
                                             fqry=qry12+sdatpfmat+stime+".00.00.000000000 "+hday+qry22+edatpfmat+stime+".59.59.999999999 "+hday+qry32;
                                             
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                             
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 hwcount+=ers.getInt("count(*)");
                                             
                                                 unta.appendText("\n"+stime+" "+hday+" \t"+ers.getInt("count(*)"));
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              dwcount+=hwcount;
                                              hwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                            
                                         }

                                         mwcount=dwcount;
                                         dwcount=0;
                                         if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                         {
                                             s=1;
                                             unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                             fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                             
                                             mwcount=0;
                                             
                                         }
                                         else
                                         {
                                             if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                             {
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                             }
                                         }
                                     }
                                     
                                     
                                     if(hbcb.equals("hb")&&dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nHour,count\n");
                                         }
                                         if(s==1)
                                         {
                                             s=0;
                                         }
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                            
                                             fqry=qry12+sdatpfmat+stime+".00.00.000000000 "+hday+qry22+edatpfmat+stime+".59.59.999999999 "+hday+qry32;
                                             
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                             
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 hwcount+=ers.getInt("count(*)");
                                             
                                                 unta.appendText("\n"+stime+" "+hday+" \t"+ers.getInt("count(*)"));
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              dwcount+=hwcount;
                                              hwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                            
                                         }
                                         unta.appendText("\n                "+sdatpfmat+" :\t"+dwcount);
                                         fileWriter.append("\n,,,,,"+sdatpfmat+","+dwcount);
                                         mwcount+=dwcount;
                                         dwcount=0;
                                         if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                         {
                                     
                                             s=1;
                                                 
                                             unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                             fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                     
                                             mwcount=0;
                                             //System.out.println("Success");                        
                                         }
                                         else
                                         {
                                             if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                             {
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                             }
                                         }
                                     }
                                     
                                     i++;
                                 }
                                     
                                     
                                    
                                 Thread.sleep(50);
                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                 pi++;
                              }
                              //unpbar.setStyle("-fx-accent: green;");
                              //unpbar.setProgress(1);
                              //File simv.setImage("");
                              fileWriter.close();
                              ers.close();
                              esmt.close();
                              con.close();
                              Thread.holdsLock(false);
                              //System.out.println("\nQuerry:\t"+fqry);
                              //System.out.print(~10);
                         }
                         catch(Exception exc)
                         {
                             System.out.println("\nException inside case 1 :\n"+exc);
                         }
                         
                         break;
                     }
                     case 3:
                     {
                         
                          dposition=filname.indexOf('.');
                          for(m=dposition-1;filname.charAt(m)!='/';m--);
                          unta.appendText("\n Before the Last Slash count :\t"+m);
                          sfilname=filname.substring(0,m+1);
                          unta.appendText("\nFile Name Before :\t"+sfilname);
                          mfilname=filname.substring(m+1,dposition);
                          unta.appendText("\nMiddle File Name :\t"+mfilname);
                          ufilname=sfilname+mfilname+"_Entitlement_Snapshot.csv";
                          unta.appendText("\nUpdated FilName :\t"+ufilname);
                         
                         //totdfpb=j+3;
                         //Chages made for Progress indicator to go continuously but not sure it will work or not
                         //totdfpb=j;
                         
//                         int qtotdfpb=totdfpb/4;
//                         int htotdfpb=totdfpb/2;
//                         int ttotdfpb=totdfpb/3;
                         unpbar.setProgress(pi/(totdfpb+0.0));
                         pi++;
                         System.out.print("\nTotal Days:\t"+totdfpb);
                         try
                         {
                              
                              //fileWriter.append("Usage Notification Id,Channel,Status,Product Line Code,Company Pub X Ref Number,File ID,Create Date,Update Date,Instance SWID,Entitlement SWID,Gateway Serial Number\n");
                              //fileWriter.append("Usage ID,Instance ID,Usage Product ID,Usage Qty,Processing Timestamp,ESRS Received Timestamp,Usage File Timestamp,VE Instance SWID\n");
                              
                              //"\nUsage Notification Id:\t"+ers.getString("usage_notification_id")+"\tChannel:\t"+ers.getString("channel")+"\tStatus:\t"+ers.getString("status")+"\tProduct Line Code:\t"+ers.getString("product_line_code")+"\tCompany Pub X Ref Number:\t"+ers.getString("company_pub_x_ref_number")+"\tFile id:\t"+ers.getString("file_id")+"\tStart Date:\t"+ers.getDate("create_date")+"\tEnd Date:\t"+ers.getDate("update_date")+"\tInstance Swid :\t"+ers.getString("instance_swid")+"\tEntitlement_swid :\t"+ers.getString("entitlement_swid")+"\tGateway Serial Number :\t"+ers.getString("gateway_serial_number")
                              
                              FileWriter fileWriter = new FileWriter(ufilname);
                              Class.forName("oracle.jdbc.driver.OracleDriver");
                              Connection con = DriverManager.getConnection(dburl,dbuname,dbpword);
                              Statement esmt=con.createStatement();
                              ResultSet ers=null;
                              if(undpsd.getValue().isAfter(undped.getValue()))
                              {
                                 JOptionPane.showMessageDialog(null,"Sorry Can not Proceed..\nBecause the Start Date "+undpsd.getValue()+" is ahead of end date\n"+undped.getValue(),"Error",JOptionPane.ERROR_MESSAGE);
                                 //System.out.println("Sorry Can not Proceed..\nBecause the Start Date is ahead of end date\n"+undpsd.getValue().plusDays(1));
                              }
                              else
                              {
                                 
                                 System.out.println("\nSelected Dates are :\t");
                                 int i=0;
                                 Thread.sleep(50);
                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                 pi++;
                                 int s=0;
                                 while((undpsd.getValue().plusDays(i).isBefore(undped.getValue()))||(undpsd.getValue().plusDays(i).isEqual(undped.getValue())))
                                 {
                                     
                                     
                                     System.out.println("\n"+undpsd.getValue().plusDays(i));
                                         
                                     if(hbcb.equals("hb")&&!dbcb.equals("db")&&!mbcb.equals("mb"))
                                     {
                                         
                                         if(i==0)
                                             fileWriter.append("Hours,Count\n");
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         fileWriter.append("\n\n"+sdatpfmat+"\n");
                                         unta.appendText("\n\n"+sdatpfmat+"\n");
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                             //qry2="' and create_date<'"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             //qry2="' and esrs_received_timestamp <='"+edatpfmat+stime+".59.59.999999999 "+hday+"' order by 1 asc";
                                             fqry=qry13+sdatpfmat+stime+".00.00.000000000 "+hday+qry23+edatpfmat+stime+".59.59.999999999 "+hday+qry33;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 
                                                 System.out.println(""+stime+"\t"+hday+" :\t"+ers.getInt("count(*)"));
                                                 
                                                 hwcount+=ers.getInt("count(*)");
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+hwcount);
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                             unta.appendText("\n"+stime+" "+hday+" :\t"+hwcount);
                                             dwcount+=hwcount;
                                             
                                             hwcount=0;
                                             
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                         }
                          
                                         Thread.sleep(20);     
                                         unpbar.setProgress(pi/(totdfpb+0.0));
                                         pi++;    
                                         System.out.println("\nDay Wise Count:\t"+dwcount);
                                         mwcount+=dwcount;
                                         dwcount=0;
                          
                                     }
                                     
                                     if(!hbcb.equals("hb")&&dbcb.equals("db")&&!mbcb.equals("mb"))
                                     {
                          
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i+1).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i+1).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                          
                                             
                                             fqry=qry13+sdatpfmat+qry23+edatpfmat+qry33;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                          
                                                 System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                          
                                                 dwcount+=ers.getInt("count(*)");
                                                 fileWriter.append(sdatpfmat+",\t"+ers.getInt("count(*)")+",");
                                                 unta.appendText("\n"+sdatpfmat+" :\t"+dwcount);
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             fileWriter.append("\n");
                              
                                             dwcount=0;
                                             
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }

                                              mwcount+=dwcount;
                                              
                                     }
                                     
                                     if(!hbcb.equals("hb")&&!dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i+1).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i+1).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nMonth,count\n");
                                         }
                                         if(s==1)
                                         {
                                             s=0;
                                         }
                                             

                                             
                                             fqry=qry13+sdatpfmat+qry23+edatpfmat+qry33;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 
                                                 System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                                                 
                                                 dwcount+=ers.getInt("count(*)");
                                                 
                                                 unta.appendText("\n"+sdatpfmat+" :\t"+dwcount);
                                                 
                                             }
                                             
                                              mwcount+=dwcount;
                                              dwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                              
                                              if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                              {
                                              
                                     
                                                 s=1;
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append(""+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                     
                                                 mwcount=0;
                                     
                                              }
                                              else
                                              {
                                                 if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                                 {
                                                      Thread.sleep(5);     
                                                      unpbar.setProgress(pi/(totdfpb+0.0));
                                                      pi++;
                                                      unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                      fileWriter.append(""+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 }
                                              }
                                     }
                                     
                                     
                                     if(!hbcb.equals("hb")&&dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                     
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i+1).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i+1).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nDay,count\n");
                                         }
                                         if(s==1)
                                         {
                                             s=0;
                                         }
                                             

                                             
                                             fqry=qry13+sdatpfmat+qry23+edatpfmat+qry33;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                             
                                                 System.out.println(""+sdatpfmat+" :\t"+ers.getInt("count(*)"));
                                             
                                                 dwcount+=ers.getInt("count(*)");
                                                 fileWriter.append("\n"+sdatpfmat+",\t"+ers.getInt("count(*)")+",");
                                                 unta.appendText("\n"+sdatpfmat+" :\t"+dwcount);
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              mwcount+=dwcount;
                                              dwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                              
                                              if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                              {
                                              
                                     
                                                 s=1;
                                                 
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append("\n,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 //fileWriter.append("\n,,,,,,,,,,Month Count,"+mwcount+"\n");
                                                 mwcount=0;
                                                 //System.out.println("Success");                        
                                              }
                                              else
                                              {
                                                 if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                                 {
                                                      unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                      fileWriter.append("\n,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 }
                                              }
                                     }
                                     
                                     if(hbcb.equals("hb")&&dbcb.equals("db")&&!mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nHour,count\n");
                                         }
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                             
                                             fqry=qry13+sdatpfmat+stime+".00.00.000000000 "+hday+qry23+edatpfmat+stime+".59.59.999999999 "+hday+qry33;
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 hwcount+=ers.getInt("count(*)");
                                                 unta.appendText("\n"+stime+" "+hday+" \t"+ers.getInt("count(*)"));
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              dwcount+=hwcount;
                                              hwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                            
                                         }
                                         unta.appendText("\n                "+sdatpfmat+","+dwcount);
                                         fileWriter.append("\n,,,,,,"+sdatpfmat+","+dwcount);
                                         dwcount=0;
                                     }
                                     
                                     
                                     if(hbcb.equals("hb")&&!dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                         
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nHour,count\n");
                                         }
                                         if(s==1)
                                         {
                                             s=0;
                                         }
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                         
                                             fqry=qry13+sdatpfmat+stime+".00.00.000000000 "+hday+qry23+edatpfmat+stime+".59.59.999999999 "+hday+qry33;
                                             
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             while(ers.next())
                                             {
                                             
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 hwcount+=ers.getInt("count(*)");
                                             
                                                 unta.appendText("\n"+stime+" "+hday+" \t"+ers.getInt("count(*)"));
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              dwcount+=hwcount;
                                              hwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                            
                                         }

                                         mwcount=dwcount;
                                         dwcount=0;
                                         if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                         {
                                             s=1;
                                             unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                             fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                             
                                             mwcount=0;
                                             
                                         }
                                         else
                                         {
                                             if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                             {
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                             }
                                         }
                                     }
                                     
                                     
                                     if(hbcb.equals("hb")&&dbcb.equals("db")&&mbcb.equals("mb"))
                                     {
                                         //FileWriter fileWriter = new FileWriter(filname);
                                         sdatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i).toString().substring(2, 4);
                                         edatpfmat=undpsd.getValue().plusDays(i).toString().substring(8, 10)+"-"+undpsd.getValue().plusDays(i).getMonth().toString().substring(0, 3)+"-"+undpsd.getValue().plusDays(i+1).toString().substring(2, 4);
                                         if(i==0)
                                         {
                                             fileWriter.append("\n\nHour,count\n");
                                         }
                                         if(s==1)
                                         {
                                             s=0;
                                         }
                                         for(int k=1;k<=24;k++)
                                         {
                                             if(k>12)
                                             {
                                                 if(k!=24)
                                                 {
                                                     hday="PM";
                                                 }
                                                 else
                                                 {
                                                     hday="AM";
                                                 }
                                                 if(k!=12)
                                                 {
                                                     if(k==24)
                                                     {
                                                         stime=" 12";
                                                     }
                                                     else
                                                     {
                                                         stime=" "+(k%12);
                                                     }
                                                 } 
                                             }
                                             else
                                             {
                                                 if(k!=12)
                                                 {
                                                     hday="AM";
                                                 }  
                                                 else
                                                 {
                                                     hday="PM";
                                                 }
                                                 stime=" "+k;
                                             }
                                            
                                             fqry=qry13+sdatpfmat+stime+".00.00.000000000 "+hday+qry23+edatpfmat+stime+".59.59.999999999 "+hday+qry33;
                                             
                                             System.out.println("\nQuerry:\t"+fqry);
                                             ers=esmt.executeQuery(fqry);
                                             //hwcount=0;
                                             //dwcount=0;
                                             //mwcount=0;
                                             while(ers.next())
                                             {
                                             
                                                 fileWriter.append("\n"+stime+" "+hday+" :,"+ers.getInt("count(*)"));
                                                 hwcount+=ers.getInt("count(*)");
                                             
                                                 unta.appendText("\n"+stime+" "+hday+" \t"+ers.getInt("count(*)"));
                                                 Thread.sleep(5);     
                                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                                 pi++;
                                             }
                                             
                                              dwcount+=hwcount;
                                              hwcount=0;
                                              
                                             if(pi<totdfpb/4)
                                             {
                                                 unpbar.setStyle("-fx-accent: red;");
                                             }
                                             else
                                             {
                                                 if((pi>totdfpb/4)&&(pi<totdfpb/2))
                                                 {
                                                     unpbar.setStyle("-fx-accent: orange;");
                                                 }
                                                 else
                                                 {
                                                     if((pi>totdfpb/2)&&(pi<((totdfpb*3)/4)))
                                                     {
                                                         unpbar.setStyle("-fx-accent: yellow;");
                                                     }
                                                     else
                                                     {
                                                         if(pi>((totdfpb*3)/4))
                                                         {
                                                             unpbar.setStyle("-fx-accent: green;");
                                                         }
                                                     }
                                                 }    
                                             }
                                            
                                         }
                                         unta.appendText("\n                "+sdatpfmat+" :\t"+dwcount);
                                         fileWriter.append("\n,,,,,"+sdatpfmat+","+dwcount);
                                         mwcount+=dwcount;
                                         dwcount=0;
                                         if((undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue()))||(undpsd.getValue().plusDays(i+1).getMonthValue()>(undpsd.getValue().plusDays(i).getMonthValue())))
                                         {
                                     
                                             s=1;
                                                 
                                             unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                             fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                     
                                             mwcount=0;
                                             //System.out.println("Success");                        
                                         }
                                         else
                                         {
                                             if((undpsd.getValue().plusDays(i).isEqual(undped.getValue()))&&s==0)
                                             {
                                                 unta.appendText("\nMonth wise Count :\t"+undpsd.getValue().plusDays(i).getMonth()+" :\t"+mwcount);
                                                 fileWriter.append("\n,,,,,,,,"+undpsd.getValue().plusDays(i).getMonth()+","+mwcount+"\n");
                                                 //mwcount=0;
                                             }
                                         }
                                     }
                                     
                                     i++;
                                 }
                                     
                                     
                                    
                                 Thread.sleep(50);
                                 unpbar.setProgress(pi/(totdfpb+0.0));
                                 pi++;
                              }
                              //unpbar.setStyle("-fx-accent: green;");
                              //unpbar.setProgress(1);
                              //File simv.setImage("");
                              unpbar.setProgress(1);
                              fileWriter.close();
                              ers.close();
                              esmt.close();
                              con.close();
                              //Thread.holdsLock(false);
                              //System.out.println("\nQuerry:\t"+fqry);
                              //System.out.print(~10);
                         }
                         catch(Exception exc)
                         {
                             System.out.println("\nException inside case 1 :\n"+exc);
                         }
                         
                         break;
                     }
                 }
                 ctab=""+(Integer.parseInt(ctab)/10);
             }
             
//             while((undpsd.getValue().plusDays(j).isBefore(undped.getValue()))||(undpsd.getValue().plusDays(j).isEqual(undped.getValue())))
//             {
//                j++;
//             }
             
        }
    }

    @FXML
    private void ExtractData(ActionEvent event) 
    {
      String temp[]={"1","2","3"};
      double values[]={1,2,3,4};
      XYChart.Series set1= new XYChart.Series<>();
      
      set1.getData().add(new XYChart.Data("Pravin",80.56));
      set1.getData().add(new XYChart.Data("navin",100.00));
      set1.getData().add(new XYChart.Data("Vignesh",94.32));
      set1.getData().add(new XYChart.Data("Tamil Selvan",79.44));
      set1.setName("Yoo");
      bgraph.getData().addAll(set1);
      clpcb="";
      uncb="";
      esscb="";
      hbcb="";
      dbcb="";
      mbcb="";
      m=0;
      sfilname="";
      mfilname="";
      ufilname="";
      dposition=0;
      filname="";
      
      if(!(ptcbclp.isSelected()||ptcbunot.isSelected()||ptcbess.isSelected()))
      {
          JOptionPane.showMessageDialog(null,"Please select the Tables from which the data has to be extracted","Error",JOptionPane.ERROR_MESSAGE);
      }
      else
      {
          if(!(psmcbhb.isSelected()||psmcbdb.isSelected()||psmcbmb.isSelected()))
          {
              JOptionPane.showMessageDialog(null,"Please select the Data Segregation by which the data has to be extracted","Error",JOptionPane.ERROR_MESSAGE);              
          }
          else
          {
              if(ptcbclp.isSelected())
              {
                  unta.appendText("\nCLP Selected :\t");
                  clpcb="clp";                  
              }
              if(ptcbunot.isSelected())
              {
                  unta.appendText("\nUsage Notification Selected");
                  uncb="unot";                  
              }
              if(ptcbess.isSelected())
              {
                  unta.appendText("\nEntitlement Sanpshot Selected");
                  esscb="ess";
              }
              if(psmcbhb.isSelected())
              {
                  unta.appendText("\nHourly Basis Selected");
                  hbcb="hb";
              }
              if(psmcbdb.isSelected())
              {
                  unta.appendText("\nDaily Basis Selected");
                  dbcb="db";
              }
              if(psmcbmb.isSelected())
              {
                  unta.appendText("\nMonthly Basis Selected");
                  mbcb="mb";
              }
              
              //unta.appendText(""+clpcb);
              FileChooser fc=new FileChooser();
              File selectedfile= fc.showSaveDialog(null);
              filname=selectedfile.getPath();
              filname=filname.replaceAll("\\\\","/");
              if(!filname.equals(""))
              {  
                  Thread unth1=new Thread(new PUNotificationEData());
                  if((""+filname.charAt(filname.length()-4)).equals("."))
                  {     
                     if(filname.substring(((filname.length())-4),(filname.length())).equalsIgnoreCase(".csv"))
                     {
                         unta.appendText("\n Inside File Chooser In correct Format :\t"+filname);
                         unth1.start();
                     }
                     else
                     {
                         JOptionPane.showMessageDialog(null,"You can Save the contents only in an .CSV file\nPlease choose a CSV file and then try again\n","Warning",JOptionPane.WARNING_MESSAGE);
                     }
                  }
                  else
                  {
                     filname+=".csv";
                     unta.appendText("\n Inside File Chooser Incorrect Format after adding the extension :\t"+filname);
                     unth1.start();
                  }
              } 
              else
              {
                  JOptionPane.showMessageDialog(null,"You haven't selected the file where the data has to be written\nPlease Enter the File Path...\n","Warning",JOptionPane.WARNING_MESSAGE);
              }              
          }
      }
    }

    @FXML
    private void CTBCLP(ActionEvent event) 
    {
        //clpcb="clp";
    }

    @FXML
    private void PTCBUN(ActionEvent event) 
    {
            //uncb="unot";
    }

    @FXML
    private void PTCBESS(ActionEvent event) 
    {
        //esscb="ess";
    }

    @FXML
    private void PSMCBHB(ActionEvent event) 
    {
        //hbcb="hour";
    }

    @FXML
    private void PSMCBDB(ActionEvent event) 
    {
        //dbcb="daily";
    }

    @FXML
    private void PSMCBMB(ActionEvent event) 
    {
        //mbcb="month";
    }

    @FXML
    private void uNotificationSChanged(Event event) 
    {
        
    }
    
}
