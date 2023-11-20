public class Main {
    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator(30); // Example size and positions
        generator.generateMaze();
//        generator.printSymbolicMaze();
//        System.out.println();
//        generator.printBreakableWallMaze();
//        System.out.println();
        generator.addPaths(30);
        generator.printSymbolicMaze();
    }
}
