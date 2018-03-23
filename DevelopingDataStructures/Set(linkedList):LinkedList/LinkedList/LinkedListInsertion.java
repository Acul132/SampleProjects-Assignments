/**
 * A class that implements static methods which can reverse linked lists,
 * sort a linked list using insertionSort, and fill a linked list with random integers
 * @author Joshua Renelli(0344117)
 */

public class LinkedListInsertion{

    /**
     * The reverse methods revereses the order of a given list
     * @preCondition    n/a
     * @param           IntNode head the head of the linked list to be reverse
     * @postCondition   A linked list in the reverse order of the given linked list
     */
    public static IntNode reverse(IntNode head){
        IntNode previous = null;
        IntNode next = null;
        IntNode current = head;
        while(current != null){
            next = current.getLink();
            current.setLink(previous);
            previous = current;
            current = next;
        }
        head = previous;
        return head;
    }

    /**
     * An implementation of the insertion sort algorithm that will sort any given linked list
     * @preCondition    n/a
     * @param           IntNode head the head of the list you wish to be sorted
     * @postCondition   A linked list that has been sorted in ascending order
     */
    public static IntNode insertionSort(IntNode head){
        if (head == null || head.getLink() == null)             //Deals with an empty or 1 element list
			return head;

		IntNode newHead = new IntNode(head.getData(),null);     //Instanciating the head for your sorted list
		IntNode cursor = head.getLink();                        //Instanciating the cursor to loop through the unsorted list

		// loop through each element in the list
		while (cursor != null) {                               //Will loop until it reaches the end of the unsorted list
			// insert this element to the new list

			IntNode innerCursor = newHead;                     //Instanciating a curser to loop through the sortedList
			IntNode next = cursor.getLink();                   //Saves the next cursor position to iterate through later

			if (cursor.getData() <= newHead.getData()) {       //Deals with a situation where you can simply insert the new
				IntNode oldHead = newHead;                     //element at the beggining of the sortedList
				newHead = cursor;
				newHead.setLink(oldHead);
			} else {
				while (innerCursor.getLink() != null) {        //Will loop until it reaches the end of the sortedList
					if (cursor.getData() > innerCursor.getData() && cursor.getData() <= innerCursor.getLink().getData()) {
						IntNode oldNext = innerCursor.getLink();
						innerCursor.setLink(cursor);
						cursor.setLink(oldNext);
					}
					innerCursor = innerCursor.getLink();
				}
				if (innerCursor.getLink() == null && cursor.getData() > innerCursor.getData()) {    //If the element is the biggest
					innerCursor.setLink(cursor);                                                   //one currently in the list
					cursor.setLink(null);
				}
			}
			cursor = next;                               //Moves on to the next element in the unsorted list
		}
		return newHead;                                  //Returning the sortedList
    }

    /**
     * A static method that will fill any given linked list with "numOfElements" number of
     * elements within the range of (1-1000)
     *
     * @preCondition    numOfElements must be >= 1
     * @param           IntNode head          The head of an empty linked list to fill
     * @param           int     numOfElements the number of elements you wish to add to the list
     */
    public static void fillList(IntNode head,int numOfElements){
        for(int i = 0; i < numOfElements-1; i++)
            head.addNodeAfter((int)((Math.random()*1000)+1));
    }
}
