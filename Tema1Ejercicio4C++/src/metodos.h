/*
 * metodos.h
 *
 *  Created on: 01/10/2014
 *      Author: usuario
 */

#ifndef METODOS_H_
#define METODOS_H_
/*
 * Metodo buscar al que le pasamos el mapa, la clave y el numero registro.Definimos un iterador i en el cual guardamos
 */
void buscar(map<dni, direc, greater<dni> > miMapa, dni alfa, direc& registro) {
// devuelve cero si no existe y != si existe la clave alfa
	map<dni, direc, greater<dni> >::iterator i; //El iterador nos permite movernos a lo largo del mapa.

	i = miMapa.find(alfa); //devuelve iterador al elemento encontrado // miMapa.end() referencia al siguiente del ultimo con lo cual no existe.

	if (i != miMapa.end()) //Si es distinto del final, lo ha encontrado
		registro = (*i).second;
	else
		//Si no, no existe.
		registro = 0;

} // fin de busca
/*
 Metodo encargado de cargar el mapa desde el fichero pclave. Le pasamos el mapa por referencia, pues va a ser modificado. También le pasamos a y b.??
 */
void construye(map<dni, direc, greater<dni> > &miMapa, int a, int b) {
	dni clave;
	direc dir;
	fstream indice("c:\\datos\\indice.txt", ios::out | ios::in); //abrimos archivo indice para leer altas y bajas

	indice >> a; //leemos altas
	indice >> b; //leemos bajas

	indice.close();
	ifstream pclave("c:\\datos\\pclave.txt", ios::in); //abrimos flujo para leer de archivo pclave.

	for (int t = 1; t <= a - b; t++) {
		pclave >> clave; //leemos del flujo pclave y guardamos lo leido en clave
		pclave >> dir; //leemos del flujo pclave y guardamos lo leido endir
		miMapa.insert(pair<dni, direc>(clave, dir)); //insertamos lo leido en el mapa.

	}

	pclave.close(); //cerramo flujo.
}
void leerMapa(map<dni, direc, greater<dni> > miMapa) {
	map<dni, direc, greater<dni> >::iterator inicio;
	fstream leerMapa("c:\\datos\\pclave.txt", ios::in | ios::out);
	if (leerMapa.fail()) {
		cout << "La lectura fallo\n";
	}
	for (inicio = miMapa.begin(); inicio != miMapa.end(); inicio++) {
		cout << "DNI: " << (*inicio).first << " Numero Registro: "
				<< (*inicio).second << endl;
	}
	leerMapa.close();
}
/*
 * Metodo al que le pasamos el mapa miMapa.
 * */
void guarda(map<dni, direc, greater<dni> > miMapa) {
	map<dni, direc, greater<dni> >::iterator inicio; //definimos iterador inicio
	inicio = miMapa.begin(); //begin()iterador que referencia al primer elemento.

	ofstream pclave("c:\\datos\\pclave.txt", ios::out); //abrimos flujo escritura para escribir en el archivo pclave.

	for (inicio = miMapa.begin(); inicio != miMapa.end(); inicio++) { //recorremos miMapa de principio a fin.
		pclave << (*inicio).first; //escribimos la parte first(un dni en esta caso)
		pclave << " "; //escribimos tambien un espacio en blanco para hacer una lectura correcta en su momento.
		pclave << (*inicio).second; //escribimos parte second(numero registro.)
		pclave << endl;
	}
	pclave.close(); //cerramos flujo escritura.
} // fin de guarda

