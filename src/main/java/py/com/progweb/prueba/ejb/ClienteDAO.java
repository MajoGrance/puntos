package py.com.progweb.prueba.ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import py.com.progweb.prueba.model.Cliente;

@Stateless
public class ClienteDAO {
    private static final Logger LOGGER = LogManager.getLogger(ClienteDAO.class);
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
	public List<Cliente> listarByCumple(String cumple) throws ParseException {
        LOGGER.info("CUMPLE STRING: {}", cumple);
        Date fechaCumple = new SimpleDateFormat("dd/MM/yyyy").parse(cumple);
         LOGGER.info("FECHA CUMPLE�OS: {}", fechaCumple);
        Query q = this.em.createNamedQuery("Cliente.byCumple");
        
        
        List<Cliente> todos = (List<Cliente>) q.getResultList();
        
        LOGGER.info("LISTA DE TODOS: [{}]", todos);
        List<Cliente> listaCumple = new LinkedList<Cliente>();
        Date fechaNacimiento;
        
        for (Cliente cliente : todos) {
             fechaNacimiento = cliente.getFechaNacimiento();
             
            String fechaNacimientoString = new SimpleDateFormat("dd/MM").format(fechaNacimiento);
            String fechaCumpleString = new SimpleDateFormat("dd/MM").format(fechaCumple);
            if (fechaNacimiento != null && fechaCumple != null 
                    && fechaNacimientoString.equals(fechaCumpleString)){
                listaCumple.add(cliente);
            }
        }
        LOGGER.info("LISTA DE CUMPLEA�OS: [{}]", listaCumple);
        
        return listaCumple;
    }

    public Cliente getById(Integer idCliente) {
        Cliente ret = this.em.find(Cliente.class, idCliente);
        return ret;
    }
    
}
