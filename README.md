Ce projet a été réalisé dans le cadre de l'UV IA54 à l'Université de Technologie de Belfort-Montbéliard, durant le semestre Automne 2017.

Le groupe de projet était composé de Thomas BOUCHEREAU et de Guillaume PROST, et le projet portait sur le développement d'une application SARL permettant de faire des calculs d'optimisation sur des problèmes de voyageurs de commerce, en utilisant un système de _Ant Colony Optimization_
# Structure du dépôt

## Rapport de projet
Le rapport de ce projet se trouve sous _/resources/rapport_.

## Fichiers de données TSP
Les fichiers contenant les données des TSP se trouvent sous _/resources/benchmark_.
ATTENTION : les fichiers benchmarkExample.txt et benchmarks_source.txt ne sont pas des fichiers lisibles par le parseur.

## Code source
Le code source est divisé en 3 workspaces : 
 * _/sarl_ correspond au workspace SARL, lisible par l'IDE Eclipse SARL (téléchargement : http://www.sarl.io/). Il contient un unique projet nommé IA54_Ant.
 * _/workspace_ correspond au workspace Java dédié à l'interface graphique, lisible par l'IDE IntelliJ IDEA (téléchargement : https://www.jetbrains.com/idea/)
 * _/TSPFileParser_ correspond au workspace Java dédié au parseur de fichiers, lisible par l'IDE IntelliJ IDEA

## Librairies annexes
Les archives compilées des deux projets Java décrit ci-dessus se trouvent sous _/lib_, et sont nécessaire pour lancer correctement le projet SARL.

# Lancement du projet SARL

Le lancement du projet SARL se fait obligatoirement via l'IDE Eclipse SARL, puisque la génération d'un exécutable a été impossible.

Pour lancer le projet dans l'IDE SARL : 

 1. Ajoutez les deux archives _.jar_ se trouvant sous _/lib_ aux librairies externes du projet IA54_Ant, en tant que _External JARs_
 1. Créez une nouvelle configuration de lancement _SARL Application_, en sélectionnant IA54_Ant comme projet et _com.utbm.ia54.aco.Agent.Environment_ comme agent de lancement. Aucun argument n'est nécessaire pour le lancement.
 1. Exécutez la configuration de lancement.