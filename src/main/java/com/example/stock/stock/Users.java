package com.example.stock.stock;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Users {
    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )

    private long Id;
    private String username;
    private List<String> tickers;

    public Users(){
    }

    public Users(Long Id, String username){
        this.Id = Id;
        this.username = username;
    }

    public Users(String username){
        this.username = username;
    }

    public Users(String username, List<String> tickers){
        this.username = username;
        this.tickers = tickers;
    }

    public List<String> getTickers() {
        return tickers;
    }

    public void addTicker(String newTicker){
        if(this.tickers == null){
            this.tickers = new ArrayList<>(){
                {
                    add(newTicker);
                }
            };
        } else {
            this.tickers.add(newTicker);
        }
    }

    public void deleteTicker(String oldTicker){
        int index = -1;
        for(int i = 0; i < this.tickers.size(); i++){
            if(tickers.get(i).equals(oldTicker)){
                index = i;
            }
        }
        if(index == -1){
            throw new IllegalStateException("ticker not found");
        }

        tickers.remove(index);
    }

    public void setTickers(List<String> tickers) {
        this.tickers = tickers;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Users{ \n" +
                "Id=" + Id +
                ", \n username='" + username +
                "\n }";
    }
}