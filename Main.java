public class Main {
    public static void main(String[] args) {
        Maze m = new Maze(10, 15);
        KSGenerator gen = new KSGenerator(m);
        gen.create();
        for(int i = 0; i < m.mazeWithWalls.length; i++) {
            for(int j = 0; j < m.mazeWithWalls[0].length; j++) {
                System.out.print(m.mazeWithWalls[i][j] + " ");
            }
            System.out.println("");
        }

    }
}
