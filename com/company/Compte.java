package com.company;


import static com.company.Client.numCompte;

public class Compte {


    int numero;
    float solde;

    Compte(float solde) {
        this.numero = numCompte++;
        this.solde = solde;
    }

    public void depot(float valeur) {
        this.solde = this.solde + valeur;
    }

    public void retrait(float valeur) {
        this.solde = this.solde - valeur;
    }

    public void afficherSolde() {
        System.out.println(this.solde);
    }

    public float getSolde() {
        return this.solde;
    }

    public void virer(float valeur, Compte CompteDestinataire) {
        retrait(valeur);
        CompteDestinataire.depot(valeur);
    }
}
