import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class question01GUI extends JFrame {
    private JButton union, intersection, difference, subset, cardinality,
                    rem, add, output, createButton, createCombo, elementOf;
    private JFrame create;
    private JScrollPane scrollable;
    private JPanel  left, right, main, setCommands, remAdd, radioButtons, mainWindow, labels, cButtonPanel, setComboOptions;
    private JTextField inputField,name,size;
    private JTextArea outputArea;
    private JComboBox setSelect;
    private LinkedSet[] sets;
    private String[] setNames;


    public question01GUI(){
        super("Set Theory");
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
        main = new JPanel(new GridLayout(1,2));
        right = new JPanel();
        left = new JPanel();
        setCommands = new JPanel(new GridLayout(6,1));
        remAdd = new JPanel();

        //Creating ComboBox
        sets = new LinkedSet[2];
        setNames = new String[2];
        sets = new LinkedSet[2];
        sets[0] = new LinkedSet(new IntNode(1,
                                new IntNode(2,
                                new IntNode(3,
                                new IntNode(4,null)))));
        sets[1] = new LinkedSet(new IntNode(3,
                                new IntNode(4,
                                new IntNode(5,
                                new IntNode(6,null)))));
        setNames[0] = "Set 1";
        setNames[1] = "Set 2";
        setSelect = new JComboBox(setNames);

        //Creatings Buttons
        union = new JButton("Union");
        setCommands.add(union);
        intersection = new JButton("Intersection");
        setCommands.add(intersection);
        difference = new JButton("Difference");
        setCommands.add(difference);
        subset = new JButton("Subset");
        setCommands.add(subset);
        cardinality = new JButton("Cardinality");
        setCommands.add(cardinality);
        output = new JButton("Output Selected Set");
        add = new JButton("Add");
        remAdd.add(add);
        rem = new JButton("Remove");
        remAdd.add(rem);
        elementOf = new JButton("∈?");
        setCommands.add(elementOf);

        //Creating JTextArea/Scrolling
        outputArea = new JTextArea(20,20);
        outputArea.setEditable(false);
        scrollable = new JScrollPane(outputArea);

        //Creating JTextField/
        inputField = new JTextField(12);

        //Adding to left panel
        left.add(setCommands);
        left.add(output);
        left.add(inputField);
        left.add(remAdd);
        left.add(setSelect);

        //Adding to right Panel
        right.add(scrollable);

        //Adding to main panel
        main.add(left);
        main.add(right);
        add(main);
    }

    public void addActions(){
        union.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                if(setSelect.getSelectedIndex() == 0)
                    outputArea.append(sets[0].union(sets[1]).toString() + "\n");
                else if(setSelect.getSelectedIndex() == 1)
                    outputArea.append(sets[1].union(sets[0]).toString() + "\n");
            }
        });
        intersection.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                if(setSelect.getSelectedIndex() == 0)
                    outputArea.append(sets[0].intersection(sets[1]).toString() + "\n");
                else if(setSelect.getSelectedIndex() == 1)
                    outputArea.append(sets[1].intersection(sets[0]).toString() + "\n");
            }
        });
        difference.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                if(setSelect.getSelectedIndex() == 0)
                    outputArea.append(sets[0].difference(sets[1]).toString() + "\n");
                else if(setSelect.getSelectedIndex() == 1)
                    outputArea.append(sets[1].difference(sets[0]).toString() + "\n");
            }
        });
        subset.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                if(setSelect.getSelectedIndex() == 0)
                    outputArea.append("Is Set 1 subset of Set 2?: " + sets[0].isSubset(sets[1]) + "\n");
                else if(setSelect.getSelectedIndex() == 1)
                    outputArea.append("Is Set 2 subset of Set 1?: " + sets[1].isSubset(sets[0]) + "\n");
            }
        });
        cardinality.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                if(setSelect.getSelectedIndex() == 0)
                    outputArea.append("Cardinality of Set 1 is: " + sets[0].cardinality() + "\n");
                else if(setSelect.getSelectedIndex() == 1)
                    outputArea.append("Cardinality of Set 2 is: " + sets[1].cardinality() + "\n");
            }
        });
        elementOf.addActionListener(new ActionListener(){

            @Override
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){
                try{
                    if(setSelect.getSelectedIndex() == 0)
                        if(sets[0].hasElement(Integer.parseInt(inputField.getText())))
                            outputArea.append("That element is in the Set!\n");
                        else
                            outputArea.append("That element is NOT in the Set!\n");
                    else if(setSelect.getSelectedIndex() == 1)
                        if(sets[1].hasElement(Integer.parseInt(inputField.getText())))
                            outputArea.append("That element is in the Set!\n");
                        else
                            outputArea.append("That element is NOT in the Set!\n");
                }
                catch(NumberFormatException ex){
                    outputArea.append("Invalid Type Entered!\n");
                    inputField.setText("");
                }
            }
        });

        add.addActionListener(new ActionListener(){

            @Override
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){
                    String[] elements = inputField.getText().split(",");
                    try{
                        if(setSelect.getSelectedIndex() == 0)
                            for(int i = 0; i < elements.length; i++)
                                sets[0].add(Integer.parseInt(elements[i]));
                        else if(setSelect.getSelectedIndex() == 1)
                            for(int i = 0; i < elements.length; i++)
                                sets[1].add(Integer.parseInt(elements[i]));
                    }
                    catch(NumberFormatException ex){
                        outputArea.append("Invalid Type Entered!\n");
                        inputField.setText("");
                    }
                inputField.setText("");
            }
        });
        rem.addActionListener(new ActionListener(){

            @Override
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){
                    String[] elements = inputField.getText().split(",");
                    try{
                        if(setSelect.getSelectedIndex() == 0)
                            for(int i = 0; i < elements.length; i++)
                                sets[0].remove(Integer.parseInt(elements[i]));
                        else if(setSelect.getSelectedIndex() == 1)
                            for(int i = 0; i < elements.length; i++)
                                sets[1].remove(Integer.parseInt(elements[i]));
                    }
                    catch(NumberFormatException ex){
                        outputArea.append("Invalid Type Entered!\n");
                        inputField.setText("");
                    }
                inputField.setText("");
            }
        });

        output.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                if(setSelect.getSelectedIndex() == 0)
                    outputArea.append(setNames[0] + " Contents: " + sets[0].toString() + "\n");
                else if(setSelect.getSelectedIndex() == 1)
                    outputArea.append(setNames[1] + " Contents: " + sets[1].toString() + "\n");
            }
        });
    }

    public static void main(String[] args){
        question01GUI gui = new question01GUI();
    }

}
