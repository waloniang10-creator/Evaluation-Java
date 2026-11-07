public class Inventaire {
    public static long valeurAssuree(Object[] objets) {
        long somme = 0;
        if (objets == null) return somme;

        for (int i = 0; i < objets.length; i++) {
            if (objets[i] instanceof ColisFragile) {
                ColisFragile fragile = (ColisFragile) objets[i];
                somme += fragile.getValeurDeclaree();
            }
        }
        return somme;
    }

    // Résultat : {nombre de standards, express, fragiles, autres}.
    // Les null sont ignorés et un fragile n'est pas compté comme standard.
    public static int[] compterParType(Object[] objets) {
        int[] compteurs = new int[4];
        if (objets == null) return compteurs;

        for (int i = 0; i < objets.length; i++) {
            Object objet = objets[i];

            if (objet == null) {
                continue;
            } else if (objet instanceof ColisFragile) {
                compteurs[2]++;
            } else if (objet instanceof ColisExpress) {
                compteurs[1]++;
            } else if (objet instanceof ColisStandard) {
                compteurs[0]++;
            } else {
                compteurs[3]++;
            }
        }
        return compteurs;
    }
}
