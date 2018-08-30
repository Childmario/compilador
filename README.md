# compilador

Proyecto Compliadores 2018 Fase 1


1.	Funcionamiento General del proyecto:
El proyecto recibe 2 entradas, uno es el archivo de reglas Jflex y el otro archivo es la entrada a ser analizada.
La salida es una, es un texto con extensión .out el que contiene la información del token, lexema, fila y columna
De las expresiones correctas, así como de las expresiones erroneas.


	1.1	Archivo de entrada de reglas Jflex
	Mi archivo de entrada de reglas Jflex, contiene una serie de escritos deividos en 3 series, la primera es de imports
	de librerías que vamos a utlilizar, la segunda es de directivas(Como nombre clase a generar, filas, columnas, return)
	y el tercer segmento se compone de acciones, donde yo le digo como comportarse dependiendo de la regex que hace match.

	1.2	Archivo de entrada a analizar
	Al ejecutar el programa, aparece un dialogo de entrada, donde se selecciona el archivo texto pertinente a la fase de 
	proyecto. Este archivo debe tener extensión .txt y cumplir con el propósito del proyecto.

	1.3	Archivo de salida
	En el archivo de salida, se puede observar todos los tokens encontrados (correctos y errores), menos los comentarios.
	Veremos también que si un id se pasa de los 31 caracteres, este mostrara un * en la cadena, indicando que se ha truncado.
	El archivo de salida debe guardarse como .out. Se despliega un dialogo para elgir donde guardarlo.


2. Interfaz gráfica
En la intefaz gráfica nosotros podemos interactuar con el programa. El botón analizar nos servira para abrir el dialogo de
entrada para el texto a analizar y posterior a abrir el archivo a analizar, se abrirá el dialogo para guardar la salida.
Veremos 2 gráficos, a la izquiera un texto enriquecido con todos los tokens leidos, los que se deben mostrar en el arhivo
de salida y los que no. Del lado derecho veremos los tokens leidos, solo los que se exportarán en el arhcivo de texto.

3. Manejo de errores
Cada Expresión regular cumple con un patrón. Si una expresión regular no cumple con los patrones asignados, se va por una
expersión regular de errores generales (.   return error). Para el especifico caso del error de no cerrar comentarios, 
se creo una epxresión regular ("/\*" [^*]+), esta regex me ayuda a controlar que si no se cierra el comentario, lanza error.

4. Pruebas realizadas
El proyecto fue sometido a diferentes pruebas, evaluando los siguientes escenarios:

	4.1 Caracteres que no pertenezcan en la gramática.
	4.2 Comentarios /* no cerrados.
	4.3 Id que superen los 31 caracteres.
	4.4 Palabras reservadas de todo tipo.
	4.5 Pruebas con enteros.
	4.6 Pruebas con double.
	4.7 Pruebas con simbolos.


