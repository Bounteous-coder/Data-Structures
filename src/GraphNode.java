// Numan Salahuddin - 251264939
public class GraphNode {

	private int name;
	private boolean mark;

	public GraphNode(int name) {
		this.name = name;
		this.mark = false;
	}

	public void mark(boolean mark) {
		this.mark = mark;
	}

	public boolean isMarked() {
		return mark;
	}

	public int getName() {
		return name;
	}
}
