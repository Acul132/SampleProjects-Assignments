public class IntNode{

    private int data;
    private IntNode link;

    public IntNode(int data, IntNode link){
        this.data = data;
        this.link = link;
    }

    public IntNode getLink(){
        return link;
    }
    public int getData(){
        return data;
    }

    public void setLink(IntNode link){
        this.link = link;
    }
    public void setData(int data){
        this.data = data;
    }

    public void addNodeAfter(int newData){
        link = new IntNode(newData,link);
    }

    public void removeNodeAfter(){
        link = link.link;
    }

    public String toString(){
        String output = "Linked List: ";
        IntNode cursor = this;
        while(cursor != null){
            output += cursor.data + " -> ";
            cursor = cursor.link;
        }
        output = output.substring(0,output.length()-3);
        return output;
    }

    public static int listLength(IntNode head){
        int length = 0;
        IntNode cursor = head;
        while(cursor != null){
            length++;
            cursor = cursor.getLink();
        }
        return length;
    }

    public static void main(String[] args){
        IntNode head = new IntNode(2,null);
        head = new IntNode(4,head);
        head.addNodeAfter(3);
        System.out.println(listLength(head));
        System.out.println(head);
        head.removeNodeAfter();
        System.out.println(head);
    }
}
