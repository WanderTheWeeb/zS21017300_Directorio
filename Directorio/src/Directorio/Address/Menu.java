/**
 * Este paquete contiene las clases que permiten al usuario manipular el directorio
 */
package Directorio.Address;

import Directorio.AddressBook.AddressBook;
import Directorio.AddressBook.AddressEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que permite la manipulacion de datos del directorio al usuario
 * @author Jose Francisco Santes Primo
 * @version Alpha 1.0
 */
public class Menu {
        Scanner sc = new Scanner(System.in);
        private final AddressBook ADDRESSBOOK;

        /**
         * Instancia de la clase AddressBook (Singleton)
         */
        public Menu(){
                ADDRESSBOOK = AddressBook.getInstance();
        }

        /**
         * Carga los datos desde un archivo CSV y los agrega a la libreta de direcciones.
         *
         * @param fileName El nombre del archivo CSV a leer.
         */
        private void loadData(String fileName) {
                try {
                        BufferedReader reader = new BufferedReader(new FileReader(fileName));
                        String line;

                        reader.readLine();

                        // Leer cada línea del archivo CSV
                        while ((line = reader.readLine()) != null) {
                                String[] data = line.split(",");

                                // Agregar los datos directamente a la libreta de direcciones
                                ADDRESSBOOK.addEntry(new AddressEntry(
                                        data[0], // Nombre
                                        data[1], // Apellido
                                        data[2], // Dirección
                                        data[3], // Ciudad
                                        data[4], // Estado
                                        Integer.parseInt(data[5]), // Código Postal
                                        data[6], // Email
                                        data[7] // Número de teléfono
                                ));
                        }

                        reader.close();

                        System.out.println("Las entradas se cargaron correctamente desde el archivo CSV.");
                } catch (IOException e) {
                        System.out.println("Error: No se pudo leer el archivo.");
                } catch (NumberFormatException e) {
                        System.out.println("Error: Se esperaba un número entero en el campo Código Postal.");
                }
        }

        /**
         * Inserta un nuevo registro al AddressBook
         */
        private void insertData() {
                Scanner sc = new Scanner(System.in);

                System.out.println("Agregar una entrada:");
                System.out.print("Nombre: ");
                String firstName = sc.nextLine();

                System.out.print("Apellido: ");
                String lastName = sc.nextLine();

                System.out.print("Dirección: ");
                String address = sc.nextLine();

                System.out.print("Ciudad: ");
                String city = sc.nextLine();

                System.out.print("Estado: ");
                String state = sc.nextLine();

                System.out.print("Código Postal: ");
                int postalCode = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea pendiente después de la entrada numérica

                System.out.print("Número de teléfono: ");
                String phoneNumber = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                AddressEntry newEntry = new AddressEntry(firstName, lastName, address, city, state, postalCode, phoneNumber, email);
                ADDRESSBOOK.addEntry(newEntry);

                System.out.println("La entrada se ha agregado correctamente.");
        }

        /**
         * Despliega los datos almacenados en AddressBook
         */
        private void showData(){
                List<AddressEntry> entries = ADDRESSBOOK.getAllEntriesInAlphabeticalOrder();
                for (AddressEntry entry : entries) {
                        System.out.println(entry+"\n========================");
                }
        }

        /**
         * Elimina un registro almacenado en AddressBook, a traves de la busqueda de su apellido
         */
        private void deleteData(){
                System.out.println("Eliminar");
                System.out.print("Ingrese el apellido de la entrada a eliminar: ");
                String lastNameToRemove = sc.nextLine();
                List<AddressEntry> searchResults = ADDRESSBOOK.searchEntryByLastName(lastNameToRemove);

                if (searchResults.isEmpty()) {
                        System.out.println("No se encontraron entradas con el apellido especificado.");
                } else {
                        System.out.println("Se encontraron las siguientes entradas:");
                        for (int i = 0; i < searchResults.size(); i++) {
                                AddressEntry entry = searchResults.get(i);
                                System.out.println((i + 1) + ". " + entry);
                        }

                        System.out.print("Ingrese el número de la entrada a eliminar: ");
                        int entryIndex = sc.nextInt();
                        sc.nextLine(); // Consumir el salto de línea pendiente después de la entrada numérica

                        if (entryIndex >= 1 && entryIndex <= searchResults.size()) {
                                AddressEntry entryToRemove = searchResults.get(entryIndex - 1);
                                ADDRESSBOOK.removeEntry(entryToRemove);
                                System.out.println("La entrada se ha eliminado correctamente.");
                        } else {
                                System.out.println("El número de entrada ingresado no es válido.");
                        }
                }
        }

