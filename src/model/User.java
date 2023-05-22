/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class User {
    private int ID;
    private String username;
    private String password;
    private String nickname;
    private int numberOfGame;
    private int numberOfwin;
    private int numberOfDraw;
    private int rank;

    public User(int ID, String username, String password, String nickname, int numberOfGame, int numberOfwin, int numberOfDraw, int rank) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.numberOfGame = numberOfGame;
        this.numberOfwin = numberOfwin;
        this.numberOfDraw = numberOfDraw;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    public User(int ID, String username, String password, String nickname, int numberOfGame, int numberOfwin, int numberOfDraw) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.numberOfGame = numberOfGame;
        this.numberOfwin = numberOfwin;
        this.numberOfDraw = numberOfDraw;
    }

    public User(int ID, String nickname) {
        this.ID = ID;
        this.nickname = nickname;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public int getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public int getNumberOfGame() {
        return numberOfGame;
    }

    public int getNumberOfwin() {
        return numberOfwin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNumberOfGame(int numberOfGame) {
        this.numberOfGame = numberOfGame;
    }

    public void setNumberOfwin(int numberOfwin) {
        this.numberOfwin = numberOfwin;
    }

    public User(int ID, String nickname, int numberOfGame, int numberOfDraw) {
        this.ID = ID;
        this.nickname = nickname;
        this.numberOfGame = numberOfGame;
        this.numberOfDraw = numberOfDraw;
    }

    public int getNumberOfDraw() {
        return numberOfDraw;
    }

    public void setNumberOfDraw(int numberOfDraw) {
        this.numberOfDraw = numberOfDraw;
    }

    
    
}
