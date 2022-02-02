package com.company;

import java.util.Scanner;
import static com.company.BanqueInteraction.banqueEcureuil;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Compte courant = new Compte(100);
        Compte livretA = new Compte(2000);
        Compte PEL = new Compte(10000);
        Compte PEA = new Compte(15000);
        Client clientA = new Client("Rémi");
        Client clientB = new Client("Pépito");

        banqueEcureuil.ajouterClient(clientA.nom);
        banqueEcureuil.listeClients[1].comptes[0] = courant;
        banqueEcureuil.listeClients[1].ajouterCompte();
        banqueEcureuil.listeClients[1].comptes[1] = livretA;
        banqueEcureuil.listeClients[1].ajouterCompte();
        banqueEcureuil.listeClients[1].comptes[2] = PEL;
        banqueEcureuil.listeClients[1].ajouterCompte();
        banqueEcureuil.listeClients[1].comptes[3] = PEA;
        BanqueInteraction linkStaruto = new BanqueInteraction();
        linkStaruto.interaction();


    }
}
