import java.util.*;

class KSGenerator{
    Maze maze;
    public KSGenerator(Maze maze) {
        this.maze = maze;
    }

    public void create() {
        int h = maze.getH();
        int w = maze.getW();
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
            int ni = pick / w + maze.dirs[rd][0];
            int nj = pick % w + maze.dirs[rd][1];
            if(ni >= 0 && nj >= 0 && ni < h && nj < w && find(ni * w + nj, cells) != find(pick, cells)) {
                maze.removeWall(pick / w, pick % w, rd);
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
            
            
        




}
