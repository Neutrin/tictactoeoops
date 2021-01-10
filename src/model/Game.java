package model;

import java.util.List;

public class Game{
    private List<User> users;
    private User userWon;
    Board board;
    public void saveGame() {

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUserWon() {
        return userWon;
    }

    public void setUserWon(User userWon) {
        this.userWon = userWon;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }



}