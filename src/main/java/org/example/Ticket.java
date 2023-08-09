package org.example;

import client.Client;
import jakarta.persistence.*;
import planet.Planet;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name ="created_at")
    private String createdAt;
    @ManyToOne
    @JoinColumn(name="from_planet_id", referencedColumnName = "id",nullable=false)
    private Planet fromPlanet;
    @ManyToOne
    @JoinColumn(name="to_planet_id",referencedColumnName = "id",nullable=false)
    private Planet toPlanet;
    @ManyToOne
    @JoinColumn(name="client_id", referencedColumnName = "id", nullable=false)
    private Client client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Planet getFromPlanet() {
        return fromPlanet;
    }

    public void setFromPlanet(Planet fromPlanet) {
        this.fromPlanet = fromPlanet;
    }

    public Planet getToPlanet() {
        return toPlanet;
    }

    public void setToPlanet(Planet toPlanet) {
        this.toPlanet = toPlanet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
