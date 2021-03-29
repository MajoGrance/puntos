package py.com.progweb.prueba.ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.ConceptoPuntos;
import py.com.progweb.prueba.model.UsoPuntosCabecera;

@Stateless
public class UsoPuntosCabeceraDAO {

    @Inject
    ConceptoPuntosDAO conceptoDao;
    
    @Inject
    UsoPuntosDetallesDAO usoDetalleDao;
    
    @Inject
    BolsaPuntosDAO bolsaDao;
    
    @Inject
    EmailAsyncDAO emailDao;
    
    @Inject
    ClienteDAO clienteDao;
    
    @PersistenceContext(unitName = "puntosPU")
    private EntityManager em;

    public void agregar(UsoPuntosCabecera entity) {
        this.em.persist(entity);
    }

    @SuppressWarnings("unchecked")
	public List<UsoPuntosCabecera> listar() {
        Query q = this.em.createNamedQuery("UsoPuntosCabecera.all");
        return (List<UsoPuntosCabecera>) q.getResultList();
    }

    public void eliminar(Integer uso_puntos_cabeceraId) {
        UsoPuntosCabecera uso = em.find(UsoPuntosCabecera.class, uso_puntos_cabeceraId);
        this.em.remove(uso);
    }

    public void actualizar(UsoPuntosCabecera uso) {
        this.em.merge(uso);
    }
    
    @SuppressWarnings("unchecked")
	public List<UsoPuntosCabecera> listarByConcepto(Integer idConcepto){
        Query q = this.em.createNamedQuery("UsoPuntosCabecera.byConcepto");
        return (List<UsoPuntosCabecera>) q.setParameter("idConcepto", idConcepto).getResultList();
    }

    @SuppressWarnings("unchecked")
	public Object listarByFecha(String fechaInicio, String fechaFin) throws ParseException {
        Query q = this.em.createNamedQuery("UsoPuntosCabecera.byFecha");
        Date fechaInicioDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicio);
        Date fechaFinDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
        
        return (List<UsoPuntosCabecera>) 
                q.setParameter("fechaInicio", fechaInicioDate)
                .setParameter("fechaFin", fechaFinDate)
                .getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<UsoPuntosCabecera> listarByCliente(Integer idCliente){
        Query q = this.em.createNamedQuery("UsoPuntosCabecera.byCliente");
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        return (List<UsoPuntosCabecera>) q.setParameter("idCliente", cliente).getResultList();
    }
    
    public Map<String, Object> utilizarPuntos(Integer idCliente, Integer idConcepto) {
        Cliente cliente = clienteDao.getById(idCliente);
        Map<String, Object> m = new HashMap<String, Object>();
        ConceptoPuntos concepto = conceptoDao.getById(idConcepto);
        
        // Verificar si el cliente tiene bolsas suficientes para pagar el concepto
        if (bolsaDao.tieneSaldoSuficiente(idCliente, concepto.getPuntosRequeridos())){
            // Se crea el Uso Puntos cabecera
            UsoPuntosCabecera cabecera =  new UsoPuntosCabecera();
            Date fechaActual = new Date();
            cabecera.setFecha(fechaActual);
            cabecera.setConcepto(idConcepto);
            cabecera.setCliente(cliente);
            cabecera.setPuntosUtilizados(concepto.getPuntosRequeridos());
            this.em.persist(cabecera);

            // Se crean los Uso Puntos Detalle
            usoDetalleDao.agregarDetallesDeCabecera(cabecera);
            
            // Se envia el correo
            List <String> destinatarios = new LinkedList<String>();
            destinatarios.add(cliente.getEmail());
            emailDao.sendHttpEmail(destinatarios, "Comprobante de Utilizacion de Puntos", 
                    ConstantesEmail.TEMPLATE.replace("{{}}", cliente.getNombre()).replace("[[]]", concepto.getPuntosRequeridos().toString()));
            // Se crea el mensaje de respuesta
            m.put("estado", 0);
            m.put("mensaje", "Exito");
            return m;
        }
        m.put("estado", -1);
        m.put("mensaje", "El cliente no tiene puntos suficientes");
        return m;
    }
}
