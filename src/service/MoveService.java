package service;

import model.Move;

import java.util.Scanner;

public class MoveService{

    public Move getMove(){
        Scanner sc = new Scanner(System.in);
        int rowNo = 0;
        int colNo = 0;
        System.out.println("Enter row No");
        rowNo = sc.nextInt();
        System.out.println("Enter col no");
        colNo = sc.nextInt();
        return new Move(rowNo, colNo, null);
    }

}