/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;


/**
 * FXML Controller class
 *
 * @author duraip
 */
public class ELMS_R_Generator_FXMLController implements Initializable {

    @FXML
    private AnchorPane extractor;
    @FXML
    private Label label;
    @FXML
    private TabPane TabbedPane;
    @FXML
    private Tab isilon;
    @FXML
    private AnchorPane isln;
    @FXML
    private Label lstid;
    @FXML
    private TextField tfstid;
    @FXML
    private Label letid;
    @FXML
    private TextField tfetid;
    @FXML
    private Button biedata;
    @FXML
    private ProgressBar ipbar;
    @FXML
    private Label lsuccess;
    @FXML
    private TextField tfsuccess;
    @FXML
    private Label ipsuccess;
    @FXML
    private TextField tfipsuccess;
    @FXML
    private Label lfailed;
    @FXML
    private TextField tffailed;
    @FXML
    private Button bifind;
    @FXML
    private Button Boall;
    @FXML
    private RadioButton rinf;
    @FXML
    private ToggleGroup Parent;
    @FXML
    private RadioButton rninf;
    @FXML
    private RadioButton rcus;
    @FXML
    private ToggleGroup User;
    @FXML
    private RadioButton remp;
    @FXML
    private RadioButton rpart;
    @FXML
    private RadioButton rsml;
    @FXML
    private ToggleGroup FilSize;
    @FXML
    private RadioButton rmed;
    @FXML
    private RadioButton rlrg;
    @FXML
    private Tab saasNPI;
    @FXML
    private AnchorPane saas;
    @FXML
    private Label userbasedlaccreation;
    @FXML
    private Label userbasedactivation;
    @FXML
    private Label autoactivlaccreation;
    @FXML
    private Label ulsn;
    @FXML
    private Label ules;
    @FXML
    private Label uasn;
    @FXML
    private Label uaen;
    @FXML
    private Label alsn;
    @FXML
    private Label alen;
    @FXML
    private TextField ulsntf;
    @FXML
    private TextField ulentf;
    @FXML
    private TextField uasntf;
    @FXML
    private TextField uaentf;
    @FXML
    private TextField alsntf;
    @FXML
    private TextField alentf;
    @FXML
    private ProgressBar pbar1;
    @FXML
    private Button extractDate;
    @FXML
    private Label linfo;
    @FXML
    private Button BUC;
    @FXML
    private Button BAAC;
    @FXML
    private Label lcomplete;
    @FXML
    private Label lvalidated;
    @FXML
    private TextField tfubc;
    @FXML
    private TextField tfubv;
    @FXML
    private TextField tfaac;
    @FXML
    private TextField tfaav;
    @FXML
    private Tab usageThroughput;
    @FXML
    private AnchorPane usage;
    @FXML
    private Label heading;
    @FXML
    private Label ssno;
    @FXML
    private Label esno;
    @FXML
    private Label setid;
    @FXML
    private Label eetid;
    @FXML
    private Label scid;
    @FXML
    private Label ecid;
    @FXML
    private TextField ssnotf;
    @FXML
    private TextField esnotf;
    @FXML
    private TextField scidtf;
    @FXML
    private TextField ecidtf;
    @FXML
    private TextField settf;
    @FXML
    private TextField eettf;
    @FXML
    private ProgressBar pbar;
    @FXML
    private Button start;
    @FXML
    private Label linfo2;
    
    String itfilname;
    String sfilname="";
    String filname="";
    String dburl="jdbc:oracle:thin:@//elmorastg01.isus.emc.com:1721/elmf";
    //"jdbc:oracle:thin:@//elmorastg01.isus.emc.com:1721/elmf";  //Perf URL
    //"jdbc:oracle:thin:@//elmoratst01.isus.emc.com:1721/elmbtapp" //Test5 URL
    String dbuname="POETK";
    //"POETK";  //Perf
    //"POETK_T3"  //Test5
    String dbpword="perf3k";
    //"perf3k";  //Perf
    //"elm3kbt";  Test5  
    int stno;
    int eno;
    int i=1;
    int j=4;
    DateFormat df = new SimpleDateFormat("YYY-MM-dd hh:mm:ss.SSS");
    
    
    Connection con = null;
    Statement stmt = null;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }

