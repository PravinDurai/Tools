/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author duraip
 */
public class Reading_Metric_Logs_FXMLController implements Initializable {

    @FXML
    private Button mlbextract;
    @FXML
    private TextArea mlta;
    @FXML
    private TextField mltflfp;
    @FXML
    private TextField mltfcfp;
    @FXML
    private Button mlblfp;
    @FXML
    private Button mlbcfp;
    @FXML
    String lfilname="";
    @FXML
    String cfilname="";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void MetricLogExtract(ActionEvent event) 
    {
        String line="";
        String metricid="";
        String sdat="";
        String edat="";
        String pfmat="";
        String stpfmat="";
        Date ssdat=null;
        Date sedat=null;
        long diff=0;
        int i=0;
        float min=0;
        float max=0;
        float avg=0.0f;
        int sd=0;
        int ed=0;
        try
        {
            FileReader lfr=new FileReader(lfilname);
            BufferedReader lbr=new BufferedReader(lfr);
            
            FileWriter cfw=new FileWriter(cfilname);
            cfw.append("\nSN#,Metric ID,Start Date,End Date,Difference (in Secs)");
            while((line=lbr.readLine())!=null)
            {   
                sd=0;
                ed=0;
 
                //line=lbr.readLine();
                //mlta.appendText("\n"+line);
                if(line.contains("\"metricId\":\""))
                {
                    //metricid="";
                    //sdat="";
                    //edat="";
                    //mlta.appendText("\n"+i+1+".\t"+line.indexOf("\"metricId\":\""));
                    //mlta.appendText("\n"+(i+1)+"\tMetric ID :\t"+line.substring(line.indexOf("\"metricId\":\"")+12,line.indexOf("\",", line.indexOf("\"metricId\":\""))));
                    metricid=line.substring(line.indexOf("\"metricId\":\"")+12,line.indexOf("\",", line.indexOf("\"metricId\":\"")));
                    //cfw.append("\n"+(i+1)+","+line.substring(line.indexOf("\"metricId\":\"")+12,line.indexOf("\",", line.indexOf("\"metricId\":\""))));
                    //mlta.appendText("\t"+"Start Date Time:\t"+line.substring(line.indexOf("\"startDateTime\":\"")+17,line.indexOf("\",", line.indexOf("\"startDateTime\":\""))));
                    //"startDateTime":"
                    sdat=line.substring(line.indexOf("\"startDateTime\":\"")+17,line.indexOf("\",", line.indexOf("\"startDateTime\":\"")));
                    //ssdat=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(sdat);
                    //cfw.append(","+line.substring(line.indexOf("\"startDateTime\":\"")+17,line.indexOf("\",", line.indexOf("\"startDateTime\":\""))));
                    //mlta.appendText("\t"+"End Date Time :\t"+line.substring(line.indexOf("\"endDateTime\":\"")+15,line.indexOf("\"}}", line.indexOf("\"endDateTime\":\""))));
                    edat=line.substring(line.indexOf("\"endDateTime\":\"")+15,line.indexOf("\"}}", line.indexOf("\"endDateTime\":\"")));
                    if(sdat.contains("."))
                    {
                        stpfmat=sdat.substring(0,sdat.indexOf('.'))+"-"+sdat.substring(sdat.indexOf('.')+1, sdat.indexOf('.')+3)+"-"+sdat.substring(sdat.indexOf('.')+4, sdat.indexOf('.')+6)+" "+sdat.substring(sdat.indexOf('.')+7, sdat.indexOf('.')+9)+":"+sdat.substring(sdat.indexOf('.')+10, sdat.indexOf('.')+12)+":"+sdat.substring(sdat.indexOf('.')+13, sdat.indexOf('.')+15);
                        ssdat=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(stpfmat);
                        sd=1;
                    }
                    else
                    {
                        stpfmat+=sdat;
                        ssdat=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(sdat);
                    }
                    if(edat.contains("."))
                    {
                        pfmat=edat.substring(0,edat.indexOf('.'))+"-"+edat.substring(edat.indexOf('.')+1, edat.indexOf('.')+3)+"-"+edat.substring(edat.indexOf('.')+4, edat.indexOf('.')+6)+" "+edat.substring(edat.indexOf('.')+7, edat.indexOf('.')+9)+":"+edat.substring(edat.indexOf('.')+10, edat.indexOf('.')+12)+":"+edat.substring(edat.indexOf('.')+13, edat.indexOf('.')+15);
                        sedat=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(pfmat);
                        ed=1;
                    }
                    else
                    {
                        sedat=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(edat);
                    }
                    
                    diff=(sedat.getTime()-ssdat.getTime())/1000;
                    
                    if(min>diff)
                    {
                        min=diff;
                    }
                    if(max<diff)
                    {
                        max=diff;
                    }
                    avg+=diff;
                    
                    mlta.appendText("\n"+(i+1)+"\tMetric ID :\t"+metricid+"\tStart Date :\t"+sdat+"\tEnd Date :\t"+pfmat+"\tDifference :\t"+diff);
                    //cfw.append("\n"+(i+1)+","+metricid+","+sdat+","+pfmat+","+diff);
                    cfw.append("\n"+(i+1)+","+metricid+","+stpfmat+","+pfmat+","+diff);
                    //cfw.append("\n"+(i+1)+","+metricid+","+ssdat+","+sedat+","+diff);
                    i++;
                    stpfmat="";
                }
            }
            avg/=(i+0.0f);
            cfw.append("\n,,,,,Minimum ,"+min+",Average ,"+avg+",Maximum ,"+max);
            mlta.appendText("\nMinimum :\t"+min+"\t Average :\t"+avg+"\t Maximum :\t"+max);
            lbr.close();
            lfr.close();
            cfw.close();
            JOptionPane.showMessageDialog(null,"Data Successfully Extracted...!\n","Task Completed",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception exc)
        {
            System.out.println("Caught in Exception inside Extract Data\n And the Exception is :\t"+exc);
        }
        
        
    }

    @FXML
    private void logFilePath(ActionEvent event) 
    {
              FileChooser fc=new FileChooser();
              File selectedfile= fc.showOpenDialog(null);
              lfilname=selectedfile.getPath();
              lfilname=lfilname.replaceAll("\\\\","/");
              if(!lfilname.equals(""))
              {  
                  
                  if((""+lfilname.charAt(lfilname.length()-4)).equals("."))
                  {     
                     if(lfilname.substring(((lfilname.length())-4),(lfilname.length())).equalsIgnoreCase(".txt"))
                     {
                         mlta.appendText("\n Inside File Chooser In correct Format :\t"+lfilname);
                         mltflfp.setText(""+lfilname);
                     }
                     else
                     {
                         JOptionPane.showMessageDialog(null,"You can Save the contents only in an .txt file\nPlease choose a TXT file and then try again\n","Warning",JOptionPane.WARNING_MESSAGE);
                     }
                  }
                  else
                  {
                     lfilname+=".txt";
                     mlta.appendText("\n Inside File Chooser Incorrect Format after adding the extension :\t"+lfilname);
                     mltflfp.setText(""+lfilname);
                  }
                  
              } 
              else
              {
                  JOptionPane.showMessageDialog(null,"You haven't selected the file where the data has to be written\nPlease Enter the File Path...\n","Warning",JOptionPane.WARNING_MESSAGE);
              }
    }

    @FXML
    private void convertedFilePath(ActionEvent event) 
    {
              FileChooser fc=new FileChooser();
              File selectedfile= fc.showSaveDialog(null);
              cfilname=selectedfile.getPath();
              cfilname=cfilname.replaceAll("\\\\","/");
              if(!cfilname.equals(""))
              {  
                  
                  if((""+cfilname.charAt(cfilname.length()-4)).equals("."))
                  {     
                     if(cfilname.substring(((cfilname.length())-4),(cfilname.length())).equalsIgnoreCase(".csv"))
                     {
                         mlta.appendText("\n Inside File Chooser In correct Format :\t"+cfilname);
                         mltfcfp.setText(""+cfilname);
                     }
                     else
                     {
                         JOptionPane.showMessageDialog(null,"You can Save the contents only in an .CSV file\nPlease choose a CSV file and then try again\n","Warning",JOptionPane.WARNING_MESSAGE);
                     }
                  }
                  else
                  {
                     cfilname+=".csv";
                     mlta.appendText("\n Inside File Chooser Incorrect Format after adding the extension :\t"+cfilname);
                     mltfcfp.setText(""+cfilname);
                  }
              } 
              else
              {
                  JOptionPane.showMessageDialog(null,"You haven't selected the file where the data has to be written\nPlease Enter the File Path...\n","Warning",JOptionPane.WARNING_MESSAGE);
              }        
    }
    
}
