package test.java.data.enums;

public enum EtatProduit {
    Disponible,Indisponible;

    public static EtatProduit getEtatProduit(String value) {
        for (EtatProduit etatProduit : EtatProduit.values()) {
            if (etatProduit.name().compareToIgnoreCase(value) == 0) {
                return etatProduit;
            }
        }
        return null;
    }

    public static EtatProduit getEtatProduitId (int id) {
        for (EtatProduit etatProduit : EtatProduit.values()) {
            if (etatProduit.ordinal() == (id - 1)) {
                return etatProduit;
            }
        }
        return null;
    }

    public static int getEtatProduitIdAsInt(EtatProduit etat) {
        if (etat != null) {
            return etat.ordinal() + 1;
        } else {
            throw new IllegalArgumentException("etatProduit cannot be null");
        }
    }
}
