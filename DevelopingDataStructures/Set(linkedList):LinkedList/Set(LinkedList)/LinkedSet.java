/**
 * A set class that utilizes the IntNode class to make a set container using the linked list data structure
 * @author Joshua Renelli(0344117)
 */

public class LinkedSet{

    private IntNode head;
    private int dataCount;

    /**
     * Creates an empty Set
     *
     * @preCondition n/a
     * @postCondition An empty set
     */
    public LinkedSet(){
        head = null;
        dataCount = 0;
    }

    /**
     * Creates an empty Set with a size using the given "capacity"
     *
     * @preCondition   Cannot at multiple linked nodes at once with the SAME value (can add multiple with different values)
     * @param         IntNode first node to be added to the list
     * @postCondition   Creates a list with "node" being the first node to be added
     */
    public LinkedSet(IntNode node){
        head = node;
        dataCount = IntNode.listLength(node);       //List length ensures the sime is right
    }
    /**
     * Adds a unique element to the set
     *
     * @preCondition the element to be added must be of the same type as the Set
     * @param        int item is the element that will be added to the set if unique
     * @postCondition the set will have a new element in it
     */
    public void add(int element){
        if(head == null)                                        //Allows for use of a default constructor
            head = new IntNode(element,null);
        else if(!hasElement(element)){
            head.addNodeAfter(element);
            dataCount++;
        }
    }

    /**
     * Removes the target element from the Set
     *
     * @preCondition    n/a
     * @param         int element is the target element that is going to be removed
     * @postCondition   If an element is removed the method returns true, if the
     *                  element is not found the method returns false
     */
    public void remove(int element){
        if(head == null)
            throw new IllegalArgumentException("There are no element's in the set to remove!");
        else if(head.getData() == element)
            head = head.getLink();
        else{
            IntNode cursor = head;
            while(cursor.getLink() != null){
                if(cursor.getLink().getData() == element){
                    cursor.removeNodeAfter();
                    dataCount--;
                    break;
                }
                cursor = cursor.getLink();
            }
        }
    }

    /**
     * Returns the head of the set
     *
     * @preCondition    n/a
     * @postCondition   returns the head of the set, if the head is null it will return null
     */
    public IntNode getSetHead(){
        return head;
    }

    /**
     * This is the Set's toString method which allows the Set to be printed out in
     * a viewable form for bug testing
     *
     * @preCondition    n/a
     * @postCondition   A string representation of this Set is returned
     */
    public String toString(){
        if(head != null)
            return head.toString();
        else
            return "Empty Set";
    }
     //////////////////////
    //Assignment Methods//
   //////////////////////

   /**
    * Determines how many unique elements are in the Set
    *
    * @preCondition    n/a
    * @postCondition   Returns the number of unique elements in the Set
    */
    public int cardinality(){
        return dataCount;
    }

    /**
     * Determines if the element is a member of the Set
     *
     * @preCondition    n/a
     * @param         int element is the element which you are checking if the Set hasElement
     * @postCondition   returns true if the element is a member of the set, but false
     * if the element is not a member of the set
     */
    public boolean hasElement(int element){
        IntNode cursor = head;
        while(cursor != null){
            if(cursor.getData() == element)
                return true;
            cursor = cursor.getLink();
        }
        return false;
    }

    /**
     * Determines if all of the elements in this Set are also elements of
     * the given Set, which if true would make it a subset of the given Set
     *
     * @preCondition    both sets must be of the same typeth
     * @param         LinkedSet otherSet the Set that will be check if this Set is a subset of it
     * @postCondition   Will return true if this set IS a subset of the given Set, and false
     *                  if it is NOT a subset
     */
    public boolean isSubset(LinkedSet otherSet){
        for(IntNode cursor = head; cursor != null; cursor = cursor.getLink())
            if(!otherSet.hasElement(cursor.getData()))
                return false;
        return true;
    }

    /**
     * Finds the set that contains all elements from either this set OR the given set
     *
     * @preCondition    n/a
     * @param         LinkedSet otherSet the other set used to find the Union
     * @postCondition   A Set which is a Union of this set and the given Set (otherSet)
     */
    public LinkedSet union(LinkedSet otherSet){
        LinkedSet newSet = new LinkedSet();
        for(IntNode cursor = head; cursor != null; cursor = cursor.getLink())
            newSet.add(cursor.getData());
        for(IntNode cursor = otherSet.getSetHead(); cursor != null; cursor = cursor.getLink())
            newSet.add(cursor.getData());
        return newSet;
    }

    /**
     * Finds the set that contains elements that are in both this set AND the given set
     *
     * @preCondition    both sets must be of the same type
     * @param         LinkedSet otherSet the set which will be used for finding the intersection
     * @postCondition   A Set which is an Intersection of this set and the given Set (otherSet)
     */
    public LinkedSet intersection(LinkedSet otherSet){
        LinkedSet newSet = new LinkedSet();
        for(IntNode cursor = head; cursor != null; cursor = cursor.getLink())
            if(otherSet.hasElement(cursor.getData()))
                newSet.add(cursor.getData());
        return newSet;
    }

    /**
     * Finds the elements that are in this Set and NOT in the given Set
     *
     * @preCondition    both Sets must be of the same type
     * @param         LinkedSet otherSet the set that will be used to find the difference
     * @postCondition   A set that contains the difference between this Set and the given Set
     */
    public LinkedSet difference(LinkedSet otherSet){
        LinkedSet newSet = new LinkedSet();
        for(IntNode cursor = head; cursor != null; cursor = cursor.getLink())
            if(!otherSet.hasElement(cursor.getData()))
                newSet.add(cursor.getData());
        return newSet;
    }
}
