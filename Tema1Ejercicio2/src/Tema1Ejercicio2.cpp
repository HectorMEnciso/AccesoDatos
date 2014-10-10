//============================================================================
// Name        : tema1Ejercicio2.cpp
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
struct mialumno
{
int clase; //numero de registro donde se almacena
char apellido[25];// apellidos del alumno
float nota1;//nota primera eval
float nota2;// idem segunda
float nota3;// idem tercera
};
typedef  float nota;
typedef  int posicion;
typedef multimap<nota, posicion, greater<nota> > multimapa;
int main() {
	mialumno alumno,alumno1;
	multimapa miMapa;
	multimapa::iterator i;
	float notaMedia;

	ifstream entrada("c:\\archivos\\ejer1.txt", ios::in | ios::binary);//ABRIMOS FLUJO ENTRADA

	entrada.read(reinterpret_cast<char*>(&alumno),sizeof(mialumno));//LEEMOS DEL ARCHIVO

			while(!entrada.eof()){

				notaMedia=(float)((alumno.nota1+alumno.nota2+alumno.nota3)/3);//CALCULAMOS MEDIA DE LAS 3 NOTAS
				miMapa.insert(pair<nota,posicion>(notaMedia,alumno.clase));//INSERTAMOS EN EL MAPA LA NOTA PARA PODER LISTAR POR ELLA Y EL NUMERO DE REGISTRO.
				//cout<<alumno.clase<<" "<<alumno.apellido<<" "<<alumno.nota1<<" "<<alumno.nota2<<" "<<alumno.nota3 << " MEDIA: " << notaMedia << endl;
				entrada.read(reinterpret_cast<char*>(&alumno),sizeof(mialumno));

			}
			entrada.close();//CERRAMOS FLUJO ENTRADA
			cout<<"FINAL LECTURA DESDE ARCHIVO"<<endl;
			ifstream entrada1("c:\\archivos\\ejer1.txt", ios::in | ios::binary);//ABRIMOS OTRO FLUJO DE LECTURA
			   for (i = miMapa.begin (); i != miMapa.end (); i ++){//RECORREMOS EL MAPA
					      cout << "Media: "<<(*i).first << " -- ";
					      entrada1.seekg((*i).second*sizeof(mialumno), ios::beg);//NOS POSICIONAMOS
					      entrada1.read(reinterpret_cast<char*>(&alumno1),sizeof(mialumno));//LEEMOS
					      cout<<alumno1.clase<<" "<<alumno1.apellido<<" "<<alumno1.nota1<<" "<<alumno1.nota2<<" "<<alumno1.nota3 <<endl;
					 //  flushall(); vaciar el buffer(flujos)
					   }
				entrada1.close();

	return 0;
}
