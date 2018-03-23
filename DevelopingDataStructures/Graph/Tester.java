import java.util.Scanner;
import java.io.*;

public class Tester {
    public static void main(String[] args) throws IOException{

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the file name to parse into graph: ");

        String inFile = scan.next();
        File file = new File(inFile);
        Scanner input = new Scanner(file);

        int numVertices = input.nextInt();
        String directed = input.next();
        String weighted = input.next();
        System.out.println(numVertices + "        // number of vertices");
        System.out.println(directed);
        System.out.println(weighted);

        boolean isDirected, isWeighted;

        if(weighted.equals("weighted"))
            isWeighted = true;
        else
            isWeighted = false;

        if(directed.equals("directed"))
            isDirected = true;
        else
            isDirected = false;

        Graph graph = new Graph(numVertices,isDirected,isWeighted);

        int vertices = 0;
        input.nextLine();
        while(input.hasNextLine()) {
            String line = input.nextLine();

            Scanner scanLine = new Scanner(line);
            graph.setVertex(vertices,scanLine.next());
            int edges = 0;      //Edge counter
            while(scanLine.hasNext()) {
                int weight = scanLine.nextInt();
                if(weight > 0) {
                    if(isWeighted)      //Call the weighted constructer if its a weighted graph
                        graph.addEdge(vertices,edges,weight);
                    else
                        graph.addEdge(vertices,edges);
                }
                edges++;
            }
            scanLine.close();
            vertices++;
        }

        input.close();
        System.out.print(graph); //Display the graph
    }
}