class TThread1 implements Runnable
    {

        @Override
        public void run() 
        {
           HSSFWorkbook workbook = new HSSFWorkbook();
           HSSFSheet sheet1=workbook.createSheet("EntCreate_01_EntitlementCreation");
           HSSFSheet sheet2=workbook.createSheet("EntCreate_02_EOMtoEntRouter");
           HSSFSheet sheet3=workbook.createSheet("EntCreate_03_AckService");
           HSSFSheet sheet4=workbook.createSheet("EntCreate_04_LacSend");
           HSSFSheet sheet5=workbook.createSheet("EntCreate_05_CompleteOrderProcessing");
           HSSFSheet sheet6=workbook.createSheet("Act_08_AckService");
           HSSFSheet sheet7=workbook.createSheet("AA_EntCreate_01_EntitlementCreation");
           HSSFSheet sheet8=workbook.createSheet("AA_EntCreate_02_EOMtoEntRouter");
           HSSFSheet sheet9=workbook.createSheet("AA_EntCreate_03_AckService (Discarded)");
           HSSFSheet sheet10=workbook.createSheet("AA_Act_01_Activate");
           HSSFSheet sheet11=workbook.createSheet("AA_Act_02_AckService");
           HSSFSheet sheet12=workbook.createSheet("AA__Act_03_LacSend");
           HSSFSheet sheet13=workbook.createSheet("AA_CompleteOrderProcessing");
           String title[]=new String[14];
           for(int i=0;i<14;i++)
           {
               title[i]=new String();
           }
           title[0]="EntCreate_01_EntitlementCreation";
           title[1]="EntCreate_02_EOMtoEntRouter";
           title[2]="EntCreate_03_AckService";
           title[3]="EntCreate_04_LacSend";
           title[4]="EntCreate_05_CompleteOrderProcessing";
           title[5]="Act_08_AckService";
           title[6]="AA_EntCreate_01_EntitlementCreation";
           title[7]="AA_EntCreate_02_EOMtoEntRouter";
           title[8]="AA_EntCreate_03_AckService (Discarded)";
           title[9]="AA_Act_01_Activate";
           title[10]="AA_Act_02_AckService";
           title[11]="AA__Act_03_LacSend";
           title[12]="AA_CompleteOrderProcessing";
           title[13]="Process Successfully completed";
           pbar1.setProgress(0.02000);
           //pbar1.setStringPainted(true);
           
           String q5="";//select distinct(status) from emc_ui_usage_notification where usage_notification_id>'"+ssnotf.getText()+"' and usage_notification_id<'"+esnotf.getText()+"'";
           //String fname=tffnam.getText();
           //sfilname=sfilname.replaceAll("\\\\","/");
           if(!(""+sfilname.charAt(sfilname.length()-4)).equals("."))
           {
               sfilname=sfilname+".xls";
           }
           int loop2=0;
           int val=0;
           int i;
           int j;
           linfo.setVisible(true);
           
           if(!(ulsntf.getText().equals("")||ulsntf.getText().equals(" "))&&!(ulentf.getText().equals("")||ulentf.getText().equals(" "))&&!(uasntf.getText().equals("")||uasntf.getText().equals(" "))&&!(uaentf.getText().equals("")||uaentf.getText().equals(" "))&&!(alsntf.getText().equals("")||alsntf.getText().equals(" "))&&!(alentf.getText().equals("")||alentf.getText().equals(" ")))
           {
                try
                {
                    
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    con = DriverManager.getConnection(dburl,dbuname,dbpword);
                    Statement ssmt=con.createStatement();
                    ResultSet srs=null;
               
               
                     
                     
                     Thread.sleep(100);
                     
                     for(;loop2<13;loop2++)
                     {
                         Map<String,Object[]> data = new TreeMap<String,Object[]>();
                   
                        i=1;
                        j=4;
                        switch(loop2)
                        {
                             case 0:
                                     data.put("1", new Object[] {"SO Number","Transaction Date","Create Date"});
                                     q5="select t2.so_number, t1.transaction_date, t2.create_date from emc_om_transaction t1, emc_om_so t2 where t1.status='SAASHOLD' and t1.so_id=t2.so_id and t2.so_number >='"+ulsntf.getText()+"' and t2.so_number<='"+ulentf.getText()+"'";
                                     val=0;
                                     break;
                             case 1:
                                     q5="select t1.so_number,t1.xid, t1.create_date,t2.transaction_date from emc_om_entro_msg_detl t1, emc_om_transaction t2, emc_om_so t3 where t2.status='SAASHOLD' and t1.status='COMPLETE' and t1.queue_name='SAAS-TEST' and t1.so_number=t3.so_number and t3.so_id=t2.so_id and t1.so_number>'"+ulsntf.getText()+"' and t1.so_number<='"+ulentf.getText()+"'";
                                     data.put("1", new Object[] {"SO Number","XID","Create Date","Transaction Date"});
                                     val=1;
                                     break;
                             case 2:
                                     q5="select so_number,xid,upd_date,create_Date from emc_om_entro_msg_detl where status='COMPLETE' and queue_name='SAAS-TEST' and so_number>='"+ulsntf.getText()+"' and so_number<='"+ulentf.getText()+"'";
                                     data.put("1", new Object[] {"SO Number","XID","Update Date","Create Date"});
                                     val=2;
                                     break;
                             case 3:
                                     q5="select t3.so_number,t1.transaction_date, t2.upd_date from emc_om_transaction t1, emc_om_entro_msg_Detl t2, emc_om_so t3 where t1.status='LACSENT' and t2.status='COMPLETE' and t2.queue_name='SAAS-TEST' and t2.so_number=t3.so_number and t3.so_id=t1.so_id and t2.so_number>='"+ulsntf.getText()+"' and t2.so_number<='"+ulentf.getText()+"'";
                                     data.put("1", new Object[] {"SO Number","Transaction Date","Update Date"});
                                     val=3;
                                     break;
                             case 4:
                                     q5="select t2.so_number,t1.transaction_date,t2.create_date from emc_om_transaction t1, emc_om_so t2 where t1.status='COMPLETE' and t2.so_id=t1.so_id and t2.so_number>='"+ulsntf.getText()+"' and t2.so_number<='"+ulentf.getText()+"'";
                                     data.put("1", new Object[] {"SO Number","Transaction Date","Create Date"});
                                     val=4;
                                     break;
                             case 5:                                                                                                                                                                            //uasntf                                   uaentf         
                                     q5="select t1.so_number,t1.xid,t1.upd_Date,t1.create_date from emc_om_entro_msg_detl t1 where t1.queue_name='SAAS-TEST' and t1.xml_msg like'%ENTACTIV%' and t1.so_number>='"+Integer.parseInt(uasntf.getText())+"' and t1.so_number<='"+Integer.parseInt(uaentf.getText())+"'";
                                     data.put("1", new Object[] {"SO Number","XID","Update Date","Create Date"});
                                     val=5;
                                     break;
                             case 6:
                                     q5="select t2.so_number,t1.transaction_date, t2.create_date from emc_om_transaction t1, emc_om_so t2 where t1.status='SAASHOLD' and t2.so_id=t1.so_id and t2.so_number>='"+alsntf.getText()+"' and t2.so_number<='"+alentf.getText()+"'";
                                     data.put("1", new Object[] {"SO Number","Transaction Date","Create Date"});
                                     val=6;
                                     break;
                             case 7:
                                     q5="select t1.so_number,t1.xid, t1.create_date, t2.transaction_date from emc_om_entro_msg_Detl t1, emc_om_transaction t2, emc_om_so t3 where t2.status='SAASHOLD' and t1.queue_name='SAAS-AMAZONITE' and t1.status='COMPLETE' and t1.so_number=t3.so_number and t3.so_id=t2.so_id and t1.so_number>='"+alsntf.getText()+"' and t1.so_number<='"+alentf.getText()+"'";
                                     data.put("1", new Object[] {"SO Number","XID","Create Date","Transaction Date"});
                                     val=7;
                                     break;
                             case 8:
                                     q5="select t1.so_number,t1.xid,t1.upd_Date,t1.create_date from emc_om_entro_msg_Detl t1 where t1.status='DISCARDED' and t1.so_number>='"+alsntf.getText()+"' and t1.so_number<='"+alentf.getText()+"' and t1.queue_name='SAAS-AMAZONITE'";
                                     data.put("1", new Object[] {"SO Number","XID","Update Date","Create Date"});
                                     val=8;
                                     break;
                             case 9:
                                     q5="select t1.so_number, t2.upd_Date, t1.create_Date from emc_om_entro_msg_detl t1, activation_Detail t2, entitlement t3 where t1.lac=t3.authorization_key and t3.entitlement_id=t2.entitlement_id and t1.so_number>='"+alsntf.getText()+"' and t1.so_number<='"+alentf.getText()+"' and t1.queue_name='SAAS-AMAZONITE' and t1.status='COMPLETE'";
                                     data.put("1", new Object[] {"SO Number","Update Date","Create Date"});
                                     val=9;
                                     break;
                             case 10:
                                     q5="select so_number,xid,upd_date,create_date from emc_om_entro_msg_Detl where status='COMPLETE' and so_number>='"+alsntf.getText()+"' and so_number<='"+alentf.getText()+"' and queue_name='SAAS-AMAZONITE'";
                                     data.put("1", new Object[] {"SO Number","XID","Update Date","Create Date"});
                                     val=10;
                                     break;
                             case 11:
                                     q5="select t2.so_number, t1.transaction_date, t2.upd_Date from emc_om_transaction t1, emc_om_entro_msg_Detl t2, emc_om_so t3 where t1.status='LACSENT' and t2.status='COMPLETE' and t2.so_number=t3.so_number and t3.so_id=t1.so_id and t2.so_number>='"+alsntf.getText()+"' and t2.so_number<='"+alentf.getText()+"' and t2.queue_name='SAAS-AMAZONITE'";
                                     data.put("1", new Object[] {"SO Number","Transaction Date","Update Date"});
                                     val=11;
                                     break;
                             case 12:
                                     q5="select t2.so_number,t1.transaction_date, t2.create_Date from emc_om_transaction t1, emc_om_so t2 where t1.status='COMPLETE' and t1.so_id=t2.so_id and t2.so_number>='"+alsntf.getText()+"' and t2.so_number<='"+alentf.getText()+"'";
                                     data.put("1", new Object[] {"SO Number","Transaction Date","Create Date"});
                                     val=12;
                                     break;
                        }
                   
                   
                        srs=ssmt.executeQuery(q5);
                        while(srs.next())
                        {
                            pbar1.setVisible(true);
                            
//                              Timestamp cd=Timestamp.valueOf(df.format(srs.getTimestamp("start_date_time")));
//                               Timestamp ud=Timestamp.valueOf(df.format(srs.getTimestamp("end_date_time")));                
                            switch(val)
                            {
                                case 0:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),df.format(srs.getTimestamp("transaction_date")),df.format(srs.getTimestamp("create_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+df.format(srs.getTimestamp("transaction_date"))+"\t"+df.format(srs.getTimestamp("create_date")));
                                    break;
                                case 1:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),srs.getInt("xid"),df.format(srs.getTimestamp("create_date")),df.format(srs.getTimestamp("transaction_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+srs.getInt("xid")+"\t"+df.format(srs.getTimestamp("create_date"))+"\t"+df.format(srs.getTimestamp("transaction_date")));
                                    break;
                                case 2:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),srs.getInt("xid"),df.format(srs.getTimestamp("upd_date")),df.format(srs.getTimestamp("create_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+srs.getInt("xid")+"\t"+df.format(srs.getTimestamp("upd_date"))+"\t"+df.format(srs.getTimestamp("create_date")));
                                    break;
                                case 3:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),df.format(srs.getTimestamp("transaction_date")),df.format(srs.getTimestamp("upd_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+df.format(srs.getTimestamp("transaction_date"))+"\t"+df.format(srs.getTimestamp("upd_date")));
                                    break;
                                case 4:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),df.format(srs.getTimestamp("transaction_date")),df.format(srs.getTimestamp("create_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+df.format(srs.getTimestamp("transaction_date"))+"\t"+df.format(srs.getTimestamp("create_date")));
                                    break;
                                case 5:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),srs.getInt("xid"),df.format(srs.getTimestamp("upd_date")),df.format(srs.getTimestamp("create_date"))});
                                    System.out.println(i+":\t"+srs.getString("so_number")+"\t"+srs.getString("xid")+"\t"+df.format(srs.getTimestamp("upd_date"))+"\t"+df.format(srs.getTimestamp("create_date")));
                                    break;
                                case 6:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),df.format(srs.getTimestamp("transaction_date")),df.format(srs.getTimestamp("create_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+df.format(srs.getTimestamp("transaction_date"))+"\t"+df.format(srs.getTimestamp("create_date")));
                                    break;
                                case 7:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),srs.getInt("xid"),df.format(srs.getTimestamp("transaction_date")),df.format(srs.getTimestamp("create_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+srs.getInt("xid")+"\t"+df.format(srs.getTimestamp("transaction_date"))+"\t"+df.format(srs.getTimestamp("create_date")));
                                    break;
                                case 8:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),srs.getInt("xid"),df.format(srs.getTimestamp("upd_date")),df.format(srs.getTimestamp("create_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+srs.getInt("xid")+"\t"+df.format(srs.getTimestamp("upd_date"))+"\t"+df.format(srs.getTimestamp("create_date")));
                                    break;
                                case 9:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),df.format(srs.getTimestamp("upd_date")),df.format(srs.getTimestamp("create_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+df.format(srs.getTimestamp("upd_date"))+"\t"+df.format(srs.getTimestamp("create_date")));
                                    break;
                                case 10:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),srs.getInt("xid"),df.format(srs.getTimestamp("upd_date")),df.format(srs.getTimestamp("create_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+srs.getInt("xid")+"\t"+df.format(srs.getTimestamp("upd_date"))+"\t"+df.format(srs.getTimestamp("create_date")));
                                    break;
                                case 11:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),df.format(srs.getTimestamp("transaction_date")),df.format(srs.getTimestamp("upd_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+df.format(srs.getTimestamp("transaction_date"))+"\t"+df.format(srs.getTimestamp("upd_date")));
                                    break;
                                case 12:
                                    data.put(""+j, new Object[] {srs.getInt("so_number"),df.format(srs.getTimestamp("transaction_date")),df.format(srs.getTimestamp("create_date"))});
                                    System.out.println(i+":\t"+srs.getInt("so_number")+"\t"+df.format(srs.getTimestamp("transaction_date"))+"\t"+df.format(srs.getTimestamp("create_date")));
                                    break;     
                            }
                            i++;
                            j++;
                            
                        }
                        Thread.sleep(100);
                        
                        
                        
                        Thread.sleep(100);
                        pbar1.setProgress((loop2+1)/(13+5.0));   
                        Thread.sleep(1000);
                        //linfo.setText(""+(loop2+1)/(13+5.0));
                        //setTextFill(repaint());
                        Set<String> keyset=data.keySet();
            
                        int rownum = 5;
                        for (String key : keyset)
                        { 
                            Row row=null;
                            switch(val)
                            {
                                case 0:
                                    row = sheet1.createRow(rownum++);
                                    break;
                                case 1:
                                    row = sheet2.createRow(rownum++);
                                    break;
                                case 2:
                                    row = sheet3.createRow(rownum++);
                                    break;
                                case 3:
                                    row = sheet4.createRow(rownum++);
                                    break;
                                case 4:
                                    row = sheet5.createRow(rownum++);
                                    break;
                                case 5:
                                    row = sheet6.createRow(rownum++);
                                    break;
                                case 6:
                                    row = sheet7.createRow(rownum++);
                                    break;
                                case 7:
                                    row = sheet8.createRow(rownum++);
                                    break;
                                case 8:
                                    row = sheet9.createRow(rownum++);
                                    break;    
                                case 9:
                                    row = sheet10.createRow(rownum++);
                                    break;
                                case 10:
                                    row = sheet11.createRow(rownum++);
                                    break;
                                case 11:
                                    row = sheet12.createRow(rownum++);
                                    break;
                                case 12:
                                    row = sheet13.createRow(rownum++);
                                    break;
                            }
                            //HSSFCell cell2,cell1;
                            //Row row3=sheet.getRow(11);
                            Object [] objArr = data.get(key);
                            int cellnum = 5;
                            for (Object obj : objArr)
                            {
                                    org.apache.poi.ss.usermodel.Cell cell = row.createCell(cellnum++);
                                    if(obj instanceof String)
                                        cell.setCellValue((String)obj);
                                    else
                                        if(obj instanceof Integer)
                                                cell.setCellValue((Integer)obj);
                                        else
                                        {
                                                if(obj instanceof Timestamp)
                                                    cell.setCellValue((Timestamp)obj);
//                                                else
//                                                    cell.setCellValue((Double)obj);
                                        }
                            
                            }
                        }
                        //pbar1.setProgress(0.8000);
                        Thread.sleep(100);
                        pbar1.setProgress((loop2+1)/(13+1.0));                   
                        Thread.sleep(100);
                        //linfo.setText(""+((loop2/(loop2+1.0))*100)+"% Completed");
                        //linfo.setAccessibleText(""+((loop2/(loop2+1.0))*100)+"% Completed");
                        //FileOutputStream out = new FileOutputStream(new File("C:/Pravin/SaaS_NPI_9Nov_Pass2.xls"));
                        FileOutputStream out = new FileOutputStream(new File(sfilname));                        
                        workbook.write(out);
                        out.close();
                        System.out.println(""+title[loop2]+" written successfully on disk.");
                        Thread.sleep(500);
                        //linfo.setText("Successfully written"+title[loop2]+"into the File");
                        //linfo.setAccessibleText("Successfully written"+title[loop2]+"into the File");
                   
                    }
                    Thread.sleep(300);
                    pbar1.setProgress(1.000);
                    srs.close();
                    ssmt.close();
                }
                catch(Exception e)
                {
                    System.out.println("Caught in Exception iside Extrate Data (SaaS) and the exception is :\n"+e);
                }
           
            }
            else
            {
                JOptionPane.showMessageDialog(null,"One of these fields is empty...\n1.\tStarting So Number (LAC Creation)\n2.\tEnding So Number (LAC Creation)\n3.\tStarting So Number(Activation)\n4.\tEnding So Number(Activation)\n5.\tStarting So Number (AA LAC Creation)\n6.\tEnding So Number (AA LAC Creation)","warning",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    class IThread implements Runnable
     {

       @Override
       public void run() 
       {
//          if(tfFil.getText().equals(""))
//          {
//             JOptionPane.showMessageDialog(null,"Please Choose the Location where the file has to be saved...","Warning",JOptionPane.WARNING_MESSAGE);       
//          }
//          else
//          {
            if((tfstid.getText().equals(""))||(tfetid.getText().equals("")))
            {
                JOptionPane.showMessageDialog(null,"Please enter the starting and ending transaction ID...","Warning",JOptionPane.WARNING_MESSAGE);           
            }
            else
            {
                //String itfilname=itfilname.replaceAll("\\\\", "/");
                if(!(""+itfilname.charAt(itfilname.length()-4)).equals("."))
                {
                    itfilname=itfilname.concat(".csv");
                }
                else
                {
                    //itfilname=itfilname.concat(".csv");
                }
                String filtype[]=new String[18];
                double j=5.46;
                for(int i=0;i<18;i++)
                {
                    filtype[i]=new String();                    
                }
                filtype[0]="%XML_Infinity_Customer_Small%";
                filtype[1]="%XML_Infinity_Customer_Medium%";
                filtype[2]="%XML_Infinity_Customer_Large%";
                filtype[3]="%XML_Infinity_Employee_Small%";
                filtype[4]="%XML_Infinity_Employee_Medium%";
                filtype[5]="%XML_Infinity_Employee_Large%";
                filtype[6]="%XML_Infinity_Partner_Small%";
                filtype[7]="%XML_Infinity_Partner_Medium%";
                filtype[8]="%XML_Infinity_Partner_Large%";
                filtype[9]="%_NoNInfinity_Customer_Small_%";
                filtype[10]="%_NoNInfinity_Customer_Medium_%";
                filtype[11]="%_NoNInfinity_Customer_Large_%";
                filtype[12]="%_NoNInfinity_Employee_Small_%";
                filtype[13]="%_NoNInfinity_Employee_Medium_%";
                filtype[14]="%_NoNInfinity_Employee_Large_%";
                filtype[15]="%_NoNInfinity_Partner_Small_%";
                filtype[16]="%_NoNInfinity_Partner_Medium_%";
                filtype[17]="%_NoNInfinity_Partner_Large_%";
                
                try
                {
                     ipbar.setProgress(j/42);
                     Thread.sleep(2000);
                     String qry1="select aft.transaction_id, aft.status, aft.update_date, aft.create_date, fst.file_name from emc_actv_file_transaction aft,EMC_FILE_STORE fst where aft.file_id=fst.file_id and aft.transaction_id >= '"+tfstid.getText()+"' and aft.transaction_id <= '"+tfetid.getText()+"' and fst.file_name like '";
                     String qry2="' and aft.parent_transaction_id is null";
                     int l=0;
                     int k=0;
                     long ud=0;
                     long cd=0;
                     long dif=0;
                     long min=0;
                     long avg=0;
                     long max=0;
                     Date idat2=new Date();
                     Date idat3=new Date();
                     Date idat4=new Date();
                     int avs=0;
                     int mis=0;
                     int mas=0;
                     Date idat=new Date();
                     idat.setYear(0);
                     idat.setMonth(0);
                     idat.setHours(0);
                     idat.setMinutes(0);
                     idat.setTime(dif);
                     Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                     DateFormat idf = new SimpleDateFormat("hh:mm:ss.SSS");
                     idf.setTimeZone(TimeZone.getTimeZone("UTC"));
                     Class.forName("oracle.jdbc.driver.OracleDriver");
                     con = DriverManager.getConnection(dburl,dbuname,dbpword);
                     Statement ismt=con.createStatement();
                     ResultSet irs=null;
                     FileWriter fileWriter = new FileWriter(itfilname);
                     fileWriter.append("Transaction ID,Status,Update Date,Create Date,File Name,Difference,Average,Minimum,Maximum,Average(in Sec),Minimum(in Sec),Maximum(in Sec)\n");
                             
                     for(;k<18;k++)
                     {
                         
//                         if(k<9)
//                             l=k;
//                         else
//                             l=k+k-(k-1);
                         irs=ismt.executeQuery(qry1+filtype[k]+qry2);
                         
//                          if(k==2||k==8||k==11||k==17)
//                             fileWriter.append(" "+","+" "+","+" "+","+" "+","+" "+"\n");
//                         if(k==3||k==5||k==6||k==9||k==12||k==14||k==15)
//                         {
//                             tfsuccess.setText(""+filename);
//                             fileWriter.append(" "+","+" "+","+" "+","+" "+","+" "+"\n");
                             //fileWriter.append(" "+","+" "+","+" "+","+" "+","+" "+"\n");
                             //data.put(""+k, new Object[] {"","","",""});
                             //data.put(""+k, new Object[] {"","","",""});
//                         }
                         int iter=0;
                         avg=0;
                         min=0;
                         max=0;
                         while(irs.next())
                         {
                             
                             j++;
                             ud=irs.getTimestamp("update_date").getTime()*1000-19800000;
                             cd=irs.getTimestamp("create_date").getTime()*1000-19800000;
                             dif=ud-cd;
                             avg+=dif/1000;
                             if(iter==0)
                             {
                                 min=dif/1000;
                                 max=dif/1000;
                             }
                             else
                             {
                                 if(min>(dif/1000))
                                     min=dif/1000;
                                 if(max<(dif/1000))
                                     max=dif/1000;
                             }
                             idat.setTime(dif/1000);
                         
                             //data.put(""+k, new Object[] {irs.getInt("transaction_id"),irs.getString("status"),df.format(irs.getTimestamp("update_date")),df.format(irs.getTimestamp("create_date")),irs.getString("file_name")});
                             fileWriter.append(irs.getInt("transaction_id")+","+irs.getString("status")+","+df.format(irs.getTimestamp("update_date"))+","+df.format(irs.getTimestamp("create_date"))+","+irs.getString("file_name")+","+"00:"+(idf.format(dif/1000)).substring(3,12)+"\n"); //dif/1000    idf.format(idat)
                             //System.out.println(irs.getInt("transaction_id")+"\t"+irs.getString("status")+"\t"+df.format(irs.getTimestamp("update_date"))+"\t"+df.format(irs.getTimestamp("create_date"))+"\t"+irs.getString("file_name")+"\t"+(dif/1000)+"\t"+ud/1000+"\t00:"+(idf.format(dif/1000)).substring(3,12)+"\t"+irs.getTimestamp("update_date").getTime()*1000+"\t"+irs.getTimestamp("create_date").getTime()*1000);
                             iter++;
                         }
                         avg=avg/(iter);
                         idat2.setTime(avg);
                         idat3.setTime(min);
                         idat4.setTime(max);
                         avs=(Integer.parseInt(idf.format(avg).substring(3,5))*60)+Integer.parseInt(idf.format(avg).substring(6,8));
                         mis=(Integer.parseInt(idf.format(min).substring(3,5))*60)+Integer.parseInt(idf.format(min).substring(6,8));
                         mas=(Integer.parseInt(idf.format(max).substring(3,5))*60)+Integer.parseInt(idf.format(max).substring(6,8));
                         if(Integer.parseInt(idf.format(avg).substring(9,10))>=5)
                         {
                             avs+=1;
                         }
                         if(Integer.parseInt(idf.format(min).substring(9,10))>=5)
                         {
                             mis+=1;
                         }
                         if(Integer.parseInt(idf.format(max).substring(9,10))>=5)
                         {
                             mas+=1;
                         }
                         //fileWriter.append(" "+","+" "+","+" "+","+" "+","+" "+","+" "+","+idf.format(avg)+","+idf.format(min)+","+idf.format(max)+"\n");
                         fileWriter.append(" "+","+" "+","+" "+","+" "+","+" "+","+" "+",00:"+idf.format(avg).substring(3,12)+",00:"+idf.format(min).substring(3,12)+",00:"+idf.format(max).substring(3,12)+","+avs+" secs,"+mis+" secs,"+mas+" secs\n");
//                         System.out.println("avg :\t"+idat2.getMinutes()+"\t min :\t"+idat3.getMinutes()+"\t max :\t"+idat4.getMinutes());
//                         System.out.println("Sec avg :\t"+idat2.getMinutes()*60+"\t sec min :\t"+idat3.getMinutes()*60+"\t sec max :\t"+idat4.getMinutes()*60);
//                         System.out.println("Sec2 avg :\t"+idat2.getMinutes()*60+idat2.getSeconds()+"\t sec2 min :\t"+idat3.getMinutes()*60+idat3.getSeconds()+"\t sec2 max :\t"+idat4.getMinutes()*60+idat4.getSeconds());
                         Thread.sleep(500);
                         ipbar.setProgress(j/(44+6));
                         
                     }
                     fileWriter.close();
                     Thread.sleep(1000);
                     ipbar.setProgress(1);
                     System.out.println("Writen successfully into"+itfilname);
                     //Thread.sleep(500);
                     irs.close();
                     ismt.close();
                     con.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    //System.out.println("Caught in exception while writing data into file or fetching data from DB \n And the exception is \n"+e);
                    //JOptionPane.showMessageDialog(null,"Caught in exception while writing data into file or fetching data from DB \n And the exception is \n"+e,"Warning",JOptionPane.WARNING_MESSAGE);            
                }
            }
       }
    }    

    @FXML
    private void BIEData(ActionEvent event) 
    {
         try
        {
            FileChooser fc=new FileChooser();
            //JFileChooser fc = new JFileChooser();
            //fc.setFileSelectionMode(FileChooser.DIRECTORIES_ONLY);
            //FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
            File selectedfile= fc.showSaveDialog(null);
            //int result=fc.showSaveDialog(fc);
//            if(result == JFileChooser.CANCEL_OPTION) 
//                return;
            //File selectedfile= fc.getSelectedFile();
            //filname=filname.replaceAll("\\\\","/");
            //tfFil.setText(selectedfile.getPath());
            itfilname=selectedfile.getPath();
            itfilname=itfilname.replaceAll("\\\\","/");
            if(!itfilname.equals(""))
            {
                 //if(tffnam.getText().substring((tffnam.getText().length()-(tffnam.getText().length()-4)),(tffnam.getText().length()+1)).equals(".xls"))
                 Thread ith=new Thread(new IThread());
                 if((""+itfilname.charAt(itfilname.length()-4)).equals("."))
                 {   
                     
                     if(itfilname.substring(((itfilname.length())-4),(itfilname.length())).equalsIgnoreCase(".csv"))
                     {
                         //Thread ith=new Thread(new IThread());
                         ith.start();
                     }
                     else
                     {
                         JOptionPane.showMessageDialog(null,"You can Save the contents only in an .CSV file\nPlease choose a .CSV file and then try again\n","Warning",JOptionPane.WARNING_MESSAGE);
                     } 
                 }
                 else
                 {
                     itfilname+=".csv";
                     ith.start();
                 }
            }
            else
            {
                 JOptionPane.showMessageDialog(null,"You haven't selected the file where the data has to be written\nPlease Enter the File Path...\n","Warning",JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(Exception e)
        {
            System.out.println("Caught in Exception while using File Chooser and the Exception is\n"+e);
        }
    }

    @FXML
    private void BIFind(ActionEvent event) 
    {
        if(!(tfstid.getText().equals(""))&&!(tfetid.getText().equals("")))
        {
             try
             {
                 String srp=rinf.getToggleGroup().getSelectedToggle().toString().substring(rinf.getToggleGroup().getSelectedToggle().toString().indexOf('\'', 0)+1, rinf.getToggleGroup().getSelectedToggle().toString().lastIndexOf('\''));
                 String sru=rcus.getToggleGroup().getSelectedToggle().toString().substring(rcus.getToggleGroup().getSelectedToggle().toString().indexOf('\'', 0)+1, rcus.getToggleGroup().getSelectedToggle().toString().lastIndexOf('\''));
                 String srfs=rsml.getToggleGroup().getSelectedToggle().toString().substring(rsml.getToggleGroup().getSelectedToggle().toString().indexOf('\'', 0)+1, rsml.getToggleGroup().getSelectedToggle().toString().lastIndexOf('\''));
//               tfsuccess.setText(srp);
//               tffailed.setText(srfs);
             
                 String qry1="select count(*) from emc_actv_file_transaction aft,EMC_FILE_STORE fst where aft.file_id=fst.file_id and aft.transaction_id >= '"+tfstid.getText()+"' and aft.transaction_id <= '"+tfetid.getText()+"' and fst.file_name like '%XML_"+srp+"_"+sru+"_"+srfs+"_%'and aft.parent_transaction_id is null and aft.status='SUCCESS'";
                 String qry2="select count(*) from emc_actv_file_transaction aft,EMC_FILE_STORE fst where aft.file_id=fst.file_id and aft.transaction_id >= '"+tfstid.getText()+"' and aft.transaction_id <= '"+tfetid.getText()+"' and fst.file_name like '%XML_"+srp+"_"+sru+"_"+srfs+"_%'and aft.parent_transaction_id is null and aft.status='PARTIAL_SUCCESS'";
                 String qry3="select count(*) from emc_actv_file_transaction aft,EMC_FILE_STORE fst where aft.file_id=fst.file_id and aft.transaction_id >= '"+tfstid.getText()+"' and aft.transaction_id <= '"+tfetid.getText()+"' and fst.file_name like '%XML_"+srp+"_"+sru+"_"+srfs+"_%'and aft.parent_transaction_id is null and aft.status='FAILED'";
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 con = DriverManager.getConnection(dburl,dbuname,dbpword);
                 Statement ifsmt1=con.createStatement();
                 Statement ifsmt2=con.createStatement();
                 Statement ifsmt3=con.createStatement();
                 ResultSet ifrs1=ifsmt1.executeQuery(qry1);
                 ResultSet ifrs2=ifsmt2.executeQuery(qry2);
                 ResultSet ifrs3=ifsmt3.executeQuery(qry3);
                 while(ifrs1.next())
                 {
                     tfsuccess.setText(""+ifrs1.getInt("count(*)"));
                 }
                 while(ifrs2.next())
                 {
                     tfipsuccess.setText(""+ifrs2.getInt("count(*)"));
                 }
                 while(ifrs3.next())
                 {
                     tffailed.setText(""+ifrs3.getInt("count(*)"));
                 }
                 
                 ifrs3.close();
                 ifrs2.close();
                 ifrs1.close();
                 ifsmt3.close();
                 ifsmt2.close();
                 ifsmt1.close();
                 con.close();
             }
             catch(Exception e)
             {
                 System.out.println("Caught in exception while selecting Radio Buttion \nAnd the Exception is\n"+e);
                 JOptionPane.showMessageDialog(null,"You Haven't selected one of the 3 Radio Buttons\n1.  Type  (inf (or) non inf)\n2.  User  (Cus, Emp, Part)\n3.  File Size  (Small, Medium, Large)","warning",JOptionPane.WARNING_MESSAGE);
             }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"One (or) more than one of the field is left blank\n1.  Starting Transaction ID\n2.  Ending Transaction ID","Warning",JOptionPane.WARNING_MESSAGE);       
        }
    }

    @FXML
    private void BOAll(ActionEvent event) 
    {
        if(!(tfstid.getText().equals(""))&&!(tfetid.getText().equals("")))
        {
             String qry1="select count(*) from emc_actv_file_transaction aft,EMC_FILE_STORE fst where aft.file_id=fst.file_id and aft.transaction_id >= '"+tfstid.getText()+"' and aft.transaction_id <= '"+tfetid.getText()+"' and aft.parent_transaction_id is null and aft.status='SUCCESS'";
             String qry2="select count(*) from emc_actv_file_transaction aft,EMC_FILE_STORE fst where aft.file_id=fst.file_id and aft.transaction_id >= '"+tfstid.getText()+"' and aft.transaction_id <= '"+tfetid.getText()+"' and aft.parent_transaction_id is null and aft.status='PARTIAL_SUCCESS'";
             String qry3="select count(*) from emc_actv_file_transaction aft,EMC_FILE_STORE fst where aft.file_id=fst.file_id and aft.transaction_id >= '"+tfstid.getText()+"' and aft.transaction_id <= '"+tfetid.getText()+"' and aft.parent_transaction_id is null and aft.status='FAILED'";
             try
             {
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 con = DriverManager.getConnection(dburl,dbuname,dbpword);
                 Statement iosmt1=con.createStatement();
                 Statement iosmt2=con.createStatement();
                 Statement iosmt3=con.createStatement();
                 ResultSet iors1=iosmt1.executeQuery(qry1);
                 ResultSet iors2=iosmt2.executeQuery(qry2);
                 ResultSet iors3=iosmt3.executeQuery(qry3);
                 while(iors1.next())
                 {
                     tfsuccess.setText(""+iors1.getInt("count(*)"));
                 }
                 while(iors2.next())
                 {
                     tfipsuccess.setText(""+iors2.getInt("count(*)"));
                 }
                 while(iors3.next())
                 {
                     tffailed.setText(""+iors3.getInt("count(*)"));
                 }
                 iors3.close();
                 iors2.close();
                 iors1.close();
                 iosmt3.close();
                 iosmt2.close();
                 iosmt1.close();
                 con.close();
             }
             catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null,"Caught in exception while trying to fetch over all count\nAnd the exception is"+e,"Warning",JOptionPane.WARNING_MESSAGE);       
                 System.out.println("Caught in exception while trying to fetch over all count\nAnd the exception is"+e);
             }
        
        }
        else
        {
            JOptionPane.showMessageDialog(null,"One (or) more than one of the field is left blank\n1.  Starting Transaction ID\n2.  Ending Transaction ID","Warning",JOptionPane.WARNING_MESSAGE);       
        }
    }

    @FXML
    private void extractData(ActionEvent event) 
    {
        try
           {
                 FileChooser fc=new FileChooser();
                 File selectedfile= fc.showSaveDialog(null);
                 //filname=filname.replaceAll("\\\\","\\\\\\\\");
                 sfilname=selectedfile.getPath();
                 sfilname=sfilname.replaceAll("\\\\","/");
                 if(!sfilname.equals(""))
                 {
                     Thread th1=new Thread(new TThread1());
                     //if(tffnam.getText().substring((tffnam.getText().length()-(tffnam.getText().length()-4)),(tffnam.getText().length()+1)).equals(".xls"))
                     if((""+sfilname.charAt(sfilname.length()-4)).equals("."))
                     {     
                         if(sfilname.substring(((sfilname.length())-4),(sfilname.length())).equalsIgnoreCase(".xls"))
                         {
                             th1.start();
                         }
                         else
                         {
                             JOptionPane.showMessageDialog(null,"You can Save the contents only in an .XLS file\nPlease choose a XLS file and then try again\n","Warning",JOptionPane.WARNING_MESSAGE);
                         }
                     }
                     else
                     {
                         sfilname+=".xls";
                         th1.start();
                     }
                 }
                 else
                 {
                     JOptionPane.showMessageDialog(null,"You haven't selected the file where the data has to be written\nPlease Enter the File Path...\n","Warning",JOptionPane.WARNING_MESSAGE);
                 }
           }
           catch(Exception e)
           {
                 System.out.println("Caught in Exception while using File Chooser and the Exception is\n"+e);
           }        
    }

    @FXML
    private void BUBCount(ActionEvent event) 
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(dburl,dbuname,dbpword);
            Statement ubcsmt=con.createStatement();
            ResultSet ubcrs=null;
            String cq="";
            String cq1="";
            if(ulsntf.getText().equals("")||(ulentf.getText().equals("")))
            {
                JOptionPane.showMessageDialog(null,"One of the below field is Missing\n1.  Starting So Number\n2.  Ending So Number\n","Error",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                cq="select count(*) from emc_om_so where so_number>='"+ulsntf.getText().toString()+"' and so_number <='"+ulentf.getText()+"' and status='COMPLETE'";
                ubcrs=ubcsmt.executeQuery(cq);
                while(ubcrs.next())
                {
                    tfubc.setText(""+ubcrs.getInt("count(*)"));
                }
                cq1="select count(*) from emc_om_so where so_number>='"+ulsntf.getText().toString()+"' and so_number <='"+ulentf.getText()+"' and status='VALIDATED'";
                ubcrs=ubcsmt.executeQuery(cq1);
                while(ubcrs.next())
                {
                    tfubv.setText(""+ubcrs.getInt("count(*)"));
                }
            }
            ubcrs.close();
            ubcsmt.close();
            con.close();
        }
        catch(Exception exe)
        {
            JOptionPane.showMessageDialog(null,"Caugth in Exceptin in User Based Count Button\n and the exception is\n"+exe,"Warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    private void BAACount(ActionEvent event) 
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(dburl,dbuname,dbpword);
            Statement aacsmt=con.createStatement();
            ResultSet aacrs=null;
            String cq="";
            String cq1="";
            if((alsntf.getText().equals("")||(alentf.getText().equals(""))))
            {
                JOptionPane.showMessageDialog(null,"One of the below field is Missing\n1.  Starting So Number\n2.  Ending So Number\n","Error",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                cq="select count(*) from emc_om_so where so_number>='"+alsntf.getText()+"' and so_number <='"+alentf.getText()+"' and status='COMPLETE'";
                aacrs=aacsmt.executeQuery(cq);
                while(aacrs.next())
                {
                    System.out.println(""+aacrs.getInt("count(*)"));
                    tfaac.setText(""+aacrs.getInt("count(*)"));
                }
                cq1="select count(*) from emc_om_so where so_number>='"+alsntf.getText()+"' and so_number <='"+alentf.getText()+"' and status='VALIDATED'";
                aacrs=aacsmt.executeQuery(cq1);
                while(aacrs.next())
                {
                    System.out.println(""+aacrs.getInt("count(*)"));
                    tfaav.setText(""+aacrs.getInt("count(*)"));
                }
            }
            aacrs.close();
            aacsmt.close();
            con.close();
        }
        catch(Exception exe)
        {
             JOptionPane.showMessageDialog(null,"Caugth in Exceptin in Auto Active Count Button\n and the exception is\n"+exe,"Warning",JOptionPane.WARNING_MESSAGE);       
        }
    }

    @FXML
    private void saasTabSelected(Event event) 
    {
        
    }

    @FXML
    private void startExtracting(ActionEvent event) 
    {
        pbar.setProgress(0.0100);
       String q0="select distinct(status) from emc_ui_usage_notification where usage_notification_id>'"+ssnotf.getText()+"' and usage_notification_id<'"+esnotf.getText()+"'";
       String q1;
       String q2;
       String q3="select distinct(transaction) from emc_ui_event_transaction where transactionid>='"+settf.getText()+"' and transactionid<='"+eettf.getText()+"' order by 1 desc";
       String q4;
       String s[]=new String[10];
       int st=0;
       int sv=0;
       int loop=0;
       
       HSSFWorkbook workbook = new HSSFWorkbook();
       
       HSSFSheet clpsheet=workbook.createSheet("CLP Table");
       
       
       HSSFSheet sheet=null;
       HSSFSheet sheet1=null;
       HSSFSheet sheet2=null;
       HSSFSheet sheet3=null;
       HSSFSheet sheet4=null;
       HSSFSheet sheet5=null;
       HSSFSheet sheet6=null;
       HSSFSheet sheet7=null;
       HSSFSheet sheet8=null;
       HSSFSheet sheet9=null;
       
       
       HSSFSheet sheet10=null;
       HSSFSheet sheet11=null;
       HSSFSheet sheet12=null;
       HSSFSheet sheet13=null;
       HSSFSheet sheet14=null;
       HSSFSheet sheet15=null;
       HSSFSheet sheet16=null;
       HSSFSheet sheet17=null;
       HSSFSheet sheet18=null;
       HSSFSheet sheet19=null;
       HSSFSheet sheet20=null;
       HSSFSheet sheet21=null;
       HSSFSheet sheet22=null;
       HSSFSheet sheet23=null;
       
       if(!((scidtf.getText().equals(""))||(scidtf.getText().equals(" ")))&&(!(ecidtf.getText().equals(""))||ecidtf.getText().equals(" ")))
       {
           q2="select usage_id,instance_id,ve_instance_swid,processing_timestamp,esrs_received_timestamp,usage_file_timestamp from emc_ui_clp_usage where usage_id>='"+scidtf.getText()+"' and usage_id<='"+ecidtf.getText()+"'";
           try
           {
               Class.forName("oracle.jdbc.driver.OracleDriver");
               con = DriverManager.getConnection(dburl,dbuname,dbpword);
               Statement smt2=con.createStatement();
               ResultSet rs2=smt2.executeQuery(q2);
               
               Map<String,Object[]> data = new TreeMap<String,Object[]>();
               data.put("1", new Object[] {"usage_id","instance_id","ve_instance_swid","processing_timestamp","esrs_received_timestamp","usage_file_timestamp"});
               
               Timestamp ccd,cud;
               
               while(rs2.next())
               {
                    pbar.setVisible(true);
                    ccd=Timestamp.valueOf(df.format(rs2.getTimestamp("processing_timestamp")));
                    cud=Timestamp.valueOf(df.format(rs2.getTimestamp("esrs_received_timestamp")));                
                    
                    data.put(""+j, new Object[] {rs2.getInt("usage_id"),rs2.getInt("instance_id"),rs2.getString("ve_instance_swid"),df.format(rs2.getTimestamp("processing_timestamp")),df.format(rs2.getTimestamp("esrs_received_timestamp")),df.format(rs2.getTimestamp("usage_file_timestamp"))});
                    System.out.println(i+":\t"+rs2.getInt("usage_id")+"\t"+rs2.getInt("instance_id")+"\t"+rs2.getString("ve_instance_swid")+"\t"+df.format(rs2.getTimestamp("processing_timestamp"))+"\t"+df.format(rs2.getTimestamp("esrs_received_timestamp"))+"\t"+df.format(rs2.getTimestamp("usage_file_timestamp")));
                    
                    i++;
                    j++;     
                }
                Set<String> keyset=data.keySet();
                int rownum = 5;
                for (String key : keyset)
                {
                    Row row = clpsheet.createRow(rownum++);    
                    Object [] objArr = data.get(key);
                    int cellnum = 5;
                    for (Object obj : objArr)
                    {
                        org.apache.poi.ss.usermodel.Cell cell = row.createCell(cellnum++);
                        if(obj instanceof String)
                               cell.setCellValue((String)obj);
                        else
                               if(obj instanceof Integer)
                                     cell.setCellValue((Integer)obj);
                               else
                                     if(obj instanceof Timestamp)
                                          cell.setCellValue((Timestamp)obj);
                    }   
                }
                pbar.setProgress(0.8000);
                FileOutputStream out = new FileOutputStream(new File("C:/Pravin/Usage_Throughput.xls"));
                workbook.write(out);
                out.close();
                System.out.println("CLP.xls written successfully on disk.");
                rs2.close();
                smt2.close();
                con.close();
           }
           catch(Exception e)
           {
                System.out.println("Caught in Exceptin inside ... CLP and the exception is ...\n"+e);
           }    
       }
       else
       {
           JOptionPane.showMessageDialog(null,"You haven't Entered...\n1.\t Starting CLP ID\n\t\t(or)\n2.\tEnding CLP ID","warning",JOptionPane.WARNING_MESSAGE);
       }
       
       if(!(settf.getText().equals("")||settf.getText().equals(" "))&&(!(eettf.getText().equals("")||eettf.getText().equals(" "))))
       {
           int loop1=0;
           i=1;
           j=4;
           try
           {
               Class.forName("oracle.jdbc.driver.OracleDriver");
               con = DriverManager.getConnection(dburl,dbuname,dbpword);
               Statement smt3=con.createStatement();
               ResultSet rs3=smt3.executeQuery(q3);
               
               Statement smt4=con.createStatement();
               ResultSet rs4=null;
               
               String dtrans[]=new String[14];
               int dtc=0;
               int detv=0;
               while(rs3.next())
               {
                   if(dtc<14)
                   {
                       dtrans[dtc]=new String();
                       dtrans[dtc]=rs3.getString("transaction");
                       dtc++;
                   }
//                   else
//                   {
//                       dtc++;
//                       if(dtc==15)
//                       {
//                           JOptionPane.showMessageDialog(null,"Total Distinct Transaction crossed 14\n");
//                       }
//                   }
               }
               for(;loop1<dtc;loop1++)
               { 
                   q4="select transactionid,transuid,failureinfo,transaction,start_date_time,end_date_time,status from emc_ui_event_transaction where transactionid>='"+settf.getText()+"' and transactionid<='"+eettf.getText()+"' and transaction='"+dtrans[loop1]+"'order by 1 desc";
                   rs4=smt4.executeQuery(q4);
                   System.out.println(" "+loop1+":\t"+dtrans[loop1]);
                   
                   Map<String,Object[]> data = new TreeMap<String,Object[]>();
                   
                   Timestamp cd,ud;          

                   //Create a blank sheet
                   switch(loop1)
                   {
                       
                       case 0:
                           sheet10 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=10;
                           break;
                       case 1:
                           sheet11 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=11;
                           break;
                       case 2:
                           sheet12 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=12;
                           break;
                       case 3:
                           sheet13 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=13;
                           break;    
                       case 4:
                           sheet14 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=14;
                           break;
                       case 5:
                           sheet15 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=15;
                           break;
                       case 6:
                           sheet16 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=16;
                           break;
                       case 7:
                           sheet17 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=17;
                           break;
                       case 8:
                           sheet18 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=18;
                           break;
                       case 9:
                           sheet19 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=19;
                           break;
                       case 10:
                           sheet20 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=20;
                           break;
                       case 11:
                           sheet21 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=21;
                           break;
                       case 12:
                           sheet22 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=22;
                           break;
                       case 13:
                           sheet23 = workbook.createSheet("event_"+dtrans[loop1]);
                           detv=23;
                           break;
                       
                }
                
                data.put("1", new Object[] {"Transaction ID","Transuid","Failure INFO","Transaction","Start_Date_Time","End_Date_Time","Status"});
         
                pbar.setProgress(0.0500);
                //pbar.progressProperty();
                
                while(rs4.next())
                {
                    pbar.setVisible(true);
                    cd=Timestamp.valueOf(df.format(rs4.getTimestamp("start_date_time")));
                    ud=Timestamp.valueOf(df.format(rs4.getTimestamp("end_date_time")));                
                    data.put(""+j, new Object[] {rs4.getInt("transactionid"),rs4.getString("transuid"),rs4.getString("failureinfo"),rs4.getString("transaction"),df.format(rs4.getTimestamp("start_date_time")),df.format(rs4.getTimestamp("end_date_time")),rs4.getString("status")});
                    System.out.println(i+":\t"+rs4.getInt("transactionid")+"\t"+rs4.getString("transuid")+"\t"+rs4.getString("failureinfo")+"\t"+rs4.getString("transaction")+"\t"+df.format(rs4.getTimestamp("start_date_time"))+"\t"+df.format(rs4.getTimestamp("end_date_time"))+"\t"+rs4.getString("status"));
                    i++;
                    j++;
                    pbar.setProgress(0.12+(i*0.0001));
                }
                Set<String> keyset=data.keySet();
            
                int rownum = 5;
                for (String key : keyset)
                {
                    Row row=null;
                    switch(detv)
                    {
                        case 10:
                            row = sheet10.createRow(rownum++);
                            break;
                        case 11:
                            row = sheet11.createRow(rownum++);
                            break;
                        case 12:
                            row = sheet12.createRow(rownum++);
                            break;
                        case 13:
                            row = sheet13.createRow(rownum++);
                            break;
                        case 14:
                            row = sheet14.createRow(rownum++);
                            break;
                        case 15:
                            row = sheet15.createRow(rownum++);
                            break;
                        case 16:
                            row = sheet16.createRow(rownum++);
                            break;
                        case 17:
                            row = sheet17.createRow(rownum++);
                            break;
                        case 18:
                            row = sheet18.createRow(rownum++);
                            break;    
                        case 19:
                            row = sheet19.createRow(rownum++);
                            break;
                        case 20:
                            row = sheet20.createRow(rownum++);
                            break;
                        case 21:
                            row = sheet21.createRow(rownum++);
                            break;
                        case 22:
                            row = sheet22.createRow(rownum++);
                            break;
                        case 23:
                            row = sheet23.createRow(rownum++);
                            break;
                    }
                    //HSSFCell cell2,cell1;
                    //Row row3=sheet.getRow(11);
                    Object [] objArr = data.get(key);
                    int cellnum = 5;
                    for (Object obj : objArr)
                    {
                        org.apache.poi.ss.usermodel.Cell cell = row.createCell(cellnum++);
                        if(obj instanceof String)
                               cell.setCellValue((String)obj);
                        else
                               if(obj instanceof Integer)
                                     cell.setCellValue((Integer)obj);
                               else
                                     if(obj instanceof Timestamp)
                                          cell.setCellValue((Timestamp)obj);
                    }   
                }
                pbar.setProgress(0.8000);
                FileOutputStream out = new FileOutputStream(new File("C:/Pravin/Usage_Throughput.xls"));
                workbook.write(out);
                out.close();
                System.out.println(""+dtrans[loop1]+".xls written successfully on disk.");       
               }
               rs3.close();
               rs4.close();
               smt3.close();
               smt4.close();
               con.close();
           }
           catch(Exception e)
           {
               System.out.println("Caught in Exception in Event Transaction ... and the exception is ...\n"+e);
           }
       }
       else
       {
           JOptionPane.showMessageDialog(null,"You haven't Entered...\n1.\t Starting Event Transaction ID\n\t\t(or)\n2.\tEnding Event Transaction ID","warning",JOptionPane.WARNING_MESSAGE);
       }
       
      if(!(ssnotf.getText().equals("")||ssnotf.getText().equals(" ")))
      {
       pbar.setProgress(0.5000);
       
       i=1;
       j=4;
       
       try
       {
           
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(dburl,dbuname,dbpword);
            
            Statement smt0=con.createStatement();
            ResultSet rs0=smt0.executeQuery(q0);
            
            Statement smt=con.createStatement();
            ResultSet rs=null;
           
            while(rs0.next())
            {
                s[st]=new String();
                s[st]=rs0.getString("status");
                
                st++;
            }
           
            
            for(;loop<st;loop++)
            {
                q1="select un.usage_notification_id,un.entitlement_swid,fs.file_id,fs.file_name,fs.create_date,fs.update_date,un.status from  emc_ui_usage_notification un,emc_file_store fs where un.usage_notification_id>'"+ssnotf.getText()+"' and un.usage_notification_id<'"+esnotf.getText()+"' and un.status='"+s[loop]+"' and un.file_id=fs.file_id order by 1 desc";
                rs=smt.executeQuery(q1);
                System.out.println(" "+loop+":\t"+s[loop]);
                
                Map<String,Object[]> data = new TreeMap<String,Object[]>();
                
                Timestamp cd,ud;          
                 
                
                
                //Create a blank sheet
                switch(loop)
                {
                    case 0:
                        sheet = workbook.createSheet(s[loop]);
                        sv=0;
                        break;
                    case 1:
                        sheet1 = workbook.createSheet(s[loop]);
                        sv=1;
                        break;
                    case 2:
                        sheet2 = workbook.createSheet(s[loop]);
                        sv=2;
                        break;
                    case 3:
                        sheet3 = workbook.createSheet(s[loop]);
                        sv=3;
                        break;    
                    case 4:
                        sheet4 = workbook.createSheet(s[loop]);
                        sv=4;
                        break;
                    case 5:
                        sheet5 = workbook.createSheet(s[loop]);
                        sv=5;
                        break;
                    case 6:
                        sheet6 = workbook.createSheet(s[loop]);
                        sv=6;
                        break;
                    case 7:
                        sheet7 = workbook.createSheet(s[loop]);
                        sv=7;
                        break;
                    case 8:
                        sheet8 = workbook.createSheet(s[loop]);
                        sv=8;
                        break;
                    case 9:
                        sheet9 = workbook.createSheet(s[loop]);
                        sv=9;
                        break;    
                }
                    
                
                
                //This data needs to be written (Object[])
                
                data.put("1", new Object[] {"Usage_Notification_ID","Entitlement_SWID","File_ID","File_Name","Create_Date","Update_Date","Status"});
          
//                Map<String,Object[]> data2 = new TreeMap<String,Object[]>();
//                data2.put("1", new Object[] {"Usage_Notification_ID","Entitlement_SWID","File_ID","File_Name","Create_Date","Update_Date","Status"});
                pbar.setProgress(0.0500);
                //pbar.progressProperty();
                
                while(rs.next())
                {
                    pbar.setVisible(true);
                    cd=Timestamp.valueOf(df.format(rs.getTimestamp("create_date")));
                    ud=Timestamp.valueOf(df.format(rs.getTimestamp("update_date")));                
                    data.put(""+j, new Object[] {rs.getInt("usage_notification_id"),rs.getString("entitlement_swid"),rs.getInt("file_id"),rs.getString("file_name"),df.format(rs.getTimestamp("update_date")),df.format(rs.getTimestamp("create_date")),rs.getString("status")});
                    System.out.println(i+":\t"+rs.getInt("usage_notification_id")+"\t"+rs.getString("entitlement_swid")+"\t"+rs.getInt("file_id")+"\t"+rs.getString("file_name")+"\t"+df.format(rs.getTimestamp("update_date"))+"\t"+df.format(rs.getTimestamp("create_date"))+"\t"+rs.getString("status"));
                    i++;
                    j++;                        
                    pbar.setProgress(0.12+(i*0.0001));
                }
                Set<String> keyset=data.keySet();
            
                int rownum = 5;
                for (String key : keyset)
                {
                    Row row=null;
                    switch(sv)
                    {
                        case 0:
                            row = sheet.createRow(rownum++);
                            break;
                        case 1:
                            row = sheet1.createRow(rownum++);
                            break;
                        case 2:
                            row = sheet2.createRow(rownum++);
                            break;
                        case 3:
                            row = sheet3.createRow(rownum++);
                            break;
                        case 4:
                            row = sheet4.createRow(rownum++);
                            break;
                        case 5:
                            row = sheet5.createRow(rownum++);
                            break;
                        case 6:
                            row = sheet6.createRow(rownum++);
                            break;
                        case 7:
                            row = sheet7.createRow(rownum++);
                            break;
                        case 8:
                            row = sheet8.createRow(rownum++);
                            break;    
                        case 9:
                            row = sheet9.createRow(rownum++);
                            break;
                    }
                    //HSSFCell cell2,cell1;
                    //Row row3=sheet.getRow(11);
                    Object [] objArr = data.get(key);
                    int cellnum = 5;
                    for (Object obj : objArr)
                    {
                        org.apache.poi.ss.usermodel.Cell cell = row.createCell(cellnum++);
                        if(obj instanceof String)
                               cell.setCellValue((String)obj);
                        else
                               if(obj instanceof Integer)
                                     cell.setCellValue((Integer)obj);
                               else
                                     if(obj instanceof Timestamp)
                                          cell.setCellValue((Timestamp)obj);
                    }   
                }
                pbar.setProgress(0.8000);
                FileOutputStream out = new FileOutputStream(new File("C:/Pravin/Usage_Throughput.xls"));
                workbook.write(out);
                out.close();
                System.out.println(""+s[loop]+".xls written successfully on disk.");
                
            }
            
            
            rs.close();
            rs0.close();
            smt0.close();
            smt.close();
            con.close();
            pbar.setProgress(1.000);
        }
        catch(Exception e)
        {
             System.out.println("Caught in Exception and the exception is:-\n"+e);
        }
      }
      else
      {
            JOptionPane.showMessageDialog(null,"You haven't Entered...\n1.\t Starting So Number\n\t\t(or)\n2.\tEnding So Number","warning",JOptionPane.WARNING_MESSAGE);
            pbar.setProgress(0.125);
      }
    }

    @FXML
    private void usageThroghputTabSelected(Event event) 
    {
        
    }
    
}
