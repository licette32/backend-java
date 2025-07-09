
// Clase abstracta que representa un producto general
// que es una clase abstracta:
//
// - No se puede instanciar directamente.
// - Puede contener métodos abstractos (sin implementación) y métodos concretos (con implementación).
// - Se utiliza como base para otras clases que heredan de ella.
// - Permite definir un comportamiento común para todas las subclases.
// - Se puede utilizar para definir una jerarquía de clases.
// - Puede contener atributos y métodos que son compartidos por todas las subclases.
// - Se puede utilizar para definir una interfaz común para todas las subclases.
// - Se puede utilizar para definir un comportamiento común para todas las subclases.
// que beneficio tiene una clase abstracta sobre una interfaz:
// - Una clase abstracta puede contener implementación de métodos, mientras que una interfaz solo puede contener la firma de los métodos.
// - Una clase abstracta puede contener atributos, mientras que una interfaz no puede contener atributos.
// - Una clase abstracta puede contener métodos concretos (con implementación), mientras que una interfaz solo puede contener métodos abstractos (sin implementación).
// - Una clase abstracta puede heredar de otra clase abstracta o de una clase concreta, mientras que una interfaz solo puede heredar de otra interfaz.
// - Una clase abstracta puede contener constructores, mientras que una interfaz no puede contener constructores.
// - Una clase abstracta puede contener métodos estáticos, mientras que una interfaz no puede contener métodos estáticos.
// - que es un method static:
// - Un método estático es un método que pertenece a la clase en sí misma, en lugar de a una instancia específica de la clase.
// - Se puede llamar a un método estático sin crear una instancia de la clase.
// - Un método estático no puede acceder a los miembros de instancia (no estáticos) de la clase, ya que no tiene una referencia a una instancia específica.
// - Un método estático se puede utilizar para crear métodos de utilidad que no dependen del estado de una instancia específica de la clase.
// - Un método estático se puede utilizar para crear métodos de fábrica que devuelven instancias de la clase.
public abstract class Producto extends Object{
    public int id;
    private String nombre;
    protected String descripcion;

    public Producto(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return this.descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // Método abstracto que será implementado por las subclases
    public abstract void mostrarDetalle();
}
