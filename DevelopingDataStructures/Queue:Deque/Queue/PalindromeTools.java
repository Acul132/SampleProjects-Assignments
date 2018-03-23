/**
 * Class of static methods to determine if a line contains a word-by-word palindrome
 * Created by Joshua Renelli(0344117)
 */

public class PalindromeTools{

    /**
     * Returns an array containing each word from the original entered line
     * without any unwanted characters
     * @preCondition    n/a
     * @param         String line the line of words to be processed
     * @postCondition   returns the words array
     */
    public static String[] processLine(String line){
        line = line.replaceAll("[\\.,!?]","");
        String[] words = line.split(" ");
        return words;
    }

    /**
     * Boolean method which will determine if the entered line is a word-by-word
     * palindrome or not
     * @preCondition    n/a
     * @param         String line The line to check
     * @postCondition   Returns true if it is a word-by-word palindrome
     * and false otherwise 
     */
    public static boolean isPalindrome(String line){
        ArrayQueue queue = new ArrayQueue();
        LinkedStack stack = new LinkedStack();
        String[] words = processLine(line);


        for(int i = 0; i < words.length; i++)
            queue.enqueue(words[i]);
        for(int i = 0; i < words.length; i++)
            stack.push(words[i]);
        for(int i = 0; i < words.length; i++)
            if(!(queue.dequeue().equalsIgnoreCase(stack.pop())))
                return false;
        return true;
    }
}
