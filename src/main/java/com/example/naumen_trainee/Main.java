package com.example.naumen_trainee;

import java.io.Console;

import static com.example.naumen_trainee.MazeSolver.GetSolvedMaze;
import static com.example.naumen_trainee.MazeSolver.MazeParser;

public class Main {

    public static void main(String[] args){
        String testStr1 = "...@.\n.####\n.....\n####.\n.X...";
        String testStr2 = "....@\n#.###\n.....\n....X\n.....";
        var labyrinth = MazeParser(testStr1);
        var a = MazeSolver.FindPath(labyrinth);
        var sm = GetSolvedMaze(a, labyrinth);
        System.out.println("Нерешенный лабиринт");
        System.out.println(testStr1);
        System.out.println("Решенный лабиринт");
        System.out.println(sm);




    }
}
