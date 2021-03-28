package py.com.progweb.prueba.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import py.com.progweb.prueba.model.ReglaAsignacionPuntos;

@Stateless
public class ReglaAsignacionPuntosDAO {

    private static final Logger LOGGER = LogManager.getLogger(ReglaAsignacionPuntosDAO.class);
    @PersistenceContext(unitName = "puntosPU")
    private EntityManager em;

    public void agregar(ReglaAsignacionPuntos entity) {
        this.em.persist(entity);
    }

    @SuppressWarnings("unchecked")
	public List<ReglaAsignacionPuntos> listar() {
        Query q = this.em.createNamedQuery("ReglaAsignacionPuntos.all");
        return (List<ReglaAsignacionPuntos>) q.getResultList();
    }

    public void eliminar(Integer regla_asignacion_puntosId) {
        ReglaAsignacionPuntos regla = em.find(ReglaAsignacionPuntos.class, regla_asignacion_puntosId);
        this.em.remove(regla);
    }

    public void actualizar(ReglaAsignacionPuntos regla) {
        this.em.merge(regla);
    }

    public Map<String, Object> getCantidadDePuntos(Double monto) {
        LOGGER.info("IN: [{}]", monto);
        Query q = this.em.createNamedQuery("ReglaAsignacionPuntos.cantidadPuntos");
        q.setParameter("monto", monto.intValue());
        ReglaAsignacionPuntos regla = (ReglaAsignacionPuntos) q.getSingleResult();
        LOGGER.info("regla: [{}]", regla);
        Map<String, Object> m = new HashMap<String, Object>();
        if (regla == null) {
            m.put("estado", -1);
            m.put("mensaje", "No se encontro ninguna regla que este en el rango del monto");
            return m;
        }
        m.put("estado", 0);
        m.put("mensaje", "Exito");
        LOGGER.info("monto equivalencia: [{}]", regla.getMontoEquivalencia().doubleValue());
        Double puntos = monto / regla.getMontoEquivalencia().doubleValue();
        LOGGER.info("puntos: [{}]", puntos);
        m.put("puntos", puntos.intValue());

        return m;
    }
}
