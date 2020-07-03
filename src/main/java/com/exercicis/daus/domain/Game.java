package com.exercicis.daus.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Random;

@Entity(name="Game")
public class Game {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="id_game")
    private Integer id;
    private Integer dice1;
    private Integer dice2;
    private Boolean won;

    @JoinColumn(name ="player_id", nullable = false)
    @ManyToOne(targetEntity = Player.class)
    private Player player;

    public Game() {
    }

    public Game(Player player) {
        this.player = player;

        Random dc1 = new Random();
        Random dc2 = new Random();
        int roll1 = dc1.nextInt(6)+1;
        int roll2 = dc2.nextInt(6)+1;
        int result = roll1 + roll2;
        Boolean isSeven = false;
        if (result==7) { isSeven=true; }

        this.dice1 = roll1;
        this.dice2 = roll2;
        this.won = isSeven;

    }

    public Game(Integer dice1, Integer dice2, Player player) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.player = player;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDice1() {
        return dice1;
    }

    public void setDice1(Integer dice1) {
        this.dice1 = dice1;
    }

    public Integer getDice2() {
        return dice2;
    }

    public void setDice2(Integer dice2) {
        this.dice2 = dice2;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
