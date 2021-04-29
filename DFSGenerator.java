import java.util.*;

public class DFSGenerator{
    Maze maze;
    int h,w;
    int[][] dirs;

    public DFSGenerator(Maze maze) {
        this.maze = maze;
        this.h = maze.getH();
        this.w = maze.getW();
        this.dirs = maze.dirs;
    }

    public void create() {
        int[][][] boxes = new int[h][w][4]; // 4 means 4 walls, top, right, bottom, left; 0 is intact; 1 is broken
        Stack<Integer> stack = new Stack<>();  // c = i * w + j; i = c / w; j = c % w
        Set<Integer> visited = new HashSet<>();
        stack.push(0);
        visited.add(0);
        Random r = new Random();
        int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        while(!stack.isEmpty()) {
            int cur = stack.peek();
            //System.out.println("height is " + (cur / w) + " width is " + (cur % w));
            List<Integer> avails = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int ti = cur / w + dir[i][0];
                int tj = cur % w + dir[i][1];
                if(ti >= 0 && tj >= 0 && ti < h && tj < w && !visited.contains(ti * w + tj)) avails.add(i);
            }
            if(avails.size() == 0) {
                stack.pop();
            }
            else {
                int nextIndex = r.nextInt(avails.size());
                int nextDir = avails.get(nextIndex);
                int ni = cur / w + dir[nextDir][0];
                int nj = cur % w + dir[nextDir][1];
                stack.push(ni * w + nj);
                maze.removeWall(cur / w, cur % w, nextDir);
                boxes[cur / w][cur % w][nextDir] = 1;
                visited.add(ni * w + nj);
            }
        }
    }
}
