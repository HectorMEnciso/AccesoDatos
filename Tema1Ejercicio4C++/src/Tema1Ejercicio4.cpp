//============================================================================
// Name        : tema1Ejercicio4.cpp
// Author      :
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
#include <iostream>
#include <string.h>
#include <stdlib.h>
#include <fstream>
#include <time.h>
#include <stdio.h>
#include <cstdlib>
#include <map>
#include <vector>

using namespace std;

typedef struct ficha {
	char dni[10];
	char nombre[25];
	char apellidos[25];
} persona;
typedef string dni;
typedef int direc;
typedef map<dni, direc, greater<dni> > mapa;
#include "metodos.h"
int main() {
	//fstream datos1("c:\\archivos\\datos.txt", ios::binary|ios::out);
	mapa miMapa;
	map<dni, direc, greater<dni> >::iterator pos;
	int opcion = 0, hueco, a, b;
	vector<int> v;
	string dni, nombre, apellidos;
	comprobarFichero();
	construye(miMapa, a, b);
	//leerMapa(miMapa);
	do {
		cout << "1.-INSERTAR PERSONA" << endl;
		cout << "2.-BORRAR PERSONA" << endl;
		cout << "3.-LISTAR" << endl;
		cout << "4.-COMPROBAR MAPA" << endl;
		cout << "5.-SALIR" << endl;
		cout << "Introduzca opción: ";
		cin >> opcion;

		if (opcion == 1) {
			altas(miMapa, a, b, hueco, v);
		} else if (opcion == 2) {
			Bajas(miMapa, a, b, hueco, v);
		} else if (opcion == 3) {
			Listado(miMapa);
		} else if (opcion == 4) {
			leerMapa(miMapa);
		} else {
			cout << "Ha pulsado salir.";
		}
	} while (opcion != 5);

}
