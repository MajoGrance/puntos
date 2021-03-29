package py.com.progweb.prueba.ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.progweb.prueba.model.Cliente;

@Stateless
public class ClienteDAO {

    @PersistenceContext(unitName = "puntosPU")
    private EntityManager em;

    public void agregar(Cliente entity) {
        this.em.persist(entity);
    }

    @SuppressWarnings("unchecked")
	public List<Cliente> listar() {
        Query q = this.em.createNamedQuery("Cliente.all");
        return (List<Cliente>) q.getResultList();
    }

    public void eliminar(Integer clienteId) {
        Cliente c = em.find(Cliente.class, clienteId);
        this.em.remove(c);
    }

    public void actualizar(Cliente cliente) {
        this.em.merge(cliente);
    }
    
    @SuppressWarnings("unchecked")
	public List<Cliente> listarByNombre(String nombreCliente) {
        Query q = this.em.createNamedQuery("Cliente.byNombre");
        return (List<Cliente>) q
                .setParameter("nombreCliente", '%' + nombreCliente + '%')
                .getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Cliente> listarByApellido(String nombreApellido) {
        Query q = this.em.createNamedQuery("Cliente.byApellido");
        return (List<Cliente>) q
                .setParameter("nombreApellido", '%' + nombreApellido + '%')
                .getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Cliente> listarByCumple(String desdeFecha, String hastaFecha) throws ParseException {
        Query q = this.em.createNamedQuery("Cliente.byCumple");
        Date fechaInicioDate = new SimpleDateFormat("yyyy-MM-dd").parse(desdeFecha);
        Date fechaFinDate = new SimpleDateFormat("yyyy-MM-dd").parse(hastaFecha);
        
        return (List<Cliente>) 
                q.setParameter("desdeFecha", fechaInicioDate)
                .setParameter("hastaFecha", fechaFinDate)
                .getResultList();
        
    }

    public Cliente getById(Integer idCliente) {
        Cliente ret = this.em.find(Cliente.class, idCliente);
        return ret;
    }
    
}
