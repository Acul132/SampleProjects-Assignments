    /**
     * A Set class that makes use of java generics to allow any type of Set to be made
     * @author Joshua Renelli(0344117)
     */
public class Set<E> {
    private static final int INITIAL_CAPACITY = 10;

    private Object[] setData;
    private int dataCount;

    /**
     * Creates an empty Set with an initial capacity of 10
     *
     * @preCondition n/a
     * @postCondition An empty set with size 10
     */
    public Set(){
        this(INITIAL_CAPACITY);
    }

    /**
     * Creates an empty Set with a size using the given "capacity"
     *
     * @preCondition    Capacity must be a positive number
     * @param         int capacity is the size of the set
     * @postCondition   Creates an empty set with size relative to the capacity
     */
    public Set(int capacity){
        if(capacity < 0)
            throw new IllegalArgumentException("Initial Capacity Cannot be Negative");
        setData = new Object[capacity];
        dataCount = 0;
    }

    /**
     * Adds a unique element to the set
     *
     * @preCondition the element to be added must be of the same type as the Set
     * @param        Int Element is the element that will be added to the set if unique
     * @postCondition the set will have a new element in it
     */
    public void add(E item){
        if(!contains(item)){                               //Ensures the element is not already in the Set
            ensureCapacity(dataCount+1);                   //Ensure the set is large enought to add another element
            setData[dataCount] = item;                     //and re-size's if need be
            dataCount++;
        }
    }

    /**
     * Allows you to add multiple elements at once into the Set
     *
     * @preCondition    The elements being added must be of the same type as the Set
     * @param        E... items     is an array of items that will be added to the Set
     * @postCondition   Any unique elements in the items array will be added to the Set
     */
    @SuppressWarnings("unchecked")
    public void addMany(E... items){
        for(int i = 0; i < items.length; i++){
            add(items[i]);
        }
    }

    /**
     * Removes the target element from the Set
     *
     * @preCondition    n/a
     * @param         E target is the target element that is going to be removed
     * @postCondition   If an element is removed the method returns true, if the
     *                  element is not found the method returns false
     */
    public boolean remove(E target){
        if(contains(target)){
            for(int i = indexOf(target); i < dataCount; i++)        //Uses the indexOf function to determine the
                setData[i] = setData[i+1];                          //index of the given element to remove
            dataCount--;
            return true;
        }
        return false;
    }

    /**
     * Determines the index of a target element
     *
     * @preCondition    n/a
     * @param         E item is the element you wish the find the index of
     * @postCondition   The method returns the index of the target element in the Set
     *                  and if it is not in the set, it returns -1
     */
    public int indexOf(E target){
        for(int i = 0; i < dataCount; i++)
            if(setData[i].equals(target))return i;
        return -1;
    }

    /**
     * Ensures that the Set will be large enough to hold any given number of items
     * and in the case that it is not large enough, it will resize the Set to be large
     * enough
     *
     * @preCondition    minimumCapacity > 0
     * @param        int minimumCapacity [description]
     * @postCondition   Changes the capacity to minimumCapacity unless the capacity
     * of the set was already large enough
     */
    public void ensureCapacity(int minimumCapacity){
        Object[] newArray;
        if(setData.length < minimumCapacity){
            newArray = new Object[minimumCapacity];
            for(int i = 0; i < dataCount; i++)
                newArray[i] = setData[i];
            setData = newArray;
        }
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
     * @param         E item is the element which you are checking if the Set contains
     * @postCondition   returns true if the element is a member of the set, but false
     * if the element is not a member of the set
     */
    public boolean contains(E item){
        for(int i = 0; i < dataCount; i++)
            if(setData[i].equals(item)) return true;    //Returns true if requested item is in the array
        return false;                                   //Returns false if the requested item is not found
    }

    /**
     * Finds the set that contains all elements from either this set OR the given set
     *
     * @preCondition    both sets must be of the same type
     * @param         Set<E> otherSet the other set used to find the Union
     * @postCondition   A Set which is a Union of this set and the given Set (otherSet)
     */
    @SuppressWarnings("unchecked")
    public Set<E> union(Set<E> otherSet){
        Set<E> combinedSet = new Set<>((dataCount + otherSet.dataCount)*2);
        for(int i = 0; i < dataCount; i++)
            combinedSet.add((E)setData[i]);
        for(int i = 0; i < otherSet.dataCount; i++)
            combinedSet.add((E)otherSet.setData[i]);
        return combinedSet;
    }

    /**
     * Finds the set that contains elements that are in both this set AND the given set
     *
     * @preCondition    both sets must be of the same type
     * @param         Set<E> otherSet the set which will be used for finding the intersection
     * @postCondition   A Set which is an Intersection of this set and the given Set (otherSet)
     */
    @SuppressWarnings("unchecked")
    public Set<E> intersection(Set<E> otherSet){
        Set<E> combinedSet = new Set<>((dataCount + otherSet.dataCount)*2);
        for(int i = 0; i < dataCount; i++){
            if(otherSet.contains((E)setData[i]))
                combinedSet.add((E)setData[i]);
        }
        for(int i = 0; i < otherSet.dataCount; i++){
            if(contains((E)otherSet.setData[i]))
                combinedSet.add((E)otherSet.setData[i]);
        }
        return combinedSet;
    }

    /**
     * Finds the elements that are in this Set and NOT in the given Set
     *
     * @preCondition    both Sets must be of the same type
     * @param         Set<E> otherSet the set that will be used to find the difference
     * @postCondition   A set that contains the difference between this Set and the given Set
     */
    @SuppressWarnings("unchecked")
    public Set<E> difference(Set<E> otherSet){
        Set<E> combinedSet = new Set<>(dataCount);
        for(int i = 0; i < dataCount; i++)
            if(!otherSet.contains((E)setData[i]))
                combinedSet.add((E)setData[i]);         //Add's elements to the newly created set that are in
        return combinedSet;                             //both of the Set's
    }

    /**
     * Determines if all of the elements in this Set are also elements of
     * the given Set, which if true would make it a subset of the given Set
     *
     * @preCondition    both sets must be of the same typeth
     * @param         Set<E> otherSet the Set that will be check if this Set is a subset of it
     * @postCondition   Will return true if this set IS a subset of the given Set, and false
     *                  if it is NOT a subset
     */
    @SuppressWarnings("unchecked")
    public boolean isSubset(Set<E> otherSet){
        for(int i = 0; i < dataCount; i++)
            if(!otherSet.contains((E)setData[i]))
                return false;
        return true;
    }

    /**
     * This is the Set's toString method which allows the Set to be printed out in
     * a viewable form for bug testing
     *
     * @preCondition    n/a
     * @postCondition   A string representation of this Set is returned in the
     *                  form --> Set Contents [1 , 2, 3, 4]
     */
    public String toString(){
        String output = "[";
        for(int i = 0; i < dataCount; i++){
            output+= (" " + setData[i] + ",");
        }
        output = output.substring(0,output.length()-1);         //Substring used to remove the final comma
        output+= "]";                                           //that would appear at the end of the String
        return output;
    }
}
