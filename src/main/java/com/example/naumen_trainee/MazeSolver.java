package com.example.naumen_trainee;

import java.util.*;

public class MazeSolver {

    public static LinkedList<Point> FindPath (String lab){

        var labyrinth = stringParser(lab);
        var map = new State[labyrinth[0].length][labyrinth.length];

        // Заполняем карту Enumами, и получаем где находится начало (можно сразу получить и конец)
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

            // проверяем, что точка находится в пределах лабиринта
            var currentList = queue.poll();

            // возможно здесь кроется ошибка
            var neighbourPoints = GetNeighbourPoints(currentList.element());
            for (Point neighbourPoint:neighbourPoints
                 ) {
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


                if (neighbourPoint.isEqual(end))
                    return neighbourList;
                visited.add(neighbourPoint);
                queue.add(neighbourList);
            }
        }
        return null;
    }

    // Возвращает список точек
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

    // Попробовать сделать поиск в глубину:
    // смотреть путь, если доходит до выхода, сохранять путь.
    // Если не доходит, помечать тупиковым.
    // Пути сравнить по длине (количеству элементов [точек]) и выбрать кратчайший
    // Или прекращать поиск пути как только находится один(?)

//****************************************************************************************
    static void Solver(String lab) {


        var labyrinth = stringParser(lab);
        var map = new State[labyrinth[0].length][labyrinth.length];

        // Fill Map with Enums
        var start = new Point(0,0);
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
                if (labyrinth[x][y] == 'X')
                    map[x][y] = State.End;
            }

        var queue = new ArrayDeque<Point>();
        queue.add(start);

        while (queue.size() != 0) {
            var point = queue.poll();
            if (point.X < 0 || point.X >= map[0].length || point.Y < 0 || point.Y >= map.length) {
                continue;
            }
            if (map[point.X][point.Y] == State.End) break;
            if (map[point.X][point.Y] !=State.Road) continue;
            map[point.X][point.Y] = State.Visited;
//            if (map[point.X + 1][point.Y + 1] == State.End
//                    && map[point.X + 1][point.Y - 1] == State.End
//                    && map[point.X - 1][point.Y + 1] == State.End
//                    && map[point.X - 1][point.Y - 1] == State.End)
//                break;


            // смотрим соседние клетки, если есть свободные, добавляем в очередь
            for (var dy = -1; dy <= 1; dy++)
                for (var dx = -1; dx <= 1; dx++) {
                    var pt = new Point(0,0);
                    if (dx != 0 && dy != 0) continue;
                    else {
                        pt.X = point.X + dx;
                        pt.Y = point.Y + dy;
                        queue.add(pt);
                    }

                }
        }
        map[start.X][start.Y] = State.Start;
        System.out.print(map);
    }

    public static char[][] stringParser (String maze){
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
