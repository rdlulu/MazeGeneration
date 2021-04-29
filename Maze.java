public class Maze {
    char[][] mazeWithWalls;
    int h;
    int w;
    final static int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public Maze(int height, int width) {

        this.mazeWithWalls = new char[height * 3 + 1][width * 3 + 1];
        this.h = height;
        this.w = width;
        //the wall is one character thick
        for (int i = 0; i <= height * 3; i++) {
            for (int j = 0; j <= width * 3; j++) {
                if (i % 3 == 0 || j % 3 == 0) this.mazeWithWalls[i][j] = 'X';
                else this.mazeWithWalls[i][j] = ' ';
            }
        }
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public void removeWall(int i, int j, int dir) {
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
}
