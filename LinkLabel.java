/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newjob;

/**
 *
 * @author pingying
 */
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class LinkLabel extends JLabel {

    private String text, url;
    private int tabID;
    private boolean isSupported;
    private boolean mycolorL;
    PopupMenu popupMenu1 = new PopupMenu();
    MenuItem menuItem1 = new MenuItem();

    public LinkLabel(String text, String url, int tabID) {
        this.text = text;
        this.url = url;
        this.tabID = tabID;
        mycolorL = true;
        try {
            this.isSupported = Desktop.isDesktopSupported()
                    && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
        } catch (Exception e) {
            this.isSupported = false;
        }
        setText(false, mycolorL);
        menuItem1.setLabel("delete");
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setText(true, mycolorL);
                if (isSupported) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            public void mouseExited(MouseEvent e) {
                setText(false, mycolorL);
            }

            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                    mycolorL = false;
                    setText(true, mycolorL);
                    try {
                        Desktop.getDesktop().browse(
                                new java.net.URI(LinkLabel.this.url));
                    } catch (Exception ex) {
                    }
                    StringBuilder myrecord = new StringBuilder();
                    myrecord.append(LinkLabel.this.text + " ");



                    String mytime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

                    System.out.println(mytime);
                    myrecord.append(mytime + " \n");
                    byte b[] = myrecord.toString().getBytes();
                    try {
                        FileOutputStream out = new FileOutputStream("testrecord.txt", true);//clear and write
                        out.write(b);
                        out.flush();
                        out.close();
                    } catch (Exception evt) {
                    }
                }
            }

            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
        });
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuItem1_actionPerformed(e);
            }
        });
        popupMenu1.add(menuItem1);
        add(popupMenu1);
    }

    private void setText(boolean b, boolean c) {
        if (!c) {
            if (b) {
                setText("<html><font color=blue><u>" + text);
            } else {
                setText("<html><font color=black><u>" + text);
            }

        } else if (b) {
            setText("<html><font color=green><u>" + text);
        } else {
            setText("<html><font color=red><u>" + text);
        }
    }

    void this_mousePressed(MouseEvent e) {
        int mods = e.getModifiers();

        if ((mods & InputEvent.BUTTON3_MASK) != 0) {

            popupMenu1.show(this, e.getX(), e.getY());
        }
    }

    void menuItem1_actionPerformed(ActionEvent e) {
        this.setVisible(false);
        mymainframe.undotext = this.text;
        mymainframe.undourl = this.url;
        mymainframe.jButton2.setEnabled(true);
        mymainframe.undotabID = this.tabID;

    }
}
