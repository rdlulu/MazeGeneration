import java.util.*;

class Maze {
    char[][] mazeWithWalls;
    int h;
    int w;
    final static int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

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
    
    public void createMazeKS() {
        int[] cells = new int[h * w];
        Random r = new Random();
        int cnt = h * w;
        for(int i = 0; i < cells.length; i++) {
            cells[i] = i;
            //System.out.print((cells[i]) + ", ");
        }
        while(cnt > 1) {
            int pick = r.nextInt(h * w);
            int rd = r.nextInt(4);
            int ni = pick / w + dir[rd][0];
            int nj = pick % w + dir[rd][1];
            if(ni >= 0 && nj >= 0 && ni < h && nj < w && find(ni * w + nj, cells) != find(pick, cells)) {
                removeWall(pick / w, pick % w, rd);
                union(pick, ni * w + nj, cells);
                cnt--;
            }
        }
    }
    private int find(int cell, int[] cells) {
        int next = cells[cell];
        int cur = cell;
        while(cells[next] != next) {
            cells[cur] = cells[next];
            cur = next;
            next = cells[next];
        }
        cells[cur] = next;
        return next;
    }
    private void union(int a, int b, int[] cells) {
        int agroup = find(a, cells);
        int bgroup = find(b, cells);
        for(int i = 0; i < cells.length; i++) {
            if(cells[i] == bgroup) {
                cells[i] = agroup;
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
        Maze m = new Maze(10, 15);
        m.createMazeKS();
        for(int i = 0; i < m.mazeWithWalls.length; i++) {
            for(int j = 0; j < m.mazeWithWalls[0].length; j++) {
                System.out.print(m.mazeWithWalls[i][j] + " ");
            }
            System.out.println("");
        }

    }
}
