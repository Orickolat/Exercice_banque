package com.company;

import static com.company.Banque.scanner;

public class BanqueInteraction {

    public static Banque banqueEcureuil = new Banque();

    void interaction() {
        int instance=0;
        do{
        System.out.println("Quelle opération voulez-vous effectuer ?");
        System.out.println("1) Ajouter un client.");
        System.out.println("2) Effectuer une opération.");
        System.out.println("3) Afficher un bilan général.");
        System.out.println("4) Renflouer l'ensemble des clients.");
            instance = scanner.nextInt();
            switch (instance) {
                case 1:
                    interactionAjouter1Client();
                    break;
                case 2:
                    interactionOperation();
                    break;
                case 3:
                    banqueEcureuil.afficherBilan();
                    break;
                case 4:
                    banqueEcureuil.renfloumentGeneral();
                    break;
                default:
                    break;
            }//Fin switch
        }while( (instance==1) ||
                (instance==2) ||
                (instance==3) ||
                (instance==4) );
    }// Fin interaction


    void interactionAjouter1Client() {
        String name="";
        System.out.print("Quel est le nom du client ?");
        name = scanner.nextLine(); //nextLine() ne marche pas je ne comprend pas pourquoi.
        banqueEcureuil.ajouterClient(name);
    }


    void interactionOperation() {
        System.out.println("Quel client ?");
        String name = scanner.next();
        if (identifiantClient(name) == -1) {
            System.out.println("Ce client n'existe pas.");
        } else {
            int identifiant = identifiantClient(name);
            int instance=0;
            do {
                System.out.println("Quelle opération voulez-vous effectuer ?");
                System.out.println("1) Afficher un bilan.");
                System.out.println("2) Faire un retrait.");
                System.out.println("3) Faire un dépöt.");
                System.out.println("4) Faire un virement.");
                System.out.println("5) Menu principale.");
                instance = scanner.nextInt();

                switch (instance) {
                    case 1:
                        banqueEcureuil.
                                bilanClient(
                                        banqueEcureuil.
                                                listeClients[identifiant]
                                );
                        break;
                    case 2:
                        interactionRetrait(identifiant);
                        break;

                    case 3:
                        interactionDepot(identifiant);
                        break;
                    case 4:
                        InteractionVirement(identifiant, name);
                        break;
                    default:
                        break;
                }// fin switch
            }while( (instance==1) ||
                    (instance==2) ||
                    (instance==3) ||
                    (instance==4) );
        }//fin verifClient
    }//interactionOperation

    void InteractionVirement(int identifiant, String name) {
        float montantPognon = valeaurTransaction();
        System.out.println("Le deuxième compte appartient'il au même client ?(Y/N)");
        if (scanner.next() == "Y") {
            System.out.println("Quel compte débiter ?");
            int compteNumber = scanner.nextInt();
            if (verificationExistCompte(compteNumber, identifiant)) {
            } else {
                System.out.println("Quel compte débiter ?");
                int compteNumber2 = scanner.nextInt();
                if (verificationExistCompte(compteNumber2, identifiant)) {
                    if (montantPognon > 0) {
                        virementAuto(identifiant, compteNumber, montantPognon, identifiant, compteNumber2);
                    }
                }
            }
        }
        //Fin même client
        else {
            System.out.println("Quel est le deuxième client ?");
            String name2 = scanner.next();
            if (identifiantClient(name2) == -1) {
                System.out.println("Ce client n'existe pas.");
            } else {
                int identifiant2 = identifiantClient(name2);
                System.out.println("Quel est son numero de compte ?");
                int numeroCompte2= scanner.nextInt();
                if (verificationExistCompte(numeroCompte2,identifiant2)) {
                    System.out.println("Quel est le numero de compte de " + name);
                    int compteNumber= scanner.nextInt();
                    if (verificationExistCompte(compteNumber, identifiant)) {
                        System.out.println("Le client " + name + " est il débiteur ? (Y/N)");
                        if (scanner.next() == "Y") {
                            if (montantPognon > 0) {
                                virementAuto(identifiant, compteNumber, montantPognon, identifiant2, numeroCompte2);
                            }
                        } else {virementAuto(identifiant2, numeroCompte2, montantPognon, identifiant, compteNumber);}
                    }
                }
            }
        }//Fin deux client
    }//Fin virement

    void interactionRetrait(int identifiant) {
        System.out.println("Quel compte ?");
        int compteNumber = scanner.nextInt();
        if (verificationExistCompte(compteNumber, identifiant)) {
            if (valeaurTransaction() > 0) {
                banqueEcureuil.
                        listeClients[identifiant].
                        comptes[compteNumber].
                        retrait(valeaurTransaction());
                System.out.println("Transaction fini.");
            }
        }
    }

    void interactionDepot(int identifiant) {
        System.out.println("Quel compte ?");
        int compteNumber2 = scanner.nextInt();
        if (verificationExistCompte(compteNumber2, identifiant)) {
            if (valeaurTransaction() > 0) {
                banqueEcureuil.
                        listeClients[identifiant].
                        comptes[compteNumber2].
                        depot(valeaurTransaction());
                System.out.println("Transaction fini.");
            }
        }
    }

    void virementAuto(int identifiant ,int compteNumber, float montantPognon,int identifiant2, int compteNumber2){
        banqueEcureuil.
                listeClients[identifiant]
                .comptes[compteNumber]
                .virer(
                        montantPognon,
                        banqueEcureuil.
                                listeClients[identifiant2]
                                .comptes[compteNumber2]
                );
    }

    float valeaurTransaction() {
        int i = 0;
        float pognon = -1f;
        do {// Fait deux fois la boucle mais je ne sais pas pourquoi.
            System.out.println("Combien sera le montant de la transaction ? Le montant doit être positif.");
            pognon = scanner.nextFloat();
            i++;
            if (i == 3) {
                System.out.println("Trop d'essaie. Veuillez vous consulter le manuel d'utilisation.");
                break;
            }
        } while (pognon < 0f);
        return pognon;
    }

    boolean verificationExistCompte(int compteNumber, int identifiant) {
        boolean bouboule = ((compteNumber >= 0) &&
                (compteNumber < banqueEcureuil.listeClients[identifiant].comptes.length));
        if (!bouboule) {
            System.out.println("Ce numero de compte n'existe pas.");
        }
        return bouboule;
    }

    int identifiantClient(String name) {
        int identifiant = -1;
        for (int i = 0; i < banqueEcureuil.listeClients.length; i++) {
            if (banqueEcureuil.listeClients[i].nom.equals(name)) {
                identifiant = i;
                break;
            }
        }
        return identifiant;
    }


}// fin "main"