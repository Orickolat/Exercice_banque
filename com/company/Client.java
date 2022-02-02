package com.company;

import java.util.Arrays;

public class Client {


    static int numCompte = 0;
    String nom="";
    Compte[] comptes= new Compte[1];
    int nbComptes=0;



    public Client(String nom) {
        this.nom=nom;
        this.comptes[0]= new Compte(0);
        this.nbComptes=comptes.length;              //ne s'incrémente pas
    }

    public void ajouterCompte(){
        this.comptes = Arrays.copyOf(comptes, comptes.length + 1);
        Compte nouveauCompte = new Compte(0);
        this.comptes[comptes.length - 1]= nouveauCompte;
    }

    public String getNomClient(){
        return nom;
    } //osef

    public float getSoldeClient(Compte[] compteCourant){
        float somme=0;
            for (int i=0; i<compteCourant.length;i++){
                somme=somme+compteCourant[i].getSolde();
            }
        return somme;
    }

    public void afficherSoldeClient(){
        for (int i = 0; i < comptes.length; i++) {
            System.out.print(nom+" Compte numéro "+i+" solde :");
            comptes[i].afficherSolde();
        }
    }

    public void renflouer(Client pépito){
        if (pépito.comptes[0].solde<0) {
            for (int i = 0; i < pépito.comptes.length; i++) {
                if (Math.abs(pépito.comptes[0].solde) < pépito.comptes[i].solde) {
                    pépito.comptes[i].virer(Math.abs(pépito.comptes[0].solde), pépito.comptes[0]);
                    break;
                } else {
                    pépito.comptes[i].virer(pépito.comptes[i].solde, pépito.comptes[0]);
                }
            }
        }
        if (pépito.comptes[0].solde<0){
            System.out.println("Attention le solde global de ce client est négatif et ne peut pas être renflouer completement.");
        }
    }

}
