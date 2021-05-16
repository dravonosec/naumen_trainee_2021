package com.example.naumen_trainee;

import java.util.*;

public class MazeSolver {

    public static LinkedList<Point> FindPath (char[][] labyrinth){

        var map = new State[labyrinth[0].length][labyrinth.length];

        // Заполняем карту Enum'ами, и сразу получаем начало и конец в лабиринте
        var start = new Point(0,0);
        var end = new Point(0,0);
        for (int x = 0; x < map.length; x++)
            for (int y = 0; y < map[0].length; y++) {
                if (labyrinth[x][y] == '.')
                    map[x][y] = State.Road;
                if (labyrinth[x][y] == '#')
                    map[x][y] = State.Wall;
                if (labyrinth[x][y] == '@'){
                    map[x][y] = State.Road;
                    start.X = x;
                    start.Y = y;
                }
                if (labyrinth[x][y] == 'X') {
                    map[x][y] = State.Road;
                    end.X = x;
                    end.Y = y;
                }
            }

        var queue = new ArrayDeque<LinkedList<Point>>();
        var visited = new HashSet<Point>();
        visited.add(start);

        // добавляем в очередь связный список с началом в точке start
        var startLinkedList =  new LinkedList<Point>();
        startLinkedList.add(start);
        queue.add(startLinkedList);

        while (queue.size() != 0){


            var currentList = queue.poll();


            var neighbourPoints = GetNeighbourPoints(currentList.element());
            for (Point neighbourPoint:neighbourPoints
                 ) {
                // проверяем, что точка находится в пределах лабиринта
                if (visited.contains(neighbourPoint)) continue;
                if (neighbourPoint.X < 0 ||
                        neighbourPoint.X >= map[0].length ||
                        neighbourPoint.Y <0 ||
                        neighbourPoint.Y >= map.length
                )
                    continue;
                if (map[neighbourPoint.X][neighbourPoint.Y] == State.Wall) continue;

                var neighbourList = new LinkedList<Point>();
                neighbourList.add(neighbourPoint);
                neighbourList.addAll(currentList);


                if (neighbourPoint.equals(end))
                    return neighbourList;
                visited.add(neighbourPoint);
                queue.add(neighbourList);
            }
        }
        return null;
    }

    public static String GetSolvedMaze (LinkedList<Point> path, char[][] labyrinth){

        for (Point point: path
             ) {
            var x = point.X;
            var y = point.Y;
            labyrinth[x][y] = '+';
        }
        labyrinth[path.getFirst().X][path.getFirst().Y] = 'X';
        labyrinth[path.getLast().X][path.getLast().Y] = '@';

        String solvedMaze = new String();

        for (int x = 0; x < labyrinth.length; x++)
            for (int y = 0; y < labyrinth[0].length; y++)
            {
                solvedMaze += labyrinth[x][y];
                if (y == labyrinth[0].length - 1)
                    solvedMaze += "\n";
            }

        return solvedMaze;
    }

    public static ArrayList<Point> GetNeighbourPoints (Point currentPoint){
        var pointList = new ArrayList<Point>();
        for (var dy = -1; dy <= 1; dy++)
            for (var dx = -1; dx <= 1; dx++) {
                var pt = new Point(0,0);
                if (dx != 0 && dy != 0) continue;
                else {
                    pt.X = currentPoint.X + dx;
                    pt.Y = currentPoint.Y + dy;
                    pointList.add(pt);
                }
            }
        pointList.remove(currentPoint);
        return pointList;
    }

    public static char[][] MazeParser(String maze){
        String[] splitMaze = maze.split("\n");
        char[][] mazeChar = new char[splitMaze[0].length()][splitMaze.length];
        for (int i = 0; i< splitMaze[0].length();i++ ){
            for (int j = 0; j < splitMaze.length;j++)
            {
                mazeChar[i][j] = splitMaze[i].charAt(j);
            }
        }
        return mazeChar;
    }

}
