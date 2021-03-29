package py.com.progweb.prueba.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.progweb.prueba.model.ReglaAsignacionPuntos;

@Stateless
public class ReglaAsignacionPuntosDAO {

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

    @SuppressWarnings("unchecked")
	public Map<String, Object> getCantidadDePuntos(Double monto) {
        Query q = this.em.createNamedQuery("ReglaAsignacionPuntos.cantidadPuntos");
        q.setParameter("monto", monto.intValue());
        Map<String, Object> m = new HashMap<String, Object>();
		List<ReglaAsignacionPuntos> reglas = (List<ReglaAsignacionPuntos>) q.getResultList();
		ReglaAsignacionPuntos regla;
        if (reglas.isEmpty()) {
        	m.put("estado", -1);
            m.put("mensaje", "No se encontro ninguna regla que este en el rango del monto");
            return m;
        } else {
        	regla = reglas.get(0);
        }
        m.put("estado", 0);
        m.put("mensaje", "Exito");
        Double puntos = monto / regla.getMontoEquivalencia().doubleValue();
        m.put("puntos", puntos.intValue());

        return m;
    }
}
