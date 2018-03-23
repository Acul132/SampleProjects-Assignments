import java.util.*;
import java.io.*;

public class Heap{
    private String[] words;
    private int size;

    public Heap(int num){
        words = new String[2];
        size = 0;
    }

    private int getParent(int n){
        return (n/2);
    }

    private int getLeft(int n){
        return (2*n);
    }

    private int getRight(int n){
        return (2*n)+1;
    }

    public boolean isFull(){
        return size == words.length-1;
    }

    public void insert(String word){
        word = word.replaceAll("[]\\.,!?]","");
        if(isFull())
            ensureCapacity();

        size++;
        words[size] = word;
        int cursor = size;

        while(cursor != 1 && (sHeap[cursor].compareTo(sHeap[parent(cursor)]) < 0)) {
            swap(cursor,parent(cursor));
            cursor = parent(cursor);
        }
    }

    private void ensureCap() {
        String[] tmp = words;
        words = new String[words.length * 2];
        System.arraycopy(tmp, 1, words, 1, size);
    }


    public String[] getSortedHeap() {
        int tmpSize = size;
        String[] tmp = words;
        words = new String[tmp.length];
        System.arraycopy(tmp, 1, words, 1, size);
        while(size != 1) {
            swap(1,size);
            size--;
    		heapifyDown(1);
        }
        //Copy Valid Values from the Sorted Heap and reverse
        String[] tmp2 = new String[tmpSize];
        for(int i = 0; i < tmpSize; i++) {
            tmp2[i] = words[tmpSize-i];
        }

        words = tmp;
        size = tmpSize;
        System.out.println(Arrays.toString(words));

        return tmp2;
    }


    private void heapifyDown(int n){
        String tmp = words[n];

        for(int child; left(n) <= size; n = child) {
           child = left(n);

           if(words[left(n)].compareTo(words[right(n)]) > 0 && child != size)
               child = right(n);

           if(tmp.compareTo(words[child]) > 0)
               words[n] = words[child];

           else{
               break;
           }
       }
       words[n] = tmp;
     }

     public String toString() {
        String output = "";
        for(int i = 1; i <= size; i++)
            output += words[i] + " ";
        return output;
    }

}
