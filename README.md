# TONDEUSE LIBON (AKA MARS ROVER OVER HTTP)

## CONTEXTE

Implémenter Mars Rover avec les contraintes suivantes :
 - Concevoir et écrire un programme s'exécutant sur une JVM
 - Implémenter sous forme d'un endpoint HTTP
 - repo github avec un historique de commit pour permettre de facilement de voir le cheminement

Objectifs :

> voir comment tu développes, quels frameworks tu aimes utiliser et comment tu industrialises tout ça.

## APPROCHE

Je n'ai pas particulièrement de préférences pour un framework donné, ni pour un langage qui tourne sur la JVM. J'aurais bien tenté de faire ça en Kotlin, en Jython voire en Frege mais je perdrais plus de temps à chercher la doc du langage et ça n'apportera pas vraiment grand chose.

Côté industrialisation, j'interprète ça comme gestion de toute la chaine, avec pour but de déployer l'application quelque part. Du coup, aujourd'hui si on me demande de déployer cette application, je la deployerais sur le cloud parce que je n'ai pas de serveur à disposition (que ça soit un serveur web ou un serveur de conteneurs). Etant donné la "simplicité" de l'application, j'aurais tendance à vouloir la déployer en mode serverless, pour une raison de coût et de facilité de deploiement (une petite usine logicielle en soutien, intégrée au provider cloud).
Lors de la présentation, j'ai cru comprendre que Libon utilise google cloud platform. En regardant la [documentation](https://cloud.google.com/blog/products/application-development/introducing-java-11-on-google-cloud-functions), je vois qu'il y a 2 frameworks : [Micronaut](https://micronaut.io/) et [Spring](https://spring.io/projects/spring-cloud-function), sauf que ce dernier est en alpha au moment de la réalisation de ce projet. Je vais donc porter mon choix de framework sur Micronaut : il a l'air compatible avec les principaux provider clouds, peut aussi intégrer son propre serveur web ou encore être dans un conteneur Docker.
Je vais donc passer par la génération de projet disponible sur https://micronaut.io/launch/.

### DOMAINE

Je commence d'abord par créer les concepts du domaine :
 - Coordonnées (`x,y`)
 - Orientation (`N,E,W,S`)
 - Position de la tondeuse (une coordonnée et une orientation)
 - Commande (utilisée en séquence pour contrôler la tondeuse, `D,G,A`)
 - Surface de pelouse (pour stocker la taille du terrain)
 - Tondeuse (une position et une séquence de commande)

Je vais considérer pour le moment que chaque concept est **immutable**.
Etant donné que je suis dans mon domaine, et que je vais le cloisonner, je prends ici le choix délibéré de ne PAS faire de getter/setter.
Pour la tondeuse, je passe par la lib apache commons pour avoir une réelle notion d'immutabibilité au niveau de la liste de commandes.
J'ai hésité avec Queue pour montrer le concept de FIFO, mais je repousse ma décision à plus tard...
Il n'y a pour l'instant aucun comportement.

### Implémentation comportements

*en mode TDD pour changer*
Je sais que je vais avoir un souci avec la pelouse : je ne sais pas encore à quel niveau l'intégrer. Donc je repousse ça à plus tard, avec l'impact de refacto que cela entrainera.
Pour l'instant, je vais ne pas la tenir en compte.

Une branche tdd a été créée, elle montre un peu plus de détail sur l'implémentation du déplacement (par contre je n'ai pas non plus fait des micro commit pour montrer que je faisais ça en TDD as u mean it, ce que je ne fais d'ailleurs pas)

### Adapter Http

Je ne connais pas Micronauts, et si j'aurais fait ça en Spring boot, cela fait tellement longtemps que je n'arriverais pas à faire ce que je veux en 1h (temps qui me reste à peu près).
Donc dans l'ordre, j'aimerais faire :
 1. Partir d'un test d'api avec la payload de l'exemple en entrée et la payload de sortie en assert
 2. Créer un controller et mock toute la partie domaine
 3. Faire la couche d'anticorruption qui va traduire la payload en objet du domaine et l'appeler (via tests aussi)
 4. Avoir de quoi tester l'ensemble (avec le serveur lancé et aucun mock)
 5. Monter une doc api (swagger ou autre)
 6. Ajouter une couche de validation d'input côté controller
 7. Ajouter du monitoring healthcheck (je sais même pas si c'est vraiment utile en serverless, mais en standalone ça pourrait l'être)
 8. Des logs, des logs et des logs.
 
Au final, j'ai fait 1, 3 et 4 et j'ai dépassé l'heure... à cause de :
 - gradle pas bien configuré
 - difficultés avec Micronauts et intégration ide (pas lu la doc comme il fallait - RTFM)
 - encore du mal à revenir vers Java... 