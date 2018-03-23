/**
 * LinkedDeque class Created by Joshua Renelli(0344117)
 */

public class LinkedDeque<E>{

    /**
     * Node class to be used by LinkedDeque
     */
    public class Node{
        private E data;
        private Node link;

        /**
         * Argumented Constructor for the Node class
         * @preCondition    n/a
         * @param         E    data value to set the node to
         * @param         Node link The node that this node links to
         * @postCondition   a new Node
         */
        public Node(E data, Node link){
            this.data = data;
            this.link = link;
        }

        public void setLink(Node link){this.link = link;}
        public Node getLink(){return link;}
        public void setData(E data){this.data = data;}
        public E getData(){return data;}
    }

    private Node head;
    private int size;

    /**
     * Default constructor for the LinkedDeque class
     * @preCondition    n/a
     * @postCondition   A new LinkedDeque object with head = null and size = 0
     */
    public LinkedDeque(){
        head = null;
        size = 0;
    }

    /**
     * Inserts an element at the front of the queue
     * @preCondition    n/a
     * @param        E e The element to be added
     * @postCondition   A queue with one more element
     */
    public void insertFront(E e){
        head = new Node(e,head);
        size++;
    }

    /**
     * Inserts an element at the end of the queue
     * @preCondition    n/a
     * @param        E e Element to be added
     * @postCondition   A queue with one less element
     */
    public void insertRear(E e){
        if(size == 0){
            head = new Node(e,null);
            size++;
        }
        else{
            Node cursor = head;
            while(cursor != null){
                if(cursor.getLink() == null){
                    cursor.setLink(new Node(e,null));
                    size++;
                    break;
                }
                cursor = cursor.getLink();
            }
        }
    }

    /**
     * Removes and returns the element at the front of the queue
     * @preCondition    size > 0
     * @postCondition   Returns the element at the front
     */
    public E removeFront(){
        E element = null;
        if(size > 0){
            element = (E)head.getData();
            head = head.getLink();
            size--;
        }
        return element;
    }

    /**
     * Removes and returns the element at the end of the queue
     * @preCondition    size > 0
     * @postCondition   Returns the element at the end of the queue
     */
    public E removeRear(){
        E element = null;
        if(size == 1){
            element = head.getData();
            head = null;
            size --;
        }
        if(size > 1){

            for(Node cursor = head; cursor != null; cursor = cursor.getLink())
                if(cursor.getLink().getLink() == null){
                    element = (E)cursor.getLink().getData();
                    cursor.setLink(null);
                    size--;
                    break;
                }
        }
        return element;
    }

    /**
     * Method to check the element at the front of the queue
     * @preCondition    size > 0
     * @postCondition   Returns the element at the front of the queue
     */
    public E front(){
        return (E)head.getData();
    }

    /**
     * Method to check the element at the end of the queue
     * @preCondition    size > 0
     * @postCondition Returns the element at the end of the queue
     */
    public E rear(){
        if(size == 0)
            return null;
        else if(size == 1)
            return head.getData();
        else
            for(Node cursor = head; cursor != null; cursor = cursor.getLink())
                if(cursor.getLink().getLink() == null)
                    return (E)cursor.getLink().getData();
        return null;
    }

    /**
     * Method to clear and set the LinkedDeque
     * @preCondition    n/a
     * @postCondition   The LinkedQueue will be empty
     */
    public void clear(){
        head = null;
        size = 0;
    }

    /**
     * Determines if the LinkedDeque is empty or not
     * @preCondition    n/a
     * @postCondition   Returns true if empty and false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Determines the number of elements in the queue
     * @preCondition    n/a
     * @postCondition   Returns the number of elements in the queue
     */
    public int size(){
        return size;
    }

    /**
     * The LinkedDeque's toString method
     * @preCondition    n/a
     * @postCondition   Returns a String representation of the LinkedDeque object
     */
    public String toString(){
        String output = "Deque: ";
        Node cursor = head;

        while(cursor != null){
            output += cursor.getData();
            if(cursor.getLink() != null)
                output += " -> ";
            cursor = cursor.getLink();
        }
        return output;
    }

    public static void main(String[] args){
        LinkedDeque<Integer> test = new LinkedDeque();
        test.insertFront(5);
        test.insertFront(4);
        test.insertFront(3);
        test.insertFront(2);
        test.insertFront(1);
        System.out.println(test);
        test.removeFront();
        test.removeFront();
        test.removeRear();
        test.removeRear();
        System.out.println(test);
        test.removeRear();
        System.out.println(test);
        test.insertRear(2);
        test.insertRear(4);
        test.insertRear(6);
        System.out.println(test);
        test.clear();
        System.out.println(test);
    }
}
