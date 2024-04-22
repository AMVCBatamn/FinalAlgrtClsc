# Índice:
## Contenido de este Archivo:

* Experiencias con el proyecto
* Sistema de Gestión de Rutas
* Instalación
* Uso
* Licencia
* Documentación
* Contribución
* Autor
* Versiones

# Experiencias con el Proyecto:

Si bien logramos implementar toda la lógica del programa y su funcionamiento fue óptimo a través de la consola, decidimos dar un paso más allá y desarrollar la interfaz gráfica de usuario simultáneamente, con el fin de brindar una experiencia más cómoda e intuitiva para el usuario.

# Sistema de Gestión de Rutas:

Este proyecto es un sistema de gestión de rutas que permite calcular diferentes aspectos de las rutas entre ubicaciones. Ofrece funcionalidades para agregar, editar y eliminar ubicaciones, así como encontrar rutas óptimas utilizando algoritmos de grafos.

## Instalación:

1. Clona este repositorio.
2. Abre el proyecto en tu IDE favorito.
3. Configura las dependencias necesarias.
4. Ejecuta el proyecto.

## Uso:

El sistema permite:

- Insertar y eliminar nodos (ubicaciones) y aristas (conexiones).
- Calcular rutas mínimas utilizando los algoritmos de Dijkstra, Kruskal, Prim y Floyd-Warshall.
- Ver la distancia o el tiempo de las rutas.
- Visualizar la matriz de adyacencia del grafo.
- Obtener una representación básica del grafo.

## Licencia:

Este proyecto está bajo la [Licencia MIT]

MIT License  Copyright (c) [2024] [Marcelo Marcelino, Antonio Veras]  

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal 
in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO 
THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Documentación:

- **Clase: Grafo.java**

  **Propósito:** Esta clase maneja la estructura básica del grafo que incluye nodos y aristas. Es fundamental para 
  representar las conexiones entre las diferentes ubicaciones en el sistema de gestión de rutas.
             
  **Funcionalidades Principales:**  
  - Agregar y eliminar nodos.
  - Agregar y eliminar aristas.
  - Obtener información sobre rutas y conexiones.
  - Algoritmos para la búsqueda de rutas mínimas, como Dijkstra y Floyd-Warshall.

- **Clase: Nodo.java**

  **Propósito:** Representa cada ubicación dentro del sistema de gestión de rutas. Cada nodo es un punto individual en el grafo.

  - Funcionalidades Principales:
  - Mantener información específica de la ubicación.
  - Mantener una lista de aristas que conectan el nodo con otros nodos, representando posibles rutas.

- **Clase: Arista.java**

  **Propósito:** Representa la conexión entre dos nodos. Esta clase es crucial para definir el peso de las
  conexiones, que puede ser interpretado como distancia o tiempo entre puntos, dependiendo de las necesidades del sistema.
       
  **Funcionalidades Principales:**
  - Conecta dos nodos en el grafo.
  - Almacenar información sobre el costo o peso de la conexión, que se usa en los cálculos de las rutas más cortas.

- **Clase: Main.java**

  **Propósito y Funcionalidad Principal:**
       Aquí se proporciona el punto de entrada para probar el funcionamiento del proyecto de manera lógica.


## Contribución:

1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature/feature-name`).
3. Realiza tus cambios.
4. Haz commit de tus cambios (`git commit -am 'Add some feature'`).
5. Haz push a la rama (`git push origin feature/feature-name`).
6. Abre un Pull Request.


## Autor:

- Nombres: [Antonio Veras/"Marcelo Marcelino"]
([https://github.com](https://github.com/AMVCBatamn/FinalAlgrtClsc.git))

## Versiones:

- 1.0.0 - Versión inicial del proyecto.
