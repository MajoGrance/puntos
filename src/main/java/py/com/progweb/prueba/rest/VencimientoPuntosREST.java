package py.com.progweb.prueba.rest;

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

import py.com.progweb.prueba.ejb.VencimientoPuntosDAO;
import py.com.progweb.prueba.model.VencimientoPuntos;

@Path("vencimientos")
@Consumes("application/json")
@Produces("application/json")
public class VencimientoPuntosREST {

    @Inject
    private VencimientoPuntosDAO vencimientoPuntosBean;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(vencimientoPuntosBean.listar()).build();
    }

    @POST
    @Path("/")
    public Response agregar(VencimientoPuntos entity) {
        this.vencimientoPuntosBean.agregar(entity);
        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(VencimientoPuntos entity) throws Exception {
        this.vencimientoPuntosBean.actualizar(entity);
        return Response.ok().build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@QueryParam("id") Integer id) {
        this.vencimientoPuntosBean.eliminar(id);
        return Response.ok().build();
    }
}
