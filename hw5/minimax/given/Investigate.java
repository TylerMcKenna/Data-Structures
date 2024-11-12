public class Investigate {
    public static void main(String[] args) {
        Minimax model = new Minimax(3);
        System.out.println(DFS(model.root,1)); // max
        System.out.println(DFS(model.root,-1)); // min
        System.out.println(DFS(model.root,0)); // draw
    }   
    
    // Depth first search
    public static int DFS(Vertex root, int turn) {
        if (root.children.size() <= 0) 
            return root.utility == turn ? 1 : 0;
        int ret = 0;
        for (Vertex child : root.children)
            ret += DFS(child, turn);
        return ret;
    }
}