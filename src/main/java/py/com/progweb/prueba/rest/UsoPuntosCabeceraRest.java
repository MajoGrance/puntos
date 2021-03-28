package py.com.progweb.prueba.rest;

import java.text.ParseException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import py.com.progweb.prueba.ejb.UsoPuntosCabeceraDAO;
import py.com.progweb.prueba.model.UsoPuntosCabecera;

@Path("usos/cabeceras")
@Consumes("application/json")
@Produces("application/json")
public class UsoPuntosCabeceraRest {

    private static final Logger LOGGER = LogManager.getLogger(UsoPuntosCabeceraRest.class);
    @Inject
    private UsoPuntosCabeceraDAO usoPuntosCabeceraBean;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(usoPuntosCabeceraBean.listar()).build();
    }

    @POST
    @Path("/")
    public Response agregar(UsoPuntosCabecera entity) {
        this.usoPuntosCabeceraBean.agregar(entity);
        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(UsoPuntosCabecera entity) throws Exception {
        this.usoPuntosCabeceraBean.actualizar(entity);
        return Response.ok().build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@QueryParam("id") Integer id) {
        this.usoPuntosCabeceraBean.eliminar(id);
        return Response.ok().build();
    }

    @GET
    @Path("/byConcepto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarByConcepto(@QueryParam("id") Integer idConcepto) {
        return Response.ok(usoPuntosCabeceraBean.listarByConcepto(idConcepto)).build();
    }

    @GET
    @Path("/byFecha")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarByFecha(
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin) {

        try {
            return Response.ok(usoPuntosCabeceraBean.listarByFecha(fechaInicio, fechaFin)).build();
        } catch (ParseException ex) {
            LOGGER.error("", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/byCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarByCliente(@QueryParam("id") Integer idCliente) {
        return Response.ok(usoPuntosCabeceraBean.listarByCliente(idCliente)).build();
    }
    
    @POST
    @Path("/cargar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response utilizarPuntos(@QueryParam("idCliente") Integer idCliente, @QueryParam("idConcepto") Integer idConcepto){
        return Response.ok(this.usoPuntosCabeceraBean.utilizarPuntos(idCliente, idConcepto)).build();
    }
}
