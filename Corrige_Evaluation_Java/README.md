# Corrigé de révision — POO Java

Projet basé sur le sujet PDF fourni.

## Matricule fictif
Matricule choisi : 2410537 ; N = 37.

## Important concernant les paramètres
Le PDF fourni ne contient pas les valeurs/formules de `TARIF_KG`, `SURCHARGE_HORS_DAKAR` et `CAPACITE` pour N = 37. Les valeurs placées dans `Parametres.java` sont donc des valeurs d'exemple, pas les valeurs officielles de l'évaluation. Il faut les remplacer si le README original du dépôt fournit les paramètres.

Les exemples du sujet pour N = 0 permettent de déduire que le tarif de base de 5 kg à Dakar vaut 2 000 FCFA, donc TARIF_KG = 400 dans cet exemple. Pour l'express de 3 kg hors Dakar, le tarif de 4 050 FCFA implique une surcharge de 1 500 FCFA dans cet exemple.

## Organisation
Tous les fichiers Java se trouvent dans `src/`. Le code utilise des tableaux statiques et n'utilise pas de collections.

## Compilation et exécution
Depuis le dossier du projet :

```bash
javac src/*.java
java -cp src Ex1
java -cp src Tournee
```

## Contenu
- Ex1 : prédiction de l'affichage et transtypage.
- Colis et sous-classes : héritage, redéfinition, equals/hashCode, compareTo et toString.
- Inventaire : parcours d'un tableau Object[].
- Tournee : tri fusion et sac à dos par programmation dynamique.
