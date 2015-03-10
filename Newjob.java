/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newjob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.Box;
import javax.swing.ImageIcon;

/**
 *
 * @author pingying
 */
public class Newjob {

    public static int messageID = 0;//later should be read from file    
    public static int mytableID = 0;//later should be read from file
    public static long prevPref = System.currentTimeMillis();
    public static long curtime;
    public static Box textArea = Box.createVerticalBox();
    public static Box textArea2 = Box.createVerticalBox();
    public static boolean start1 = false;
    public static boolean start2 = false;
    public static String[] filecach = null;
    public static mymainframe Mycanel = new mymainframe();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        ///I should write read file here
        //read old file first

        //log.txt
        Mycanel.jRadioButton1.setSelected(true);
        BufferedReader logfile = null;
        String mynp = null;
        logfile = new BufferedReader(new FileReader(new File("log.txt")));
        mynp = logfile.readLine();
        if (mynp != null) {
            filecach = mynp.split(" ");
            chectmail.loginname = filecach[0];
            chectmail.loginpassword = filecach[1];
            Mycanel.jRadioButton2.setSelected(true);
            //login set
            Mycanel.jButton3.setText("change");
            Mycanel.jLabel2.setVisible(false);
            Mycanel.jPasswordField1.setVisible(false);

        }
        //key.txt
        BufferedReader keyfile = null;
        String nandp = null;

        keyfile = new BufferedReader(new FileReader(new File("key.txt")));
        nandp = keyfile.readLine();

        if (nandp != null) {
            mymainframe.jRadioButton4.setSelected(true);
            filecach = nandp.split("&");
            //  keyword1=filecach[0];
            // keyword1=filecach[1];
            messageID = Integer.parseInt(filecach[2]);

            textArea.add(new LinkLabel("tracing.......", "rr", 1), 0);
            textArea2.add(new LinkLabel("tracing.......", "rr", 2), 0);
            mymainframe.jScrollPane3.getViewport().add(Newjob.textArea);
            if (filecach[1] != null) {
                mymainframe.jScrollPane1.getViewport().add(Newjob.textArea2);
            }
        }
        //end read old file
        while (true) {
            if (!start1) {
                Mycanel.setVisible(true);


                if (nandp != null) {
                    mymainframe.jRadioButton4.setSelected(true);
                    Mycanel.jTabbedPane1.setTitleAt(0, filecach[0]);// change the icon  of this tag
                    String myiconpath1 = "src/" + filecach[0] + ".gif";
                    ImageIcon ic1 = new ImageIcon(myiconpath1);
                    Mycanel.jTabbedPane1.setIconAt(0, ic1);
                    Mycanel.jTabbedPane1.setTitleAt(1, filecach[1]);// change the icon of this tag
                    String myiconpath2 = "src/" + filecach[1] + ".gif";
                    ImageIcon ic2 = new ImageIcon(myiconpath2);
                    Mycanel.jTabbedPane1.setIconAt(1, ic2);
                    chectmail.keyword1 = filecach[0];
                    chectmail.keyword2 = filecach[1];

                    chectmail.fetchMail();
                    mydo();
                }


                try {
                    Thread thread = Thread.currentThread();
                    thread.sleep(10000);//暂停1.5秒后程序继续执行
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else {
                mymainframe.jRadioButton4.isSelected();
                break;
                // Mycanel.setVisible(true);
            }
        }
//     
        chectmail.fetchMail();
//       
        mydo();
        while (true) {
            Mycanel.setVisible(true);
        }
        //// if(true)//if(!Mycanel.kk.TraComNam.equals(null))//will read from file later
        // {


        //}


    }

    public static void mydo() throws Exception {

        while (start1) {



            curtime = System.currentTimeMillis();

            if (curtime - prevPref > 90000) {
                chectmail.fetchMail();

                Mycanel.setVisible(true);
                // Mycanel.ypcount=i;
                //fetchmail here
                prevPref = curtime;
                //  Mycanel.jTable2.setValueAt(Mycanel.ypcount, 2,0);
            }

        }

    }
}