        /**
         * Busca un registro almacenado en AddressBook, a traves de buscarlo por su apellido
         */
        private void searchData(){
                System.out.println("Buscar");
                System.out.print("Ingrese el apellido a buscar: ");
                String searchLastName = sc.nextLine();
                List<AddressEntry> searchResults = ADDRESSBOOK.searchEntryByLastName(searchLastName);

                if (searchResults.isEmpty()) {
                        System.out.println("No se encontraron entradas con el apellido especificado.");
                } else {
                        System.out.println("Se encontraron las siguientes entradas:");
                        for (AddressEntry entry : searchResults) {
                                System.out.println(entry);
                        }
                }
        }

        /**
         * Permite guarda los datos en un archivo CVS
         * @param fileName El nombre del archivo CVS , donde se almacenaran los datos.
         */
        private void saveData(String fileName){
                        try {
                                FileWriter writer = new FileWriter(fileName);

                                // Escribir encabezados de columna
                                writer.write("Nombre,Apellido,Dirección,Ciudad,Estado,Código Postal,Email,Número de teléfono\n");

                                // Obtener todas las entradas en orden alfabético
                                List<AddressEntry> entries = ADDRESSBOOK.getAllEntriesInAlphabeticalOrder();

                                // Escribir cada entrada en una línea separada
                                for (AddressEntry entry : entries) {
                                        writer.write(
                                                entry.getFirstName() + "," +
                                                        entry.getLastName() + "," +
                                                        entry.getStreet() + "," +
                                                        entry.getCity() + "," +
                                                        entry.getState() + "," +
                                                        entry.getZip() + "," +
                                                        entry.getEmail() + "," +
                                                        entry.getPhoneNumber() + "\n"
                                        );
                                }

                                writer.close();

                                System.out.println("Las entradas se guardaron correctamente en el archivo CSV.");
                        } catch (IOException e) {
                                System.out.println("Error: No se pudo escribir en el archivo.");
                        }
                }
        /**
         * Muestra el menú de opciones y maneja las interacciones con el usuario.
         */
        private void showMenu(){
                System.out.println("========== MENU ==========");
                System.out.println("a) Cargar entradas desde un archivo");
                System.out.println("b) Agregar una entrada");
                System.out.println("c) Eliminar una entrada");
                System.out.println("d) Buscar una entrada");
                System.out.println("e) Mostrar todas las entradas");
                System.out.println("f) Guardar datos en un CSV");
                System.out.println("g) Salir");
                System.out.println("========================");
                System.out.print("Ingrese su opción: ");
        }

        /**
         *Imprime el menu y permite al usuario interactuar con el Directorio.
         */
        public void displayMenu() {
                boolean exit = false;
                String fileName;

                while (!exit) {
                        showMenu();
                        String choice = sc.nextLine().toLowerCase();

                        switch (choice) {
                                case "a":
                                        System.out.print("Ingrese el nombre del archivo CSV para cargar los Datos: ");
                                        fileName = sc.nextLine();
                                        loadData(fileName+".csv");
                                        break;
                                case "b":
                                        insertData();
                                        break;
                                case "c":
                                        deleteData();
                                        break;
                                case "d":
                                        searchData();
                                        break;
                                case "e":
                                        showData();
                                        break;
                                case "f":
                                        System.out.println("Guardar");
                                        System.out.print("Ingrese el nombre del archivo CSV para guardar las entradas: ");
                                        fileName = sc.nextLine();
                                        saveData(fileName+".csv");
                                        break;
                                case "g":
                                        System.out.println("¡Gracias por usar el sistema!");
                                        exit = true;
                                        break;
                                default:
                                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                        }

                        if (!exit) {
                                System.out.println("Presione Enter para continuar...");
                                sc.nextLine();
                        }
                }
        }
}
