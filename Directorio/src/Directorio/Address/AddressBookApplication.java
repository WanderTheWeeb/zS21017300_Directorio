package Directorio.Address;

import Directorio.AddressBook.AddressBook;


/**
 * Clase principal en la cual se llaman los metodos para que el programa comience
 * las interacciones con el usuario
 * @author Jose Francisco Santes Primo
 * @version Alpha 1.0
 */
public class AddressBookApplication {
        private final Menu menu; // Referencia a la clase menu
        private final AddressBook addressBook; // Referencia a la clase AddressBook

    /**
     * Se hace manejo de la parte principal del programa
     */
    public AddressBookApplication() {
            menu = new Menu();
            addressBook = AddressBook.getInstance();
        }

    /**
     * Llama al menu
     */
        public void run() {
        menu.displayMenu();

        }

    /**
     * Crea un objeto AddreessBookAplication y empieza e flujo de datos
     * @param args
     */
    public static void main(String[] args) {
            AddressBookApplication app = new AddressBookApplication();
            app.run();
        }
    }
