# AndrOthello
Projet Semestre 2 sur Android

## Répartition du travail
Loïc Guenver => Toute la partie Modèle : les différentes règles de jeu, le chargement, la sauvegarde ainsi que l'intelligence artificielle.

Letay Benoit => Toute la partie Interface : L'affichage du plateau, des boutons et des options et la gestion du cycle de vie.

## modèle
    Les cellules sont contenu sous forme de matrice dans un plateau lui même connu du moteur de jeu
    Les joueurs, humain ou IA son contenu dans un tableau dans le moteur de jeu
    Lors d'un coup, si ce dernier est valide, le moteur indique au joueur courant le coup à jouer, si le joueur est humain alors la case appuyée
    est changée ainsi que toutes les cases capturées
    Si le joueur est une IA, un coup est choisi aléatoirement parmis les coup valide possible.
    Quand il n'y a pas de coup possible pour un joueur, son tour est passé; si aucun des 2 joueurs ne peut jouer, la partie se termine et le vainqueur est décidé
    en fonction du nombre de cases possedées par chaque joueur
    
    
## Controller
Le controller est la classe motor qui est appelé par les différents onclicklistener de la Vue

#Vue
La vue se charge de l'affichage des différents boutton et de la grille.


