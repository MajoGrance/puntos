package py.com.progweb.prueba.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "uso_puntos_detalle")
@NamedQueries({
    @NamedQuery(name = "UsoPuntosDetalle.all", query = "SELECT upd FROM UsoPuntosDetalle upd")})
public class UsoPuntosDetalle {

    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "usoPuntosDetalleSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "usoPuntosDetalleSec", sequenceName = "uso_puntos_detalle_sec", allocationSize = 0)
    private Integer id;

    @JoinColumn(name = "id_cabecera", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private UsoPuntosCabecera cabecera;

    @JoinColumn(name = "id_bolsa_puntos", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
//    @JsonManagedReference
    private BolsaPuntos bolsa;

    @Column(name = "puntos_utilizados")
    @Basic(optional = false)
    private Integer puntosUtilizados;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsoPuntosCabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(UsoPuntosCabecera cabecera) {
        this.cabecera = cabecera;
    }

    public BolsaPuntos getBolsa() {
        return bolsa;
    }

    public void setBolsa(BolsaPuntos bolsa) {
        this.bolsa = bolsa;
    }

    public Integer getPuntosUtilizados() {
        return puntosUtilizados;
    }

    public void setPuntosUtilizados(Integer puntosUtilizados) {
        this.puntosUtilizados = puntosUtilizados;
    }

}
