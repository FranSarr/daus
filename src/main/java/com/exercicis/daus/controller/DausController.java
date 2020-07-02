package com.exercicis.daus.controller;

import com.exercicis.daus.domain.Player;
import com.exercicis.daus.persistence.GameRepository;
import com.exercicis.daus.persistence.PlayerRepository;
import com.exercicis.daus.utilities.PlayerExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController // This means that this class is a Controller
@RequestMapping(path="")
public class DausController {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    // Shows the list of players TODO include stadistics
    @GetMapping(path="/players")
    public @ResponseBody
    Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Adds one player to the database
    @PostMapping(path="/players")
    public Player newPlayer(@RequestBody Player newPlayer) {
        //System.out.println(newPlayer.getName());
        if ( playerRepository.existsByName(newPlayer.getName()) && !newPlayer.getName().equalsIgnoreCase("anonymous"))
        {
                throw new PlayerExistsException(newPlayer.getName());
        }
        return playerRepository.save(newPlayer);
    }
}
