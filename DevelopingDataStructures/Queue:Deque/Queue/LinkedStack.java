/**
 * Linked stack class Created by Joshua Renelli(0344117)
 */
public class LinkedStack{
    private Node top;
    private int size;

    /**
     * Default constructor to create a LinkedStack
     * @preCondition    n/a
     * @postCondition   A new LinkedStack object
     */
    public LinkedStack(){
        top = null;
        size = 0;
    }

    /**
     * Method to push a word to the top of the stack
     * @preCondition    n/a
     * @param        String word The word to be pushed onto the LinkedStack
     * @postCondition   The stack will contain 1 more element
     */
    public void push(String word){
        top = new Node(word,top);
        size++;
    }

    /**
     * Method will "pop" an element off the stack which returns the element
     * on the top of the stack and returns it at the same time
     * @preCondition    The stack is nonEmpty
     * @postCondition   The stack will contain one less element
     */
    public String pop(){
        if(isEmpty())
            throw new IllegalArgumentException();

        String topValue = top.getData();
        top = top.getLink();
        size--;
        return topValue;
    }

    /**
     * Will return the top element without removing it
     * @preCondition    Stack is nonEmpty
     * @postCondition   n/a
     */
    public String peek(){
        if(isEmpty())
            throw new IllegalArgumentException();
        return top.getData();
    }

    /**
     * Method to clear and reset the stack to be empty
     * @preCondition    n/a
     * @postCondition   THe stack will be empty
     */
    public void clear(){
        top = null;
        size = 0;
    }

    /**
     * Method to determine if the stack is empty
     * @preCondition    n/a
     * @postCondition   Returns true if empty and false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }
}
