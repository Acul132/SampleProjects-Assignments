import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class question01GUI extends JFrame {
    private JPanel main;
    private JButton check;
    private JTextArea output;
    private JTextField input;
    private JLabel label;

    public question01GUI(){
        super("Word-By-Word Palindrome?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGui();
        setSize(300,130);
        setVisible(true);
        setResizable(false);
        addActions();
    }
    @SuppressWarnings("unchecked")
    public void createGui(){

        //Creating Panels
        main = new JPanel();

        //Input Field
        input = new JTextField(20);


        //Creating Output Area
        output = new JTextArea(1,20);
        output.setEditable(false);
        main.add(output);
        main.add(input);

        //Creating label
        label = new JLabel("^ Check for Palindrome ^");
        main.add(label);

        //Creating Buttons
        check = new JButton("Check Palindrome");
        main.add(check);

        add(main);
    }

    public void addActions(){
        check.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String line = input.getText();
                if(PalindromeTools.isPalindrome(line))
                    output.setText("That IS a word-word palindrome!");
                else
                    output.setText("That is NOT a word-word palindrome!");
            }
        });
    }

    public static void main(String[] args){
        question01GUI gui = new question01GUI();
    }

}
