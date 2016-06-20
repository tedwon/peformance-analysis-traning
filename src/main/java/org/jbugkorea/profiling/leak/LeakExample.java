package org.jbugkorea.profiling.leak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 버튼을 추가하고 삭제하는 기능을 가진 메모리 누수 예제.
 * 버튼을 추가하고 삭제하는 경우 메모리 누수가 발생하는 지점을 찾아내어 수정하는 과제입니다.
 */
public class LeakExample extends JFrame {

    JPanel panel = new JPanel();
    JButton buttons[] = new JButton[100];
    int removeButtonIndex[] = new int[100];
    int numRemoveButtons = 0;
    int numButtons = 0;
    static boolean fixed = false;

    public LeakExample() {

        super("메모리 누수 예제");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        panel.setLayout(new FlowLayout());
        getContentPane().add("Center", panel);

        JPanel addRemovePanel = new JPanel();

        JButton addBtn = new JButton("버튼 추가");
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButtonToPanel();
            }
        });
        addRemovePanel.add(addBtn);

        JButton removeBtn = new JButton("버튼 삭제");
        removeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeButtonFromPanel();
            }
        });
        addRemovePanel.add(removeBtn);
        getContentPane().add("North", addRemovePanel);
    }

    public void addButtonToPanel() {
        if (numButtons < buttons.length) {
            JButton btn = new JButton(String.valueOf(numButtons));
            buttons[numButtons++] = btn;
            removeButtonIndex[numRemoveButtons++] = numButtons - 1;
            panel.add(btn);
            panel.validate();
            panel.repaint();
        }
    }

    public void removeButtonFromPanel() {
        if (numRemoveButtons > 0) {
            panel.remove(buttons[removeButtonIndex[--numRemoveButtons]]);

            if (fixed) {
                buttons[removeButtonIndex[numRemoveButtons]] = null;
            }

            panel.validate();
            panel.repaint();
        }
    }

    static public void main(String args[]) {

        if (args.length > 0) {
            if (args[0].equals("fix")) {
                fixed = true;
            }
        }

        JFrame frame = new LeakExample();
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

}