void altas(map<dni, direc, greater<dni> > &miMapa, int a, int b, int hueco,
		vector<int> v) {

	map<dni, direc, greater<dni> >::iterator pos;
	dni busc;
	string nombre, apellidos;

	direc numreg;
	direc registro;
	persona tmp;

	fstream datos1("c:\\datos\\datos.txt", ios::out | ios::in | ios::binary);
	fstream indice("c:\\datos\\indice.txt", ios::out | ios::in); //abrimos archivo indice para leer altas y bajas

	indice >> a; //leemos altas
	indice >> b; //leemos bajas

	indice.close();

	if (b != 0) {
		fstream lapila("c:\\datos\\lapila.txt", ios::out | ios::in);
		for (int z = 1; z <= b; z++) {
			lapila >> hueco;
			v.push_back(hueco);
		}
		lapila.close();
	}

	cout << "Introduzca DNI: ";
	cin >> busc;

	while (busc != "0") {
		buscar(miMapa, busc, registro);
		if (registro != 0)
			cout << "El DNI introducido ya existe.\n";
		else {
			strcpy(tmp.dni, busc.c_str());
			cout << "Introduzca nombre: ";
			fflush (stdin);
			getline(cin, nombre);
			strcpy(tmp.nombre, nombre.c_str()); //insertamos datos en la struct
			cout << "Introduzca apellido: ";
			getline(cin, apellidos);
			strcpy(tmp.apellidos, apellidos.c_str()); //insertamos datos en la struct

			// si existen datos en la pila de huecos se aprovechan
			// antes de extender el archvio
			if (v.size() != 0) {//size nos da el numero de elementos del vector.
				b--;			// restamos 1 a b porque aprovechamos una baja
				numreg = v.back();		//el ultimos valor es numero registro
				v.pop_back();			//borramos ultimo elemento del vector
			} else
			// sino se extiende el archivo con el registro siguiente al ultimo(marcado por a)
			{
				a++;
				numreg = a;
			}
			// se inserta en el mapa la pareja clave(busc), numereg(dirección del registro)
			//inserta (m,busc,numreg);

			miMapa.insert(pair<dni, direc>(busc, numreg));

			datos1.seekp((numreg - 1) * sizeof(persona), ios::beg);
			datos1.write(reinterpret_cast<char*>(&tmp), sizeof(persona)); //ESCRIBIMOS LA ESTRUCTURA EN EL ARCHIVO

			cout << "registrado en :" << numreg << endl;

		}
		busc = "0";
	} // se repite el proceso para más claves

	guarda(miMapa);

	ofstream indice1("c:\\datos\\indice.txt", ios::out);

	indice1 << a;
	indice1 << " ";
	indice1 << b;

	indice1.close();

	ofstream lapila1("c:\\datos\\lapila.txt", ios::out);

	for (int z = 1; z <= b; z++) {
		lapila1 << v.back();
		v.pop_back();
	}

	lapila1.close();
	datos1.close();
	fstream prueba("c:\\datos\\datos.txt", ios::in | ios::binary);
	prueba.read(reinterpret_cast<char*>(&tmp), sizeof(persona));
	while (!prueba.eof()) {
		cout << "dni: " << tmp.dni << " nombre: " << tmp.nombre
				<< "  apellidos: " << tmp.apellidos << endl;
		prueba.read(reinterpret_cast<char*>(&tmp), sizeof(persona));
	}
	prueba.close();
}

void Bajas(map<dni, direc, greater<dni> > &miMapa, int a, int b, int hueco,
		vector<int> v) {
	map<dni, direc, greater<dni> >::iterator pos; //definimos iterador pos
	dni busc;
	string nombre, apellidos;
	direc registro;
	persona tmp;
	fstream datos1("c:\\datos\\datos.txt", ios::out | ios::in | ios::binary);

	fstream indice("c:\\datos\\indice.txt", ios::out | ios::in); //abrimos archivo indice para leer altas y bajas
	indice >> a; //leemos altas
	indice >> b; //leemos bajas
	indice.close();

	if (b != 0) { //si hay bajas vamos a aprovechar esa hueco para la siguiente alta.
		fstream lapila("c:\\datos\\lapila.txt", ios::out | ios::in); //leemos de fichero pila para saber que numeros de registros son los que estan libres
		for (int z = 1; z <= b; z++) { //lee tantas bajas como haya
			lapila >> hueco; //lee el elemento
			v.push_back(hueco); //añade el elemento o elementos al vector.
		}
		lapila.close();
	}

	cout << "Introduce DNI a borrar: ";
	cin >> busc;

	while (busc != "0") {

		buscar(miMapa, busc, registro);
		if (registro == 0)
			cout << "El DNI introducido no existe\n";
		else {
			datos1.seekg((registro - 1) * sizeof(persona), ios::beg);
			datos1.read(reinterpret_cast<char*>(&tmp), sizeof(persona));
			cout << "=========================================" << endl;
			cout << "DNI: " << tmp.dni << endl;
			cout << "NOMBRE: " << tmp.nombre << endl;
			cout << "APELLIDOS: " << tmp.apellidos << endl;
			cout << "=========================================" << endl;
			pos = miMapa.find(busc); //devuelve iterador al elementos encontrado
			miMapa.erase(pos); //con ese iterador borramos
			cout << "DNI :" << busc << ": con registro :" << registro
					<< " ha sido borrado con éxito." << endl;
			b++; //incrementas las bajas en uno pues acabamos de borrar
			v.push_back(registro); //añadimos al vector de bajas el nuevo registro libre para ser aprovechado
		}
		busc = "0"; //forzamos salida bucle
	}
	guarda(miMapa); //guardamos el mapa en el archivo.

	ofstream indice1("c:\\datos\\indice.txt", ios::out); //abrimos flujo para escribir en el archivo indice

	indice1 << a; //escribimos altas
	indice1 << " ";
	indice1 << b; //escribimo el numero de bajas

	indice1.close();

	ofstream lapila1("c:\\datos\\lapila.txt", ios::out);

	for (int z = 1; z <= b; z++) { //lee tantas bajas como haya
		lapila1 << v.back(); //escribe ultimo valor del vector
		lapila1 << " "; //escribimos espacio
		v.pop_back(); //borramos ultimo elemento del vector.
	}

	lapila1.close();
	datos1.close();

}
/*
 * metodo que lista los datos de la estructura recorriendo el mapa de principio a fin.
 * */
