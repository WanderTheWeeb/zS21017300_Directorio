package Directorio.AddressBook;

/**
 * Clase que permite crear nuevos objetos para almacenar en el directorio
 * @author Jose Francisco Santes Primo
 * @version Alpha 1.0
 */
public class AddressEntry implements Comparable<AddressEntry> {
    protected String firstName; // Nombre de la persona.
    protected String lastName; // Apellido de la persona.
    protected String street; // Calle de la persona.
    protected String city; // Ciudad de la persona.
    protected String state; // Estado de la persona.
    protected int zip; // Codigo postal de la persona.
    protected String email; // Correo electronico de la persona.
    protected String phoneNumber; // Numero de telefono de la persona

    /**
     * Constructor de la clase AddressEntry
     * @param firstName Nombre de la persona
     * @param lastName Apellido de la persona
     * @param street Calle de la persona
     * @param city Ciudad de la persona
     * @param state Estado de la persona
     * @param zip Codigo postal de la persona
     * @param email Correo electronico de la persona
     * @param phoneNumber Numero de telefono de la persona
     */
    public AddressEntry(String firstName, String lastName, String street, String city,
                        String state, int zip, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\n" +
                "Street: " + street + "\n" +
                "City: " + city + "\n" +
                "State: " + state + "\n" +
                "Postal Code: " + zip + "\n" +
                "Email: " + email + "\n" +
                "Phone Number: " + phoneNumber;
    }

    @Override
    public int compareTo(AddressEntry other) {
        return lastName.compareToIgnoreCase(other.getLastName());
    }
}