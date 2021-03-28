package py.com.progweb.prueba.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.progweb.prueba.model.VencimientoPuntos;

@Stateless
public class VencimientoPuntosDAO {

    @PersistenceContext(unitName = "puntosPU")
    private EntityManager em;

    public void agregar(VencimientoPuntos entity) {
        this.em.persist(entity);
    }

    @SuppressWarnings("unchecked")
	public List<VencimientoPuntos> listar() {
        Query q = this.em.createNamedQuery("VencimientoPuntos.all");
        return (List<VencimientoPuntos>) q.getResultList();
    }

    public void eliminar(Integer vencimiento_puntosId) {
        VencimientoPuntos venc = em.find(VencimientoPuntos.class, vencimiento_puntosId);
        this.em.remove(venc);
    }

    public void actualizar(VencimientoPuntos venc) {
        this.em.merge(venc);
    }
    
    public Integer getDiasCaducidad() {
        Query q = this.em.createNamedQuery("VencimientoPuntos.duracionByRango");
        Date actual = new Date();
        return (Integer) q.setParameter("fechaAsignacion", actual).getSingleResult();
    }
}