void Listado(map<dni, direc, greater<dni> > miMapa) {
	map<dni, direc, greater<dni> >::iterator i;
	persona tmp;
	i = miMapa.begin(); //begin()iterador que referencia al primer elemento.

	ifstream datos2("c:\\datos\\datos.txt", ios::in | ios::binary);

	if (datos2.fail()) {
		cout << "Operacion lectura fallada.\n";
	}
	for (i = miMapa.begin(); i != miMapa.end(); i++) {
		cout << "mapa: " << (*i).second << endl;

		datos2.seekg(((*i).second - 1) * sizeof(persona), ios::beg); //nos posicionamos
		datos2.read(reinterpret_cast<char*>(&tmp), sizeof(persona)); //leemos
		cout << "=========================================" << endl; //mostramos
		cout << "DNI: " << tmp.dni << endl;
		cout << "NOMBRE: " << tmp.nombre << endl;
		cout << "APELLIDOS: " << tmp.apellidos << endl;
		cout << "=========================================" << endl;
	}
	datos2.close();
}
void comprobarFichero() {

	fstream entrada;
	entrada.open("c:\\datos\\datos.txt", ios::in | ios::binary);
	if (!entrada) {
		cout << "No se puede abrir el archivo" << endl;
		entrada.close();

		entrada.open("c:\\datos\\datos.txt", ios::out | ios::binary);
		entrada.close();
		entrada.open("c:\\datos\\datos.txt", ios::in | ios::binary);
	}

	ifstream entrada2;
	entrada2.open("C:\\datos\\indice.txt", ios::in);
	if (!entrada2) {

		cout << "No se puede abrir el archivo" << endl;
		entrada2.close();
		ofstream entrada2;
		entrada2.open("C:\\datos\\indice.txt", ios::out);
		entrada2 << 0;
		entrada2 << " ";
		entrada2 << 0;
		entrada2.close();
		entrada2.open("C:\\datos\\indice.txt", ios::in);
	}
	fstream entrada3;
	entrada3.open("C:\\datos\\lapila.txt", ios::in);
	if (!entrada3) {
		cout << "No se puede abrir el archivo" << endl;
		entrada3.close();
		entrada3.open("C:\\datos\\lapila.txt", ios::out);
		entrada3 << 0;
		entrada3.close();
		entrada3.open("C:\\datos\\lapila.txt", ios::in);
	}

	fstream entrada4;
	entrada4.open("C:\\datos\\pclave.txt", ios::in);
	if (!entrada4) {
		cout << "No se puede abrir el archivo" << endl;
		entrada4.close();
		entrada4.open("C:\\datos\\pclave.txt", ios::out);
		entrada4 << 0;
		entrada4.close();
		entrada4.open("C:\\datos\\pclave.txt", ios::in);
	}

}

#endif /* METODOS_H_ */
