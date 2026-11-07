public abstract class Colis implements Comparable<Colis> {
    private String code;
    private int poids;
    private boolean horsDakar;

    public Colis(String code, int poids, boolean horsDakar) {
        if (poids <= 0) {
            throw new IllegalArgumentException("Le poids doit être supérieur à zéro.");
        }
        this.code = code;
        this.poids = poids;
        this.horsDakar = horsDakar;
    }

    public String getCode() {
        return code;
    }

    public int getPoids() {
        return poids;
    }

    public boolean isHorsDakar() {
        return horsDakar;
    }

    public abstract long tarif();
    public abstract String sigle();
    public abstract int priorite();

    // Calcul du tarif de base écrit une seule fois.
    protected long tarifBase() {
        long resultat = (long) poids * Parametres.TARIF_KG;
        if (horsDakar) {
            resultat += Parametres.SURCHARGE_HORS_DAKAR;
        }
        return resultat;
    }

    @Override
    public String toString() {
        String zone = horsDakar ? "hors-Dakar" : "Dakar";
        return "[" + sigle() + "] " + code + " " + poids + "kg "
                + zone + " " + tarif() + " FCFA";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colis autre = (Colis) o;
        return code.equals(autre.code);
    }

    @Override
    public int hashCode() {
        return 31 * getClass().hashCode() + code.hashCode();
    }

    @Override
    public int compareTo(Colis autre) {
        // Priorité décroissante.
        int resultat = Integer.compare(autre.priorite(), this.priorite());
        if (resultat != 0) return resultat;

        // Tarif décroissant.
        resultat = Long.compare(autre.tarif(), this.tarif());
        if (resultat != 0) return resultat;

        // Code croissant.
        return this.code.compareTo(autre.code);
    }
}
