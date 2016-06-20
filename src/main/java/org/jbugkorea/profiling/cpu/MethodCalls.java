package org.jbugkorea.profiling.cpu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MethodCalls extends JFrame {

    protected JTextArea textArea;

    protected String newline = "\n";

    protected int b1Count = 1;

    protected int b2Count = 1;

    protected int b3Count = 1;

    public MethodCalls() {
        super("메소드 호출 테스트");
        this.addWindowListener(new WindowCloser());

        JToolBar toolBar = new JToolBar();
        addButtons(toolBar);

        textArea = new JTextArea(5, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(400, 100));
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        setContentPane(contentPane);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("파일");
        menuBar.add(fileMenu);
        JMenuItem quitItem = new JMenuItem("종료");
        quitItem.addActionListener(new QuitListener());
        fileMenu.add(quitItem);

        JMenu helpMenu = new JMenu("도움말");
        menuBar.add(helpMenu);
        JMenuItem usageItem = new JMenuItem("사용법");
        usageItem.addActionListener(new UsageHelpListener());
        helpMenu.add(usageItem);

        setJMenuBar(menuBar);

        pack();
        setSize(300, 300);
        setVisible(true);
    }

    private void addButtons(JToolBar toolBar) {
        // first button
        JButton button1 = new JButton("버튼 1");
        button1.addActionListener(new Button1Listener());
        toolBar.add(button1);

        // second button
        JButton button2 = new JButton("버튼 2");
        button2.addActionListener(new Button2Listener());
        toolBar.add(button2);

        // third button
        JButton button3 = new JButton("버튼 3");
        button3.addActionListener(new Button3Listener());
        toolBar.add(button3);
    }

    protected void displayResult(String output) {
        textArea.append(output + newline);
    }

    protected void displayUsage() {
        JOptionPane.showMessageDialog(this,
                "Purpose:\n"
                        + "This Demo demonstrates Performance's ability to track method calls\n"
                        + "by allowing the user to control the number of calls to specific\n"
                        + "methods.\n\n"
                        + "Specification:\n"
                        + "Each button has a corresponding ActionListener.\n"
                        + "When a button is pressed, the actionPerformed method displays\n"
                        + "a message in the text area.  The user can see how Performance\n"
                        + "tracks method calls by comparing Performance's reported number\n"
                        + "of calls to each method and the data displayed in the text area.\n\n"
                        + "Test Procedures:\n"
                        + "   1. Use a Java 2 VM to run this program in Performance.\n"
                        + "   2. Click randomly on each of the three buttons displayed,\n"
                        + "      noting the output displayed in the text area below.\n"
                        + "      (Note the number of calls to each of the button's\n"
                        + "      ActionListeners)\n"
                        + "   3. Exit the application. Compare the known number of calls to\n"
                        + "      each listener with the Performance data in the snapshot taken\n"
                        + "      on exit.\n", "Usage",
                JOptionPane.INFORMATION_MESSAGE);
    }

    protected void closeApplication() {
        dispose();
        System.exit(0);
    }

    private class UsageHelpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayUsage();
        }
    }

    private class QuitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            closeApplication();
        }
    }

    private class Button1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayResult("버튼 1 호출 #" + b1Count);
            b1Count++;
        }
    }

    private class Button2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayResult("버튼 2 호출 #" + b2Count);
            b2Count++;
        }
    }

    private class Button3Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayResult("버튼 3 호출 #" + b3Count);
            b3Count++;
        }
    }

    private class WindowCloser extends WindowAdapter {
        public void windowClosing(WindowEvent evt) {
            closeApplication();
        }
    }

    public static void main(String[] args) {
        MethodCalls test = new MethodCalls();
        test.displayUsage();
    }

}