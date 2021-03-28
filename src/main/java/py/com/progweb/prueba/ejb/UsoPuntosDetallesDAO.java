package py.com.progweb.prueba.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.UsoPuntosCabecera;
import py.com.progweb.prueba.model.UsoPuntosDetalle;

@Stateless
public class UsoPuntosDetallesDAO {
    
    @Inject
    BolsaPuntosDAO bolsaBean;
    
    @PersistenceContext(unitName = "puntosPU")
    private EntityManager em;

    public void agregar(UsoPuntosDetalle entity) {
        this.em.persist(entity);
    }

    @SuppressWarnings("unchecked")
	public List<UsoPuntosDetalle> listar() {
        Query q = this.em.createNamedQuery("UsoPuntosDetalle.all");
        return (List<UsoPuntosDetalle>) q.getResultList();
    }

    public void eliminar(Integer uso_puntos_detalleId) {
        UsoPuntosDetalle uso = em.find(UsoPuntosDetalle.class, uso_puntos_detalleId);
        this.em.remove(uso);
    }
    
    
    public void actualizar(UsoPuntosDetalle uso) {
        this.em.merge(uso);    
    }

    public void agregarDetallesDeCabecera(UsoPuntosCabecera cabecera) {
        int montoTotal = cabecera.getPuntosUtilizados();
        
        List<BolsaPuntos> bolsas = bolsaBean.listarByClienteEnOrden(cabecera.getCliente().getId());
        int i = 0, montoBolsa;
        BolsaPuntos bolsaActual;
        
        while (montoTotal>0 && i < bolsas.size()){
            UsoPuntosDetalle usoDetalle = new UsoPuntosDetalle();
            usoDetalle.setCabecera(cabecera);
            bolsaActual = bolsas.get(i++);
            usoDetalle.setBolsa(bolsaActual);
            montoBolsa = bolsaActual.getPuntosSaldo();
            
            if ( montoBolsa >= montoTotal ){
                usoDetalle.setPuntosUtilizados(montoTotal);
                bolsaActual.setPuntosUtilizados(bolsaActual.getPuntosUtilizados() + montoTotal);
                bolsaActual.setPuntosSaldo(bolsaActual.getPuntosSaldo() - montoTotal);
                bolsaBean.actualizar(bolsaActual);
                montoTotal = 0;
                this.agregar(usoDetalle);
            }else if ( montoBolsa > 0 ){
                usoDetalle.setPuntosUtilizados(montoBolsa);
                bolsaActual.setPuntosUtilizados(bolsaActual.getPuntosAsignados());
                bolsaActual.setPuntosSaldo(0);
                bolsaBean.actualizar(bolsaActual);
                montoTotal = montoTotal - montoBolsa;
                this.agregar(usoDetalle);
            }
        }
    }

}
