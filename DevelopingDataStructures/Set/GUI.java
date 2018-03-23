import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private JButton union, intersection, difference, subset, cardinality,
                    createNew, rem, add, output, createButton, createCombo, elementOf;
    private JFrame create, setCombo;
    private JLabel comboType;
    private JScrollPane scrollable;
    private JRadioButton strng,intg,doubl;
    private ButtonGroup typeOption;
    private JPanel  left, right, main, setCommands, remAdd, radioButtons, mainWindow, labels, cButtonPanel, setComboOptions;
    private JTextField inputField,name,size;
    private JTextArea outputArea;
    private JComboBox setSelect, option1, option2;
    private String[] setNames, setType;
    private Set[] sets;
    private int index;

    public GUI(){
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
        setNames = new String[100];
        sets = new Set[100];
        setType = new String[100];
        setNames[0] = "Select a Set";
        setSelect = new JComboBox(setNames);
        index = 1;

        //Creatings Buttons
        union = new JButton("Union");
        union.setEnabled(false);
        setCommands.add(union);
        intersection = new JButton("Intersection");
        intersection.setEnabled(false);
        setCommands.add(intersection);
        difference = new JButton("Difference");
        difference.setEnabled(false);
        setCommands.add(difference);
        subset = new JButton("Subset");
        subset.setEnabled(false);
        setCommands.add(subset);
        cardinality = new JButton("Cardinality");
        cardinality.setEnabled(false);
        setCommands.add(cardinality);
        output = new JButton("Output Selected Set");
        add = new JButton("Add");
        remAdd.add(add);
        rem = new JButton("Remove");
        remAdd.add(rem);
        createNew = new JButton("Create New Set");
        elementOf = new JButton("∈?");
        elementOf.setEnabled(false);
        setCommands.add(elementOf);

        //Creating JTextArea/Scrolling
        outputArea = new JTextArea(20,20);
        outputArea.setLineWrap(true);
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
        left.add(createNew);

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
                setCombinations("Union");
            }
        });
        intersection.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                setCombinations("Intersection");
            }
        });
        difference.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                setCombinations("Difference");
            }
        });
        subset.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                setCombinations("Subset");
            }
        });
        cardinality.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                int currentIndex = setSelect.getSelectedIndex();
                if(setSelect.getSelectedIndex() > 0)
                    outputArea.append("Cardinality of selected Set : " + sets[currentIndex].cardinality() + "\n");
            }
        });
        elementOf.addActionListener(new ActionListener(){

            @Override
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){
                try{
                    int setIndex = setSelect.getSelectedIndex();
                    if(setIndex > 0){
                        String element = inputField.getText();
                        if(setType[setIndex].equals("String")){
                            if(sets[setIndex].contains(element))
                                outputArea.append(element + " is an element of the set " + setNames[setIndex] + "\n");
                                else
                                outputArea.append(element + " is not an element of the set " + setNames[setIndex] + "\n");
                            }
                            else if(setType[setIndex].equals("Integer")){
                                if(sets[setIndex].contains(Integer.parseInt(element)))
                                outputArea.append(element + " is an element of the set " + setNames[setIndex] + "\n");
                                else
                                outputArea.append(element + " is not an element of the set " + setNames[setIndex] + "\n");
                            }
                            else if(setType[setIndex].equals("Double")){
                                if(sets[setIndex].contains(Double.parseDouble(element)))
                                outputArea.append(element + " is an element of the set " + setNames[setIndex] + "\n");
                                else
                                outputArea.append(element + " is not an element of the set " + setNames[setIndex] + "\n");
                            }
                        }
                }
                catch(NumberFormatException ex){
                    outputArea.append("Invalid Type Entered!\n");
                    inputField.setText("");
                }
            }
        });
        createNew.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                createSet();
            }
        });
        add.addActionListener(new ActionListener(){

            @Override
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){
                if(index > 1 && setSelect.getSelectedIndex() > 0){
                    int setIndex = setSelect.getSelectedIndex();
                    try{
                        String[] elements = inputField.getText().split(",");
                        if(setType[setIndex].equals("String")){
                            for(int i = 0; i < elements.length; i++)
                                sets[setIndex].add(elements[i]);
                            }
                            else if(setType[setIndex].equals("Integer")){
                                for(int i = 0; i < elements.length; i++)
                                sets[setIndex].add(Integer.parseInt(elements[i]));
                            }
                            else if(setType[setIndex].equals("Double")){
                                for(int i = 0; i < elements.length; i++)
                                sets[setIndex].add(Double.parseDouble(elements[i]));
                            }
                        }catch(NumberFormatException ex){
                            outputArea.append("Invalid Type Entered!\n");
                        }
                    }
                inputField.setText("");
            }
        });
        rem.addActionListener(new ActionListener(){

            @Override
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){
                if(index > 1 && setSelect.getSelectedIndex() > 0){
                    int setIndex = setSelect.getSelectedIndex();
                    try{
                        String[] elements = inputField.getText().split(",");
                        if(setType[setIndex].equals("String")){
                            for(int i = 0; i < elements.length; i++)
                                sets[setIndex].remove(elements[i]);
                            }
                            else if(setType[setIndex].equals("Integer")){
                                for(int i = 0; i < elements.length; i++)
                                sets[setIndex].remove(Integer.parseInt(elements[i]));
                            }
                            else if(setType[setIndex].equals("Double")){
                                for(int i = 0; i < elements.length; i++)
                            sets[setIndex].remove(Double.parseDouble(elements[i]));
                        }
                    }
                    catch(NumberFormatException ex){
                        outputArea.append("Invalid Type Entered!\n");
                        inputField.setText("");
                    }
                }
                inputField.setText("");
            }
        });

        output.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                if(index > 1 && setSelect.getSelectedIndex() > 0){
                    int setIndex = setSelect.getSelectedIndex();
                    outputArea.append(setNames[setIndex] + " Contents: " + sets[setIndex].toString() + "\n");
                }
            }
        });
    }

    public void createSet(){
        //Creating Window
        create = new JFrame("Create New Set");
        createButton = new JButton("Create Set");
        radioButtons = new JPanel();
        mainWindow = new JPanel(new GridLayout(3,1));
        labels = new JPanel(new GridLayout(2,2));
        cButtonPanel = new JPanel();
        cButtonPanel.add(createButton);

        typeOption = new ButtonGroup();
        strng = new JRadioButton("String");
        strng.setSelected(true);
        typeOption.add(strng);
        radioButtons.add(strng);
        intg = new JRadioButton("Integer");
        typeOption.add(intg);
        radioButtons.add(intg);
        doubl = new JRadioButton("Double");
        typeOption.add(doubl);
        radioButtons.add(doubl);

        name = new JTextField(10);
        size = new JTextField(10);
        JLabel nameLabel = new JLabel("Name: ");
        JLabel sizeLabel = new JLabel("Size: ");

        labels.add(nameLabel);
        labels.add(name);
        labels.add(sizeLabel);
        labels.add(size);

        mainWindow.add(labels);
        mainWindow.add(radioButtons);
        mainWindow.add(cButtonPanel);

        create.add(mainWindow);

        create.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        create.setSize(300,200);
        create.pack();
        create.setVisible(true);

        createButton.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e){
            try{
                int sizeNumber = Integer.parseInt(size.getText());
                if(strng.isSelected()){
                    sets[index] = new Set<String>(sizeNumber);
                    setType[index] = "String";
                }
                else if(intg.isSelected()){
                    sets[index] = new Set<Integer>(sizeNumber);
                    setType[index] = "Integer";
                }
                else{
                    sets[index] = new Set<Double>(sizeNumber);
                    setType[index] = "Double";
                }
                setNames[index] = name.getText();
                index++;
                updateCombo(setSelect);
                setSelect.setSelectedIndex(index-1);
                elementOf.setEnabled(true);
                cardinality.setEnabled(true);
                if(index > 2){
                    union.setEnabled(true);
                    intersection.setEnabled(true);
                    difference.setEnabled(true);
                    subset.setEnabled(true);
                }
                create.dispose();
            }
            catch(NumberFormatException ex){
                size.setText("Invalid Size");
            }
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void setCombinations(String method){
        setCombo = new JFrame("Set Functions");
        option1 = new JComboBox(setNames);
        updateCombo(option1);
        option2 = new JComboBox(setNames);
        updateCombo(option2);
        setComboOptions = new JPanel();

        switch(method){
            case "Union":
                comboType = new JLabel("OR");
                createCombo = new JButton("Display Union");
                break;

            case "Intersection":
                comboType = new JLabel("AND");
                createCombo = new JButton("Display Intersection");
                break;

            case "Difference":
                comboType = new JLabel("NOT");
                createCombo = new JButton("Display Difference");
                break;

            case "Subset":
                comboType = new JLabel("SUBSET OF?");
                createCombo = new JButton("Display Result");
                break;

            default:
                System.out.println("Error, combo type not correct");
        }

        setComboOptions.add(option1);
        setComboOptions.add(comboType);
        setComboOptions.add(option2);
        setComboOptions.add(createCombo);

        setCombo.add(setComboOptions);
        setCombo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setCombo.setSize(300,200);
        setCombo.pack();
        setCombo.setVisible(true);

        createCombo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                int setIndex1 = option1.getSelectedIndex();
                int setIndex2 = option2.getSelectedIndex();
                if(setIndex1 > 0 && setIndex2 > 0){
                    if(comboType.getText().equals("OR")){
                        outputArea.append("Union : " + sets[setIndex1].union(sets[setIndex2]).toString() + "\n");
                    }
                    else if(comboType.getText().equals("AND")){
                        outputArea.append("Intersection : " + sets[setIndex1].intersection(sets[setIndex2]).toString() + "\n");
                    }
                    else if(comboType.getText().equals("NOT")){
                        outputArea.append("Difference : " + sets[setIndex1].difference(sets[setIndex2]).toString() + "\n");
                    }
                    else if(comboType.getText().equals("SUBSET OF?")){
                        if(sets[setIndex1].isSubset(sets[setIndex2]))
                            outputArea.append(setNames[setIndex1] + " is a subset of " + setNames[setIndex2] + "\n");
                        else
                            outputArea.append(setNames[setIndex1] + " is not a subset of " + setNames[setIndex2] + "\n");
                        }
                    setCombo.dispose();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void updateCombo(JComboBox comboBox){
        comboBox.removeAllItems();
        for(int i = 0; i < index; i++){
            comboBox.addItem(setNames[i]);
        }
    }

    public static void main(String[] args){
        GUI gui = new GUI();
    }
}
