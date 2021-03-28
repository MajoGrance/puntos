package py.com.progweb.prueba.model;

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

@Entity
@Table(name = "concepto_puntos")
@NamedQueries({
    @NamedQuery(
            name = "ConceptoPuntos.all", 
            query = "SELECT cp FROM ConceptoPuntos cp"),
    @NamedQuery(
            name = "ConceptoPuntos.byId", 
            query = "SELECT cp FROM ConceptoPuntos cp WHERE cp.id=:idConcepto")
})
public class ConceptoPuntos {

    @Id
    @Column(name = "id")
    @Basic(optional = false)
    @GeneratedValue(generator = "conceptoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "conceptoSec", sequenceName = "concepto_puntos_sec", allocationSize = 0)
    private Integer id;

    @Column(name = "descripcion")
    @Basic(optional = false)
    private String descripcion;

    @Column(name = "puntos_requeridos")
    @Basic(optional = false)
    private Integer puntosRequeridos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

}
