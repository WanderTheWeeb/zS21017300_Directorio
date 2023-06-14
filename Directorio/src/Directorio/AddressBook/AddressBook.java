/**
 * Este paquete cuenta con la parte logica del directorio
 * Contiene la estructura de los datos como los metodos de manipulacion de la misma
 */
package Directorio.AddressBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase AddressBook
 * Contiene todos los metodos que permiten manipular el flujo de datos
 * como su contenido.
 * @author Jose Francisco Santes Primo
 * @version Alpha 1.0
 */
public class AddressBook {
    private final List<AddressEntry> entries;

    /**
     * Este metodo crea el arrayList AddressBook
     */
    private AddressBook() {
        entries = new ArrayList<>();
    }

    /**
     * Permite hacer instancia de AddressBook
     * @return La instancia de AddressBook
     */
    public static AddressBook getInstance() {
        return AddressBookHolder.INSTANCE;
    }

    /**
     * Crea la instancia AddressBookHolder
     */
    private static class AddressBookHolder {
        private static final AddressBook INSTANCE = new AddressBook();
    }

    /**
     * Permite añadir un nuevo registro al Directorio
     * @param entry es la instancia de la clase AddressEntry
     */
    public void addEntry(AddressEntry entry) {
        entries.add(entry);
    }

    /**
     * Permite borrar un elemento del Directorio.
     * @param entry es la instancia de la clase AddressEntry
     */
    public void removeEntry(AddressEntry entry) {
        entries.remove(entry);
    }
    /**
     * Permite buscar un registro a traves de su apellido
     * @param lastName Es el apellido por el cual se buscara el registro dentro del Directorio
     * @return Los resultados d la busqueda
     */
    public List<AddressEntry> searchEntryByLastName(String lastName) {
        List<AddressEntry> searchResults = new ArrayList<>();
        for (AddressEntry entry : entries) {
            if (entry.getLastName().startsWith(lastName)) {
                searchResults.add(entry);
            }
        }
        return searchResults;
    }

    /**
     * Despliega todos los registros en orden alfabetico.
     * @return Los registros en orden alfabetico
     */
    public List<AddressEntry> getAllEntriesInAlphabeticalOrder() {
        List<AddressEntry> sortedEntries = new ArrayList<>(entries);
        Collections.sort(sortedEntries);
        return sortedEntries;
    }
}