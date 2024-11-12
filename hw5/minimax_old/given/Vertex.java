    import java.util.ArrayList;

public class Vertex {
    final static int CONT = 2;

    public int utility = CONT;
    public int[] board;
    public ArrayList<Vertex> children;

    public Vertex(int[] board) {
        this.board = board;
        this.children = new ArrayList<Vertex>();
    }

    public int minimax(int turn) {
        if (turn == 1) {
            this.utility = Integer.MIN_VALUE;
            for (int i = 0; i < this.children.size(); i++)
                if (this.utility < this.children.get(i).utility)
                    this.utility = this.children.get(i).utility;
        } else {
            this.utility = Integer.MAX_VALUE;
            for (int i = 0; i < this.children.size(); i++)
                if (this.utility > this.children.get(i).utility)
                    this.utility = this.children.get(i).utility;
        }
        return this.utility;
    }

    public int terminal() {
        int[] b = this.board;
        if (b[0] != 0 && b[0] == b[1] && b[1] == b[2])
            return b[0];
        if (b[3] != 0 && b[3] == b[4] && b[4] == b[5])
            return b[3];
        if (b[6] != 0 && b[6] == b[7] && b[7] == b[8])
            return b[6];
        if (b[0] != 0 && b[0] == b[3] && b[3] == b[6])
            return b[0];
        if (b[1] != 0 && b[1] == b[4] && b[4] == b[7])
            return b[1];
        if (b[2] != 0 && b[2] == b[5] && b[5] == b[8])
            return b[2];
        if (b[0] != 0 && b[0] == b[4] && b[4] == b[8])
            return b[0];
        if (b[2] != 0 && b[2] == b[4] && b[4] == b[6])
            return b[2];
        if (!this.contains(0, b))
            return 0;
        return CONT;
    }

    public boolean contains(int x, int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == x)
                return true;
        return false;
    }

    public int[] boardCopy() {
        int[] copy = new int[this.board.length];
        System.arraycopy(this.board, 0, copy, 0, copy.length);
        return copy;
    }

    public String toString() {
        String f = " [%2d, %2d, %2d]";
        String ret = String.format(f, board[0], board[1], board[2]);
        ret += "\n" + String.format(f, board[3], board[4], board[5]);
        ret += "\n" + String.format(f, board[6], board[7], board[8]);
        return "[" + ret.substring(1) + "]";
    }
}
