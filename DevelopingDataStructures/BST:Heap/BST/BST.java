
import java.io.*;
import java.util.Scanner;

/**
 * Binary Search Tree class created by Joshua Renelli(0344117)
 */
public class BST{
    private BTNode root;
    private String output, original;
    private int kData;

    /**
     * Inner node class to be used by the BST
     */
    private class BTNode{
        private int data;
        private BTNode left, right;

        public BTNode(int data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    /*
     * Default constructor to create a binary search Tree
     * @preCondition    n/a
     * @postCondition   empty BST
     */
    public BST(){
        root = null;
        original= "";
        output = "";
    }

    /**
     * Argumented BST constructor to create a BST with
     * "num" random numbers between 1 - 100
     * @preCondition    num > 0
     * @param         int num The number of elements to add to the BST
     * @postCondition   new BST with "num" elements
     */
    public BST(int num){
        root = null;
        original= "";
        output = "";
        generateRandom(num);
    }

    /**
     * Argumented BST constructor to create a BST with numbers
     * from a text file
     * @preCondition    file exists
     * @param         File file The file contianing the numbers
     * @postCondition   new BST created from file
     */
    public BST(File file){
        root = null;
        original= "";
        output = "";
        generateFile(file);
    }

    /**
     * Inserts the elements from the input file
     * @preCondition    file exists
     * @param        File file The file containing the numbers
     * @postCondition   n/a
     */
    public void generateFile(File file){
        try{
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){
                insert(scan.nextInt());
            }
            scan.close();

        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }

    /**
     * Generates "numElements" of random numbers and inserts them
     * @preCondition    numElements > 0
     * @param        int numElements the number of elements to inserts
     * @postCondition   n/a
     */
    public void generateRandom(int numElements){
        for(int i = 0; i < numElements; i++)
            insert((int)(Math.random()*100+1));
    }

    /**
     * Method to return the elements in ascending order
     * @preCondition    The BST is nonEmpty
     * @postCondition   Returns a string representation of the sorted tree
     */
    public String sortAscending(){
        output = "";
        sortA(root);
        return output;
    }

    /**
     * Recursive method to return the elements in ascending order
     * @preCondition    n/a
     * @param        BTNode node Current root to check
     * @postCondition   n/a
     */
    private void sortA(BTNode node){
        if(node.left!=null)
            sortA(node.left);
        output += node.data + " ";
        if(node.right!=null)
            sortA(node.right);
    }

    /**
     * Method to return the elements in ascending order
     * @preCondition    The BST is nonEmpty
     * @postCondition   A string representation of the sorted tree
     */
    public String sortDescending(){
        output = "";
        sortD(root);
        return output;
    }

    /**
     * Recursive method to return the elements in ascending order
     * @preCondition    n/a
     * @param        BTNode node Current root to check
     * @postCondition   n/a
     */
    private void sortD(BTNode node){
        if(node.right!=null)
            sortD(node.right);
        output += node.data + " ";
        if(node.left!=null)
            sortD(node.left);
    }

    /**
     * Method to return the originial numbers entered
     * @preCondition   n/a
     * @postCondition   Returns a string representation of the entered numbers
     */
    public String displayOriginal(){
        return original;
    }

    /**
     * Method to determine the height of the tree
     * @preCondition    n/a
     * @postCondition   Returns the height of the tree
     */
    public int height(){
        return height(root);
    }

    /**
     * Recursive definition to determine the height of the tree
     * @preCondition    n/a
     * @param         BTNode node Current root
     * @postCondition   Returns -1 if empty, 0 or greater if elements
     */
    private int height(BTNode node){
        if(node == null)
            return -1;
        return 1 + Math.max(height(node.left),height(node.right));
    }

    /**
     * Method to correctly insert an element into the BST
     * @preCondition    n/a
     * @param         int newNum Number to insert
     * @postCondition   Returns true if the number was entered correctly
     */
    public boolean insert(int newNum){
        if(root == null){
            root = new BTNode(newNum);
            original += (newNum + " ");
            return true;
        }else{
            original += (newNum + " ");
            return insert(newNum,root);
        }

    }

    /**
     * Recursive method to insert the number
     * @preCondition    n/a
     * @param         int    newNum Number to insert
     * @param         BTNode node   Current node
     * @postCondition   Returns true if the number was entered correctly
     */
    private boolean insert(int newNum, BTNode node){
        if(node.data == newNum)
            return false;
        if(newNum < node.data){
            if(node.left != null)
                return insert(newNum,node.left);
            else{
                node.left = new BTNode(newNum);
                return true;
            }
        }
        else{
            if(node.right != null)
                return insert(newNum,node.right);
            else{
                node.right = new BTNode(newNum);
                return true;
            }
        }
    }

    /**
     * Method to find the k'th smallest element in the BST
     * @preCondition    BST is nonEmpty
     * @param         int k index
     * @postCondition   The k'th smallest element
     */
    public int kthSmallest(int k){
        kData = 0;
        kthSmallest(k,root);
        return kData;

    }

    /**
     * Recursive method to determine the k'th smallest element
     * @preCondition    BST is nonEmpty
     * @param         int    k    index
     * @param         BTNode node current node
     * @postCondition   The k'th smallest element
     */
    private int kthSmallest(int k, BTNode node){
        if(node == null) return k;
        k = kthSmallest(k,node.left);
        k--;
        if(k == 0) {
            kData = node.data;
        }
        k = kthSmallest(k,node.right);
        return k;
    }

}
