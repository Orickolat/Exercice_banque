package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Banque {

    static Scanner scanner = new Scanner(System.in);
    static int numClient = 0;
    String name="banqueEcureuil";
    Client[] listeClients = new Client[1];
    int identifiant=0;

    public Banque(){
        this.identifiant=numClient++;
        this.name=name;
        this.listeClients[0]= new Client("Boris");
    }

    public void ajouterClient(String nameClient){
        this.listeClients =  Arrays.copyOf(this.listeClients, this.listeClients.length + 1);
        Client nouveauClient = new Client(nameClient);
        this.listeClients[this.listeClients.length-1] = nouveauClient;
    }

    public void bilanClient(Client client){
        client.afficherSoldeClient();
    }

    public void afficherBilan(){
        System.out.println(this.listeClients.length);
        for (int i = 0; i < this.listeClients.length; i++) {
            bilanClient(listeClients[i]);
        }
    }

    public void renfloumentGeneral(){
        for (int i = 0; i < listeClients.length; i++){
            listeClients[i].renflouer(listeClients[i]);
        }
    }

}