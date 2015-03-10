/*
 * 
 * 
 */
package newjob;

import java.awt.Color;
import java.io.*;
import java.security.Security;
import java.util.Properties;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.swing.JOptionPane;

/**
 *
 * @author pingying
 */
public class chectmail {

    private static String messageContentMimeType = "text/html; charset=gb2312";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    public static boolean loginsuc = false;
    public static String loginname = "";
    public static String loginpassword = "";
    public static String keyword1;
    public static String keyword2;

    public static Properties getProperties() {
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.imap.port", "993");
        props.setProperty("mail.imap.socketFactory.port", "993");

        props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty("mail.pop3.port", "995");
        props.setProperty("mail.pop3.socketFactory.port", "995");

        props.put("mail.smtp.auth", "true");
        return props;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        fetchMail();
        while (true) {
        }
    }

    /**
     *
     *
     * 156.
     *
     * @param text 157.
     * @return 158.
     * @throws UnsupportedEncodingException 159.
     */
    protected static String decodeText(String text)
            throws UnsupportedEncodingException {
        if (text == null) {
            return null;
        }
        if (text.startsWith("=?GB") || text.startsWith("=?gb")) {
            text = MimeUtility.decodeText(text);
        } else {
            text = new String(text.getBytes("ISO8859_1"));
        }
        return text;
    }
    ///for internship start

