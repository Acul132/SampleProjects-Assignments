import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Question02GUI extends JFrame {
    private JPanel main,top,bottom;
    private LinkedDeque<Integer> deque;
    private JButton display, insBeg, insEnd, remBeg, remEnd, peekBeg, peekEnd, clear, isEmpty, size;
    private JTextArea output;
    private JScrollPane scrollable;
    private JTextField input;

    public Question02GUI(){
        super("Insertion Sort");
        deque = new LinkedDeque<Integer>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGui();
        setSize(500,450);
        setVisible(true);
        setResizable(false);
        addActions();

    }
    @SuppressWarnings("unchecked")
    public void createGui(){

        //Creating Panels
        main = new JPanel();

        //Input Field
        input = new JTextField(12);

        //Creating Output Area
        output = new JTextArea(18,40);
        output.setEditable(false);
        scrollable = new JScrollPane(output);
        main.add(scrollable);
        main.add(input);

        //Creating Buttons
        display = new JButton("Display Deque");
        main.add(display);
        insBeg = new JButton("Insert at Start");
        main.add(insBeg);
        insEnd = new JButton("Insert at End");
        main.add(insEnd);
        remBeg = new JButton("Remove from Start");
        main.add(remBeg);
        remEnd = new JButton("Remove from End");
        main.add(remEnd);
        peekBeg = new JButton("Display Start");
        main.add(peekBeg);
        peekEnd = new JButton("Display End");
        main.add(peekEnd);
        clear = new JButton("Clear Deque");
        main.add(clear);
        size = new JButton("Check Size");
        main.add(size);

        //Instanciating Deque


        add(main);
    }

    public void addActions(){
        display.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(deque.size() > 0)
                    output.append(deque + "\n");
                else
                    output.append("No Element's in Deque\n");
            }
        });

        insBeg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    deque.insertFront(Integer.parseInt(input.getText()));
                }catch(NumberFormatException ex){
                    System.out.println(ex);
                }
                input.setText("");
            }
        });

        insEnd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    deque.insertRear(Integer.parseInt(input.getText()));
                }catch(NumberFormatException ex){
                    System.out.println(ex);
                }
                input.setText("");
            }
        });

        remBeg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(deque.size() > 0)
                    deque.removeFront();
            }
        });

        remEnd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(deque.size() > 0)
                    deque.removeRear();
            }
        });

        peekBeg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(deque.size() > 0)
                    output.append("Start of queue: " + deque.front() + "\n");
                else
                    output.append("No Element's in Deque\n");
            }
        });

        peekEnd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(deque.size() > 0)
                    output.append("End of Queue: " + deque.rear() + "\n");
                else
                    output.append("No Element's in Deque\n");
            }
        });

        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                deque.clear();
                output.setText("");
            }
        });

        size.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                output.append("Number of Elements: " + deque.size() + "\n");
            }
        });
    }

    public static void main(String[] args){
        Question02GUI gui = new Question02GUI();
    }

}
