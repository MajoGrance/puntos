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
@Table(name = "regla_asignacion_puntos")
@NamedQueries({
    @NamedQuery(name = "ReglaAsignacionPuntos.all", query = "SELECT rap FROM ReglaAsignacionPuntos rap")
    ,
    @NamedQuery(name = "ReglaAsignacionPuntos.cantidadPuntos",
            query = "SELECT rap FROM ReglaAsignacionPuntos rap where :monto >= rap.limiteInferior and :monto <= rap.limiteSuperior")})
public class ReglaAsignacionPuntos {

    public ReglaAsignacionPuntos() {
    }

    @Id
    @Column(name = "id")
    @Basic(optional = false)
    @GeneratedValue(generator = "reglaAsignacionPuntosSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reglaAsignacionPuntosSec", sequenceName = "regla_asignacion_puntos_sec", allocationSize = 0)
    private Integer id;

    @Column(name = "limite_inferior")
    @Basic(optional = false)
    private Integer limiteInferior;

    @Column(name = "limite_superior")
    @Basic(optional = false)
    private Integer limiteSuperior;

    @Column(name = "monto_equivalencia")
    @Basic(optional = false)
    private Integer montoEquivalencia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Integer getMontoEquivalencia() {
        return montoEquivalencia;
    }

    public void setMontoEquivalencia(Integer montoEquivalencia) {
        this.montoEquivalencia = montoEquivalencia;
    }

    @Override
    public String toString() {
        return "ReglaAsignacionPuntos{" + "id=" + id + ", limiteInferior=" + limiteInferior + ", limiteSuperior=" + limiteSuperior + ", montoEquivalencia=" + montoEquivalencia + '}';
    }

}
