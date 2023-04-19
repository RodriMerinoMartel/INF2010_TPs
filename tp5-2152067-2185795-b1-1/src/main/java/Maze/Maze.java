package Maze;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {
    static private final int POSSIBILITIES = 3;

    /** TODO
     * Returns the distance of the shortest path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the shortest path within the maze, null if not solvable
     */
    public static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze) {
        ArrayList<ArrayList<Integer>> distances = new ArrayList<>(new ArrayList<>());
        ArrayList<Integer> dists = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        if (mazeIsValid(maze)) {
            int entrancePosition = 0, exitPosition = 0;
            for (int i = 0; i < maze.size() ; i++) {
                if (maze.get(i).get(0) == Tile.Exit) {
                    entrancePosition = i;
                    break;
                }
            }

            int lastRow = maze.size() - 1;
            int lastColumn = maze.get(0).size() - 1;

            for (int i = 0; i < maze.size() ; i++) {
                if (maze.get(i).get(lastColumn) == Tile.Exit) {
                    exitPosition = i;
                    break;
                }
            }

            for (int i = 0; i <= lastColumn ; i++) {
                dists.add(Integer.MAX_VALUE);
            }

            for (int i = 0; i <= lastRow ; i++) {
                ArrayList<Integer> distsToAdd = new ArrayList<Integer>(dists);
                distances.add(distsToAdd);
            }
            distances.get(entrancePosition).set(0,0);
            queue.offer(new int[]{entrancePosition, 0, 0});

            int distance = 0;

            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int row = curr[0];
                int col = curr[1];
                int currDist = curr[2];
                distance = currDist;

                if (row == exitPosition && col == lastColumn) {
                    break;
                }

                for (int[] dir : new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    if (newRow >= 0 && newRow <= lastRow && newCol >= 0 && newCol <= lastColumn
                            && maze.get(newRow).get(newCol) != Tile.Wall) {
                        int newDist = currDist + 1;
                        if (newDist < distances.get(newRow).get(newCol)) {
                            distances.get(newRow).set(newCol, newDist);
                            queue.offer(new int[]{newRow, newCol, newDist});
                        }
                    }
                }
            }
            return distance;
        }
        return null;
    }

    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }

    /**
     * Returns if the maze is valid
     * @param maze 2D table representing the maze
     * @return true if the maze is valid, false if not.
     *
    */
    private static Boolean mazeIsValid(ArrayList<ArrayList<Tile>> maze) {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (ArrayList<Tile> row : maze) {
            for (Tile tile : row) {
                if (tiles.size() <  POSSIBILITIES) {
                    if (!tiles.contains(tile)) {
                        tiles.add(tile);
                    }
                }
            }
        }
        return tiles.size() == POSSIBILITIES;
    }
}
