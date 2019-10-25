import java.util.*;

public class Maze {
    char[][] mazeWithWalls;
    int h;
    int w;

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
                this.removeWall(cur / w, cur % w, nextDir);
                boxes[cur / w][cur % w][nextDir] = 1;
                visited.add(ni * w + nj);
            }
        }
    }

    private void removeWall(int i, int j, int dir) {
        if(i == 0 && dir == 0 || j == 0 && dir == 3) return;
        //System.out.println(dir);
        switch(dir) {
            case 0:
                this.mazeWithWalls[i * 3][j * 3 + 1] = ' ';
                this.mazeWithWalls[i * 3][j * 3 + 2] = ' ';
                break;
            case 1:
                this.mazeWithWalls[i * 3 + 1][j * 3 + 3] = ' ';
                this.mazeWithWalls[i * 3 + 2][j * 3 + 3] = ' ';
                break;
            case 2:
                this.mazeWithWalls[i * 3 + 3][j * 3 + 1] = ' ';
                this.mazeWithWalls[i * 3 + 3][j * 3 + 2] = ' ';
                break;
            case 3:
                this.mazeWithWalls[i * 3 + 1][j * 3] = ' ';
                this.mazeWithWalls[i * 3 + 2][j * 3] = ' ';
                break;
        }
    }

    public static void main(String[] args) {
        Maze m = new Maze(15, 15);
        m.createMaze();
        for(int i = 0; i < m.mazeWithWalls.length; i++) {
            for(int j = 0; j < m.mazeWithWalls[0].length; j++) {
                System.out.print(m.mazeWithWalls[i][j] + " ");
            }
            System.out.println("");
        }

    }
}
