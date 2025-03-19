package com.wed_connect.backend.model;

public class ClientWeddingForm {
    private Client client;
    private Wedding wedding;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Wedding getWedding() {
        return wedding;
    }

    public void setWedding(Wedding wedding) {
        this.wedding = wedding;
    }
}
