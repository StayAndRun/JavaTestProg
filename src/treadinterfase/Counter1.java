/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treadinterfase;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Counter1 extends JFrame {

    private JButton start = new JButton("Start"),
            onOff = new JButton("Switch");
    private JTextField txt = new JTextField(10);
    private SeparateSubTask sp = null;
    
    public void init() {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(txt);
        start.addActionListener(new StartL());
        cp.add(start);
        onOff.addActionListener(new OnOffL());
        cp.add(onOff);
    }

    private class SeparateSubTask extends Thread {

        public SeparateSubTask() {
            start();
        }
        private int count = 0;
        private boolean runFlag = true;
        
        public void invertFlag() {
            runFlag = !runFlag;
        }
        
        public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("Performance is interrupted");
            }
            if (runFlag) {
                txt.setText(Integer.toString(count++));
            }
        }
            
        }

    }

    private class StartL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (sp == null)
                sp = new SeparateSubTask();
        }
    }

    private class OnOffL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (sp != null) {
                sp.invertFlag();
            }
        }
    }

    public static void main(String[] args) {
        Counter1 test = new Counter1();
        test.init();
        test.setDefaultCloseOperation(EXIT_ON_CLOSE);
        test.setResizable(false);
        test.setSize(300, 75);
        test.setVisible(true);
    }

}
