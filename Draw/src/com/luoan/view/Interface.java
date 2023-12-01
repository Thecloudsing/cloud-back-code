package com.luoan.view;

import com.luoan.JButton_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Interface extends JFrame implements Runnable {
    private Thread thread;
    private JLabel contest;

    private JLabel drawStatus;
    private final Random random = new Random();
    public final static List<String> data = new ArrayList<>();
    private final StringBuilder status = new StringBuilder();

    public Interface() throws IOException {
        this.setTitle("成语揭秘");
        this.setBounds(400,250,800,600);
        this.setLayout(null);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.init();
        this.assembly();
    }
//    private void init() throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\name.properties"));
//        Properties properties = new Properties();
//        properties.load(br);
//        for (int i = 1; i < properties.size(); i++) {
//            data.add(properties.getProperty(String.valueOf(i)));
//        }
//        br.close();
//    }


//    public static void main(String[] args) throws IOException {
//        Interface anInterface = new Interface();
//        anInterface.assembly();
//        anInterface.setVisible(true);
//    }
    private void logicData() {
        if (data.size() != 0) {
            if (this.thread == null || this.thread.getState() == Thread.State.TERMINATED) {
                this.thread = new Thread(this);
                this.thread.start();
                this.status.append("Drawing");
                this.drawStatus.setText(this.status.toString());
            }
        } else {
            this.status.append("Draw.conf is null");
            this.drawStatus.setText(this.status.toString());
            this.status.delete(0,this.status.length());
            this.repaint();
        }
    }

    private void assembly() {
        Font font = new Font("隶书", Font.TRUETYPE_FONT, 40);
        JLabel title = new JLabel("Idiom Lottery Application");
        title.setBounds(250,50,500,50);
        title.setFont(font);
        this.add(title,BorderLayout.NORTH);

        this.contest = new JLabel("hello world");
        this.contest.setBounds(250,200,550,200);
        this.contest.setFont(new Font("宋体",Font.TRUETYPE_FONT,100));
        this.add(this.contest,BorderLayout.CENTER);

        this.drawStatus = new JLabel();
        this.drawStatus.setBounds(300, 450,150,50);
        this.add(this.drawStatus,BorderLayout.SOUTH);


        AbstractAction abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("start")) {
                    logicData();
                }
            }
        };

        JButton start = new JButton_("start");
        start.setBounds(300,500,200,50);
        start.addActionListener(abstractAction);
        this.add(start,BorderLayout.SOUTH);

        JFrame jFrame = this;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
//                int x = BasisDataProvider.MAIN_FRAME_LOC_X - (int) subWindowSize.getWidth() / 2 + BasisDataProvider.MAIN_FRAME_WIDTH/2;
//                int y = BasisDataProvider.MAIN_FRAME_LOC_Y - (int) subWindowSize.getHeight() / 2 + BasisDataProvider.MAIN_FRAME_HEIGHT/2;
//                dialog.setLocation(x, y);
//                System.out.println(jFrame.getSize());
                int jFrameWidth = jFrame.getWidth();
                int jFrameHeight = jFrame.getHeight();

                Rectangle titleBounds = title.getBounds();
                int titleX = (jFrameWidth - titleBounds.width) / 2;
//                int titleY = titleBounds.y;
                int titleY = (jFrameHeight - titleBounds.height) / 8;
                title.setBounds(titleX,titleY,titleBounds.width,titleBounds.height);

                Rectangle contestBounds = contest.getBounds();
                int contestX = (jFrameWidth - contestBounds.width) /2;
                int contestY = (jFrameHeight - contestBounds.height) / 3;
                contest.setBounds(contestX,contestY,contestBounds.width,contestBounds.height);

                Rectangle drawStatusBounds = drawStatus.getBounds();
                int drawStatusX = (jFrameWidth - drawStatusBounds.width) / 2;
                int drawStatusY = (int) ((jFrameHeight - drawStatusBounds.height) / 1.5);
                drawStatus.setBounds(drawStatusX,drawStatusY,drawStatusBounds.width,drawStatusBounds.height);

                Rectangle startBounds = start.getBounds();
                int startX = (jFrameWidth - startBounds.width) / 2;
                int startY = (int) ((jFrameHeight - startBounds.height) / 1.2);
                start.setBounds(startX,startY,startBounds.width,startBounds.height);
            }
        });

    }

    private int dataSize;

    public void setDataSize(int size) {
        this.dataSize = size;
    }

    private void randomData() {
        int i = 1;
//        int size = this.data.size();
        int count = this.dataSize / 6;
        for (int j = 0; j < this.dataSize; j++) {
            this.contest.setText(data.get(random.nextInt(data.size()-1)));
            this.repaint();
            i++;
            if (i > count) {
                this.status.append("  .");
                this.drawStatus.setText(this.status.toString());
                i = 1;
            }
            try {
                Thread.sleep(50);
            } catch (Exception ignored) {
            }
        }
        this.status.delete(0, status.length());
        this.drawStatus.setText(this.status.toString());
        this.repaint();
    }

    private void shuffleData() {
        Collections.shuffle(data);
        int i = 1;
        int s = 6;
        int size = data.size();
        int count = size / 6;
        for (int j = 0; j < 100 / size; j++) {
            for (String str : data) {
                this.contest.setText(str);
                this.repaint();
                i++;
                if (i > count) {
                    this.status.append("  .");
                    this.drawStatus.setText(this.status.toString());
                    i = 1;
                }
                try {
                    Thread.sleep(50);
                } catch (Exception ignored) {
                }
            }
        }
        this.status.delete(0, status.length());
        this.drawStatus.setText(this.status.toString());
        this.repaint();
    }
    @Override
    public void run() {
        this.randomData();
    }
}
