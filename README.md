App_Gtrackz

Aquesta aplicació té la intencionalitat d'oferir a l'usuari una serie d'eines per poder controlar les seves col·leccions de videojocs, modificant, actualitzant o esborrant jocs, així com afegir jocs desitjats.

Per oferir llistes dinàmiques utilitzem RecyclerView. La finalitat d'aquesta app és que l’usuari pugui veure i identificar les diferents categories, així com filtrar-les a través d’un buscador.

Adapter i Holder

Per gestionar les dades del RecyclerView s’ha creat la classe GameAdapter, que rep una llista de plataformes i s’encarrega de mostrar-les per la pantalla.

Dins de l’adapter hi ha la classe GameHolder, que representa cada element individual de la llista. Aquesta classe manté les referències als elements visuals utilitzats, en aquest cas el text amb el nom de la plataforma.

L’adapter s’encarrega de crear les vistes quan és necessari, assignar el nom corresponent a cada posició i indicar al RecyclerView quants elements s’han de mostrar.

Filtrat

Per implementar el sistema de cerca s’ha utilitzat un TextWatcher associat al camp de text del buscador. Cada vegada que l’usuari escriu, es crida el mètode filter() de l’adapter.

Aquest mètode compara el text introduït amb la llista original de plataformes i crea una nova llista amb només aquells elements que coincideixen amb la cerca. Si el camp està buit, es torna a mostrar la llista completa.

El RecyclerView s’actualitza automàticament cada cop que canvia el filtre, mostrant només les plataformes que compleixen el criteri de cerca.