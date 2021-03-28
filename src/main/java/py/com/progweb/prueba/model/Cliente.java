package py.com.progweb.prueba.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(
            name = "Cliente.all",
            query = "SELECT c FROM Cliente c"),
    @NamedQuery(
            name = "Cliente.byNombre",
            query = "SELECT c FROM Cliente c WHERE c.nombre LIKE :nombreCliente"),
    @NamedQuery(
            name = "Cliente.byApellido",
            query = "SELECT c FROM Cliente c WHERE c.apellido LIKE :nombreApellido"),
    @NamedQuery(
            name = "Cliente.byCumple",
            query = "SELECT c FROM Cliente c ")
})  
public class Cliente {

    @Id
    @Column(name = "id")
    @Basic(optional = false)
    @GeneratedValue(generator = "clienteSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clienteSec", sequenceName = "cliente_sec", allocationSize = 0)
    private Integer id;

    @Column(name = "nombre", length = 50)
    @Basic(optional = false)
    private String nombre;

    @Column(name = "apellido", length = 50)
    @Basic(optional = false)
    private String apellido;

    @Column(name = "numero_documento")
    @Basic(optional = false)
    private Integer numeroDocumento;

    @Column(name = "tipo_documento", length = 50)
    @Basic(optional = false)
    private String tipoDocumento;

    @Column(name = "nacionalidad", length = 50)
    private String nacionalidad;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", numeroDocumento=" + numeroDocumento + ", tipoDocumento=" + tipoDocumento + ", nacionalidad=" + nacionalidad + ", email=" + email + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
}
