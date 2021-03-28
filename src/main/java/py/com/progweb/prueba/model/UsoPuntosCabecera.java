package py.com.progweb.prueba.model;

import java.util.Date;
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
import javax.persistence.Temporal;

@Entity
@Table(name = "uso_puntos_cabecera")
@NamedQueries({
    @NamedQuery(
            name = "UsoPuntosCabecera.all", 
            query = "SELECT upc FROM UsoPuntosCabecera upc"),
    @NamedQuery(
            name = "UsoPuntosCabecera.byConcepto", 
            query = "SELECT upc FROM UsoPuntosCabecera upc WHERE upc.concepto=:idConcepto"),
    @NamedQuery(
            name = "UsoPuntosCabecera.byCliente", 
            query = "SELECT upc FROM UsoPuntosCabecera upc WHERE upc.cliente=:idCliente"),
    @NamedQuery(
            name = "UsoPuntosCabecera.byFecha", 
            query = "SELECT upc FROM UsoPuntosCabecera upc WHERE upc.fecha BETWEEN :fechaInicio and :fechaFin"),
})
public class UsoPuntosCabecera {

    @Id
    @Column(name = "id")
    @Basic(optional = false)
    @GeneratedValue(generator = "usoPuntosCabeceraSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "usoPuntosCabeceraSec", sequenceName = "uso_puntos_cabecera_sec", allocationSize = 0)
    private Integer id;

    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Cliente cliente;

    @Column(name = "puntos_utilizados")
    @Basic(optional = false)
    private Integer puntosUtilizados;

    @Column(name = "fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    @Basic(optional = false)
    private Date fecha;

    @Column(name = "concepto")
    @Basic(optional = false)
    private Integer concepto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getPuntosUtilizados() {
        return puntosUtilizados;
    }

    public void setPuntosUtilizados(Integer puntosUtilizados) {
        this.puntosUtilizados = puntosUtilizados;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getConcepto() {
        return concepto;
    }

    public void setConcepto(Integer concepto) {
        this.concepto = concepto;
    }

}
