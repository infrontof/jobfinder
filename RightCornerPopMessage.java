/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newjob;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 *
 * by pingying;
 */
public class RightCornerPopMessage extends JWindow implements Runnable, MouseListener {

    private static final long serialVersionUID = -3564453685861233338L;
    private Integer screenWidth;  //
    private Integer screenHeight; //
    private Integer windowWidth = 200; //
    private Integer windowHeight = 100; //
    private Integer bottmToolKitHeight; //
    private Integer stayTime = 5000; //
    private Integer x; //
    private Integer y; //
    private String title = "New job in";
    private String message;// = "icon and link";
    private String myurl;
    private String icn;
    private JPanel mainPanel; //
    private JLabel titleLabel; //
    private JPanel titlePanel; //
    private JLabel messageLabel; //
    private JPanel messagePanel; //

    public RightCornerPopMessage(String a1, String a2, String iconname) {
        message = a1;
        myurl = a2;
        icn = iconname;
        this.init();
//this.run();
        Thread thread = new Thread(this);
        thread.start();
//while(true){
//}
    }

    private void init() {
        bottmToolKitHeight = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration()).bottom;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = dimension.width;
        screenHeight = dimension.height;

        x = screenWidth - windowWidth;
        y = screenHeight;
        this.setLocation(x, y - bottmToolKitHeight - windowHeight);
        mainPanel = new JPanel(new BorderLayout());

        titleLabel = new JLabel(title);
        String myiconpath1 = "src/" + icn + ".gif";
        ImageIcon ic1 = new ImageIcon(myiconpath1);
        titleLabel.setIcon(ic1);
        titleLabel.setForeground(Color.WHITE);
        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.add(titleLabel);

        messageLabel = new JLabel(message);
        messagePanel = new JPanel();
        messagePanel.add(messageLabel);
        messagePanel.setBackground(Color.WHITE);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(messagePanel, BorderLayout.CENTER);

        this.setSize(windowWidth, windowHeight);
        this.setAlwaysOnTop(false);
        this.getContentPane().add(mainPanel);
        this.addMouseListener(this);
        Toolkit.getDefaultToolkit().beep(); // alert sound
        this.setVisible(true);
    }

    @Override
    public void run() {
        Integer delay = 10;
        Integer step = 1;
        Integer end = windowHeight + bottmToolKitHeight;
        while (true) {
            try {
                step++;
                y = y - 1;
                this.setLocation(x, y);
                if (step > end) {
                    Thread.sleep(stayTime);
                    break;
                }
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        step = 1;
        while (true) {
            try {
                step++;
                y = y + 1;
                this.setLocation(x, y);
                if (step > end) {
                    this.dispose();
                    break;
                }
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//System.exit(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            Desktop.getDesktop().browse(
                    new java.net.URI(myurl));
        } catch (Exception ex) {
        }
        StringBuilder myrecord = new StringBuilder();
        myrecord.append(message + " ");
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
        this.dispose();
//System.exit(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
//public static void main(String[] args) {
//new RightCornerPopMessage();
//}
}
