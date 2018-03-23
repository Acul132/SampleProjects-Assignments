import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Question01GUI extends JFrame {
    private JPanel main,top,bottom;
    private BST tree;
    private JButton random,chooseFile,sortedA,sortedD,kth,height,unsorted;
    private JTextArea output;
    private JScrollPane scrollable;
    private JTextField input;
    private JFileChooser fileC;

    public Question01GUI(){
        super("Binary Search Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGui();
        setSize(650,425);
        setVisible(true);
        setResizable(false);
        addActions();

    }
    @SuppressWarnings("unchecked")
    public void createGui(){

        fileC = new JFileChooser();

        //Creating Panels
        main = new JPanel();
        top = new JPanel();
        bottom = new JPanel();

        //Input Field
        input = new JTextField(12);

        //Creating Output Area
        output = new JTextArea(18,40);
        output.setEditable(false);
        scrollable = new JScrollPane(output);
        main.add(scrollable);
        top.add(input);

        //Creating Buttons
        random = new JButton("Create Random");
        top.add(random);
        chooseFile = new JButton("Choose File");
        top.add(chooseFile);
        sortedA = new JButton("Display Sorted(Asc)");
        sortedA.setEnabled(false);
        bottom.add(sortedA);
        sortedD = new JButton("Display Sorted(Desc)");
        sortedD.setEnabled(false);
        bottom.add(sortedD);
        unsorted = new JButton("Display Unsorted");
        unsorted.setEnabled(false);
        bottom.add(unsorted);
        height = new JButton("Display Height");
        height.setEnabled(false);
        bottom.add(height);
        kth = new JButton("Find K'th Smallest");
        kth.setEnabled(false);
        top.add(kth);

        main.add(top);
        main.add(bottom);
        //Instanciating BST
        tree = new BST();

        add(main);
    }

    public void addActions(){
        random.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    tree = new BST(Integer.parseInt(input.getText()));
                    output.append("Numbers Added: " + tree.displayOriginal() + "\n");
                    input.setText("");
                    sortedA.setEnabled(true);
                    sortedD.setEnabled(true);
                    unsorted.setEnabled(true);
                    height.setEnabled(true);
                    kth.setEnabled(true);
                }catch(NumberFormatException ex){
                    output.append("Must be of type INT\n");
                }
            }
        });

        chooseFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int result = fileC.showOpenDialog(Question01GUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    tree = new BST(fileC.getSelectedFile());
                    output.append("Numbers Added: " + tree.displayOriginal() + "\n");
                    sortedA.setEnabled(true);
                    sortedD.setEnabled(true);
                    unsorted.setEnabled(true);
                    height.setEnabled(true);
                    kth.setEnabled(true);
                }
            }
        });

        sortedA.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                output.append("Sorted Ascending: " + tree.sortAscending() + "\n");
            }
        });

        sortedD.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                output.append("Sorted Ascending: " + tree.sortDescending() + "\n");
            }
        });

        unsorted.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                output.append("Unsorted Input: " + tree.displayOriginal() + "\n");
            }
        });

        height.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                output.append("Height of Tree: " + tree.height() + "\n");
            }
        });

        kth.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    output.append("K'th Smallest Element: " + tree.kthSmallest(Integer.parseInt(input.getText())) + "\n");
                    input.setText();
                }catch(NumberFormatException ex){
                    output.append("Must be of type INT\n");
                }
            }
        });

    }

    public static void main(String[] args){
        Question01GUI gui = new Question01GUI();
    }

}
