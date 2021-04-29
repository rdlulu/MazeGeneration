import java.util.*;

public class UnionGenerator{
    Maze maze;
    public UnionGenerator(Maze maze) {
        this.maze = maze;
    }


    public void create() {
        Stack<Integer> stack = new Stack<>();  // c = i * w + j; i = c / w; j = c % w
        Set<Integer> visited = new HashSet<>();
        stack.push(0);
        visited.add(0);
        Random r = new Random();
        int w = maze.getW();
        int h = maze.getH();
        int[][] dirs = maze.dirs;
        while(!stack.isEmpty()) {
            int cur = stack.peek();
            //System.out.println("height is " + (cur / w) + " width is " + (cur % w));
            List<Integer> avails = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int ti = cur / w + dirs[i][0];
                int tj = cur % w + dirs[i][1];
                if(ti >= 0 && tj >= 0 && ti < h && tj < w && !visited.contains(ti * w + tj)) avails.add(i);
            }
            if(avails.size() == 0) {
                stack.pop();
            }
            else {
                int nextIndex = r.nextInt(avails.size());
                int nextDir = avails.get(nextIndex);
                int ni = cur / w + dirs[nextDir][0];
                int nj = cur % w + dirs[nextDir][1];
                stack.push(ni * w + nj);
                maze.removeWall(cur / w, cur % w, nextDir);
                visited.add(ni * w + nj);
            }
        }
    }

}
