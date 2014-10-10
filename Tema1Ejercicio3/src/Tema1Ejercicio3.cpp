//============================================================================
// Name        : Tema1Ejercicio3.cpp
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
using namespace std;
struct datos {
	char nombre[80]; //nombre
	char apell1[80]; //primer apellido
	char apell2[80]; //segundo apellido
	int telefono; //teléfono

};
int main() {
	int opcion = 0, telefono;
	datos dato1, dato2, dato3, dato4;
	string nombre, ape1, ape2;
	do {
		cout << "1.-INSERTAR PERSONA EN TXT" << endl;
		cout << "2.-INSERTAR PERSONA EN BIN" << endl;
		cout << "3.-LISTAR TXT" << endl;
		cout << "4.-LISTAR BIN" << endl;
		cout << "5.-SALIR" << endl;
		cout << "Introduzca opción: ";
		cin >> opcion;

		if (opcion == 1) {
			ofstream salida("c:\\archivos\\texto.txt", ios::out | ios::app); //ABRIMOS FLUJO ESCRITURA para añadir por el final
			cout << "Introduzca nombre: ";
			fflush(stdin);
			getline(cin, nombre);
			strcpy(dato1.nombre, nombre.c_str()); //insertamos datos en la struct
			cout << "Introduzca primer apellido: ";
			getline(cin, ape1);
			strcpy(dato1.apell1, ape1.c_str()); //insertamos datos en la struct
			cout << "Introduzca segundo apellido: ";
			getline(cin, ape2);
			strcpy(dato1.apell2, ape2.c_str()); //insertamos datos en la struct
			cout << "Introduzca telefono: ";
			cin >> telefono;
			dato1.telefono = telefono;

			//salida.write(reinterpret_cast<char*>(&dato1), sizeof(datos)); //ESCRIBIMOS LA ESTRUCTURA EN EL ARCHIVO
			salida << dato1.nombre << " " << dato1.apell1 << " " << dato1.apell2
					<< " " << dato1.telefono << endl;
			salida.close();
		} else if (opcion == 2) {
			ofstream salida2("c:\\archivos\\binario.txt",
					ios::out | ios::binary | ios::app); //ABRIMOS FLUJO ESCRITURA para añadir por el final
			cout << "Introduzca nombre: ";
			cin >> nombre;
			strcpy(dato2.nombre, nombre.c_str()); //insertamos datos en la struct
			cout << "Introduzca primer apellido: ";
			cin >> ape1;
			strcpy(dato2.apell1, ape1.c_str()); //insertamos datos en la struct
			cout << "Introduzca segundo apellido: ";
			cin >> ape2;
			strcpy(dato2.apell2, ape2.c_str()); //insertamos datos en la struct
			cout << "Introduzca telefono ";
			cin >> telefono;
			dato2.telefono = telefono;
			salida2.write(reinterpret_cast<char*>(&dato2), sizeof(datos)); //ESCRIBIMOS LA ESTRUCTURA EN EL ARCHIVO

			salida2.close();
		} else if (opcion == 3) {
			ifstream entrada("c:\\archivos\\texto.txt", ios::in); //ABRIMOS FLUJO ENTRADA

			entrada >> dato3.nombre;
			entrada >> dato3.apell1;
			entrada >> dato3.apell2;
			entrada >> dato3.telefono;
			/*
			 * Para leer primero leemos todos los datos de la estructura, y mientras no sea final de fichero mostramos datos y leemos al final de while.
			 * */
			while (!entrada.eof()) {
				cout << "NOMBRE: ";
				cout << dato3.nombre;
				cout << " 1º APELLIDO: ";
				cout << dato3.apell1;
				cout << " 2º APELLIDO: ";
				cout << dato3.apell2;
				cout << " TELEFONO: ";
				cout<<dato3.telefono;
				cout<<endl;
				entrada >> dato3.nombre;
				entrada >> dato3.apell1;
				entrada >> dato3.apell2;
				entrada >> dato3.telefono;
			}
			entrada.close();
		} else if (opcion == 4) {
			ifstream entrada2("c:\\archivos\\binario.txt",
					ios::in | ios::binary); //ABRIMOS FLUJO ENTRADA
			entrada2.read(reinterpret_cast<char*>(&dato4), sizeof(datos)); //LEEMOS UNO DEL ARCHIVO
			while (!entrada2.eof()) {
				cout << "NOMBRE: " << dato4.nombre << " 1º APELLIDO: "
						<< dato4.apell1 << " 2º APELLIDO: " << dato4.apell2
						<< " TELEFONO: " << dato4.telefono << endl; //MOSTRAMOS DATOS
				entrada2.read(reinterpret_cast<char*>(&dato4), sizeof(datos)); //LEEMOS EL SIGUIENTE
			}
			entrada2.close();
		} else {
			cout << "Ha pulsado salir.";
		}
	} while (opcion != 5);
	return 0;
}
