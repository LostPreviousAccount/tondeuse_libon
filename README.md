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
