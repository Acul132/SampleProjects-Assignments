import java.util.*;

//Graph class created by Joshua Renelli(0344117)
public class Graph<E>{

    private class Edge{
        private int target;
        private int weight;
        public Edge(int target){
            this.target = target;
            weight = 1;
        }
        public Edge(int target, int weight){
            this.target = target;
            this.weight = weight;
        }
    }

    private boolean isDiagraph, isWeighted;
    private LinkedList<Edge>[] edges;
    private E[] vertices;

    /**
     * Argumented constructor to create a graph class
     * @preCondition    numOfV > 0
     * @param         int     numOfV     number of vertices
     * @param         boolean isDiagraph boolean to determine if it's directed
     * @param         boolean isWeighted boolean to determine if it's weighted
     * @postCondition   new graph object
     */
    public Graph(int numOfV, boolean isDiagraph, boolean isWeighted){
        edges =  new LinkedList[numOfV*numOfV];
        vertices =  (E[]) new Object[numOfV];
        this.isDiagraph = isDiagraph;
        this.isWeighted = isWeighted;
    }

    /**
     * Sets the given vertex to whichever label you give it
     * @preCondition    vertex is a valid vertex
     * @param        int vertex vertex index
     * @param        E   label  desired label for vertex
     * @postCondition   new label for given vertex
     */
    public void setVertex(int vertex, E label){
        vertices[vertex] = label;
    }

    /**
     * Add's an edge from the source vertex to the target vertex with a weight
     * @preCondition    source and target vertex are legal vertices
     * @param        int source source vertex index
     * @param        int target target vertex index
     * @param        int weight weight of the edges
     * @postCondition   new weighted edge from source to target
     */
    public void addEdge(int source, int target, int weight){
        if(edges[source] == null)edges[source] = new LinkedList<Edge>();

        edges[source].addFirst(new Edge(target,weight));
    }

    /**
     * Add's an edge from the source vertex to the target
     * @preCondition    source and target vertex are legal vertices
     * @param        int source source vertex index
     * @param        int target target vertex index
     * @postCondition   new edge from source to target
     */
    public void addEdge(int source, int target){
        if(edges[source] == null)edges[source] = new LinkedList<Edge>();

        edges[source].addFirst(new Edge(target));
    }

    /**
     * Removed the given edge
     * @preCondition    source and target are legal vertex indices
     * @param        int source index of source vertex
     * @param        int target index of target vertex
     * @postCondition   the graph will have this edge removed
     */
    public void removeEdge(int source, int target){
        ListIterator<Edge> iter = edges[source].listIterator();
        while(iter.hasNext())
            if(iter.next().target == target){
                iter.remove();
                break;
            }
    }

    /**
     * Determines if there is an edge between the two vertices
     * @preCondition    both vertices are legal vertex indices
     * @param         int vertex1 index of source vertex
     * @param         int vertex2 index of target vertex
     * @postCondition   Returns true if an edge exists and false otherwise
     */
    public boolean isConnected(int vertex1, int vertex2){
        ListIterator<Edge> iter = edges[vertex1].listIterator();
        while(iter.hasNext())
            if(iter.next().target == vertex2)
                return true;

        iter = edges[vertex2].listIterator();
        while(iter.hasNext())
            if(iter.next().target == vertex1)
                return true;

        return false;
    }

    /**
     * Function that returns an array of edges connected to the given vertex
     * @preCondition    vertex is a legal vertex index
     * @param         int vertex index of vertex
     * @postCondition   Returns an integer array of indices to target edges
     */
    public int[] neighbors(int vertex){
        if(edges[vertex] == null)return new int[0];
        int[] n = new int[edges[vertex].size()];
        ListIterator<Edge> iter = edges[vertex].listIterator();

        for(int i = 0; i < n.length; i++)
            n[i] = iter.next().target;

        return n;
    }

    /**
     * Recursive function that will perform a depth first search on a given
     * vertex, will print to std output
     *
     * @preCondition    Start is a valid vertex index
     * @param        int start index of the source vertex
     * @postCondition   n/a
     */
    public void printDFS(int start){
        boolean[] marked = new boolean[vertices.length];
        printDFSRecurse(start,marked);
    }

    //Utility function for the printDFS function
    private void printDFSRecurse(int start, boolean[] marked){
        int[] connections = neighbors(start);

        marked[start] = true;
        System.out.print(vertices[start] + " ");

        for(int i = 0; i < connections.length; i++){
            if(!marked[connections[i]])
                printDFSRecurse(connections[i],marked);
        }
    }

    /**
     * Function that will perform a breadth first search on a given
     * vertex, will print to std output
     *
     * @preCondition    start is a legal vertex index
     * @param        int start index of source vertex
     * @postCondition   n/a
     */
    public void printBFS(int start) {
        boolean[] marked = new boolean[vertices.length];
        Queue<Integer> queue = new LinkedList<Integer>();

        marked[start] = true;
        System.out.print(vertices[start] + " ");
        queue.add(start);

        while(!queue.isEmpty()){
            int next = queue.poll();
            int[] connections = neighbors(next);

            for(int i = 0; i < connections.length; i++){
                if(!marked[connections[i]]){
                    marked[connections[i]] = true;
                    System.out.print(vertices[connections[i]] + " ");
                    queue.add(connections[i]);
                }
            }
        }
    }

    /**
     * Recursive function that will determine if the graph contains a cycle starting from any
     * vertex in the Graph
     *
     * @preCondition    n/a
     * @postCondition   Returns true if a cycle is found and false otherwise
     */
    public boolean hasCycle(){
        boolean[] marked = new boolean[vertices.length];

        for(int i = 0; i < marked.length; i++)
            if(!marked[i])
                if(hasCycle(i, -1, marked))
                    return true;

        return false;
    }

    //Utility function that the hasCycle function utilizes for the recursive part
    private boolean hasCycle(int vertex, int parent, boolean[] marked){
        marked[vertex] = true;
        int[] connections = neighbors(vertex);

        for(int i = 0; i < connections.length; i++){
            if(!marked[i]){
                if(hasCycle(i, vertex, marked))
                    return true;
            }
            else if(i != parent)
                return true;
        }
        return false;
    }

    /**
     * toString function for the graph class
     * @preCondition    n/a
     * @postCondition   Returns a string representation of this graph
     */
    public String toString(){
        String output = "";
        for(int i = 0; i < vertices.length; i++){
            output += vertices[i] + " edges: ";
            if(edges[i] != null){
                ListIterator<Edge> iter = edges[i].listIterator();
                while(iter.hasNext()){
                    Edge current = iter.next();
                    if(isWeighted)
                        output += "(" + vertices[i] + "," + vertices[current.target] + "," + current.weight + ")";
                    else
                        output += "(" + vertices[i] + "," + vertices[current.target] + ")";
                }
            }
            output += "\n";
        }
        return output;
    }

    public int size(){
        return vertices.length;
    }
}
