import java.util.*;

public class Maze{
    char[][] mazeWithWalls;
    int h;
    int w;
    final static int[][] dirs = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    public Maze(int height, int width) {

        this.mazeWithWalls = new char[height * 3 + 1][width * 3 + 1];
        this.h = height;
        this.w = width;
        //the wall is one character thick
        for(int i = 0; i <= height * 3; i++) {
            for(int j = 0; j <= width * 3; j++) {
                if(i % 3 == 0 || j % 3 == 0) this.mazeWithWalls[i][j] = 'X';
                else this.mazeWithWalls[i][j] = ' ';
            }
        }
    }

    public void createMaze() {
        Stack<Integer> stack = new Stack<>();  // c = i * w + j; i = c / w; j = c % w
        Set<Integer> visited = new HashSet<>();
        stack.push(0);
        visited.add(0);
        Random r = new Random();
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
                this.removeWall(cur / w, cur % w, nextDir);
                visited.add(ni * w + nj);
            }
        }
    }

    public void createMazeKS() {
        int[] sets = new int[h * w];
        for(int i = 0; i < sets.length; i++) {
            sets[i] = i;
        }
        Random r = new Random();
        int cnt = h * w;
        while(cnt > 1)  {
            int pick = r.nextInt(h * w);
            int dir = r.nextInt(4);
            int ni = pick / w + dirs[dir][0];
            int nj = pick % w + dirs[dir][1];
            if(ni < h && nj < w && ni >= 0 && nj >= 0 && find(pick, sets) != find(ni * w + nj, sets)) {
                union(pick, ni * w + nj, sets);
                removeWall(pick / w, pick % w, dir);
                cnt--;
            }
        }
    }

    private int find(int cell, int[] sets) {
        int cur = cell;
        int father = sets[cell];
        int grandfather = sets[father];
        while(grandfather != father) {
            cur = father;
            father = grandfather;
            grandfather = sets[grandfather];
        }
        sets[cur] = grandfather;
        return grandfather;
    }

    private void union(int a, int b, int[] sets) {
        int ag = find(a, sets);
        int bg = find(b, sets);
        if(ag != bg)) sets[ag] = sets[bg];
    }

    private void removeWall(int i, int j, int dir) {
        if(i == 0 && dir == 0 || j == 0 && dir == 3) return;
        //System.out.println(dir);
        int id = dirs[dir][0];
        int jd = dirs[dir][1];
        int[][] points = new int[][]{{0, 1},{0, 2}, {1, 3}, {2,3}, {3,2}, {3,1}, {2,0}, {1, 0}};
        this.mazeWithWalls[i * 3 + points[dir * 2][0]][j * 3 + points[dir * 2][1]] = ' ';
        this.mazeWithWalls[i * 3 + points[dir * 2 + 1][0]][j * 3 + points[dir * 2 + 1][1]] = ' ';
    }

    public static void main(String[] args) {
        Maze m = new Maze(10, 10);
        m.createMazeKS();
        for(int i = 0; i < m.mazeWithWalls.length; i++) {
            for(int j = 0; j < m.mazeWithWalls[0].length; j++) {
                System.out.print(m.mazeWithWalls[i][j] + " ");
            }
            System.out.println("");
        }

    }
}
