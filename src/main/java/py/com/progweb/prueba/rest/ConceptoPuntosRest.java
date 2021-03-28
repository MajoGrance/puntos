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
import py.com.progweb.prueba.ejb.ConceptoPuntosDAO;
import py.com.progweb.prueba.model.ConceptoPuntos;

@Path("conceptos")
@Consumes("application/json")
@Produces("application/json")
public class ConceptoPuntosRest {

    @Inject
    private ConceptoPuntosDAO ConceptoPuntosBean;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(ConceptoPuntosBean.listar()).build();
    }

    @POST
    @Path("/")
    public Response agregar(ConceptoPuntos entity) {
        this.ConceptoPuntosBean.agregar(entity);
        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(ConceptoPuntos entity) throws Exception {
        this.ConceptoPuntosBean.actualizar(entity);
        return Response.ok().build();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@QueryParam("id") Integer id) {
        this.ConceptoPuntosBean.eliminar(id);
        return Response.ok().build();
    }

}
