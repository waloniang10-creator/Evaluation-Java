public class Tournee {
    public static void trierFusion(Colis[] t) {
        if (t == null || t.length <= 1) return;
        Colis[] temporaire = new Colis[t.length];
        trierFusion(t, temporaire, 0, t.length - 1);
    }

    private static void trierFusion(Colis[] t, Colis[] temp,
                                    int debut, int fin) {
        if (debut >= fin) return;

        int milieu = debut + (fin - debut) / 2;
        trierFusion(t, temp, debut, milieu);
        trierFusion(t, temp, milieu + 1, fin);
        fusionner(t, temp, debut, milieu, fin);
    }

    private static void fusionner(Colis[] t, Colis[] temp,
                                  int debut, int milieu, int fin) {
        int i = debut;
        int j = milieu + 1;
        int k = debut;

        while (i <= milieu && j <= fin) {
            if (t[i].compareTo(t[j]) <= 0) {
                temp[k++] = t[i++];
            } else {
                temp[k++] = t[j++];
            }
        }

        while (i <= milieu) temp[k++] = t[i++];
        while (j <= fin) temp[k++] = t[j++];

        for (int p = debut; p <= fin; p++) {
            t[p] = temp[p];
        }
    }

    public static Colis[] chargementOptimal(Colis[] t, int capacite) {
        if (t == null || t.length == 0 || capacite <= 0) {
            return new Colis[0];
        }

        int n = t.length;
        long[][] meilleur = new long[n + 1][capacite + 1];

        // Programmation dynamique : chaque colis est pris au maximum une fois.
        for (int i = 1; i <= n; i++) {
            int poids = t[i - 1].getPoids();
            long valeur = t[i - 1].tarif();

            for (int cap = 0; cap <= capacite; cap++) {
                meilleur[i][cap] = meilleur[i - 1][cap];

                if (poids <= cap) {
                    long avec = meilleur[i - 1][cap - poids] + valeur;
                    if (avec > meilleur[i][cap]) {
                        meilleur[i][cap] = avec;
                    }
                }
            }
        }

        // Première traversée : compter les colis retenus.
        int nombreChoisi = 0;
        int cap = capacite;

        for (int i = n; i >= 1; i--) {
            if (meilleur[i][cap] != meilleur[i - 1][cap]) {
                nombreChoisi++;
                cap -= t[i - 1].getPoids();
            }
        }

        // Deuxième traversée : remplir un tableau de taille exacte.
        Colis[] choix = new Colis[nombreChoisi];
        cap = capacite;
        int position = nombreChoisi - 1;

        for (int i = n; i >= 1; i--) {
            if (meilleur[i][cap] != meilleur[i - 1][cap]) {
                choix[position--] = t[i - 1];
                cap -= t[i - 1].getPoids();
            }
        }

        return choix;
    }

    public static void main(String[] args) {
        Colis[] lot = {
            new ColisStandard("C01", 4, false),
            new ColisExpress("C02", 3, true),
            new ColisFragile("C03", 5, false, 120_000),
            new ColisStandard("C04", 7, true),
            new ColisExpress("C05", 2, false),
            new ColisFragile("C06", 6, true, 45_000),
            new ColisStandard("C07", 1, true),
            new ColisExpress("C08", 8, false),
            new ColisFragile("C09", 3, false, 300_000),
            new ColisStandard("C10", 5, false)
        };

        trierFusion(lot);

        System.out.println("Colis après le tri :");
        for (int i = 0; i < lot.length; i++) {
            System.out.println(lot[i]);
        }

        Colis[] selection = chargementOptimal(lot, Parametres.CAPACITE);
        long recette = 0;
        for (int i = 0; i < selection.length; i++) {
            recette += selection[i].tarif();
        }

        System.out.println("Colis chargés :");
        for (int i = 0; i < selection.length; i++) {
            System.out.println(selection[i]);
        }

        System.out.println("Recette optimale : " + recette
                + " FCFA (capacite " + Parametres.CAPACITE + " kg)");
    }
}
