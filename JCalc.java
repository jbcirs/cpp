package JCalc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DisplayPanel extends JPanel
{
   static JLabel jlabDisp;
   DisplayPanel()
   {
      setLayout(new GridLayout(1, 1));
      setOpaque(true);
      
      jlabDisp = new JLabel("0", SwingConstants.RIGHT);
      jlabDisp.setFont(new Font("Serif", Font.BOLD, 18));
      jlabDisp.setForeground(Color.RED);
      jlabDisp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
      
      add(jlabDisp);
   }
}

class KeypadPanel extends JPanel implements ActionListener
{
   JButton[] jbtn;
   final int ADD = 1;
   final int SUB = 2;
   final int MULT = 3;
   final int DIV = 4;
   final int NO_LAST_OP = 5;
   int leftOp = 0;
   int rightOp = 0;
   int lastMathOp = NO_LAST_OP;
   int displayText = 1;
   String str = "";

   KeypadPanel()
   {
      setLayout(new GridLayout(4, 4));
      setOpaque(true);
      
      jbtn = new JButton[16];
      for(int i = 0; i < 10; i++)
      {
         String buttonText = "" + i;
         jbtn[i] = new JButton(buttonText);
         jbtn[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
         jbtn[i].setActionCommand("number");
         jbtn[i].addActionListener(this);
      }
      
      jbtn[10] = new JButton("C");
      jbtn[10].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      jbtn[10].addActionListener(this);
      jbtn[11] = new JButton("=");
      jbtn[11].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      jbtn[11].addActionListener(this);
      jbtn[12] = new JButton("+");
      jbtn[12].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      jbtn[12].addActionListener(this);
      jbtn[13] = new JButton("-");
      jbtn[13].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      jbtn[13].addActionListener(this);
      jbtn[14] = new JButton("*");
      jbtn[14].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      jbtn[14].addActionListener(this);
      jbtn[15] = new JButton("/");
      jbtn[15].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      jbtn[15].addActionListener(this);
      
      add(jbtn[7]);
      add(jbtn[8]);
      add(jbtn[9]);
      add(jbtn[15]);
      add(jbtn[4]);
      add(jbtn[5]);
      add(jbtn[6]);
      add(jbtn[14]);
      add(jbtn[1]);
      add(jbtn[2]);
      add(jbtn[3]);
      add(jbtn[13]);
      add(jbtn[0]);
      add(jbtn[10]);
      add(jbtn[11]);
      add(jbtn[12]);
   }

   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getActionCommand().equals("number"))
      {
         JButton jbtnTemp = new JButton();
         jbtnTemp = (JButton) ae.getSource();
         str += jbtnTemp.getText();

         if(lastMathOp == NO_LAST_OP)
         {
            leftOp = Integer.parseInt(str);
            str = "" + leftOp;  //prevents leading zeros from being displayed
            DisplayPanel.jlabDisp.setText(str);
         }
         else
         {
            rightOp = Integer.parseInt(str);
            str = "" + rightOp;  //prevents leading zeros from being displayed
            DisplayPanel.jlabDisp.setText(str);
         }
      }
      else if(ae.getActionCommand().equals("+"))
      {
         lastMathOp = ADD;
         str = "";
      }
      else if(ae.getActionCommand().equals("-"))
      {
         lastMathOp = SUB;
         str = "";
      }
      else if(ae.getActionCommand().equals("*"))
      {
         lastMathOp = MULT;
         str = "";
      }
      else if(ae.getActionCommand().equals("/"))
      {
         lastMathOp = DIV;
         str = "";
      }
      else if(ae.getActionCommand().equals("="))
      {
         switch(lastMathOp)
         {
            case ADD:
               leftOp += rightOp;
               str = "" + leftOp;
               DisplayPanel.jlabDisp.setText(str);
               break;
            case SUB:
               leftOp -= rightOp;
               str = "" + leftOp;
               DisplayPanel.jlabDisp.setText(str);
               break;
            case MULT:
               leftOp *= rightOp;
               str = "" + leftOp;
               DisplayPanel.jlabDisp.setText(str);
               break;
            case DIV:
               leftOp /= rightOp;
               str = "" + leftOp;
               DisplayPanel.jlabDisp.setText(str);
               break;
         }
      }
      else if(ae.getActionCommand().equals("C"))
      {
         lastMathOp = NO_LAST_OP;
         str = "";
         DisplayPanel.jlabDisp.setText("0");
         if((ae.getModifiers() & ActionEvent.CTRL_MASK) != 0)
         {
            if(displayText % 2 != 0)
            {
               DisplayPanel.jlabDisp.setText("(C) 2011 Hall");
               displayText++;
            }
            else
            {
               DisplayPanel.jlabDisp.setText("version 0.1");
               displayText++;
            }
         }
      }
   }
}

class JCalc
{
   JCalc()
   {
      JFrame jfrm = new JFrame("JCalc");
      jfrm.setLayout(new GridLayout(2, 1));
      jfrm.setSize(215, 280);
      jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      DisplayPanel jpnlDisplay = new DisplayPanel();
      KeypadPanel jpnlKeypad = new KeypadPanel();
      jfrm.add(jpnlDisplay);
      jfrm.add(jpnlKeypad);

      JPanel cp = ((JPanel) jfrm.getContentPane());
      cp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      jfrm.getRootPane().setDefaultButton(jpnlKeypad.jbtn[11]);
      jfrm.setVisible(true);
   }
   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(new Runnable()
      {
         public void run()
         {
            new JCalc();
         }
      });
   }
}
