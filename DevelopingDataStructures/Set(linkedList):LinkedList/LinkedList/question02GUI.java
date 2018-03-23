import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class question02GUI extends JFrame {
    private JPanel main,top,bottom;
    IntNode head;
    private JButton create,display,sort,reverse,reset;
    private JTextArea output;
    private JScrollPane scrollable;
    private JTextField input;

    public question02GUI(){
        super("Insertion Sort");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGui();
        setSize(500,400);
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
        create = new JButton("Create");
        main.add(create);
        display = new JButton("Display");
        display.setEnabled(false);
        main.add(display);
        sort = new JButton("Sort List");
        sort.setEnabled(false);
        main.add(sort);
        reverse = new JButton("Reverse");
        reverse.setEnabled(false);
        main.add(reverse);
        reset = new JButton("Reset");
        reset.setEnabled(false);
        main.add(reset);

        //Instanciating head
        IntNode head = null;

        add(main);
    }

    public void addActions(){
        create.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    int numOfElements = Integer.parseInt(input.getText());
                    if(numOfElements > 0){
                        head = new IntNode((int)((Math.random()*1000)+1),null);
                        if(numOfElements > 1)
                            LinkedListInsertion.fillList(head,numOfElements);
                    }
                    input.setText("");
                    output.append(head + "\n");
                    display.setEnabled(true);
                    sort.setEnabled(true);
                    reset.setEnabled(true);
                    reverse.setEnabled(true);
                    create.setEnabled(false);
                }catch(NumberFormatException exep){
                    output.append("Enter and Integer Value!\n");
                }
            }
        });
        display.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                output.append(head + "\n");
            }
        });
        sort.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                head = LinkedListInsertion.insertionSort(head);
                sort.setEnabled(false);
            }
        });
        reverse.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                head = LinkedListInsertion.reverse(head);
            }
        });
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                output.setText("");
                head = null;
                create.setEnabled(true);
                input.setText("");
                display.setEnabled(false);
                sort.setEnabled(false);
                reset.setEnabled(false);
                reverse.setEnabled(false);
            }
        });
    }

    public static void main(String[] args){
        question02GUI gui = new question02GUI();
    }

}
