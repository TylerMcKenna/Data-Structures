import java.util.Arrays;

public class Minimax {

  public Vertex root;
  public String message;

  public Minimax(int size) {
    this.message = "Tic Tac Toe";
    this.root = new Vertex(size);
    growTree(this.root, 1, 0);
  }

  private int growTree(Vertex root, int turn, int utility) {
    if ((utility = root.terminal()) != Vertex.CONT) {
      return utility;
    }
    for (int i = 0; i < root.board.length; i++) {
      root.grow(i, turn);
    }
    for (Vertex child : root.children) {
      child.utility = (byte)growTree(child, -turn, utility);
    }
    return root.minimax(turn);
  }

  public void play(int place) {
    byte[] move = new byte[this.root.board.length];
    System.arraycopy(this.root.board, 0, move, 0, move.length);
    move[place] = 1;
    Vertex newRoot = null;
    for (Vertex child : root.children) {
      newRoot = Arrays.equals(child.board, move) ? child : newRoot;
    }
    if (newRoot == null) {
      return;
    }

    root = newRoot.min();
    this.message = this.evalMessage();
  }

  public int gameOverState() {
    if (this.root.children.size() > 0)
      return Vertex.CONT;
    return this.root.terminal();
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