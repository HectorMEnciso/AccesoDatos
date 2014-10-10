#include <iostream>
#include <string.h>
#include <stdlib.h>
#include <fstream>
#include <time.h>
#include <stdio.h>
#include <cstdlib>
#define dim 100
using namespace std;
struct mialumno
{
int clase; //numero de registro donde se almacena
char apellido[25];// apellidos del alumno
float nota1;//nota primera eval
float nota2;// idem segunda
float nota3;// idem tercera
};


int main() {
	mialumno alumno,alumno1;
	string nombres[]={"Mauricio","Hector","Davinia","Raúl","Luis"};
	ofstream salida("c:\\archivos\\ejer1.txt", ios::out | ios::binary);//ABRIMOS FLUJO ESCRITURA PARA ARCHIVO BINARIO
	int clave;
	srand(time(NULL));

	for(int x=1;x<=dim;x++){
		alumno.clase=x;
		strcpy(alumno.apellido, nombres[rand()%5].c_str());
		alumno.nota1=rand()%10+1;
		alumno.nota2=rand()%10+1;
		alumno.nota3=rand()%10+1;
		salida.write(reinterpret_cast<char*>(&alumno),sizeof(mialumno));//ESCRIBIMOS LA ESTRUCTURA EN EL ARCHIVO
	}
	salida.close();//CERRAMOS FLUJO ESCRITURA
	ifstream entrada("c:\\archivos\\ejer1.txt", ios::in | ios::binary);//ABRIMOS FLUJO LECTURA PARA ARCHIVO BINARIO
	entrada.read(reinterpret_cast<char*>(&alumno1),sizeof(mialumno));//LEEMOS UNO DEL ARCHIVO
		while(!entrada.eof()){
			cout<<alumno1.clase<<" "<<alumno1.apellido<<" "<<alumno1.nota1<<" "<<alumno1.nota2<<" "<<alumno1.nota3 <<endl;//MOSTRAMOS DATOS
			entrada.read(reinterpret_cast<char*>(&alumno1),sizeof(mialumno));//LEEMOS EL SIGUIENTE
		}

	entrada.close();//se necesita cerrar flujo y volver a abrir para que se posicione el puntero correctamente.
	ifstream entrada1("c:\\archivos\\ejer1.txt", ios::in | ios::binary);//ABRIMOS FLUJO LECTURA PARA ARCHIVO BINARIO
		cout<<"Introduzca numero de registro: ";
		cin>>clave;
		entrada1.seekg((clave-1)*sizeof(mialumno), ios::beg);//NOS POSICIONAMOS EL BYTE CORRESPONDIENTE (NUMERO REGISTRO * TAMAÑO ESTRUCTURA)
		entrada1.read(reinterpret_cast<char*>(&alumno1),sizeof(mialumno));//LEEMOS
		cout<<alumno1.clase<<" "<<alumno1.apellido<<" "<<alumno1.nota1<<" "<<alumno1.nota2<<" "<<alumno1.nota3 <<endl;//MOSTRAMOS POR PANTALLA

	entrada1.close();//CERRAMOS FLUJO LECTURA

	return 0;
}
