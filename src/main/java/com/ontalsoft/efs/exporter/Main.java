package com.ontalsoft.efs.exporter;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.ontalsoft.efs.exporter.swing.SwingUtil;

public class Main {

    public static final String MAIN_FRAME = "mainFrame";
    public static final String PANEL_MAIN = "panelMain";
    public static final String TEXT_LOG = "textLog";
    public static final String TEXT_BINPATH = "textBinPath";
    public static final String TEXT_BMPPATH = "textBmpPath";

    private Map<String, Component> componentMap;
    private JTextArea textLog;

    public Main() {
        initialize();
    }

    public void appendToLog(final String text) {
        this.textLog.setText(this.textLog.getText() + text);
        this.textLog.setCaretPosition(this.textLog.getCaretPosition());
    }

    public static void main(final String[] args) {
        try {
            for(final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(final Exception e) {
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final Main main = new Main();
                    final JFrame mainFrame = SwingUtil.getComponentByName(main.getComponentMap(), Main.MAIN_FRAME);
                    mainFrame.setVisible(true);

                    //scanBinDirectory("e:/_CODE/_PROJECTS/efs_phoenix/EFS/BIN/", main);
                    Operations.scanBinDirectory("", main);

                }
                catch(final Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        final Main windowMain = this;
        final Events events = new Events();

        final JFrame mainFrame = new JFrame();
        mainFrame.setName(MAIN_FRAME);
        mainFrame.setTitle("EFS Graphics Converter v1.0 by akaine");
        mainFrame.setResizable(false);
        mainFrame.setBounds(100, 100, 510, 410);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panelMain = new JPanel();
        panelMain.setName(PANEL_MAIN);
        panelMain.setBounds(170, 0, 500, 400);
        mainFrame.getContentPane().add(panelMain);
        panelMain.setLayout(null);

        final JButton btnBinPathBrowse = new JButton("Browse...");
        btnBinPathBrowse.setBounds(405, 7, 79, 23);
        btnBinPathBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                events.browseBinPath(e, windowMain);
            }
        });
        panelMain.add(btnBinPathBrowse);

        final JButton btnBmpPathBrowse = new JButton("Browse...");
        btnBmpPathBrowse.setBounds(405, 33, 79, 23);
        panelMain.add(btnBmpPathBrowse);

        final JTextField textBinPath = new JTextField();
        textBinPath.setName(TEXT_BINPATH);
        textBinPath.setBounds(77, 8, 318, 20);
        textBinPath.setEditable(false);
        panelMain.add(textBinPath);
        textBinPath.setColumns(10);

        final JTextField textBmpPath = new JTextField();
        textBmpPath.setName(TEXT_BMPPATH);
        textBmpPath.setBounds(77, 34, 318, 20);
        textBmpPath.setEditable(false);
        panelMain.add(textBmpPath);
        textBmpPath.setColumns(10);

        final JButton btnBin2Bmp = new JButton("Export graphics BINs to BMPs");
        btnBin2Bmp.setBounds(10, 68, 230, 23);
        btnBin2Bmp.setEnabled(false);
        btnBin2Bmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                events.convertBinsToBmps(e, windowMain);
            }
        });
        panelMain.add(btnBin2Bmp);

        final JButton btnBmp2Bin = new JButton("Overwrite graphics BINs with BMP data");
        btnBmp2Bin.setBounds(254, 68, 230, 23);
        panelMain.add(btnBmp2Bin);

        final JLabel lblStatus = new JLabel("Event log:");
        lblStatus.setBounds(10, 102, 183, 14);
        panelMain.add(lblStatus);

        final JScrollPane textLogScroll = new JScrollPane();
        textLogScroll.setBounds(10, 118, 474, 245);
        panelMain.add(textLogScroll);

        textLog = new JTextArea();
        textLog.setWrapStyleWord(true);
        textLog.setLineWrap(true);
        textLog.setName(TEXT_LOG);
        textLog.setEditable(false);
        textLog.setFont(textLog.getFont().deriveFont(11f));
        textLog.setBounds(10, 118, 474, 245);
        textLogScroll.setViewportView(textLog);

        final JLabel lblBinPath = new JLabel("BINs path:");
        lblBinPath.setBounds(10, 11, 57, 14);
        panelMain.add(lblBinPath);

        final JLabel lblBmpPath = new JLabel("BMPs path:");
        lblBmpPath.setBounds(10, 37, 57, 14);
        panelMain.add(lblBmpPath);

        componentMap = SwingUtil.createComponentMap(mainFrame);
    }

    public Map<String, Component> getComponentMap() {
        return componentMap;
    }

    public JTextArea getTextLog() {
        return textLog;
    }

    public void setTextLog(final JTextArea textLog) {
        this.textLog = textLog;
    }
}
