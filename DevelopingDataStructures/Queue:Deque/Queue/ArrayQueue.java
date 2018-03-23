/**
 * ArrayQueue method created by Joshua Renelli(0344117)
 */

public class ArrayQueue{
    private String[] array;
    private int r;
    private int f;

    /**
     * Default constructor fo the ArrayQueue class which creates
     * an ArrayQueue with size of 5
     * @preCondition    n/a
     * @postCondition      a new ArrayQueue with size of 5
     */
    public ArrayQueue(){
        array = new String[5];
        r = 0;
        f = 0;
    }

    /**
     * Argumented constructor for the ArrayQueue class which
     * sets the size to be euqal to "size"
     * @preCondition       size > 0
     * @param         int size variable of the arrayqueue size
     * @postCondition       new array queue of size "size"
     */
    public ArrayQueue(int size){
        array = new String[size];
        r = 0;
        f = 0;
    }

    /**
     * THe method which will add a value to the queue
     * @preCondition        n/a
     * @param        String word the word which will be added to the queueu
     * @postCondition       The new string will be added to the queue
     */
    public void enqueue(String word){
        if(!(size() == array.length-1)){
            array[r] = word;
            r = (r + 1) % array.length;
        }else{
            resize();
            enqueue(word);
        }
    }

    /**
     * Method which will return the element at the end of the queueu
     * and will also remove it from the queueu
     * @preCondition    The queue is not full
     * @postCondition   The queue will contain 1 less element
     */
    public String dequeue(){
        String word = array[f];
        array[f] = null;
        f = (f + 1) % array.length;
        return word;
    }

    /**
     * Returns the element at the front of the queueu
     * @preCondition        The element has at least 1 element
     * @postCondition       n/a
     */
    public String front(){
        return array[f];
    }

    /**
     * Method to clear the whole queue and reset it
     * @preCondition        n/a
     * @postCondition       The will be reset
     */
    public void clear(){
        r = 0;
        f = 0;
        array = new String[array.length];
    }

    /**
     * Method that is called whenever an element is added to
     * the queue which ensures that the queue has enough space
     * to hold the new element, this method will resize the queue
     * to double it's current size if necessary
     * @preCondition        The queue is full
     * @postCondition       A non full queue
     */
    public void resize(){
        int capacity = size();
        String[] temp = new String[capacity*2];
        for(int i = 0; i < capacity; i++)
            temp[i] = array[(f+i) % array.length];
        array = temp;
        f = 0;
        r = capacity;
    }

    /**
     * Method to check if the queue is empty
     * @preCondition    n/a
     * @postCondition   returns true if empty and false if not empty
     */
    public boolean isEmpty(){
        return r == f;
    }

    /**
     * Checks how many elements are in the queue
     * @preCondition        n/a
     * @postCondition       Returns the amount of elements in the queue
     */
    public int size(){
        return (array.length - f + r) % array.length;
    }
}
