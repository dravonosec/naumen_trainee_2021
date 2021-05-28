package com.example.naumen_trainee;

import java.util.Random;

import static com.example.naumen_trainee.MazeSolver.mazeParser;

public class Main {

    public static void main(String[] args){
        String testStr1 = "...@.\n#####\n.....\n####.\n.X...";
        String testStr2 = "....@\n#.###\n.....\n....X\n.....";
        StringBuilder s = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (i == 0 && j == 0)
                    s.append('@');
                else if (i == 999 && j == 999)
                    s.append('X');
                else if (rnd.nextInt(100) < 20)
                    s.append('#');
                else s.append('.');
            }
            if (i < 999)
                s.append('\n');
        }


        var a = MazeSolver.mazeParser(testStr1);
        var b = MazeSolver.findRoute(a);
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++)
                System.out.print(b[i][j]);
            System.out.println();
        }
    }
}
