public class Minimax {

    public Vertex root;
    public String message;
    
    public Minimax() {
        this.message = "Tic Tac Toe";
        this.root = new Vertex(new int[9]);
        growTree(this.root, 1, 0);
    }
    
    private int growTree(Vertex root, int turn, int utility) {
        if ((utility = root.terminal()) != Vertex.CONT) {
            return utility;
        }
        for (int i = 0; i< root.board.length; i++) {
            if (root.board[i] == 0) {
                int[] curr = root.boardCopy();
                curr[i] = turn;
                root.children.add(new Vertex(curr));
            }
        }
        for (int i = 0; i < root.children.size(); i++) {
            Vertex curr = root.children.get(i);
            root.children.get(i).utility = growTree(curr, turn * -1, utility);
        }
        return root.minimax(turn);
    }

    public void play(int place) {
        // implement me
    }

    private Vertex min() {
        // implement me
    }
    
    private Vertex newRoot(int place) {
        // implement me
    }

    private String evalMessage() {
        if (this.root.children.size() > 0)
            return this.message;
        else if (this.root.terminal() == 0)
            return "Draw! (right click to reset).";
        else
            return "Peasant! (right click to reset).";
    }
}