    public static void parseMailContent(Object content) {
        try {
            if (content instanceof MimeMultipart) {
                Multipart mPart = (MimeMultipart) content;
                //  for (int i = 0; i < mPart.getCount(); i++) {   
                extractPart((MimeBodyPart) mPart.getBodyPart(0));
                // }   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 191. * 192. * 193.
     *
     * @param part 194.
     */
    public static void extractPart(MimeBodyPart part) {
        String[] inMailContent = null;
        // String mykkkk="Alstom";
        String[] ConforeachM = null;
        try {
            String disposition = part.getDisposition();

            if (disposition != null
                    && (disposition.equalsIgnoreCase(Part.ATTACHMENT) || disposition.equalsIgnoreCase(Part.INLINE))) {// Attach file
                String fileName = decodeText(part.getFileName());
                //  saveAttachFile(part);//save attachfil
            } else {// content
                if (part.getContent() instanceof String) {//pure text
                    String mycontent1 = part.getContent().toString();
                    inMailContent = mycontent1.split("<a href=");

                    for (int intakk = 1; intakk <= 5; intakk++) {//check the company name in each lin
                        // System.out.println(intakk);
                        String inhh = inMailContent[intakk].replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
                        for (int j = 150; j <= (inhh.length() - keyword1.length()); j++) {//judge kk
                            if (keyword1.regionMatches(false, 0, inhh, j, keyword1.length())) {
                                //here we deal with the hh
                                ConforeachM = inhh.replaceAll("\'", "&&").split("&&");
                                String inmyaddress = ConforeachM[1];//get the address
                                ConforeachM = inhh.split(">");
                                String innnmydescrpt = ConforeachM[1];//get the descript
                                ConforeachM = innnmydescrpt.split("                    ");
                                String innmydescrpt = ConforeachM[0];//get the descript
                                ConforeachM = innmydescrpt.split("\n");
                                String inmydescrpt = ConforeachM[0];
                                System.out.println("address:     " + inmyaddress);
                                System.out.println("description:            " + inmydescrpt);
                                Newjob.textArea.add(new LinkLabel(inmydescrpt, inmyaddress, 1), 1);
                                mymainframe.jScrollPane3.getViewport().add(Newjob.textArea);
                                mymainframe.jTabbedPane1.setTitleAt(0, keyword1 + "NEW!");
                                mymainframe.jTabbedPane1.setBackgroundAt(0, Color.ORANGE);
                                mymainframe.jTabbedPane1.setForegroundAt(0, Color.RED);
                                new RightCornerPopMessage(inmydescrpt, inmyaddress, keyword1);
                                break;

                            }     //end  if       
                        }
                        if (keyword2 != null) {
                            for (int j = 150; j <= (inhh.length() - keyword2.length()); j++) {//judge kk
                                if (keyword2.regionMatches(false, 0, inhh, j, keyword2.length())) {
                                    //here we deal with the hh
                                    ConforeachM = inhh.replaceAll("\'", "&&").split("&&");
                                    String inmyaddress = ConforeachM[1];//get the address
                                    ConforeachM = inhh.split(">");
                                    String innnmydescrpt = ConforeachM[1];//get the descript
                                    ConforeachM = innnmydescrpt.split("                    ");
                                    String innmydescrpt = ConforeachM[0];//get the descript
                                    ConforeachM = innmydescrpt.split("\n");
                                    String inmydescrpt = ConforeachM[0];
                                    System.out.println("address:     " + inmyaddress);
                                    System.out.println("description:            " + inmydescrpt);
                                    Newjob.textArea2.add(new LinkLabel(inmydescrpt, inmyaddress, 2), 1);
                                    mymainframe.jScrollPane1.getViewport().add(Newjob.textArea2);
                                    mymainframe.jTabbedPane1.setTitleAt(1, keyword2 + "NEW!");
                                    mymainframe.jTabbedPane1.setBackgroundAt(1, Color.ORANGE);
                                    mymainframe.jTabbedPane1.setForegroundAt(1, Color.RED);
                                    new RightCornerPopMessage(inmydescrpt, inmyaddress, keyword2);
                                    break;

                                }     //end  if       
                            }
                        }

                    }//end for


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //end for intership 
    public static void Seelog() throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session session = Session.getDefaultInstance(getProperties(), null);

        //  URLName urln = new URLName("imap", "imap.gmail.com", 995, null,"marissa013new@gmail.com","yijiayi=2");
        URLName urln = new URLName("imap", "imap.gmail.com", 995, null, loginname, loginpassword);
        Store store = null;
        Folder inbox = null;

        try {
            //Store used to receive,Store class类实现特定邮件协议上的读、写、监视、查找等操作。
            store = session.getStore(urln);
            try {
                store.connect();
                mymainframe.jRadioButton2.setSelected(true);
                loginsuc = true;
                Newjob.messageID = 0;;// new login,set messageId=0
            } catch (Exception e) {//System.out.println("wrong name or password");
                javax.swing.JOptionPane.showMessageDialog(null, "Wrong name or password!", "Information", JOptionPane.INFORMATION_MESSAGE);
                loginsuc = false;
            }


        } finally {
            try {
                inbox.close(false);
            } catch (Exception e) {
            }
            try {
                store.close();
            } catch (Exception e) {
            }
        }



        //write the addressh() and password()
        StringBuilder nmApw = new StringBuilder();
        nmApw.append(loginname + " ");
        nmApw.append(loginpassword + " /n");
        byte b[] = nmApw.toString().getBytes();
        try {
            FileOutputStream out = new FileOutputStream("log.txt", false);//clear and write
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }

    /**
     * . *
     * 263. * 264.
     *
     * @throws Exception 265.
     */
    public static void fetchMail() throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session session = Session.getDefaultInstance(getProperties(), null);

        URLName urln = new URLName("imap", "imap.gmail.com", 995, null, loginname, loginpassword);
        //URLName urln = new URLName("imap", "imap.gmail.com", 995, null,loginname,loginpassword);
        Store store = null;
        Folder inbox = null;

        try {

            store = session.getStore(urln);
            store.connect();
            while (store.isConnected() == false) {// login error set button3
                javax.swing.JOptionPane.showMessageDialog(null, "Wrong name or password!", "Information", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("wrong name or password");
                //fetchMail();
            }



            inbox = store.getFolder("INBOX");//收件箱   
            inbox.open(Folder.READ_ONLY);
            FetchProfile profile = new FetchProfile();
            profile.add(FetchProfile.Item.ENVELOPE);
            Message[] messages = inbox.getMessages();
            inbox.fetch(messages, profile);
            System.out.println("收件箱的邮件数：" + messages.length);
            System.out.println("未读邮件数：" + inbox.getUnreadMessageCount());
            System.out.println("新邮件数：" + inbox.getNewMessageCount());
            String[] MailContent = null;
            String[] ConforeachM = null;


            for (int i = Newjob.messageID; i < messages.length; i++) {
                // sender

                String from = decodeText(messages[i].getFrom()[0].toString());
                InternetAddress ia = new InternetAddress(from);

                if (from.equals("Monster <jagent@route.monster.com>")) {
                    String mycontent1 = messages[i].getContent().toString();
                    MailContent = mycontent1.split("<a href=");
                    String newcontent2 = MailContent[2].replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");

                    for (int takk = 1; takk <= MailContent.length - 6; takk++) {//check the company name in each lin
                        String hh = MailContent[takk].replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
                        for (int j = 150; j <= (hh.length() - keyword1.length()); j++) {//judge kk
                            if (keyword1.regionMatches(false, 0, hh, j, keyword1.length())) {
                                //here we deal with the hh
                                ConforeachM = hh.replaceAll("\"", "&&").split("&&");
                                String myaddress = ConforeachM[1];//get the address
                                ConforeachM = hh.split(">");
                                String mydescrpt = ConforeachM[1];//get the descript
                                Newjob.textArea.add(new LinkLabel(mydescrpt, myaddress, 1), 1);
                                mymainframe.jScrollPane3.getViewport().add(Newjob.textArea);
                                mymainframe.jTabbedPane1.setTitleAt(0, keyword1 + "NEW!");
                                mymainframe.jTabbedPane1.setBackgroundAt(0, Color.ORANGE);
                                mymainframe.jTabbedPane1.setForegroundAt(0, Color.RED);
                                new RightCornerPopMessage(mydescrpt, myaddress, keyword1);
                            }     //end  if       
                        } //end for
                        //judage the tt
                        if (keyword2 != null) {
                            for (int j = 150; j <= (hh.length() - keyword2.length()); j++) {//judge tt
                                if (keyword2.regionMatches(false, 0, hh, j, keyword2.length())) {
                                    //here we deal with the hh
                                    ConforeachM = hh.replaceAll("\"", "&&").split("&&");
                                    String myaddress = ConforeachM[1];//get the address
                                    ConforeachM = hh.split(">");
                                    String mydescrpt = ConforeachM[1];//get the descript
                                    Newjob.textArea2.add(new LinkLabel(mydescrpt, myaddress, 2), 1);
                                    mymainframe.jScrollPane1.getViewport().add(Newjob.textArea2);
                                    mymainframe.jTabbedPane1.setTitleAt(1, keyword2 + "NEW!");
                                    mymainframe.jTabbedPane1.setBackgroundAt(1, Color.ORANGE);
                                    mymainframe.jTabbedPane1.setForegroundAt(1, Color.RED);
                                    new RightCornerPopMessage(mydescrpt, myaddress, keyword2);
                                }     //end  if       
                            } //end for

                        }//end strat2 if

                    } //end for


                    // mail size
                    System.out.println("SIZE:" + messages[i].getSize());
                    //mail sending time
                    System.out.println("DATE:" + messages[i].getSentDate());
                } else if (messages[i].getSubject().substring(0, 24).equals("Your Internship Matches:")) {

                    parseMailContent(messages[i].getContent());

                } else if ("20+ new".equals(messages[i].getSubject().substring(0, 7))) {
                    String inmycontent1 = messages[i].getContent().toString();
                    String[] mysplit1 = null;
                    String[] mysplit2 = null;
                    String[] mysplit3 = null;
                    String indeedlink = null;
                    String indeedposition = null;
                    String indeedlastitem = null;
                    String indeedcompany = null;
                    mysplit1 = inmycontent1.split("<!--BEGIN-->");
                    String mycontent2 = mysplit1[1];
                    mysplit1 = mycontent2.split("<font color=\"#008800\">");
                    for (int innum = 0; innum <= mysplit1.length - 2; innum++) {
                        String mycontent3 = mysplit1[innum];
                        mysplit2 = mycontent3.split("<a href=\"");
                        String mycontent4 = mysplit2[1];
                        mysplit2 = mycontent4.split("\">");
                        indeedlink = mysplit2[0];//address
                        if (mysplit2.length != 1) {
                            String myindeed1 = mysplit2[1];
                            mysplit3 = myindeed1.split("<");
                            indeedposition = mysplit3[0];// position name
                            indeedlastitem = mysplit1[innum + 1];
                            mysplit2 = indeedlastitem.split("<");

                            if (mysplit2.length != 1) {
                                indeedcompany = mysplit2[0];


                                for (int j = 0; j <= (indeedcompany.length() - keyword1.length()); j++) {//judge kk
                                    if (keyword1.regionMatches(false, 0, indeedcompany, j, keyword1.length())) {


                                        Newjob.textArea.add(new LinkLabel(indeedposition, indeedlink, 1), 1);
                                        mymainframe.jScrollPane3.getViewport().add(Newjob.textArea);
                                        mymainframe.jTabbedPane1.setTitleAt(0, keyword1 + "NEW!");
                                        mymainframe.jTabbedPane1.setBackgroundAt(0, Color.ORANGE);
                                        mymainframe.jTabbedPane1.setForegroundAt(0, Color.RED);
                                        new RightCornerPopMessage(indeedposition, indeedlink, keyword1);
                                    }     //end  if       
                                } //end for
                                if (keyword2 != null) {
                                    for (int j = 0; j <= (indeedcompany.length() - keyword2.length()); j++) {//judge tt
                                        if (keyword2.regionMatches(false, 0, indeedcompany, j, keyword2.length())) {
                                            Newjob.textArea2.add(new LinkLabel(indeedposition, indeedlink, 2), 1);
                                            mymainframe.jScrollPane1.getViewport().add(Newjob.textArea2);
                                            mymainframe.jTabbedPane1.setTitleAt(1, keyword2 + "NEW!");
                                            mymainframe.jTabbedPane1.setBackgroundAt(1, Color.ORANGE);
                                            mymainframe.jTabbedPane1.setForegroundAt(1, Color.RED);
                                            new RightCornerPopMessage(indeedposition, indeedlink, keyword2);
                                        }     //end  if
                                    } //end for

                                }//end strat2 if
                            }//end if
                        }//end if



                    }//end for



                }//end indeed 

            }  //for end

            Newjob.messageID = messages.length;// later this should be write into file
            //write the Search key word
            StringBuilder twoKeywords = new StringBuilder();
            twoKeywords.append(keyword1 + "&");
            twoKeywords.append(keyword2 + "&");
            twoKeywords.append(Newjob.messageID + "\n");
            byte wk[] = twoKeywords.toString().getBytes();
            try {
                FileOutputStream out = new FileOutputStream("key.txt", false);//clear and write
                out.write(wk);
                out.flush();
                out.close();
            } catch (Exception e) {
            }

            //finish writing 
        } finally {
            try {
                inbox.close(false);
            } catch (Exception e) {
            }
            try {
                store.close();
            } catch (Exception e) {
            }
        }
    }
}
